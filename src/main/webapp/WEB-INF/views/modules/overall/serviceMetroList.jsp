<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>地铁管理</title>
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/overall/serviceMetro/">地铁列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="serviceArea" action="${ctx}/overall/serviceMetro/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<form:select path="provinceId" cssStyle="width:150px;">
				<form:option value="" label="--省--"/>
				<form:options items="${provinceList}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
			</form:select>--
			<form:select path="cityId" cssStyle="width:150px;">
				<form:option value="" label="--市--"/>
			</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加地铁线路', '${ctx}/overall/serviceMetro/form','${ctx}/overall/serviceMetro/save','810px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加地铁线路</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>省</th>
				<th>市</th>
				<th>地铁线路</th>
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
					${serviceArea.areaName}
				</td>
				<td>
    				<a href="#" onclick="openDialogPro('编辑地铁线路', '${ctx}/overall/serviceMetro/form?id=${serviceArea.id}','${ctx}/overall/serviceMetro/save','810px', '700px')">编辑</a>
					<a href="${ctx}/overall/serviceMetro/delete?id=${serviceArea.id}" onclick="return confirmx('确认要删除该活动示例吗？', this.href)">删除</a>
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