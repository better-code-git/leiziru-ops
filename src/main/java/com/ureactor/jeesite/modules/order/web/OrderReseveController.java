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
import com.ureactor.jeesite.modules.order.entity.OrderReseve;
import com.ureactor.jeesite.modules.order.service.OrderReseveService;

/**
 * 订单管理Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderReseve")
public class OrderReseveController extends BaseController {

  @Autowired
  private OrderReseveService orderReseveService;

  @ModelAttribute
  public OrderReseve get(@RequestParam(required = false) String id) {
	    OrderReseve entity = null;
	    if (StringUtils.isNotBlank(id)) {
	      entity = orderReseveService.get(id);
	    }
	    if (entity == null) {
	      entity = new OrderReseve();
	    }
	    return entity;
  }

  @RequiresPermissions("order:orderReseve:view")
  @RequestMapping(value = {"list", ""})
  public String list(OrderReseve orderReseve, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<OrderReseve> page = orderReseveService.findPage(new Page<OrderReseve>(request, response), orderReseve);
    model.addAttribute("page", page);
    model.addAttribute("orderReseve", orderReseve);
    return "modules/order/orderReseveList";
  }

  @RequiresPermissions("order:orderReseve:view")
  @RequestMapping(value = "form")
  public String form(OrderReseve orderReseve, Model model) {
    model.addAttribute("orderReseve", orderReseve);
    return "modules/order/orderReseveForm";
  }

  @RequiresPermissions("order:orderReseve:edit")
  @RequestMapping(value = "save")
  public String save(OrderReseve orderReseve, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, orderReseve)) {
      return form(orderReseve, model);
    }
    orderReseveService.save(orderReseve);
    addMessage(redirectAttributes, "保存订单管理成功");
    return "redirect:" + Global.getAdminPath() + "/order/orderReseve/?repage";
  }

  @RequiresPermissions("order:orderReseve:delete")
  @RequestMapping(value = "delete")
  public String delete(OrderReseve orderReseve, RedirectAttributes redirectAttributes) {
	  orderReseveService.delete(orderReseve);
    addMessage(redirectAttributes, "删除订单管理成功");
    return "redirect:" + Global.getAdminPath() + "/order/orderReseve/?repage";
  }

  @RequestMapping(value = "deleteAll")
	@ResponseBody
	public AjaxJson deleteAll(OrderReseve orderReseve, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		String ids = orderReseve.getIds();
		if(ids!=null&&!"".equals(ids)){
			String idArray[] =ids.split(",");
			List<String> idList =new ArrayList<String>();
			for (String id : idArray) {
				idList.add(id);
			}
			OrderReseve odr =new OrderReseve();
			odr.setIdList(idList);
			orderReseveService.deleteBatchByLogic(odr);
		}
		j.setMsg(message);
		return j;
	}


}
