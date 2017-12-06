<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>房源小区管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		});
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/product/productBlock/">小区列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="productBlock" action="${ctx}/product/productBlock/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>小区名称：</label>
				<form:input path="blockName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>更新时间：</label>
			<input id="updateStartDate" name="updateStartDate" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${product.updateStartDate}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       		<input id="updateEndDate" name="updateEndDate" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${product.updateEndDate}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li><label>区域：</label>
			<form:select path="productProvinceId" cssStyle="width:100px;">
				<form:option value="" label="--省--"/>
				<form:options items="${serviceAreaList}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
			</form:select>--
			<form:select path="productCityId" cssStyle="width:100px;">
				<form:option value="" label="--市--"/>
			</form:select>--
			<form:select path="productZoneId" cssStyle="width:100px;">
				<form:option value="" label="--区/县--"/>
			</form:select>--
			<form:select path="productDistrictId" cssStyle="width:100px;">
				<form:option value="" label="--商圈--"/>
			</form:select>
			</li>
			<li class="btns">
			<a href="#" onclick="openDialogPro('添加小区', '${ctx}/product/productBlock/form','${ctx}/product/productBlock/save','810px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加小区</a>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>小区ID</th>
				<th>区域</th>
				<th>小区名称</th>
				<th>小区价格</th>
				<th>最后更新</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productBlock">
			<tr>
				<td>
					${productBlock.id}
				</td>
				<td>
				    ${fns:getAreaLabel(productBlock.productProvinceId, '')}--
					${fns:getAreaLabel(productBlock.productCityId, '')}--
					${fns:getAreaLabel(productBlock.productZoneId, '')}--
					${fns:getAreaLabel(productBlock.productDistrictId, '')}
				</td>
				<td>
					${productBlock.blockName}
				</td>
				<td>
					<span id="checked-all-button" class="btn btn-mini"
					onclick="openDialogPro('价格走势', '${ctx}/product/productBlock/blockPrice?id=${productBlock.id}','${ctx}/product/productBlock/savePrice','810', '700px')">价格走势</span>
				</td>
				<td>
					<fmt:formatDate value="${productBlock.updateDate}" type="both"/>
				</td>
				<td>
				    <a href="#" onclick="openDialogPro('编辑小区', '${ctx}/product/productBlock/form?id=${productBlock.id}','${ctx}/product/productBlock/save','810', '700px')">编辑</a>
    				<a  href="#" onclick="updateOp('${ctx}/product/productBlock/form?id=${productBlock.id}')">删除</a>
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