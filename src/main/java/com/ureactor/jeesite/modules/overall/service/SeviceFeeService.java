/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.overall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.overall.dao.SeviceFeeDao;
import com.ureactor.jeesite.modules.overall.entity.SeviceFee;

/**
 * 服务费Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class SeviceFeeService extends CrudService<SeviceFeeDao, SeviceFee> {

	public SeviceFee get(String id) {
		return super.get(id);
	}
	
	public List<SeviceFee> findList(SeviceFee seviceFee) {
		return super.findList(seviceFee);
	}
	
	public Page<SeviceFee> findPage(Page<SeviceFee> page, SeviceFee seviceFee) {
		return super.findPage(page,seviceFee);
	}
	
	@Transactional(readOnly = false)
	public void save(SeviceFee seviceFee) {
		super.save(seviceFee);
	}
	
	@Transactional(readOnly = false)
	public void delete(SeviceFee seviceFee) {
		super.delete(seviceFee);
	}
	
}