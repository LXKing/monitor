/**
 * 终端升级结果报告
 */
window.deviceUpgradeResultReport = {
    deviceNumber: null,
    plateNumber: null,
    gridDeviceUpgradeResultReport: null,
    gridDeviceUpgradeResultReportParms: {
        deviceNumber: null,
        start: null,
        end: null
    },
    query: function () {
        if (!deviceUpgradeResultReport.gridDeviceUpgradeResultReport) {
            deviceUpgradeResultReport.gridDeviceUpgradeResultReport = $("#gridDeviceUpgradeResultReport").ligerGrid({
                columns: [{
                    display: '时间',
                    name: 'time',
                    width: 150,
                    type: 'date'
                }, {
                    display: '升级结果',
                    name: 'result',
                    width: 80
                }, {
                    display: '升级类型',
                    name: 'type'
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../deviceData/deviceUpgradeResultReport/query',
                width: '100%',
                height: '100%',
                pageSize: 30,
                rownumbers: true,
                parms: deviceUpgradeResultReport.gridDeviceUpgradeResultReportParms
            });
        } else {
            deviceUpgradeResultReport.gridDeviceUpgradeResultReport.changePage('first');
            deviceUpgradeResultReport.gridDeviceUpgradeResultReport.reload();
        }
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
    deviceUpgradeResultReport.layout();
    deviceUpgradeResultReport.vehicle = parent.deviceData.vehicle;
    deviceUpgradeResultReport.deviceNumber = parent.deviceData.deviceNumber;
    deviceUpgradeResultReport.plateNumber = parent.deviceData.plateNumber;

    deviceUpgradeResultReport.gridDeviceUpgradeResultReportParms.deviceNumber = deviceUpgradeResultReport.deviceNumber;
    var endDate = new Date().addDays(1);
    ;
    deviceUpgradeResultReport.gridDeviceUpgradeResultReportParms.end = endDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");
    var startDate = endDate.addDays(-7);
    deviceUpgradeResultReport.gridDeviceUpgradeResultReportParms.start = startDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");

    deviceUpgradeResultReport.txtStartDate = $('#txtStartDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        showTime: true,
        label: '开始时间',
        labelWidth: 65,
        initValue: startDate.toDateTimeString("yyyy-MM-dd hh:mm")
    });

    deviceUpgradeResultReport.txtEndDate = $('#txtEndDate').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm",
        cancelable: true,
        showTime: true,
        label: '结束时间',
        labelWidth: 65,
        initValue: endDate.toDateTimeString("yyyy-MM-dd hh:mm")
    });

    deviceUpgradeResultReport.query();

    $('#btnQuery').click(function () {
        var start = deviceUpgradeResultReport.txtStartDate.getValue();
        var end = deviceUpgradeResultReport.txtEndDate.getValue();
        if (start == null || end == null) {
            $.ligerDialog.error('请选择时间段！');
            return;
        }
        deviceUpgradeResultReport.gridDeviceUpgradeResultReportParms.start = start.toDateTimeString("yyyy-MM-dd hh:mm:ss");
        deviceUpgradeResultReport.gridDeviceUpgradeResultReportParms.end = end.toDateTimeString("yyyy-MM-dd hh:mm:ss");
        deviceUpgradeResultReport.query();
    });
});