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
    <%--<jsp:include page="/WEB-INF/jsp/common/common_header.jsp"></jsp:include>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

    <link href="/statics/css/fileUpload/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/fileUpload/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/fileUpload/components.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/fileUpload/custom.css" media="all" rel="stylesheet" type="text/css"/>

    <script src="/statics/js/fileUpload/js/plugins/piexif.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/plugins/sortable.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/fileinput.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/locales/fr.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/locales/es.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/themes/fas/theme.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/themes/explorer-fas/theme.js" type="text/javascript"></script>
</head>
<body>
<div class="inner bg-container">
    <div class="row">
        <div class="col-12 col-xl-12">
            <div class="card m-t-35">
                <div class="card-header bg-white">
                    上传
                </div>
                <div class="card-block">
                    <form class="form-horizontal">
                        <fieldset>
                            <!-- Name input-->
                            <div class="form-group row m-t-35">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="name3" class="col-form-label">First Name</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <input type="text" id="name3" class="form-control" placeholder="First Name">
                                    </div>
                                </div>
                            </div>
                            <!-- first name-->
                            <div class="form-group row">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="lastname3" class="col-form-label">Last Name</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="lastname3" placeholder="Last Name">
                                    </div>
                                </div>
                            </div>
                            <!-- last name-->
                            <div class="form-group row">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="email3" class="col-form-label">E-mail</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <input type="text" id="email3" class="form-control" placeholder="E-mail">
                                    </div>
                                </div>
                            </div>
                            <!-- mail name-->
                            <div class="form-group row">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="password3" class="col-form-label">Password</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <input type="password" id="password3" class="form-control" placeholder="Password">
                                    </div>
                                </div>
                            </div>
                            <!-- password-->
                            <div class="form-group row">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="confirm3" class="col-form-label">Confirm Password</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <input type="password" id="confirm3" class="form-control" placeholder="Confirm Password">
                                    </div>
                                </div>
                            </div>
                            <!-- re password name-->
                            <div class="form-group row">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="gender3" class="col-form-label">Gender</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <select class="form-control" id="gender3">
                                            <option>Male</option>
                                            <option>Female</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!-- password-->
                            <div class="form-group row">
                                <div class="col-lg-3 text-lg-right">
                                    <label for="confirm3" class="col-form-label">上传</label>
                                </div>
                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <div class="container">
                                            <input id="file-0" name="file-0" type="file">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- last name-->
                            <%--<div class="form-group row">
                                <div class="col-lg-9 push-lg-3">
                                    <label class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input">
                                        <span class="custom-control-indicator"></span>
                                        <span class="custom-control-description"> I Agree Terms to the
                                                            <a href="#">Terms and Conditions</a></span>
                                    </label>
                                </div>
                            </div>--%>
                            <div class="form-group row">
                                <div class="col-lg-9 push-lg-3">
                                    <button class="btn btn-primary layout_btn_prevent">上传</button>
                                    <%--<button class="btn btn-warning layout_btn_prevent">Cancel</button>--%>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>--%>
<script>
    $("#file-0").fileinput({
        theme: 'fas',
        uploadUrl: '#'
    }).on('filepreupload', function(event, data, previewId, index) {
        alert("上传开始");
        alert('The description entered is:\n\n' + ($('#description').val() || ' NULL'));
    });

    $(".btn-warning").on('click', function () {
        var $el = $("#file-4");
        if ($el.attr('disabled')) {
            $el.fileinput('enable');
        } else {
            $el.fileinput('disable');
        }
    });
    $(".btn-info").on('click', function () {
        $("#file-4").fileinput('refresh', {previewClass: 'bg-info'});
    });
    $(document).ready(function () {
        $("#test-upload").fileinput({
            'theme': 'fas',
            'showPreview': false,
            'allowedFileExtensions': ['jpg', 'png', 'gif'],
            'elErrorContainer': '#errorBlock'
        });
        $("#kv-explorer").fileinput({
            'theme': 'explorer-fas',
            'uploadUrl': '#',
            overwriteInitial: false,
            initialPreviewAsData: true,
            initialPreview: [
                "http://lorempixel.com/1920/1080/nature/1",
                "http://lorempixel.com/1920/1080/nature/2",
                "http://lorempixel.com/1920/1080/nature/3"
            ],
            initialPreviewConfig: [
                {caption: "nature-1.jpg", size: 329892, width: "120px", url: "{$url}", key: 1},
                {caption: "nature-2.jpg", size: 872378, width: "120px", url: "{$url}", key: 2},
                {caption: "nature-3.jpg", size: 632762, width: "120px", url: "{$url}", key: 3}
            ]
        });
    });
</script>
</html>
