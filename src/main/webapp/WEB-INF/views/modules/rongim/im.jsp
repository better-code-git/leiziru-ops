<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>IM</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/rongIM/vendor/jqueryrebox/jquery-rebox-0.1.0.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/rongIM/css/RongIMWidget.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/rongIM/css/main.css"/>
</head>
<body>
	<div ng-app="demo">
		<div ng-controller="main">
			<input type="hidden" class="input" id="token" value="${fns:getConfig('rong.im.kefuatoken')}"/>
			<input type="hidden" class="input" ng-model="token" ng-change="" ng-value="${fns:getConfig('rong.im.kefuatoken')}"/>
			<h2>当前用户：<span ng-bind="user"></span></h2><br/>
			<label>用户Id：</label><input class="input" ng-model="targetId">
			<button class="btn" ng-click="setconversation()">发送消息</button><br/> <br/>
			<rong-widget></rong-widget>
		</div>
	</div>

	<script src="${ctxStatic}/rongIM/vendor/jquery-2.2.2.js"></script>
	<script src="${ctxStatic}/rongIM/vendor/angular-1.4.8.js"></script>

	<!-- 融云IMLib -->
	<script src="http://cdn.ronghub.com/RongIMLib-2.2.5.min.js"></script>
	<script src="http://cdn.ronghub.com/RongEmoji-2.2.5.min.js"></script>
	<script src="http://cdn.ronghub.com/Libamr-2.2.5.min.js"></script>
	<script src="http://cdn.ronghub.com/RongIMVoice-2.2.5.min.js"></script>

	<!-- 增强体验插件 -->
	<script src="${ctxStatic}/rongIM/vendor/jqueryrebox/jquery-rebox-0.1.0.js"></script>

	<!-- 上传插件 -->
	<script src="${ctxStatic}/rongIM/vendor/plupload.full.min-2.1.1.js"></script>
	<script src="${ctxStatic}/rongIM/vendor/qiniu-1.0.17.js"></script>

	<!-- IM插件 -->
	<script src="${ctxStatic}/rongIM/js/RongIMWidget.js"></script>
	<script src="${ctxStatic}/rongIM/js/common.js"></script>

	<script type="text/javascript">
		var demo = angular.module("demo", ["RongWebIMWidget"]);

		demo.controller("main", ["$scope", "WebIMWidget", "$http", function($scope, WebIMWidget, $http) {

			// $scope.token = angular.element("#token").val();
			$scope.token = "FL+pA8/3JIdCg+sE1SdCBYPQrPrle5nC2qXd4ndhiiSkfcz5SuWaHHCj8ArSKDpcZ9A2x+pvmS/5P9/7DC15pVawwFt78XIu";

			//注意实际应用中 appkey 、 token 使用自己从融云服务器注册的。
			var config = {
				appkey: 'lmxuhwagl4vjd',
				token: $scope.token,
				displayConversationList: true,
				style:{
					left:3,
					bottom:3,
					width:430
				},
				onSuccess: function(id) {
					$scope.user = id;
				},
				onError: function(error) {
					console.log('连接失败：' + error);
				}
			};
			var serviceUrl = '${ctx}';
			RongDemo.common(WebIMWidget, config, serviceUrl, $scope, $http);

		}]);
	</script>
</body>

</html>