<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>评价管理</title>
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
		<li class="active"><a href="${ctx}/product/productComment/">评价管理列表</a></li>
		<shiro:hasPermission name="product:productComment:edit"><li><a href="${ctx}/product/productComment/form">评价管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="productComment" action="${ctx}/product/productComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>评价人：</label>
				<form:input path="memberId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>评价时间：</label>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>全选</th>
				<th>评价ID</th>
				<th>评价人</th>
				<th>评价内容</th>
				<th>评价晒图</th>
				<th>评价时间</th>
				<th>商品Id</th>
				<shiro:hasPermission name="product:productComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productComment">
			<tr>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<shiro:hasPermission name="product:productComment:edit"><td>
    				<a href="${ctx}/product/productComment/form?id=${productComment.id}">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="product:productComment:delete">
					<a href="${ctx}/product/productComment/delete?id=${productComment.id}" onclick="return confirmx('确认要删除该房源管理吗？', this.href)">删除</a>
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