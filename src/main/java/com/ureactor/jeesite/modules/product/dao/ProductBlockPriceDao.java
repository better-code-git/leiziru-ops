package com.ureactor.jeesite.modules.product.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.product.entity.ProductBlockPrice;

/**
 * 活动示例DAO接口
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Mapper
public interface ProductBlockPriceDao extends CrudDao<ProductBlockPrice> {
	
}