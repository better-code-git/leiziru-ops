<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>推荐管理</title>
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
			$("#productProvinceId").change(function(){ 
				var provinceId =$("#productProvinceId").val();
				if(provinceId!=""||provinceId!=null){
					var productProvinceName =$("#productProvinceId").find("option:selected").text();
					$("#productProvinceName").val(productProvinceName);
					$.ajax({
				        async: false,
				        url: "${ctx}/overall/serviceArea/selectAreas",
				        data:{"type":2,"provinceId":provinceId},
				        dataType: "json",
				        success: function (data) {
				        	$("#productCityId").empty();
			    	    	$("#productCityId").append("<option class='required' value=''>--市--"+"</option>");
			    	    	$("#productZoneId").empty();
			    	    	$("#productZoneId").append("<option class='required' value=''>--区/县--"+"</option>");
			    	    	$("#productDistrictId").empty();
			    	    	$("#productDistrictId").append("<option class='required' value=''>--商圈--"+"</option>");
			    	    	$("#productMetroId").empty();
			    	    	$("#productMetroId").append("<option class='required' value=''>--地铁--"+"</option>");
				    	    if(data.success){
				    	    	var strs=data.body.serviceAreaList;
				    	    	if(strs.length>0){
				    	    		for (i=0;i<strs.length ;i++){ 
					    	    	$("#productCityId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
					    	    	} 	
				    	    	}
				    	    }else{
				    	    	//alert("获取信息异常!");
				    	    }
				        }
					});
				}else{
					$("#productProvinceName").val("");
				}
				
			}) 

			 /**
			 **根据市选择区县
			**/
			$('#productCityId').change(function(){ 
				var cityId =$("#productCityId").val();
				if(cityId!=""||cityId!=null){
					var productCityName =$("#productCityId").find("option:selected").text();
					$("#productCityName").val(productCityName);
					$.ajax({
				        async: false,
				        url: "${ctx}/overall/serviceArea/selectZoneAndMetro",
				        data:{"type":3,"cityId":cityId},
				        dataType: "json",
				        success: function (data) {
				        	$("#productZoneId").empty();
			    	    	$("#productZoneId").append("<option class='required' value=''>--区/县--"+"</option>");
			    	    	$("#productDistrictId").empty();
			    	    	$("#productDistrictId").append("<option class='required' value=''>--商圈--"+"</option>");
			    	    	$("#productMetroId").empty();
			    	    	$("#productMetroId").append("<option class='required' value=''>--地铁--"+"</option>");
			    	    	if(data.success){
				    	    	var strs=data.body.serviceAreaList;
				    	    	if(strs.length>0){
				    	    		for (i=0;i<strs.length ;i++){ 
				    	    			if(strs[i].type=='3'){
				    	    				$("#productZoneId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
				    	    			}else{
				    	    				$("#productMetroId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
				    	    			}
					    	    	
					    	    	} 	
				    	    	}
				    	    }else{
				    	    	//alert("获取信息异常!");
				    	    }
				        }
					});
				}else{
					$("#productCityName").val("");
				}
				
			}) 
			
			
			 /**
			 **根据区县选择商圈
			**/
			$('#productZoneId').change(function(){ 
				var zoneId =$("#productZoneId").val();
				if(zoneId!=""||zoneId!=null){
					var productZoneName =$("#productZoneId").find("option:selected").text();
					$("#productZoneName").val(productZoneName);
					$.ajax({
				        async: false,
				        url: "${ctx}/overall/serviceArea/selectAreas",
				        data:{"type":4,"zoneId":zoneId},
				        dataType: "json",
				        success: function (data) {
				        	$("#productDistrictId").empty();
			    	    	$("#productDistrictId").append("<option class='required' value=''>--商圈--"+"</option>");
				    	    if(data.success){
				    	    	var strs=data.body.serviceAreaList;
				    	    	if(strs.length>0){
				    	    		for (i=0;i<strs.length ;i++){ 
					    	    	$("#productDistrictId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
					    	    	} 	
				    	    	}
				    	    }else{
				    	    	//alert("获取信息异常!");
				    	    }
				        }
					});
				}else{
					$("#productZoneName").val("");
				}
				
			})
			
			 /**
			 **选择商圈名称
			**/
			$("#productDistrictId").change(function(){ 
				var productDistrictId =$("#productDistrictId").val();
				if(productDistrictId!=""&&productDistrictId!=null){
					var productDistrictName =$("#productDistrictId").find("option:selected").text();
					$("#productDistrictName").val(productDistrictName);
				}else{
					$("#productDistrictName").val("");
				}
			}) 
			
			/**
			 **选择地铁名称
			**/
			$("#productMetroId").change(function(){ 
				var productMetroId =$("#productMetroId").val();
				if(productMetroId!=""&&productMetroId!=null){
					var productMetroName =$("#productMetroId").find("option:selected").text();
					$("#productMetroName").val(productMetroName);
				}else{
					$("#productMetroName").val("");
				}
			}) 
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="recommend" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="productProvinceName"/>
		<form:hidden path="productCityName"/>
		<form:hidden path="productZoneName"/>
		<form:hidden path="productMetroName"/>
		<form:hidden path="productDistrictName"/>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:input path="recPicUrl" htmlEscape="false" class="form-control input-xlarge required" readonly="true"/>
				<input type="hidden" name="recPicUrlOld" id="recPicUrlOld" value="${recommend.recPicUrl}"/>
				<div id="uploadMode">
				<table:addPic showUrl="recPicUrl" imageUrl="${recommend.recPicUrl}" fileDir="product" imagePreview="viewImg" imgFileData="imgFileData"></table:addPic>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域：</label>
			<div class="controls">
				<form:select path="productProvinceId" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${serviceAreaListP}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<form:select path="productCityId" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${serviceAreaListC}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<form:select path="productZoneId" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${serviceAreaListZ}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">商圈：</label>
			<div class="controls">
				<form:select path="productDistrictId" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${serviceAreaListD}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label">地铁：</label>
			<div class="controls">
				<form:select path="productMetroId" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${serviceAreaListM}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		</form:form>
</body>
</html>