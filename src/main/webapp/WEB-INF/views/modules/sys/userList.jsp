<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>管理员管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sys/user/list">管理员列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post"
           class="breadcrumb form-search ">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
        <li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50"
                                                            class="input-medium"/></li>
        <li class="btns">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"
                                onclick="return page();"/>
        </li>
        <li class="clearfix"></li>
        <a href="#" onclick="openDialogPro('添加管理员', '${ctx}/sys/user/form','${ctx}/sys/user/save','800px', '700px')" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>添加管理员</a>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>编号</th>
        <th>登录名</th>
        <th>姓名</th>
        <th>手机</th>
        <th>邮箱</th>
        <th>上次登录</th>
        <th>操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.loginName}</td>
            <td>${user.name}</td>
            <td>${user.phone}</td>
            <td>${user.email}</td>
            <td>
            <fmt:formatDate value="${user.loginDate}" type="both"/>
            </td>
            <td>
                <a href="#" onclick="openDialogPro('编辑管理员', '${ctx}/sys/user/form?id=${user.id}','${ctx}/sys/user/save','810px', '700px')">编辑</a>
                <a href="#" onclick="updateOp('${ctx}/sys/user/delete?id=${user.id}');"> 删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>