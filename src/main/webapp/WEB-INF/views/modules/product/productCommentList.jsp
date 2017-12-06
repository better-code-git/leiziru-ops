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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productComment/">评价管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="productComment" action="${ctx}/product/productComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>评价人：</label>
				<form:input path="memberName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>评价时间：</label>
			<input id="commentDateStart" name="commentDateStart" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${productComment.commentDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       		<input id="commentDateEnd" name="commentDateEnd" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${productComment.commentDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
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
					${productComment.id}
				</td>
				<td>
					${productComment.memberName}
				</td>
				<td>
					${productComment.commentContent}
				</td>
				<td>
				    <c:forEach items="${productComment.picUrlList}" var="picUrl">
						<img src="${picUrl}" style="text-align:center; width:50px; height:50px;"/>
					</c:forEach>
				</td>
				<td>
					<fmt:formatDate value="${productComment.commentDate}" type="both"/>
				</td>
				<td>
					${productComment.productId}
				</td>
				<td>
				    <c:if test="${productComment.commentStatus=='0'}">
				    <a  href="#" onclick="updateOp('${ctx}/product/productComment/updateStatus?id=${productComment.id}&commentStatus=1')">上架</a>
				    </c:if>
				    <c:if test="${productComment.commentStatus=='1'}">
				    <a  href="#" onclick="updateOp('${ctx}/product/productComment/updateStatus?id=${productComment.id}&commentStatus=0')">下架</a>
				    </c:if>
				    <a  href="#" onclick="updateOp('${ctx}/product/productComment/delete?id=${productComment.id}')">删除</a>
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