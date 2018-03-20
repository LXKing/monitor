<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>修改地图图层</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/mapLayer/editor.js"></script>
</head>
<body>
	<div id="mapLayerEditor" style="padding: 5px;">
		<form:form id="editMapLayerForm" method="post"
			modelAttribute="mapLayer">
			<form:hidden path="id" />
			<form:hidden path="companyId" />
			<form:hidden path="editTime" />
			<div class="editor-label">
				<form:label path="name">名称：
						</form:label>
			</div>
			<div class="editor-field">
				<form:input path="name" required="true" />
				<form:errors path="name" cssClass="error" />
			</div>
			<div class="editor-label">
				<form:label path="visible">可见否：
						</form:label>
			</div>
			<div class="editor-field">
				<form:checkbox path="visible" />
				<form:errors path="visible" cssClass="error" />
			</div>

			<div class="editor-label">
				<form:label path="remark">备注：
						</form:label>
			</div>
			<div class="editor-field width-6x">
				<form:input path="remark" />
				<form:errors path="remark" cssClass="error" />
			</div>
		</form:form>
	</div>
</body>
</html>