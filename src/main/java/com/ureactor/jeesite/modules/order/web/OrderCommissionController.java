/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.ureactor.jeesite.modules.order.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.order.entity.OrderCommission;
import com.ureactor.jeesite.modules.order.service.OrderCommissionService;

/**
 * 委托订单Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderCommission")
public class OrderCommissionController extends BaseController {

  @Autowired
  private OrderCommissionService orderCommissionService;

  @ModelAttribute
  public OrderCommission get(@RequestParam(required = false) String id) {
	    OrderCommission entity = null;
	    if (StringUtils.isNotBlank(id)) {
	      entity = orderCommissionService.get(id);
	    }
	    if (entity == null) {
	      entity = new OrderCommission();
	    }
	    return entity;
  }

  @RequiresPermissions("order:orderCommission:view")
  @RequestMapping(value = {"list", ""})
  public String list(OrderCommission orderCommission, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<OrderCommission> page = orderCommissionService.findPage(new Page<OrderCommission>(request, response), orderCommission);
    model.addAttribute("page", page);
    model.addAttribute("orderCommission", orderCommission);
    return "modules/order/orderCommissionList";
  }

  @RequiresPermissions("order:orderCommission:view")
  @RequestMapping(value = "form")
  public String form(OrderCommission orderCommission, Model model) {
    model.addAttribute("orderCommission", orderCommission);
    return "modules/order/orderCommissionForm";
  }

  @RequiresPermissions("order:orderCommission:edit")
  @RequestMapping(value = "save")
  public String save(OrderCommission orderCommission, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, orderCommission)) {
      return form(orderCommission, model);
    }
    orderCommissionService.save(orderCommission);
    addMessage(redirectAttributes, "保存订单管理成功");
    return "redirect:" + Global.getAdminPath() + "/order/orderCommission/?repage";
  }

  @RequiresPermissions("order:orderCommission:delete")
  @RequestMapping(value = "delete")
  public String delete(OrderCommission orderCommission, RedirectAttributes redirectAttributes) {
    orderCommissionService.delete(orderCommission);
    addMessage(redirectAttributes, "删除订单管理成功");
    return "redirect:" + Global.getAdminPath() + "/order/orderCommission/?repage";
  }

  @RequestMapping(value = "deleteAll")
 	@ResponseBody
 	public AjaxJson deleteAll(OrderCommission orderCommission, RedirectAttributes redirectAttributes) {
 		AjaxJson j = new AjaxJson();
 		String message = "操作成功！";
 		j.setSuccess(true);
 		String ids = orderCommission.getIds();
 		if(ids!=null&&!"".equals(ids)){
 			String idArray[] =ids.split(",");
 			List<String> idList =new ArrayList<String>();
 			for (String id : idArray) {
 				idList.add(id);
 			}
 			OrderCommission odc =new OrderCommission();
 			odc.setIdList(idList);
 			orderCommissionService.deleteBatchByLogic(odc);
 		}
 		j.setMsg(message);
 		return j;
 	}

}
