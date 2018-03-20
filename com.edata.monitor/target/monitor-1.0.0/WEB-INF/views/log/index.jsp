<%@page import="com.edata.monitor.domain.security.Identify"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>数据日志</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/log/log.js"></script>
</head>
<body>
	<%=request.getAttribute("name")%>

</body>
</html>