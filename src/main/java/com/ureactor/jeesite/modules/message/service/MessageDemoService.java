/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.message.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.message.dao.MessageDemoDao;
import com.ureactor.jeesite.modules.message.entity.MessageDemo;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class MessageDemoService extends CrudService<MessageDemoDao, MessageDemo> {

	public MessageDemo get(String id) {
		return super.get(id);
	}
	
	public List<MessageDemo> findList(MessageDemo messageDemo) {
		return super.findList(messageDemo);
	}
	
	public Page<MessageDemo> findPage(Page<MessageDemo> page, MessageDemo messageDemo) {
		return super.findPage(page, messageDemo);
	}
	
	@Transactional(readOnly = false)
	public void save(MessageDemo messageDemo) {
		super.save(messageDemo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MessageDemo messageDemo) {
		super.delete(messageDemo);
	}
	
}