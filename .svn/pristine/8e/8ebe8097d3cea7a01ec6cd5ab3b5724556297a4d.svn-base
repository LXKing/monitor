/**
 * 车辆上线率统计
 */
window.historyVehicleOnline = {
    gridHistoryVehicleOnlineParms: {
        motorcade: '',
        start: null,
        end: null
    },
    query: function () {
        if (!historyVehicleOnline.gridHistoryVehicleOnline)
            historyVehicleOnline.gridHistoryVehicleOnline = $("#gridHistoryVehicleOnline").ligerGrid({
                columns: [{
                    display: '车队',
                    name: 'motorcade',
                    align: 'left',
                    width: 150
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
                }, {
                    display: '车辆总数',
                    name: 'total'
                }, {
                    display: '上线数量',
                    name: 'online'
                }, {
                    display: '上线率',
                    name: 'onlineRate',
                    render: function (row) {
                        return row.onlineRate + '%';
                    }
                }, {
                    display: '下线数量',
                    name: 'offline'
                }, {
                    display: '下线率',
                    name: 'offlineRate',
                    render: function (row) {
                        return row.offlineRate + '%';
                    }
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../statistics/historyOnlineOfflineCount',
                width: '100%',
                height: '100%',
                pageSize: 30,
                parms: historyVehicleOnline.gridHistoryVehicleOnlineParms,
                detail: {
                    onShowDetail: historyVehicleOnline.showDetail,
                    height: 'auto'
                },
                onSuccess: function (data) {
                    historyVehicleOnline.drawChart(data);
                }
            });
        else {
            historyVehicleOnline.gridHistoryVehicleOnline.changePage('first');
            historyVehicleOnline.gridHistoryVehicleOnline.reload();
        }
    },
    showDetail: function (row, detailPanel, callback) {
        var start = historyVehicleOnline.gridHistoryVehicleOnlineParms.start;
        var end = historyVehicleOnline.gridHistoryVehicleOnlineParms.end;
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
            }, {
                display: '在线情况',
                name: 'online',
                render: function (row) {
                    if (row.online == true)
                        return '上线';
                    else
                        return '下线';
                }
            }],
            toolbar: {
                items: [{
                    text: '导出Excel',
                    click: function () {
                        historyVehicleOnline.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
                    },
                    img: '../resources/css/x16/excel.png'
                }, {
                    text: '导出Pdf',
                    click: function () {
                        historyVehicleOnline.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
                    },
                    img: '../resources/css/x16/pdf.png'
                }]
            },
            toolbarShowInLeft: false,
            width: '95%',
            url: '../statistics/historyOnlineOfflineDetail',
            title: '车辆上线率明细',
            showTitle: false,
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
        var categories = [];
        var onlines = {
            name: '上线',
            data: []
        };
        var offlines = {
            name: '下线',
            data: []
        };

        for (var x = 0; x < rows.length; x++) {
            categories.push(rows[x].motorcade);
            onlines.data.push(rows[x].online);
            offlines.data.push(rows[x].offline);
        }
        $('#chart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '车辆历史上线统计'
            },
            exporting: {
                enabled: true
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: '辆'
                }
            },
            credits: {
                enabled: false
            },
            tooltip: {
                valueSuffix: ' 辆'
            },
            series: [onlines, offlines]
        });
    },
    countExporting: function (type) {
        var form = $('#exportForm');
        form.remove();

        form = $('<form id="exportForm">');// 定义一个form表单
        form.attr('method', 'post');
        form.attr('action', 'historyOnlineOfflineCountExport');

        var txtMotorcade = $('<input>');
        txtMotorcade.attr('type', 'hidden');
        txtMotorcade.attr('name', 'motorcade');
        txtMotorcade.attr('value', historyVehicleOnline.gridHistoryVehicleOnlineParms.motorcade);

        var txtStart = $('<input>');
        txtStart.attr('type', 'hidden');
        txtStart.attr('name', 'start');
        txtStart.attr('value', historyVehicleOnline.gridHistoryVehicleOnlineParms.start);

        var txtEnd = $('<input>');
        txtEnd.attr('type', 'hidden');
        txtEnd.attr('name', 'end');
        txtEnd.attr('value', historyVehicleOnline.gridHistoryVehicleOnlineParms.end);

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
        form.attr('action', 'historyOnlineOfflineDetailExport');

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

    historyVehicleOnline.gridHistoryVehicleOnlineParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
    historyVehicleOnline.gridHistoryVehicleOnlineParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

    historyVehicleOnline.startDate = $('#txtStartDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: startDate.toDateTimeString('yyyy-MM-dd')
    });
    historyVehicleOnline.endDate = $('#txtEndDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: endDate.toDateTimeString('yyyy-MM-dd')
    });
    historyVehicleOnline.query();

    $('#btnQuery').click(function () {
        historyVehicleOnline.gridHistoryVehicleOnlineParms.motorcade = $('#txtMotorcade').val();
        historyVehicleOnline.gridHistoryVehicleOnlineParms.start = historyVehicleOnline.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        historyVehicleOnline.gridHistoryVehicleOnlineParms.end = historyVehicleOnline.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        historyVehicleOnline.query();
    });

    $('#btnExportExcel').click(function () {
        historyVehicleOnline.countExporting('excel');
    });
    $('#btnExportPdf').click(function () {
        historyVehicleOnline.countExporting('pdf');
    });
})