<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>创建兴趣点</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/poi/editor.js"></script>
</head>
<body>
<div id="poiEditor" style="padding: 5px;">
    <form:form id="createPoiForm" method="post" modelAttribute="poi">
        <table class="mon-layout-table">
            <tr>
                <td style="width: 400px;">
                    <div class="editor-label">
                        <form:label path="name">名称：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="name" required="true"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="type">类型：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="type"/>
                        <form:errors path="type" cssClass="error"/>
                    </div>
                </td>
                <td>
                    <div class="editor-label">
                        <form:label path="lat">纬度：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="lat" type="number"/>
                        <form:errors path="lat" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="lng">经度：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="lng" type="number"/>
                        <form:errors path="lng" cssClass="error"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="editor-label">
                        <form:label path="remark">备注：
                        </form:label>
                    </div>
                    <div class="editor-field width-6x">
                        <form:input path="remark"/>
                        <form:errors path="remark" cssClass="error"/>
                    </div>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>