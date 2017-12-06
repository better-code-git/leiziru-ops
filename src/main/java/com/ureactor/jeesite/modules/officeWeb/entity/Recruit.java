/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.officeWeb.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 招聘信息Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Recruit extends DataEntity<Recruit> {
	
	private static final long serialVersionUID = 1L;
	private String jobTitle;
	private String jobLocation;
	private Integer jobNumbers;
	private String jobRequired;


	
	public Recruit() {
		super();
	}

	public Recruit(String id){
		super(id);
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public Integer getJobNumbers() {
		return jobNumbers;
	}

	public void setJobNumbers(Integer jobNumbers) {
		this.jobNumbers = jobNumbers;
	}

	public String getJobRequired() {
		return jobRequired;
	}

	public void setJobRequired(String jobRequired) {
		this.jobRequired = jobRequired;
	}


	
}