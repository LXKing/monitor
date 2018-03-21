<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <title>地图设置</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/mapOption/mapOption.js"></script>
</head>
<body>
<div class="mon-toolbar metro-gray">
    <div class="title mon-3x">地图设置</div>
    <div id="btnReloadMapOtions" class="mon-button">
        <div class="icon i-16-refresh"></div>
        <span>刷新</span>
    </div>
    <div class="spliter"></div>
    <div id="btnSaveMapOtions" class="mon-button">
        <div class="icon i-16-save"></div>
        <span>保存</span>
    </div>
</div>
<div id="divAllMap"
     style="width: 100%; height: 100px; overflow: hidden; zoom: 1; position: relative;">
    <div id="optionMap"
         style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
</div>
</body>
</html>