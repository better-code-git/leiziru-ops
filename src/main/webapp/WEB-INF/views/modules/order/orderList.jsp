<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>订单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/order/">订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			    <li><label>订单号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>客户号：</label>
				<form:input path="memberId" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>手机号：</label>
				<form:input path="phoneNo" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>客户姓名：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>状态：</label>
				<form:input path="orderStatus" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>订单类型：</label>
				<form:input path="orderType" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>下单时间：</label>
					<input id="orderTimeStart" name="orderTimeStart" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${orderReseve.orderTimeStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       				<input id="orderTimeEnd" name="orderTimeEnd" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${orderReseve.orderTimeEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
				</li>
				<li><label>房屋来源：</label>
					<form:select path="productSource" style="width: 100px;height: 25px;padding:0;"   class="form-control m-b">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('lzr_product_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
			<li class="btns">
			<table:batchRow url="${ctx}/order/order/deleteAll" id="contentTable" buttonName ="批量删除" ></table:batchRow>
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
				<th>下单时间</th>
				<th>订单号</th>
				<th>订单金额</th>
				<th>商品名称</th>
				<th>状态</th>
				<th>订单类型</th>
				<th>房屋来源</th>
				<th>用户ID</th>
				<th>实名认证</th>
				<th>客户名称</th>
				<th>客户手机</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td align="center">
				<label class="inline">
					<input type="checkbox" value="${order.id}" id="${order.id}"/> 
				</label>
				</td>
				<td>
					<fmt:formatDate value="${order.orderTime}" type="both"/>
				</td>
				<td>
					${order.orderId}
				</td>
				<td>
					${order.orderAmount}
				</td>
				<td>
					${order.productName}
				</td>
				<td>
					${order.orderStatus}
				</td>
				<td>
					${order.orderType}
				</td>
				<td>
					${fns:getDictLabel(order.productSource, 'lzr_product_source', '')}
				</td>
				<td>
					${order.memberId}
				</td>
				<td>
				    ${fns:getDictLabel(order.authenticated, 'lzr_member_authenticated', '')}
				</td>
				<td>
					${order.nickName}
				</td>
				<td>
					${order.phoneNo}
				</td>
				<td>
    				<a href="${ctx}/order/order/form?id=${order.id}">查看</a>
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