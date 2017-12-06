package com.ureactor.jeesite.modules.message.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.message.entity.MessageDemo;
import com.ureactor.jeesite.modules.message.service.MessageDemoService;

/**
 * 消息模板Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/message/messageDemo")
public class MessageDemoController extends BaseController {

	@Autowired
	private MessageDemoService messageDemoService;

	@ModelAttribute
	public MessageDemo get(@RequestParam(required = false) String id) {
		MessageDemo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = messageDemoService.get(id);
		}
		if (entity == null) {
			entity = new MessageDemo();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(MessageDemo messageDemo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MessageDemo> page = messageDemoService.findPage(new Page<MessageDemo>(request, response), messageDemo);
		model.addAttribute("page", page);
		model.addAttribute("messageDemo", messageDemo);
		return "modules/message/messageDemoList";
	}

	@RequestMapping(value = "form")
	public String form(MessageDemo messageDemo, Model model) {
		model.addAttribute("messageDemo", messageDemo);
		return "modules/message/messageDemoForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(MessageDemo messageDemo, Model model, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, messageDemo)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		messageDemoService.save(messageDemo);
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(MessageDemo messageDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		messageDemoService.delete(messageDemo);
		j.setMsg(message);
		return j;
	}

}
