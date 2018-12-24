/**
 * 外部电源供电日志
 */
window.powerLog = {
	gridPowerLogParms : {
		start : null,
		end : null
	},
	query : function() {
		if (!powerLog.gridPowerLog) {
			powerLog.gridPowerLog = $("#gridPowerLog").ligerGrid({
				columns : [ {
					display : '时间',
					name : 'time',
					type : 'date'
				}, {
					display : '事件',
					name : 'event'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/powerLog/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : powerLog.gridPowerLogParms
			});
		} else {
			powerLog.gridPowerLog.changePage('first');
			powerLog.gridPowerLog.reload();
		}
	}
}
$(function() {
	powerLog.vehicle = parent.deviceData.vehicle;
	powerLog.deviceNumber = parent.deviceData.deviceNumber;
	powerLog.plateNumber = parent.deviceData.plateNumber;

	powerLog.gridPowerLogParms.deviceNumber = powerLog.deviceNumber;
	var endDate = new Date().addDays(1);
	powerLog.gridPowerLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
	var startDate = endDate.addDays(-7);
	powerLog.gridPowerLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

	powerLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd")
	});

	powerLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd")
	});

	powerLog.query();

	$('#btnQuery').click(function() {
		var start = powerLog.txtStartDate.getValue();
		var end = powerLog.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		powerLog.gridPowerLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
		powerLog.gridPowerLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
		powerLog.query();
	});
});