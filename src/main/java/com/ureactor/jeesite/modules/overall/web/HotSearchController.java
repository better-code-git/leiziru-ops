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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.overall.entity.HotSearch;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.service.HotSearchService;
import com.ureactor.jeesite.modules.overall.service.ServiceAreaService;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 热搜数据Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/overall/hotSearch")
public class HotSearchController extends BaseController {

	@Autowired
	private HotSearchService hotSearchService;
	@Autowired
	private ServiceAreaService serviceAreaService;

	@ModelAttribute
	public HotSearch get(@RequestParam(required = false) String id) {
		HotSearch entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = hotSearchService.get(id);
		}
		if (entity == null) {
			entity = new HotSearch();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(HotSearch hotSearch, HttpServletRequest request, HttpServletResponse response, Model model) {
		ServiceArea serviceAreaQry = new ServiceArea();
		serviceAreaQry.setOpenStatus(LzrConstant.openStatus);
		serviceAreaQry.setType(LzrConstant.arealevelC);
		List<ServiceArea> serviceCityList = serviceAreaService.findList(serviceAreaQry);
		ServiceArea serviceAreaInit = serviceCityList.get(0);
		hotSearch.setId(serviceAreaInit.getId());
		List<HotSearch> hotSearchList = hotSearchService.findList(hotSearch);
		model.addAttribute("serviceCityList", serviceCityList);
		model.addAttribute("hotSearchList", hotSearchList);
		model.addAttribute("hotSearch", hotSearch);
		return "modules/overall/hotSearchList";
	}

	@RequestMapping(value = "form")
	public String form(HotSearch hotSearch, Model model) {
		model.addAttribute("hotSearch", hotSearch);
		return "modules/hotSearch/hotSearchForm";
	}

	@RequestMapping(value = "save")
	public String save(HotSearch hotSearch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hotSearch)) {
			return form(hotSearch, model);
		}
		hotSearchService.save(hotSearch);
		addMessage(redirectAttributes, "保存热搜数据成功");
		return "redirect:" + Global.getAdminPath() + "/overall/hotSearch/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(HotSearch hotSearch, RedirectAttributes redirectAttributes) {
		hotSearchService.delete(hotSearch);
		addMessage(redirectAttributes, "删除热搜数据成功");
		return "redirect:" + Global.getAdminPath() + "/overall/hotSearch/?repage";
	}

}
