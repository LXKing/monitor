/**
 * 终端事件报告
 */
window.deviceEventReport = {
	deviceNumber : null,
	plateNumber : null,
	gridDeviceEventReport : null,
	gridDeviceEventReportParms : {
		deviceNumber : null,
		start : null,
		end : null
	},
	query : function() {
		if (!deviceEventReport.gridDeviceEventReport) {
			deviceEventReport.gridDeviceEventReport = $("#gridDeviceEventReport").ligerGrid({
				columns : [ {
					display : '时间',
					name : 'time',
					width : 150,
					type : 'date'
				}, {
					display : '事件ID',
					name : 'eventId',
					width : 50
				}, {
					display : '事件内容',
					name : 'content' 
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/deviceEventReport/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : deviceEventReport.gridDeviceEventReportParms
			});
		} else {
			deviceEventReport.gridDeviceEventReport.changePage('first');
			deviceEventReport.gridDeviceEventReport.reload();
		}
	},
	layout : function() {
		var height = $(document).height();
		var centerHeight = height * 0.7;
		var bottomHeight = height - centerHeight;
		$('#divCenter').height(centerHeight);
		$('#divBottom').height(bottomHeight);
	}
}
$(function() {
	deviceEventReport.layout();
	deviceEventReport.vehicle = parent.deviceData.vehicle;
	deviceEventReport.deviceNumber = parent.deviceData.deviceNumber;
	deviceEventReport.plateNumber = parent.deviceData.plateNumber;

	deviceEventReport.gridDeviceEventReportParms.deviceNumber = deviceEventReport.deviceNumber;
	var endDate = new Date().addDays(1);;
	deviceEventReport.gridDeviceEventReportParms.end = endDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");
	var startDate = endDate.addDays(-7);
	deviceEventReport.gridDeviceEventReportParms.start = startDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");

	deviceEventReport.txtStartDate = $('#txtStartDate').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	deviceEventReport.txtEndDate = $('#txtEndDate').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	deviceEventReport.query();

	$('#btnQuery').click(function() {
		var start = deviceEventReport.txtStartDate.getValue();
		var end = deviceEventReport.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		deviceEventReport.gridDeviceEventReportParms.start = start.toDateTimeString("yyyy-MM-dd hh:mm:ss");
		deviceEventReport.gridDeviceEventReportParms.end = end.toDateTimeString("yyyy-MM-dd hh:mm:ss");
		deviceEventReport.query();
	});
});