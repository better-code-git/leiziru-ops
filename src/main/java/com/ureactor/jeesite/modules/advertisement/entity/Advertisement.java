package com.ureactor.jeesite.modules.advertisement.entity;


import org.springframework.web.multipart.MultipartFile;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 广告推荐Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Advertisement extends DataEntity<Advertisement> {
	
	private static final long serialVersionUID = 1L;
	private String adPicUrl;
	private String adPicUrlOld;//老图
	private MultipartFile  imgFileData; //图片上传文件
	private Integer productId;
	private Integer adPosition;
	
	public Advertisement() {
		super();
	}

	public Advertisement(String id){
		super(id);
	}

	public String getAdPicUrl() {
		return adPicUrl;
	}

	public void setAdPicUrl(String adPicUrl) {
		this.adPicUrl = adPicUrl;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(Integer adPosition) {
		this.adPosition = adPosition;
	}

	public String getAdPicUrlOld() {
		return adPicUrlOld;
	}

	public void setAdPicUrlOld(String adPicUrlOld) {
		this.adPicUrlOld = adPicUrlOld;
	}

	public MultipartFile getImgFileData() {
		return imgFileData;
	}

	public void setImgFileData(MultipartFile imgFileData) {
		this.imgFileData = imgFileData;
	}

	
}