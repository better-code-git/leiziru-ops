/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.message.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.message.dao.MessageDao;
import com.ureactor.jeesite.modules.message.entity.Message;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class MessageService extends CrudService<MessageDao, Message> {

	public Message get(String id) {
		return super.get(id);
	}
	
	public List<Message> findList(Message message) {
		return super.findList(message);
	}
	
	public Page<Message> findPage(Page<Message> page, Message message) {
		return super.findPage(page, message);
	}
	
	@Transactional(readOnly = false)
	public void save(Message message) {
		super.save(message);
	}
	
	@Transactional(readOnly = false)
	public void delete(Message message) {
		super.delete(message);
	}
	
}