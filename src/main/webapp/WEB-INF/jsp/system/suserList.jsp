<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!--360浏览器优先以webkit内核解析-->
    <title></title>
    <jsp:include page="/WEB-INF/jsp/common/common_header.jsp"></jsp:include>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="userName">用户名</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" id="userName" name="userName">
                    </div>
                    <div class="col-sm-2" style="text-align:right;">
                        <button type="button" id="btn_query" class="btn btn-primary">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div id="toolbar" class="btn-group">
        <shiro:hasPermission name="user:add">
        <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>增加
        </button>
        </shiro:hasPermission>
    </div>
    <div class="example-wrap">
        <div class="example">
            <table id="tb_list" class="table table-bordered"></table>
        </div>
    </div>
</div>

<!-- 增加 -->
<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">新增用户</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <table>
                        <tr>
                            <td style="text-align: center;padding: 30px;font-size: 1em">
                                <label>用户名称:</label>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" placeholder="用户名称" class="form-control" id="suserName">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;padding: 30px;font-size: 1em">
                                <label>联系电话:</label>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" placeholder="联系电话" class="form-control" id="phone">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addUser()">保存</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>
<script>
     function addUser(){
        var flag = true;
        var suserName = $("#suserName").val();
        var phone = $("#phone").val();

        if("" == suserName || null == suserName){
            flag = false;
        }
        if("" == phone || null == phone){
            flag = false;
        }

        if(flag){
            var param = {
                "userName" : suserName,
                "phone" : phone
            };
            $.ajax({
                url: "/system/addSystemUser",
                type: "post",
                contentType:"application/json",
                data:JSON.stringify(param),
                dataType: "json",
                success: function (obj) {
                    deal(obj,"/system/suserList","/system/suserList");
                },
                error: function (obj) {
                    alert(obj);
                }
            });
        }else{
            sweetAlert("参数不对")
        }
    }

    //导出
    $("#btn_export").click(function () {
        sweetAlert('正在导出...下载窗口未弹出前，请不要关闭此窗口');
        $("#formSearch").attr("action", "v_exportUserAccount.do");
        $("#formSearch").submit();
        $("#formSearch").attr("action", "");
    });


    //点击刷新当前table
    $('#btn_query').click(function () {
        $('#tb_list').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});
        $('#tb_list').bootstrapTable("refresh");
    });

    $('#tb_list').bootstrapTable({
        url:"/system/getUserList",         /*请求后台的URL（*）*/
        method: 'post',                      /*请求方式（*）*/
        search: false,//是否搜索
        pagination: true,//是否分页
        pageSize: 10,//单页记录数
        pageList: [10,20, 50,100],//分页步进值
        sidePagination: "server",//服务端分页
        //contentType: "application/x-www-form-urlencoded",//请求数据内容格式 默认是 application/json 自己根据格式自行服务端处理
        dataType: "json",//期待返回数据类型
        searchAlign: "left",//查询框对齐方式
        queryParamsType: "limit",//查询参数组织方式
/*        ajaxOptions:{
                headers:{"Access-Token":token,"Access-Source":"2"},
        },*/
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                "pageSize": params.limit,
                "pageNo": params.offset,
                "userName": $("#userName").val()
            };
            return param;
        },
        searchOnEnterKey: false,//回车搜索
        showRefresh: true,//刷新按钮
        showColumns: true,//列选择按钮
        buttonsAlign: "left",//按钮对齐方式
        toolbar: "#toolbar",//指定工具栏
        toolbarAlign: "right",//工具栏对齐方式
        idField: "id",
        silentSort: false,
        columns: [
            {
                title: "id",//标题
                field: "id"
            },
            {
                title: "用户名",//标题
                field: "userName"
            },
            {
                title: "手机号",//标题
                field: "phone"
            },
            {
                title: "状态",//标题
                field: "status"
            },
            {
                title: "创建时间",//标题
                field: "createTime",
                formatter: function (value, row, index) {
                    return changeDateFormat(value)
                }
            }
        ], onLoadSuccess: function () {

        }, onLoadError: function () {

        }
    });
</script>
</body>

</html>