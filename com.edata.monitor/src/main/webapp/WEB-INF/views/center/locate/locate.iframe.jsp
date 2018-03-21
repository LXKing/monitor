<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edata.monitor.cache.AuthorizeCache" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/sockjs.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/gpsparser.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/instruct.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/datalog.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/locate.js"></script>
    <style type="text/css">
        .loc-titlebar {
            height: 32px;
            border: 1px solid gray;
            line-height: 32px;
            padding-left: 10px;
        }

        .loc-titlebar div {
            display: inline-block;
            width: 90px;
        }

        .loc-vehicletree {
            overflow: auto;
            position: absolute !important;
            position: relative;
            top: 32px !important;
            top: 32;
            bottom: 232px;
            width: 100%;
            height: auto !important;
            height: 100%;
        }

        .loc-controlpad {
            width: 100%;
            height: 232px;
            clear: both;
            position: absolute;
            bottom: 0;
            left: 0;
        }

        #divGroupOperate {
            margin-top: -1px;
            border: 1px solid gray;
            height: 200px;
        }

        #txtMessageTotal {
            background-color: red;
            height: 16px;
            line-height: 16px;
            border-radius: 8px;
            width: 40px;
            position: absolute;
            left: 44px;
            margin-top: 5px;
            color: white;
            text-align: center;
            display: none;
        }
    </style>
</head>
<body>
<%
    boolean vehileinfo_auth = AuthorizeCache.hasAuthorized("center.locate.vehileinfo", request);
    request.setAttribute("vehileinfo_auth", vehileinfo_auth);

    boolean deviceData_auth = AuthorizeCache.hasAuthorized("center.deviceData", request);
    request.setAttribute("deviceData_auth", deviceData_auth);

    boolean alarm_auth = AuthorizeCache.hasAuthorized("center.alarm", request);
    request.setAttribute("alarm_auth", alarm_auth);

    boolean replay_auth = AuthorizeCache.hasAuthorized("center.replay", request);
    request.setAttribute("replay_auth", replay_auth);

    boolean instruct_auth = AuthorizeCache.hasAuthorized("center.locate.instruct", request);
    request.setAttribute("instruct_auth", instruct_auth);

    boolean datalog_auth = AuthorizeCache.hasAuthorized("center.locate.datalog", request);
    request.setAttribute("datalog_auth", datalog_auth);

    boolean processOnce_auth = AuthorizeCache.hasAuthorized("center.alarm.processOnce", request);
    request.setAttribute("processOnce_auth", processOnce_auth);

    boolean processAll_auth = AuthorizeCache.hasAuthorized("center.alarm.processAll", request);
    request.setAttribute("processAll_auth", processAll_auth);
%>
<input id="vehileinfo_auth" type="hidden" value="${vehileinfo_auth}">
<input id="deviceData_auth" type="hidden" value="${deviceData_auth}">
<input id="alarm_auth" type="hidden" value="${alarm_auth}">
<input id="replay_auth" type="hidden" value="${replay_auth}">
<input id="instruct_auth" type="hidden" value="${instruct_auth}">
<input id="datalog_auth" type="hidden" value="${datalog_auth}">
<input id="httpRootPath" type="hidden"
       value="<%=request.getRequestURL().toString().replace(request.getServletPath(), "")%>">
