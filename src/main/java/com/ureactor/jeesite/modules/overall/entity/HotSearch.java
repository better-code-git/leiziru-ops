package com.ureactor.jeesite.modules.overall.entity;


import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 热搜数据Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class HotSearch extends DataEntity<HotSearch> {
	
	private static final long serialVersionUID = 1L;
	private String keyName;
	private String  cityName;
	private Integer cityId;

	public HotSearch() {
		super();
	}

	public HotSearch(String id){
		super(id);
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
}