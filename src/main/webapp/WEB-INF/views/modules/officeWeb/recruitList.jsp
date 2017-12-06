<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>招聘管理</title>
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
		<li class="active"><a href="${ctx}/officeWeb/recruit/">招聘列表</a></li>
		<shiro:hasPermission name="recruit:recruit:edit"><li><a href="${ctx}/recruit/recruit/form">招聘添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recruit" action="${ctx}/recruit/recruit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>查询条件：</label>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>招聘职位</th>
				<th>工作地点</th>
				<th>招聘人数</th>
				<th>招聘要求</th>
				<th>添加时间</th>
				<shiro:hasPermission name="recruit:recruit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recruit">
			<tr>
				<td><a href="${ctx}/recruit/recruit/form?id=${recruit.id}">
					${recruit.id}
				</a></td>
				<td>
					${recruit.jobTitle}
				</td>
				<td>
					${recruit.jobLocation}
				</td>
				<td>
					${recruit.jobNumbers}
				</td>
				<td>
					${recruit.jobRequired}
				</td>
				<td>
					<fmt:formatDate value="${recruit.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="recruit:recruit:edit"><td>
    				<a href="${ctx}/recruit/recruit/form?id=${recruit.id}">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="recruit:recruit:delete">
					<a href="${ctx}/recruit/recruit/delete?id=${recruit.id}" onclick="return confirmx('确认要删除该活动示例吗？', this.href)">删除</a>
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