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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

    <link href="/statics/css/fileUpload/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/fileUpload/theme.css" media="all" rel="stylesheet" type="text/css"/>

    <script src="/statics/js/fileUpload/js/plugins/piexif.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/plugins/sortable.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/fileinput.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/locales/fr.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/locales/es.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/themes/fas/theme.js" type="text/javascript"></script>
    <script src="/statics/js/fileUpload/js/themes/explorer-fas/theme.js" type="text/javascript"></script>
</head>
<body>
<div class="container my-4">
    <h1>Bootstrap File Input Examples
        <small><a href="https://github.com/kartik-v/bootstrap-fileinput-samples"><i
                class="glyphicon glyphicon-download"></i> Download Sample Files</a></small>
    </h1>
    <hr>
    <form enctype="multipart/form-data">
        <div class="file-loading">
            <input id="kv-explorer" type="file" multiple>
        </div>
        <br>
        <div class="file-loading">
            <input id="file-0a" class="file" type="file" multiple data-min-file-count="1" data-theme="fas">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-outline-secondary">Reset</button>
    </form>
    <hr>
    <h5>Preupload Validation</h5>
    <input id="file-0" name="file-0" type="file">
    <br>
    <textarea id="description" rows=3" class="form-control" placeholder="Enter description for the files selected..."></textarea>
    <br>
    <form enctype="multipart/form-data">
        <label for="file-0b">Test invalid input type</label>
        <div class="file-loading">
            <input id="file-0b" name="file-0b" class="file" type="text" multiple data-min-file-count="1" data-theme="fas">
        </div>
        <script>
            $(document).on('ready', function () {
                $("#file-0b").fileinput();
            });
        </script>
    </form>
    <hr>
    <form enctype="multipart/form-data">
        <div class="file-loading">
            <input id="file-0c" class="file" type="file" multiple data-min-file-count="3" data-theme="fas">
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <input id="file-0d" class="file" type="file" data-theme="fas">
            </div>
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="2" data-theme="fas">
            </div>
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <input id="file-2" type="file" class="file" readonly data-show-upload="false" data-theme="fas">
            </div>
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <label>Preview File Icon</label>
                <input id="file-3" type="file" multiple>
            </div>
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <input id="file-4" type="file" class="file" data-upload-url="#" data-theme="fas">
            </div>
        </div>
        <hr>
        <div class="form-group">
            <button class="btn btn-warning" type="button">Disable Test</button>
            <button class="btn btn-info" type="reset">Refresh Test</button>
            <button class="btn btn-primary">Submit</button>
            <button class="btn btn-outline-secondary" type="reset">Reset</button>
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <input type="file" class="file" id="test-upload" multiple data-theme="fas">
            </div>
            <div id="errorBlock" class="help-block"></div>
        </div>
        <hr>
        <div class="form-group">
            <div class="file-loading">
                <input id="file-5" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#" data-theme="fas">
            </div>
        </div>
    </form>


    <hr>
    <h4>Multi Language Inputs</h4>
    <form enctype="multipart/form-data">
        <label>French Input</label>
        <div class="file-loading">
            <input id="file-fr" name="file-fr[]" type="file" multiple>
        </div>
        <hr style="border: 2px dotted">
        <label>Spanish Input</label>
        <div class="file-loading">
            <input id="file-es" name="file-es[]" type="file" multiple>
        </div>
    </form>
    <hr>
    <br>
</div>
</body>
<%--<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>--%>
<script>
    $('#file-fr').fileinput({
        theme: 'fas',
        language: 'fr',
        uploadUrl: '#',
        allowedFileExtensions: ['jpg', 'png', 'gif']
    });
    $('#file-es').fileinput({
        theme: 'fas',
        language: 'es',
        uploadUrl: '#',
        allowedFileExtensions: ['jpg', 'png', 'gif']
    });
    $("#file-0").fileinput({
        theme: 'fas',
        uploadUrl: '#'
    }).on('filepreupload', function(event, data, previewId, index) {
        alert('The description entered is:\n\n' + ($('#description').val() || ' NULL'));
    });
    $("#file-1").fileinput({
        theme: 'fas',
        uploadUrl: '#', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 10,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });
    /*
     $(".file").on('fileselect', function(event, n, l) {
     alert('File Selected. Name: ' + l + ', Num: ' + n);
     });
     */
    $("#file-3").fileinput({
        theme: 'fas',
        showUpload: false,
        showCaption: false,
        browseClass: "btn btn-primary btn-lg",
        fileType: "any",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        overwriteInitial: false,
        initialPreviewAsData: true,
        initialPreview: [
            "http://lorempixel.com/1920/1080/transport/1",
            "http://lorempixel.com/1920/1080/transport/2",
            "http://lorempixel.com/1920/1080/transport/3"
        ],
        initialPreviewConfig: [
            {caption: "transport-1.jpg", size: 329892, width: "120px", url: "{$url}", key: 1},
            {caption: "transport-2.jpg", size: 872378, width: "120px", url: "{$url}", key: 2},
            {caption: "transport-3.jpg", size: 632762, width: "120px", url: "{$url}", key: 3}
        ]
    });
    $("#file-4").fileinput({
        theme: 'fas',
        uploadExtraData: {kvId: '10'}
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
    /*
     $('#file-4').on('fileselectnone', function() {
     alert('Huh! You selected no files.');
     });
     $('#file-4').on('filebrowse', function() {
     alert('File browse clicked for #file-4');
     });
     */
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
        /*
         $("#test-upload").on('fileloaded', function(event, file, previewId, index) {
         alert('i = ' + index + ', id = ' + previewId + ', file = ' + file.name);
         });
         */
    });
</script>
</html>
