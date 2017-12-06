package com.ureactor.jeesite.modules.order.entity;



import com.ureactor.jeesite.common.persistence.DataEntity;
import com.ureactor.jeesite.modules.trade.entity.Trade;

/**
 * 订单管理Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class OrderSub extends DataEntity<OrderSub> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String orderSubId;//子订单号
	private Trade trade;
    private Integer period;//分期期数
    private Integer subOrderStatus;//子订单状态 0- 待支付  1-已支付

	
	public OrderSub() {
		super();
	}

	public OrderSub(String id){
		super(id);
	}

	public OrderSub(String id,String orderId){
		super(id);
		this.orderId =orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderSubId() {
		return orderSubId;
	}

	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getSubOrderStatus() {
		return subOrderStatus;
	}

	public void setSubOrderStatus(Integer subOrderStatus) {
		this.subOrderStatus = subOrderStatus;
	}

	
}