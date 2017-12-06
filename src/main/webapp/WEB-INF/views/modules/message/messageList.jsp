<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>消息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/message/message/">消息管理列表</a></li>
		<shiro:hasPermission name="message:message:edit"><li><a href="${ctx}/message/message/form">消息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="message" action="${ctx}/message/message/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="demoName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>发送时间：</label>
				<input id="sendDateStart" name="sendDateStart" type="text" readonly="readonly" maxlength="20"  class="input-mini Wdate"
                   value="<fmt:formatDate value="${message.sendDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                   --
       				<input id="sendDateEnd" name="sendDateEnd" type="text" readonly="readonly" maxlength="20"
                   class="input-mini Wdate" value="<fmt:formatDate value="${message.sendDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>消息Id</th>
				<th>模板标题</th>
				<th>内容</th>
				<th>会员</th>
				<th>发送时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="message">
			<tr>
				<td>
					${message.id}
				</td>
				<td>
					${message.demoName}
				</td>
				<td>
					${message.demoName}
				</td>
				<td>
					${message.demoName}
				</td>
				<td>
					<fmt:formatDate value="${message.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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