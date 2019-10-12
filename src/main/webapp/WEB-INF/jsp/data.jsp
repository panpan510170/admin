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
        <div class="row">
            <div class="col-lg-3">
                <div class="widget lazur-bg p-lg text-center">
                    <div class="m-b-md">
                        <i class="fa fa-user fa-4x"></i>
                        <h1 class="m-xs">520</h1>
                        <h3 class="font-bold no-margins">
                            今日登陆
                        </h3>
                        <small>人数</small>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="widget yellow-bg p-lg text-center">
                    <div class="m-b-md">
                        <i class="fa fa-user fa-4x"></i>
                        <h1 class="m-xs">520</h1>
                        <h3 class="font-bold no-margins">今日注册
                        </h3>
                        <small>人数</small>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="widget red-bg p-lg text-center">
                    <div class="m-b-md">
                        <i class="fa fa-bell fa-4x"></i>
                        <h1 class="m-xs">0</h1>
                        <h3 class="font-bold no-margins">
                            今日异常
                        </h3>
                        <small>次数</small>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <a href="/actuator/health">
                <div class="col-lg-4">
                    <div class="widget style1 navy-bg">
                        <div class="row vertical-align">
                            <div class="col-xs-3">
                                <i class="fa fa-rss fa-3x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <h2 class="font-bold"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">查看项目运行状态</font></font></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href="/actuator/heapdump">
                <div class="col-lg-4">
                    <div class="widget style1 navy-bg">
                        <div class="row vertical-align">
                            <div class="col-xs-3">
                                <i class="fa fa-rss fa-3x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <h2 class="font-bold"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">下载heapdump文件</font></font></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href="/actuator/httptrace">
                <div class="col-lg-4">
                    <div class="widget style1 navy-bg">
                        <div class="row vertical-align">
                            <div class="col-xs-3">
                                <i class="fa fa-rss fa-3x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <h2 class="font-bold"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">查看最近100个HTTP请求</font></font></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
        <div class="row">
            <a href="/actuator/env">
                <div class="col-lg-4">
                    <div class="widget style1 navy-bg">
                        <div class="row vertical-align">
                            <div class="col-xs-3">
                                <i class="fa fa-rss fa-3x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <h2 class="font-bold"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">查看项目运行环境及配置</font></font></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
        <div class="row m-t-lg">
            <div class="col-lg-12">
                <div class="ibox-content">
                    <h2>世界地图</h2>
                    <%--<small>This is simple example of map</small>--%>
                    <div id="world-map" style="height: 500px;"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<jsp:include page="/WEB-INF/jsp/common/common_footer.jsp"></jsp:include>
<script>
    $(document).ready(function(){
        var mapData = {
            "US": 498,
            "SA": 200,
            "CA": 1300,
            "DE": 220,
            "FR": 540,
            "CN": 120,
            "AU": 760,
            "BR": 550,
            "IN": 200,
            "GB": 120,
            "RU": 2000
        };

        $('#world-map').vectorMap({
            map: 'world_mill_en',
            backgroundColor: "transparent",
            regionStyle: {
                initial: {
                    fill: '#e4e4e4',
                    "fill-opacity": 1,
                    stroke: 'none',
                    "stroke-width": 0,
                    "stroke-opacity": 0
                }
            },
            series: {
                regions: [{
                    values: mapData,
                    scale: ["#1ab394", "#22d6b1"],
                    normalizeFunction: 'polynomial'
                }]
            }
        });
    });
</script>
</html>
