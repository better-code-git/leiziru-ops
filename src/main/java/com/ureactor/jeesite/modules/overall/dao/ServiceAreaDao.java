package com.ureactor.jeesite.modules.overall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;

/**
 * 区域信息DAO接口
 * @author Forrest
 * @version 2017-11-19
 */
@Mapper
public interface ServiceAreaDao extends CrudDao<ServiceArea> {

	List<ServiceArea> findZoneAndMetroList(ServiceArea serviceArea);

	void updateOpenStatus(ServiceArea serviceArea);

	List<ServiceArea> findListByCheck(ServiceArea serviceArea);
	
}