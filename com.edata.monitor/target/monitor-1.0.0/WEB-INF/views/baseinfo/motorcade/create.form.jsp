<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>创建车队</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/baseinfo/motorcade/editor.js"></script>
</head>
<body>
	<div id="motorcadeEditor" style="padding: 5px;">
		<form:form method="post" modelAttribute="motorcade">
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
							<form:label path="type">类型：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="type" />
							<form:errors path="type" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="parentVisible">上级可见：
						</form:label>
						</div>
						<div class="editor-field">
							<form:checkbox path="parentVisible" />
						</div>
					</td>
					<td>
						<div class="editor-label">
							<form:label path="linkMan">联系人：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="linkMan" />
							<form:errors path="linkMan" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="phone">电话：
						</form:label>
						</div>
						<div class="editor-field">
							<form:input path="phone" />
							<form:errors path="phone" cssClass="error" />
						</div>
						<div class="editor-label">
							<form:label path="remark">备注：
						</form:label>
						</div>
						<div class="editor-field width-4x">
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