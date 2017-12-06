<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>用户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/member/">会员列表</a></li>
		<shiro:hasPermission name="member:member:edit"><li><a href="${ctx}/member/member/form">会员详情</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/member/member/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phoneNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<br/>
			<li><label>身份证：</label>
			<form:input path="idCardNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button"  onclick="search()" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="button"  onclick="reset()" value="重置"/></li>
			<li class="clearfix"></li>
			<br/>
			<li class="btns">
			<table:batchRow url="${ctx}/member/member/deleteAll" id="contentTable" buttonName ="批量删除" ></table:batchRow><!-- 删除按钮 -->
			</li>
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
				<th>会员ID</th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>身份证号码</th>
				<th>手机</th>
				<th>身份认证</th>
				<th>注册日期</th>
				<shiro:hasPermission name="member:member:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member">
			<tr>
				<td align="center">
				<label class="inline">
					<input type="checkbox" value="${member.id}" id="${member.id}"/> 
				</label>
				</td>
				<td>
					${member.id}
				</td>
				<td>
					${member.nickName}
				</td>
				<td>
					${member.realName}
				</td>
				<td>
				    ${fns:getDictLabel(member.sex, 'sex', '')}
				</td>
				<td>
					${member.idCardNo}
				</td>
				<td>
					${member.phoneNo}
				</td>
				<td>
					${fns:getDictLabel(member.authenticated, 'lzr_member_authenticated', '')}
				</td>
				<td>
					<fmt:formatDate value="${member.registeredDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				    <c:if test="${member.memberStatus=='0'}">
				     <a  href="#" onclick="updateOp('${ctx}/member/member/updateStatus?id=${member.id}&memberStatus=1')">${fns:getDictLabel(member.memberStatus, 'lzr_member_status', '')}</a>
				    </c:if>
				    <c:if test="${member.memberStatus=='1'}">
				     <a  href="#" onclick="updateOp('${ctx}/member/member/updateStatus?id=${member.id}&memberStatus=0')">${fns:getDictLabel(member.memberStatus, 'lzr_member_status', '')}</a>
			        </c:if>
    				<a href="${ctx}/member/member/form?id=${member.id}">查看</a>
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