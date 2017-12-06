<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>活动示例管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/example/example/">活动示例列表</a></li>
		<shiro:hasPermission name="example:example:edit"><li><a href="${ctx}/example/example/form">活动示例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="example" action="${ctx}/example/example/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动名称</th>
				<th>活动LOGO</th>
				<th>联系电话</th>
				<th>市</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="example:example:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="example">
			<tr>
				<td><a href="${ctx}/example/example/form?id=${example.id}">
					${example.name}
				</a></td>
				<td>
					${example.logo}
				</td>
				<td>
					${example.telNumber}
				</td>
				<td>
					${example.city}
				</td>
				<td>
					<fmt:formatDate value="${example.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${example.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="example:example:edit"><td>
    				<a href="${ctx}/example/example/form?id=${example.id}">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="example:example:delete">
					<a href="${ctx}/example/example/delete?id=${example.id}" onclick="return confirmx('确认要删除该活动示例吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${page.count gt page.pageSize}">
		<div class="pagination">${page}</div>
	</c:if>
</body>
</html>