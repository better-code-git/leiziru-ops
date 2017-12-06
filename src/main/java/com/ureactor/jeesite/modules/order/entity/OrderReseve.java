package com.ureactor.jeesite.modules.order.entity;


import java.util.Date;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 预约订单管理Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class OrderReseve extends DataEntity<OrderReseve> {
	
	private static final long serialVersionUID = 1L;
	private String contactPhoneNo;
	private Date reseveTime;
	private String reseveMemo;
	private Integer contactSex;
	private String memberId;
	private String productId;
	private Date orderDate;
	private Date orderDateStart;
	private Date orderDateEnd;
	private Date reseveTimeStart;
    private Date reseveTimeEnd;
	private String contactName;
	private Integer orderStatus;


	
	public OrderReseve() {
		super();
	}

	public OrderReseve(String id){
		super(id);
	}

	public String getContactPhoneNo() {
		return contactPhoneNo;
	}

	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}


	public String getReseveMemo() {
		return reseveMemo;
	}

	public void setReseveMemo(String reseveMemo) {
		this.reseveMemo = reseveMemo;
	}


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getReseveTime() {
		return reseveTime;
	}

	public void setReseveTime(Date reseveTime) {
		this.reseveTime = reseveTime;
	}

	public Integer getContactSex() {
		return contactSex;
	}

	public void setContactSex(Integer contactSex) {
		this.contactSex = contactSex;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDateStart() {
		return orderDateStart;
	}

	public void setOrderDateStart(Date orderDateStart) {
		this.orderDateStart = orderDateStart;
	}

	public Date getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(Date orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public Date getReseveTimeStart() {
		return reseveTimeStart;
	}

	public void setReseveTimeStart(Date reseveTimeStart) {
		this.reseveTimeStart = reseveTimeStart;
	}

	public Date getReseveTimeEnd() {
		return reseveTimeEnd;
	}

	public void setReseveTimeEnd(Date reseveTimeEnd) {
		this.reseveTimeEnd = reseveTimeEnd;
	}
	
}