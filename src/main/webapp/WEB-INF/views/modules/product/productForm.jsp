<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
    <title>房源管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var validateForm;
        $(document).ready(function () {
            validateForm = $("#inputForm").validate({
//                onfocusout: true,
                ignore: "",
                rules: {
                    productName: {
                        required: true
                    },
                    //月租金
                    productPrice: {
                        required: true
                    },
                    //房屋类型
                    productType: {
                        required: true
                    },
                    productTypeNote: {
                        required: true
                    },
                    productRentType: {
                        required: true
                    },
                    productSource: {
                        required: true
                    },
                    productProvinceId: {
                        required: true
                    },
                    productCityId: {
                        required: true
                    },
                    productZoneId: {
                        required: true
                    },
                    productDistrictId: {
                        required: true
                    },
                    productAddressDetail: {
                        required: true
                    },
                    //地铁
                    productMetroId: {
//                        required:true
                    },
                    productArea: {
                        required: true
                    },
                    productFloor: {
                        required: true
                    },
                    productOrientation: {
                        required: true
                    },
                    decorateType: {
                        required: true
                    },
                    productIntroduce: {
                        required: true
                    },
                    blockId: {
                        required: true
                    },
                    productProfileUrl: {
                        required: true
                    }
                },
                messages: {
                    productProfileUrl: {
                        required: "图片为必选项，请选择上传图片，确认表单无误后点击提交按钮！"
                    }
                },
                submitHandler: function (form) {
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
                },
                groups: {
                    pictest: "productProfileUrl"
                },
                errorPlacement: function (error, element) {
                    if (element.attr("name") == "productProfileUrl")
                        error.insertAfter("#testInfo");
                    else
                        error.insertAfter(element);
                }
            });

            $("#decorateType").change(function () {
                if (value != "") {
                    $("label[for='decorateType']").attr("style", "display:none");
                } else {
                    $("label[for='decorateType']").removeAttr("style");
                }
            });

            $("#submitButton").mouseout(function () {
                var value = document.getElementById("productProfileUrl").value;
                if (value != null) {
                    $("label[for='pictest']").attr("style", "display:none");
                }
            });


            /**
             **根据省选择市
             **/
            $("#productProvinceId").change(function () {
                var provinceId = $("#productProvinceId").val();
                if (provinceId == "" || provinceId == null) {
                    return;
                }
                $.ajax({
                    async: false,
                    url: "${ctx}/overall/serviceArea/selectAreas",
                    data: {"type": 2, "provinceId": provinceId},
                    dataType: "json",
                    success: function (data) {
                        $("#productCityId").empty();
                        $("#productCityId").append("<option class='required' value=''>--市--" + "</option>");
                        $("#productZoneId").empty();
                        $("#productZoneId").append("<option class='required' value=''>--区/县--" + "</option>");
                        $("#productDistrictId").empty();
                        $("#productDistrictId").append("<option class='required' value=''>--商圈--" + "</option>");
                        if (data.success) {
                            var strs = data.body.serviceAreaList;
                            if (strs.length > 0) {
                                for (i = 0; i < strs.length; i++) {
                                    $("#productCityId").append("<option class='required' value='" + strs[i].id + "'>" + strs[i].areaName + "</option>");
                                }
                            }
                        } else {
                            //alert("获取信息异常!");
                        }
                    }
                });
            })

            /**
             **根据市选择区县
             **/
            $('#productCityId').change(function () {
                var cityId = $("#productCityId").val();
                if (cityId == "" || cityId == null) {
                    return;
                }
                $.ajax({
                    async: false,
                    url: "${ctx}/overall/serviceArea/selectAreas",
                    data: {"type": 3, "cityId": cityId},
                    dataType: "json",
                    success: function (data) {
                        $("#productZoneId").empty();
                        $("#productZoneId").append("<option class='required' value=''>--区/县--" + "</option>");
                        $("#productDistrictId").empty();
                        $("#productDistrictId").append("<option class='required' value=''>--商圈--" + "</option>");
                        if (data.success) {
                            var strs = data.body.serviceAreaList;
                            if (strs.length > 0) {
                                for (i = 0; i < strs.length; i++) {
                                    $("#productZoneId").append("<option class='required' value='" + strs[i].id + "'>" + strs[i].areaName + "</option>");
                                }
                            }
                        } else {
                            //alert("获取信息异常!");
                        }
                    }
                });
            })


            /**
             **根据区县选择商圈
             **/
            $('#productZoneId').change(function () {
                var zoneId = $("#productZoneId").val();
                if (zoneId == "" || zoneId == null) {
                    return;
                }
                $.ajax({
                    async: false,
                    url: "${ctx}/overall/serviceArea/selectAreas",
                    data: {"type": 4, "zoneId": zoneId},
                    dataType: "json",
                    success: function (data) {
                        $("#productDistrictId").empty();
                        $("#productDistrictId").append("<option class='required' value=''>--商圈--" + "</option>");
                        if (data.success) {
                            var strs = data.body.serviceAreaList;
                            if (strs.length > 0) {
                                for (i = 0; i < strs.length; i++) {
                                    $("#productDistrictId").append("<option class='required' value='" + strs[i].id + "'>" + strs[i].areaName + "</option>");
                                }
                            }
                        } else {
                            //alert("获取信息异常!");
                        }
                    }
                });
            })


            /**
             **增加房屋配套
             */
            $("#addProductAssort").click(function () {
                var trs = $('#addTr').find('tr');
                var length = trs.length - 1;
                var str = '<tr><td>' +
                    '<input type="hidden" name="productAssortList[' + length + '].id"/>' +
                    '<input type="text"  name="productAssortList[' + length + '].roomName" class="input-medium"/>' +
                    '<input type="text"  name="productAssortList[' + length + '].roomArea"  class="input-medium"/>' +
                    '<input type="radio" name="productAssortList[' + length + '].assort" items="${fns:getDictList('+lzr_product_assort+')}" itemLabel="label" itemValue="value"/>' +
                    '<input onclick="delProductAssort(this)" class="btn" type="button" value="删除"/>&nbsp;</label></td>';
                $('#addTr').find('tr').parent().last().append(str);
            })

            /**
             ** 提交表单
             **/
            $("#btnSubmit").click(function () {
                if (validateForm.form()) {
                    var inputForm = $("#inputForm");
                    doSubmitForAjaxNoRefresh('${ctx}/product/product/save', inputForm);
                }
            })

        });

        /**
         **删除分类
         **/
        function delProductAssort(_this) {
            var trs = $('#addTr').find('tr');
            $(_this).parent().parent().remove();
        }

        function selectJudge(ID) {
//			alert("test method!!!"); //位置错了 NM
            var value = document.getElementById(ID).value;
            if (value != "") {
                alert("test1");
                $("label[for=" + ID + "]").attr("style", "display:none");

            } else {
                alert("test2");
                $("label[for=" + ID + "]").removeAttr("style");
            }
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/product/product/">房源管理列表</a></li>
    <li class="active"><a href="${ctx}/product/product/form?id=${product.id}">房源管理<shiro:hasPermission
            name="product:product:edit">${not empty product.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="product:product:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="product" action="" method="post" class="form-horizontal" onsubmit="return ">
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">商品编号ID：</label>
        <div class="controls">
            <form:input path="id" htmlEscape="false" maxlength="255" class="input-medium" readonly="true"/>
            <label>状态：</label>
            <form:select path="productStatus" cssStyle="width:100px;">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('lzr_product_status')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">房源名称：</label>
        <div class="controls">
            <form:input path="productName" htmlEscape="false" maxlength="255" class="input-medium "/>
            <label>月租金：</label>
            <form:input path="productPrice" htmlEscape="false" maxlength="255" class="input-medium "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">房源标签：</label>
        <div class="controls">
            <form:input path="productLable" htmlEscape="false" maxlength="255" class="input-xlarge"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">房屋类型：</label>
        <div class="controls">
            <form:select path="productType" style="width: 100px;padding:0;" class="form-control m-b "
                         data-validation="[NOTEMPTY]" onchange="selectJudge('productType');">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('lzr_product_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
            <form:input path="productTypeNote" htmlEscape="false" maxlength="100" class="input-medium "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出租方式：</label>
        <div class="controls">
            <form:radiobuttons path="productRentType" items="${fns:getDictList('lzr_rent_type')}"
                               itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;房屋来源：</label>
            <form:select path="productSource" style="width: 100px;padding:0;" class="form-control m-b">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('lzr_product_source')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">位置：</label>
        <div class="controls">
            <form:select path="productProvinceId" style="width: 100px;height: 25px;padding:0;"
                         class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${serviceAreaListP}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
            </form:select>
            <form:select path="productCityId" style="width: 100px;height: 25px;padding:0;" class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${serviceAreaListC}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
            </form:select>
            <form:select path="productZoneId" style="width: 100px;height: 25px;padding:0;" class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${serviceAreaListZ}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
            </form:select>
            <form:select path="productDistrictId" style="width: 100px;height: 25px;padding:0;"
                         class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${serviceAreaListD}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
            </form:select>
            <form:input path="productAddressDetail" htmlEscape="false" class="input-medium"/>
            <label>地铁：</label>
            <form:select path="productMetroId" style="width: 100px;height: 25px;padding:0;" class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${serviceAreaListM}" itemLabel="areaName" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">面积：</label>
        <div class="controls">
            <form:input path="productArea" htmlEscape="false" class="input-medium "/>
            <label>楼层：</label>
            <form:input path="productFloor" htmlEscape="false" class="input-medium "/>
            <label>朝向：</label>
            <form:select path="productOrientation" style="width: 100px;padding:0;" class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('lzr_product_orientation')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">装修：</label>
        <div class="controls">
            <form:select path="decorateType" style="width: 100px;padding:0;" class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('lzr_decorate_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">介绍：</label>
        <div class="controls">
            <form:textarea id="productIntroduce" htmlEscape="true" path="productIntroduce" class="input-xxlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">房屋配套：</label>
        <div class="controls">
            <table class="table table-bordered" id="addTr">
                <tr>
                    <td>
                        <input id="addProductAssort" class="btn" type="button" value="添加配套"/>&nbsp;</label>
                    </td>
                </tr>
                <c:forEach items="${product.productAssortList}" var="productAssort" varStatus="status">
                    <tr>
                        <td>
                            <form:hidden path="productAssortList[${status.index}].id"/>
                            <form:input path="productAssortList[${status.index}].roomName" htmlEscape="false"
                                        class="input-medium"/>
                            <form:input path="productAssortList[${status.index}].roomArea" htmlEscape="false"
                                        class="input-medium"/>
                            <form:radiobuttons path="productAssortList[${status.index}].assort"
                                               items="${fns:getDictList('lzr_product_assort')}" itemLabel="label"
                                               itemValue="value" htmlEscape="false"/>
                            <input id="addProductAssort" onclick="delProductAssort(this)" class="btn" type="button"
                                   value="删除"/>&nbsp;</label>
                        </td>
                    </tr>
                    <label class="control-label"></label>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">所属小区：</label>
        <div class="controls">
            <form:select path="blockId" style="width: 100px;padding:0;" class="form-control m-b ">
                <form:option value="" label="请选择"/>
                <form:options items="${blockList}" itemLabel="blockName" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group" id="testHouseID">
        <label class="control-label">房源主图：</label>
        <div class="controls">
            <table:addPic showUrl="productProfileUrl" imageUrl="${product.productProfileUrl}" fileDir="product"
                          imagePreview="viewImg" imgFileData="imgFileData"></table:addPic>
            <form:hidden id="productProfileUrl" path="productProfileUrl"/>
            <input type="hidden" name="productProfileUrlOld" id="productProfileUrlOld"
                   value="${product.productProfileUrl}"/>
            <p id="testInfo"></p>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">详情图片：</label>
        <div class="controls">
            <table class="table table-bordered">
                <tr>
                    <c:forEach items="${product.productPicList}" var="productPic" varStatus="status">
                        <td>
                            <form:hidden path="productPicList[${status.index}].id"/>
                            <input type="hidden" name="productPicList[${status.index}].picUrlOld"
                                   id="productPicList[${status.index}].picUrlOld" value="${productPic.picUrl}"/>
                            <form:input path="productPicList[${status.index}].picDesc" class="form-control"
                                        maxlength="64" cssStyle="width:80px;"/>
                            <form:hidden path="productPicList[${status.index}].picUrl"
                                         id="productPicList[${status.index}].picUrl"/>
                            <table:addPic showUrl="productPicList[${status.index}].picUrl"
                                          imageUrl="${productPic.picUrl}" fileDir="product"
                                          imagePreview="viewImg_${status.index}"
                                          imgFileData="productPicList[${status.index}].imgFileData"></table:addPic>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
    <div class="form-actions">
        <input type="hidden" name="recommendType" value="1"/>
        <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>