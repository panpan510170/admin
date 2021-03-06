<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/6/10
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<style>
    li{
        padding: 4px 10px;
        border: 1px solid #eee;
        width:fit-content;
        border-radius: 5px;
        margin-bottom: 10px;
    }
</style>
<body>
<div class="container" style="margin-top:100px">
    <div class="panel panel-default">
        <div class="panel-heading">Socket.IO 简易聊天室</div>
        <div class="panel-body">
            <div class="col-md-12">
                <ul class="messages pull-letf list-unstyled"></ul>
                <form action="">
                    <div class="form-group">
                        <textarea class="form-control"  placeholder="输入发言内容"></textarea>
                    </div>
                    <button type="button" class="btn btn-primary">发言</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%--<script src="/socket.io/socket.io.js"></script>--%>
<script src="https://cdn.bootcss.com/socket.io/2.2.0/socket.io.dev.js"></script>
<script src="https://code.jquery.com/jquery-1.11.1.js"></script>
<script>
    $(function () {
        var socket = io();
        $('.btn').click(function(){
            socket.emit('chat message', $('.form-control').val());
            $('.form-control').val('');
        });

        // 监听 chat message事件
        socket.on('chat message', function(msg){
            $('.messages').append($('<li>').text(msg));
        });
    });
</script>
</body>
</html>
