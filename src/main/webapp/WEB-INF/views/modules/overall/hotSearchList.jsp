<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>热搜管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/**
		     ** 添加热搜数据
		     **/
		    $("#btnSubmit").click(function(){
				  if (validateForm.form()) {
					  var provinceName =$("#provinceName").val();
					  var cityName =$("#cityName").val();
					  var provinceId =$("#provinceId").val();
					  var cityId =$("#cityId").val();
					  var type =$("#type").val();
					  $.ajax({
							async : false,
							url : "${ctx}/overall/serviceCity/save",
							contentType:'application/json;charset=UTF-8', 
							data : {"provinceName":provinceName,"provinceId":provinceId,"cityId":cityId,"cityName":cityName,"type":type},
							dataType : "json",
							success : function(data) {
								if (data.success) {
									 sortOrRefresh();
					       			 top.$.jBox.tip(data.msg);
								} else {
									top.$.jBox.info(data.msg);
									}
							}
					 });
				}
			  })
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/overall/hotSearch/">热搜数据</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hotSearch" action="${ctx}/overall/hotSearch/" method="post" class="form-horizontal">
		<form:hidden path="cityId"/>
		<div class="control-group">
			<div class="controls">
				<c:forEach items="${serviceCityList}" var="serviceArea">
					<input id="btnSubmitNo" class="btn" type="button" value="${serviceArea.areaName}" />
					&nbsp;&nbsp;
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<c:forEach items="${hotSearchList}" var="hotSearch">
					<input id="btnSubmitNo" class="btn" type="button" value="${hotSearch.keyName}" />
					&nbsp;&nbsp;
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<form:input path="keyName" id="keyName" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<input id="btnSubmit" class="btn" type="button" value="添加热门搜索" />
			</div>
		</div>
	</form:form>
</body>
</html>