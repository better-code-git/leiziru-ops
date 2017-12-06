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
import com.ureactor.jeesite.modules.product.dao.ProductAssortDao;
import com.ureactor.jeesite.modules.product.dao.ProductDao;
import com.ureactor.jeesite.modules.product.dao.ProductPicDao;
import com.ureactor.jeesite.modules.product.dao.ProductRoomDao;
import com.ureactor.jeesite.modules.product.entity.Product;
import com.ureactor.jeesite.modules.product.entity.ProductAssort;
import com.ureactor.jeesite.modules.product.entity.ProductPic;
import com.ureactor.jeesite.modules.product.entity.ProductRoom;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 房源实体Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {

	@Autowired
	private ProductAssortDao productAssortDao;
	@Autowired
	private ProductPicDao productPicDao;
	@Autowired
	private ProductRoomDao productRoomDao;
	
	public Product get(String id) {
		Product product =super.get(id);
		if(product!=null){
			product.setProductAssortList(productAssortDao.findList(new ProductAssort(null,Integer.parseInt(product.getId()))));
			product.setProductPicList(productPicDao.findList(new ProductPic(null,LzrConstant.is_product_detail_pic,product.getId())));
			product.setProductRoomList(productRoomDao.findList(new ProductRoom()));
		}
		return product;
	}
	
	public List<Product> findList(Product product) {
		return super.findList(product);
	}
	
	public Page<Product> findPage(Page<Product> page, Product product) {
		return super.findPage(page,product);
	}
	
	@Transactional(readOnly = false)
	public void save(Product product) {
		if (product.getImgFileData()!=null) {
			if (!product.getImgFileData().isEmpty()) {
				String remotePath = product.getProductProfileUrl();
				String remotePathOld = product.getProductProfileUrlOld();
				String rtnUrl = ObsManageUtil.uploadFile(product.getImgFileData(), remotePath);
				if (remotePathOld.contains(Global.getConfig("obs.accessUrl"))) {
					ObsManageUtil.deleteFile(remotePathOld);
				}
				product.setProductProfileUrl(rtnUrl);
			}
		}
		super.save(product);
		List<ProductAssort> productAssortList =product.getProductAssortList();
		if(productAssortList!=null){
			for(ProductAssort productAssort: productAssortList){
				if(null!=productAssort.getId()&&!"".equals(productAssort.getId())){
					productAssort.setUpdateDate(new Date());
					productAssortDao.update(productAssort);
					
				}else{
					productAssort.setProductId(Integer.valueOf(product.getId()));
					productAssort.setUpdateDate(new Date());
					productAssort.setCreateDate(new Date());
					productAssortDao.insert(productAssort);
				}
			}
		}
		List<ProductPic> productPicList =product.getProductPicList();
		if(productPicList!=null){
			for(ProductPic productPic: productPicList){
				if (productPic.getImgFileData()!=null) {
					if (!productPic.getImgFileData().isEmpty()) {
						String remotePath = productPic.getPicUrl();
						String remotePathOld = productPic.getPicUrlOld();
						String rtnUrl = ObsManageUtil.uploadFile(productPic.getImgFileData(), remotePath);
						if (remotePathOld.contains(Global.getConfig("obs.accessUrl"))) {
							ObsManageUtil.deleteFile(remotePathOld);
						}
						productPic.setPicUrl(rtnUrl);
					}
				}
				if(null!=productPic.getId()&&!"".equals(productPic.getId())){
					productPic.setUpdateDate(new Date());
					productPicDao.update(productPic);
					
				}else{
					productPic.setPicType(LzrConstant.is_product_detail_pic);
					productPic.setPicRelateId(product.getId());
					productPic.setUpdateDate(new Date());
					productPic.setCreateDate(new Date());
					productPicDao.insert(productPic);
				}
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Product product) {
		super.delete(product);
	}
	
}