/**
 * 车辆警情统计
 */
window.vehicleAlarm = {
    gridVehicleAlarmParms: {
        motorcade: '',
        start: null,
        end: null
    },
    query: function () {
        if (!vehicleAlarm.gridVehicleAlarm)
            vehicleAlarm.gridVehicleAlarm = $('#gridVehicleAlarm').ligerGrid({
                columns: [{
                    display: '车队',
                    name: 'motorcade',
                    align: 'left',
                    width: 150
                }, {
                    display: '非区域超速次数',
                    name: 'overspeedNoneArea'
                }, {
                    display: '区域内超速次数',
                    name: 'overspeedInArea'
                }, {
                    display: '路段超速次数',
                    name: 'overspeedInSection'
                }, {
                    display: '疲劳驾驶次数',
                    name: 'fatigueDriving'
                }, {
                    display: '超时停车次数',
                    name: 'parkingTimeout'
                }, {
                    display: '路线偏离次数',
                    name: 'routeDeparture'
                }, {
                    display: '开始时间',
                    name: 'start',
                    align: 'left',
                    type: 'date',
                    width: 120
                }, {
                    display: '结束时间',
                    name: 'end',
                    align: 'left',
                    type: 'date',
                    width: 120
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../statistics/vehicleAlarmCount',
                width: '100%',
                height: '100%',
                pageSize: 30,
                parms: vehicleAlarm.gridVehicleAlarmParms,
                detail: {
                    onShowDetail: vehicleAlarm.showDetail,
                    height: 'auto'
                },
                onSuccess: function (data) {
                    vehicleAlarm.drawChart(data);
                }
            });
        else {
            vehicleAlarm.gridVehicleAlarm.changePage('first');
            vehicleAlarm.gridVehicleAlarm.reload();
        }
    },
    showDetail: function (row, detailPanel, callback) {
        var start = vehicleAlarm.gridVehicleAlarmParms.start;
        var end = vehicleAlarm.gridVehicleAlarmParms.end;
        var grid = document.createElement('div');
        $(detailPanel).append(grid);
        $(grid).css('margin', 10).ligerGrid({
            columns: [{
                display: '车队',
                name: 'motorcade',
                align: 'left',
                width: 150,
                frozen: true
            }, {
                display: '车牌号',
                name: 'plateNumber',
                align: 'left',
                width: 150
            }, {
                display: '非区域超速次数',
                name: 'overspeedNoneArea'
            }, {
                display: '区域内超速次数',
                name: 'overspeedInArea'
            }, {
                display: '路段超速次数',
                name: 'overspeedInSection'
            }, {
                display: '疲劳驾驶次数',
                name: 'fatigueDriving'
            }, {
                display: '超时停车次数',
                name: 'parkingTimeout'
            }, {
                display: '路线偏离次数',
                name: 'routeDeparture'
            }, {
                display: '开始时间',
                name: 'start',
                align: 'left',
                type: 'date',
                width: 120
            }, {
                display: '结束时间',
                name: 'end',
                align: 'left',
                type: 'date',
                width: 120
            }],
            toolbar: {
                items: [{
                    text: '导出Excel',
                    click: function () {
                        vehicleAlarm.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
                    },
                    img: '../resources/css/x16/excel.png'
                }, {
                    text: '导出Pdf',
                    click: function () {
                        vehicleAlarm.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
                    },
                    img: '../resources/css/x16/pdf.png'
                }]
            },
            toolbarShowInLeft: false,
            width: '98%',
            url: '../statistics/vehicleAlarmDetail',
            title: '车辆警情明细',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            parms: {
                motorcadeId: row.motorcadeId,
                motorcade: row.motorcade,
                start: start,
                end: end
            }
        });
    },
    drawChart: function (data) {
        var rows = data.rows;
        var motorcadeTimes = {};
        var categoriesTimes = {};
        for (var x = 0; x < rows.length; x++) {
            var row = rows[x];
            motorcadeTimes[row.motorcade] = (motorcadeTimes[row.motorcade] || 0) + row.overspeedNoneArea + row.overspeedInArea
                + row.overspeedInSection + row.fatigueDriving + row.parkingTimeout + row.routeDeparture;
            categoriesTimes['非区域超速'] = (categoriesTimes['非区域超速'] || 0) + row.overspeedNoneArea;
            categoriesTimes['区域内超速'] = (categoriesTimes['区域内超速'] || 0) + row.overspeedInArea;
            categoriesTimes['路段超速'] = (categoriesTimes['路段超速'] || 0) + row.overspeedInSection;
            categoriesTimes['疲劳驾驶'] = (categoriesTimes['疲劳驾驶'] || 0) + row.fatigueDriving;
            categoriesTimes['超时停车'] = (categoriesTimes['超时停车'] || 0) + row.parkingTimeout;
            categoriesTimes['路线偏离'] = (categoriesTimes['路线偏离'] || 0) + row.routeDeparture;
        }
        var motorcades = [];
        for (var attr in motorcadeTimes) {
            motorcades.push({
                name: attr,
                y: motorcadeTimes[attr]
            });
        }
        var categories = [];
        for (var attr in categoriesTimes) {
            categories.push({
                name: attr,
                y: categoriesTimes[attr]
            });
        }
        $('#chart1').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '车队报警'
            },
            credits: {
                enabled: false
            },
            tooltip: {
                pointFormat: '{point.percentage:.1f}%'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                colorByPoint: true,
                data: motorcades
            }]
        });
        $('#chart2').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '分类报警'
            },
            credits: {
                enabled: false
            },
            tooltip: {
                pointFormat: '{point.percentage:.1f}%'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                colorByPoint: true,
                data: categories
            }]
        });
    },
    countExporting: function (type) {
        var form = $('#exportForm');
        form.remove();

        form = $('<form id="exportForm">');// 定义一个form表单
        form.attr('method', 'post');
        form.attr('action', 'vehicleAlarmCountExport');

        var txtMotorcade = $('<input>');
        txtMotorcade.attr('type', 'hidden');
        txtMotorcade.attr('name', 'motorcade');
        txtMotorcade.attr('value', vehicleAlarm.gridVehicleAlarmParms.motorcade);

        var txtStart = $('<input>');
        txtStart.attr('type', 'hidden');
        txtStart.attr('name', 'start');
        txtStart.attr('value', vehicleAlarm.gridVehicleAlarmParms.start);

        var txtEnd = $('<input>');
        txtEnd.attr('type', 'hidden');
        txtEnd.attr('name', 'end');
        txtEnd.attr('value', vehicleAlarm.gridVehicleAlarmParms.end);

        var txtType = $('<input>');
        txtType.attr('type', 'hidden');
        txtType.attr('name', 'type');
        txtType.attr('value', type);

        $('body').append(form);// 将表单放置在web中
        form.append(txtMotorcade);
        form.append(txtStart);
        form.append(txtEnd);
        form.append(txtType);
        form.submit();// 表单提交
    },
    detailExporting: function (motorcadeId, motorcade, start, end, type) {
        var form = $('#exportForm');
        form.remove();

        form = $('<form id="exportForm">');// 定义一个form表单
        form.attr('method', 'post');
        form.attr('action', 'vehicleAlarmDetailExport');

        var txtMotorcadeId = $('<input>');
        txtMotorcadeId.attr('type', 'hidden');
        txtMotorcadeId.attr('name', 'motorcadeId');
        txtMotorcadeId.attr('value', motorcadeId);

        var txtMotorcade = $('<input>');
        txtMotorcade.attr('type', 'hidden');
        txtMotorcade.attr('name', 'motorcade');
        txtMotorcade.attr('value', motorcade);

        var txtStart = $('<input>');
        txtStart.attr('type', 'hidden');
        txtStart.attr('name', 'start');
        txtStart.attr('value', start);

        var txtEnd = $('<input>');
        txtEnd.attr('type', 'hidden');
        txtEnd.attr('name', 'end');
        txtEnd.attr('value', end);

        var txtType = $('<input>');
        txtType.attr('type', 'hidden');
        txtType.attr('name', 'type');
        txtType.attr('value', type);

        $('body').append(form);// 将表单放置在web中
        form.append(txtMotorcadeId);
        form.append(txtMotorcade);
        form.append(txtStart);
        form.append(txtEnd);
        form.append(txtType);
        form.submit();// 表单提交
    }
}
$(function () {
    var now = new Date();
    var startDate = now.addDays(-7);
    var endDate = now.addDays(1);

    vehicleAlarm.gridVehicleAlarmParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
    vehicleAlarm.gridVehicleAlarmParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

    vehicleAlarm.startDate = $('#txtStartDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: startDate.toDateTimeString('yyyy-MM-dd')
    });
    vehicleAlarm.endDate = $('#txtEndDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: endDate.toDateTimeString('yyyy-MM-dd')
    });
    vehicleAlarm.query();

    $('#btnQuery').click(function () {
        vehicleAlarm.gridVehicleAlarmParms.motorcade = $('#txtMotorcade').val();
        vehicleAlarm.gridVehicleAlarmParms.start = vehicleAlarm.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        vehicleAlarm.gridVehicleAlarmParms.end = vehicleAlarm.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        vehicleAlarm.query();
    });

    $('#btnExportExcel').click(function () {
        vehicleAlarm.countExporting('excel');
    });

    $('#btnExportPdf').click(function () {
        vehicleAlarm.countExporting('pdf');
    });
})