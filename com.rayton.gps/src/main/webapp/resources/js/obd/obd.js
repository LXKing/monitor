/**
 * OBD状态
 */
window.obd = {
    startDate: null,
    endDate: null,
    device: null,
    gridFlows: null,
    gridFlowsParams: {
        number: '',
        start: new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm:ss'),
        end: new Date().toDateTimeString('yyyy-MM-dd hh:mm:ss')
    },
    gridDevices: null,
    gridDevicesParams: {
        filter: ''
    },
    dialogSearchDevices: null,
    loadDeviceStatus: function (number) {
        $.post('./common/device/status', {
            number: number
        }, function (data) {
            var div = $('#divStatus');
            if (data.debugging === true) {
                $('#txtDebugging').show();
            } else {
                $('#txtDebugging').hide();
            }
            if (data.upgrading == true) {
                $('#txtPerver').text(data.preVer);
                $('#txtUpgradeStart').text(data.upgradeStart);
                $('#txtCurver').text(data.curVer);
                $('#txtUpgradeEnd').text(data.upgradeEnd);

                $('#txtUpgrading').show();
            } else {
                $('#txtUpgrading').hide();
            }
            if (data.matching == true) {
                $('#txtMatchTime').text(data.matchTime);
                $('#txtMatchResult').text(data.matchResult);
                $('#txtMatching').show();
            } else {
                $('#txtMatching').hide();
            }
            if (data.sleeping === true) {
                $('#txtSleeping').show();
            } else {
                $('#txtSleeping').hide();
            }
            if (data.repairing === true) {
                $('#txtRepairing').show();
            } else {
                $('#txtRepairing').hide();
            }

        });
    },
    chart: null,
    analyze: function (data) {
        var labels = [];
        var rpm = {
            yAxis: 0,
            name: '转速',
            data: []
        };
        var vss = {
            yAxis: 1,
            name: '车速',
            data: []
        };
        var ect = {
            yAxis: 2,
            name: '水温',
            data: []
        };
        var iat = {
            yAxis: 2,
            name: '进气温度',
            data: []
        };
        var bv = {
            yAxis: 3,
            name: '电压',
            data: []
        };
        for (var i = 0; i < data.total; i++) {
            var item = data.rows[i];

            var time = item.time.toDate();

            labels.push(time.toDateTimeString('dd hh:mm:ss'));

            vss.data.push(item.vss);
            ect.data.push(item.ect);
            rpm.data.push(item.rpm);
            bv.data.push(item.bv);
            iat.data.push(item.iat);
        }

        $('#chartContainer').highcharts({
            chart: {
                type: 'spline',
                zoomType: 'x'
            },
            title: {
                text: '状态分析'
            },

            xAxis: {
                categories: labels,
                labels: {
                    rotation: 90
                }
            },
            yAxis: [{
                min: 0,
                title: {
                    style: {
                        color: '#000000'
                    },
                    text: '转速'
                },
                labels: {
                    style: {
                        color: '#000000'
                    }
                },
                opposite: true

            }, {
                min: 0,
                title: {
                    style: {
                        color: '#4572A7'
                    },
                    text: '车速'
                },
                labels: {
                    style: {
                        color: '#4572A7'
                    }
                },
                opposite: false

            }, {
                min: 0,
                title: {
                    style: {
                        color: '#AA4643'
                    },
                    text: '水温'
                },
                labels: {
                    style: {
                        color: '#AA4643'
                    }
                },
                opposite: false
            }, {
                min: 0,
                title: {
                    style: {
                        color: '#89A54E'
                    },
                    text: '电压'
                },
                labels: {
                    style: {
                        color: '#89A54E'
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            credits: {
                enabled: false
            },
            series: [vss, rpm, bv, ect, iat]
        });

    }
};
$(function () {
    $("#layout").ligerLayout({
        topHeight: 80,
        rightWidth: 300,
        allowTopResize: false
    });
    $('#btnObd').addClass('select');
    var number = $('#txtDeviceNumber').text();
    if (number.length > 0) {
        $.post('./common/device/info', {
            number: number
        }, function (data) {
            obd.device = data;
            obd.gridFlowsParams.number = data.number;
            common.setCookie('device_number', data.number);
            common.setCookie('device_name', data.name);
            $('#txtDeviceNumber').text(data.number);
            $('#txtDeviceName').text(data.name);
        });
        obd.loadDeviceStatus(number);
    } else {
        var number = common.getCookie('device_number');
        var name = common.getCookie('device_name');
        if (number && name) {
            obd.device = {
                number: number,
                name: name
            };
            obd.gridFlowsParams.number = number;
            $('#txtDeviceNumber').text(number);
            $('#txtDeviceName').text(name);
        }
    }
    var startTime = common.getCookie('time_start');
    startTime = startTime == null ? '' : startTime;

    startTime = startTime.length > 0 ? startTime.toDate().toDateTimeString(
        'yyyy-MM-dd hh:mm') : new Date().addDays(-1).toDateTimeString(
        'yyyy-MM-dd hh:mm');
    obd.gridFlowsParams.start = startTime + ':00';

    var endTime = common.getCookie('time_end');
    endTime = endTime == null ? '' : endTime;

    endTime = endTime.length > 0 ? endTime.toDate().toDateTimeString(
        'yyyy-MM-dd hh:mm') : new Date()
        .toDateTimeString('yyyy-MM-dd hh:mm');
    obd.gridFlowsParams.end = endTime + ':00';

    obd.startDate = $("#txtStartDate").ligerDateEditor(
        {
            showTime: true,
            format: 'yyyy-MM-dd hh:mm',
            label: '开始时间',
            labelWidth: 60,
            labelAlign: 'left',
            initValue: startTime,
            onChangeDate: function (value) {
                var time = (value + ':00').toDate().toDateTimeString(
                    'yyyy-MM-dd hh:mm:ss');
                common.setCookie('time_start', time);
                obd.gridFlowsParams.start = time;
            }
        });
    obd.endDate = $("#txtEndDate").ligerDateEditor(
        {
            showTime: true,
            format: 'yyyy-MM-dd hh:mm',
            label: '结束时间',
            labelWidth: 60,
            labelAlign: 'left',
            initValue: endTime,
            onChangeDate: function (value) {
                var time = (value + ':00').toDate().toDateTimeString(
                    'yyyy-MM-dd hh:mm:ss');
                common.setCookie('time_end', time);
                obd.gridFlowsParams.end = time;
            }
        });

    $('#btnQuery').click(function () {
        if (!obd.device) {
            $.ligerDialog.warn('未发现设备信息！');
            return;
        }
        if (obd.gridFlows)
            obd.gridFlows.reload();
    });

    $('#btnSelectDevice').click(function () {
        if (!obd.gridDevices)
            return;
        var row = obd.gridDevices.getSelected();
        if (row) {
            obd.device = {
                number: row.number,
                name: row.name
            };
            common.setCookie('device_number', row.number);
            common.setCookie('device_name', row.name);
            obd.gridFlowsParams.number = row.number;
            $('#txtDeviceNumber').text(row.number);
            $('#txtDeviceName').text(row.name);
            if (obd.dialogSearchDevices)
                obd.dialogSearchDevices.hide();
            obd.loadDeviceStatus(row.number);
        }

    });

    $('#btnSearchDevices').click(function () {
        obd.gridDevicesParams.filter = $('#txtDeviceFilter').val();

        var op = {
            target: $('#dialogSearchDevices'),
            width: 800,
            height: 410,
            title: '选择设备',
        };
        obd.dialogSearchDevices = $.ligerDialog.open(op);

        if (!obd.gridDevices) {
            obd.gridDevices = $("#gridDevices").ligerGrid({
                columns: [{
                    display: '设备名',
                    name: 'name'
                }, {
                    display: '设备号',
                    name: 'number'
                }, {
                    display: 'SIM卡',
                    name: 'sim'
                }, {
                    display: '服务开始日期',
                    name: 'serviceStartTime',
                    type: 'date'
                }, {
                    display: '服务结束日期',
                    name: 'serviceEndTime',
                    type: 'date'
                }, {
                    display: '入网时间',
                    name: 'createTime',
                    type: 'date'
                }],
                pageSize: 30,
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                root: 'rows',
                record: 'total',
                url: './common/device/search',
                parms: obd.gridDevicesParams,
                width: '99%',
                height: 300
            });
        } else {
            obd.gridDevices.reload();
        }
    });
    obd.gridFlows = $("#gridFlows").ligerGrid({
        columns: [{
            display: '时间',
            name: 'time',
            width: 130,
            frozen: true
        }, {
            display: '车速',
            name: 'vss',
            width: 50
        }, {
            display: '里程',
            name: 'mileage',
            width: 50
        }, {
            display: '电压',
            name: 'bv',
            width: 50
        }, {
            display: '转速',
            name: 'rpm',
            width: 50
        }, {
            display: '水温',
            name: 'ect',
            width: 50
        }, {
            display: '运行时长',
            name: 'runtm',
            width: 50
        }, {
            display: '机油温度',
            name: 'eot',
            width: 50
        }, {
            display: '瞬时油耗',
            name: 'ifc',
            width: 50
        }, {
            display: '油箱油量',
            name: 'ot',
            width: 50
        }, {
            display: '进气温度',
            name: 'iat',
            width: 50
        }, {
            display: '环境温度',
            name: 'et',
            width: 50
        }, {
            display: '空气流量',
            name: 'maf',
            width: 50
        }, {
            display: '大气压力',
            name: 'ap',
            width: 50
        }, {
            display: '计算负荷',
            name: 'loadpct',
            width: 50
        }, {
            display: '进气歧管绝对压力',
            name: 'map',
            width: 100
        }, {
            display: '左前轮胎压力',
            name: 'lftp',
            width: 80
        }, {
            display: '左后轮胎压力',
            name: 'altp',
            width: 80
        }, {
            display: '右后轮胎压力',
            name: 'rrtp',
            width: 80
        }, {
            display: '右前轮胎压力',
            name: 'rftp',
            width: 80
        }, {
            display: '亮故障灯里程',
            name: 'mord',
            width: 80
        }, {
            display: '点火提前角',
            name: 'iaa',
            width: 80
        }, {
            display: '长期燃油修正B1',
            name: 'longftb1',
            width: 100
        }, {
            display: '短期燃油修正B1',
            name: 'shrtftb1',
            width: 100
        }, {
            display: '短期燃油修正B1S1',
            name: 'shrtftb1s1',
            width: 110
        }, {
            display: '短期燃油修正B1S2',
            name: 'shrtftb1s2',
            width: 110
        }, {
            display: '故障数量',
            name: 'faults',
            width: 50
        }, {
            display: '节气门开度',
            name: 'tp',
            width: 80
        }, {
            display: '节气门绝对位置B',
            name: 'tpalb',
            width: 110
        }, {
            display: '节气门绝对位置C',
            name: 'tpalc',
            width: 110
        }, {
            display: '燃油压力',
            name: 'frp',
            width: 50
        }, {
            display: '燃油状态1',
            name: 'fuelsys1',
            width: 80
        }, {
            display: '燃油状态2',
            name: 'fuelsys2',
            width: 80
        }, {
            display: '氧传感器电压B1S1',
            name: 'o2sb1s1',
            width: 110
        }, {
            display: '氧传感器电压B1S2',
            name: 'o2sb1s2',
            width: 110
        }, {
            display: '油门踏板位置D',
            name: 'ppsd',
            width: 100
        }, {
            display: '油门踏板位置E',
            name: 'ppse',
            width: 100
        }, {
            display: '蒸发汽温度B1S1',
            name: 'vaptb1s1',
            width: 100
        }, {
            display: '蒸发汽温度B1S2',
            name: 'vaptb1s2',
            width: 100
        }],
        usePager: false,
        root: 'rows',
        record: 'total',
        url: './common/flow/load',
        parms: obd.gridFlowsParams,
        height: '100%',
        onSuccess: obd.analyze
    });
});