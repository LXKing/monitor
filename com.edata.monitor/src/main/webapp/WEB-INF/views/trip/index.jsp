<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>行程报告</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/trip/trip.js"></script>
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
                设备：<input id="txtDeviceFilter" type="text"/>
                <div id="btnSearchDevices" class="button">
                    <span>查找</span>
                </div>
            </div>
            <div class="display-label">
                设备号：<span
                    id="txtDeviceNumber"><%=request.getParameter("number") == null ? "" : request.getParameter("number")%></span>
            </div>
            <div class="display-label">
                设备名：<span id="txtDeviceName"></span>
            </div>

            <div class="mon-nowrap">
                <div style="padding: 3px;">
                    <input id="txtStartDate" type="text"/>
                </div>
                <div style="padding: 3px;">
                    <input id="txtEndDate" type="text"/>
                </div>
            </div>
        </div>
        <div class="mon-toolbar metro-gray mon-nowrap">
            <div class="title">行程汇总</div>
            <div class="prompt width-x">
                油费：<input id="txtPrice" type="number" value="7.4"/>
            </div>
        </div>
        <div class="mon-clear"></div>
        <div style="overflow: auto; padding: 3px;">
            <div class="mon-total-title">驾驶习惯:</div>
            <div id="txtScore" class="mon-total-context">A</div>
            <div class="mon-total-title">总里程:</div>
            <div class="mon-total-context">
                <span id="txtMileage">0</span>(公里)
            </div>
            <div class="mon-total-title">总油耗:</div>
            <div class="mon-total-context">
                <span id="txtFuel">0</span>(升)
            </div>
            <div class="mon-total-title">百公里油耗:</div>
            <div class="mon-total-context">
                <span id="txtAvgFuel">0</span>(升/百公里)
            </div>
            <div class="mon-total-title">总时间:</div>
            <div class="mon-total-context">
                <span id="txtHours">0</span>小时<span id="txtMinutes">0</span>分
            </div>
            <div class="mon-total-title">总行程数:</div>
            <div class="mon-total-context">
                <div id="txtTimes">0</div>
            </div>
            <div class="mon-total-title">总费用:</div>
            <div class="mon-total-context">
                <div id="txtCost">0</div>
            </div>
        </div>
    </div>
</rightpad>
<div class="mon-toolbar metro-gray">
    <div class="title">行程报告</div>
</div>
<div id="chartContainer"></div>
<div id="gridTrips"></div>
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