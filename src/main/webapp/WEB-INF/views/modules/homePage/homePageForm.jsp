<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>管理首页</title>
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
		<li class="active"><a href="#">管理首页</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="homePage" action="" method="post" class="form-horizontal">
		<div class="control-group">
		<label class="control-label">最新数据概况：</label>
		    <table class="table table-bordered">
				  <tr>
				  <td style="text-align: center;">
				        <br> 
				        <br> 
				        <br> 
						${homePage.increasedProduct} 
						<br>
						  新增房源
						<br> 
						<br> 
						<br>   
			      </td>
			      <td style="text-align: center;">
			             <br> 
				        <br> 
				        <br> 
						${homePage.increasedOrder}  
						<br> 
						 新增订单
						  <br> 
				        <br> 
				        <br> 
			      </td>
			      <td style="text-align: center;">
			      		 <br> 
				        <br> 
				        <br> 
						${homePage.increasedMember}
						<br> 
						 新增会员
						  <br> 
				        <br> 
				        <br> 
			      </td>
			      </tr>
		    </table>
		</div>
		<div class="control-group">
			<label class="control-label">租房统计：</label>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<td>月份</td>
					<td>租房量</td>
					<td>总金额</td>
				</tr>
			<c:forEach items="${homePage.orderProductCountList}" var="orderProductCount">
				<tr>
					<td>
						${orderProductCount.countMonth}
					</td>
					<td>
						${orderProductCount.countProduct}
					</td>
					<td>
						${orderProductCount.countAmount}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</form:form>
</body>
</html>