/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.order.dao.OrderCommissionDao;
import com.ureactor.jeesite.modules.order.entity.OrderCommission;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class OrderCommissionService extends CrudService<OrderCommissionDao, OrderCommission> {

	public OrderCommission get(String id) {
		return super.get(id);
	}
	
	public List<OrderCommission> findList(OrderCommission orderCommission) {
		return super.findList(orderCommission);
	}
	
	public Page<OrderCommission> findPage(Page<OrderCommission> page, OrderCommission orderCommission) {
		return super.findPage(page, orderCommission);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderCommission orderCommission) {
		super.save(orderCommission);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderCommission orderCommission) {
		super.delete(orderCommission);
	}
	
}