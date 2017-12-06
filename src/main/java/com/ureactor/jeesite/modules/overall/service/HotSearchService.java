/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.overall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.overall.dao.HotSearchDao;
import com.ureactor.jeesite.modules.overall.entity.HotSearch;

/**
 * 热搜数据Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class HotSearchService extends CrudService<HotSearchDao, HotSearch> {

	public HotSearch get(String id) {
		return super.get(id);
	}
	
	public List<HotSearch> findList(HotSearch hotSearch) {
		return super.findList(hotSearch);
	}
	
	public Page<HotSearch> findPage(Page<HotSearch> page, HotSearch hotSearch) {
		return super.findPage(page,hotSearch);
	}
	
	@Transactional(readOnly = false)
	public void save(HotSearch hotSearch) {
		super.save(hotSearch);
	}
	
	@Transactional(readOnly = false)
	public void delete(HotSearch hotSearch) {
		super.delete(hotSearch);
	}
	
}