/**
 * 驾驶员登签日志
 */
window.loginLog = {
	gridLoginLogParms : {
		start : null,
		end : null
	},
	query : function() {
		if (!loginLog.gridLoginLog) {
			loginLog.gridLoginLog = $("#gridLoginLog").ligerGrid({
				columns : [ {
					display : '时间',
					name : 'time',
					type : 'date'
				}, {
					display : '驾驶员',
					name : 'driver'
				}, {
					display : '驾驶证号',
					name : 'license'
				}, {
					display : '事件',
					name : 'event'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/loginLog/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : loginLog.gridLoginLogParms
			});
		} else {
			loginLog.gridLoginLog.changePage('first');
			loginLog.gridLoginLog.reload();
		}
	}
}
$(function() {
	loginLog.vehicle = parent.deviceData.vehicle;
	loginLog.deviceNumber = parent.deviceData.deviceNumber;
	loginLog.plateNumber = parent.deviceData.plateNumber;

	loginLog.gridLoginLogParms.deviceNumber = loginLog.deviceNumber;
	var endDate = new Date().addDays(1);
	loginLog.gridLoginLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
	var startDate = endDate.addDays(-7);
	loginLog.gridLoginLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

	loginLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd")
	});

	loginLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd")
	});

	loginLog.query();

	$('#btnQuery').click(function() {
		var start = loginLog.txtStartDate.getValue();
		var end = loginLog.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		loginLog.gridLoginLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
		loginLog.gridLoginLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
		loginLog.query();
	});
});