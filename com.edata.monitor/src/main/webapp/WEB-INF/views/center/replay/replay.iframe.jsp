<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/gpsparser.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/webmap.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/center/replay.js"></script>
    <style>
        #gridReplayTrack tr:hover, #gridReplayPark tr:hover, #gridReplayAlarm tr:hover {
            background-color: #a8d8eb;
            cursor: pointer;
        }

        #gridReplayTrack div, #gridReplayPark div {
            padding: 2px;
        }

        .tr-selected {
            background-color: #DCF8A8;
        }

        .content {
            overflow: auto;
        }
    </style>
</head>
<body>
<div id="replayLayout">
    <div position="right" title="操作面板" style="height: 100%; padding: 2px;">
        <input id="txtDeviceNumber" type="hidden"
               value="<%=request.getParameter("deviceNumber")%>"/> <input
            id="txtPlateNumber" type="hidden"
            value="<%=request.getParameter("plateNumber")%>"/>
        <div id="divReplayMapControl">
            <fieldset id="group1">
                <legend>参数设置</legend>
                <!-- <div>
						车牌号码： <b id="txtPlateNumber"><%=request.getParameter("plateNumber")%></b>
						<div id="btnReplayFindVehicle" class="mon-button">
							<div class="mon-icon-h-x16 i-16-search"></div>
							<span>查找</span>
						</div> 
					</div>-->
                <div class="editor-field">
                    <input id="txtStartTime" type="text"/> <input id="txtEndTime"
                                                                  type="text"/>
                </div>
                <div id="divProcess"
                     style="width: 100%; height: 3px; background-color: white; margin-top: 3px">
                    <div class="metro-blue" style="height: 3px; width: 0px;"></div>
                </div>
                <input id="txtProcess" type="range" name="points" min="1" max="100"
                       value="20" step="1"
                       style="width: 100%; height: 3px; display: none;"/>
                <div class="mon-toolbar metro-gray mon-nowrap">
                    <div id="btnReplayDownloadTracks" class="mon-button">
                        <div class="icon i-16-download"></div>
                        <span>载入轨迹</span>
                    </div>
                    <div class="spliter"></div>
                    <div id="btnRelayStart" class="mon-button">
                        <div class="icon i-16-play"></div>
                        <span>播放</span>
                    </div>
                    <div id="btnReplayPause" class="mon-slice">
                        <div class="icon i-16-pause"></div>
                        <span>暂停</span>
                    </div>
                    <div id="btnReplayStop" class="mon-slice">
                        <div class="icon i-16-stop"></div>
                        <span>停止</span>
                    </div>
                </div>

            </fieldset>


            <fieldset id="group2">
                <legend>统计</legend>
                <table>
                    <tr>
                        <td style="width: 225px;">
                            <div class="display-label">
                                行驶时长：<span id="txtRunTime"></span>
                            </div>
                        </td>
                        <td>
                            <div class="display-label">
                                行驶里程:<span id="txtRunMileage"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="display-label">
                                行驶油耗：<span id="txtRunOil"></span>
                            </div>
                        </td>
                        <td>
                            <div class="display-label">
                                平均速度:<span id="txtAverageSpeed"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="display-label">
                                平均油耗：<span id="txtAverageOil"></span>
                            </div>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <fieldset id="group3">
                <legend>速度</legend>
                <div id="speedChart" style="width: 100%; height: 150px;"></div>
            </fieldset>

            <fieldset id="group4">
                <legend>轨迹</legend>
                <div id="repalyPages" class="liger-tab">
                    <div tabid="pageReplayTrip" title="行程" lselected="true">
                        <div class="content">
                            <table id="gridReplayTrack" style="width: 100%;">
                            </table>
                            <div class="mon-clear"></div>
                        </div>
                    </div>
                    <div tabid="pageReplayStop" title="停车">
                        <div class="content">
                            <table id="gridReplayPark" style="width: 100%;">
                            </table>
                        </div>
                    </div>
                    <div tabid="pageReplayAlarm" title="报警">
                        <div class="content">
                            <table id="gridReplayAlarm" style="width: 100%;">
                            </table>
                        </div>
                    </div>
            </fieldset>
        </div>
    </div>
    <div position="center" style="width: 100%; height: 100%;">
        <div id="replayMap"
             style="width: 100%; height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;"></div>
    </div>
</div>
</body>
</html>