package com.ureactor.jeesite.common.security.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.utils.DateUtils;
import com.ureactor.jeesite.common.utils.IdGen;
import com.ureactor.jeesite.common.utils.RedisUtils;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.Servlets;

/**
 * 自定义会话授权类 使用spring-data-redis去实现
 * 
 * @author fangpengli@uworks.cc
 *
 */
@Component("sessionDAO")
public class RedisSessionDAO extends AbstractSessionDAO implements SessionDAO,InitializingBean {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private String sessionKeyPrefix = "shiro_session";
  
  @Autowired
  private IdGen idgen;
  
  @Override
  public void afterPropertiesSet() throws Exception {
    setSessionIdGenerator(idgen);
  }
  
  public RedisSessionDAO(){
    super();
  }

  @Override
  public void update(Session session) throws UnknownSessionException {
    if (session == null || session.getId() == null) {
      return;
    }
    HttpServletRequest request = Servlets.getRequest();
    if (request != null) {
      String uri = request.getServletPath();
      // 如果是静态文件，则不更新SESSION
      if (Servlets.isStaticFile(uri)) {
        return;
      }
      // 如果是视图文件，则不更新SESSION
      if (StringUtils.startsWith(uri, Global.getConfig("spring.mvc.view.prefix"))
          && StringUtils.endsWith(uri, Global.getConfig("spring.mvc.view.suffix"))) {
        return;
      }
      // 手动控制不更新SESSION
      String updateSession = request.getParameter("updateSession");
      if (Global.FALSE.equals(updateSession) || Global.NO.equals(updateSession)){
          return;
      }
    }

    // 获取登录者编号
    PrincipalCollection pc = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
    String principalId = pc != null ? pc.getPrimaryPrincipal().toString() : StringUtils.EMPTY;

    RedisUtils.hput(sessionKeyPrefix, session.getId().toString(),
        principalId + "|" + session.getTimeout() + "|" + session.getLastAccessTime().getTime());
    RedisUtils.put(sessionKeyPrefix + session.getId().toString(), session);
    
    // 设置超期时间
    int timeoutSeconds = (int) (session.getTimeout() / 1000);
    RedisUtils.setExpired(sessionKeyPrefix + session.getId().toString(), timeoutSeconds);

    logger.debug("update {} {}", session.getId(), request != null ? request.getRequestURI() : "");
  }

  @Override
  public void delete(Session session) {
    if (session == null || session.getId() == null) {
      return;
    }

    RedisUtils.hdelete(sessionKeyPrefix, session.getId().toString());
    RedisUtils.delete(sessionKeyPrefix + session.getId().toString());
    logger.debug("delete {} ", session.getId());
  }

  @Override
  public Collection<Session> getActiveSessions() {
    return getActiveSessions(true);
  }

  @Override
  public Collection<Session> getActiveSessions(boolean includeLeave) {
    return getActiveSessions(includeLeave, null, null);
  }

  @Override
  public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession) {
    Set<Session> sessions = Sets.newHashSet();

    Map<String, String> map = RedisUtils.hgetAll(sessionKeyPrefix);
    for (Map.Entry<String, String> e : map.entrySet()) {
      if (StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue())) {

        String[] ss = StringUtils.split(e.getValue(), "|");
        if (ss != null && ss.length == 3) {
          SimpleSession session = new SimpleSession();
          session.setId(e.getKey());
          session.setAttribute("principalId", ss[0]);
          session.setTimeout(Long.valueOf(ss[1]));
          session.setLastAccessTime(new Date(Long.valueOf(ss[2])));
          try {
            // 验证SESSION
            session.validate();

            boolean isActiveSession = false;
            // 不包括离线并符合最后访问时间小于等于3分钟条件。
            if (includeLeave || DateUtils.pastMinutes(session.getLastAccessTime()) <= 3) {
              isActiveSession = true;
            }
            // 符合登陆者条件。
            if (principal != null) {
              PrincipalCollection pc = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
              if (principal.toString().equals(pc != null ? pc.getPrimaryPrincipal().toString() : StringUtils.EMPTY)) {
                isActiveSession = true;
              }
            }
            // 过滤掉的SESSION
            if (filterSession != null && filterSession.getId().equals(session.getId())) {
              isActiveSession = false;
            }
            if (isActiveSession) {
              sessions.add(session);
            }

          }
          // SESSION验证失败
          catch (Exception e2) {
            RedisUtils.hdelete(sessionKeyPrefix, e.getKey());
          }
        }
        // 存储的SESSION不符合规则
        else {
          RedisUtils.hdelete(sessionKeyPrefix, e.getKey());
        }
      }
      // 存储的SESSION无Value
      else if (StringUtils.isNotBlank(e.getKey())) {
        RedisUtils.hdelete(sessionKeyPrefix, e.getKey());
      }
    }
    logger.info("getActiveSessions size: {} ", sessions.size());

    return sessions;
  }

  @Override
  protected Serializable doCreate(Session session) {
    HttpServletRequest request = Servlets.getRequest();
    if (request != null) {
      String uri = request.getServletPath();
      // 如果是静态文件，则不创建SESSION
      if (Servlets.isStaticFile(uri)) {
        return null;
      }
    }
    Serializable sessionId = this.generateSessionId(session);
    this.assignSessionId(session, sessionId);
    this.update(session);
    return sessionId;
  }

  @Override
  protected Session doReadSession(Serializable sessionId) {

    Session s = null;
    HttpServletRequest request = Servlets.getRequest();
    if (request != null) {
      String uri = request.getServletPath();
      // 如果是静态文件，则不获取SESSION
      if (Servlets.isStaticFile(uri)) {
        return null;
      }
      s = (Session) request.getAttribute("session_" + sessionId);
    }
    if (s != null) {
      return s;
    }

    Session session = null;
    session = (Session) RedisUtils.get(sessionKeyPrefix + sessionId.toString());
    logger.debug("doReadSession {} {}", sessionId, request != null ? request.getRequestURI() : "");

    if (request != null && session != null) {
      request.setAttribute("session_" + sessionId, session);
    }

    return session;
  }
}
