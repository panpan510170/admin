<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/9/24
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="row" id="showLottery"></div>
</div>
<!-- 翻转弹窗-->
<div class="modal inmodal" id="myModalssq" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated flipInY">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="title_ssq"></h4>
                <small class="font-bold" id="remark_ssq"></small>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>奖项名称</th>
                        <th>中奖数量</th>
                        <th>中奖金额</th>
                        <th>中奖条件</th>
                    </tr>
                    </thead>
                    <tbody id="table_ssq">
                    <%--<tr>
                        <td>1</td>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Larry</td>
                        <td>the Bird</td>
                        <td>@twitter</td>
                    </tr>--%>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>
<script>
    //查询彩票种类
    $.ajax({
        url: "/lottery/selectType",
        type: "post",
        contentType:"application/json",
        dataType: "json",
        success: function (obj) {
            if(obj.code == 1){
                var str = "";
                $.each(obj.data ,function (index,value) {
                   str +=
                        '<div class="col-lg-3" id="result_'+value.lottery_id+'">\n' +
                        '<input type="hidden" id="value_'+value.lottery_id+'" value="'+value.lottery_id+'"/>\n' +
                        '<div class="widget lazur-bg p-lg text-center">\n' +
                        '<div class="m-b-md">\n' +
                        '<i class="fa fa-shield fa-4x"></i>\n' +
                        '<h1 class="m-xs"></h1>\n' +
                        '<h3 class="font-bold no-margins">'+value.lottery_name+'</h3>\n' +
                        '<small>'+value.remarks+'</small>\n' +
                        '</div>\n' +
                        '</div>\n' +
                        '</div>';
                });
                $("#showLottery").append(str);
            }else{
                alertErrorMsg(obj.message,"/system/data");
            }
        },
        error: function (obj) {
            alertErrorMsg(obj.message,"/system/data");
        }
    });
    $(function () {
        //ajax动态追加的click事件  要用on
        $(document).on('click','#result_ssq',function(){
            var param = {
                "lottery_id" : $("#value_ssq").val()
            };
            $.ajax({
                url: "/lottery/selectResult",
                type: "post",
                data:JSON.stringify(param),
                contentType:"application/json",
                dataType: "json",
                success: function (obj) {
                    if(obj.code == 1){
                        $("#title_ssq").html(obj.data.lottery_name+"--"+obj.data.lottery_no);
                        $("#remark_ssq").html("开奖日期:"+obj.data.lottery_date+"---兑奖截止日期:"+obj.data.lottery_exdate+"<br/>本期销售额:"+obj.data.lottery_sale_amount+"---奖池滚存:"+obj.data.lottery_pool_amount+"<br/>中奖号码:"+obj.data.lottery_res);
                        var str = "";
                        $.each(obj.data.lottery_prize,function (index,value) {
                            str +=
                                '<tr>\n' +
                                '<td>'+value.prize_name+'</td>\n' +
                                '<td>'+value.prize_num+'</td>\n' +
                                '<td>'+value.prize_amount+'</td>\n' +
                                '<td>'+value.prize_require+'</td>\n' +
                                '</tr>';
                        });
                        $("#table_ssq").html(str);
                        $("#myModalssq").modal();
                    }else{
                        alertErrorMsg(obj.message,"/system/data");
                    }
                },
                error: function (obj) {
                    alertErrorMsg(obj.message,"/system/data");
                }
            });
        })
    });

</script>
</html>
