<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>首页</title>

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
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/home/home.js"></script>
    <style type="text/css">
        .box {
            width: 650px;
            height: 600px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -300px 0 0 -310px;
            color: white;
        }

        .box a {
            text-decoration: underline;
            color: white;
        }

        .counter {
            width: 300px;
            height: 100px;
            margin: 3px;
            display: inline-block;
            vertical-align: middle;
            position: relative;
        }

        .counter .icon {
            height: 100%;
            width: 80px;
            display: inline-block;
            vertical-align: top;
            position: relative;
        }

        .counter .image64 {
            width: 64px;
            height: 64px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -42px 0 0 -32px;
        }

        .counter .content {
            padding: 5px;
            display: inline-block;
            vertical-align: middle;
        }

        .counter .footer {
            width: 100%;
            height: 20px;
            line-height: 20px;
            background-color: rgba(255, 255, 255, 0.5);
            position: absolute;
            left: 0px;
            bottom: 0px;
            padding-left: 10px;
        }

        .chart {
            width: 607px;
            height: 320px;
            margin: 3px;
        }
    </style>
</head>
<body>
<div class="box">
    <div class="counter metro-blue">
        <div class="icon">
            <div class="image64 i-64-company"></div>
        </div>
        <div class="content">
            <table>
                <tr>
                    <td>企业总数：</td>
                    <td>${companyServiceOverview.total}</td>
                </tr>
                <tr>
                    <td><a id="btnCompanyServiceExpired" href="#">服务已过期：</a></td>
                    <td>${companyServiceOverview.expired}</td>
                </tr>
                <tr>
                    <td><a id="btnCompanyServiceExpired30" href="#">30天内过期：</a></td>
                    <td>${companyServiceOverview.expired30}</td>
                </tr>
                <tr>
                    <td><a id="btnCompanyServiceExpired15" href="#">15天内过期：</a></td>
                    <td>${companyServiceOverview.expired15}</td>
                </tr>
                <tr>
                    <td><a id="btnCompanyServiceExpired7" href="#">&nbsp;&nbsp;7天内过期：</a></td>
                    <td>${companyServiceOverview.expired7}</td>
                </tr>
            </table>
        </div>
        <div class="mon-clear footer">
            <b>企业服务</b>
        </div>
    </div>
    <div class="counter metro-green">
        <div class="icon">
            <div class="image64 i-64-vehicleservice"></div>
        </div>
        <div class="content">
            <table>
                <tr>
                    <td>车辆总数：</td>
                    <td>${vehicleServiceOverview.total}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleServiceExpired" href="#">服务已过期：</a></td>
                    <td>${vehicleServiceOverview.expired}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleServiceExpired30" href="#">30天内过期：</a></td>
                    <td>${vehicleServiceOverview.expired30}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleServiceExpired15" href="#">15天内过期：</a></td>
                    <td>${vehicleServiceOverview.expired15}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleServiceExpired7" href="#">&nbsp;&nbsp;7天内过期：</a></td>
                    <td>${vehicleServiceOverview.expired7}</td>
                </tr>
            </table>
        </div>
        <div class="mon-clear footer">
            <b>车辆服务</b>
        </div>
    </div>
    <div class="counter metro-orange">
        <div class="icon">
            <div class="image64 i-64-vehiclerepair"></div>
        </div>
        <div class="content">
            <table>
                <tr>
                    <td>车辆总数：</td>
                    <td>${vehicleMaintainOverview.total}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleMaintainExpired" href="#">保养已过期：</a></td>
                    <td>${vehicleMaintainOverview.expired}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleMaintainExpired30" href="#">30天内过期：</a></td>
                    <td>${vehicleMaintainOverview.expired30}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleMaintainExpired15" href="#">15天内过期：</a></td>
                    <td>${vehicleMaintainOverview.expired15}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleMaintainExpired7" href="#">&nbsp;&nbsp;7天内过期：</a></td>
                    <td>${vehicleMaintainOverview.expired7}</td>
                </tr>
            </table>
        </div>
        <div class="mon-clear footer">
            <b>车辆保养</b>
        </div>
    </div>
    <div class="counter metro-pink">
        <div class="icon">
            <div class="image64 i-64-vehiclecheck"></div>
        </div>
        <div class="content">
            <table>
                <tr>
                    <td>车辆总数：</td>
                    <td>${vehicleCheckOverview.total}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleAnnualSurveyExpired" href="#">年检已过期：</a></td>
                    <td>${vehicleCheckOverview.expired}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleAnnualSurveyExpired30" href="#">30天内过期：</a></td>
                    <td>${vehicleCheckOverview.expired30}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleAnnualSurveyExpired15" href="#">15天内过期：</a></td>
                    <td>${vehicleCheckOverview.expired15}</td>
                </tr>
                <tr>
                    <td><a id="btnVehicleAnnualSurveyExpired7" href="#">&nbsp;&nbsp;7天内过期：</a></td>
                    <td>${vehicleCheckOverview.expired7}</td>
                </tr>
            </table>
        </div>
        <div class="mon-clear footer">
            <b>车辆年检</b>
        </div>
    </div>
    <input id="vehicleOnline" type="hidden"
           value="${vehicleStatusOverview.online}"> <input
        id="vehicleOffline" type="hidden"
        value="${vehicleStatusOverview.offline}"> <input
        id="vehicleAccon" type="hidden"
        value="${vehicleStatusOverview.accon}"> <input
        id="vehicleAccoff" type="hidden"
        value="${vehicleStatusOverview.accoff}"><input
        id="vehicleAlarm" type="hidden"
        value="${vehicleStatusOverview.alarm}">
    <div id="chartVehicles" class="chart"></div>
</div>
</body>
</html>