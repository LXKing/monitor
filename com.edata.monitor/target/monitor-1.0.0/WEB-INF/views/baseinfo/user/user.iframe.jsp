<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.edata.monitor.cache.AuthorizeCache"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/user/user.js"></script>
</head>
<body>
	<%
		boolean create_auth = AuthorizeCache.hasAuthorized("baseinfo.user.create", request);
		request.setAttribute("create_auth", create_auth);

		boolean update_auth = AuthorizeCache.hasAuthorized("baseinfo.user.update", request);
		request.setAttribute("update_auth", update_auth);

		boolean delete_auth = AuthorizeCache.hasAuthorized("baseinfo.user.delete", request);
		request.setAttribute("delete_auth", delete_auth);

		boolean addMonitors_auth = AuthorizeCache.hasAuthorized("baseinfo.user.addMonitors", request);
		request.setAttribute("addMonitors_auth", addMonitors_auth);

		boolean removeMonitor_auth = AuthorizeCache.hasAuthorized("baseinfo.user.removeMonitor", request);
		request.setAttribute("removeMonitor_auth", removeMonitor_auth);

		boolean addRoles_auth = AuthorizeCache.hasAuthorized("baseinfo.user.addRoles", request);
		request.setAttribute("addRoles_auth", addRoles_auth);

		boolean removeRole_auth = AuthorizeCache.hasAuthorized("baseinfo.user.removeRole", request);
		request.setAttribute("removeRole_auth", removeRole_auth);
	%>
	<input id="addMonitors_auth" type="hidden" value="${addMonitors_auth}">
	<input id="removeMonitor_auth" type="hidden"
		value="${removeMonitor_auth}">
	<input id="addRoles_auth" type="hidden" value="${addRoles_auth}">
	<input id="removeRole_auth" type="hidden" value="${removeRole_auth}">
	<div id="userFrame">
		<div class="mon-toolbar metro-gray">
			<div class="title mon-3x">用户列表</div>
			<div class="prompt width-3x">
				用户：<input id="txtUserFilter" type="text" />
			</div>
			<div id="btnQueryUser" class="mon-button">
				<div class="icon i-16-search"></div>
				<span>查询</span>
			</div>
			<c:if test="${create_auth || update_auth || delete_auth}">
				<div class="spliter"></div>
			</c:if>
			<c:if test="${create_auth}">
				<div id="btnCreateUser" class="mon-button">
					<div class="icon i-16-add"></div>
					<span>添加</span>
				</div>
			</c:if>
			<c:if test="${update_auth}">
				<div id="btnEditUser" class="mon-button">
					<div class="icon i-16-edit"></div>
					<span>修改</span>
				</div>
			</c:if>
			<c:if test="${delete_auth}">
				<div id="btnRemoveUser" class="mon-button">
					<div class="icon i-16-remove"></div>
					<span>删除</span>
				</div>
			</c:if>
		</div>
		<div class="mon-clear"></div>
		<div id="gridUser"></div>

		<div class="mon-clear"></div>
		<div id="dialogUserSelectMonitor" class="display-none">
			<div class="mon-toolbar metro-gray">
				<div class="title mon-3x">监控列表</div>
				<div class="prompt width-3x">
					名称：<input id="txtMonitorFilter" type="text" placeholder="企业名、车队名" />
				</div>
				<div id="btnQueryMonitor" class="mon-button">
					<div class="icon i-16-search"></div>
					<span>查询</span>
				</div>
				<div class="spliter"></div>
				<div id="btnSelectMonitor" class="mon-button">
					<div class="icon i-16-checked"></div>
					<span>选择</span>
				</div>
			</div>
			<div class="mon-clear"></div>
			<div id="gridUserSelectMonitor55c80c4d1d19b6424f084014"></div>
		</div>

		<div class="mon-clear"></div>
		<div id="dialogUserSelectRole" class="display-none">
			<div class="mon-toolbar metro-gray">
				<div class="title mon-3x">角色列表</div>

				<div id="btnRefreshRole" class="mon-button">
					<div class="icon i-16-refresh"></div>
					<span>刷新</span>
				</div>
				<div class="spliter"></div>
				<div id="btnSelectRole" class="mon-button">
					<div class="icon i-16-checked"></div>
					<span>选择</span>
				</div>
			</div>
			<div class="mon-clear"></div>
			<div id="gridUserSelectRole55c80c4d1d19b6424f084015"></div>
		</div>
	</div>
</body>
</html>