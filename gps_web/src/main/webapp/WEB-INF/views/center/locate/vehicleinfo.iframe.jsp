<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript">
        $(function () {
            $("#pagesVehicleinfo").ligerTab();
        })
    </script>
    <style type="text/css">
        .table-vehileinfo {
            border-collapse: collapse;
            border-spacing: 0;
            border-left: 1px solid #888;
            border-top: 1px solid #888;
            width: 100%;
        }

        .table-vehileinfo thead, .table-vehileinfo td {
            border-right: 1px solid #888;
            border-bottom: 1px solid #888;
            padding: 5px 15px;
        }

        .table-vehileinfo thead {
            font-weight: bold;
            background: #ccc;
        }
    </style>
</head>
<body>
<div id="pagesVehicleinfo"
     style="width: 100%; overflow: hidden; border: 1px solid #A3C0E8;">
    <div title="车辆资料" lselected="true">
        <table class="mon-layout-table">
            <tr>
                <td style="width: 400px;">
                    <div class="display-label">车牌号码：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.plateNumber}"/>
                    </div>
                    <div class="display-label">车牌颜色：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.plateColor}"/>
                    </div>
                    <div class="display-label">车辆颜色：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.vehicleColor}"/>
                    </div>
                    <div class="display-label">所属行政区域：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.adminArea}"/>
                    </div>
                    <div class="display-label">载运类型：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.carryType}"/>
                    </div>
                    <div class="display-label">车辆类型：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.vehicleType}"/>
                    </div>
                    <div class="display-label">车辆电压：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.vehicleVoltage}"/>
                    </div>
                </td>
                <td style="width: 400px;">
                    <div class="display-label">设备号：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.deviceNumber}"/>
                    </div>
                    <div class="display-label">车队：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.motorcade}"/>
                    </div>
                    <div class="display-label">初始里程：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.initialMileage}"/>
                    </div>
                    <div class="display-label">百公里油耗：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.initialMileage}"/>
                    </div>
                    <div class="display-label">使用年限：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.usefulLife}"/>
                    </div>
                    <div class="display-label">安装日期：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.installDate}"/>
                    </div>
                    <div class="display-label">年检日期：</div>
                    <div class="display-field">
                        <c:out value="${vehicle.baseInfo.annualSurveyDate}"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="display-label">备注：</div>
                    <div class="display-field width-6x">
                        <c:out value="${vehicle.baseInfo.remark}"/>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div title="车主资料">
        <table class="table-vehileinfo">
            <thead>
            <tr>
                <td>姓名</td>
                <td>电话</td>
                <td>证件类型</td>
                <td>证件编号</td>
                <td>邮箱</td>
                <td>备注</td>
            </tr>
            </thead>
            <c:forEach var="owner" items="${vehicle.owners}">
                <tr>
                    <td>${owner.ownerName }</td>
                    <td>${owner.phone }</td>
                    <td>${owner.idType }</td>
                    <td>${owner.idNumber }</td>
                    <td>${owner.email }</td>
                    <td>${owner.remark }</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div title="驾驶员资料">
        <table class="table-vehileinfo">
            <thead>
            <tr>
                <td>姓名</td>
                <td>性别</td>
                <td>电话</td>
                <td>证件类型</td>
                <td>证件编号</td>
                <td>驾驶证号</td>
                <td>驾驶证有效日期</td>
                <td>备注</td>
            </tr>
            </thead>
            <c:forEach var="driver" items="${vehicle.drivers}">
                <tr>
                    <td>${driver.name }</td>
                    <td>${driver.sex }</td>
                    <td>${driver.phone }</td>
                    <td>${driver.idType }</td>
                    <td>${driver.idNumber }</td>
                    <td>${driver.drivingLicenseNumber }</td>
                    <td>${driver.drivingLicenseExpiryDate }</td>
                    <td>${driver.remark }</td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
</body>
</html>