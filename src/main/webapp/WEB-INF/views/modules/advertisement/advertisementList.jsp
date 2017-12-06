<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>广告管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/advertisement/advertisement/">广告列表</a></li>
		<shiro:hasPermission name="advertisement:advertisement:edit"><li><a href="${ctx}/advertisement/advertisement/form">广告添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="advertisement" action="${ctx}/advertisement/advertisement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns">
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加广告', '${ctx}/advertisement/advertisement/form','${ctx}/advertisement/advertisement/save','810px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加广告</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>图片</th>
				<th>链接数据</th>
				<th>添加时间</th>
				<th>位置</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="advertisement">
			<tr>
				<td>
					${advertisement.id}
				</td>
				<td>
				    <img src="${advertisement.adPicUrl}" style="text-align:center; width:140px; height:140px;"/>
				</td>
				<td>
					${advertisement.productId}
				</td>
				<td>
					<fmt:formatDate value="${advertisement.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				    ${fns:getDictLabel(advertisement.adPosition, 'lzr_advertisement_position', '')}
				</td>
				<td>
    				<a  href="#" onclick="openDialogPro('编辑广告', '${ctx}/advertisement/advertisement/form?id=${advertisement.id}','${ctx}/advertisement/advertisement/save','810px', '700px')">编辑</a>
					<a  href="#" onclick="updateOp('${ctx}/advertisement/advertisement/delete?id=${advertisement.id}')">删除</a>
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