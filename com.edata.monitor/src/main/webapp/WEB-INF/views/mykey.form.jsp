<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>个人信息</title>
    <script type="text/javascript">
        function validate() {
            var v = $('form').validate({
                rules: {
                    oldKey: {
                        required: true,
                        rangelength: [6, 50]
                    },
                    newKey: {
                        required: true,
                        rangelength: [6, 50]
                    },
                    confirmKey: {
                        required: true,
                        rangelength: [6, 50]
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
    <form:form method="POST" modelAttribute="mykey">
        <div class="editor-label">
            <form:label path="oldKey">旧密码：
            </form:label>
        </div>
        <div class="editor-field width-xxx">
            <form:password path="oldKey"/>
            <form:errors path="oldKey" cssClass="display-error"/>
        </div>
        <div class="editor-label">
            <form:label path="newKey">新密码：
            </form:label>
        </div>
        <div class="editor-field width-xxx">
            <form:password path="newKey"/>
            <form:errors path="newKey" cssClass="display-error"/>
        </div>
        <div class="editor-label">
            <form:label path="confirmKey">确认密码：
            </form:label>
        </div>
        <div class="editor-field width-xxx">
            <form:password path="confirmKey"/>
            <form:errors path="confirmKey" cssClass="display-error"/>
        </div>
    </form:form>
</div>
</body>
</html>