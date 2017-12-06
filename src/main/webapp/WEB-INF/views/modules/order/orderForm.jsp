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
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/order/">订单列表</a></li>
		<li class="active"><a href="${ctx}/order/order/form?id=${order.id}">订单详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="order" action="${ctx}/order/order/save" method="post" class="breadcrumb form-search">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th rowspan="1" colspan="6" style="text-align: center;" height="50px">下单/支付</th>
			</tr>
			<tr>
				<td colspan="2">订单号</td>
				<td colspan="2">下单时间</td>
				<td colspan="2">订单金额</td>
			</tr>
			<tr>
				<td colspan="2">${order.orderId}</td>
				<td colspan="2"><fmt:formatDate value="${order.orderTime}" type="both"/></td>
				<td colspan="2">${order.orderAmount}</td>
			</tr>
		    <tr>
				<th rowspan="1" colspan="6" style="text-align: center;" height="50px">买家信息</th>
			</tr>
			<tr>
				<td colspan="2">买家</td>
				<td colspan="2">联系方式</td>
				<td colspan="2">姓名</td>
			</tr>
			<tr>
				<td colspan="2">${order.orderId}</td>
				<td colspan="2"><fmt:formatDate value="${order.orderTime}" type="both"/></td>
				<td colspan="2">${order.orderAmount}</td>
			</tr>
			<tr>
				<td colspan="2">身份证</td>
				<td colspan="4">身份证照片</td>
			</tr>
			<tr>
				<td colspan="2">${order.orderId}</td>
				<td colspan="4">${order.orderId}</td>
			</tr>
		    <tr>
				<th rowspan="1" colspan="6" style="text-align: center;" height="50px">支付详情</th>
			</tr>
			<tr>
			<td colspan="6">支付方式 ：${order.payType}  ${fn:length(order.orderSubList)} </td>
			</tr>
			<tr>
				<td>商品ID</td>
				<td>商品名称</td>
				<td>价格</td>
				<td>期数</td>
				<td>金额</td>
				<td>状态</td>
			</tr>
			<tr>
				<td rowspan="${fn:length(order.orderSubList)}">${order.orderId}</td>
				<td rowspan="${fn:length(order.orderSubList)}"><fmt:formatDate value="${order.orderTime}" type="both"/></td>
				<td rowspan="${fn:length(order.orderSubList)}">${order.orderAmount}</td>
			</tr>
			<c:forEach items="${order.orderSubList}" var="orderSub">
			<tr>
				<td>${orderSub.orderId}</td>
				<td>${orderSub.orderId}</td>
				<td>${orderSub.orderId}</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</form:form>
</body>
</html>