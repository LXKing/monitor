<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<H2>提示:</H2>
<span class="error"> <%=request.getAttribute("error")%></span>
</body>
</html>