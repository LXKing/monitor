/**
 * 车辆里程油耗
 */
window.mileageOil = {
    gridMileageOilParms: {
        motorcade: '',
        start: null,
        end: null
    },
    query: function () {
        if (!mileageOil.gridMileageOil)
            mileageOil.gridMileageOil = $('#gridMileageOil').ligerGrid({
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
                    name: 'vehicles'
                }, {
                    display: '行驶里程(KM)',
                    name: 'mileages',
                    render: function (row) {
                        if (row.mileages === 0)
                            return '0';
                        return common.round(row.mileages, 3) + '';
                    }
                }, {
                    display: '车辆油耗(L)',
                    name: 'oils',
                    render: function (row) {
                        if (row.oils === 0)
                            return '0';
                        return common.round(row.oils, 1) + '';
                    }
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../statistics/mileageOilCount',
                width: '100%',
                height: '100%',
                pageSize: 30,
                parms: mileageOil.gridMileageOilParms,
                detail: {
                    onShowDetail: mileageOil.showDetail,
                    height: 'auto'
                },
                onSuccess: function (data) {
                    mileageOil.drawChart(data);
                }
            });
        else {
            mileageOil.gridMileageOil.changePage('first');
            mileageOil.gridMileageOil.reload();
        }
    },
    showDetail: function (row, detailPanel, callback) {
        var start = mileageOil.gridMileageOilParms.start;
        var end = mileageOil.gridMileageOilParms.end;
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
                display: '行驶里程(KM)',
                name: 'mileages',
                render: function (row) {
                    if (row.mileages === 0)
                        return '0';
                    return common.round(row.mileages, 3) + '';
                }
            }, {
                display: '车辆油耗(L)',
                name: 'oils',
                render: function (row) {
                    if (row.oils === 0)
                        return '0';
                    return common.round(row.oils, 1) + '';
                }
            }],
            toolbar: {
                items: [{
                    text: '导出Excel',
                    click: function () {
                        mileageOil.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
                    },
                    img: '../resources/css/x16/excel.png'
                }, {
                    text: '导出Pdf',
                    click: function () {
                        mileageOil.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
                    },
                    img: '../resources/css/x16/pdf.png'
                }]
            },
            toolbarShowInLeft: false,
            width: '95%',
            url: '../statistics/mileageOilDetail',
            title: '车辆里程油耗明细',
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
        var mileages = {
            name: '里程',
            data: []
        };
        var oils = {
            name: '油耗',
            data: []
        };

        for (var x = 0; x < rows.length; x++) {
            categories.push(rows[x].motorcade);
            mileages.data.push(common.round(rows[x].mileages, 3));
            oils.data.push(common.round(rows[x].oils, 1));
        }
        $('#chart').highcharts(
            {
                chart: {
                    type: 'column'
                },
                title: {
                    text: '行驶里程及油耗'
                },
                xAxis: {
                    categories: categories
                },
                yAxis: [{
                    labels: {
                        format: '{value}公里',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    title: {
                        text: '公里',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    }
                }, {
                    labels: {
                        format: '{value}升',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    title: {
                        text: '升',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    opposite: true
                }],
                credits: {
                    enabled: false
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
                    + '<td style="padding:0"><b>{point.y}</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                series: [mileages, oils]
            });
    },
    countExporting: function (type) {
        var form = $('#exportForm');
        form.remove();

        form = $('<form id="exportForm">');// 定义一个form表单
        form.attr('method', 'post');
        form.attr('action', 'mileageOilCountExport');

        var txtMotorcade = $('<input>');
        txtMotorcade.attr('type', 'hidden');
        txtMotorcade.attr('name', 'motorcade');
        txtMotorcade.attr('value', mileageOil.gridMileageOilParms.motorcade);

        var txtStart = $('<input>');
        txtStart.attr('type', 'hidden');
        txtStart.attr('name', 'start');
        txtStart.attr('value', mileageOil.gridMileageOilParms.start);

        var txtEnd = $('<input>');
        txtEnd.attr('type', 'hidden');
        txtEnd.attr('name', 'end');
        txtEnd.attr('value', mileageOil.gridMileageOilParms.end);

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
        form.attr('action', 'mileageOilDetailExport');

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

    mileageOil.gridMileageOilParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
    mileageOil.gridMileageOilParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

    mileageOil.startDate = $('#txtStartDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: startDate.toDateTimeString('yyyy-MM-dd')
    });
    mileageOil.endDate = $('#txtEndDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: endDate.toDateTimeString('yyyy-MM-dd')
    });
    mileageOil.query();

    $('#btnQuery').click(function () {
        mileageOil.gridMileageOilParms.motorcade = $('#txtMotorcade').val();
        mileageOil.gridMileageOilParms.start = mileageOil.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        mileageOil.gridMileageOilParms.end = mileageOil.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
        mileageOil.query();
    });
    $('#btnExportExcel').click(function () {
        mileageOil.countExporting('excel');
    });
    $('#btnExportPdf').click(function () {
        mileageOil.countExporting('pdf');
    });
})