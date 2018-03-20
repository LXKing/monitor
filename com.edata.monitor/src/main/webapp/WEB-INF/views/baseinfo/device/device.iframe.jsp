<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.edata.monitor.cache.AuthorizeCache"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>终端管理</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/device/device.js"></script>
</head>
<body>
	<%
		boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.device.create", request);
		request.setAttribute("create_auth", create_auth);

		boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.device.update", request);
		request.setAttribute("update_auth", update_auth);

		boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.device.delete", request);
		request.setAttribute("delete_auth", delete_auth);
	%>
	<div id="deviceFrame">
		<div class="mon-toolbar metro-gray">
			<div class="title mon-3x">终端列表</div>
			<div class="prompt width-3x">
				关键字：<input id="txtDeviceFilter" type="text" placeholder="终端号、sim卡号" />
			</div>
			<div id="btnQueryDevice" class="mon-button">
				<div class="icon i-16-search"></div>
				<span>查询</span>
			</div>
			<c:if test="${create_auth || update_auth || delete_auth}">
				<div class="spliter"></div>
			</c:if>
			<c:if test="${create_auth}">
				<div id="btnCreateDevice" class="mon-button">
					<div class="icon i-16-add"></div>
					<span>添加</span>
				</div>
			</c:if>
			<c:if test="${update_auth}">
				<div id="btnEditDevice" class="mon-button">
					<div class="icon i-16-edit"></div>
					<span>修改</span>
				</div>
			</c:if>
			<c:if test="${delete_auth}">
				<div id="btnRemoveDevice" class="mon-button">
					<div class="icon i-16-remove"></div>
					<span>删除</span>
				</div>
			</c:if>
		</div>
		<div class="mon-clear"></div>
		<div id="gridDevice"></div>
	</div>
</body>
</html>