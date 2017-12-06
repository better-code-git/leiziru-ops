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
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.service.ServiceAreaService;

/**
 * 区域信息Controller
 * @author Forrest
 * @version 2017-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/overall/serviceArea")
public class ServiceAreaController extends BaseController {

	@Autowired
	private ServiceAreaService serviceAreaService;
	
	@ModelAttribute
	public ServiceArea get(@RequestParam(required=false) String id) {
		ServiceArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = serviceAreaService.get(id);
		}
		if (entity == null){
			entity = new ServiceArea();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ServiceArea serviceArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ServiceArea> page = serviceAreaService.findPage(new Page<ServiceArea>(request, response), serviceArea); 
		model.addAttribute("page", page);
		return "modules/overall/serviceAreaList";
	}

	@RequestMapping(value = "form")
	public String form(ServiceArea serviceArea, Model model) {
		model.addAttribute("serviceArea", serviceArea);
		return "modules/overall/serviceAreaForm";
	}

	@RequestMapping(value = "save")
	public String save(ServiceArea serviceArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, serviceArea)){
			return form(serviceArea, model);
		}
		serviceAreaService.save(serviceArea);
		addMessage(redirectAttributes, "保存区域信息成功");
		return "redirect:"+Global.getAdminPath()+"/overall/serviceArea/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ServiceArea serviceArea, RedirectAttributes redirectAttributes) {
		serviceAreaService.delete(serviceArea);
		addMessage(redirectAttributes, "删除区域信息成功");
		return "redirect:"+Global.getAdminPath()+"/overall/serviceArea/?repage";
	}
	
	
	/**
	 * 动态区域信息
	 * 下拉框使用
	 */
	@ResponseBody
	@RequestMapping(value = "selectAreas")
	public AjaxJson selectAreas(ServiceArea serviceArea, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		List<ServiceArea> serviceAreaList=serviceAreaService.findList(serviceArea);
		j.put("serviceAreaList", serviceAreaList);
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 动态区域信息
	 * 下拉框使用
	 */
	@ResponseBody
	@RequestMapping(value = "selectZoneAndMetro")
	public AjaxJson selectZoneAndMetro(ServiceArea serviceArea, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		List<ServiceArea> serviceAreaList=serviceAreaService.findZoneAndMetroList(serviceArea);
		j.put("serviceAreaList", serviceAreaList);
		j.setSuccess(true);
		return j;
	}
	
	
	

}