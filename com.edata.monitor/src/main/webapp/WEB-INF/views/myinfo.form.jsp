<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>个人信息</title>
<script type="text/javascript">
function validate() {
	var v = $('form').validate({
		rules : {
			account : {
				required : true,
				rangelength : [ 2, 50 ],
				remote : {
					url : "user/exist",
					type : "post",
					dataType : "json",
					data : {
						key : function() {
							return $("#account").val();
						},
						id : function() {
							return $("#id").val();
						},
						checkId : function() {
							return true;
						}
					}
				}
			},
			pwd : {
				required : true,
				rangelength : [ 6, 50 ]
			},
			name : {
				required : true,
				rangelength : [ 2, 50 ]
			},
			email : {
				email : true
			}
		},
		messages : {
			account : {
				remote : '该帐号已存在'
			}
		}
	});

	var result = v.checkForm();
	v.showErrors();
	return result;
}
</script>
</head>
<body>
	<div style="padding: 5px;">
		<form:form method="POST" modelAttribute="myinfo">
			<form:hidden path="id" />
			<form:hidden path="editTime" />
			<div class="editor-label">
				<form:label path="account">帐号：
						</form:label>
			</div>
			<div class="editor-field width-xxx">
				<form:input path="account" />
				<form:errors path="account" cssClass="display-error" />
			</div>
			<div class="editor-label">
				<form:label path="pwd">密码：
						</form:label>
			</div>
			<div class="editor-field width-xxx">
				<form:password path="pwd" />
				<form:errors path="pwd" cssClass="display-error" />
			</div>
			<div class="editor-label">
				<form:label path="name">名称：
						</form:label>
			</div>
			<div class="editor-field width-xxx">
				<form:input path="name" />
				<form:errors path="name" cssClass="display-error" />
			</div>
			<div class="editor-label">
				<form:label path="email">邮箱：
						</form:label>
			</div>
			<div class="editor-field width-xxxx">
				<form:input path="email" />
				<form:errors path="email" cssClass="display-error" />
			</div>
			<div class="editor-label">
				<form:label path="phone">电话：
						</form:label>
			</div>
			<div class="editor-field width-xxx">
				<form:input path="phone" />
			</div>
			<div class="editor-label">
				<form:label path="contact">联系人：
						</form:label>
			</div>
			<div class="editor-field width-xxx">
				<form:input path="contact" />
			</div>

		</form:form>
	</div>
</body>
</html>