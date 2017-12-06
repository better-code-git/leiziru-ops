/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.officeWeb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.officeWeb.dao.WebBannerDao;
import com.ureactor.jeesite.modules.officeWeb.entity.WebBanner;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class WebBannerService extends CrudService<WebBannerDao, WebBanner> {

	public WebBanner get(String id) {
		return super.get(id);
	}
	
	public List<WebBanner> findList(WebBanner webBanner) {
		return super.findList(webBanner);
	}
	
	public Page<WebBanner> findPage(Page<WebBanner> page, WebBanner webBanner) {
		return super.findPage(page,webBanner);
	}
	
	@Transactional(readOnly = false)
	public void save(WebBanner webBanner) {
		super.save(webBanner);
	}
	
	@Transactional(readOnly = false)
	public void delete(WebBanner webBanner) {
		super.delete(webBanner);
	}
	
}