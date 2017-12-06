package com.ureactor.jeesite.modules.product.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 房源实体Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ProductAssort extends DataEntity<ProductAssort> {
	
	private static final long serialVersionUID = 1L;
	private Integer  productId;//房屋编号
	private Integer  assort;//房屋设施
	private String   roomName;//房屋名称
	private Integer  roomArea;//房屋面积

	
	public ProductAssort() {
		super();
	}
	
	public ProductAssort(String id,Integer productId) {
		super();
		this.productId=productId;
		this.id=id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getAssort() {
		return assort;
	}

	public void setAssort(Integer assort) {
		this.assort = assort;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(Integer roomArea) {
		this.roomArea = roomArea;
	}

	public ProductAssort(String id){
		super(id);
	}


	
}