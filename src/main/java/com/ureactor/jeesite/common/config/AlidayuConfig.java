package com.ureactor.jeesite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alidayu")
@Component
public class AlidayuConfig {

  private String product;
  private String appKey;
  private String appSecret;
  private String signName;
  private String sendMsgUrl;
  private String registerTmplId;
  private String resetPasswordTmplId;

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public String getSignName() {
    return signName;
  }

  public void setSignName(String signName) {
    this.signName = signName;
  }

  public String getSendMsgUrl() {
    return sendMsgUrl;
  }

  public void setSendMsgUrl(String sendMsgUrl) {
    this.sendMsgUrl = sendMsgUrl;
  }

  public String getRegisterTmplId() {
    return registerTmplId;
  }

  public void setRegisterTmplId(String registerTmplId) {
    this.registerTmplId = registerTmplId;
  }

  public String getResetPasswordTmplId() {
    return resetPasswordTmplId;
  }

  public void setResetPasswordTmplId(String resetPasswordTmplId) {
    this.resetPasswordTmplId = resetPasswordTmplId;
  }

}
