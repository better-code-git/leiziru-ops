<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>消息发送管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/message/messageSend/">消息发送列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="messageSend" action="${ctx}/message/messageSend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员ID：</label>
				<form:input path="member.id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="member.nickName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="member.realName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="member.phoneNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="member.idCardNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			</ul>
			<ul class="ul-form">
			<li>
			 <form:select path="messageDemoId" cssStyle="width:9em;">
					<form:option  label="消息模板" value=""/>
					<form:options items="${messageDemoList}" itemLabel="demoName" itemValue="id" htmlEscape="false"/>
			</form:select>
			</li>
			<li>
			&nbsp;
			<table:batchRow url="${ctx}/member/member/deleteAll" id="contentTable" buttonName ="发送消息" ></table:batchRow><!-- 删除按钮 -->
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发送至所有会员"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
				 <input type="checkbox" id="checked-all-checkbox"/> 
			     <span id="checked-all-button" class="btn btn-mini">全选</span>
				</th>
				<th>会员Id</th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>身份证号码</th>
				<th>手机</th>
				<th>消费次数</th>
				<th>身份认证</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="messageSend">
			<tr>
				<td>
					<input type="checkbox" value="${messageSend.member.id}" id="${messageSend.member.id}"/> 
				</td>
				<td>
					${messageSend.member.id}
				</td>
				<td>
					${messageSend.member.nickName}
				</td>
				<td>
					${messageSend.member.realName}
				</td>
				<td>
					${messageSend.member.sex}
				</td>
				<td>
					${messageSend.member.idCardNo}
				</td>
				<td>
					${messageSend.member.phoneNo}
				</td>
				<td>
					${messageSend.demoName}
				</td>
				<td>
					${fns:getDictLabel(messageSend.member.authenticated, 'lzr_member_authenticated', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${page.count gt page.pageSize}">
		<div class="pagination">${page}</div>
	</c:if>
</body>
</html>