/**
 * 历史在线率统计
 */
window.historyOnlineTime = {
    gridHistoryOnlineTimeParms: {
        motorcade: '',
        start: null,
        end: null
    },
    query: function () {
        if (!historyOnlineTime.gridHistoryOnlineTime)
            historyOnlineTime.gridHistoryOnlineTime = $("#gridHistoryOnlineTime").ligerGrid({
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
                    display: '应在线时长',
                    name: 'must',
                    render: function (row) {
                        return row.must + '分钟';
                    }
                }, {
                    display: '实在线时长',
                    name: 'real',
                    render: function (row) {
                        return row.real + '分钟';
                    }
                }, {
                    display: '在线率',
                    name: 'onlineRate',
                    render: function (row) {
                        return common.round(row.onlineRate, 2) + '%';
                    }
                }, {
                    display: '离线率',
                    name: 'offlineRate',
                    render: function (row) {
                        return common.round(row.offlineRate, 2) + '%';
                    }
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../statistics/historyOnlineTimeCount',
                width: '100%',
                height: '100%',
                pageSize: 30,
                parms: historyOnlineTime.gridHistoryOnlineTimeParms,
                detail: {
                    onShowDetail: historyOnlineTime.showDetail,
                    height: 'auto'
                },
                onSuccess: function (data) {
                    historyOnlineTime.drawChart(data);
                }
            });
        else {
            historyOnlineTime.gridHistoryOnlineTime.changePage('first');
            historyOnlineTime.gridHistoryOnlineTime.reload();
        }
    },
    showDetail: function (row, detailPanel, callback) {
        var start = historyOnlineTime.startDate.getValue().toDateTimeString('yyyy-MM-dd hh:mm:ss');
        var end = historyOnlineTime.endDate.getValue().toDateTimeString('yyyy-MM-dd hh:mm:ss');
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
                display: '应在线时长',
                name: 'must',
                render: function (row) {
                    return row.must + '分钟';
                }
            }, {
                display: '实在线时长',
                name: 'real',
                render: function (row) {
                    return row.real + '分钟';
                }
            }, {
                display: '在线率',
                name: 'onlineRate',
                render: function (row) {
                    return common.round(row.onlineRate, 2) + '%';
                }
            }, {
                display: '离线率',
                name: 'offlineRate',
                render: function (row) {
                    return common.round(row.offlineRate, 2) + '%';
                }
            }],
            toolbar: {
                items: [{
                    text: '导出Excel',
                    click: function () {
                        historyOnlineTime.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
                    },
                    img: '../resources/css/x16/excel.png'
                }, {
                    text: '导出Pdf',
                    click: function () {
                        historyOnlineTime.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
                    },
                    img: '../resources/css/x16/pdf.png'
                }]
            },
            toolbarShowInLeft: false,
            width: '95%',
            url: '../statistics/historyOnlineTimeDetail',
            title: '车辆上线率明细',
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
        var categories = [];
        var onlines = {
            name: '在线率',
            data: []
        };
        var offlines = {
            name: '离线率',
            data: []
        };

        for (var x = 0; x < rows.length; x++) {
            categories.push(rows[x].motorcade);
            onlines.data.push(common.round(rows[x].onlineRate, 2));
            offlines.data.push(common.round(rows[x].offlineRate, 2));
        }
        $('#chart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '车辆历史在线率统计'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                max: 100,
                title: {
                    text: '%'
                }
            },
            credits: {
                enabled: false
            },
            tooltip: {
                valueSuffix: ' %'
            },
            series: [onlines, offlines]
        });
    },
    countExporting: function (type) {
        var form = $('#exportForm');
        form.remove();

        form = $('<form id="exportForm">');// 定义一个form表单
        form.attr('method', 'post');
        form.attr('action', 'historyOnlineTimeCountExport');

        var txtMotorcade = $('<input>');
        txtMotorcade.attr('type', 'hidden');
        txtMotorcade.attr('name', 'motorcade');
        txtMotorcade.attr('value', historyOnlineTime.gridHistoryOnlineTimeParms.motorcade);

        var txtStart = $('<input>');
        txtStart.attr('type', 'hidden');
        txtStart.attr('name', 'start');
        txtStart.attr('value', historyOnlineTime.gridHistoryOnlineTimeParms.start);

        var txtEnd = $('<input>');
        txtEnd.attr('type', 'hidden');
        txtEnd.attr('name', 'end');
        txtEnd.attr('value', historyOnlineTime.gridHistoryOnlineTimeParms.end);

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
        form.attr('action', 'historyOnlineTimeDetailExport');

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

    historyOnlineTime.gridHistoryOnlineTimeParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
    historyOnlineTime.gridHistoryOnlineTimeParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

    historyOnlineTime.startDate = $('#txtStartDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: startDate.toDateTimeString('yyyy-MM-dd')
    });
    historyOnlineTime.endDate = $('#txtEndDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: endDate.toDateTimeString('yyyy-MM-dd')
    });
    historyOnlineTime.query();

    $('#btnQuery').click(function () {
        historyOnlineTime.gridHistoryOnlineTimeParms.motorcade = $('#txtMotorcade').val();
        historyOnlineTime.gridHistoryOnlineTimeParms.start = historyOnlineTime.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        historyOnlineTime.gridHistoryOnlineTimeParms.end = historyOnlineTime.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        historyOnlineTime.query();
    });

    $('#btnExportExcel').click(function () {
        historyOnlineTime.countExporting('excel');
    });
    $('#btnExportPdf').click(function () {
        historyOnlineTime.countExporting('pdf');
    });
})