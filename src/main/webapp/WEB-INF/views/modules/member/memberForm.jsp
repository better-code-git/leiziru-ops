<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>会员详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/member/">会员列表</a></li>
		<li class="active"><a href="${ctx}/member/member/form?id=${member.id}">会员详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="member" action="${ctx}/member/member/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<td>
			    <div align="center">
		       		 <img src="${member.profilePhotoUrl}" style="margin:3px auto;border-radius:50%"/>
				</div>
				<div class="control-group">
					<label>
						${fns:getDictLabel(member.authenticated, 'lzr_member_authenticated', '')}
					</label>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label">${member.nickName}的资料详情</label>
				</div>
				<div class="control-group">
					<label class="control-label">昵称：</label>
					<div class="controls">
						${member.nickName}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别：</label>
					<div class="controls">
						${fns:getDictLabel(member.sex, 'sex', '')}
					</div>
				</div>
				<div class="control-group">	
					<label class="control-label">真实姓名：</label>
					<div class="controls">
						${member.realName}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">生日：</label>
					<div class="controls">
						<fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">身份证号：</label>
					<div class="controls">
						${member.idCardNo}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">手机号：</label>
					<div class="controls">
						${member.phoneNo}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">消费金额：</label>
					<div class="controls">
						${member.consumeAmount}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">注册日期：</label>
					<div class="controls">
						<fmt:formatDate value="${member.registeredDate}" pattern="yyyy-MM-dd"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">押金：</label>
					<div class="controls">
						${member.deposit}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">账户可用余额：</label>
					<div class="controls">
						${member.availableAmount}
					</div>
				</div>
				<div class="form-actions">
					<shiro:hasPermission name="member:member:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="实名信息审核通过"/>&nbsp;</shiro:hasPermission>
					<input id="btnCancel" class="btn" type="button" value="实名信息审核驳回" onclick="history.go(-1)"/>
				</div>
		</td>
		<td>
		     <div class="control-group">
				<label class="control-label">
				     <img src="${member.idCardNoUpPicUrl}" style="text-align:center; width:140px; height:140px;"/>
				</label>
			 </div>
			 <div class="control-group">
				<label class="control-label">
				    <img src="${member.idCardNoDownPicUrl}" style="text-align:center; width:140px; height:140px;"/>
				</label>
			</div>
		</td>
		</tr>
		</thead>
		</table>
	</form:form>
</body>
</html>