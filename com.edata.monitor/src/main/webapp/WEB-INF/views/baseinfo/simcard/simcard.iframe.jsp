<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edata.monitor.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>sim卡管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/simcard/simcard.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.simcard.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.simcard.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.simcard.delete", request);
    request.setAttribute("delete_auth", delete_auth);
%>
<div id="simcardFrame">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">sim卡列表</div>

        <div class="prompt width-3x">
            关键字：<input id="txtSimcardFilter" type="text"
                       placeholder="电话号码、sim卡号、运营商"/>
        </div>
        <div id="btnQuerySimcard" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>
        <c:if test="${create_auth || update_auth || delete_auth}">
            <div class="spliter"></div>
        </c:if>
        <c:if test="${create_auth}">
            <div id="btnCreateSimcard" class="mon-button">
                <div class="icon i-16-add"></div>
                <span>添加</span>
            </div>
        </c:if>
        <c:if test="${update_auth}">
            <div id="btnEditSimcard" class="mon-button">
                <div class="icon i-16-edit"></div>
                <span>修改</span>
            </div>
        </c:if>
        <c:if test="${delete_auth}">
            <div id="btnRemoveSimcard" class="mon-button">
                <div class="icon i-16-remove"></div>
                <span>删除</span>
            </div>
        </c:if>
    </div>
    <div class="mon-clear"></div>
    <div id="gridSimcard"></div>
</div>
</body>
</html>