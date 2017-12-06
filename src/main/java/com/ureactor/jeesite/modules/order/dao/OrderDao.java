/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.order.entity.Order;

/**
 * 订单管理DAO接口
 * @author ForrestCao
 * @version 2017-09-06
 */
@Mapper
public interface OrderDao extends CrudDao<Order> {

	List<Order> getNewCount(Order order);
	
}