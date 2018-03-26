<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>超时驾驶日志</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/deviceData/timeoutLog.js"></script>
</head>
<body>
<div id="timoutLogMap"
     style="width: 100%; height: 400px; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
<div id="divBottom">
    <div class="mon-toolbar metro-gray">
        <div class="title mon-3x">超时驾驶列表</div>
        <div class="prompt editor-field">
            <input id="txtStartDate" type="text"/>
        </div>
        <div class="prompt editor-field">
            <input id="txtEndDate" type="text"/>
        </div>
        <div id="btnQuery" class="mon-button">
            <div class="icon i-16-search"></div>
            <span>查询</span>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="gridTimeoutLog"></div>
</div>
</body>
</html>