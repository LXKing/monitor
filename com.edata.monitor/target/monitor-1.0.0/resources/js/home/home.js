/**
 * 首页
 */
window.home = {
    companyServiceExpired: function (days) {
        var url = 'overview/companyServiceExpired.form';
        var op = {
            url: url,
            urlParms: {
                days: days
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '企业服务到期',
            onLoaded: function () {
                home.dialog.frame.window.companyServiceExpired.query(days);
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        if (days > 0) {
            op.title += days + '天内'
        }
        home.dialog = $.ligerDialog.open(op);
    },
    vehicleServiceExpired: function (days) {
        var url = 'overview/vehicleServiceExpired.form';
        var op = {
            url: url,
            urlParms: {
                days: days
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '车辆服务到期',
            onLoaded: function () {
                home.dialog.frame.window.vehicleServiceExpired.query(days);
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        if (days > 0) {
            op.title += days + '天内'
        }
        home.dialog = $.ligerDialog.open(op);
    },
    vehicleMaintainExpired: function (days) {
        var url = 'overview/vehicleMaintainExpired.form';
        var op = {
            url: url,
            urlParms: {
                days: days
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '车辆保养到期',
            onLoaded: function () {
                home.dialog.frame.window.vehicleMaintainExpired.query(days);
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        if (days > 0) {
            op.title += days + '天内'
        }
        home.dialog = $.ligerDialog.open(op);
    },
    vehicleAnnualSurveyExpired: function (days) {
        var url = 'overview/vehicleAnnualSurveyExpired.form';
        var op = {
            url: url,
            urlParms: {
                days: days
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '车辆年检到期',
            onLoaded: function () {
                home.dialog.frame.window.vehicleAnnualSurveyExpired.query(days);
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        if (days > 0) {
            op.title += days + '天内'
        }
        home.dialog = $.ligerDialog.open(op);
    }
};
$(function () {
    $("#layout").ligerLayout({
        space: 0,
        topHeight: 80,
        allowTopResize: false
    });
    $('#btnHome').addClass("select");
    var vehicleOnline = parseInt($('#vehicleOnline').val());
    var vehicleOffline = parseInt($('#vehicleOffline').val());
    var vehicleAccon = parseInt($('#vehicleAccon').val());
    var vehicleAccoff = parseInt($('#vehicleAccoff').val());
    var vehicleAlarm = parseInt($('#vehicleAlarm').val());
    $('#chartVehicles').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: ['在线', '离线', '启动', '熄火', '报警']
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            valueSuffix: ' 辆'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '车辆',
            data: [{
                color: '#00FF00',
                y: vehicleOnline
            }, {
                color: '#FF00FF',
                y: vehicleOffline
            }, {
                color: '#0F0455',
                y: vehicleAccon
            }, {
                color: '#993432',
                y: vehicleAccoff
            }, {
                color: '#ff0000',
                y: vehicleAlarm
            }]
        }]
    });
    $('#btnCompanyServiceExpired').click(function () {
        home.companyServiceExpired(0);
    });
    $('#btnCompanyServiceExpired30').click(function () {
        home.companyServiceExpired(30);
    });
    $('#btnCompanyServiceExpired15').click(function () {
        home.companyServiceExpired(15);
    });
    $('#btnCompanyServiceExpired7').click(function () {
        home.companyServiceExpired(7);
    });
    $('#btnVehicleServiceExpired').click(function () {
        home.vehicleServiceExpired(0);
    });
    $('#btnVehicleServiceExpired30').click(function () {
        home.vehicleServiceExpired(30);
    });
    $('#btnVehicleServiceExpired15').click(function () {
        home.vehicleServiceExpired(15);
    });
    $('#btnVehicleServiceExpired7').click(function () {
        home.vehicleServiceExpired(7);
    });
    $('#btnVehicleMaintainExpired').click(function () {
        home.vehicleMaintainExpired(0);
    });
    $('#btnVehicleMaintainExpired30').click(function () {
        home.vehicleMaintainExpired(30);
    });
    $('#btnVehicleMaintainExpired15').click(function () {
        home.vehicleMaintainExpired(15);
    });
    $('#btnVehicleMaintainExpired7').click(function () {
        home.vehicleMaintainExpired(7);
    });
    $('#btnVehicleAnnualSurveyExpired').click(function () {
        home.vehicleAnnualSurveyExpired(0);
    });
    $('#btnVehicleAnnualSurveyExpired30').click(function () {
        home.vehicleAnnualSurveyExpired(30);
    });
    $('#btnVehicleAnnualSurveyExpired15').click(function () {
        home.vehicleAnnualSurveyExpired(15);
    });
    $('#btnVehicleAnnualSurveyExpired7').click(function () {
        home.vehicleAnnualSurveyExpired(7);
    });
});