<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>消息模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/message/messageDemo/">消息模板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="messageDemo" action="${ctx}/message/messageDemo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
				<form:input path="demoName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<br/>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加模板', '${ctx}/message/messageDemo/form','${ctx}/message/messageDemo/save','810', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加模板</a>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板编号</th>
				<th>模板名称</th>
				<th>模板内容</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="messageDemo">
			<tr>
				<td>
					${messageDemo.id}
				</td>
				<td>
					${messageDemo.demoName}
				</td>
				<td>
					${messageDemo.demoContent}
				</td>
				<td>
					<fmt:formatDate value="${messageDemo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="#" onclick="openDialogPro('编辑模板', '${ctx}/message/messageDemo/form?id=${messageDemo.id}','${ctx}/message/messageDemo/save','810', '700px')">编辑</a>
					<a href="${ctx}/message/messageDemo/delete?id=${messageDemo.id}" onclick="return confirmx('确认要删除该消息模板吗？', this.href)">删除</a>
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