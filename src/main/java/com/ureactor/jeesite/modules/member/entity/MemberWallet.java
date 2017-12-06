/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.member.entity;

import java.util.Date;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 会员钱包Entity
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
public class MemberWallet extends DataEntity<MemberWallet> {
	
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String profilePhotoUrl;
	private String nickName;
	private String realName;
	private Integer sex;
	private String zone;
	private String city;
	private String province;
	private String birthday;
	private String idCardNo;
	private String idCardNoDownPicUrl;
	private String idCardNoUpPicUrl;
	private String phoneNo;
	private Double availableAmount;
	private Double deposit;
	private Double consumeAmount;
	private String authenticated;
	private Integer memberStatus;
	private Date registeredDate;
	private Date authDate;

	
	
	
	
	public MemberWallet() {
		super();
	}

	public MemberWallet(String id){
		super(id);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getIdCardNoDownPicUrl() {
		return idCardNoDownPicUrl;
	}

	public void setIdCardNoDownPicUrl(String idCardNoDownPicUrl) {
		this.idCardNoDownPicUrl = idCardNoDownPicUrl;
	}

	public String getIdCardNoUpPicUrl() {
		return idCardNoUpPicUrl;
	}

	public void setIdCardNoUpPicUrl(String idCardNoUpPicUrl) {
		this.idCardNoUpPicUrl = idCardNoUpPicUrl;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public Double getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(Double availableAmount) {
		this.availableAmount = availableAmount;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(Double consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public String getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(String authenticated) {
		this.authenticated = authenticated;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	
	
}