<div id="locateLayout">
    <div position="left" title="操作面板" style="height: 100%;">
        <div class="mon-toolbar metro-gray">
            <div id="btnRefreshVehicle" class="mon-button">
                <div class="icon i-16-refresh"></div>
                <span>刷新</span>
            </div>
            <div class="prompt width-2x">
                关键字： <input id="txtVehicleFilter" type="text" placeholder="车牌号、设备号"/>
            </div>
            <div id="btnSearchVehicle" class="mon-button">
                <div class="icon i-16-search"></div>
                <span>查找</span>
            </div>
        </div>
        <div class="loc-vehicletree">
            <div class="mon-clear"></div>
            <div style="overflow: auto;">
                <ul id="groupVehicles"></ul>
            </div>
        </div>
        <div class="loc-controlpad">
            <div class="loc-titlebar">
                <div>
                    <a id="btnAllVehicles" href="#">全部(<span id="txtAllVehicles"></span>)
                    </a>
                </div>
                <div>
                    <a id="btnOlineVehicles" href="#">在线(<span
                            id="txtOnlineVehicles"></span>)
                    </a>
                </div>
                <div>
                    <a id="btnOfflineVehicles" href="#">离线(<span
                            id="txtOfflineVehicles"></span>)
                    </a>
                </div>
            </div>
            <div id="divGroupOperate">
                <div id="btnMessage" class="mon-button-x64">
                    <div id="txtMessageTotal"></div>
                    <div class="icon-v-x64 i-64-message"></div>
                    <div>未读消息</div>
                </div>
                <c:if test="${replay_auth}">
                    <div id="btnReplay" class="mon-button-x64">
                        <div class="icon-v-x64 i-64-replay"></div>
                        <span>轨迹回放</span>
                    </div>
                </c:if>
                <c:if test="${instruct_auth}">
                    <div id="btnInstruct" class="mon-button-x64">
                        <div class="icon-v-x64 i-64-instruct"></div>
                        <span>指令下发</span>
                    </div>
                </c:if>
                <div id="btnIntercom" class="mon-button-x64">
                    <div class="icon-v-x64 i-64-intercom"></div>
                    <span>集群对讲</span>
                </div>
                <c:if test="${alarm_auth}">
                    <div id="btnAlarm" class="mon-button-x64">
                        <div class="icon-v-x64 i-64-alarmoff"></div>
                        <span>报警信息</span>
                    </div>
                </c:if>
                <c:if test="${deviceData_auth}">
                    <div id="btnDataQuery" class="mon-button-x64">
                        <div class="icon-v-x64 i-64-querydata"></div>
                        <span>数据查询</span>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div position="center">
        <div id="divLocateAllMap">
            <div id="locateMap"
                 style="width: 100%; height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
        </div>
        <div id="divMapSpliter"
             style="height: 10px; width: 100%; background-color: #ccc; cursor: pointer;">
            <div
                    style="height: 10px; width: 100px; background-color: gray; margin: 0 auto;"></div>
        </div>
        <div id="divCenterBottom">
            <div id="gridLatests"></div>
        </div>
    </div>
    <div class="mon-clear"></div>
    <div id="dialogHandleAlarms" class="display-none">
        <c:if test="${processOnce_auth || processAll_auth}">
            <div class="mon-toolbar metro-gray mon-nowrap">
                <c:if test="${processOnce_auth}">
                    <div id="btnHandleOnceAlarm" class="mon-button">
                        <div class="icon i-16-todoonce"></div>
                        <span>处理报警</span>
                    </div>
                </c:if>
                <c:if test="${processAll_auth}">
                    <div id="btnHandleAllAlarm" class="mon-button">
                        <div class="icon i-16-todolist"></div>
                        <span>全部处理</span>
                    </div>
                </c:if>
                <!-- <div id="btnSpeaker" class="mon-button">
                    <div class="icon i-16-sound"></div>
                    <span>声音提示:开</span>
                </div>
                <div id="btnSpeakerFile" class="mon-button">
                    <div class="icon i-16-music"></div>
                    <span>声音文件</span>
                </div> -->
            </div>
        </c:if>
        <div id="gridUnhandledAlarms"></div>
    </div>
    <div id="dialogUnreadMessage" class="display-none">
        <div id="unreadPages">
            <div tabid="multimediaEventReprotPage" title="多媒体事件报告"
                 lselected="true">
                <div id="gridMultimediaEventReprot"></div>
            </div>
            <div tabid="deviceEventReprotPage" title="终端事件报告">
                <div id="gridDeviceEventReport"></div>
            </div>
            <div tabid="deviceUpgradeResultReprotPage" title="终端升级报告">
                <div id="gridDeviceUpgradeResultReport"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>