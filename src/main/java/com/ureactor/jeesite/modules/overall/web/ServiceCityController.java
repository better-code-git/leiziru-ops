package com.ureactor.jeesite.modules.overall.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.service.ServiceAreaService;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 开通城市Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/overall/serviceCity")
public class ServiceCityController extends BaseController {

	@Autowired
	private ServiceAreaService serviceAreaService;

	@ModelAttribute
	public ServiceArea get(@RequestParam(required = false) String id) {
		ServiceArea entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = serviceAreaService.get(id);
		}
		if (entity == null) {
			entity = new ServiceArea();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(ServiceArea serviceArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		ServiceArea serviceAreaQry = new ServiceArea();
		serviceAreaQry.setOpenStatus(LzrConstant.openStatus);
		serviceAreaQry.setType(LzrConstant.arealevelC);
		List<ServiceArea> serviceCityList = serviceAreaService.findList(serviceAreaQry);
		List<ServiceArea> provinceList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
		serviceArea.setType(LzrConstant.arealevelC);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("serviceArea", serviceArea);
		model.addAttribute("serviceCityList", serviceCityList);
		return "modules/overall/serviceCityList";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(ServiceArea serviceArea, Model model, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		serviceArea.setId(String.valueOf(serviceArea.getCityId()));
		serviceArea.setOpenStatus(LzrConstant.openStatus);
		serviceAreaService.updateOpenStatus(serviceArea);
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "delete")
	public String delete(ServiceArea serviceArea, RedirectAttributes redirectAttributes) {
		serviceAreaService.delete(serviceArea);
		addMessage(redirectAttributes, "删除开通城市成功");
		return "redirect:" + Global.getAdminPath() + "/webBanner/webBanner/?repage";
	}

}
