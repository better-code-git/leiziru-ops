package com.ureactor.jeesite.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.ureactor.jeesite.common.config.AlidayuConfig;
import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.config.SmsConfig;
import com.ureactor.jeesite.common.constants.SmsConstants;
import com.ureactor.jeesite.common.persistence.CaptchaEntity;
import com.ureactor.jeesite.common.utils.HttpUtil;
import com.ureactor.jeesite.common.utils.StringUtils;


/**
 * 短信服务
 * 
 * @author wangshichuan@uwrks.cc
 *
 */
@Service("smsService")
public class SmsService {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Resource
  private SmsConfig smsConfig;
  @Resource
  private AlidayuConfig alidayuConfig;

  public boolean sendByAlidayu(CaptchaEntity bean, String code) {
    TaobaoClient client = new DefaultTaobaoClient(Global.getConfig("alidayu.sendMsgUrl"), Global.getConfig("alidayu.appKey"),
        Global.getConfig("alidayu.appSecret"));
    AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
    // req.setExtend("123456");
    req.setSmsType("normal");
    // String paramString = "{\"code\":\"" + code + "\",\"product\":\"" + alidayuConfig.getProduct()
    // + "\"}";
    String paramString = "{\"name\":\"" + code + "\"}";
    req.setSmsParamString(paramString);
    req.setRecNum(bean.getMobile());
    req.setSmsFreeSignName(Global.getConfig("alidayu.signName"));
    if (bean.getType() == 1) {
      req.setSmsTemplateCode(Global.getConfig("alidayu.registerTmplId"));
    } else if (bean.getType() == 3) {
      req.setSmsTemplateCode(Global.getConfig("alidayu.resetPasswordTmplId"));
    }
    AlibabaAliqinFcSmsNumSendResponse rsp = null;
    try {
      rsp = client.execute(req);
      System.out.println(rsp.getBody());
      return true;
    } catch (ApiException e) {
      logger.error("发送短信失败， mobile = {}, error={}", bean.getMobile(), e.getMessage(), e);
      return false;
    }

  }

  /**
   * 短信单发
   * 
   * @param mobile
   * @param content
   * @return true 发送成功，false 发送失败
   */
  public boolean send(String mobile, String content) {
    try {
      logger.info("发送短信, mobile = {}, content = {}", mobile, content);
      if (StringUtils.isBlank(mobile)) {
        throw new Exception("手机号不能为空");
      }
      if (StringUtils.isBlank(content)) {
        throw new Exception("短信内容不能为空");
      }
      JSONObject params = new JSONObject();
      params.put("content", content);
      params.put("mobile", mobile);
      params.put("sign", Global.getConfig("sms.sign"));
      params.put("uid", Global.getConfig("sms.uid"));
      params.put("token", Global.getConfig("sms.token"));
      String json = params.toJSONString();

      HttpUtil.doPostJson(Global.getConfig("sms.url"), json);
      return true;
    } catch (Exception e) {
      logger.error("发送短信失败， mobile = {}, content = {}, error={}", mobile, content, e.getMessage(), e);
      return false;
    }
  }

  /**
   * 短信群发
   * 
   * @param mobiles
   * @param content
   */
  public void send(List<String> mobiles, String content) {
    StringBuilder mobilesStr = new StringBuilder();
    mobiles.forEach(mobile -> {
      if (StringUtils.isNotBlank(mobile)) {
        mobilesStr.append(mobile).append(",");
      }
      if (mobiles.toString().split(",").length >= SmsConstants.MAX_COUNT) {
        this.send(mobiles.toString().replaceAll(",$", ""), content);
        mobilesStr.setLength(0);
      }
    });
    if (mobilesStr.length() > 0) {
      this.send(mobiles.toString().replaceAll(",$", ""), content);
    }
  }


}
