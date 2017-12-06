package com.ureactor.jeesite.modules.overall.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 区域信息Entity
 * @author ForrestCao
 * @version 2017-11-19
 */
public class ServiceDistrict extends DataEntity<ServiceDistrict> {
	
	private static final long serialVersionUID = 1L;
	private Integer provinceId;		// 省
	private String  provinceName;		// 省名称
	private Integer cityId;		// 城市代码
	private Integer cityName;		// 城市名称
	private Integer zoneId;		// 区县
	private Integer zoneName;		// 区县名称
	private Integer metroId;		// 地铁线
	private Integer metroName;		// 地铁名称
	private Integer districtId;		// 商圈代码
	private Integer districtName;		// 商圈名称
	private Integer type;		// 区域类型 1-省 2-市 3-区 4-商圈 5-地铁
	private String areaName;		// 名称
	private Integer isService;		// 是否开通 0-未开通 1- 开通
	private Date openDate;		// 开通时间
	
	public ServiceDistrict() {
		super();
	}

	public ServiceDistrict(String id){
		super(id);
	}
	
	public ServiceDistrict(String id,Integer type){
		super(id);
		this.type=type;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	
	public Integer getMetroId() {
		return metroId;
	}

	public void setMetroId(Integer metroId) {
		this.metroId = metroId;
	}
	
	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public Integer getIsService() {
		return isService;
	}

	public void setIsService(Integer isService) {
		this.isService = isService;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityName() {
		return cityName;
	}

	public void setCityName(Integer cityName) {
		this.cityName = cityName;
	}

	public Integer getZoneName() {
		return zoneName;
	}

	public void setZoneName(Integer zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getMetroName() {
		return metroName;
	}

	public void setMetroName(Integer metroName) {
		this.metroName = metroName;
	}

	public Integer getDistrictName() {
		return districtName;
	}

	public void setDistrictName(Integer districtName) {
		this.districtName = districtName;
	}
	
}