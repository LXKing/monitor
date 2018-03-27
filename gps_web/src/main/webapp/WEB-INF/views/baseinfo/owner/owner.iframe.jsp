<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>车主管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/owner/owner.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.owner.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.owner.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.owner.delete", request);
    request.setAttribute("delete_auth", delete_auth);

    boolean addVehicles_auth = AuthorizeCache.hasAuthorized("baseinfo.owner.addVehicles", request);
    request.setAttribute("addVehicles_auth", addVehicles_auth);

    boolean removeVehicle_auth = AuthorizeCache.hasAuthorized("baseinfo.owner.removeVehicle", request);
    request.setAttribute("removeVehicle_auth", removeVehicle_auth);
%>
<input id="addVehicles_auth" type="hidden" value="${addVehicles_auth}">
<input id="removeVehicle_auth" type="hidden"
       value="${removeVehicle_auth}">
<div id="ownerFrame">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">车主列表</div>
        <div class="prompt width-3x">
            关键字：<input id="txtOwnerFilter" type="text" placeholder="姓名、电话"/>
        </div>
        <div id="btnQueryOwner" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>
        <c:if test="${create_auth || update_auth || delete_auth}">
            <div class="spliter"></div>
        </c:if>
        <c:if test="${create_auth}">
            <div id="btnCreateOwner" class="mon-button">
                <div class="icon i-16-add"></div>
                <span>添加</span>
            </div>
        </c:if>
        <c:if test="${update_auth}">
            <div id="btnEditOwner" class="mon-button">
                <div class="icon i-16-edit"></div>
                <span>修改</span>
            </div>
        </c:if>
        <c:if test="${delete_auth}">
            <div id="btnRemoveOwner" class="mon-button">
                <div class="icon i-16-remove"></div>
                <span>删除</span>
            </div>
        </c:if>
    </div>
    <div class="mon-clear"></div>
    <div id="gridOwner"></div>
    <div class="mon-clear"></div>
    <div id="dialogOwnerSelectVehicle" class="display-none">
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
        <div id="gridVehicleInOwner55c6cc891d19b64134b972ec"></div>
    </div>
</div>
</body>
</html>