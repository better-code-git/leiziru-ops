/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.overall.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.overall.entity.SeviceFee;

/**
 * 服务费DAO接口
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Mapper
public interface SeviceFeeDao extends CrudDao<SeviceFee> {

	
}