<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>添加商圈</title>
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
			 **根据省选择市
			**/
			$("#provinceId").change(function(){ 
				var provinceId =$("#provinceId").val();
				if(provinceId!=""&&provinceId!=null){
					var provinceName =$("#provinceId").find("option:selected").text();
					$("#provinceName").val(provinceName);
					$.ajax({
				        async: false,
				        url: "${ctx}/overall/serviceArea/selectAreas",
				        data:{"type":2,"provinceId":provinceId},
				        dataType: "json",
				        success: function (data) {
				        	$("#cityId").empty();
			    	    	$("#cityId").append("<option class='required' value=''>--市--"+"</option>");
			    	    	$("#zoneId").empty();
			    	    	$("#zoneId").append("<option class='required' value=''>--区/县--"+"</option>");
				    	    if(data.success){
				    	    	var strs=data.body.serviceAreaList;
				    	    	if(strs.length>0){
				    	    		for (i=0;i<strs.length ;i++){ 
					    	    	$("#cityId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
					    	    	} 	
				    	    	}
				    	    }else{
				    	    	//alert("获取信息异常!");
				    	    }
				        }
					});
				}else{
					$("#provinceName").val("");
				}
			}) 

			 /**
			 **根据市选择区县
			**/
			$('#cityId').change(function(){ 
				var cityId =$("#cityId").val();
				if(cityId!=""&&cityId!=null){
					var cityName =$("#cityId").find("option:selected").text();
					$("#cityName").val(cityName);
					$.ajax({
				        async: false,
				        url: "${ctx}/overall/serviceArea/selectAreas",
				        data:{"type":3,"cityId":cityId},
				        dataType: "json",
				        success: function (data) {
				        	$("#zoneId").empty();
			    	    	$("#zoneId").append("<option class='required' value=''>--区/县--"+"</option>");
				    	    if(data.success){
				    	    	var strs=data.body.serviceAreaList;
				    	    	if(strs.length>0){
				    	    		for (i=0;i<strs.length ;i++){ 
					    	    	$("#zoneId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
					    	    	} 	
				    	    	}
				    	    }else{
				    	    	//alert("获取信息异常!");
				    	    }
				        }
					});
				}else{
					$("#cityName").val("");
				}
			}) 
			

			 /**
			 **选择区县同步区县的名称
			**/
			$("#zoneId").change(function(){ 
				var zoneId =$("#zoneId").val();
				if(zoneId!=""&&zoneId!=null){
					var zoneName =$("#zoneId").find("option:selected").text();
					$("#zoneName").val(zoneName);
				}else{
					$("#zoneName").val("");
				}
			}) 
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="serviceArea" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="provinceName"/>
		<form:hidden path="cityName"/>
		<form:hidden path="zoneName"/>
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
				<form:select path="cityId" cssStyle="width:150px;">
					<form:option value="" label="--市--"/>
					<form:options items="${cityList}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区/县：</label>
			<div class="controls">
				<form:select path="zoneId" cssStyle="width:150px;">
					<form:option value="" label="--区/县--"/>
					<form:options items="${zoneList}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商圈：</label>
			<div class="controls">
				<form:input path="areaName" htmlEscape="false" maxlength="11" class="required"/>
			</div>
		</div>
	</form:form>
</body>
</html>