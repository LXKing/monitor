<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>创建驾驶员资料</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/driver/editor.js"></script>
</head>
<body>
<div id="driverEditor" style="padding: 5px;">
    <form:form method="post" modelAttribute="driver">
        <table class="mon-layout-table">
            <tr>
                <td style="width: 400px;">
                    <div class="editor-label">
                        <form:label path="name">姓名：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="name" required="true"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="sex">性别：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <label style="margin-right: 20px;"><form:radiobutton
                                path="sex" value="男"/>男</label><label><form:radiobutton
                            path="sex" value="女"/> 女</label>
                        <form:errors path="sex" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="phone">电话：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="phone"/>
                        <form:errors path="phone" cssClass="error"/>
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
                        <form:label path="idNumber">证件号：
                        </form:label>
                    </div>
                    <div class="editor-field width-4x">
                        <form:input path="idNumber"/>
                        <form:errors path="idNumber" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="drivingLicenseNumber">驾驶证号：
                        </form:label>
                    </div>
                    <div class="editor-field width-4x">
                        <form:input path="drivingLicenseNumber" required="true"/>
                        <form:errors path="drivingLicenseNumber" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="drivingLicenseExpiryDate">驾驶证有效日期：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="drivingLicenseExpiryDate"/>
                        <form:errors path="drivingLicenseExpiryDate" cssClass="error"/>
                    </div>

                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>