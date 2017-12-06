package com.ureactor.jeesite.modules.product.entity;


import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 房源实体Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ProductBlock extends DataEntity<ProductBlock> {
	
	private static final long serialVersionUID = 1L;
	private String  blockName;//小区名称
	private Integer productDistrictId;//房源所在商圈
	private Integer productZoneId;//房源所在地区
	private Integer productProvinceId;//房源所在省份
	private Integer productCityId;//房源所在城市
	private Integer productMetroId;//地铁线
	private String blockIntroduce;//小区介绍
	private String trafficIntroduce;//交通介绍
	private String addressDetail;//小区详细地址
	private Date updateStartDate;//更新开始时间
	private Date updateEndDate;//更新结束时间
	private String blockPicUrl;//小区详情图片
	private String blockPicUrlOld;//小区详情图片老图X
	private MultipartFile  imgFileData; //图片上传文件
	private List<ProductRoom> roomList;
	private List<ProductBlockPrice> blockPriceList;

	
	public ProductBlock() {
		super();
	}

	public ProductBlock(String id){
		super(id);
	}

	public Integer getProductDistrictId() {
		return productDistrictId;
	}

	public void setProductDistrictId(Integer productDistrictId) {
		this.productDistrictId = productDistrictId;
	}

	public Integer getProductZoneId() {
		return productZoneId;
	}

	public void setProductZoneId(Integer productZoneId) {
		this.productZoneId = productZoneId;
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

	public String getBlockIntroduce() {
		return blockIntroduce;
	}

	public void setBlockIntroduce(String blockIntroduce) {
		this.blockIntroduce = blockIntroduce;
	}

	public String getTrafficIntroduce() {
		return trafficIntroduce;
	}

	public void setTrafficIntroduce(String trafficIntroduce) {
		this.trafficIntroduce = trafficIntroduce;
	}

	public Date getUpdateStartDate() {
		return updateStartDate;
	}

	public void setUpdateStartDate(Date updateStartDate) {
		this.updateStartDate = updateStartDate;
	}

	public Date getUpdateEndDate() {
		return updateEndDate;
	}

	public void setUpdateEndDate(Date updateEndDate) {
		this.updateEndDate = updateEndDate;
	}

	public String getBlockPicUrl() {
		return blockPicUrl;
	}

	public void setBlockPicUrl(String blockPicUrl) {
		this.blockPicUrl = blockPicUrl;
	}

	public String getBlockPicUrlOld() {
		return blockPicUrlOld;
	}

	public void setBlockPicUrlOld(String blockPicUrlOld) {
		this.blockPicUrlOld = blockPicUrlOld;
	}

	public MultipartFile getImgFileData() {
		return imgFileData;
	}

	public void setImgFileData(MultipartFile imgFileData) {
		this.imgFileData = imgFileData;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public List<ProductRoom> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<ProductRoom> roomList) {
		this.roomList = roomList;
	}

	public List<ProductBlockPrice> getBlockPriceList() {
		return blockPriceList;
	}

	public void setBlockPriceList(List<ProductBlockPrice> blockPriceList) {
		this.blockPriceList = blockPriceList;
	}


	
}