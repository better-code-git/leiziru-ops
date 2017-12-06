package com.ureactor.jeesite.modules.product.entity;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 房源类型Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ProductRoom extends DataEntity<ProductRoom> {
	
	private static final long serialVersionUID = 1L;

	private String  blockId;
	private Integer roomType;
	private String  roomName;
	
	public ProductRoom() {
		super();
	}

	public ProductRoom(String id){
		super(id);
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}


	
}