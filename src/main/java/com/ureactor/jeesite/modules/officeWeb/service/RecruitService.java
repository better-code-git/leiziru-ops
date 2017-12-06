package com.ureactor.jeesite.modules.officeWeb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.officeWeb.dao.RecruitDao;
import com.ureactor.jeesite.modules.officeWeb.entity.Recruit;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class RecruitService extends CrudService<RecruitDao, Recruit> {

	public Recruit get(String id) {
		return super.get(id);
	}
	
	public List<Recruit> findList(Recruit recruit) {
		return super.findList(recruit);
	}
	
	public Page<Recruit> findPage(Page<Recruit> page, Recruit recruit) {
		return super.findPage(page,recruit);
	}
	
	@Transactional(readOnly = false)
	public void save(Recruit recruit) {
		super.save(recruit);
	}
	
	@Transactional(readOnly = false)
	public void delete(Recruit recruit) {
		super.delete(recruit);
	}
	
}