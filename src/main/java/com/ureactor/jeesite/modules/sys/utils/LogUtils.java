/**
 * Copyright &copy; 2012-2016
 * <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.ureactor.jeesite.modules.sys.utils;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.modules.sys.entity.Log;
import com.ureactor.jeesite.modules.sys.entity.User;
import com.ureactor.jeesite.modules.sys.interceptor.InterceptorLogEntity;
import com.ureactor.jeesite.modules.sys.interceptor.LogThread;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 日志工具类
 * @author peidawei@uworks.cc
 *
 */
public class LogUtils {

  public static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";

  /**
   * 保存日志
   */
  public static void saveLog(HttpServletRequest request, String title) {
    saveLog(request, null, null, title);
  }

  /**
   * 保存日志
   */
  public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title) {
    // 检查是否记录日志
    if(Global.isRecord(request)){
      return;
    }
    User user = UserUtils.getUser();
    if (user != null && user.getId() != null) {
      Log log = new Log();
      log.setTitle(title);
      log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
      log.setRemoteAddr(StringUtils.getRemoteAddr(request));
      log.setUserAgent(request.getHeader("user-agent"));
      log.setRequestUri(request.getRequestURI());
      log.setParams(request.getParameterMap());
      log.setMethod(request.getMethod());
      log.setCreateBy(user);
      log.setUpdateBy(user);
      log.setUpdateDate(new Date());
      log.setCreateDate(new Date());
      // 异步保存日志
      try {
        InterceptorLogEntity entiry = new InterceptorLogEntity(log, handler, ex);
        LogThread.interceptorLogQueue.put(entiry);
      } catch (Exception e) {
        e.printStackTrace(System.out);
      }
    }
  }

}
