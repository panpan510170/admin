<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>free-后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<jsp:include page="/WEB-INF/jsp/common/common_header.jsp"></jsp:include>
<body>

<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse" id="daohang">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span>
                            <img alt="image" class="img-circle" src="/statics/img/profile_small.jpg" />
                            <b style="margin-left: 7%;color: white"><shiro:principal property="userName"/></b>
                        </span>
                    </div>
                </li>
                <%--<li>
                    <a>
                        <i class="fa fa-th-large"></i>
                        <span class="nav-label">Graphs</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="graph_flot.html">Flot Charts</a></li>
                        <li><a href="graph_morris.html">Morris.js Charts</a></li>
                        <li><a href="graph_rickshaw.html">Rickshaw Charts</a></li>
                        <li><a href="graph_chartjs.html">Chart.js</a></li>
                        <li><a href="graph_chartist.html">Chartist</a></li>
                        <li><a href="c3.html">c3 charts</a></li>
                        <li><a href="graph_peity.html">Peity Charts</a></li>
                        <li><a href="graph_sparkline.html">Sparkline Charts</a></li>
                    </ul>
                </li>
                <li>
                    <a>
                        <i class="fa fa-th-large"></i>
                        <span class="nav-label">系统管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse" id="系统管理1">
                        <li><a href="/system/suserList" target="content">用户管理</a></li>
                        <li><a href="/system/sroleList" target="content">角色管理</a></li>
                        <li><a href="/system/sPermissionsList" target="content">权限管理</a></li>
                        <li><a href="/system/suserRoleList" target="content">用户角色管理</a></li>
                    </ul>
                </li>--%>
            </ul>
        </div>
    </nav>
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;background-color: #1a2d41">
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <a onclick="updatePassword()">
                            <i class="fa fa-twitter-square"></i> 修改密码
                        </a>
                    </li>
                    <li>
                        <a href="/logout">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row  border-bottom white-bg dashboard-header">
            <iframe name="content" src="/system/data"  width="100%" height="100%"  frameborder="0"></iframe>
        </div>
    </div>
</div>
<!-- 修改密码 -->
<div class="modal inmodal" id="myModalPassword" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <table>
                        <tr>
                            <td style="text-align: center;padding: 30px;font-size: 1em">
                                <label>密码:</label>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" placeholder="密码" class="form-control" id="password">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal" onclick="down()">关闭</button>
                <button type="button" class="btn btn-primary" onclick="doUpdatePassword()">保存</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>
<script type="text/javascript">
    var userId = sessionStorage.getItem("userId");
    function doUpdatePassword(){
        var flag = true;
        var password = $("#password").val();
        if("" == password || null == password){
            flag = false;
        }
        if(flag){
            var param = {
                "password" : password
            };
            $.ajax({
                url: "/system/updateSystemUser",
                type: "post",
                contentType:"application/json",
                data:JSON.stringify(param),
                dataType: "json",
                //headers:{"Access-Token":token,"Access-Source":"2"},
                success: function (obj) {
                    if(1 != obj.code){
                        $("#myModalPassword").hide();
                        sweetAlert(obj.message);
                    }else{
                        $("#myModalPassword").hide();
                        sweetAlert(obj.message);
                    }
                },
                error: function (obj) {
                    alert(obj);
                }
            });
        }else{
            sweetAlert("参数不对")
        }
    }
    function updatePassword(){
        $("#myModalPassword").show();
    }

    function down(){
        $("#myModalPassword").hide();
    }

    $.ajax({
        url: "/system/userPermissionsList",
        type: "post",
        data:{
            "userId":userId
        },
        dataType: "json",
        async:false,
        //headers:{"Access-Token":token,"Access-Source":"2"},
        success: function (obj) {
            if(1 != obj.code){
                sweetAlert({
                    title: "",
                    text: obj.message,
                    type: "error"
                }, function () {
                    location.href = "login.jsp";
                });

            }else{
                $.each(obj.data ,function (index,value) {
                    $("#side-menu").append(
                        "<li>" +
                        "<a>" +
                        "<i class="+'"fa fa-th-large"'+"></i>" +
                        "<span class=" +'"nav-label"'+">"+value.name+"</span>" +
                        "<span class="+'"fa arrow"'+"></span></a> " +
                        "<ul class="+'"nav nav-second-level collapse"'+" id='"+value.name+"'></ul></li>")
                    var id = value.name;
                    $.each(value.list ,function (index,value) {
                        $("#"+id).append("<li><a href='"+value.url+"' target="+"content"+">"+value.name+"</a></li>");
                    });
                });

            }
        },
        error: function (obj) {
            location.href = "login.jsp";
        }
    });
    /*$("body").on("click","li",function(){
        $(this).siblings('li').removeClass('active');
        $("ul").removeClass('in');
        $(this).addClass('active');
        $(this).children("ul").addClass('in');
    });*/
</script>
</body>
</html>