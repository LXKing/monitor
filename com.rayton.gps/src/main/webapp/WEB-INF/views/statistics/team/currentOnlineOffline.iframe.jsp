<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>当前上线率统计</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/exporting.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/statistics/team/currentOnlineOffline.js"></script>
</head>
<body>
<div id="chart" style="height: 300px; width: 98%;"></div>
<div class="mon-toolbar metro-gray">
    <div class="title mon-3x">当前上线率统计</div>
    <div class="prompt width-3x">
        车队：<input id="txtMotorcade" type="text"/>
    </div>
    <div id="btnQuery" class="mon-button">
        <div class="icon i-16-search"></div>
        <span>查询</span>
    </div>
    <div class="spliter"></div>
    <div id="btnExportExcel" class="mon-button">
        <div class="icon i-16-excel"></div>
        <span>导出Excel</span>
    </div>
    <div id="btnExportPdf" class="mon-button">
        <div class="icon i-16-pdf"></div>
        <span>导出Pdf</span>
    </div>
</div>
<div id="gridCurrentVehicleOnline"></div>
</body>
</html>