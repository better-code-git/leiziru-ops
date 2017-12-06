package com.ureactor.jeesite.modules.trade.entity;

import java.util.Date;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 交易信息Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Trade extends DataEntity<Trade> {
	
	private static final long serialVersionUID = 1L;
	private String tradeCode;
	private Date tradeTime;
	private Double tradeAmount;
	private Integer tradeChannel;
	private Integer tradeType;
	private String memberId;
	private String nickName;
	private String orderId;
	private Integer orderPeriods;

	
	public Trade() {
		super();
	}

	public Trade(String id){
		super(id);
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Integer getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(Integer tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderPeriods() {
		return orderPeriods;
	}

	public void setOrderPeriods(Integer orderPeriods) {
		this.orderPeriods = orderPeriods;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	
}