<%--
  Created by IntelliJ IDEA.
  User: crazyang
  Date: 2018-6-6
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
login-----------------<input value="登录" onclick="login()" type="button"/>
<script>
    function login() {
        var userName = "admin";
        var pas = "111111";
        $.ajax({
            url: "login1?userName="+userName+"&password="+pas,
            type: "post",
            dataType: "json",
            success: function (obj) {
                if(1 != obj.code){
                    alert(obj.message);
                }else{
                    location.href="index";

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