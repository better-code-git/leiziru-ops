package com.ureactor.jeesite.modules.product.entity;



import org.springframework.web.multipart.MultipartFile;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 房源实体Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ProductPic extends DataEntity<ProductPic> {
	
	private static final long serialVersionUID = 1L;
	private Integer picType;// 1- 房屋详情
	private String  picUrl;
	private String  picUrlOld;
	private MultipartFile  imgFileData; //图片上传文件
	private String  picDesc;
	private String picRelateId;
	
	
	public ProductPic() {
		super();
	}

	public ProductPic(String id,Integer picType,String picRelateId){
		super(id);
		this.picType=picType;
		this.picRelateId =picRelateId;
	}
	
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPicDesc() {
		return picDesc;
	}
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	public String getPicRelateId() {
		return picRelateId;
	}
	public void setPicRelateId(String picRelateId) {
		this.picRelateId = picRelateId;
	}

	public String getPicUrlOld() {
		return picUrlOld;
	}

	public void setPicUrlOld(String picUrlOld) {
		this.picUrlOld = picUrlOld;
	}

	public MultipartFile getImgFileData() {
		return imgFileData;
	}

	public void setImgFileData(MultipartFile imgFileData) {
		this.imgFileData = imgFileData;
	}
	
}