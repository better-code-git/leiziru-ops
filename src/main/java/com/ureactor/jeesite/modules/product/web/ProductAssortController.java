package com.ureactor.jeesite.modules.product.web;

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
import com.ureactor.jeesite.modules.product.entity.ProductAssort;
import com.ureactor.jeesite.modules.product.service.ProductAssortService;

/**
 * 房源管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productAssort")
public class ProductAssortController extends BaseController {

  @Autowired
  private ProductAssortService productAssortService;

  @ModelAttribute
  public ProductAssort get(@RequestParam(required = false) String id) {
	ProductAssort entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = productAssortService.get(id);
    }
    if (entity == null) {
      entity = new ProductAssort();
    }
    return entity;
  }

  @RequiresPermissions("product:productAssort:view")
  @RequestMapping(value = {"list", ""})
  public String list(ProductAssort productAssort, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<ProductAssort> page = productAssortService.findPage(new Page<ProductAssort>(request, response), productAssort);
    model.addAttribute("page", page);
    model.addAttribute("productAssort", productAssort);
    return "modules/product/productAssortList";
  }

  @RequiresPermissions("product:productAssort:view")
  @RequestMapping(value = "form")
  public String form(ProductAssort productAssort, Model model) {
    model.addAttribute("productAssort", productAssort);
    return "modules/product/productAssortForm";
  }

  @RequiresPermissions("product:productAssort:edit")
  @RequestMapping(value = "save")
  public String save(ProductAssort productAssort, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, productAssort)) {
      return form(productAssort, model);
    }
    productAssortService.save(productAssort);
    addMessage(redirectAttributes, "保存房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/product/productAssort/?repage";
  }

  @RequiresPermissions("product:productAssort:delete")
  @RequestMapping(value = "delete")
  public String delete(ProductAssort productAssort, RedirectAttributes redirectAttributes) {
    productAssortService.delete(productAssort);
    addMessage(redirectAttributes, "删除房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/product/productAssort/?repage";
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
