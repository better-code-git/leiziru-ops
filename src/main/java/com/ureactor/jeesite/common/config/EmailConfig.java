package com.ureactor.jeesite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Methol on 2016/1/13.
 */
@ConfigurationProperties(prefix = "email")
@Component
public class EmailConfig {
  private String mandrillappKey;

  private String fromName;

  private String fromEmail;

  private String password;

  private String smtpHost;
  private Integer smtpPort;

  public String getMandrillappKey() {
    return mandrillappKey;
  }

  public void setMandrillappKey(String mandrillappKey) {
    this.mandrillappKey = mandrillappKey;
  }

  public String getFromName() {
    return fromName;
  }

  public void setFromName(String fromName) {
    this.fromName = fromName;
  }

  public String getFromEmail() {
    return fromEmail;
  }

  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSmtpHost() {
    return smtpHost;
  }

  public void setSmtpHost(String smtpHost) {
    this.smtpHost = smtpHost;
  }

  public Integer getSmtpPort() {
    return smtpPort == null ? 25 : smtpPort;
  }

  public void setSmtpPort(Integer smtpPort) {
    this.smtpPort = smtpPort;
  }

}
