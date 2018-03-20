<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.edata.monitor.cache.AuthorizeCache"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>路线管理</title>
<style type="text/css">
#allmap {
	width: 100%;
	height: 400px;
	overflow: hidden;
}
</style>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/routeArea/routeArea.js"></script>
</head>
<body>
	<%
		boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.create", request);
		request.setAttribute("create_auth", create_auth);

		boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.update", request);
		request.setAttribute("update_auth", update_auth);

		boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.delete", request);
		request.setAttribute("delete_auth", delete_auth);

		boolean addSections_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.addSections", request);
		request.setAttribute("addSections_auth", addSections_auth);

		boolean removeSection_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.removeSection", request);
		request.setAttribute("removeSection_auth", removeSection_auth);

		boolean addVehicles_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.addVehicles", request);
		request.setAttribute("addVehicles_auth", addVehicles_auth);

		boolean removeVehicle_auth = AuthorizeCache.hasAuthorized("baseinfo.routeArea.removeVehicle", request);
		request.setAttribute("removeVehicle_auth", removeVehicle_auth);
	%>
	<input id="addSections_auth" type="hidden" value="${addSections_auth}">
	<input id="removeSection_auth" type="hidden"
		value="${removeSection_auth}">
	<input id="addVehicles_auth" type="hidden" value="${addVehicles_auth}">
	<input id="removeVehicle_auth" type="hidden"
		value="${removeVehicle_auth}">
	<div id="routeAreaFrame">
		<div id="divRouteAreaAllMap"
			style="overflow: hidden; zoom: 1; position: relative;">
			<div id="routeAreaMap"
				style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
		</div>
		<div style="height: 300px;">
			<div class="mon-toolbar metro-gray">
				<div class="title mon-3x">路线列表</div>
				<div class="prompt width-3x">
					名称：<input id="txtRouteAreaFilter" type="text" placeholder="路线名称" />
				</div>
				<div id="btnQueryRouteArea" class="mon-button">
					<div class="icon i-16-search"></div>
					<span>查询</span>
				</div>
				<c:if test="${create_auth || update_auth || delete_auth}">
					<div class="spliter"></div>
				</c:if>
				<c:if test="${create_auth}">
					<div id="btnCreateRouteArea" class="mon-button">
						<div class="icon i-16-add"></div>
						<span>添加</span>
					</div>
				</c:if>
				<c:if test="${update_auth}">
					<div id="btnEditRouteArea" class="mon-button">
						<div class="icon i-16-edit"></div>
						<span>编辑</span>
					</div>
				</c:if>
				<c:if test="${delete_auth}">
					<div id="btnRemoveRouteArea" class="mon-button">
						<div class="icon i-16-remove"></div>
						<span>删除</span>
					</div>
				</c:if>
			</div>
			<div class="mon-clear"></div>
			<div id="gridRouteArea"></div>
		</div>
		<div id="dialogRouteSelectSection" class="display-none">
			<div class="mon-toolbar metro-gray">
				<div class="title mon-3x">路段列表</div>
				<div class="prompt width-3x">
					名称：<input id="txtSectionAreaFilter" type="text" placeholder="路段名称" />
				</div>
				<div id="btnQuerySectionArea" class="mon-button">
					<div class="icon i-16-search"></div>
					<span>查询</span>
				</div>

				<div class="spliter"></div>
				<div id="btnSelectSectionArea" class="mon-button">
					<div class="icon i-16-checked"></div>
					<span>选择</span>
				</div>
			</div>
			<div class="mon-clear"></div>
			<div id="gridSectionInRoute55c6d38e1d19b641500578f7"></div>
		</div>
	</div>
	<div class="mon-clear"></div>
	<div id="dialogRouteAreaSelectVehicle" class="display-none">
		<div class="mon-toolbar metro-gray">
			<div class="title mon-3x">车辆列表</div>
			<div class="prompt width-3x">
				关键字：<input id="txtVehicleFilter" type="text"
					placeholder="车牌号、设备号、SIM卡号" />
			</div>
			<div id="btnQueryVehicle" class="mon-button">
				<div class="icon i-16-search"></div>
				<span>查询</span>
			</div>

			<div class="spliter"></div>
			<div id="btnSelectVehicle" class="mon-button">
				<div class="icon i-16-checked"></div>
				<span>选择</span>
			</div>
		</div>
		<div class="mon-clear"></div>
		<div id="gridVehicleInRouteArea"></div>
	</div>
</body>
</html>