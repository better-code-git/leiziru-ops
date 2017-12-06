package com.ureactor.jeesite.modules.message.entity;

import java.util.Date;


import com.ureactor.jeesite.common.persistence.DataEntity;
import com.ureactor.jeesite.modules.member.entity.Member;

/**
 * 消息发送Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class MessageSend extends DataEntity<MessageSend> {
	
	private static final long serialVersionUID = 1L;
	private String messageDemoId;		// 消息模板编号
	private String demoName;
	private Member member ;		        // 会员信息
	private String memberIds;           //会员Id
	private Date sendDate;		        // 发送时间
	private Integer sendType ;          //0- 所有人 1-选定范围的会员
	
	public MessageSend() {
		super();
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getMessageDemoId() {
		return messageDemoId;
	}

	public void setMessageDemoId(String messageDemoId) {
		this.messageDemoId = messageDemoId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public String getDemoName() {
		return demoName;
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	
}