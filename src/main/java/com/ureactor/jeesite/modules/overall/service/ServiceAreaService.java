/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.overall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.dao.ServiceAreaDao;

/**
 * 区域信息Service
 * @author Forrest
 * @version 2017-11-19
 */
@Service
@Transactional(readOnly = true)
public class ServiceAreaService extends CrudService<ServiceAreaDao, ServiceArea> {

	public ServiceArea get(String id) {
		return super.get(id);
	}
	
	public List<ServiceArea> findList(ServiceArea serviceArea) {
		return super.findList(serviceArea);
	}
	
	public Page<ServiceArea> findPage(Page<ServiceArea> page, ServiceArea serviceArea) {
		return super.findPage(page, serviceArea);
	}
	
	@Transactional(readOnly = false)
	public void save(ServiceArea serviceArea) {
		super.save(serviceArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(ServiceArea serviceArea) {
		super.delete(serviceArea);
	}

	public List<ServiceArea> findZoneAndMetroList(ServiceArea serviceArea) {
		return dao.findZoneAndMetroList(serviceArea);
	}

	@Transactional(readOnly = false)
	public void updateOpenStatus(ServiceArea serviceArea) {
		dao.updateOpenStatus(serviceArea);
		
	}
	
	public List<ServiceArea> findListByCheck(ServiceArea serviceArea) {
		return dao.findListByCheck(serviceArea);
	}
	
}