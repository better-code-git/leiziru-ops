<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>房源管理管理</title>
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
		<li class="active"><a href="${ctx}/product/product/">房源管理列表</a></li>
		<shiro:hasPermission name="product:product:edit"><li><a href="${ctx}/product/product/form">房源管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/product/product/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="productName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
			<form:select path="productStatus" cssStyle="width:100px;">
				<form:option value="" label="全部"/>
				<form:options items="${fns:getDictList('lzr_product_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
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
			<li><label>房屋来源：</label>
				<form:select path="productSource" style="width: 100px;height: 25px;padding:0;"   class="form-control m-b">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('lzr_product_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
			<table:batchRow url="${ctx}/product/product/deleteAll" id="contentTable" buttonName ="批量删除" ></table:batchRow>
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
				<th>
			    <input type="checkbox" id="checked-all-checkbox"/> 
			    <span id="checked-all-button" class="btn btn-mini">全选</span>
			    </th>
				<th>商品ID</th>
				<th>商品名称</th>
				<th>类型</th>
				<th>商品价格</th>
				<th>区域</th>
				<th>房屋来源</th>
				<th>状态</th>
				<th>最后更新</th>
				<th>推荐</th>
				<shiro:hasPermission name="product:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td align="center">
				<label class="inline">
					<input type="checkbox" value="${product.id}" id="${product.id}"/> 
				</label>
				</td>
				<td>
					${product.id}
				</td>
				<td>
					${product.productName}
				</td>
				<td>
					${fns:getDictLabel(product.productRentType, 'lzr_rent_type', '')}
				</td>
				<td>
					${product.productPrice}
				</td>
				<td>
				    ${fns:getAreaLabel(product.productProvinceId, '')}--
					${fns:getAreaLabel(product.productCityId, '')}--
					${fns:getAreaLabel(product.productZoneId, '')}--
					${fns:getAreaLabel(product.productDistrictId, '')}
				</td>
				<td>
					${fns:getDictLabel(product.productSource, 'lzr_product_source', '')}
				</td>
				<td>
					${fns:getDictLabel(product.productStatus, 'lzr_product_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${product.updateDate}" type="both"/>
				</td>
				<td>
					${fns:getDictLabel(product.recommendType, 'lzr_product_home', '')}
				</td>
				<shiro:hasPermission name="product:product:edit"><td>
    				<a href="${ctx}/product/product/form?id=${product.id}">编辑</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="product:product:edit">
					<a href="${ctx}/product/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该房源管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${page.count gt page.pageSize}">
		<div class="pagination">${page}</div>
	</c:if>
</body>
</html>