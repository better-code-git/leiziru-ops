/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.product.entity.Product;

/**
 * 活动示例DAO接口
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Mapper
public interface ProductDao extends CrudDao<Product> {

	List<Product> getNewCount(Product product);
	
}