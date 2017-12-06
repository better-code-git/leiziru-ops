/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.example.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.example.entity.Example;
import com.ureactor.jeesite.modules.example.dao.ExampleDao;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ExampleService extends CrudService<ExampleDao, Example> {

	public Example get(String id) {
		return super.get(id);
	}
	
	public List<Example> findList(Example example) {
		return super.findList(example);
	}
	
	public Page<Example> findPage(Page<Example> page, Example example) {
		return super.findPage(page, example);
	}
	
	@Transactional(readOnly = false)
	public void save(Example example) {
		super.save(example);
	}
	
	@Transactional(readOnly = false)
	public void delete(Example example) {
		super.delete(example);
	}
	
}