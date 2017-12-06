/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.order.dao.OrderReseveDao;
import com.ureactor.jeesite.modules.order.entity.OrderReseve;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class OrderReseveService extends CrudService<OrderReseveDao, OrderReseve> {

	public OrderReseve get(String id) {
		return super.get(id);
	}
	
	public List<OrderReseve> findList(OrderReseve orderReseve) {
		return super.findList(orderReseve);
	}
	
	public Page<OrderReseve> findPage(Page<OrderReseve> page, OrderReseve orderReseve) {
		return super.findPage(page, orderReseve);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderReseve orderReseve) {
		super.save(orderReseve);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderReseve orderReseve) {
		super.delete(orderReseve);
	}
	
}