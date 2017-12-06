<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>委托单管理</title>
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
		<li class="active"><a href="${ctx}/order/orderCommission/">委托单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderCommission" action="${ctx}/order/orderCommission/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>客户号：</label>
				<form:input path="memberId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="contactPhoneNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="contactName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="orderStatus" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>提交时间：</label>
			<input id="orderDateStart" name="orderDateStart" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${orderCommission.orderDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       		<input id="orderDateEnd" name="orderDateEnd" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${orderCommission.orderDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li class="clearfix"></li>
			<li class="btns">
			<table:batchRow url="${ctx}/order/orderCommission/deleteAll" id="contentTable" buttonName ="批量删除" ></table:batchRow>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
			    <input type="checkbox" id="checked-all-checkbox"/> 
			    <span id="checked-all-button" class="btn btn-mini">全选</span>
			    </th>
				<th>提交时间</th>
				<th>委托单号</th>
				<th>姓名</th>
				<th>手机</th>
				<th>城市</th>
				<th>小区名</th>
				<th>提交用户ID</th>
				<th>实名认证</th>
				<th>客户名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderCommission">
			<tr>
				<td align="center">
				<label class="inline">
					<input type="checkbox" value="${orderCommission.id}" id="${orderCommission.id}"/> 
				</label>
				</td>
				<td>
					<fmt:formatDate value="${orderCommission.orderDate}" type="both"/>
				</td>
				<td>
					${orderCommission.id}
				</td>
				<td>
					${orderCommission.contactName}
				</td>
				<td>
					${orderCommission.contactPhoneNo}
				</td>
				<td>
					${orderCommission.cityName}
				</td>
				<td>
					${orderCommission.blockName}
				</td>
				<td>
					${orderCommission.memberId}
				</td>
				<td>
					${orderCommission.id}
				</td>
				<td>
					${orderCommission.id}
				</td>
				<td>
    				<a href="${ctx}/order/orderCommission/form?id=${order.id}">修改</a>
					<a href="${ctx}/order/orderCommission/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单管理吗？', this.href)">删除</a>
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