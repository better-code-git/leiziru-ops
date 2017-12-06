package com.ureactor.jeesite.modules.officeWeb.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 公司介绍Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Company extends DataEntity<Company> {
	
	private static final long serialVersionUID = 1L;
    private String companyProfile;
	
	public Company() {
		super();
	}

	public Company(String id){
		super(id);
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}


	
}