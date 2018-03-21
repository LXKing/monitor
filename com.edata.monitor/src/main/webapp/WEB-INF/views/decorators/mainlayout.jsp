<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edata.monitor.cache.AuthorizeCache" %>
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
<div id="layout">
    <sitemesh:write property='leftpad'/>
    <sitemesh:write property='rightpad'/>
    <div position="center">
        <sitemesh:write property='body'/>
    </div>
    <div position="top" class="mon-top">
        <div class="mon-top-left">
            <%
                boolean saveMyinfo_auth = AuthorizeCache.hasAuthorized("security.saveMyinfo", request);
                request.setAttribute("saveMyinfo_auth", saveMyinfo_auth);

                boolean saveMyKey_auth = AuthorizeCache.hasAuthorized("security.saveMyKey", request);
                request.setAttribute("saveMyKey_auth", saveMyKey_auth);
            %>
            <div class="mon-top-info">

            </div>
        </div>
        <div class="mon-top-middle">
            <div class="mon-top-menu">
                <div class="mon-mainmenu">
                    <a id="btnHome" href="<%=request.getContextPath()%>/home">
                        <div class="home"></div>
                        <div class="title">首页</div>
                    </a>
                    <%
                        boolean center_auth = AuthorizeCache.hasAuthorized("center", request);
                        request.setAttribute("center_auth", center_auth);
                    %>
                    <c:if test="${center_auth}">
                        <a id="btnLocate" href="<%=request.getContextPath()%>/center">
                            <div class="monitor"></div>
                            <div class="title">监控中心</div>
                        </a>
                    </c:if>
                    <%
                        boolean baseinfo_auth = AuthorizeCache.hasAuthorized("baseinfo", request);
                        request.setAttribute("baseinfo_auth", baseinfo_auth);
                    %>
                    <c:if test="${baseinfo_auth}">
                        <a id="btnBaseInfo" href="<%=request.getContextPath()%>/baseinfo">
                            <div class="baseinfo"></div>
                            <div class="title">基础信息</div>
                        </a>
                    </c:if>
                    <%
                        boolean statistics_auth = AuthorizeCache.hasAuthorized("statistics", request);
                        request.setAttribute("statistics_auth", statistics_auth);
                    %>
                    <c:if test="${statistics_auth}">
                        <a id="btnStatistics"
                           href="<%=request.getContextPath()%>/statistics">
                            <div class="statistics"></div>
                            <div class="title">统计分析</div>
                        </a>
                    </c:if>
                </div>
            </div>

        </div>
        <div class="mon-top-right">


            <div class="mon-mainmenu">
<span style="vertical-align: center">

      <c:if test="${saveMyinfo_auth}">
                    <a id="btnMyinfo"><%=request.getAttribute("name")%>
                        <div>用户管理</div>
                    </a>
      </c:if>
                <c:if test="${!saveMyinfo_auth}">
                    <%=request.getAttribute("name")%>
                </c:if>

</span>


                <a>
                    <c:if test="${saveMyKey_auth}">
                        <span id="btnMykey">修改密码</span>
                    </c:if>
                    <span id="btnLogout">退出</span>
                </a>


            </div>


        </div>
    </div>
</div>
<form action="<%=request.getContextPath()%>/logout" id="LogoutForm"
      method="post"></form>
<div id="dialogs" style="display: none;"></div>
<input id="rootPath" type="hidden"
       value="<%=request.getContextPath()%>"/>
</body>
</html>