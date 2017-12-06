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
import com.ureactor.jeesite.modules.product.entity.Product;
import com.ureactor.jeesite.modules.product.service.ProductService;

/**
 * 房源管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productAveragePrice")
public class ProductBlockPriceController extends BaseController {

  @Autowired
  private ProductService productService;

  @ModelAttribute
  public Product get(@RequestParam(required = false) String id) {
	Product entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = productService.get(id);
    }
    if (entity == null) {
      entity = new Product();
    }
    return entity;
  }

  @RequiresPermissions("product:product:view")
  @RequestMapping(value = {"list", ""})
  public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
    model.addAttribute("page", page);
    model.addAttribute("product", product);
    return "modules/product/productList";
  }

  @RequiresPermissions("product:product:view")
  @RequestMapping(value = "form")
  public String form(Product product, Model model) {
    model.addAttribute("product", product);
    return "modules/product/productForm";
  }

  @RequiresPermissions("product:product:edit")
  @RequestMapping(value = "save")
  public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, product)) {
      return form(product, model);
    }
    productService.save(product);
    addMessage(redirectAttributes, "保存房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/product/product/?repage";
  }

  @RequiresPermissions("product:product:delete")
  @RequestMapping(value = "delete")
  public String delete(Product product, RedirectAttributes redirectAttributes) {
    productService.delete(product);
    addMessage(redirectAttributes, "删除房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/product/product/?repage";
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
