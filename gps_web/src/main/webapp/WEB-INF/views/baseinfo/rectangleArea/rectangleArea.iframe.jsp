<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <title>矩形区域管理</title>
    <style type="text/css">
        #rectangleAreaAllmap {
            width: 100%;
            height: 400px;
            overflow: hidden;
        }
    </style>
    <link rel="stylesheet"
          href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/rectangleArea/rectangleArea.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.rectangleArea.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.rectangleArea.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.rectangleArea.delete", request);
    request.setAttribute("delete_auth", delete_auth);

    boolean addVehicles_auth = AuthorizeCache.hasAuthorized("baseinfo.rectangleArea.addVehicles", request);
    request.setAttribute("addVehicles_auth", addVehicles_auth);

    boolean removeVehicle_auth = AuthorizeCache.hasAuthorized("baseinfo.rectangleArea.removeVehicle", request);
    request.setAttribute("removeVehicle_auth", removeVehicle_auth);
%>
<input id="create_auth" type="hidden" value="${create_auth}">
<input id="addVehicles_auth" type="hidden" value="${addVehicles_auth}">
<input id="removeVehicle_auth" type="hidden"
       value="${removeVehicle_auth}">
<div id="rectangleAreaFrame">
    <div id="divRectangleAreaAllmap"
         style="overflow: hidden; zoom: 1; position: relative;">
        <div id="rectangleAreaMap"
             style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
    </div>
    <div style="height: 300px;">
        <div class="mon-toolbar metro-gray">
            <div class="title mon-3x">矩形区域列表</div>
            <div class="prompt width-3x">
                名称：<input id="txtRectangleAreaFilter" type="text"
                          placeholder="矩形区域名称"/>
            </div>
            <div id="btnQueryRectangleArea" class="mon-button">
                <div class="icon i-16-search"></div>
                <span>查询</span>
            </div>
            <c:if test="${update_auth || delete_auth}">
                <div class="spliter"></div>
            </c:if>
            <c:if test="${update_auth}">
                <div id="btnEditRectangleArea" class="mon-button">
                    <div class="icon i-16-edit"></div>
                    <span>编辑资料</span>
                </div>
            </c:if>
            <c:if test="${delete_auth}">
                <div id="btnRemoveRectangleArea" class="mon-button">
                    <div class="icon i-16-remove"></div>
                    <span>删除</span>
                </div>
            </c:if>
            <c:if test="${update_auth}">
                <div class="spliter"></div>
                <div id="btnEditGraph" class="mon-button">
                    <div class="icon i-16-authorize"></div>
                    <span>编辑图形</span>
                </div>
                <div id="btnApplyGraph" class="mon-button">
                    <div class="icon i-16-authorize"></div>
                    <span>应用图形</span>
                </div>
            </c:if>
        </div>
        <div class="mon-clear"></div>
        <div id="gridRectangleArea"></div>
    </div>
</div>
<div class="mon-clear"></div>
<div id="dialogRectangleAreaSelectVehicle" class="display-none">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">车辆列表</div>
        <div class="prompt width-3x">
            关键字：<input id="txtVehicleFilter" type="text"
                       placeholder="车牌号、设备号、SIM卡号"/>
        </div>
        <div id="btnQueryVehicle" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>
        <div class="spliter"></div>
        <div id="btnSelectVehicle" class="mon-button">
            <div class="icon i-16-checked"></div>
            <span>选择</span>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="gridVehicleInRectangleArea"></div>
</div>
</body>
</html>