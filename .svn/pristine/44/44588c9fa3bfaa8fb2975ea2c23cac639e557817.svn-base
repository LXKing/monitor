<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>操作日志</title>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/statistics/log/operateLog.js"></script>
</head>
<body>
<div class="mon-toolbar metro-gray">
    <div class="title mon-3x">操作日志</div>
    <div class="prompt width-3x">
        用户：<input id="txtUser" type="text"/>
    </div>
    <div class="prompt editor-field">
        开始时间：<input id="txtStartDate" type="text"/>
    </div>
    <div class="prompt editor-field">
        结束时间：<input id="txtEndDate" type="text"/>
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
<div id="gridOperateLog"></div>
</body>
</html>