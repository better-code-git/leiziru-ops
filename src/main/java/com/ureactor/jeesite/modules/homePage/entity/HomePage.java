package com.ureactor.jeesite.modules.homePage.entity;

import java.util.List;
import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 管理首页Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class HomePage extends DataEntity<HomePage> {
	
	private static final long serialVersionUID = 1L;
	private Integer increasedProduct;
	private Integer increasedOrder;
	private Integer increasedMember;
	private List<OrderProductCount> orderProductCountList;
	
	public HomePage() {
		super();
	}

	public HomePage(String id){
		super(id);
	}

	public Integer getIncreasedProduct() {
		return increasedProduct;
	}

	public void setIncreasedProduct(Integer increasedProduct) {
		this.increasedProduct = increasedProduct;
	}

	public Integer getIncreasedOrder() {
		return increasedOrder;
	}

	public void setIncreasedOrder(Integer increasedOrder) {
		this.increasedOrder = increasedOrder;
	}

	public Integer getIncreasedMember() {
		return increasedMember;
	}

	public void setIncreasedMember(Integer increasedMember) {
		this.increasedMember = increasedMember;
	}

	public List<OrderProductCount> getOrderProductCountList() {
		return orderProductCountList;
	}

	public void setOrderProductCountList(List<OrderProductCount> orderProductCountList) {
		this.orderProductCountList = orderProductCountList;
	}
	
}