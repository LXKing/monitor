<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>地图图层设置</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/mapLayer/setting.js"></script>
</head>
<body>
	<div class="mon-toolbar metro-gray">
		<div class="title mon-3x">地图图层列表</div>
		<div class="prompt width-3x">
			名称：<input id="txtMapLayerFilter" type="text" placeholder="图层名称" />
		</div>
		<div id="btnQueryMapLayer" class="mon-button">
			<div class="icon i-16-search"></div>
			<span>查询</span>
		</div>
		<div class="spliter"></div>
		<div id="btnShowMapLayer" class="mon-button">
			<div class="icon i-16-show"></div>
			<span>显示</span>
		</div>
		<div id="btnHideMapLayer" class="mon-button">
			<div class="icon i-16-hide"></div>
			<span>隐藏</span>
		</div>
	</div>
	<div class="mon-clear"></div>
	<div id="gridMapLayer"></div>
</body>
</html>