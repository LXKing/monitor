<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><sitemesh:write property='title'/></title>
    <link href="<%=request.getContextPath()%>/resources/new/css/zTreeStyle.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/new/js/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/new/css/Maincss.css" rel="stylesheet"/>
    <script src="${ctx}/resources/new/js/jquery/jquery.2.1.4.min.js"></script>


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
        <div class="headtop">
            <!-- logo -->
            <%-- <div class="logoimg">
                 <img src="${ctx}/image/logorun.png"/>
            </div> --%>
            <!-- 头部图标功能 -->
            <div class="imgtubiao">
                <ul>
                    <li>
                        <shiro:hasPermission name="center">
                            <a id="btnLocate" href="<%=request.getContextPath()%>/center">
                                <img src="${ctx}/resources/new/image/2018321.png"/>
                                <span>在线监控</span>
                            </a>
                        </shiro:hasPermission>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/baseinfo">
                            <img src="${ctx}/resources/new/image/siji.png"/>
                            <span>司机管理</span>
                        </a>
                    </li>
                    <li>
                        <img src="${ctx}/resources/new/image/car.png"/>
                        <span>多车跟踪</span>
                    </li>

                    <li>

                        <img src="${ctx}/resources/new/image/statistics.png"/>
                        <span>统计分析</span>
                    </li>

                    <li>

                        <img src="${ctx}/resources/new/image/Operation.png"/>
                        <span>运行管理</span>
                    </li>

                    <li>
                        <img src="${ctx}/resources/new/image/report.png"/>
                        <span>报表管理</span>
                    </li>

                    <li>
                        <img src="${ctx}/resources/new/image/history.png"/>
                        <span>历史轨迹</span>
                    </li>

                    <li>
                        <img src="${ctx}/resources/new/image/Signaling.png"/>
                        <span>信令管理</span>
                    </li>

                    <li>
                        <img src="${ctx}/resources/new/image/alarm.png"/>
                        <span>告警管理</span>
                    </li>

                    <li>
                        <img src="${ctx}/resources/new/image/More_functions.png"/>
                        <span>更多功能</span>
                    </li>

                </ul>
            </div>
            <div class="user">
                兵工有限公司
            </div>
            <div class="head_right">

                <span><shiro:principal property="account"/></span>
                <span>退出</span>

            </div>
        </div>
    </div>


    <%--<script src="${ctx}/resources/new/js/jquery/jquery.ztree.core-3.5.min.js"></script>
    <script src="${ctx}/resources/new/js/jquery/jquery.ztree.exedit.js"></script>
    <script src="${ctx}/resources/new/js/jquery/jquery.ztree.excheck-3.5.js"></script>--%>
    <script src="${ctx }/resources/new/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx }/resources/new/js/Main.js"></script>

    <script>

    </script>
    <form action="<%=request.getContextPath()%>/logout" id="LogoutForm"
          method="post"></form>
    <div id="dialogs" style="display: none;"></div>
    <input id="rootPath" type="hidden"
           value="<%=request.getContextPath()%>"/>
</body>
</html>