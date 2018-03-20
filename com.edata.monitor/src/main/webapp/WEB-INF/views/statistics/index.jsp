<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>统计分析</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/statistics/statistics.js"></script>
</head>
<body>
	<leftpad>
	<div position="left" title="功能菜单">
		<ul id="catalog"></ul>
	</div>
	</leftpad>
	<div id="pages" class="liger-tab"></div>
</body>
</html>