<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>用户管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/user/editor.js"></script>
</head>
<body>
<div id="userEditor" style="padding: 5px;">
    <form:form method="post" modelAttribute="user">
        <form:hidden path="id"/>
        <form:hidden path="companyId"/>
        <form:hidden path="kind"/>
        <form:hidden path="editTime"/>
        <table class="mon-layout-table">
            <tr>
                <td style="width: 400px;">
                    <div class="editor-label">
                        <form:label path="account">帐号：
                        </form:label>
                    </div>
                    <div class="editor-field width-3x">
                        <form:input path="account"/>
                        <form:errors path="account" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="name">姓名：
                        </form:label>
                    </div>
                    <div class="editor-field width-3x">
                        <form:input path="name"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="email">邮箱：
                        </form:label>
                    </div>
                    <div class="editor-field width-4x">
                        <form:input path="email" type="email"/>
                        <form:errors path="email" cssClass="display-error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="phone">电话：
                        </form:label>
                    </div>
                    <div class="editor-field width-3x">
                        <form:input path="phone"/>
                        <form:errors path="phone" cssClass="display-error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="contact">联系人：
                        </form:label>
                    </div>
                    <div class="editor-field width-3x">
                        <form:input path="contact"/>
                    </div>
                </td>
                <td>
                    <div class="editor-label">
                        <form:label path="enable">启用否：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:checkbox path="enable"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="serviceStartDate">服务开始时间：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="serviceStartDate"/>
                        <form:errors path="serviceStartDate" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="serviceEndDate">服务结束时间：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="serviceEndDate"/>
                        <form:errors path="serviceEndDate" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="remark">说明：
                        </form:label>
                    </div>
                    <div class="editor-field width-5x">
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