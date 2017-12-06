package com.ureactor.jeesite.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.message.entity.MessageSend;

/**
 * 消息发送DAO接口
 * @author ForrestCao
 * @version 2017-09-06
 */
@Mapper
public interface MessageSendDao extends CrudDao<MessageSend> {
	
}