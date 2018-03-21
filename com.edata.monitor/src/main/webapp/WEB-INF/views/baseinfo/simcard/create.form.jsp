<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>创建sim卡</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/simcard/editor.js"></script>
</head>
<body>
<div id="simcardEditor" style="padding: 5px;">
    <form:form method="post" modelAttribute="simcard">
        <table class="mon-layout-table">
            <tr>
                <td style="width: 400px;">
                    <div class="editor-label">
                        <form:label path="phoneNumber">电话号码：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="phoneNumber" required="true"/>
                        <form:errors path="phoneNumber" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="speechType">语音类型：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="speechType"/>
                        <form:errors path="speechType" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="openSMS">开通短信否：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:checkbox path="openSMS"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="openDate">开通日期：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="openDate"/>
                        <form:errors path="openDate" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="flowset">流量套餐：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="flowset"/>
                        <form:errors path="flowset" cssClass="error"/>
                    </div>
                </td>
                <td>
                    <div class="editor-label">
                        <form:label path="simcardNumber">sim卡号：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="simcardNumber" required="true"/>
                        <form:errors path="simcardNumber" cssClass="error"/>
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
                        <form:label path="carrierOperator">运营商：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="carrierOperator"/>
                        <form:errors path="carrierOperator" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="prepayment">预交费(元)：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="prepayment" type="number"/>
                        <form:errors path="prepayment" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="expireDate">预交费到期日期：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="expireDate"/>
                        <form:errors path="expireDate" cssClass="error"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="editor-label">
                        <form:label path="remark">备注：
                        </form:label>
                    </div>
                    <div class="editor-field width-4x">
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