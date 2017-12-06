package com.ureactor.jeesite.modules.message.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.message.entity.Message;

/**
 * 活动示例DAO接口
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Mapper
public interface MessageDao extends CrudDao<Message> {
	
}