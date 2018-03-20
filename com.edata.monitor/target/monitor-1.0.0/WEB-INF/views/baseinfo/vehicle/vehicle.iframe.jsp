<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.edata.monitor.cache.AuthorizeCache"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>车辆管理</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/vehicle/vehicle.js"></script>
</head>
<body>
	<%
		boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.create", request);
		request.setAttribute("create_auth", create_auth);

		boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.update", request);
		request.setAttribute("update_auth", update_auth);

		boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.delete", request);
		request.setAttribute("delete_auth", delete_auth);

		boolean addOwners_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.addOwners", request);
		request.setAttribute("addOwners_auth", addOwners_auth);

		boolean removeOwner_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.removeOwner", request);
		request.setAttribute("removeOwner_auth", removeOwner_auth);

		boolean addDrivers_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.addDrivers", request);
		request.setAttribute("addDrivers_auth", addDrivers_auth);

		boolean removeDriver_auth = AuthorizeCache.hasAuthorized("baseinfo.vehicle.removeDriver", request);
		request.setAttribute("removeDriver_auth", removeDriver_auth);
	%>
	<input id="addOwners_auth" type="hidden" value="${addOwners_auth}">
	<input id="removeOwner_auth" type="hidden" value="${removeOwner_auth}">
	<input id="addDrivers_auth" type="hidden" value="${addDrivers_auth}">
	<input id="removeDriver_auth" type="hidden"
		value="${removeDriver_auth}">
	<div id="vehicleFrame">
		<div class="mon-toolbar metro-gray">
			<div class="title mon-2x">车辆列表</div>
			<div class="prompt width-3x">
				关键字：<input id="txtVehicleFilter" type="text"
					placeholder="车牌号、设备号、sim卡号" />
			</div>
			<div id="btnQueryVehicle" class="mon-button">
				<div class="icon i-16-search"></div>
				<span>查询</span>
			</div>
			<c:if test="${create_auth || update_auth || delete_auth}">
				<div class="spliter"></div>
			</c:if>
			<c:if test="${create_auth}">
				<div id="btnCreateVehicle" class="mon-button">
					<div class="icon i-16-add"></div>
					<span>添加</span>
				</div>
			</c:if>
			<c:if test="${update_auth}">
				<div id="btnEditVehicle" class="mon-button">
					<div class="icon i-16-edit"></div>
					<span>修改</span>
				</div>
			</c:if>
			<c:if test="${delete_auth}">
				<div id="btnRemoveVehicle" class="mon-button">
					<div class="icon i-16-remove"></div>
					<span>删除</span>
				</div>
			</c:if>
		</div>
		<div class="mon-clear"></div>
		<div id="gridVehicle"></div>

		<div class="mon-clear"></div>
		<div id="dialogVehicleSelectOwner" class="display-none">
			<div class="mon-toolbar metro-gray">
				<div class="title mon-3x">车主列表</div>
				<div class="prompt width-3x">
					关键字：<input id="txtOwnerFilter" type="text" placeholder="姓名、电话" />
				</div>
				<div id="btnQueryOwner" class="mon-button">
					<div class="icon i-16-search"></div>
					<span>查询</span>
				</div>

				<div class="spliter"></div>
				<div id="btnSelectOwner" class="mon-button">
					<div class="icon i-16-checked"></div>
					<span>选择</span>
				</div>
			</div>
			<div class="mon-clear"></div>
			<div id="gridVehicleInOwner55c6cc891d19b64134b972ea"></div>
		</div>

		<div class="mon-clear"></div>
		<div id="dialogVehicleSelectDriver" class="display-none">
			<div class="mon-toolbar metro-gray">
				<div class="title mon-3x">驾驶员列表</div>
				<div class="prompt width-3x">
					关键字：<input id="txtDriverFilter" type="text"
						placeholder="姓名、电话、驾驶证号" />
				</div>
				<div id="btnQueryDriver" class="mon-button">
					<div class="icon i-16-search"></div>
					<span>查询</span>
				</div>

				<div class="spliter"></div>
				<div id="btnSelectDriver" class="mon-button">
					<div class="icon i-16-checked"></div>
					<span>选择</span>
				</div>
			</div>
			<div class="mon-clear"></div>
			<div id="gridVehicleInDriver55c6cc891d19b64134b972eb"></div>
		</div>
	</div>
</body>
</html>