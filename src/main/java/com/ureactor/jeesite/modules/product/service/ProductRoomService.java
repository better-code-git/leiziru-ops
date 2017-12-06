/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.product.dao.ProductRoomDao;
import com.ureactor.jeesite.modules.product.entity.ProductRoom;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ProductRoomService extends CrudService<ProductRoomDao, ProductRoom> {

	public ProductRoom get(String id) {
		return super.get(id);
	}
	
	public List<ProductRoom> findList(ProductRoom productRoom) {
		return super.findList(productRoom);
	}
	
	public Page<ProductRoom> findPage(Page<ProductRoom> page, ProductRoom productRoom) {
		return super.findPage(page, productRoom);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductRoom productRoom) {
		super.save(productRoom);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductRoom productRoom) {
		super.delete(productRoom);
	}

	
}