<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>添加模板</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var validateForm;
    $(document).ready(function() {
		validateForm = $("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
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
	<form:form id="inputForm" modelAttribute="messageDemo" action="${ctx}/messageDemo/messageDemo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模板名称：</label>
			<div class="controls">
				<form:input path="demoName" id="demoName" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板内容：</label>
			<div class="controls">
				<form:textarea path="demoContent" id="demoContent" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
			</div>
		</div>
		
	</form:form>
</body>
</html>