/**
 * 参数修改日志
 */
window.parameterLog = {
	gridParameterLogParms : {
		start : null,
		end : null
	},
	query : function() {
		if (!parameterLog.gridParameterLog) {
			parameterLog.gridParameterLog = $("#gridParameterLog").ligerGrid({
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
				url : '../deviceData/parameterLog/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : parameterLog.gridParameterLogParms
			});
		} else {
			parameterLog.gridParameterLog.changePage('first');
			parameterLog.gridParameterLog.reload();
		}
	}
}
$(function() {
	parameterLog.vehicle = parent.deviceData.vehicle;
	parameterLog.deviceNumber = parent.deviceData.deviceNumber;
	parameterLog.plateNumber = parent.deviceData.plateNumber;

	parameterLog.gridParameterLogParms.deviceNumber = parameterLog.deviceNumber;
	var endDate = new Date().addDays(1);
	parameterLog.gridParameterLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
	var startDate = endDate.addDays(-7);
	parameterLog.gridParameterLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

	parameterLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd")
	});

	parameterLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd")
	});

	parameterLog.query();

	$('#btnQuery').click(function() {
		var start = parameterLog.txtStartDate.getValue();
		var end = parameterLog.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		parameterLog.gridParameterLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
		parameterLog.gridParameterLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
		parameterLog.query();
	});
});