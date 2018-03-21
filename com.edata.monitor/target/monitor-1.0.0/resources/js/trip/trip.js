/**
 * 行程报告
 */
window.trip = {
    startDate: null,
    endDate: null,
    gridTrips: null,
    gridTripsParams: {
        number: '',
        start: new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm:ss'),
        end: new Date().toDateTimeString('yyyy-MM-dd hh:mm:ss')
    },
    gridDevices: null,
    gridDevicesParams: {
        filter: ''
    },
    dialogSearchDevices: null,
    chart: null,
    total: {
        mileages: 0,
        speedUps: 0,
        breaks: 0,
        overSpeeds: 0,
        fuels: 0,
        durations: 0,
        reset: function () {
            trip.total.mileages = 0;
            trip.total.speedUps = 0;
            trip.total.breaks = 0;
            trip.total.overSpeeds = 0;
            trip.total.fuels = 0;
            trip.total.durations = 0;
        }
    },

    labels: [],// X轴标签
    fuel: [],// 油耗
    mileage: [],// 里程
    runtime: [],// 行车时长

    analyze: function (data) {
        var isSameDay = trip.startDate.getValue().getShortDate().getTime() == trip.endDate
            .getValue().getShortDate().getTime();

        var sumFuel = {};
        var sumMileage = {};
        var sumRuntime = {};
        trip.fuel = [];
        trip.mileage = [];
        trip.runtime = [];
        trip.labels = [];
        trip.total.reset();
        for (var i = 0; i < data.rows.length; i++) {
            var item = data.rows[i];

            trip.total.mileages += item.mileage;
            trip.total.speedUps += item.speedUp;
            trip.total.breaks += item.breaks;
            trip.total.overSpeeds += item.overSpeed
            trip.total.fuels += item.totalOil;

            var endTime = item.endTime.toDate();
            var startTime = item.startTime.toDate();

            var label = (startTime.getHours() + ':' + startTime.getMinutes()
                + '-' + endTime.getHours() + ':' + endTime.getMinutes());

            trip.total.durations += Math.round((endTime - startTime) * 0.001);
            var duration = Math.round((endTime - startTime) / 1000 / 60);

            if (isSameDay) {
                trip.labels.push(label);
                trip.fuel.push(item.totalOil);
                trip.mileage.push(item.mileage);
                trip.runtime.push(duration);
            } else {
                var mmdd = (endTime.getMonth() + 1) + '-' + endTime.getDate();
                if (sumFuel.hasOwnProperty(mmdd))
                    sumFuel[mmdd] += item.totalOil;
                else
                    sumFuel[mmdd] = item.totalOil;

                if (sumMileage.hasOwnProperty(mmdd))
                    sumMileage[mmdd] += item.mileage;
                else
                    sumMileage[mmdd] = item.mileage;

                if (sumRuntime.hasOwnProperty(mmdd))
                    sumRuntime[mmdd] += duration;
                else
                    sumRuntime[mmdd] = duration;
            }
        }
        trip.total.mileages = trip.total.mileages * 0.001;
        var avgFuel = trip.total.fuels
            / (trip.total.mileages == 0 ? 1 : trip.total.mileages) * 100;
        $('#txtMileage').text(common.round(trip.total.mileages, 1));
        $('#txtFuel').text(common.round(trip.total.fuels, 2));
        $('#txtAvgFuel').text(common.round(avgFuel, 2));

        var timesanp = trip.total.durations / 60 / 60;
        var hours = Math.floor(timesanp);
        timesanp -= hours;
        var minutes = Math.floor(timesanp * 60);
        $('#txtHours').text(hours);
        $('#txtMinutes').text(minutes);
        $('#txtTimes').text(data.rows.length);

        var price = parseFloat($('#txtPrice').val());
        var costs = common.round(price * trip.total.fuels, 2);
        $('#txtCost').text(costs);

        if (isSameDay === false) {
            for (var key in sumFuel) {
                trip.labels.push(key);
                trip.fuel.push(common.round(sumFuel[key], 2));
                trip.mileage.push(sumMileage[key]);
                trip.runtime.push(sumRuntime[key]);
            }
        }

        trip.resetChart();
    },
    resetChart: function () {
        trip.chart = $('#chartContainer').highcharts({
            chart: {
                type: 'column',
                zoomType: 'x',
                height: 350
            },
            title: {
                text: '行程分析'
            },
            xAxis: {
                type: 'category',
                categories: trip.labels
            },
            yAxis: [{
                min: 0,
                title: {
                    style: {
                        color: '#000000'
                    },
                    text: '升'
                },
                labels: {
                    style: {
                        color: '#000000'
                    }
                }
            }, {
                min: 0,
                title: {
                    style: {
                        color: '#2f7ed8'
                    },
                    text: '公里'
                },
                labels: {
                    style: {
                        color: '#2f7ed8'
                    }
                },
                opposite: true
            }, {
                min: 0,
                title: {
                    style: {
                        color: '#8bbc21'
                    },
                    text: '分钟'
                },
                labels: {
                    style: {
                        color: '#8bbc21'
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
            series: [{
                name: '油耗',
                color: '#000000',
                data: trip.fuel
            }, {
                name: '里程',
                color: '#2f7ed8',
                yAxis: 1,
                data: trip.mileage
            }, {
                name: '时间',
                yAxis: 2,
                color: '#8bbc21',
                data: trip.runtime
            }]
        });
    }
};
$(function () {
    $("#layout").ligerLayout({
        topHeight: 80,
        rightWidth: 300,
        allowTopResize: false
    });
    $('#btnTrip').addClass('select');
    var number = $('#txtDeviceNumber').text();
    if (number.length > 0) {
        $.post('./common/device/info', {
            number: number
        }, function (data) {
            trip.device = data;
            trip.gridTripsParams.number = data.number;
            common.setCookie('device_number', data.number);
            common.setCookie('device_name', data.name);
            $('#txtDeviceNumber').text(data.number);
            $('#txtDeviceName').text(data.name);
        });
    } else {
        var number = common.getCookie('device_number');
        var name = common.getCookie('device_name');
        if (number && name) {
            trip.device = {
                number: number,
                name: name
            };
            trip.gridTripsParams.number = number;
            $('#txtDeviceNumber').text(number);
            $('#txtDeviceName').text(name);
        }
    }
    var startTime = common.getCookie('time_start');
    startTime = startTime == null ? '' : startTime;

    startTime = startTime.length > 0 ? startTime.toDate().toDateTimeString(
        'yyyy-MM-dd hh:mm') : new Date().addDays(-1).toDateTimeString(
        'yyyy-MM-dd hh:mm');
    trip.gridTripsParams.start = startTime + ':00';

    var endTime = common.getCookie('time_end');
    endTime = endTime == null ? '' : endTime;

    endTime = endTime.length > 0 ? endTime.toDate().toDateTimeString(
        'yyyy-MM-dd hh:mm') : new Date()
        .toDateTimeString('yyyy-MM-dd hh:mm');
    trip.gridTripsParams.end = endTime + ':00';

    trip.startDate = $("#txtStartDate").ligerDateEditor(
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
                trip.gridTripsParams.start = time;
            }
        });
    trip.endDate = $("#txtEndDate").ligerDateEditor(
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
                trip.gridTripsParams.end = time;
            }
        });

    $('#txtPrice').change(function () {
        var price = parseFloat($('#txtPrice').val());
        var costs = common.round(price * trip.total.fuels, 2);
        $('#txtCost').text(costs);
    });

    $('#btnQuery').click(function () {
        if (!trip.device) {
            $.ligerDialog.warn('未发现设备信息！');
            return;
        }
        if (trip.gridTrips)
            trip.gridTrips.reload();
    });

    $('#btnSelectDevice').click(function () {
        if (!trip.gridDevices)
            return;
        var row = trip.gridDevices.getSelected();
        if (row) {
            trip.device = {
                number: row.number,
                name: row.name
            };
            common.setCookie('device_number', row.number);
            common.setCookie('device_name', row.name);
            trip.gridTripsParams.number = row.number;
            $('#txtDeviceNumber').text(row.number);
            $('#txtDeviceName').text(row.name);
            if (trip.dialogSearchDevices)
                trip.dialogSearchDevices.hide();
        }

    });

    $('#btnSearchDevices').click(function () {
        trip.gridDevicesParams.filter = $('#txtDeviceFilter').val();

        var op = {
            target: $('#dialogSearchDevices'),
            width: 800,
            height: 410,
            title: '选择设备',
        };
        trip.dialogSearchDevices = $.ligerDialog.open(op);

        if (!trip.gridDevices) {
            trip.gridDevices = $("#gridDevices").ligerGrid({
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
                parms: trip.gridDevicesParams,
                width: '99%',
                height: 300
            });
        } else {
            trip.gridDevices.reload();
        }

    });
    trip.gridTrips = $("#gridTrips").ligerGrid(
        {
            columns: [
                {
                    display: '开始时间',
                    name: 'startTime',
                    width: 130,
                    frozen: true
                },
                {
                    display: '结果时间',
                    name: 'endTime',
                    width: 130,
                    frozen: true
                },
                {
                    display: '里程',
                    name: 'mileage',
                    width: 50
                },
                {
                    display: '总油耗',
                    name: 'totalOil',
                    width: 50
                },
                {
                    display: '平均油耗',
                    name: 'avgOil',
                    width: 50
                },
                {
                    display: '怠速',
                    name: 'idleTime',
                    width: 50
                },
                {
                    display: '急加速',
                    name: 'speedUp',
                    width: 50
                },
                {
                    display: '急减速',
                    name: 'breaks',
                    width: 50
                },
                {
                    display: '最高速度',
                    name: 'maxSpeed',
                    width: 50
                },
                {
                    display: '平均速度',
                    name: 'avgSpeed',
                    width: 50
                },
                {
                    display: '超速次数',
                    name: 'overSpeed',
                    width: 50
                },
                {
                    display: '超速时长',
                    name: 'overSpeedTime',
                    width: 50
                },
                {
                    display: '最高水温',
                    name: 'maxEct',
                    width: 50
                },
                {
                    display: '最高转速',
                    name: 'maxRpm',
                    width: 50
                },
                {
                    display: '平均电压',
                    name: 'avgBv',
                    width: 50
                },
                {
                    display: '疲驾时长',
                    name: 'fatigueTime',
                    width: 50
                },
                {
                    display: '操作',
                    isAllowHide: false,
                    render: function (row) {
                        var html = '<a href="./track?number='
                            + row.number + '&start='
                            + row.startTime + '&end=' + row.endTime
                            + '">轨迹</a>';
                        return html;
                    }
                }],
            usePager: false,
            root: 'rows',
            record: 'total',
            url: './common/trip/load',
            parms: trip.gridTripsParams,
            height: '100%',
            onSuccess: trip.analyze
        });
});