package com.ureactor.jeesite.modules.officeWeb.web;

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
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.officeWeb.entity.WebBanner;
import com.ureactor.jeesite.modules.officeWeb.service.WebBannerService;

/**
 * 房源管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/officeWeb/webBanner")
public class WebBannerController extends BaseController {

  @Autowired
  private WebBannerService webBannerService;

  @ModelAttribute
  public WebBanner get(@RequestParam(required = false) String id) {
	WebBanner entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = webBannerService.get(id);
    }
    if (entity == null) {
      entity = new WebBanner();
    }
    return entity;
  }

  @RequestMapping(value = {"list", ""})
  public String list(WebBanner webBanner, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<WebBanner> page = webBannerService.findPage(new Page<WebBanner>(request, response), webBanner);
    model.addAttribute("page", page);
    model.addAttribute("webBanner", webBanner);
    return "modules/officeWeb/webBannerList";
  }

  @RequestMapping(value = "form")
  public String form(WebBanner webBanner, Model model) {
    model.addAttribute("webBanner", webBanner);
    return "modules/officeWeb/webBannerForm";
  }

  @RequestMapping(value = "save")
  public String save(WebBanner webBanner, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, webBanner)) {
      return form(webBanner, model);
    }
    webBannerService.save(webBanner);
    addMessage(redirectAttributes, "保存房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/webBanner/?repage";
  }

  @RequestMapping(value = "delete")
  public String delete(WebBanner webBanner, RedirectAttributes redirectAttributes) {
    webBannerService.delete(webBanner);
    addMessage(redirectAttributes, "删除房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/webBanner/?repage";
  }

}
