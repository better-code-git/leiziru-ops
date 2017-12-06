<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>添加城市</title>
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
			
			 /**
			 **选择省同步省的名称
			**/
			$("#provinceId").change(function(){ 
				var provinceId =$("#provinceId").val();
				if(provinceId!=""&&provinceId!=null){
					var provinceName =$("#provinceId").find("option:selected").text();
					$("#provinceName").val(provinceName);
				}else{
					$("#provinceName").val("");
				}
			}) 
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="serviceArea" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="provinceName"/>
		<form:hidden path="type"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
				<form:select path="provinceId" cssStyle="width:150px;">
					<form:option value="" label="--省--"/>
					<form:options items="${provinceList}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市：</label>
			<div class="controls">
				<form:input path="areaName" id="areaName" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
			</div>
		</div>
	</form:form>
</body>
</html>