<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="imgFileData" type="java.lang.String" required="true"%>
<%@ attribute name="imagePreview" type="java.lang.String" required="true"%>
<%@ attribute name="imageUrl" type="java.lang.String" required="true"%>
<%@ attribute name="showUrl" type="java.lang.String" required="true"%>
<%@ attribute name="fileDir" type="java.lang.String" required="true"%>
	<input type='button' onclick="javascript:document.getElementById('${imgFileData}').click();" value='添加图片'/>
	<div style="display:none;">
	<input type="file" accept="image/*" name="${imgFileData}"  id="${imgFileData}"
	onchange="previewFileTag('${imagePreview}','${imgFileData}','${imageUrl}','${fileDir}','${showUrl}')"/>
	</div> 
	<br>
	<img src="${imageUrl}" id="${imagePreview}" style="width:60px; height:80px; background-size: 100% 100%;"/>
	<script type="text/javascript">
	var baseUrl="http://dev-proj.obs.cn-north-1.myhwclouds.com";
	var imageUrlData ="${imageUrl}";
	var imagePreviewData="${imagePreview}";
	if(imageUrlData==""||imageUrlData==null){
		$("#"+imagePreviewData).attr("src", "/leiziru-ops/static/common/upload.jpg");
	}
	/**
	** 图片控件上传图片预览并返回拼接的url
	** param：imagePreview---预览图片<img>标签id
	**       imgFileData ----图片上传<input type="file"> 标签name 
	**       imageUrl---预览图片<img>src属性值
	**       fileDir ---上传的图片的目录
	**       showUrl ---回显的有pic的url
	**/
	function previewFileTag(imagePreview,imgFileData,imageUrl,fileDir,showUrl){
		  var  preview = $("#"+imagePreview.replace(".", "\\."));
		  var file = document.getElementById(imgFileData).files[0];
		  var reader  = new FileReader();
		  var date=new Date();
		  var month = date.getMonth() + 1;
	      var strDate = date.getDate();
	      var hours =date.getHours();
	      var minutes =date.getMinutes();
	      var seconds =date.getSeconds();
	      if (month >= 1 && month <= 9) {
	        month = "0" + month;
	      }
	      if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	      }
	      if (hours >= 1 && hours <= 9) {
	    	  hours = "0" + hours;
	      }
	      if (minutes >= 0 && minutes <= 9) {
	    	  minutes = "0" + minutes;
	      }
	      if (seconds >= 0 && seconds <= 9) {
	    	  seconds = "0" + seconds;
	      }
		  var currentdate = String(date.getFullYear()) +String(month)+String(strDate)+String(hours)+String(minutes)+ String(seconds);
		  reader.onloadend = function() {
			  preview.attr("src", reader.result);
		  }
		  if (file) {
		    reader.readAsDataURL(file);
		    var fileType = file.name;
		    var pos =fileType.indexOf(".");
		    fileType =fileType.substring(pos);
		    var randomText=''; 
		    for(var i=0;i<10;i++){ 
		    	randomText+=Math.floor(Math.random()*10); 
		    } 
		    var wuc=baseUrl+"/"+fileDir+"/"+currentdate+randomText+fileType;
		    showUrl=showUrl.replace(".","\\.");
		    showUrl=showUrl.replace("[","\\[");
		    showUrl=showUrl.replace("]","\\]");
		    $("#"+showUrl).val(wuc);
		  }
	}
	</script>