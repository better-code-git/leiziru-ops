package com.ureactor.jeesite.modules.message.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.message.dao.MessageSendDao;
import com.ureactor.jeesite.modules.message.entity.MessageSend;

/**
 * 消息发送Service
 * @author ForrestCap
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class MessageSendService extends CrudService<MessageSendDao, MessageSend> {

	public MessageSend get(String id) {
		return super.get(id);
	}
	
	public List<MessageSend> findList(MessageSend messageSend) {
		return super.findList(messageSend);
	}
	
	public Page<MessageSend> findPage(Page<MessageSend> page, MessageSend messageSend) {
		return super.findPage(page, messageSend);
	}
	
	@Transactional(readOnly = false)
	public void save(MessageSend messageSend) {
		super.save(messageSend);
	}
	
	@Transactional(readOnly = false)
	public void delete(MessageSend messageSend) {
		super.delete(messageSend);
	}
	
}