/**
 * 操作日志
 */
window.operateLog = {
	gridOperateLogParms : {
		user : '',
		start : null,
		end : null
	},
	query : function() {
		if (!operateLog.gridOperateLog)
			operateLog.gridOperateLog = $('#gridOperateLog').ligerGrid({
				columns : [ {
					display : '时间',
					name : 'time',
					align : 'left',
					type : 'date',
					width : 120
				}, {
					display : '用户',
					name : 'user',
					width : 150
				}, {
					display : '操作',
					align : 'left',
					name : 'action'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../statistics/operateLog',
				width : '100%',
				height : '100%',
				pageSize : 30,
				parms : operateLog.gridOperateLogParms
			});
		else {
			operateLog.gridOperateLog.changePage('first');
			operateLog.gridOperateLog.reload();
		}
	},
	exporting : function(type) {
		var form = $('#exportForm');
		form.remove();

		form = $('<form id="exportForm">');// 定义一个form表单
		form.attr('method', 'post');
		form.attr('action', 'operateLogExport');

		var txtUser = $('<input>');
		txtUser.attr('type', 'hidden');
		txtUser.attr('name', 'user');
		txtUser.attr('value', operateLog.gridOperateLogParms.user);

		var txtStart = $('<input>');
		txtStart.attr('type', 'hidden');
		txtStart.attr('name', 'start');
		txtStart.attr('value', operateLog.gridOperateLogParms.start);

		var txtEnd = $('<input>');
		txtEnd.attr('type', 'hidden');
		txtEnd.attr('name', 'end');
		txtEnd.attr('value', operateLog.gridOperateLogParms.end);

		var txtType = $('<input>');
		txtType.attr('type', 'hidden');
		txtType.attr('name', 'type');
		txtType.attr('value', type);

		$('body').append(form);// 将表单放置在web中
		form.append(txtUser);
		form.append(txtStart);
		form.append(txtEnd);
		form.append(txtType);
		form.submit();// 表单提交
	},
}
$(function() {
	var now = new Date();
	var startDate = now;
	var endDate = now.addDays(1);

	operateLog.gridOperateLogParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
	operateLog.gridOperateLogParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

	operateLog.startDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : 'yyyy-MM-dd',
		cancelable : true,
		initValue : startDate.toDateTimeString('yyyy-MM-dd')
	});
	operateLog.endDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : 'yyyy-MM-dd',
		cancelable : true,
		initValue : endDate.toDateTimeString('yyyy-MM-dd')
	});
	operateLog.query();

	$('#btnQuery').click(function() {
		operateLog.gridOperateLogParms.user = $('#txtUser').val();
		operateLog.gridOperateLogParms.start = operateLog.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
		operateLog.gridOperateLogParms.end = operateLog.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
		operateLog.query();
	});
	$('#btnExportExcel').click(function() {
		operateLog.exporting('excel');
	});

	$('#btnExportPdf').click(function() {
		operateLog.exporting('pdf');
	});
})