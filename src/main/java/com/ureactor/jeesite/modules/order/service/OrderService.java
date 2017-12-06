package com.ureactor.jeesite.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.common.utils.SpringContextHolder;
import com.ureactor.jeesite.modules.order.dao.OrderDao;
import com.ureactor.jeesite.modules.order.dao.OrderSubDao;
import com.ureactor.jeesite.modules.order.entity.Order;
import com.ureactor.jeesite.modules.order.entity.OrderSub;

/**
 * 订单管理Service
 * @author ForrestCao
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudService<OrderDao, Order> {

	private static OrderSubDao orderSubDao = SpringContextHolder.getBean(OrderSubDao.class);
	
	public Order get(String id) {
		Order orderRtn =new Order();
		orderRtn =super.get(id);
		List<OrderSub> orderSubList = orderSubDao.findList(new OrderSub(null,orderRtn.getOrderId()));
		orderRtn.setOrderSubList(orderSubList);
		return orderRtn;
	}
	
	public List<Order> findList(Order order) {
		return super.findList(order);
	}
	
	public Page<Order> findPage(Page<Order> page, Order order) {
		return super.findPage(page, order);
	}
	
	@Transactional(readOnly = false)
	public void save(Order order) {
		super.save(order);
	}
	
	@Transactional(readOnly = false)
	public void delete(Order order) {
		super.delete(order);
	}
	
}