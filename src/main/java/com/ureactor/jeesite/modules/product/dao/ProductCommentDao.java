/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.product.entity.ProductComment;

/**
 * 房源评价Dao
 * @author ForrestCao
 * @version 2017-09-06
 */
@Mapper
public interface ProductCommentDao extends CrudDao<ProductComment> {

	void updateStatus(ProductComment productComment);
	
}