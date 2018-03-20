<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>角色授权</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/role/authorize.js"></script>
</head>
<body>
	<div id="gridRoleAuthorize" style="height: 460px; overflow: auto;"></div>
</body>
</html>