<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript">
	function validate() {
		var v = $('form').validate();
		var result = v.checkForm();
		v.showErrors();
		return result;
	}
	function setNumbers(numbers) {
		$('#processAlarmAllForm #deviceNumbers').val(numbers);
	}
</script>
</head>
<body>
	<div id="processAlarmAllEditor" style="padding: 5px;">
		<form:form id="processAlarmAllForm" method="post"
			modelAttribute="alarm">
			<form:hidden path="deviceNumbers" />

			<div class="editor-label">
				<form:label path="userMethod">处理方式：
						</form:label>
			</div>
			<div class="editor-field">
				<form:select path="userMethod" items="${types}" itemLabel="name"
					itemValue="name"></form:select>
				<form:errors path="userMethod" cssClass="display-error" />
			</div>
			<div class="editor-label">
				<form:label path="userRemark">处理内容：
						</form:label>
			</div>
			<div class="editor-field">
				<form:textarea path="userRemark" rows="5" cols="84" required="true"></form:textarea>
				<form:errors path="userRemark" cssClass="display-error" />
			</div>
		</form:form>
	</div>
</body>
</html>