package com.ureactor.jeesite.modules.officeWeb.web;

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
import com.ureactor.jeesite.modules.officeWeb.entity.Recruit;
import com.ureactor.jeesite.modules.officeWeb.service.RecruitService;

/**
 * 房源管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/officeWeb/recruit")
public class RecruitController extends BaseController {

  @Autowired
  private RecruitService recruitService;

  @ModelAttribute
  public Recruit get(@RequestParam(required = false) String id) {
	  Recruit entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = recruitService.get(id);
    }
    if (entity == null) {
      entity = new Recruit();
    }
    return entity;
  }

  @RequestMapping(value = {"list", ""})
  public String list(Recruit recruit, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<Recruit> page = recruitService.findPage(new Page<Recruit>(request, response), recruit);
    model.addAttribute("page", page);
    model.addAttribute("recruit", recruit);
    return "modules/officeWeb/recruitList";
  }

  @RequestMapping(value = "form")
  public String form(Recruit recruit, Model model) {
    model.addAttribute("recruit", recruit);
    return "modules/officeWeb/recruitForm";
  }

  @RequestMapping(value = "save")
  public String save(Recruit recruit, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, recruit)) {
      return form(recruit, model);
    }
    recruitService.save(recruit);
    addMessage(redirectAttributes, "保存房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/recruit/?repage";
  }

  @RequiresPermissions("recruit:recruit:delete")
  @RequestMapping(value = "delete")
  public String delete(Recruit recruit, RedirectAttributes redirectAttributes) {
    recruitService.delete(recruit);
    addMessage(redirectAttributes, "删除房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/recruit/?repage";
  }


}
