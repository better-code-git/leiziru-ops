package com.ureactor.jeesite.modules.product.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 商品评价Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class ProductComment extends DataEntity<ProductComment> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;
	private String memberName;
	private String commentContent;
	private String commentPicUrl;//多图用逗号分隔
	private Date commentDate;
	private Date commentDateStart;
	private Date commentDateEnd;
	private String commentStatus;
	private String productId;
	private List<String> picUrlList =new ArrayList<String>();

	
	public ProductComment() {
		super();
	}

	public ProductComment(String id){
		super(id);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCommentPicUrl() {
		return commentPicUrl;
	}

	public void setCommentPicUrl(String commentPicUrl) {
		this.commentPicUrl = commentPicUrl;
	}

	public Date getCommentDateStart() {
		return commentDateStart;
	}

	public void setCommentDateStart(Date commentDateStart) {
		this.commentDateStart = commentDateStart;
	}

	public Date getCommentDateEnd() {
		return commentDateEnd;
	}

	public void setCommentDateEnd(Date commentDateEnd) {
		this.commentDateEnd = commentDateEnd;
	}

	public List<String> getPicUrlList() {
		if(this.commentPicUrl!=null){
			String picUrlArray[] =this.commentPicUrl.split(",");
			for (String picUrl : picUrlArray) {
				picUrlList.add(picUrl);
			}
		}
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

	
}