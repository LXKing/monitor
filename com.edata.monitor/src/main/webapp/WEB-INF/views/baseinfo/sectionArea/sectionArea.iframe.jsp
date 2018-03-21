<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edata.monitor.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <title>路段管理</title>
    <style type="text/css">
        #allmap {
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
            src="<%=request.getContextPath()%>/resources/js/baseinfo/sectionArea/sectionArea.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.sectionArea.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.sectionArea.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.sectionArea.delete", request);
    request.setAttribute("delete_auth", delete_auth);
%>
<input id="create_auth" type="hidden" value="${create_auth}">
<div id="sectionAreaFrame">
    <div id="divSectionAreaAllMap"
         style="overflow: hidden; zoom: 1; position: relative;">
        <div id="sectionAreaMap"
             style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
    </div>
    <div style="height: 300px;">
        <div class="mon-toolbar metro-gray">
            <div class="title mon-3x">路段列表</div>
            <div class="prompt width-3x">
                名称：<input id="txtSectionAreaFilter" type="text" placeholder="路段名称"/>
            </div>
            <div id="btnQuerySectionArea" class="mon-button">
                <div class="icon i-16-search"></div>
                <span>查询</span>
            </div>
            <c:if test="${update_auth || delete_auth}">
                <div class="spliter"></div>
            </c:if>
            <c:if test="${update_auth}">
                <div id="btnEditSectionArea" class="mon-button">
                    <div class="icon i-16-edit"></div>
                    <span>编辑资料</span>
                </div>
            </c:if>
            <c:if test="${delete_auth}">
                <div id="btnRemoveSectionArea" class="mon-button">
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
        <div id="gridSectionArea"></div>
    </div>
</div>
</body>
</html>