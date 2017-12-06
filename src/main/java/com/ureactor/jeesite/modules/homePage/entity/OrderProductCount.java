package com.ureactor.jeesite.modules.homePage.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 租房统计Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class OrderProductCount extends DataEntity<OrderProductCount> {
	
	private static final long serialVersionUID = 1L;
	private String  countYear;
	private String  countMonth;
	private String  countYearMonth;
	private Integer countProduct;
	private Double countAmount;
	
	public OrderProductCount() {
		super();
	}

	public OrderProductCount(String id){
		super(id);
	}

	public String getCountYear() {
		return countYear;
	}

	public void setCountYear(String countYear) {
		this.countYear = countYear;
	}

	public String getCountMonth() {
		return countMonth;
	}

	public void setCountMonth(String countMonth) {
		this.countMonth = countMonth;
	}

	public String getCountYearMonth() {
		return countYearMonth;
	}

	public void setCountYearMonth(String countYearMonth) {
		this.countYearMonth = countYearMonth;
	}

	public Integer getCountProduct() {
		return countProduct;
	}

	public void setCountProduct(Integer countProduct) {
		this.countProduct = countProduct;
	}

	public Double getCountAmount() {
		return countAmount;
	}

	public void setCountAmount(Double countAmount) {
		this.countAmount = countAmount;
	}
	
}