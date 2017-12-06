<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>房源小区</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
	    $(document).ready(function() {
			validateForm = $("#inputForm").validate({
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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productBlock" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">价格走势：</label>
			<div class="controls">
			   <c:forEach items="${productBlock.roomList}" var="room" varStatus="status" >
			 	 <input id="btnSubmitNo" class="btn" type="button" value="${room.roomName}" />
			   </c:forEach>
			</div>
			<br>
			<div class="controls">
				<table class="table table-bordered">
					<tr>
					<td>月份</td><td>平均价格</td><td>最低价格</td>
					<td><input id="addRoomType" class="btn" type="button" value="添加价格"/>&nbsp;</label></td>
					</tr>
				    <c:forEach items="${productBlock.blockPriceList}" var="blockPrice" varStatus="status" > 
					   <tr>
					     <td>
					     ${blockPrice.belongMonth}月份 
					     </td>  
					     <td>
					      ${blockPrice.avagePrice}
					     </td>  
					     <td>
					      ${blockPrice.lowPrice}
					     </td>
					     <td>
					     <input id="addRoomType" class="btn" type="button" value="保存"/>&nbsp;</label>
					     <input id="addRoomType" class="btn" type="button" value="删除"/>&nbsp;</label>
					     </td>
					  	</tr> 
					 </c:forEach>
				</table> 
			 </div>
		</div>
	</form:form>
</body>
</html>