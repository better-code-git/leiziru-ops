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
import com.ureactor.jeesite.modules.officeWeb.entity.FriendlyLink;
import com.ureactor.jeesite.modules.officeWeb.service.FriendlyLinkService;

/**
 * 房源管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/officeWeb/friendlyLink")
public class FriendlyLinkController extends BaseController {

  @Autowired
  private FriendlyLinkService friendlyLinkService;

  @ModelAttribute
  public FriendlyLink get(@RequestParam(required = false) String id) {
	  FriendlyLink entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = friendlyLinkService.get(id);
    }
    if (entity == null) {
      entity = new FriendlyLink();
    }
    return entity;
  }

  @RequestMapping(value = {"list", ""})
  public String list(FriendlyLink friendlyLink, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<FriendlyLink> page = friendlyLinkService.findPage(new Page<FriendlyLink>(request, response), friendlyLink);
    model.addAttribute("page", page);
    model.addAttribute("friendlyLink", friendlyLink);
    return "modules/officeWeb/friendlyLinkForm";
  }

  @RequestMapping(value = "form")
  public String form(FriendlyLink friendlyLink, Model model) {
    model.addAttribute("friendlyLink", friendlyLink);
    return "modules/officeWeb/friendlyLinkForm";
  }

  @RequestMapping(value = "save")
  public String save(FriendlyLink friendlyLink, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, friendlyLink)) {
      return form(friendlyLink, model);
    }
    friendlyLinkService.save(friendlyLink);
    addMessage(redirectAttributes, "保存房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/friendlyLink/?repage";
  }

  @RequestMapping(value = "delete")
  public String delete(FriendlyLink friendlyLink, RedirectAttributes redirectAttributes) {
    friendlyLinkService.delete(friendlyLink);
    addMessage(redirectAttributes, "删除房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/friendlyLink/?repage";
  }

  /**
   * 跳转到融云页面
   * 
   * @return
   */
  @RequestMapping(value = "rongIM")
  public String rongIM() {
    return "modules/rongim/im";
  }

}
