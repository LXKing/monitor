<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/deviceData/deviceData.js"></script>
</head>
<body>
<div id="deviceDataLayout">
    <div position="left" title="操作面板" style="height: 100%; padding: 2px;">
        <input id="txtDeviceNumber" type="hidden"
               value="<%=request.getParameter("deviceNumber")%>"/> <input
            id="txtPlateNumber" type="hidden"
            value="<%=request.getParameter("plateNumber")%>"/>
        <div id="divDeviceDataControl">
            <!-- 			<div>
            车牌号码： <b id="txtPlateNumber">XXXXXXX</b>
            <div id="btnDeviceDataFindVehicle" class="mon-button">
                <div class="mon-icon-h-x16 i-16-search"></div>
                <span>查找</span>
            </div>
        </div>
        <hr /> -->
            <ul>
            </ul>
        </div>
    </div>
    <div position="center">
        <div id="deviceDataPages" class="liger-tab"></div>
    </div>
</div>
</body>
</html>