/**
 * 多媒体事件报告
 */
window.multimediaEventReport = {
    deviceNumber: null,
    plateNumber: null,
    gridMultimediaEventReport: null,
    gridMultimediaEventReportParms: {
        deviceNumber: null,
        start: null,
        end: null
    },
    query: function () {
        if (!multimediaEventReport.gridMultimediaEventReport) {
            multimediaEventReport.gridMultimediaEventReport = $("#gridMultimediaEventReport").ligerGrid({
                columns: [{
                    display: '时间',
                    name: 'time',
                    align: 'left',
                    width: 120
                }, {
                    display: '媒体id',
                    name: 'mediaId',
                    align: 'left',
                    width: 50
                }, {
                    display: '媒体类型',
                    name: 'mediaType',
                    align: 'left',
                    width: 50
                }, {
                    display: '格式类型',
                    name: 'formatType',
                    align: 'left',
                    width: 50
                }, {
                    display: '事件类型',
                    name: 'eventType',
                    width: 80
                }, {
                    display: '通道',
                    name: 'channelId',
                    width: 50
                }, {
                    display: '操作',
                    isAllowHide: false,
                    width: 100,
                    render: function (row) {
                        var html = '<a href="#" onclick="multimediaEventReport.pickup(\'' + row.number + '\',' + row.mediaId + ')">提取</a>';
                        return html;
                    }
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../deviceData/multimediaEventReport/query',
                width: '100%',
                height: '100%',
                pageSize: 30,
                rownumbers: true,
                parms: multimediaEventReport.gridMultimediaEventReportParms,
                onSelectRow: function (row) {
                    multimediaEventReport.showMultimediaEventReport(row);
                }
            });
        } else {
            multimediaEventReport.gridMultimediaEventReport.changePage('first');
            multimediaEventReport.gridMultimediaEventReport.reload();
        }
    },
    pickup: function (deviceNumber, mediaId) {
        $.post('../deviceData/pickupMultmedia', {
            deviceNumber: deviceNumber,
            mediaId: mediaId
        }, function (r) {
            if (r.code == 0) {
                common.tip('success', '指令已发送！', 3);
            } else
                common.tip('error', r.error, 3);
        });
    },
    layout: function () {
        var height = $(document).height();
        var centerHeight = height * 0.7;
        var bottomHeight = height - centerHeight;
        $('#divCenter').height(centerHeight);
        $('#divBottom').height(bottomHeight);
    }
}
$(function () {
    multimediaEventReport.layout();
    multimediaEventReport.vehicle = parent.deviceData.vehicle;
    multimediaEventReport.deviceNumber = parent.deviceData.deviceNumber;
    multimediaEventReport.plateNumber = parent.deviceData.plateNumber;

    multimediaEventReport.gridMultimediaEventReportParms.deviceNumber = multimediaEventReport.deviceNumber;
    var endDate = new Date().addDays(1);
    ;
    multimediaEventReport.gridMultimediaEventReportParms.end = endDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");
    var startDate = endDate.addDays(-7);
    multimediaEventReport.gridMultimediaEventReportParms.start = startDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");

    multimediaEventReport.txtStartDate = $('#txtStartDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        showTime: true,
        label: '开始时间',
        labelWidth: 65,
        initValue: startDate.toDateTimeString("yyyy-MM-dd hh:mm")
    });

    multimediaEventReport.txtEndDate = $('#txtEndDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        showTime: true,
        label: '结束时间',
        labelWidth: 65,
        initValue: endDate.toDateTimeString("yyyy-MM-dd hh:mm")
    });

    multimediaEventReport.query();

    $('#btnQuery').click(function () {
        var start = multimediaEventReport.txtStartDate.getValue();
        var end = multimediaEventReport.txtEndDate.getValue();
        if (start == null || end == null) {
            $.ligerDialog.error('请选择时间段！');
            return;
        }
        multimediaEventReport.gridMultimediaEventReportParms.start = start.toDateTimeString("yyyy-MM-dd hh:mm:ss");
        multimediaEventReport.gridMultimediaEventReportParms.end = end.toDateTimeString("yyyy-MM-dd hh:mm:ss");
        multimediaEventReport.query();
    });
});