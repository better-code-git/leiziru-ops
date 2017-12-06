package com.ureactor.jeesite.modules.overall.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 服务费Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class SeviceFee extends DataEntity<SeviceFee> {
	
	private static final long serialVersionUID = 1L;
	private Double feePercent;

	public SeviceFee() {
		super();
	}

	public SeviceFee(String id){
		super(id);
	}

	public Double getFeePercent() {
		return feePercent;
	}

	public void setFeePercent(Double feePercent) {
		this.feePercent = feePercent;
	}
	
}