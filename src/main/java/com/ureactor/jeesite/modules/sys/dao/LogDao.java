/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.sys.dao;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.sys.entity.Log;

import org.apache.ibatis.annotations.Mapper;

/**
 * 日志DAO接口
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Mapper
public interface LogDao extends CrudDao<Log> {

}
