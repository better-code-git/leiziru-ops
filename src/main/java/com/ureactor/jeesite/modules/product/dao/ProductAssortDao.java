/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.product.entity.ProductAssort;

/**
 * 房屋配套DAO接口
 * @author ForrestCao
 * @version 2017-09-06
 */
@Mapper
public interface ProductAssortDao extends CrudDao<ProductAssort> {
	
}