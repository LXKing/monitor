<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>驾驶员管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/driver/driver.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.driver.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.driver.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.driver.delete", request);
    request.setAttribute("delete_auth", delete_auth);

    boolean addVehicles_auth = AuthorizeCache.hasAuthorized("baseinfo.driver.addVehicles", request);
    request.setAttribute("addVehicles_auth", addVehicles_auth);

    boolean removeVehicle_auth = AuthorizeCache.hasAuthorized("baseinfo.driver.removeVehicle", request);
    request.setAttribute("removeVehicle_auth", removeVehicle_auth);
%>
<input id="addVehicles_auth" type="hidden" value="${addVehicles_auth}">
<input id="removeVehicle_auth" type="hidden"
       value="${removeVehicle_auth}">
<div id="driverFrame">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">驾驶员列表</div>
        <div class="prompt width-4x">
            关键字：<input id="txtDriverFilter" type="text" placeholder="姓名、电话、驾驶证号"/>
        </div>
        <div id="btnQueryDriver" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>
        <c:if test="${create_auth || update_auth || delete_auth}">
            <div class="spliter"></div>
        </c:if>
        <c:if test="${create_auth}">
            <div id="btnCreateDriver" class="mon-button">
                <div class="icon i-16-add"></div>
                <span>添加</span>
            </div>
        </c:if>
        <c:if test="${update_auth}">
            <div id="btnEditDriver" class="mon-button">
                <div class="icon i-16-edit"></div>
                <span>修改</span>
            </div>
        </c:if>
        <c:if test="${delete_auth}">
            <div id="btnRemoveDriver" class="mon-button">
                <div class="icon i-16-remove"></div>
                <span>删除</span>
            </div>
        </c:if>
    </div>
    <div class="mon-clear"></div>
    <div id="gridDriver"></div>
    <div class="mon-clear"></div>
    <div id="dialogDriverSelectVehicle" class="display-none">
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
        <div id="gridVehicleInDriver55c6cc891d19b64134b972ed"></div>
    </div>
</div>
</body>
</html>