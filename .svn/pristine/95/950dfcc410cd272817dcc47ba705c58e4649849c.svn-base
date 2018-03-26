<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>创建车主资料</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/owner/editor.js"></script>
</head>
<body>
<div id="ownerEditor">
    <form:form method="post" modelAttribute="editor">
        <fieldset>
            <legend>车主信息</legend>
            <table class="mon-layout-table">
                <tr>
                    <td style="width: 350px;">
                        <div class="editor-label">
                            <form:label path="ownerName">姓名：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="ownerName" required="true"/>
                            <form:errors path="ownerName" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="remark">备注：
                            </form:label>
                        </div>
                        <div class="editor-field width-6x">
                            <form:input path="remark"/>
                            <form:errors path="remark" cssClass="error"/>
                        </div>
                    </td>
                    <td>
                        <div class="editor-label">
                            <form:label path="idType">证件类型：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="idType"/>
                            <form:errors path="idType" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="idNumber">证件编号：
                            </form:label>
                        </div>
                        <div class="editor-field width-4x">
                            <form:input path="idNumber"/>
                            <form:errors path="idNumber" cssClass="error"/>
                        </div>
                    </td>
                </tr>
            </table>
        </fieldset>
        <div style="height: 20px;"></div>
        <fieldset>
            <legend>用户信息</legend>
            <table class="mon-layout-table">
                <tr>
                    <td style="width: 350px;">
                        <div class="editor-label">
                            <form:label path="account">帐号：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="account" required="true"/>
                            <form:errors path="account" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="name">用户名：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="name" required="true"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="email">邮箱：
                            </form:label>
                        </div>
                        <div class="editor-field width-4x">
                            <form:input path="email"/>
                            <form:errors path="email" cssClass="display-error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="enable">启用否：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:checkbox path="enable"/>
                        </div>
                    </td>
                    <td>
                        <div class="editor-label">
                            <form:label path="phone">联系电话：
                            </form:label>
                        </div>
                        <div class="editor-field width-xxx">
                            <form:input path="phone"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="contact">联系人：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="contact"/>
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
                    </td>
                </tr>
            </table>
        </fieldset>
    </form:form>
</div>
</body>
</html>