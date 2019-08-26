<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/6
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>free-登陆页</title>
    <jsp:include page="/WEB-INF/jsp/common/common_header.jsp"></jsp:include>
</head>
<body>
<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div style="margin-top: 70%;margin-bottom: 10%">
            <h2 style="color: white">后台管理系统</h2>
        </div>
        <form class="m-t" role="form" action="index.html">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="Username" required="" id="userName" style="width: 100%">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="" id="password" style="width: 100%">
            </div>
            <%--<div class="form-group">
                <input type="vCode" class="form-control" placeholder="验证码" required="" id="vCode" style="width: 40%">
            </div>--%>

            <button type="button" class="btn btn-primary block full-width m-b" onclick="login()">Login</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>
<script>
    function login() {
        $.ajax({
            url: "/system/login",
            type: "post",
            data:{
                "userName":$("#userName").val(),
                "password":$("#password").val()
            },
            dataType: "json",
            success: function (obj) {
                if(1 != obj.code){
                    sweetAlert(obj.message);
                }else{
                   location.href="/index";
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("jqXHR.responseText:"+"["+jqXHR.responseText+"]---"+
                    "jqXHR.status"+"["+jqXHR.status+"]---"+
                    "jqXHR.readyState"+"["+jqXHR.readyState+"]---"+
                    "jqXHR.statusText"+"["+jqXHR.statusText+"]---"+
                    "textStatus"+"["+textStatus+"]---"+
                    "errorThrown"+"["+errorThrown+"]");
            }
        });
    }

</script>
</body>
</html>
