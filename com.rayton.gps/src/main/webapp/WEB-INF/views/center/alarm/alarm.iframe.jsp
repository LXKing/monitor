<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/gpsparser.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/alarm.js"></script>
</head>
<body>
<div id="alarmLayout">
    <div position="right" title="操作面板" style="height: 100%; padding: 2px;">
        <input id="txtDeviceNumber" type="hidden"
               value="<%=request.getParameter("deviceNumber")%>"/> <input
            id="txtPlateNumber" type="hidden"
            value="<%=request.getParameter("plateNumber")%>"/>
        <div id="divAlarmMapControl">
            <!--<div>
					车牌号码： <b id="txtPlateNumber"><%=request.getParameter("plateNumber")%></b>
				 <div id="btnAlarmFindVehicle" class="mon-button">
						<div class="mon-icon-h-x16 i-16-search"></div>
						<span>查找</span>
					</div> 
				</div>-->
            <div id="alarmPages" class="liger-tab">
                <div tabid="pageUntreatedAlarms" title="未处理" lselected="true">
                    <div class="mon-toolbar metro-gray mon-nowrap">
                        <div id="btnRefreshUntreateAlarm" class="mon-button">
                            <div class="icon i-16-refresh"></div>
                            <span>刷新</span>
                        </div>
                        <div class="spliter"></div>
                        <div id="btnProcessOnceAlarm" class="mon-button">
                            <div class="icon i-16-todoonce"></div>
                            <span>处理报警</span>
                        </div>
                        <div id="btnProcessAllAlarm" class="mon-button">
                            <div class="icon i-16-todolist"></div>
                            <span>全部处理</span>
                        </div>
                    </div>
                    <div id="gridUntreatedAlarms"></div>
                </div>
                <div tabid="pageProcessedAlarms" title="已处理">
                    <div id="divSelectTimespan" class="editor-field">
                        <input id="txtStartTime" type="text"/> <input id="txtEndTime"
                                                                      type="text"/>
                    </div>
                    <div class="mon-toolbar metro-gray mon-nowrap">
                        <div id="btnAlarmDownloadTracks" class="mon-button">
                            <div class="icon i-16-download"></div>
                            <span>载入报警</span>
                        </div>
                    </div>
                    <div id="gridProcessedAlarms"></div>
                </div>
            </div>
        </div>
    </div>

    <div position="center" style="width: 100%; height: 100%;">
        <div id="alarmMap"
             style="width: 100%; height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
    </div>

    <div id="dialogAlarmSearchVehicle" class="display-none">
        <div style="margin-top: -5px; margin-right: 6px;">
            <div class="mon-toolbar metro-gray">
                <div class="prompt">
                    关键字：<input id="txtAlarmVehicleFilter" type="text"
                               placeholder="车牌号、设备号"/>
                </div>
                <div id="btnAlarmSearchVehicle" class="mon-button">
                    <div class="icon i-16-search"></div>
                    <span>查询</span>
                </div>
                <div class="spliter"></div>
                <div id="btnAlarmSelectVehicle" class="mon-button">
                    <div class="icon i-16-checked"></div>
                    <span>选择</span>
                </div>
            </div>
            <div class="mon-clear"></div>
            <div id="gridAlarmSearchVehicle"></div>
        </div>
    </div>
</div>
</body>
</html>