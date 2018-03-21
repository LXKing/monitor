<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edata.monitor.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>车辆保养管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/maintain/maintain.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.maintain.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.maintain.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.maintain.delete", request);
    request.setAttribute("delete_auth", delete_auth);
%>
<div id="maintainFrame">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">车辆保养记录</div>
        <div class="prompt width-3x">
            车辆：<input id="txtMaintainFilter" type="text" placeholder="车牌号"/>
        </div>
        <div class="prompt editor-field">
            开始时间：<input id="txtStartDate" type="text"/>
        </div>
        <div class="prompt editor-field">
            结束时间：<input id="txtEndDate" type="text"/>
        </div>
        <div id="btnQueryMaintain" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>
        <c:if test="${create_auth || update_auth || delete_auth}">
            <div class="spliter"></div>
        </c:if>
        <c:if test="${create_auth}">
            <div id="btnCreateMaintain" class="mon-button">
                <div class="icon i-16-add"></div>
                <span>添加</span>
            </div>
        </c:if>
        <c:if test="${update_auth}">
            <div id="btnEditMaintain" class="mon-button">
                <div class="icon i-16-edit"></div>
                <span>修改</span>
            </div>
        </c:if>
        <c:if test="${delete_auth}">
            <div id="btnRemoveMaintain" class="mon-button">
                <div class="icon i-16-remove"></div>
                <span>删除</span>
            </div>
        </c:if>
    </div>
    <div class="mon-clear"></div>
    <div id="gridMaintain"></div>
</div>
</body>
</html>