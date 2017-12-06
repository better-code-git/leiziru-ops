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
import com.ureactor.jeesite.modules.order.entity.Order;
import com.ureactor.jeesite.modules.order.service.OrderService;

/**
 * 订单管理Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/order/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@ModelAttribute
	public Order get(@RequestParam(required = false) String id) {
		Order entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderService.get(id);
		}
		if (entity == null) {
			entity = new Order();
		}
		return entity;
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "list", "" })
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Order> page = orderService.findPage(new Page<Order>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		return "modules/order/orderList";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "modules/order/orderForm";
	}

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(Order order, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, order)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		orderService.save(order);
		j.setMsg(message);
		return j;
	}

	@RequiresPermissions("order:order:delete")
	@RequestMapping(value = "delete")
	public String delete(Order order, RedirectAttributes redirectAttributes) {
		orderService.delete(order);
		addMessage(redirectAttributes, "删除订单管理成功");
		return "redirect:" + Global.getAdminPath() + "/order/order/?repage";
	}

	@RequestMapping(value = "deleteAll")
	@ResponseBody
	public AjaxJson deleteAll(Order order, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		String ids = order.getIds();
		if (ids != null && !"".equals(ids)) {
			String idArray[] = ids.split(",");
			List<String> idList = new ArrayList<String>();
			for (String id : idArray) {
				idList.add(id);
			}
			Order od = new Order();
			od.setIdList(idList);
			orderService.deleteBatchByLogic(od);
		}
		j.setMsg(message);
		return j;
	}

}
