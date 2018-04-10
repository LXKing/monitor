<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>修改企业资料</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/company/editor.js"></script>
</head>
<body>
<form:form method="post" modelAttribute="editor">
    <form:hidden path="id"/>
    <form:hidden path="pid"/>
    <form:hidden path="companyId"/>
    <form:hidden path="kind"/>
    <form:hidden path="userEditTime"/>
    <form:hidden path="companyEditTime"/>
    <div id="companyPages">
        <fieldset>
            <legend>企业信息</legend>
            <table class="mon-layout-table">
                <tr>
                    <td style="width: 400px;">
                        <div class="editor-label">
                            <form:label path="shortName">简称：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="shortName" required="true"/>
                            <form:errors path="shortName" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="fullName">全称：
                            </form:label>
                        </div>
                        <div class="editor-field width-6x">
                            <form:input path="fullName"/>
                            <form:errors path="fullName" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="organCode">组织机构编号：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="organCode"/>
                            <form:errors path="organCode" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="corporation">法人代表：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="corporation"/>
                            <form:errors path="corporation" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="parentVisible">上级可见否：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:checkbox path="parentVisible"/>
                            <form:errors path="parentVisible" cssClass="error"/>
                        </div>
                    </td>
                    <td>
                        <div class="editor-label">
                            <form:label path="registDate">注册日期：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="registDate"/>
                            <form:errors path="registDate" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="ondutyPhone">24小时值班电话：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="ondutyPhone"/>
                            <form:errors path="ondutyPhone" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="officeAddress">办公地址：
                            </form:label>
                        </div>
                        <div class="editor-field width-7x">
                            <form:input path="officeAddress"/>
                            <form:errors path="officeAddress" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="registAddress">注册地址：
                            </form:label>
                        </div>
                        <div class="editor-field width-7x">
                            <form:input path="registAddress"/>
                            <form:errors path="registAddress" cssClass="error"/>
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
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>管理员信息</legend>
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
    </div>
</form:form>
</body>
</html>