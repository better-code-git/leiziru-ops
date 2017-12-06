package com.ureactor.jeesite.modules.recommend.web;

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
import com.ureactor.jeesite.modules.recommend.entity.Recommend;
import com.ureactor.jeesite.modules.recommend.service.RecommendService;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 推荐信息Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/recommend/recommend")
public class RecommendController extends BaseController {

	@Autowired
	private RecommendService recommendService;
	@Autowired
	private ServiceAreaService serviceAreaService;

	@ModelAttribute
	public Recommend get(@RequestParam(required = false) String id) {
		Recommend entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = recommendService.get(id);
		}
		if (entity == null) {
			entity = new Recommend();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Recommend recommend, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Recommend> page = recommendService.findPage(new Page<Recommend>(request, response), recommend);
		model.addAttribute("page", page);
		model.addAttribute("recommend", recommend);
		return "modules/recommend/recommendList";
	}

	@RequestMapping(value = "form")
	public String form(Recommend recommend, Model model) {
		if(null==recommend.getId()||"".equals(recommend.getId())){
			List<ServiceArea> serviceAreaListP = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			model.addAttribute("serviceAreaListP", serviceAreaListP);
		}else{
			List<ServiceArea> serviceAreaListP = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			List<ServiceArea> serviceAreaListC = serviceAreaService.findList(new ServiceArea(String.valueOf(recommend.getProductCityId()), LzrConstant.arealevelC));
			List<ServiceArea> serviceAreaListZ = serviceAreaService.findList(new ServiceArea(String.valueOf(recommend.getProductZoneId()), LzrConstant.arealevelZ));
			List<ServiceArea> serviceAreaListM = serviceAreaService.findList(new ServiceArea(String.valueOf(recommend.getProductMetroId()), LzrConstant.arealevelM));
			List<ServiceArea> serviceAreaListD= serviceAreaService.findList(new ServiceArea(String.valueOf(recommend.getProductDistrictId()), LzrConstant.arealevelD));
			model.addAttribute("serviceAreaListP", serviceAreaListP);
			model.addAttribute("serviceAreaListC", serviceAreaListC);
			model.addAttribute("serviceAreaListZ", serviceAreaListZ);
			model.addAttribute("serviceAreaListM", serviceAreaListM);
			model.addAttribute("serviceAreaListD", serviceAreaListD);
		}
		model.addAttribute("recommend", recommend);
		return "modules/recommend/recommendForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(Recommend recommend, Model model, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, recommend)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		recommendService.save(recommend);
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(Recommend recommend, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		recommendService.delete(recommend);
		j.setMsg(message);
		return j;
	}

}
