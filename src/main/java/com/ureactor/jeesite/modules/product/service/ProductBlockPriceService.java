/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.product.dao.ProductBlockPriceDao;
import com.ureactor.jeesite.modules.product.entity.ProductBlockPrice;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ProductBlockPriceService extends CrudService<ProductBlockPriceDao, ProductBlockPrice> {

	public ProductBlockPrice get(String id) {
		return super.get(id);
	}
	
	public List<ProductBlockPrice> findList(ProductBlockPrice productBlockPrice) {
		return super.findList(productBlockPrice);
	}
	
	public Page<ProductBlockPrice> findPage(Page<ProductBlockPrice> page, ProductBlockPrice productBlockPrice) {
		return super.findPage(page,productBlockPrice);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductBlockPrice productBlockPrice) {
		super.save(productBlockPrice);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductBlockPrice productBlockPrice) {
		super.delete(productBlockPrice);
	}
	
}