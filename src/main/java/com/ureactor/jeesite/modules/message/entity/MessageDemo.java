package com.ureactor.jeesite.modules.message.entity;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 消息模板Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class MessageDemo extends DataEntity<MessageDemo> {
	
	private static final long serialVersionUID = 1L;
	private String demoName;
	private String demoContent; 
	

	
	public MessageDemo() {
		super();
	}

	public MessageDemo(String id){
		super(id);
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

	
}