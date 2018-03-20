<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>创建车辆保养记录</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/maintain/editor.js"></script>
</head>
<body>
	<div id="maintainEditor" style="padding: 5px;">
		<form:form id="createMaintainForm" method="post"
			modelAttribute="maintain">
			<form:hidden path="vehicleId" />
			<table class="mon-layout-table">
				<tr>
					<td style="width: 400px;">
						<div class="editor-label">
							<form:label path="plateNumber">车牌号：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="plateNumber" required="true" />
							<form:errors path="plateNumber" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="doneDate">保养日期：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="doneDate" required="true" />
							<form:errors path="doneDate" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="mileage">保养里程(公里)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="mileage" type="number" />
							<form:errors path="mileage" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="fee">保养费用(元)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="fee" type="number" />
							<form:errors path="fee" cssClass="error" />
						</div>
					</td>
					<td>
						<div class="editor-label">
							<form:label path="garage">保养单位：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="garage" />
							<form:errors path="garage" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="agent">经办人：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="agent" />
							<form:errors path="agent" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="nextDate">下次保养日期：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="nextDate" />
							<form:errors path="nextDate" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="nextMileage">下次保养里程(公里)：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="nextMileage" type="number" />
							<form:errors path="nextMileage" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="editor-label">
							<form:label path="type">保养类型：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="type" />
							<form:errors path="type" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="editor-label">
							<form:label path="content">保养内容：
						</form:label>
						</div>
						<div class="editor-field">
							<form:textarea path="content" rows="3" cols="80" />
							<form:errors path="content" cssClass="error" />
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>