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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
index------------------<input value="获取菜单" onclick="getMenu()" type="button"/>

<script>
    function getMenu() {
        var userId =1;
        $.ajax({
            url: "system/userPermissionsList",
            type: "post",
            dataType: "json",
            //headers:{"Access-Token":token,"Access-Source":"2"},
            success: function (obj) {
                if(1 != obj.code){
                    alert(obj.message);
                    location.href = "login";
                }else{
                   $.each(obj.data ,function (index,value) {
                        alert(value.name);
                        alert(value.list);
                        /*$("#side-menu").append(
                            "<li class="+'"zhui"'+">" +
                            "<a class="+'"zhui"'+">" +
                            "<i class="+'"fa fa-th-large"'+"></i>" +
                            "<span class="+'"nav-label zhui"'+">"+value.name+"</span>" +
                            "<span class="+'"fa arrow"'+"></span>" +
                            "</a>" +
                            "<ul class="+'"nav nav-second-level collapse"'+" id='"+value.name+"'></ul>" +
                            "</li>")
                        var id = value.name;
                        $.each(value.list ,function (index,value) {
                            //alert(value.name);
                            $("#"+id).append("<li class="+'"quanxian"'+"><a href='"+value.url+"' target="+"content"+">"+value.name+"</a></li>");
                        });*/

                    });
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("jqXHR.responseText:"+"["+jqXHR.responseText+"]---"+
                    "jqXHR.status"+"["+jqXHR.status+"]---"+
                    "jqXHR.readyState"+"["+jqXHR.readyState+"]---"+
                    "jqXHR.statusText"+"["+jqXHR.statusText+"]---"+
                    "textStatus"+"["+textStatus+"]---"+
                    "errorThrown"+"["+errorThrown+"]");

                location.href = "login.jsp";
            }

        });
    }

</script>
</body>
</html>