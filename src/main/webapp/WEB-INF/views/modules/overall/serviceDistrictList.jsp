<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>商圈信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			 /**
			 **根据省选择市
			**/
			$("#provinceId").change(function(){ 
				var provinceId =$("#provinceId").val();
				if(provinceId==""||provinceId==null){
					return;
				}
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
		    	    	$("#districtId").empty();
		    	    	$("#districtId").append("<option class='required' value=''>--商圈--"+"</option>");
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
			}) 

			 /**
			 **根据市选择区县
			**/
			$('#cityId').change(function(){ 
				var cityId =$("#cityId").val();
				if(cityId==""||cityId==null){
					return;
				}
				$.ajax({
			        async: false,
			        url: "${ctx}/overall/serviceArea/selectAreas",
			        data:{"type":3,"cityId":cityId},
			        dataType: "json",
			        success: function (data) {
			        	$("#zoneId").empty();
		    	    	$("#zoneId").append("<option class='required' value=''>--区/县--"+"</option>");
		    	    	$("#districtId").empty();
		    	    	$("#districtId").append("<option class='required' value=''>--商圈--"+"</option>");
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
			}) 
			
			 /**
			 **根据区县选择商圈
			**/
			$('#zoneId').change(function(){ 
				var zoneId =$("#zoneId").val();
				if(zoneId==""||zoneId==null){
					return;
				}
				$.ajax({
			        async: false,
			        url: "${ctx}/overall/serviceArea/selectAreas",
			        data:{"type":4,"zoneId":zoneId},
			        dataType: "json",
			        success: function (data) {
			        	$("#districtId").empty();
		    	    	$("#districtId").append("<option class='required' value=''>--商圈--"+"</option>");
			    	    if(data.success){
			    	    	var strs=data.body.serviceAreaList;
			    	    	if(strs.length>0){
			    	    		for (i=0;i<strs.length ;i++){ 
				    	    	$("#districtId").append("<option class='required' value='"+strs[i].id+"'>"+strs[i].areaName+"</option>");
				    	    	} 	
			    	    	}
			    	    }else{
			    	    	//alert("获取信息异常!");
			    	    }
			        }
				});
			})
			
		});
	</script>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/overall/serviceDistrict/">商圈信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="serviceArea" action="${ctx}/overall/serviceDistrict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		   <li>
			<form:select path="provinceId" cssStyle="width:100px;">
				<form:option value="" label="--省--"/>
				<form:options items="${provinceList}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
			</form:select>--
			<form:select path="cityId" cssStyle="width:100px;">
				<form:option value="" label="--市--"/>
			</form:select>--
			<form:select path="zoneId" cssStyle="width:100px;">
				<form:option value="" label="--区/县--"/>
			</form:select>--
			<form:select path="districtId" cssStyle="width:100px;">
				<form:option value="" label="--商圈--"/>
			</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加省份', '${ctx}/overall/serviceDistrict/addProvince','${ctx}/overall/serviceDistrict/save','810px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加省</a>
			</li>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加城市', '${ctx}/overall/serviceDistrict/addCity','${ctx}/overall/serviceDistrict/save','800px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加市</a>
			</li>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加区/县', '${ctx}/overall/serviceDistrict/addZone','${ctx}/overall/serviceDistrict/save','800px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加区县</a>
			</li>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加商圈', '${ctx}/overall/serviceDistrict/form','${ctx}/overall/serviceDistrict/save','800px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加商圈</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>省</th>
				<th>市</th>
				<th>区/县</th>
				<th>商圈</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="serviceArea">
			<tr>
				<td>
				${serviceArea.provinceName}
				</td>
				<td>
				${serviceArea.cityName}
				</td>
				<td>
				${serviceArea.zoneName}
				</td>
				<td>
				${serviceArea.areaName}
				</td>
				<td>
    				<a href="#" onclick="openDialogPro('编辑商圈', '${ctx}/overall/serviceDistrict/form?id=${serviceArea.id}','${ctx}/overall/serviceDistrict/save','810px', '700px')">编辑</a>
					<a href="#" onclick="updateOp('${ctx}/overall/serviceDistrict/delete?id=${serviceArea.id}');"> 删除</a>
					<!--  
					<a href="" onclick="return confirmx('确认要删除该区域信息吗？', this.href)">删除</a>
				    -->
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>