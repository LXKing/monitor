<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>车辆图标管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/marker/marker.js"></script>
</head>
<body>
<%
    boolean save_auth = AuthorizeCache.hasAuthorized("baseinfo.marker.save", request);
    request.setAttribute("save_auth", save_auth);

    boolean remove_auth = AuthorizeCache.hasAuthorized("baseinfo.marker.remove", request);
    request.setAttribute("remove_auth", remove_auth);
%>
<div class="mon-toolbar metro-gray">
    <div class="title mon-3x">车辆图标列表</div>
    <div id="btnRefreshMarker" class="mon-button">
        <div class="icon i-16-refresh"></div>
        <span>刷新</span>
    </div>
    <c:if test="${save_auth || remove_auth}">
        <div class="spliter"></div>
    </c:if>
    <c:if test="${save_auth}">
        <div id="btnCreateMarker" class="mon-button">
            <div class="icon i-16-add"></div>
            <span>添加</span>
        </div>
    </c:if>
    <c:if test="${remove_auth}">
        <div id="btnRemoveMarker" class="mon-button">
            <div class="icon i-16-remove"></div>
            <span>删除</span>
        </div>
    </c:if>
</div>
<div class="mon-clear"></div>
<div id="gridMarker"></div>
</body>
</html>