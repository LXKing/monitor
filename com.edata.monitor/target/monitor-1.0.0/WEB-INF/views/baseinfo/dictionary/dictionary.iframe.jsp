<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.edata.monitor.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>数据字典管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/dictionary/dictionary.js"></script>
</head>
<body>
<%
    boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.dictionary.create", request);
    request.setAttribute("create_auth", create_auth);

    boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.dictionary.update", request);
    request.setAttribute("update_auth", update_auth);

    boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.dictionary.delete", request);
    request.setAttribute("delete_auth", delete_auth);
%>
<div class="mon-toolbar metro-gray">
    <div class="title mon-3x">数据字典</div>
    <div class="prompt">
        类型：
        <form:select path="txtDictionaryKind" id="txtDictionaryKind"
                     items="${dictionaryKinds}" itemLabel="name" itemValue="index">
        </form:select>
    </div>
    <div id="btnQueryDictionary" class="mon-button">
        <div class="icon i-16-search"></div>
        <span>查询</span>
    </div>
    <c:if test="${create_auth || update_auth || delete_auth}">
        <div class="spliter"></div>
    </c:if>
    <c:if test="${create_auth}">
        <div id="btnCreateRootDictionary" class="mon-button">
            <div class="icon i-16-addroot"></div>
            <span>添加顶级项</span>
        </div>
        <div id="btnCreateSameDictionary" class="mon-button">
            <div class="icon i-16-addsame"></div>
            <span>添加同级项</span>
        </div>
        <div id="btnCreateSubDictionary" class="mon-button">
            <div class="icon i-16-addsub"></div>
            <span>添加子级项</span>
        </div>
    </c:if>
    <c:if test="${update_auth}">
        <div id="btnEditDictionary" class="mon-button">
            <div class="icon i-16-edit"></div>
            <span>修改</span>
        </div>
    </c:if>
    <c:if test="${delete_auth}">
        <div id="btnRemoveDictionary" class="mon-button">
            <div class="icon i-16-remove"></div>
            <span>删除</span>
        </div>
    </c:if>
</div>
<div class="mon-clear"></div>
<div id="gridDictionary"></div>
</body>
</html>