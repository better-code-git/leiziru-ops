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
			
			
			 /**
			 **根据省选择市
			**/
			$("#productProvinceId").change(function(){ 
				var provinceId =$("#productProvinceId").val();
				if(provinceId==""||provinceId==null){
					return;
				}
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
			}) 

			 /**
			 **根据市选择区县
			**/
			$('#productCityId').change(function(){ 
				var cityId =$("#productCityId").val();
				if(cityId==""||cityId==null){
					return;
				}
				$.ajax({
			        async: false,
			        url: "${ctx}/overall/serviceArea/selectAreas",
			        data:{"type":3,"cityId":cityId},
			        dataType: "json",
			        success: function (data) {
			        	$("#productZoneId").empty();
		    	    	$("#productZoneId").append("<option class='required' value=''>--区/县--"+"</option>");
		    	    	$("#productDistrictId").empty();
		    	    	$("#productDistrictId").append("<option class='required' value=''>--商圈--"+"</option>");
			    	    if(data.success){
			    	    	var strs=data.body.serviceAreaList;
			    	    	if(strs.length>0){
			    	    		for (i=0;i<strs.length ;i++){ 
				    	    	$("#productZoneId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
				    	    	} 	
			    	    	}
			    	    }else{
			    	    	//alert("获取信息异常!");
			    	    }
			        }
				});
			}) 
			
			
			 /**
			 **根据区县选择商圈
			**/
			$('#productZoneId').change(function(){ 
				var zoneId =$("#productZoneId").val();
				if(zoneId==""||zoneId==null){
					return;
				}
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
			}) 
			
			
			/**
			**增加房屋配套
			*/
		   $("#addRoomType").click(function(){
				var trs = $('#addTr').find('tr');
				var length =trs.length-1;
				var str = '<tr><td>'+
					'<input type="hidden" name="roomList['+length+'].id"/>'+
					'<input type="text"  name="roomList['+length+'].roomName" class="input-medium"/>'+
		     	    '<input onclick="delProductAssort(this)" class="btn" type="button" value="删除"/>&nbsp;</label></td>';
				$('#addTr').find('tr').parent().last().append(str);
			})
				
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
			<label class="control-label">小区名称：</label>
			<div class="controls">	
				<form:input path="blockName" htmlEscape="false" maxlength="255" class="input-medium required"/>
			</div>
		</div>
	<div class="control-group">
			<label class="control-label">位置：</label>
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
				<form:select path="productDistrictId" style="width: 100px;padding:0;"   class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${serviceAreaListD}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">	
				<form:input path="addressDetail" htmlEscape="false" class="input-medium"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小区介绍：</label>
			<div class="controls">	
				<form:textarea id="blockIntroduce" htmlEscape="true" path="blockIntroduce" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小区图片：</label>
			<div class="controls">
				<form:hidden path="blockPicUrl"/>
				<input type="hidden" name="blockPicUrlOld" id="blockPicUrlOld" value="${productBlock.blockPicUrl}"/>
				<table:addPic showUrl="blockPicUrl" imageUrl="${productBlock.blockPicUrl}" fileDir="product" imagePreview="viewImg" imgFileData="imgFileData"></table:addPic>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交通介绍：</label>
			<div class="controls">
				<form:textarea id="trafficIntroduce" htmlEscape="true" path="trafficIntroduce" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">居室类型：</label>
			<div class="controls">
			<table class="table table-bordered" id="addTr">
			 <tr>
			  <td>
				<c:forEach items="${productBlock.roomList}" var="room" varStatus="status">
					<input id="btnSubmitNo" class="btn" type="button" value="${room.roomName}" />
						&nbsp;&nbsp;
				</c:forEach>
					<input id="addRoomType" class="btn" type="button" value="添加居室"/>&nbsp;</label>
			  </td>
			 </tr>
			 </table>
			</div>
		</div>
	</form:form>
</body>
</html>