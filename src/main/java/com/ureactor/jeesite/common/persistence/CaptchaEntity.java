package com.ureactor.jeesite.common.persistence;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author ForrestCao
 *
 */
public class CaptchaEntity {

  @NotBlank(message = "手机号不能为空")
  private String mobile;

  @NotNull(message = "验证码类型不能为空")
  private Byte type;

  public String getMobile() {
    return mobile.replaceAll(" ", "");
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "CaptchaEntity [mobile=" + mobile + ", type=" + type + "]";
  }
  
}
