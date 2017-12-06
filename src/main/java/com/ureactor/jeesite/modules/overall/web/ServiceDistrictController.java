package com.ureactor.jeesite.modules.overall.web;

import java.util.Date;
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

import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.service.ServiceAreaService;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 商圈管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/overall/serviceDistrict")
public class ServiceDistrictController extends BaseController {

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
		serviceArea.setType(LzrConstant.arealevelD);
		Page<ServiceArea> page = serviceAreaService.findPage(new Page<ServiceArea>(request, response), serviceArea);
		List<ServiceArea> provinceList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
		model.addAttribute("page", page);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("serviceArea", serviceArea);
		return "modules/overall/serviceDistrictList";
	}

	@RequestMapping(value = "form")
	public String form(ServiceArea serviceArea, Model model) {
		if(serviceArea.getId()==null||"".equals(serviceArea.getId())){
			serviceArea.setType(LzrConstant.arealevelD);
			List<ServiceArea> provinceList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			model.addAttribute("provinceList", provinceList);
		}else{
			List<ServiceArea> provinceList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			List<ServiceArea> cityList = serviceAreaService.findList(new ServiceArea(String.valueOf(serviceArea.getCityId()), LzrConstant.arealevelC));
			List<ServiceArea> zoneList = serviceAreaService.findList(new ServiceArea(String.valueOf(serviceArea.getZoneId()), LzrConstant.arealevelZ));
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("cityList", cityList);
			model.addAttribute("zoneList", zoneList);
		}
		model.addAttribute("serviceArea", serviceArea);
		return "modules/overall/serviceDistrictForm";
	}
	
	@RequestMapping(value = "addProvince")
	public String addProvince(ServiceArea serviceArea, Model model) {
		serviceArea.setType(LzrConstant.arealevelP);
		model.addAttribute("serviceArea", serviceArea);
		return "modules/overall/provinceForm";
	}
	
	@RequestMapping(value = "addCity")
	public String addCity(ServiceArea serviceArea, Model model) {
		serviceArea.setType(LzrConstant.arealevelC);
		List<ServiceArea> provinceList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("serviceArea", serviceArea);
		return "modules/overall/cityForm";
	}
	
	@RequestMapping(value = "addZone")
	public String addZone(ServiceArea serviceArea, Model model) {
		serviceArea.setType(LzrConstant.arealevelZ);
		List<ServiceArea> provinceList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("serviceArea", serviceArea);
		return "modules/overall/zoneForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(ServiceArea serviceArea, Model model, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, serviceArea)) {
			message="数据校验失败";//把校验结果但会到string中
			j.setSuccess(false);
		}else{
			if(serviceAreaService.findListByCheck(serviceArea).size()>0){
				if(serviceArea.getType()==LzrConstant.arealevelP){
					message="省份已存在!";
				}
				if(serviceArea.getType()==LzrConstant.arealevelC){
					message="城市已存在!";
				}
				if(serviceArea.getType()==LzrConstant.arealevelZ){
					message="区县已存在!";
				}
				if(serviceArea.getType()==LzrConstant.arealevelP){
					message="商圈已存在!";
				}
				j.setSuccess(false);
			}else{
				if(serviceArea.getId()==null||"".endsWith(serviceArea.getId())){
					serviceArea.setIsService(LzrConstant.isService);
					serviceArea.setOpenDate(new Date());
				}
				serviceAreaService.save(serviceArea);
			}
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(ServiceArea serviceArea, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		serviceAreaService.delete(serviceArea);
		j.setMsg(message);
		return j;
	}

}
