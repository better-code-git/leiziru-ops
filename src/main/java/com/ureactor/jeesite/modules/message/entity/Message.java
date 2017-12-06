package com.ureactor.jeesite.modules.message.entity;


import java.util.Date;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 消息历史记录Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Message extends DataEntity<Message> {
	
	private static final long serialVersionUID = 1L;
	private String  memberId;
	private Integer msgDemoId;
	private String  demoName;
	private String  demoContent;
	private Date  sendDate;
	private Date sendDateStart;
	private Date sendDateEnd;
	
	public Message() {
		super();
	}

	public Message(String id){
		super(id);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getMsgDemoId() {
		return msgDemoId;
	}

	public void setMsgDemoId(Integer msgDemoId) {
		this.msgDemoId = msgDemoId;
	}

	public String getDemoName() {
		return demoName;
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	public String getDemoContent() {
		return demoContent;
	}

	public void setDemoContent(String demoContent) {
		this.demoContent = demoContent;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getSendDateStart() {
		return sendDateStart;
	}

	public void setSendDateStart(Date sendDateStart) {
		this.sendDateStart = sendDateStart;
	}

	public Date getSendDateEnd() {
		return sendDateEnd;
	}

	public void setSendDateEnd(Date sendDateEnd) {
		this.sendDateEnd = sendDateEnd;
	}


	
}