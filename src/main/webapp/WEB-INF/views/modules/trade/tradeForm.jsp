<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>活动示例管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/example/example/">活动示例列表</a></li>
		<li class="active"><a href="${ctx}/example/example/form?id=${example.id}">活动示例<shiro:hasPermission name="example:example:edit">${not empty example.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="example:example:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="example" action="${ctx}/example/example/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">活动名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动LOGO：</label>
			<div class="controls">
				<form:input path="logo" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="telNumber" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省：</label>
			<div class="controls">
				<form:input path="province" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市：</label>
			<div class="controls">
				<form:input path="city" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区：</label>
			<div class="controls">
				<form:input path="district" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<!-- 地图 -->
				<div id="map" tabindex="0" style="width: 600px;height: 300px;margin-top:10px;"></div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维度：</label>
			<div class="controls">
				<form:input path="latitude" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经度：</label>
			<div class="controls">
				<form:input path="longitude" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动介绍：</label>
			<div class="controls">
				<form:textarea id="introduce" htmlEscape="true" path="introduce" class="input-xxlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${example.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${example.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">复选框：</label>
			<div class="controls" id="checkedWrap">
				<label class="inline">
					<input type="checkbox" value="option1"> 选项 1
				</label>
				<label class="inline">
					<input type="checkbox" value="option2"> 选项 2
				</label>
				<label class="inline">
					<input type="checkbox" value="option3"> 选项 3
				</label>
				<label class="inline">
					<input type="checkbox" value="option1"> 选项 4
				</label>
				<label class="inline">
					<input type="checkbox" value="option2"> 选项 5
				</label>
			</div>
			<br>
			<div class="controls">
				<span id="checked-all" class="btn btn-mini">全选</span>
				<span id="checked-reverse" class="btn btn-mini">反选</span>
				<span id="checked-unall" class="btn btn-mini">全不选</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="example:example:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<script type="text/javascript">
		// 复选框全选，反选
		$(function () {
			// 全选
			$("#checked-all").click(function () {
				$("#checkedWrap input[type='checkbox']").prop("checked", true);
			});
			// 全不选
			$("#checked-unall").click(function () {
				$("#checkedWrap input[type='checkbox']").prop("checked", false);
			});
			// 反选
			$("#checked-reverse").click(function () {
				$("#checkedWrap input[type='checkbox']").each(function () {
					if ($(this).prop("checked")) {
						$(this).removeAttr("checked");
					} else {
						$(this).prop("checked", true);
					}
				})
			});
		})
	</script>

	<script type="text/javascript">
		// 自定义uerdit富文本配置
		var initUeditor = {
			toolbars: [[
				'source', '|', 'undo', 'redo', '|', 'bold', 'italic', 'underline', '|', 'rowspacingtop', 'rowspacingbottom', 'lineheight', '|', 'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|', 'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|', 'link', 'simpleupload', 'date', 'time'
			]],
			autoHeightEnabled: false, //是否自动长高，默认true
			autoFloatEnabled: false, //是否保持toolbar的位置不动，默认true
			autoClearEmptyNode: true, //getContent时，是否删除空的inlineElement节点（包括嵌套的情况）
			pasteplain: true, //是否默认为纯文本粘贴。false为不使用纯文本粘贴，true为使用纯文本粘贴
			indent: false, // 行首缩进
			initialFrameWidth : 700,  //初始化编辑器宽度，默认1000
			initialFrameHeight : 400,  //初始化编辑器高度，默认320
			zIndex : 0 //编辑器层级的基数,默认是900
		};

		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('introduce', initUeditor);
		ue.addListener("contentChange", function(){
			$("#introduce").val(ue.getContent());
		});
	</script>

	<!-- 地图 -->
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=12e965dfc65f4f50b2b228fe6c57821b&plugin=AMap.Autocomplete"></script><!-- 密钥针对每个项目，需要客户提供 -->
	<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script type="text/javascript">
		var lon = $("#longitude").val() || 116.404;//经度
		var lat = $("#latitude").val() || 39.9150;//纬度
		var map = new AMap.Map("map", {
			resizeEnable: true,
			zoom: 13,//地图显示的缩放级别
			center: [lon, lat]//地图中心点
		});

		//获取经纬度坐标
		var clickEventListener = map.on('click', function (e) {
			var getLat = e.lnglat.getLat();
			var getLng = e.lnglat.getLng();
			$("#latitude").val(getLat);
			$("#longitude").val(getLng);
		});

		var auto = new AMap.Autocomplete({
			input: "address"
		});
		AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
		function select(e) {
			if (e.poi && e.poi.location) {
				map.setZoom(15);
				map.setCenter(e.poi.location);
			}
		}

		// 地图选点-详细地址
		AMap.plugin('AMap.Geocoder', function () {
			var geocoder = new AMap.Geocoder({
				city: "010"//城市，默认：“全国”
			});
			var marker = new AMap.Marker({
				map: map,
				bubble: true
			})
			map.on('click', function (e) {
				marker.setPosition(e.lnglat);
				geocoder.getAddress(e.lnglat, function (status, result) {
					if (status == 'complete') {
						var address_l = result.regeocode.addressComponent;//返回的对象
						var province = address_l.province;//省
						var city = address_l.city;//市
						var district = address_l.district;//区
						$("#province").val(province);
						$("#city").val(city);
						if(!city) {
							$("#city").val(province);
						}
						$("#district").val(district);
						console.log(result.regeocode.addressComponent);
						$("#address").val(result.regeocode.formattedAddress);
					}
				})
			})
		});

		// 监听输入框焦点地图
		$("#province, #city, #district").on("blur",function () {
			var _text = $(this).val();
			map.setCity(_text);
		});
	</script>
</body>
</html>