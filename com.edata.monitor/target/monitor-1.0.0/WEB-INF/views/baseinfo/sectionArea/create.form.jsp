<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>创建路段</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/sectionArea/editor.js"></script>
</head>
<body>
	<div id="sectionAreaEditor"
		style="margin-top: -5px; margin-right: 6px;">
		<form:form id="createSectionAreaForm" method="post"
			modelAttribute="sectionArea">
			<form:hidden path="flag" />
			<form:hidden path="path" />
			<table class="mon-layout-table">
				<tr>
					<td style="width: 400px;">
						<div class="editor-label">
							<form:label path="name">名称：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="name" required="true" />
							<form:errors path="name" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="width">路段宽度(米)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="width" type="number" />
							<form:errors path="width" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="maxSeconds">路段行驶过长阈值(秒)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="maxSeconds" type="number" />
							<form:errors path="maxSeconds" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="minSeconds">路段行驶不足阈值(秒)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="minSeconds" type="number" />
							<form:errors path="minSeconds" cssClass="error" />
						</div>
					</td>
					<td>
						<div class="editor-label">
							<form:label path="maxSpeed">路段最高速度(公里/小时)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="maxSpeed" type="number" />
							<form:errors path="maxSpeed" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="overspeedSeconds">路段超速持续时间(秒)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="overspeedSeconds" type="number" />
							<form:errors path="overspeedSeconds" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="editor-label">
							<form:label path="flag">区域属性：
						</form:label>
						</div>
						<div id="flags"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="editor-label">
							<form:label path="remark">备注：
						</form:label>
						</div>
						<div class="editor-field width-6x">
							<form:input path="remark" />
							<form:errors path="remark" cssClass="error" />
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>