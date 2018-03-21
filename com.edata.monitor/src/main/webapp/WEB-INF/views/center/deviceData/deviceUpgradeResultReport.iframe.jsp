<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>终端升级结果报告</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/deviceData/deviceUpgradeResultReport.js"></script>
</head>
<body>
<div class="mon-toolbar metro-gray">
    <div class="title mon-3x">终端升级结果报告列表</div>
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
<div id="gridDeviceUpgradeResultReport"></div>
</body>
</html>