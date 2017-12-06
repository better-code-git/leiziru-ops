package com.ureactor.jeesite.modules.officeWeb.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 官网轮播图Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class WebBanner extends DataEntity<WebBanner> {
	
	private static final long serialVersionUID = 1L;
	private String webBannerUrl;
	private Integer sort;

	public WebBanner() {
		super();
	}

	public WebBanner(String id){
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