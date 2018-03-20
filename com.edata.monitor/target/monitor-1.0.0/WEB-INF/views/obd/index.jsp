<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>OBD状态</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/obd/obd.js"></script>
</head>
<body>
	<rightpad>
	<div position="right" title="操作面板">
		<div class="mon-toolbar metro-gray mon-nowrap">
			<div class="title">查询参数</div>
			<div class="mon-right">
				<div id="btnQuery" class="mon-button">
					<div class="icon i-16-search"></div>
					<span>查询</span>
				</div>
			</div>
		</div>
		<div class="mon-clear"></div>
		<div style="padding: 3px;">
			<div class="mon-find">
				设备：<input id="txtDeviceFilter" type="text" />
				<div id="btnSearchDevices" class="button">
					<span>查找</span>
				</div>
			</div>
			<div class="display-label">
				设备号：<span id="txtDeviceNumber"><%=request.getParameter("number") == null ? "" : request
					.getParameter("number")%></span>
			</div>
			<div class="display-label">
				设备名：<span id="txtDeviceName"></span>
			</div>

			<div class="mon-nowrap">
				<div style="padding: 3px;">
					<input id="txtStartDate" type="text" />
				</div>
				<div style="padding: 3px;">
					<input id="txtEndDate" type="text" />
				</div>
			</div>
		</div>
		<div class="mon-toolbar metro-gray mon-nowrap">
			<div class="title">设备状态</div>
		</div>
		<div class="mon-clear"></div>
		<div id="divStatus" style="overflow: auto; padding: 3px;">
			<div id="txtDebugging" class="mon-total-title display-none">数据调试中......</div>
			<div id="txtUpgrading" class="mon-total-title display-none">
				版本升级中......
				<div id="txtPerver"></div>
				<div id="txtUpgradeStart"></div>
				<div id="txtCurver"></div>
				<div id="txtUpgradeEnd"></div>
			</div>
			<div id="txtMatching" class="mon-total-title display-none">
				全车匹配中......
				<div id="txtMatchTime"></div>
				<div id="txtMatchResult"></div>
			</div>
			<div id="txtSleeping" class="mon-total-title display-none">设备休眠中......</div>
			<div id="txtRepairing" class="mon-total-title display-none">车辆维修中......</div>
		</div>
	</div>
	</rightpad>
	<div class="mon-toolbar metro-gray">
		<div class="title">OBD状态</div>
	</div>
	<div id="chartContainer"></div>
	<div id="gridFlows"></div>
	<div id="dialogSearchDevices" class="display-none">
		<div style="margin-top: -10px; margin-left: -6px;">
			<div class="mon-toolbar metro-gray">
				<div class="prompt">
					<span>设备列表</span>
				</div>
				<div class="mon-right">
					<div id="btnSelectDevice" class="mon-button">
						<div class="icon i-16-checked"></div>
						<span>选择</span>
					</div>
				</div>
			</div>
		</div>
		<div id="gridDevices"></div>
	</div>
</body>
</html>