<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>路书</title>
    <style type="text/css">
        body, html {
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑";
        }

        #map_canvas {
            width: 100%;
            height: 500px;
        }

        #result {
            width: 100%;
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=Y2TqMdQvIRuGGW83xP2kd8LR7Y5SAplh&callback"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
</head>
<body>
<!--  <button id="run">开始</button>
    <button id="stop">停止</button>
    <button id="pause">暂停</button>
    <button id="hide">隐藏信息窗口</button>
    <button id="show">展示信息窗口</button> -->
<div id="map_canvas"></div>
<div id="result"></div>

<script>

    var map = new BMap.Map('map_canvas');
    map.enableScrollWheelZoom();
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);
    var lushu;
    // 实例化一个驾车导航用来生成路线
    var drv = new BMap.DrivingRoute('北京', {
        onSearchComplete: function (res) {
            if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
                var plan = res.getPlan(0);
                var arrPois = [];
                var pts = [
                    new BMap.Point(126.618398, 45.721217),
                    new BMap.Point(126.62081, 45.713008),
                    new BMap.Point(126.654694, 45.732086),
                    new BMap.Point(126.675481, 45.738471)
                ];
                for (var j = 0; j < plan.getNumRoutes(); j++) {
                    var route = plan.getRoute(j);
                    arrPois = arrPois.concat(route.getPath());
                }
                map.addOverlay(new BMap.Polyline(pts, {strokeColor: '#111'}));
                map.setViewport(pts);

                lushu = new BMapLib.LuShu(map, pts, {
                    defaultContent: "",//"从天安门到百度大厦"
                    autoView: true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                    icon: new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52, 26), {anchor: new BMap.Size(27, 13)}),
                    speed: 4500,
                    enableRotation: true,//是否设置marker随着道路的走向进行旋转
                });
            }
        }
    });
    drv.search('天安门', '百度大厦');
    //绑定事件
    $("run").onclick = function () {
        lushu.start();
    }
    $("stop").onclick = function () {
        lushu.stop();
    }
    $("pause").onclick = function () {
        lushu.pause();
    }
    $("hide").onclick = function () {
        lushu.hideInfoWindow();
    }
    $("show").onclick = function () {
        lushu.showInfoWindow();
    }

    function $(element) {
        return document.getElementById(element);
    }
</script>
</body>
</html> 