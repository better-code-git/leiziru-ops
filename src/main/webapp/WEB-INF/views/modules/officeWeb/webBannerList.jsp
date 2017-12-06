<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>BANNER管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/officeWeb/webBanner/">BANNER列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="webBanner" action="${ctx}/officeWeb/webBanner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加轮播图', '${ctx}/officeWeb/webBanner/form','${ctx}/officeWeb/webBanner/save','810px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加广告</a>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>图片</th>
				<th>添加时间</th>
				<th>排序</th>
				<shiro:hasPermission name="webBanner:webBanner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webBanner">
			<tr>
				<td><a href="${ctx}/webBanner/webBanner/form?id=${webBanner.id}">
					${webBanner.id}
				</a></td>
				<td>
				   <img src="${webBanner.webBannerUrl}" style="text-align:center; width:140px; height:140px;"/>
				</td>
				<td>
					<fmt:formatDate value="${webBanner.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${webBanner.sort}
				</td>
				<td>
    				<a href="${ctx}/webBanner/webBanner/form?id=${webBanner.id}">修改</a>
					<a href="${ctx}/webBanner/webBanner/delete?id=${webBanner.id}" onclick="return confirmx('确认要删除该活动示例吗？', this.href)">删除</a>
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