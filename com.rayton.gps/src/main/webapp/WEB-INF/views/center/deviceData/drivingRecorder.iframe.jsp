<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>行驶记录仪</title>
    <style type="text/css">
        .t {
            margin-left: 30px;
            margin-top: 30px;
        }

        .t tr {
            height: 24px;
        }
    </style>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/deviceData/drivingRecorder.js"></script>
</head>
<body>
<table class="t">
    <tr>
        <td width="300px"><b>设备号:</b><span id="number"></span></td>
        <td><b>车牌号:</b><span id="plateNumber"></span></td>
    </tr>
    <tr>
        <td><b>驾驶证号:</b><span id="license"></span></td>
        <td><b>车牌分类:</b><span id="plateType"></span></td>
    </tr>
    <tr>
        <td><b>车辆识别号:</b><span id="vehicleIdCode"></span></td>
        <td><b>ccc认证号:</b><span id="cccCode"></span></td>
    </tr>
    <tr>
        <td><b>初始里程:</b><span id="initialMileage"></span></td>
        <td><b>累计里程:</b><span id="totalMileage"></span></td>
    </tr>
    <tr>
        <td><b>执行标准:</b><span id="revision"></span></td>
        <td><b>脉冲系数:</b><span id="pulseFactor"></span></td>
    </tr>
    <tr>
        <td><b>型号:</b><span id="model"></span></td>
        <td><b>序列号:</b><span id="serialNumber"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>生产日期:</b><span id="productionDate"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D0:</b><span id="d0"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D1:</b><span id="d1"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D2:</b><span id="d2"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D3:</b><span id="d3"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D4:</b><span id="d4"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D5:</b><span id="d5"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D6:</b><span id="d6"></span></td>
    </tr>
    <tr>
        <td colspan="2"><b>D7:</b><span id="d7"></span></td>
    </tr>
</table>
</body>
</html>