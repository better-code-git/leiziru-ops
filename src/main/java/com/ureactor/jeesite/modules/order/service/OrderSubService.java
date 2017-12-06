package com.ureactor.jeesite.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.order.dao.OrderSubDao;
import com.ureactor.jeesite.modules.order.entity.OrderSub;

/**
 * 子订单管理Service
 * @author ForrestCao
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class OrderSubService extends CrudService<OrderSubDao, OrderSub> {

	public OrderSub get(String id) {
		
		return super.get(id);
	}
	
	public List<OrderSub> findList(OrderSub orderSub) {
		return super.findList(orderSub);
	}
	
	public Page<OrderSub> findPage(Page<OrderSub> page, OrderSub orderSub) {
		return super.findPage(page, orderSub);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderSub orderSub) {
		super.save(orderSub);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderSub orderSub) {
		super.delete(orderSub);
	}
	
}