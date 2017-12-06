<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
	<title>广告管理</title>
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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webBanner" action="#" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden path="adPicUrl"/>
				<input type="hidden" name="webBannerUrlOld" id="webBannerUrlOld" value="${advertisement.webBannerUrl}"/>
				<table:addPic showUrl="adPicUrl" imageUrl="${webBanner.webBannerUrl}" fileDir="product" imagePreview="viewImg" imgFileData="imgFileData"></table:addPic>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">连接数据：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
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