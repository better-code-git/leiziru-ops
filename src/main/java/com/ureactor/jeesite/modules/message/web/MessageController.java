/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.ureactor.jeesite.modules.message.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.message.entity.Message;
import com.ureactor.jeesite.modules.message.service.MessageService;

/**
 * 消息管理Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/message/message")
public class MessageController extends BaseController {

  @Autowired
  private MessageService messageService;

  @ModelAttribute
  public Message get(@RequestParam(required = false) String id) {
	Message entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = messageService.get(id);
    }
    if (entity == null) {
      entity = new Message();
    }
    return entity;
  }

  @RequiresPermissions("message:message:view")
  @RequestMapping(value = {"list", ""})
  public String list(Message message, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<Message> page = messageService.findPage(new Page<Message>(request, response), message);
    model.addAttribute("page", page);
    model.addAttribute("message", message);
    return "modules/message/messageList";
  }

  @RequiresPermissions("message:message:view")
  @RequestMapping(value = "form")
  public String form(Message message, Model model) {
    model.addAttribute("message", message);
    return "modules/message/messageForm";
  }

  @RequiresPermissions("message:message:edit")
  @RequestMapping(value = "save")
  public String save(Message message, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, message)) {
      return form(message, model);
    }
    messageService.save(message);
    addMessage(redirectAttributes, "保存消息管理成功");
    return "redirect:" + Global.getAdminPath() + "/message/message/?repage";
  }

  @RequiresPermissions("message:message:delete")
  @RequestMapping(value = "delete")
  public String delete(Message message, RedirectAttributes redirectAttributes) {
    messageService.delete(message);
    addMessage(redirectAttributes, "删除消息管理成功");
    return "redirect:" + Global.getAdminPath() + "/message/message/?repage";
  }

  /**
   * 跳转到融云页面
   * 
   * @return
   */
  @RequestMapping(value = "rongIM")
  public String rongIM() {
    return "modules/rongim/im";
  }

}
