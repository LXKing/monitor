<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>车队管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/motorcade/motorcade.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.motorcade.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.motorcade.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.motorcade.delete", request);
    request.setAttribute("delete_auth", delete_auth);
%>
<div id="motorcadeFrame">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-xxx">车队列表</div>

        <div id="btnRefreshMotorcade" class="mon-button">
            <div class="icon i-16-refresh"></div>
            <span>刷新</span>
        </div>
        <c:if test="${create_auth || update_auth || delete_auth}">
            <div class="spliter"></div>
        </c:if>
        <c:if test="${create_auth}">
            <div id="btnCreateMotorcade" class="mon-button">
                <div class="icon i-16-add"></div>
                <span>添加</span>
            </div>
        </c:if>
        <c:if test="${update_auth}">
            <div id="btnEditMotorcade" class="mon-button">
                <div class="icon i-16-edit"></div>
                <span>修改</span>
            </div>
        </c:if>
        <c:if test="${delete_auth}">
            <div id="btnRemoveMotorcade" class="mon-button">
                <div class="icon i-16-remove"></div>
                <span>删除</span>
            </div>
        </c:if>
    </div>
    <div class="mon-clear"></div>
    <div id="gridMotorcade"></div>
</div>
</body>
</html>