/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.common.utils.ObsManageUtil;
import com.ureactor.jeesite.modules.product.dao.ProductBlockDao;
import com.ureactor.jeesite.modules.product.dao.ProductRoomDao;
import com.ureactor.jeesite.modules.product.entity.ProductBlock;
import com.ureactor.jeesite.modules.product.entity.ProductRoom;

/**
 * 房源实体Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ProductBlockService extends CrudService<ProductBlockDao, ProductBlock> {

	@Autowired
	private ProductRoomDao productRoomDao;
	
	public ProductBlock get(String id) {
		return super.get(id);
	}
	
	public List<ProductBlock> findList(ProductBlock productBlock) {
		return super.findList(productBlock);
	}
	
	public Page<ProductBlock> findPage(Page<ProductBlock> page, ProductBlock productBlock) {
		return super.findPage(page,productBlock);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductBlock productBlock) {
		if (productBlock.getImgFileData()!=null) {
			if (!productBlock.getImgFileData().isEmpty()) {
				String remotePath = productBlock.getBlockPicUrl();
				String remotePathOld = productBlock.getBlockPicUrlOld();
				String rtnUrl = ObsManageUtil.uploadFile(productBlock.getImgFileData(), remotePath);
				if (remotePathOld.contains(Global.getConfig("obs.accessUrl"))) {
					ObsManageUtil.deleteFile(remotePathOld);
				}
				productBlock.setBlockPicUrl(rtnUrl);
			}
		}
		super.save(productBlock);
		List<ProductRoom> roomList =productBlock.getRoomList();
		if(roomList!=null){
			for(ProductRoom productRoom: roomList){
				if(null!=productRoom.getId()&&!"".equals(productRoom.getId())){
					productRoom.setUpdateDate(new Date());
					productRoomDao.update(productRoom);
					
				}else{
					productRoom.setBlockId(productBlock.getId());
					productRoom.setUpdateDate(new Date());
					productRoom.setCreateDate(new Date());
					productRoomDao.insert(productRoom);
				}
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductBlock productBlock) {
		super.delete(productBlock);
	}
	
}