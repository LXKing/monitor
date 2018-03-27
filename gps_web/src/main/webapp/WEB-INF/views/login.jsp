<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>

<html>
<head>
    <title>车辆监控系统</title>
    <link href="<%=request.getContextPath()%>/resources/css/site.css"
          rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/resources/images/monitor.ico"
          rel="icon" type="image/x-icon"/>
    <link href="<%=request.getContextPath()%>/resources/images/monitor.ico"
          rel="shortcut" type="image/x-icon"/>
    <style type="text/css">
        *:focus {
            outline: none
        }

        body {
            background-color: #1874bf;
        }

        #btnCodeVerify {
            cursor: pointer;
            vertical-align: middle;
        }
    </style>
</head>

<body>
<form:form method="POST" modelAttribute="login">
    <div class="login-box">
        <div class="area">
            <div class="form">
                <input style="display: none">
                <form:input path="account" cssClass="login-user" autocomplete="off"/>
                <div class="display-error">
                    <form:errors path="account"/>
                </div>
                <input type="password" style="display: none">
                <form:password path="pwd" cssClass="login-pwd" autocomplete="off"/>
                <div class="display-error">
                    <form:errors path="pwd"/>
                </div>
                <input style="display: none">
                <form:input path="verify" cssClass="login-verify"/>
                <img id="btnCodeVerify" alt="验证码" src="code.verify"
                     onClick="document.getElementById('btnCodeVerify').src='code.verify?temp='+ (new Date().getTime().toString(36)); return false">
                <div style="height: 40px; width: 400px; margin-top: 16px;">
                    <input type="submit" value="登 录" class="login-button"/> <input
                        type="reset" value="重 填" class="login-button"/>
                </div>
                <div class="exception mon-left">
                    <form:errors path=""/>
                </div>
            </div>
        </div>
    </div>
</form:form>
</body>
</html>
