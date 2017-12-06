/**
 * 
 */
package com.ureactor.jeesite.common.service;

import javax.annotation.Resource;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ureactor.jeesite.common.config.EmailConfig;

/**
 * @author wangshichuan@uworks.cc
 *
 */
@Service("emailService")
public class EmailService {

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Resource
  private EmailConfig emailConfig;
  /*@Autowired
  private MemcachedService memcachedService;*/

  //private static final String staffTemplet= "尊敬的亲亲跟P虫管理员，你好！恭喜您的密码找回成功。您的新密码是%s";
  /**
   * 邮件发送缓存 EMAIL_type_email
   */
  public static final String EMAIL_KEY = "EMAIL_%s";
  /**
   * 邮件发送冷却时间(秒) 1分钟
   */
  public static final int EMAIL_COOL_TIME = 60;
  
  /*public String send(String email, String subject) {

    // 发送验证
    //sendVidate(bean);

    // 更新密码
    String password = PasswordUtil.getRandomPassword();
    //User user = userService.findByEmail(bean.getEmail());
    //user.setPassword(PasswordUtil.encode(password));
    //userService.update(user);

    // 发送
    //EmailSendInfo sendInfo = EmailSendInfo.getEmailType(bean.getType());

    if(!sendCommonMail(email, subject, String.format(staffTemplet, password))){
      return "error";
    }

    // 记录冷却时间
    String emailKey = String.format(EMAIL_KEY, email);
    this.memcachedService.set(emailKey, EMAIL_COOL_TIME, email);
    
    return password;
  }*/

  /**
   * 发送验证,邮箱是否注册、是否发送频繁等
   * 
   * @param bean
   */
  /*private void sendVidate(EmailRequestBean bean) {

    User user = userService.findByEmail(bean.getEmail());

    // 注册 验证该邮箱是否已存在
    if (bean.getType() == EmailSendInfo.REGIST.code) {
      if (user != null) {
        ExceptionUtil.throwException(1, "邮箱已注册");
      }
    }

    // 找回密码
    else if (bean.getType() == EmailSendInfo.FIND_PWD.code) {
      if (user == null) {
        ExceptionUtil.throwException(1, "邮箱未注册");
      } else if (!bean.getMobile().equals(user.getMobile())) {
        ExceptionUtil.throwException(1, "邮箱错误");
      }
    }

    // 是否未超过冷却时间
    String emailKey = String.format(EmailConstants.EMAIL_KEY, bean.getType(), bean.getEmail());
    Object obj = memcachedService.get(emailKey);
    if (obj != null) {
      ExceptionUtil.throwException(2, "请求太频繁，请稍后再试");
    }

  }*/

  /**
   * 发送普通邮件
   * 
   * @param toMailAddr 收信人地址
   * @param sendInfo
   */
  public boolean sendCommonMail(String toMailAddr, String subject, String message) {
    HtmlEmail hemail = new HtmlEmail();
    try {
      hemail.setHostName(emailConfig.getSmtpHost());
      hemail.setSmtpPort(emailConfig.getSmtpPort());
      hemail.setFrom(emailConfig.getFromEmail(), emailConfig.getFromName());
      hemail.setAuthentication(emailConfig.getFromEmail(), emailConfig.getPassword());
      hemail.setSubject(subject);
      hemail.setMsg(message);
      hemail.addTo(toMailAddr);
      hemail.setCharset("utf-8");
      hemail.setSSLOnConnect(true);
      hemail.send();
    } catch (Exception e) {
      logger.error(e.getMessage(), e.getCause());
      return false;
    }
    return true;
  }

}
