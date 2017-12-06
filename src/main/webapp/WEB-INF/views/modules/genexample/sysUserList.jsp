<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>代码生成示例管理</title>
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
		<li class="active"><a href="${ctx}/genexample/sysUser/">代码生成示例列表</a></li>
		<shiro:hasPermission name="genexample:sysUser:edit"><li><a href="${ctx}/genexample/sysUser/form">代码生成示例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysUser" action="${ctx}/genexample/sysUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>登录名</th>
				<th>密码</th>
				<th>工号</th>
				<th>姓名</th>
				<th>邮箱</th>
				<th>电话</th>
				<th>创建时间</th>
				<shiro:hasPermission name="genexample:sysUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysUser">
			<tr>
				<td><a href="${ctx}/genexample/sysUser/form?id=${sysUser.id}">
					${sysUser.companyId}
				</a></td>
				<td>
					${sysUser.office.name}
				</td>
				<td>
					${sysUser.loginName}
				</td>
				<td>
					${sysUser.password}
				</td>
				<td>
					${sysUser.no}
				</td>
				<td>
					${sysUser.name}
				</td>
				<td>
					${sysUser.email}
				</td>
				<td>
					${sysUser.phone}
				</td>
				<td>
					<fmt:formatDate value="${sysUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="genexample:sysUser:edit"><td>
    				<a href="${ctx}/genexample/sysUser/form?id=${sysUser.id}">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="genexample:sysUser:delete">
					<a href="${ctx}/genexample/sysUser/delete?id=${sysUser.id}" onclick="return confirmx('确认要删除该代码生成示例吗？', this.href)">删除</a>
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