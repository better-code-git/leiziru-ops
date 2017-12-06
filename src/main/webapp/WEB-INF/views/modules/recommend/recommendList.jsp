<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>推荐管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recommend/recommend/">推荐列表</a></li>
		<shiro:hasPermission name="recommend:recommend:edit"><li><a href="${ctx}/recommend/recommend/form">推荐添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recommend" action="${ctx}/recommend/recommend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
				<li class="btns">
				<li class="btns">
				<a href="#" onclick="openDialogPro('添加推荐', '${ctx}/recommend/recommend/form','${ctx}/recommend/recommend/save','810px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加推荐</a>
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
				<th>区域</th>
				<th>商圈</th>
				<th>地铁</th>
				<th>添加时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recommend">
			<tr>
				<td>
					${recommend.id}
				</td>
				<td>
				   <img src="${recommend.recPicUrl}" style="text-align:center; width:140px; height:140px;"/>
				</td>
				<td>
					${recommend.productProvinceName}--${recommend.productCityName}--${recommend.productZoneName}
				</td>
				<td>
					${recommend.productDistrictName}
				</td>
				<td>
					${recommend.productMetroName}
				</td>
				<td>
					<fmt:formatDate value="${recommend.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="#" onclick="openDialogPro('编辑推荐', '${ctx}/recommend/recommend/form?id=${recommend.id}','${ctx}/recommend/recommend/save','810px', '700px')">编辑</a>
				    <a  href="#" onclick="updateOp('${ctx}/recommend/recommend/delete?id=${recommend.id}')">删除</a>
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