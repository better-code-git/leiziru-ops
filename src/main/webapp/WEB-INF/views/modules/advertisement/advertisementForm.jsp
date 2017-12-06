<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>广告管理</title>
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
	<form:form id="inputForm" modelAttribute="advertisement" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">位置：</label>
			<div class="controls">
				<form:select path="adPosition" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('lzr_advertisement_position')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden path="adPicUrl"/>
				<input type="hidden" name="adPicUrlOld" id="adPicUrlOld" value="${advertisement.adPicUrl}"/>
				<table:addPic showUrl="adPicUrl" imageUrl="${advertisement.adPicUrl}" fileDir="product" imagePreview="viewImg" imgFileData="imgFileData"></table:addPic>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">连接数据：</label>
			<div class="controls">
				<form:input path="productId" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
	</form:form>
</body>
</html>