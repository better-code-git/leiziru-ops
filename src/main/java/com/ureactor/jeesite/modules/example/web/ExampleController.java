/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.ureactor.jeesite.modules.example.web;

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
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.modules.example.entity.Example;
import com.ureactor.jeesite.modules.example.service.ExampleService;

/**
 * 活动示例Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/example/example")
public class ExampleController extends BaseController {

  @Autowired
  private ExampleService exampleService;

  @ModelAttribute
  public Example get(@RequestParam(required = false) String id) {
    Example entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = exampleService.get(id);
    }
    if (entity == null) {
      entity = new Example();
    }
    return entity;
  }

  @RequiresPermissions("example:example:view")
  @RequestMapping(value = {"list", ""})
  public String list(Example example, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<Example> page = exampleService.findPage(new Page<Example>(request, response), example);
    model.addAttribute("page", page);
    model.addAttribute("example", example);
    return "modules/example/exampleList";
  }

  @RequiresPermissions("example:example:view")
  @RequestMapping(value = "form")
  public String form(Example example, Model model) {
    model.addAttribute("example", example);
    return "modules/example/exampleForm";
  }

  @RequiresPermissions("example:example:edit")
  @RequestMapping(value = "save")
  public String save(Example example, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, example)) {
      return form(example, model);
    }
    //对ueditor传回的字符串编码成html,解决ueditor转义的问题
    example.setIntroduce(StringUtils.unescapeHtml4(example.getIntroduce()));
    exampleService.save(example);
    addMessage(redirectAttributes, "保存活动示例成功");
    return "redirect:" + Global.getAdminPath() + "/example/example/?repage";
  }

  @RequiresPermissions("example:example:delete")
  @RequestMapping(value = "delete")
  public String delete(Example example, RedirectAttributes redirectAttributes) {
    exampleService.delete(example);
    addMessage(redirectAttributes, "删除活动示例成功");
    return "redirect:" + Global.getAdminPath() + "/example/example/?repage";
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
