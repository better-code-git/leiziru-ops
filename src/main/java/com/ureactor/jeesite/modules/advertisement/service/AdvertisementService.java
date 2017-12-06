/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.advertisement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.common.utils.ObsManageUtil;
import com.ureactor.jeesite.modules.advertisement.dao.AdvertisementDao;
import com.ureactor.jeesite.modules.advertisement.entity.Advertisement;

/**
 * 广告推荐Service
 * @author ForrestCao
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class AdvertisementService extends CrudService<AdvertisementDao, Advertisement> {

	public Advertisement get(String id) {
		return super.get(id);
	}
	
	public List<Advertisement> findList(Advertisement advertisement) {
		return super.findList(advertisement);
	}
	
	public Page<Advertisement> findPage(Page<Advertisement> page, Advertisement advertisement) {
		return super.findPage(page, advertisement);
	}
	
	@Transactional(readOnly = false)
	public void save(Advertisement advertisement) {
		if (advertisement.getImgFileData()!=null) {
			if (!advertisement.getImgFileData().isEmpty()) {
				String remotePath = advertisement.getAdPicUrl();
				String remotePathOld = advertisement.getAdPicUrlOld();
				String rtnUrl = ObsManageUtil.uploadFile(advertisement.getImgFileData(), remotePath);
				if (remotePathOld.contains(Global.getConfig("obs.accessUrl"))) {
					ObsManageUtil.deleteFile(remotePathOld);
				}
				advertisement.setAdPicUrl(rtnUrl);
			}
		}
		super.save(advertisement);
	}
	
	@Transactional(readOnly = false)
	public void delete(Advertisement advertisement) {
		super.delete(advertisement);
	}

	public List<Advertisement> findListByCheck(Advertisement advertisement) {
		return dao.findListByCheck(advertisement);
	}
	
}