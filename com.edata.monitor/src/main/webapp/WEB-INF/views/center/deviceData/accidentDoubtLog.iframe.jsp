<%@page import="com.edata.monitor.domain.security.Identify"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>事故疑点日志</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/exporting.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/center/deviceData/accidentDoubtLog.js"></script>
</head>
<body>
	<table style="width: 100%; height: 400px;">
		<tr>
			<td height="400px" width="50%"><div id="chart"
					style="height: 400px; width: 98%;"></div></td>
			<td width="50%"><div id="accidentDoubtLogMap"
					style="height: 400px; width: 98%;"></div></td>
		</tr>
	</table>
	<div class="mon-toolbar metro-gray">
		<div class="title mon-3x">事故疑点日志列表</div>
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
	<div id="gridAccidentDoubtLog"></div>
</body>
</html>