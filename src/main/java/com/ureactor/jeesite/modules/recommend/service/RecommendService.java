package com.ureactor.jeesite.modules.recommend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.common.utils.ObsManageUtil;
import com.ureactor.jeesite.modules.recommend.dao.RecommendDao;
import com.ureactor.jeesite.modules.recommend.entity.Recommend;

/**
 * 推荐Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class RecommendService extends CrudService<RecommendDao, Recommend> {

	public Recommend get(String id) {
		return super.get(id);
	}
	
	public List<Recommend> findList(Recommend recommend) {
		return super.findList(recommend);
	}
	
	public Page<Recommend> findPage(Page<Recommend> page, Recommend recommend) {
		return super.findPage(page, recommend);
	}
	
	@Transactional(readOnly = false)
	public void save(Recommend recommend) {
		if (recommend.getImgFileData()!=null) {
			if (!recommend.getImgFileData().isEmpty()) {
				String remotePath = recommend.getRecPicUrl();
				String remotePathOld = recommend.getRecPicUrlOld();
				String rtnUrl = ObsManageUtil.uploadFile(recommend.getImgFileData(), remotePath);
				if (remotePathOld.contains(Global.getConfig("obs.accessUrl"))) {
					ObsManageUtil.deleteFile(remotePathOld);
				}
				recommend.setRecPicUrl(rtnUrl);
			}
		}
		super.save(recommend);
	}
	
	@Transactional(readOnly = false)
	public void delete(Recommend recommend) {
		super.delete(recommend);
	}
	
}