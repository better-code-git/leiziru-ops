<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>交易记录</title>
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
		<li class="active"><a href="${ctx}/trade/trade/">交易记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="trade" action="${ctx}/trade/trade/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>交易单号：</label>
				<form:input path="tradeCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>交易单号</th>
				<th>交易日期</th>
				<th>会员ID</th>
				<th>会员名称</th>
				<th>交易金额</th>
				<th>交易类型</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trade">
			<tr>
				<td>
					${trade.tradeCode}
				</td>
				<td>
					<fmt:formatDate value="${trade.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${trade.memberId}
				</td>
				<td>
					${trade.nickName}
				</td>
				<td>
					${trade.tradeAmount}
				</td>
				<td>
					${trade.tradeType}
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