package com.ureactor.jeesite.modules.advertisement.web;

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

import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.advertisement.entity.Advertisement;
import com.ureactor.jeesite.modules.advertisement.service.AdvertisementService;

/**
 * 广告推荐Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/advertisement/advertisement")
public class AdvertisementController extends BaseController {

	@Autowired
	private AdvertisementService advertisementService;

	@ModelAttribute
	public Advertisement get(@RequestParam(required = false) String id) {
		Advertisement entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = advertisementService.get(id);
		}
		if (entity == null) {
			entity = new Advertisement();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Advertisement advertisement, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Advertisement> page = advertisementService.findPage(new Page<Advertisement>(request, response),
				advertisement);
		model.addAttribute("page", page);
		model.addAttribute("advertisement", advertisement);
		return "modules/advertisement/advertisementList";
	}

	@RequestMapping(value = "form")
	public String form(Advertisement advertisement, Model model) {
		model.addAttribute("advertisement", advertisement);
		return "modules/advertisement/advertisementForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(Advertisement advertisement, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, advertisement)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}else{
			if(advertisementService.findListByCheck(advertisement).size()>0){
				message="房源已存在!";
				j.setSuccess(false);
			}else{
				advertisementService.save(advertisement);
			}
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(Advertisement advertisement, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		advertisementService.delete(advertisement);
		j.setMsg(message);
		return j;
	}

}
