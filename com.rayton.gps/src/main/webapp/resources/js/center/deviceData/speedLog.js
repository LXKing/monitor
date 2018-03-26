/**
 * 速度记录
 */
window.speedLog = {
    drivingRecorder: null,
    gridSpeedLogParms: {
        start: null,
        end: null
    },
    getDrivingRecorder: function (number) {
        $.post('../deviceData/drivingRecorder', {
            deviceNumber: number
        }, function (o) {
            speedLog.drivingRecorder = o;
            speedLog.drawChart({
                content: [],
                time: new Date().toDateTimeString()
            });
        });
    },
    query: function () {
        if (!speedLog.gridSpeedLog) {
            speedLog.gridSpeedLog = $("#gridSpeedLog").ligerGrid({
                columns: [{
                    display: '时间',
                    name: 'time',
                    align: 'left',
                    type: 'date'
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../deviceData/speedLog/query',
                width: '100%',
                height: '100%',
                pageSize: 30,
                rownumbers: true,
                parms: speedLog.gridSpeedLogParms,
                onSelectRow: function (row) {
                    speedLog.drawChart(row);
                }
            });
        } else {
            speedLog.gridSpeedLog.changePage('first');
            speedLog.gridSpeedLog.reload();
        }
    },
    drawChart: function (row) {
        var dr = speedLog.drivingRecorder || {};
        var time = row.time.toDate().getTime();
        var maxSpeed = 0;
        var speedLine = {
            type: 'line',
            name: '速度',
            data: []
        };
        var d0Line = {
            type: 'line',
            name: '(D0)' + (dr.d0 || 'D0'),
            data: []
        }
        var d1Line = {
            type: 'line',
            name: '(D1)' + (dr.d1 || 'D1'),
            data: []
        }
        var d2Line = {
            type: 'line',
            name: '(D2)' + (dr.d2 || 'D2'),
            data: []
        }
        var d3Line = {
            type: 'line',
            name: '(D3)' + (dr.d3 || 'D3'),
            data: []
        }
        var d4Line = {
            type: 'line',
            name: '(D4)' + (dr.d4 || 'D4'),
            data: []
        }
        var d5Line = {
            type: 'line',
            name: '(D5)' + (dr.d5 || 'D5'),
            data: []
        }
        var d6Line = {
            type: 'line',
            name: '(D6)' + (dr.d6 || 'D6'),
            data: []
        }
        var d7Line = {
            type: 'line',
            name: '(D7)' + (dr.d7 || 'D7'),
            data: []
        }
        for (var x = 0; x < row.content.length / 2; x++) {
            var speed = row.content[x * 2];
            if (speed > maxSpeed)
                maxSpeed = speed;
            var status = row.content[x * 2 + 1];
            // 速度
            speedLine.data.push({
                x: time + x * 200,
                y: speed
            });

            // D0
            d0Line.data.push({
                x: time + x * 200,
                y: ((status & 1) == 1) ? 35 : null
            });
            // D1
            d1Line.data.push({
                x: time + x * 200,
                y: ((status >> 1 & 1) == 1) ? 30 : null
            });
            // D2
            d2Line.data.push({
                x: time + x * 200,
                y: ((status >> 2 & 1) == 1) ? 25 : null
            });
            // D3
            d3Line.data.push({
                x: time + x * 200,
                y: ((status >> 3 & 1) == 1) ? 20 : null
            });
            // D4
            d4Line.data.push({
                x: time + x * 200,
                y: ((status >> 4 & 1) == 1) ? 15 : null
            });
            // D5
            d5Line.data.push({
                x: time + x * 200,
                y: ((status >> 5 & 1) == 1) ? 10 : null
            });
            // D6
            d6Line.data.push({
                x: time + x * 200,
                y: ((status >> 6 & 1) == 1) ? 5 : null
            });
            // D7
            d7Line.data.push({
                x: time + x * 200,
                y: ((status >> 7 & 1) == 1) ? 130 : null
            });
        }
        for (var x = 0; x < row.content.length / 2; x++) {
            var item = d0Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d1Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d2Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d3Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d4Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d5Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d6Line.data[x];
            if (item.y)
                item.y += maxSpeed;
            item = d7Line.data[x];
            if (item.y)
                item.y += maxSpeed;
        }

        $('#chart').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: '车牌号：' + (dr.plateNumber ? dr.plateNumber : '未采集') + " 车牌分类：" + (dr.plateType ? dr.plateType : '未采集'),
                x: -20
                // center
            },
            subtitle: {
                text: '驾驶证号：' + (row.license || '未登录'),
                x: -20
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 50
            },
            yAxis: {
                min: 0,
                title: {
                    text: '速度(km/h)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            legend: {
                enabled: false
            },
            tooltip: {
                shared: true,
                formatter: function () {
                    var html = [];
                    for (var i = 0; i < this.points.length; i++) {
                        var p = this.points[i];
                        if (html.length == 0)
                            html.push('时间:' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S.%L', p.x));
                        if (p.series.index == 0) {
                            html.push('<br/>' + p.series.name + ':' + Highcharts.numberFormat(p.y, 1) + 'km/h');
                        } else {
                            html.push('<br/>' + p.series.name + ':' + (p.y ? '有' : '无') + '<br/>');
                        }
                    }
                    return html.join('');
                }
            },
            plotOptions: {
                line: {
                    lineWidth: 1
                }
            },
            credits: {
                enabled: false
                // 禁用版权信息
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [speedLine, d0Line, d1Line, d2Line, d3Line, d4Line, d5Line, d6Line, d7Line]
        });
    }
}
$(function () {
    speedLog.vehicle = parent.deviceData.vehicle;
    speedLog.deviceNumber = parent.deviceData.deviceNumber;
    speedLog.plateNumber = parent.deviceData.plateNumber;

    speedLog.getDrivingRecorder(speedLog.deviceNumber);

    speedLog.gridSpeedLogParms.deviceNumber = speedLog.deviceNumber;
    var endDate = new Date().addDays(1);
    speedLog.gridSpeedLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
    var startDate = endDate.addDays(-7);
    speedLog.gridSpeedLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

    speedLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        label: '开始时间',
        labelWidth: 65,
        initValue: startDate.toDateTimeString("yyyy-MM-dd")
    });

    speedLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        label: '结束时间',
        labelWidth: 65,
        initValue: endDate.toDateTimeString("yyyy-MM-dd")
    });

    speedLog.query();

    speedLog.drawChart({
        content: [],
        time: new Date().toDateTimeString()
    });

    $('#btnQuery').click(function () {
        var start = speedLog.txtStartDate.getValue();
        var end = speedLog.txtEndDate.getValue();
        if (start == null || end == null) {
            $.ligerDialog.error('请选择时间段！');
            return;
        }
        speedLog.gridSpeedLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
        speedLog.gridSpeedLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
        speedLog.query();
    });
});