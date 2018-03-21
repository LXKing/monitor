<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>创建终端</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/device/editor.js"></script>
</head>
<body>
<div id="deviceEditor" style="padding: 5px;">
    <form:form method="post" modelAttribute="editor">
        <form:hidden path="simcardId"/>
        <fieldset>
            <legend>终端信息</legend>
            <table class="mon-layout-table">
                <tr>
                    <td style="width: 400px;">
                        <div class="editor-label">
                            <form:label path="deviceNumber">终端号：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="deviceNumber" required="true"/>
                            <form:errors path="deviceNumber" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="phoneNumber">电话号码：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="phoneNumber"/>
                            <form:errors path="phoneNumber" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="model">型号：
                            </form:label>
                        </div>
                        <div class="editor-field width-3x">
                            <form:input path="model"/>
                            <form:errors path="model" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="factoryNumber">出厂号：
                            </form:label>
                        </div>
                        <div class="editor-field width-3x">
                            <form:input path="factoryNumber"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="factoryName">厂家：
                            </form:label>
                        </div>
                        <div class="editor-field width-4x">
                            <form:input path="factoryName"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="sensors">传感器：
                            </form:label>
                        </div>
                        <div class="editor-field width-5x">
                            <form:input path="sensors"/>
                        </div>
                    </td>
                    <td>
                        <div class="editor-label">
                            <form:label path="cameras">摄像头路数：
                            </form:label>
                        </div>
                        <div class="editor-field width-2x">
                            <form:input path="cameras" type="number"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="hasMicrophone">有无麦克风：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:checkbox path="hasMicrophone"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="hasNavigation">有无导航屏：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:checkbox path="hasNavigation"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="warranty">保修期：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="warranty"/>
                            <form:errors path="warranty" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="purchaseDate">购买日期：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="purchaseDate"/>
                            <form:errors path="purchaseDate" cssClass="error"/>
                        </div>
                        <div class="editor-label">
                            <form:label path="installDate">安装日期：
                            </form:label>
                        </div>
                        <div class="editor-field">
                            <form:input path="installDate"/>
                            <form:errors path="installDate" cssClass="error"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="editor-label">
                            <form:label path="remark">备注：
                            </form:label>
                        </div>
                        <div class="editor-field width-5x">
                            <form:input path="remark"/>
                            <form:errors path="remark" cssClass="error"/>
                        </div>
                    </td>
                </tr>
            </table>
        </fieldset>
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
                            <form:input path="email" type="email"/>
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