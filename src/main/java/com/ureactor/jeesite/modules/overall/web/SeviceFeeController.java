package com.ureactor.jeesite.modules.overall.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.overall.entity.SeviceFee;
import com.ureactor.jeesite.modules.overall.service.SeviceFeeService;

/**
 * 服务费管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/overall/seviceFee")
public class SeviceFeeController extends BaseController {

	@Autowired
	private SeviceFeeService seviceFeeService;

	@ModelAttribute
	public SeviceFee get(@RequestParam(required = false) String id) {
		SeviceFee entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = seviceFeeService.get(id);
		}
		if (entity == null) {
			entity = new SeviceFee();
		}
		return entity;
	}

	@RequestMapping(value = { "form", "" })
	public String form(SeviceFee seviceFee, Model model) {
		List<SeviceFee> serviceFeeList = seviceFeeService.findList(seviceFee);
		if (serviceFeeList.size() > 0) {
			seviceFee = serviceFeeList.get(0);
		}
		model.addAttribute("seviceFee", seviceFee);
		return "modules/overall/seviceFeeForm";
	}

	@RequestMapping(value = "save")
	public String save(SeviceFee seviceFee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, seviceFee)) {
			return form(seviceFee, model);
		}
		seviceFeeService.save(seviceFee);
		addMessage(redirectAttributes, "服务费设置成功");
		return "redirect:" + Global.getAdminPath() + "/overall/seviceFee/?repage";
	}

}
