<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>修改矩形区域</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/rectangleArea/editor.js"></script>
</head>
<body>
	<div id="rectangleAreaEditor" style="padding: 5px;">
		<form:form id="editRectangleAreaForm" method="post"
			modelAttribute="rectangleArea">
			<form:hidden path="id" />
			<form:hidden path="companyId" />
			<form:hidden path="editTime" />
			<form:hidden path="flag" />
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
							<form:label path="ullat">左上点纬度：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="ullat" type="number" />
							<form:errors path="ullat" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="ullng">左上点经度：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="ullng" type="number" />
							<form:errors path="ullng" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="brlat">右下点纬度：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="brlat" type="number" />
							<form:errors path="brlat" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="brlng">右下点经度：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="brlng" type="number" />
							<form:errors path="brlng" cssClass="error" />
						</div>
					</td>
					<td>
						<div class="editor-label">
							<form:label path="maxSpeed">最高速度(公里/小时)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="maxSpeed" type="number" />
							<form:errors path="maxSpeed" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="overspeedSeconds">超速持续时间(秒)：
						</form:label>
						</div>
						<div class="editor-field ">
							<form:input path="overspeedSeconds" type="number" />
							<form:errors path="overspeedSeconds" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="startTime">开始时间：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="startTime" />
							<form:errors path="startTime" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="endTime">结束时间：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="endTime" />
							<form:errors path="endTime" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="editor-label">
							<form:label path="deviceCatch">终端计算否：
						</form:label>
						</div>
						<div class="editor-field">
							<form:checkbox path="deviceCatch" />
							<form:errors path="deviceCatch" cssClass="error" />
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