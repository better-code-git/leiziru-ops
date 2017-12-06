package com.ureactor.jeesite.modules.homePage.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.homePage.entity.HomePage;
import com.ureactor.jeesite.modules.homePage.service.HomePageService;

/**
 * 管理首页Controller
 * 
 * @author caolei@juxiang.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/homePage/homePage")
public class HomePageController extends BaseController {

  @Autowired
  private HomePageService homePageService;

  @ModelAttribute
  public HomePage get(@RequestParam(required = false) String id) {
    HomePage entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = homePageService.get(id);
    }
    if (entity == null) {
      entity = new HomePage();
    }
    return entity;
  }


  @RequiresPermissions("homePage:homePage:view")
  @RequestMapping(value = {"form", ""})
  public String form(HomePage homePage, Model model) {
	homePage=homePageService.getInit();  
    model.addAttribute("homePage", homePage);
    return "modules/homePage/homePageForm";
  }

  

}
