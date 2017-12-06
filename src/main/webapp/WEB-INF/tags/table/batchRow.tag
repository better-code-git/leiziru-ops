<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="buttonName" type="java.lang.String" required="true"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<button class="btn btn-primary btn-sm" onclick="opAll()" 
data-toggle="tooltip" data-placement="top">${buttonName}</button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
$(document).ready(function() {
	$("#checked-all-button").click(function () {
		$("#contentTable tbody tr td input[type='checkbox']").prop("checked", true);
	});
	$("#checked-all-checkbox").click(function () {
		$("#contentTable tbody tr td input[type='checkbox']").prop("checked", true);
	});
    
});

function opAll(){

	// var url = $(this).attr('data-url');
	  var str="";
	  var ids="";
	  $("#contentTable tbody tr td input[type='checkbox']").each(function(){
	    if(true == $(this).is(':checked')){
	      str+=$(this).attr("id")+",";
	    }
	  });
	  if(str.substr(str.length-1)== ','){
	    ids = str.substr(0,str.length-1);
	  }
	  if(ids == ""){
		top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
		return;
	  }
	  var urlOp ='${url}';
	  if(urlOp.indexOf("?")>-1){
			urlOp=urlOp+"&ids="+ids;
      }else{
    	urlOp=urlOp+"?ids="+ids;
      }
	  top.layer.confirm('确认要全部删除数据吗?', {icon: 3, title:'系统提示'}, function(index){
		  updateOp(urlOp)
	  top.layer.close(index);
	});
	 

}
</script>