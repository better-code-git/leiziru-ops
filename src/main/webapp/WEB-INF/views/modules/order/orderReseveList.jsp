<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>预约单管理</title>
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
		<li class="active"><a href="${ctx}/order/orderReseve/">预约单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderReseve" action="${ctx}/order/orderReseve/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>预约单号：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>客户ID：</label>
				<form:input path="memberId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="contactPhoneNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="contactName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="orderStatus" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>下单时间：</label>
				<input id="orderDateStart" name="orderDateStart" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${orderReseve.orderDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       			<input id="orderDateEnd" name="orderDateEnd" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${orderReseve.orderDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li><label>预约时间：</label>
				<input id="reseveTimeStart" name="reseveTimeStart" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${orderReseve.reseveTimeStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       			<input id="reseveTimeEnd" name="reseveTimeEnd" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${orderReseve.reseveTimeEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<br/>
			<li class="btns">
			<table:batchRow url="${ctx}/order/orderReseve/deleteAll" id="contentTable" buttonName ="批量删除" ></table:batchRow>
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
				<th>预约单号</th>
				<th>姓名</th>
				<th>称呼</th>
				<th>手机</th>
				<th>房源信息</th>
				<th>预约时间</th>
				<th>提交用户ID</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderReseve">
			<tr>
				<td align="center">
				<label class="inline">
					<input type="checkbox" value="${orderReseve.id}" id="${orderReseve.id}"/> 
				</label>
				</td>
				<td>
					<fmt:formatDate value="${orderReseve.orderDate}" type="both"/>
				</td>
				<td>
					${orderReseve.id}
				</td>
				<td>
					${orderReseve.contactName}
				</td>
				<td>
					${orderReseve.contactSex}
				</td>
				<td>
					${orderReseve.contactPhoneNo}
				</td>
				<td>
					${orderReseve.productId}
				</td>
				<td>
					<fmt:formatDate value="${orderCommission.reseveTime}" type="both"/>
				</td>
				<td>
					${orderReseve.memberId}
				</td>
				<td>
					${orderReseve.reseveMemo}
				</td>
				<td>
    				<a href="${ctx}/order/orderReseve/form?id=${orderReseve.id}">修改</a>
					<a href="${ctx}/order/orderReseve/delete?id=${orderReseve.id}" onclick="return confirmx('确认要删除该订单管理吗？', this.href)">删除</a>
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