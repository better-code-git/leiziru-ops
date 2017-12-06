/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.product.dao.ProductAssortDao;
import com.ureactor.jeesite.modules.product.entity.ProductAssort;

/**
 * 房屋配套Service
 * @author ForrestCao
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ProductAssortService extends CrudService<ProductAssortDao, ProductAssort> {

	public ProductAssort get(String id) {
		return super.get(id);
	}
	
	public List<ProductAssort> findList(ProductAssort productAssort) {
		return super.findList(productAssort);
	}
	
	public Page<ProductAssort> findPage(Page<ProductAssort> page, ProductAssort productAssort) {
		return super.findPage(page,productAssort);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductAssort productAssort) {
		super.save(productAssort);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductAssort productAssort) {
		super.delete(productAssort);
	}
	
}