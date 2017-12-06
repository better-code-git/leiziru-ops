/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.officeWeb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.officeWeb.dao.FriendlyLinkDao;
import com.ureactor.jeesite.modules.officeWeb.entity.FriendlyLink;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class FriendlyLinkService extends CrudService<FriendlyLinkDao, FriendlyLink> {

	public FriendlyLink get(String id) {
		return super.get(id);
	}
	
	public List<FriendlyLink> findList(FriendlyLink friendlyLink) {
		return super.findList(friendlyLink);
	}
	
	public Page<FriendlyLink> findPage(Page<FriendlyLink> page, FriendlyLink friendlyLink) {
		return super.findPage(page,friendlyLink);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendlyLink friendlyLink) {
		super.save(friendlyLink);
	}
	
	@Transactional(readOnly = false)
	public void delete(FriendlyLink friendlyLink) {
		super.delete(friendlyLink);
	}
	
}