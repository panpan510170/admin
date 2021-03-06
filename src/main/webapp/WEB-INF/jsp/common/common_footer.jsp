<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/20
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Mainly scripts -->
<script src="/statics/js/jquery-2.1.1.js"></script>
<script src="/statics/js/bootstrap.min.js"></script>
<script src="/statics/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/statics/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- tree -->
<script src="/statics/js/plugins/treeview/bootstrap-treeview.js"></script>
<!-- Flot -->
<script src="/statics/js/plugins/flot/jquery.flot.js"></script>
<script src="/statics/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="/statics/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="/statics/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="/statics/js/plugins/flot/jquery.flot.pie.js"></script>

<!-- Peity -->
<script src="/statics/js/plugins/peity/jquery.peity.min.js"></script>
<script src="/statics/js/demo/peity-demo.js"></script>

<!-- Custom and plugin javascript -->
<script src="/statics/js/inspinia.js"></script>
<script src="/statics/js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI -->
<script src="/statics/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- GITTER -->
<script src="/statics/js/plugins/gritter/jquery.gritter.min.js"></script>

<!-- Sparkline -->
<script src="/statics/js/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Sparkline demo data  -->
<script src="/statics/js/demo/sparkline-demo.js"></script>

<!-- ChartJS-->
<script src="/statics/js/plugins/chartJs/Chart.min.js"></script>

<!-- Toastr -->
<script src="/statics/js/plugins/toastr/toastr.min.js"></script>

<!-- 弹窗 -->
<script src="/statics/js/sweetalert.min.js"></script>

<script src="/statics/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/statics/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/statics/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="/statics/js/my.js"></script>

<!-- Jvectormap -->
<script src="/statics/js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
<script src="/statics/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- iCheck -->
<script src="/statics/js/plugins/iCheck/icheck.min.js"></script>

<!-- 富文本编辑器 -->
<script src="/statics/js/plugins/summernote/summernote.min.js"></script>
<script type="text/javascript" src='/statics/js/wangEditor-video.min.js'></script>
<script>
    //转换日期格式(时间戳转换为datetime格式)
    function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }

    $(document).ready(function() {
        setTimeout(function() {
            toastr.options = {
                closeButton: true,
                progressBar: true,
                showMethod: 'slideDown',
                timeOut: 4000
            };
            /*toastr.success('Responsive Admin Theme', 'Welcome to INSPINIA');*/

        }, 1300);


        var data1 = [
            [0,4],[1,8],[2,5],[3,10],[4,4],[5,16],[6,5],[7,11],[8,6],[9,11],[10,30],[11,10],[12,13],[13,4],[14,3],[15,3],[16,6]
        ];
        var data2 = [
            [0,1],[1,0],[2,2],[3,0],[4,1],[5,3],[6,1],[7,5],[8,2],[9,3],[10,2],[11,1],[12,0],[13,2],[14,8],[15,0],[16,0]
        ];
        $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
                data1, data2
            ],
            {
                series: {
                    lines: {
                        show: false,
                        fill: true
                    },
                    splines: {
                        show: true,
                        tension: 0.4,
                        lineWidth: 1,
                        fill: 0.4
                    },
                    points: {
                        radius: 0,
                        show: true
                    },
                    shadowSize: 2
                },
                grid: {
                    hoverable: true,
                    clickable: true,
                    tickColor: "#d5d5d5",
                    borderWidth: 1,
                    color: '#d5d5d5'
                },
                colors: ["#1ab394", "#1C84C6"],
                xaxis:{
                },
                yaxis: {
                    ticks: 4
                },
                tooltip: false
            }
        );

        var doughnutData = [
            {
                value: 300,
                color: "#a3e1d4",
                highlight: "#1ab394",
                label: "App"
            },
            {
                value: 50,
                color: "#dedede",
                highlight: "#1ab394",
                label: "Software"
            },
            {
                value: 100,
                color: "#A4CEE8",
                highlight: "#1ab394",
                label: "Laptop"
            }
        ];

        var doughnutOptions = {
            segmentShowStroke: true,
            segmentStrokeColor: "#fff",
            segmentStrokeWidth: 2,
            percentageInnerCutout: 45, // This is 0 for Pie charts
            animationSteps: 100,
            animationEasing: "easeOutBounce",
            animateRotate: true,
            animateScale: false
        };

        //var ctx = document.getElementById("doughnutChart").getContext("2d");
       // var DoughnutChart = new Chart(ctx).Doughnut(doughnutData, doughnutOptions);

        var polarData = [
            {
                value: 300,
                color: "#a3e1d4",
                highlight: "#1ab394",
                label: "App"
            },
            {
                value: 140,
                color: "#dedede",
                highlight: "#1ab394",
                label: "Software"
            },
            {
                value: 200,
                color: "#A4CEE8",
                highlight: "#1ab394",
                label: "Laptop"
            }
        ];

        var polarOptions = {
            scaleShowLabelBackdrop: true,
            scaleBackdropColor: "rgba(255,255,255,0.75)",
            scaleBeginAtZero: true,
            scaleBackdropPaddingY: 1,
            scaleBackdropPaddingX: 1,
            scaleShowLine: true,
            segmentShowStroke: true,
            segmentStrokeColor: "#fff",
            segmentStrokeWidth: 2,
            animationSteps: 100,
            animationEasing: "easeOutBounce",
            animateRotate: true,
            animateScale: false
        };
        //var ctx = document.getElementById("polarChart").getContext("2d");
        //var Polarchart = new Chart(ctx).PolarArea(polarData, polarOptions);

    });
</script>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-4625583-2', 'webapplayers.com');
    ga('send', 'pageview');

</script>
</body>
</html>
