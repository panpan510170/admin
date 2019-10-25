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
<style>
    body{
        font-family: '微软雅黑';
    }

    body * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    p{
        line-height: 1.8;
    }
    ul li{
        line-height: 1.8;
    }
    .clearfix:after{
        content: '';
        display: block;
        clear: both;
    }
    .clearfix{
        *zoom: 1;
    }



    footer{
        margin-top: 20px;
        border-top: 1px solid #f1f1f1;
        padding: 10px 0;
        color: #999;
    }


    .jumbotron{
        background-color: #f1f1f1;
        padding: 30px 0;
        margin-bottom: 40px;
    }
    .jumbotron h1{
        font-size:40px;
        color: #555;
        margin-bottom: 20px;
        /*text-shadow: 0 2px 2px #333;*/
    }
    .jumbotron p{
        color: #999;
        font-size:19px;
        line-height: 2;
        text-shadow: 0 1px 1px #ccc;
    }


    .link-container{
        margin-bottom: 15px;
        display: table;
    }
    .link-container a {
        font-size: 16px;
        color: #999;

        display: block;
        float: left;
        margin: 0 20px;
    }
    .link-container .active{
        color: #333;
    }
    .link-container a:hover{
        color: #333;
        text-decoration: none;
    }

    .info-container{
        width: 1000px;
        margin-top: 10px;
        text-align: left;
        padding: 20px 10px;
        color: #666;
        text-shadow: 0 1px 1px #f1f1f1;
        border-bottom: 1px solid #f1f1f1;
    }
    .info-container a{
        color: #666;
    }
    .info-container ul {
        margin-left: 20px;
    }
    .info-container .title{
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 10px;
    }
    .info-container .users-logo-container a {
        display: inline-block;
        margin-right: 10px;
        border: 1px solid #f1f1f1;
        margin-top: 10px;
    }
    .info-container .users-logo-container a:hover {
        text-decoration: none;
    }
    .info-container .users-logo-container a img {
        height: 60px;
    }

    .w-e-text-container{
        height: 700px !important;/*!important是重点，因为原div是行内样式设置的高度300px*/
    }
</style>
<body class="gray-bg">
<!--demo-->
<div style="text-align:left;min-height: 90%">
    <div id="editor"></div>
</div><!--demo end-->

<button onclick="save()" id="btn1">发布文章</button>
<button id="editorSetBtn">设置内容</button>
<button id="editorGetBtn1">获取内容1</button>
<button id="editorGetBtn2">获取内容2</button>
<button id="add">追加内容</button>

</body>
<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>
<script>
    $(function () {
        //上传视屏在wangEditor-video.min.js中写死了  uploadVideoServer和uploadFileName这个属性要修改
        var editor = new wangEditor('#editor');
        editor.customConfig.uploadImgServer = 'upload.php';
        editor.customConfig.uploadImgShowBase64 = true;
        editor.customConfig.uploadImgServer = '/upload/uploadImg'; //上传URL
        editor.customConfig.uploadVideoServer = '/upload/uploadImg'; //上传URL
        editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
        editor.customConfig.uploadImgMaxLength = 15;
        editor.customConfig.uploadFileName = 'myFileName';
        editor.customConfig.uploadImgHooks = {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            // editor.command(null, 'insertHtml', '<img src="' + url + '" alt="' + url + '" style="max-width:100%;"/>');
            // result 必须是一个 JSON 格式字符串！！！否则报错
            customInsert: function (insertImg, result, editor) {
                //TODO 判断上传文件格式类型，执行不同的逻辑
                var url =result.data;
                var imggeshi = ".jpg|.bmp|.gif|.ico|.png";
                var index1=url.lastIndexOf(".");
                var index2=url.length;
                var type=url.substring(index1,index2);
                if(imggeshi.indexOf(type) != -1){
                    insertImg(url);
                }else{
                    editor.txt.append("<embed src='"+url+"'></embed>");
                }
            }
        }
        editor.create();

        $('#btn1').click(function () {
            // 获取编辑器区域完整html代码
            var html = editor.$txt.html();

            // 获取编辑器纯文本内容
            var text = editor.$txt.text();

            // 获取格式化后的纯文本
            var formatText = editor.$txt.formatText();

            alert(html);
            alert(text);
            alert(formatText);
        });

        $("#editorSetBtn").click(function(){
            //这是设置编辑器内容
            editor.txt.html("为什么不显示");
        })
        $("#editorGetBtn1").click(function(){
            //获取编辑器的内容，带样式
            //一般使用这个获取数据，通过ajax发送给服务端　，然后服务端以Ｓtring接收，保存到数据库．
            alert(editor.txt.html());
        })
        $("#editorGetBtn2").click(function(){
            //获取编辑器的内容，不带样式，纯文本
            alert(editor.txt.text());
        })

        $("#add").click(function () {
            editor.txt.append("<embed src='http://xiu8live.oss-cn-beijing.aliyuncs.com/system/img/activity/d9e89a6de2da6f509e9d5a11eec57447.mp4'></embed>");
        });
    });
</script>
</html>
