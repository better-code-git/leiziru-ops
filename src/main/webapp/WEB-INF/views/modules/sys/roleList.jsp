<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>权限管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sys/role/">权限组列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/role/list" method="post"
           class="breadcrumb form-search ">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
        <a href="#" onclick="openDialogPro('添加权限组', '${ctx}/sys/role/form','${ctx}/sys/role/save','800px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加权限组</a>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <tr>
        <th>编号</th>
        <th>权限组名称</th>
        <th>操作</th>
    	<c:forEach items="${list}" var="role">
        <tr>
            <td>${role.id}</td>
            <td>${role.name}</td>
            <td>
               <a href="#" onclick="openDialogPro('编辑权限组', '${ctx}/sys/role/form?id=${role.id}','${ctx}/sys/role/save','810px', '700px')">编辑</a>
               <a href="#" onclick="updateOp('${ctx}/sys/role/delete?id=${role.id}');"> 删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>