<%@page import="com.edata.monitor.domain.security.Identify"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>速度记录</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/exporting.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/center/deviceData/speedLog.js"></script>
</head>
<body>
	<div id="chart" style="height: 400px; width: 98%;"></div>
	<div id="divBottom">
		<div class="mon-toolbar metro-gray">
			<div class="title mon-3x">速度记录列表</div>
			<div class="prompt editor-field">
				<input id="txtStartDate" type="text" />
			</div>
			<div class="prompt editor-field">
				<input id="txtEndDate" type="text" />
			</div>
			<div id="btnQuery" class="mon-button">
				<div class="icon i-16-search"></div>
				<span>查询</span>
			</div>
		</div>
		<div class="mon-clear"></div>
		<div id="gridSpeedLog"></div>
	</div>
</body>
</html>