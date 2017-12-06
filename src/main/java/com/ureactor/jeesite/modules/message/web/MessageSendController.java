package com.ureactor.jeesite.modules.message.web;

import java.util.List;

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
import com.ureactor.jeesite.modules.member.entity.Member;
import com.ureactor.jeesite.modules.message.entity.Message;
import com.ureactor.jeesite.modules.message.entity.MessageDemo;
import com.ureactor.jeesite.modules.message.entity.MessageSend;
import com.ureactor.jeesite.modules.message.service.MessageDemoService;
import com.ureactor.jeesite.modules.message.service.MessageSendService;
import com.ureactor.jeesite.modules.message.service.MessageService;

/**
 * 消息发送Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/message/messageSend")
public class MessageSendController extends BaseController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private MessageSendService messageSendService;
  
  @Autowired
  private MessageDemoService messageDemoService;
  
  @ModelAttribute
  public MessageSend get(@RequestParam(required = false) String id) {
	MessageSend entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = messageSendService.get(id);
    }
    if (entity == null) {
      entity = new MessageSend();
    }
    return entity;
  }

  @RequiresPermissions("message:messageSend:view")
  @RequestMapping(value = {"list", ""})
  public String list(MessageSend messageSend, HttpServletRequest request, HttpServletResponse response, Model model) {
    if(messageSend.getMember()==null){
    	messageSend.setMember(new Member());
    }
	Page<MessageSend> page = messageSendService.findPage(new Page<MessageSend>(request, response), messageSend);
    List<MessageDemo> messageDemoList =messageDemoService.findList(new MessageDemo());
    model.addAttribute("page", page);
    model.addAttribute("messageSend", messageSend);
    model.addAttribute("messageDemoList", messageDemoList);
    return "modules/message/messageSendList";
  }

  @RequiresPermissions("message:message:send")
  @RequestMapping(value = "send")
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

  @RequiresPermissions("message:message:edit")
  @RequestMapping(value = "sendMessage")
  public String sendMessage(Message message, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, message)) {
      return form(message, model);
    }
    messageService.save(message);
    addMessage(redirectAttributes, "保存消息管理成功");
    return "redirect:" + Global.getAdminPath() + "/message/message/?repage";
  }

}
