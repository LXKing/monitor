/**
 * 车辆路段超速统计
 */
window.sectionOverspeed = {
    gridSectionOverspeedParms: {
        motorcade: '',
        start: null,
        end: null
    },
    query: function () {
        if (!sectionOverspeed.gridSectionOverspeed)
            sectionOverspeed.gridSectionOverspeed = $('#gridSectionOverspeed').ligerGrid({
                columns: [{
                    display: '车队',
                    name: 'motorcade',
                    align: 'left',
                    width: 150
                }, {
                    display: '超速次数',
                    name: 'times'
                }, {
                    display: '超速时长',
                    name: 'duration',
                    render: function (row) {
                        return common.timespan(row.duration);
                    }
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
                url: '../statistics/sectionOverspeedCount',
                width: '100%',
                height: '100%',
                pageSize: 30,
                parms: sectionOverspeed.gridSectionOverspeedParms,
                detail: {
                    onShowDetail: sectionOverspeed.showDetail,
                    height: 'auto'
                },
                onSuccess: function (data) {
                    sectionOverspeed.drawChart(data);
                }
            });
        else {
            sectionOverspeed.gridSectionOverspeed.changePage('first');
            sectionOverspeed.gridSectionOverspeed.reload();
        }
    },
    showDetail: function (row, detailPanel, callback) {
        var start = sectionOverspeed.gridSectionOverspeedParms.start;
        var end = sectionOverspeed.gridSectionOverspeedParms.end;
        var grid = document.createElement('div');
        $(detailPanel).append(grid);
        $(grid).css('margin', 10).ligerGrid({
            columns: [{
                display: '车队',
                name: 'motorcade',
                align: 'left',
                width: 150
            }, {
                display: '车牌号',
                name: 'plateNumber',
                align: 'left',
                width: 150
            }, {
                display: '路段',
                name: 'section',
                align: 'left',
                width: 180
            }, {
                display: '超速次数',
                name: 'times'
            }, {
                display: '超速时长',
                name: 'duration',
                render: function (row) {
                    return common.timespan(row.duration);
                }
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
                        sectionOverspeed.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
                    },
                    img: '../resources/css/x16/excel.png'
                }, {
                    text: '导出Pdf',
                    click: function () {
                        sectionOverspeed.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
                    },
                    img: '../resources/css/x16/pdf.png'
                }]
            },
            toolbarShowInLeft: false,
            width: '98%',
            url: '../statistics/sectionOverspeedDetail',
            title: '车辆路段超速明细',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            detail: {
                onShowDetail: sectionOverspeed.showDetailList,
                height: 'auto'
            },
            parms: {
                motorcadeId: row.motorcadeId,
                motorcade: row.motorcade,
                start: start,
                end: end
            }
        });
    },
    showDetailList: function (row, detailPanel, callback) {
        var grid = document.createElement('div');
        $(detailPanel).append(grid);
        $(grid).css('margin', 10).ligerGrid({
            columns: [{
                display: '路段',
                name: 'section',
                align: 'left',
                width: 180
            }, {
                display: '超速次数',
                name: 'times'
            }, {
                display: '超速时长',
                name: 'duration',
                render: function (row) {
                    return common.timespan(row.duration);
                }
            }],
            data: {
                rows: row.detail
            },
            width: '98%',
            title: '路段明细',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            usePager: false
        });
    },
    drawChart: function (data) {
        var rows = data.rows;
        var motorcadeTimes = {};
        for (var x = 0; x < rows.length; x++) {
            var row = rows[x];
            motorcadeTimes[row.motorcade] = (motorcadeTimes[row.motorcade] || 0) + row.times;
        }
        var motorcades = [];
        for (var attr in motorcadeTimes) {
            motorcades.push({
                name: attr,
                y: motorcadeTimes[attr]
            });
        }
        $('#chart').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '车辆路段超速'
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
    },
    countExporting: function (type) {
        var form = $('#exportForm');
        form.remove();

        form = $('<form id="exportForm">');// 定义一个form表单
        form.attr('method', 'post');
        form.attr('action', 'sectionOverspeedCountExport');

        var txtMotorcade = $('<input>');
        txtMotorcade.attr('type', 'hidden');
        txtMotorcade.attr('name', 'motorcade');
        txtMotorcade.attr('value', sectionOverspeed.gridSectionOverspeedParms.motorcade);

        var txtStart = $('<input>');
        txtStart.attr('type', 'hidden');
        txtStart.attr('name', 'start');
        txtStart.attr('value', sectionOverspeed.gridSectionOverspeedParms.start);

        var txtEnd = $('<input>');
        txtEnd.attr('type', 'hidden');
        txtEnd.attr('name', 'end');
        txtEnd.attr('value', sectionOverspeed.gridSectionOverspeedParms.end);

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
        form.attr('action', 'sectionOverspeedDetailExport');

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

    sectionOverspeed.gridSectionOverspeedParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
    sectionOverspeed.gridSectionOverspeedParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

    sectionOverspeed.startDate = $('#txtStartDate').ligerDateEditor({
        width: 100,
        format: 'yyyy-MM-dd',
        cancelable: true,
        initValue: startDate.toDateTimeString('yyyy-MM-dd')
    });
    sectionOverspeed.endDate = $('#txtEndDate').ligerDateEditor({
        width: 100,
        format: 'yyyy-MM-dd',
        cancelable: true,
        initValue: endDate.toDateTimeString('yyyy-MM-dd')
    });
    sectionOverspeed.query();

    $('#btnQuery').click(function () {
        sectionOverspeed.gridSectionOverspeedParms.motorcade = $('#txtMotorcade').val();
        sectionOverspeed.gridSectionOverspeedParms.start = sectionOverspeed.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        sectionOverspeed.gridSectionOverspeedParms.end = sectionOverspeed.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        sectionOverspeed.query();
    });
    $('#btnExportExcel').click(function () {
        sectionOverspeed.countExporting('excel');
    });

    $('#btnExportPdf').click(function () {
        sectionOverspeed.countExporting('pdf');
    });
})