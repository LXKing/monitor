/**
 * 事故疑点日志
 */
window.accidentDoubtLog = {
    map: null,
    drivingRecorder: null,
    deviceNumber: null,
    plateNumber: null,
    gridAccidentDoubtLog: null,
    gridAccidentDoubtLogParms: {
        deviceNumber: null,
        start: null,
        end: null
    },
    onMapLoaded: function (map) {
        accidentDoubtLog.map = map;
    },
    getDrivingRecorder: function (number) {
        $.post('../deviceData/drivingRecorder', {
            deviceNumber: number
        }, function (o) {
            accidentDoubtLog.drivingRecorder = o;
            accidentDoubtLog.drawChart({
                content: [],
                time: new Date().toDateTimeString()
            });
        });
    },
    query: function () {
        if (!accidentDoubtLog.gridAccidentDoubtLog) {
            accidentDoubtLog.gridAccidentDoubtLog = $("#gridAccidentDoubtLog").ligerGrid({
                columns: [{
                    display: '时间',
                    name: 'time',
                    align: 'left',
                    width: 120
                }, {
                    display: '驾驶证号',
                    name: 'license',
                    align: 'left',
                    width: 180
                }, {
                    display: '经度',
                    name: 'lng',
                    align: 'left',
                    width: 100
                }, {
                    display: '纬度',
                    name: 'lat',
                    align: 'left',
                    width: 100
                }, {
                    display: '高度',
                    name: 'alt',
                    align: 'left',
                    width: 50
                }, {
                    display: '地址',
                    name: 'addr',
                    align: 'left',
                    width: 300
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../deviceData/accidentDoubtLog/query',
                width: '100%',
                height: '100%',
                pageSize: 30,
                fixedCellHeight: false,
                parms: accidentDoubtLog.gridAccidentDoubtLogParms,
                onSelectRow: function (row) {
                    accidentDoubtLog.drawChart(row);
                    accidentDoubtLog.showLocate(row);
                }
            });
        } else {
            accidentDoubtLog.gridAccidentDoubtLog.changePage('first');
            accidentDoubtLog.gridAccidentDoubtLog.reload();
        }
    },
    showLocate: function (row) {
        var dr = accidentDoubtLog.drivingRecorder;
        accidentDoubtLog.map.clearOverlays();
        accidentDoubtLog.map.convertor(row.lng, row.lat, function (point) {
            accidentDoubtLog.map.drawPoi({
                lng: point.lng,
                lat: point.lat
            }, dr.plateNumber ? dr.plateNumber : '未采集');
            accidentDoubtLog.map.panTo(point.lng, point.lat);
            if (row.addr)
                return;
            accidentDoubtLog.map.queryAddress(point.lng, point.lat, function (address, row) {
                row.addr = address;
                accidentDoubtLog.gridAccidentDoubtLog.reRender({
                    rowdata: row
                });
            }, row);
        });
    },
    drawChart: function (row) {
        var dr = accidentDoubtLog.drivingRecorder;
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
    webMap.events.onMapLoadCompleted['accidentDoubtLogMap'] = accidentDoubtLog.onMapLoaded;
    webMap.createMap("accidentDoubtLogMap");

    accidentDoubtLog.vehicle = parent.deviceData.vehicle;
    accidentDoubtLog.deviceNumber = parent.deviceData.deviceNumber;
    accidentDoubtLog.plateNumber = parent.deviceData.plateNumber;
    accidentDoubtLog.getDrivingRecorder(accidentDoubtLog.deviceNumber);

    accidentDoubtLog.gridAccidentDoubtLogParms.deviceNumber = accidentDoubtLog.deviceNumber;
    var endDate = new Date().addDays(1);
    accidentDoubtLog.gridAccidentDoubtLogParms.end = endDate.toDateTimeString("yyyy-MM-dd hh:mm:00");
    var startDate = endDate.addDays(-7);
    accidentDoubtLog.gridAccidentDoubtLogParms.start = startDate.toDateTimeString("yyyy-MM-dd hh:mm:00");

    accidentDoubtLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        showTime: true,
        label: '开始时间',
        labelWidth: 65,
        initValue: startDate.toDateTimeString("yyyy-MM-dd hh:mm")
    });

    accidentDoubtLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        showTime: true,
        label: '结束时间',
        labelWidth: 65,
        initValue: endDate.toDateTimeString("yyyy-MM-dd hh:mm")
    });

    accidentDoubtLog.query();

    $('#btnQuery').click(function () {
        var start = accidentDoubtLog.txtStartDate.getValue();
        var end = accidentDoubtLog.txtEndDate.getValue();
        if (start == null || end == null) {
            $.ligerDialog.error('请选择时间段！');
            return;
        }
        accidentDoubtLog.gridAccidentDoubtLogParms.start = start.toDateTimeString("yyyy-MM-dd hh:mm:00");
        accidentDoubtLog.gridAccidentDoubtLogParms.end = end.toDateTimeString("yyyy-MM-dd hh:mm:00");
        accidentDoubtLog.query();
    });
});