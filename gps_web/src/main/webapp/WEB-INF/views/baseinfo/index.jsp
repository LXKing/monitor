<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>基础资料</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/new/css/hzkstyle.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/baseinfo/baseinfo.js"></script>

</head>
<body>
<leftpad>
    <div position="left" title="功能菜单" style="overflow: auto; height: 100%;">
        <ul id="catalog"></ul>
    </div>
</leftpad>
<div id="pages" class="liger-tab"></div>

<script>
    $(document).ready(function () {
        setTimeout(function () {
            $("#catalog").css("width", "200px");
        }, 1000);

        setTimeout(function () {
            $(".l-layout-left").css("top", "100px").css("width", "282px");
        }, 5);
        setTimeout(function () {
            $(".l-layout-center").css("top", "100px").css("left", "282px");
        }, 5);
        setTimeout(function () {
            $(".l-layout-top").css("height", "100px");
        }, 5);
        $("#catalog").addClass("dsd");
        console.log("sssssssssssssss");

        $("input:empty").css(" background-color", "orange");
    });
</script>
</body>


</html>