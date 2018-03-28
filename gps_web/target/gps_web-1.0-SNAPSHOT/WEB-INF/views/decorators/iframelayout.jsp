<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><sitemesh:write property='title'/></title>
    <link href="<%=request.getContextPath()%>/resources/images/monitor.ico"
          rel="icon" type="image/x-icon"/>
    <link href="<%=request.getContextPath()%>/resources/images/monitor.ico"
          rel="shortcut" type="image/x-icon"/>
    <link
            href="<%=request.getContextPath()%>/resources/skins/Aqua/css/ligerui-all.css"
            rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/resources/css/site.css"
          rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/resources/css/icon.css"
          rel="stylesheet" type="text/css"/>


    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/new/css/hzkstyle.css">

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jquery.metadata.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/messages_cn.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/ligerui.all.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/framework.js"></script>
    <sitemesh:write property='head'/>
</head>
<body>
<sitemesh:write property='body'/>
</body>

</html>