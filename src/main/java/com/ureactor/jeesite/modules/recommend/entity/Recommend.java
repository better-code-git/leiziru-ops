/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.recommend.entity;


import org.springframework.web.multipart.MultipartFile;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 推荐Entity
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
public class Recommend extends DataEntity<Recommend> {
	
	private static final long serialVersionUID = 1L;
	private String recPicUrl;	
	private String recPicUrlOld;//老图
	protected MultipartFile  imgFileData; //图片上传文件
	private Integer productId;		
	private Integer  productProvinceId;
	private Integer  productCityId;
	private Integer  productMetroId;
	private Integer  productZoneId;
	private Integer  productDistrictId;
	private String  productProvinceName;
	private String  productCityName;
	private String  productMetroName;
	private String  productZoneName;
	private String  productDistrictName;
	
	public Recommend() {
		super();
	}

	public Recommend(String id){
		super(id);
	}

	public String getRecPicUrl() {
		return recPicUrl;
	}

	public void setRecPicUrl(String recPicUrl) {
		this.recPicUrl = recPicUrl;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getRecPicUrlOld() {
		return recPicUrlOld;
	}

	public void setRecPicUrlOld(String recPicUrlOld) {
		this.recPicUrlOld = recPicUrlOld;
	}

	public MultipartFile getImgFileData() {
		return imgFileData;
	}

	public void setImgFileData(MultipartFile imgFileData) {
		this.imgFileData = imgFileData;
	}

	public Integer getProductProvinceId() {
		return productProvinceId;
	}

	public void setProductProvinceId(Integer productProvinceId) {
		this.productProvinceId = productProvinceId;
	}

	public Integer getProductCityId() {
		return productCityId;
	}

	public void setProductCityId(Integer productCityId) {
		this.productCityId = productCityId;
	}

	public Integer getProductMetroId() {
		return productMetroId;
	}

	public void setProductMetroId(Integer productMetroId) {
		this.productMetroId = productMetroId;
	}

	public Integer getProductZoneId() {
		return productZoneId;
	}

	public void setProductZoneId(Integer productZoneId) {
		this.productZoneId = productZoneId;
	}

	public Integer getProductDistrictId() {
		return productDistrictId;
	}

	public void setProductDistrictId(Integer productDistrictId) {
		this.productDistrictId = productDistrictId;
	}

	public String getProductProvinceName() {
		return productProvinceName;
	}

	public void setProductProvinceName(String productProvinceName) {
		this.productProvinceName = productProvinceName;
	}

	public String getProductCityName() {
		return productCityName;
	}

	public void setProductCityName(String productCityName) {
		this.productCityName = productCityName;
	}

	public String getProductMetroName() {
		return productMetroName;
	}

	public void setProductMetroName(String productMetroName) {
		this.productMetroName = productMetroName;
	}

	public String getProductZoneName() {
		return productZoneName;
	}

	public void setProductZoneName(String productZoneName) {
		this.productZoneName = productZoneName;
	}

	public String getProductDistrictName() {
		return productDistrictName;
	}

	public void setProductDistrictName(String productDistrictName) {
		this.productDistrictName = productDistrictName;
	}

	
}