package com.ureactor.jeesite.modules.order.entity;


import java.util.Date;
import java.util.List;

import com.ureactor.jeesite.common.persistence.DataEntity;
import com.ureactor.jeesite.modules.member.entity.Member;

/**
 * 订单管理Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;
	private Date orderTime;
	private Date orderTimeStart;
	private Date orderTimeEnd;
	private Double orderAmount;
	private String productId;
	private String productName;
	private Integer  productSource;
	private Integer orderStatus;
	private Integer orderType;
	private String memberId;
	private String nickName;
	private Integer authenticated;
	private String phoneNo;
	private Double countAmount;//统计金额
	private Member member;
	private String payType;
	private List<OrderSub>  orderSubList;

	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Double getCountAmount() {
		return countAmount;
	}

	public void setCountAmount(Double countAmount) {
		this.countAmount = countAmount;
	}

	public Integer getProductSource() {
		return productSource;
	}

	public void setProductSource(Integer productSource) {
		this.productSource = productSource;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Integer authenticated) {
		this.authenticated = authenticated;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getOrderTimeStart() {
		return orderTimeStart;
	}

	public void setOrderTimeStart(Date orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}

	public Date getOrderTimeEnd() {
		return orderTimeEnd;
	}

	public void setOrderTimeEnd(Date orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<OrderSub> getOrderSubList() {
		return orderSubList;
	}

	public void setOrderSubList(List<OrderSub> orderSubList) {
		this.orderSubList = orderSubList;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	
}