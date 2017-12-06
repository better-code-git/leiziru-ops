/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.product.dao.ProductCommentDao;
import com.ureactor.jeesite.modules.product.entity.ProductComment;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ProductCommentService extends CrudService<ProductCommentDao, ProductComment> {

	public ProductComment get(String id) {
		return super.get(id);
	}
	
	public List<ProductComment> findList(ProductComment productComment) {
		return super.findList(productComment);
	}
	
	public Page<ProductComment> findPage(Page<ProductComment> page, ProductComment productComment) {
		return super.findPage(page, productComment);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductComment productComment) {
		super.save(productComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductComment productComment) {
		super.delete(productComment);
	}

	@Transactional(readOnly = false)
	public void updateStatus(ProductComment productComment) {
		dao.updateStatus(productComment);
	}
	
}