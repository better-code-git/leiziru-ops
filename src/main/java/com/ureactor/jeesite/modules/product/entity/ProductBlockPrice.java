package com.ureactor.jeesite.modules.product.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 房源实体Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ProductBlockPrice extends DataEntity<ProductBlockPrice> {
	
	private static final long serialVersionUID = 1L;
	private String districtId;
	private String roomType;
	private String avagePrice;
	private String productId;
	private String lowPrice;
	private String belongMonth;
	private String districtName;


	
	public ProductBlockPrice() {
		super();
	}

	public ProductBlockPrice(String id){
		super(id);
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBelongMonth() {
		return belongMonth;
	}

	public void setBelongMonth(String belongMonth) {
		this.belongMonth = belongMonth;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAvagePrice() {
		return avagePrice;
	}

	public void setAvagePrice(String avagePrice) {
		this.avagePrice = avagePrice;
	}

	public String getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}


	
}