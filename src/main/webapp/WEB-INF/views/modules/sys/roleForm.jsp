<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>权限组管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script type="text/javascript">
      var validateForm;
        $(document).ready(function () {
            $("#name").focus();
            validateForm = $("#inputForm").validate({
                rules: {
                    name: {remote: "${ctx}/sys/role/checkName?oldName=" + encodeURIComponent("${role.name}")},
                    enname: {remote: "${ctx}/sys/role/checkEnname?oldEnname=" + encodeURIComponent("${role.enname}")}
                },
                messages: {
                    name: {remote: "角色名已存在"},
                    enname: {remote: "英文名已存在"}
                },
                submitHandler: function (form) {
                    var ids = [], nodes = tree.getCheckedNodes(true);
                    for (var i = 0; i < nodes.length; i++) {
                        ids.push(nodes[i].id);
                    }
                    $("#menuIds").val(ids);
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

            var setting = {
                check: {enable: true, nocheckInherit: true}, view: {selectedMulti: false},
                data: {simpleData: {enable: true}}, callback: {
                    beforeClick: function (id, node) {
                        tree.checkNode(node, !node.checked, true, true);
                        return false;
                    }
                }
            };

            // 用户-菜单
            var zNodes = [
                    <c:forEach items="${menuList}" var="menu">{
                    id: "${menu.id}",
                    pId: "${not empty menu.parent.id?menu.parent.id:0}",
                    name: "${not empty menu.parent.id?menu.name:'权限列表'}"
                },
                </c:forEach>];
            // 初始化树结构
            var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
            // 不选择父节点
            tree.setting.check.chkboxType = {"Y": "ps", "N": "s"};
            // 默认选择节点
            var ids = "${role.menuIds}".split(",");
            for (var i = 0; i < ids.length; i++) {
                var node = tree.getNodeByParam("id", ids[i]);
                try {
                    tree.checkNode(node, true, false);
                } catch (e) {
                }
            }
            // 默认展开全部节点
            tree.expandAll(true);

            // 用户-机构
            var zNodes2 = [
                    <c:forEach items="${officeList}" var="office">{
                    id: "${office.id}",
                    pId: "${not empty office.parent?office.parent.id:0}",
                    name: "${office.name}"
                },
                </c:forEach>];
            // 初始化树结构
            var tree2 = $.fn.zTree.init($("#officeTree"), setting, zNodes2);
            // 不选择父节点
            tree2.setting.check.chkboxType = {"Y": "ps", "N": "s"};
            // 默认展开全部节点
            tree2.expandAll(true);
            // 刷新（显示/隐藏）机构
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
</ul>
<br/>
<form:form id="inputForm" modelAttribute="role" action="" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">权限组名称:</label>
        <div class="controls">
            <input id="oldName" name="oldName" type="hidden" value="${role.name}">
            <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">权限授权:</label>
        <div class="controls">
            <form:checkboxes  path="menuList" items="${menuList}" 
				itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
        </div>
    </div>
</form:form>
</body>
</html>