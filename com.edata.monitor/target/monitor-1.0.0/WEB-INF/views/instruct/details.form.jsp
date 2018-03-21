<%@ page session="false" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>指令详情</title>
</head>
<body>
<div class="display-label">设备号：</div>
<div class="display-field mon-xxxx">
    <spring:eval expression="instruct.deviceNumber"></spring:eval>
</div>
<div class="display-label">命令：</div>
<div class="display-field mon-xxxx">
    <spring:eval expression="instruct.name"></spring:eval>
</div>
<div class="display-label">发送时间：</div>
<div class="display-field mon-xxxx">
    <spring:eval expression="instruct.sendTime"></spring:eval>
</div>
<div class="display-label">应答时间：</div>
<div class="display-field mon-xxxx">
    <spring:eval expression="instruct.replyTime"></spring:eval>
</div>
<div class="display-label">结果：</div>
<div class="display-field" style="height: 150px; overflow: auto;">
    <spring:eval expression="instruct.result"></spring:eval>
</div>
</body>
</html>