<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>数据字典管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/dictionary/editor.js"></script>
</head>
<body>
<div id="dictionaryEditor" style="padding: 5px;">
    <form:form method="post" modelAttribute="dictionaryItem">
        <form:hidden path="id"/>
        <form:hidden path="pid"/>
        <form:hidden path="kind"/>
        <form:hidden path="editTime"/>
        <div class="editor-label">
            <form:label path="index">序号：
            </form:label>
        </div>
        <div class="editor-field">
            <form:input path="index"/>
            <form:errors path="index" cssClass="display-error"/>
        </div>
        <div class="editor-label">
            <form:label path="code">编码：
            </form:label>
        </div>
        <div class="editor-field">
            <form:input path="code"/>
            <form:errors path="code" cssClass="error"/>
        </div>
        <div class="editor-label">
            <form:label path="name">名称：
            </form:label>
        </div>
        <div class="editor-field width-4x">
            <form:input path="name" required="true"/>
            <form:errors path="name" cssClass="error"/>
        </div>
    </form:form>
</div>
</body>
</html>