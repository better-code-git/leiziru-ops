package com.ureactor.jeesite.modules.overall.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 官网轮播图Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ServiceMetro extends DataEntity<ServiceMetro> {
	
	private static final long serialVersionUID = 1L;
	private String webBannerUrl;
	private Integer sort;

	public ServiceMetro() {
		super();
	}

	public ServiceMetro(String id){
		super(id);
	}

	public String getWebBannerUrl() {
		return webBannerUrl;
	}

	public void setWebBannerUrl(String webBannerUrl) {
		this.webBannerUrl = webBannerUrl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	
}