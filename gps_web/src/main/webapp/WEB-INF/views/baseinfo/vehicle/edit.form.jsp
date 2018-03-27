<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>车辆管理</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/vehicle/editor.js"></script>
</head>
<body>
<div id="vehicleEditor" style="padding: 5px;">
    <form:form method="post" modelAttribute="vehicle">
        <form:hidden path="id"/>
        <form:hidden path="companyId"/>
        <form:hidden path="editTime"/>
        <form:hidden path="motorcadeId"/>
        <form:hidden path="deviceId"/>
        <table class="mon-layout-table">
            <tr>
                <td style="width: 400px;">
                    <div class="editor-label">
                        <form:label path="plateNumber">车牌号码：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="plateNumber" required="true"/>
                        <form:errors path="plateNumber" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="plateColor">车牌颜色：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="plateColor" required="true"/>
                        <form:errors path="plateColor" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="vehicleColor">车辆颜色：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="vehicleColor"/>
                        <form:errors path="vehicleColor" cssClass="display-error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="adminArea">所属行政区域：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="adminArea"/>
                        <form:errors path="adminArea" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="carryType">载运类型：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="carryType"/>
                        <form:errors path="carryType" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="vehicleType">车辆类型：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="vehicleType" readonly="true"/>
                        <form:errors path="vehicleType" cssClass="display-error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="vehicleVoltage">车辆电压：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="vehicleVoltage"/>
                        <form:errors path="vehicleVoltage" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="marker">车辆图标：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:select path="marker" items="${icons}" itemLabel="name"
                                     itemValue="name"
                                     onchange="veicleIncon.src='../resources/icons/'+this.value"></form:select>
                        <img id="veicleIncon" alt="车辆图标"
                             src="../resources/icons/${vehicle.marker}"
                             style="vertical-align: middle;">
                    </div>
                </td>
                <td style="width: 400px;">
                    <div class="editor-label">
                        <form:label path="deviceNumber">设备号：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="deviceNumber"/>
                        <form:errors path="deviceNumber" cssClass="display-error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="motorcade">车队：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="motorcade" required="true"/>
                        <form:errors path="motorcade" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="initialMileage">初始里程：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="initialMileage" type="number"/>
                        <form:errors path="initialMileage" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="initialMileage">百公里油耗：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="initialMileage" type="number"/>
                        <form:errors path="initialMileage" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="usefulLife">使用年限：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="usefulLife" type="number"/>
                        <form:errors path="usefulLife" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="installDate">安装日期：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="installDate"/>
                        <form:errors path="installDate" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="annualSurveyDate">年检日期：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:input path="annualSurveyDate"/>
                        <form:errors path="annualSurveyDate" cssClass="error"/>
                    </div>
                    <div class="editor-label">
                        <form:label path="rotate">旋转图标否：
                        </form:label>
                    </div>
                    <div class="editor-field">
                        <form:checkbox path="rotate"/>
                        <form:errors path="rotate" cssClass="error"/>
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