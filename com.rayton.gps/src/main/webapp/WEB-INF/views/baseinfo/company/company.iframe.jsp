<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.rayton.gps.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>企业管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/company/company.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.company.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.company.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.company.delete", request);
    request.setAttribute("delete_auth", delete_auth);

    boolean authorize_auth = AuthorizeCache.hasAuthorized("baseinfo.company.authorize", request);
    request.setAttribute("authorize_auth", authorize_auth);
%>
<div id="comapnyFrame">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">企业列表</div>
        <div class="prompt width-3x">
            名称：<input id="txtCompanyFilter" type="text" placeholder="企业名称"/>
        </div>
        <div id="btnQueryCompany" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>

        <c:if test="${create_auth || update_auth || delete_auth}">
            <div class="spliter"></div>
        </c:if>
        <c:if test="${create_auth}">
            <div id="btnCreateCompany" class="mon-button">
                <div class="icon i-16-add"></div>
                <span>添加</span>
            </div>
        </c:if>
        <c:if test="${update_auth}">
            <div id="btnEditCompany" class="mon-button">
                <div class="icon i-16-edit"></div>
                <span>修改</span>
            </div>
        </c:if>
        <c:if test="${delete_auth}">
            <div id="btnRemoveCompany" class="mon-button">
                <div class="icon i-16-remove"></div>
                <span>删除</span>
            </div>
        </c:if>

        <c:if test="${authorize_auth}">
            <div class="spliter"></div>
            <div id="btnAuthorizeCompany" class="mon-button">
                <div class="icon i-16-authorize"></div>
                <span>授权</span>
            </div>
        </c:if>
    </div>
    <div class="mon-clear"></div>
    <div id="gridCompany"></div>
</div>
</body>
</html>