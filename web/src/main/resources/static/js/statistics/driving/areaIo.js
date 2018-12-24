/**
 * 车辆进出区域
 */
window.areaIo = {
	gridAreaIoParms : {
		motorcade : '',
		start : null,
		end : null
	},
	query : function() {
		if (!areaIo.gridAreaIo)
			areaIo.gridAreaIo = $('#gridAreaIo').ligerGrid({
				columns : [ {
					display : '车队',
					name : 'motorcade',
					align : 'left',
					width : 150
				}, {
					display : '进区域次数',
					name : 'in'
				}, {
					display : '出区域次数',
					name : 'out'
				}, {
					display : '开始时间',
					name : 'start',
					align : 'left',
					type : 'date',
					width : 120
				}, {
					display : '结束时间',
					name : 'end',
					align : 'left',
					type : 'date',
					width : 120
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../statistics/areaIoCount',
				width : '100%',
				height : '100%',
				pageSize : 30,
				parms : areaIo.gridAreaIoParms,
				detail : {
					onShowDetail : areaIo.showDetail,
					height : 'auto'
				},
				onSuccess : function(data) {
					areaIo.drawChart(data);
				}
			});
		else {
			areaIo.gridAreaIo.changePage('first');
			areaIo.gridAreaIo.reload();
		}
	},
	showDetail : function(row, detailPanel, callback) {
		var start = areaIo.gridAreaIoParms.start;
		var end = areaIo.gridAreaIoParms.end;
		var grid = document.createElement('div');
		$(detailPanel).append(grid);
		$(grid).css('margin', 10).ligerGrid({
			columns : [ {
				display : '车队',
				name : 'motorcade',
				align : 'left',
				width : 150,
				frozen : true
			}, {
				display : '车牌号',
				name : 'plateNumber',
				align : 'left',
				width : 150
			}, {
				display : '进区域次数',
				name : 'in'
			}, {
				display : '出区域次数',
				name : 'out'
			}, {
				display : '开始时间',
				name : 'start',
				align : 'left',
				type : 'date',
				width : 120
			}, {
				display : '结束时间',
				name : 'end',
				align : 'left',
				type : 'date',
				width : 120
			} ],
			toolbar : {
				items : [ {
					text : '导出Excel',
					click : function() {
						areaIo.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
					},
					img : '../resources/css/x16/excel.png'
				}, {
					text : '导出Pdf',
					click : function() {
						areaIo.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
					},
					img : '../resources/css/x16/pdf.png'
				} ]
			},
			toolbarShowInLeft : false,
			width : '98%',
			url : '../statistics/areaIoDetail',
			title : '车辆进出区域明细',
			showTitle : true,
			columnWidth : 100,
			onAfterShowData : callback,
			root : 'rows',
			record : 'total',
			pageParmName : 'pageIndex',
			pagesizeParmName : 'pageSize',
			parms : {
				motorcadeId : row.motorcadeId,
				motorcade : row.motorcade,
				start : start,
				end : end
			}
		});
	},
	drawChart : function(data) {
		var rows = data.rows;
		var categories = [];
		var ins = {
			name : '进区域',
			data : []
		};
		var outs = {
			name : '出区域',
			data : []
		};

		for (var x = 0; x < rows.length; x++) {
			categories.push(rows[x].motorcade);
			ins.data.push(rows[x]['in']);
			outs.data.push(rows[x].out);
		}
		$('#chart').highcharts({
			chart : {
				type : 'column'
			},
			title : {
				text : '车辆进出区域'
			},
			xAxis : {
				categories : categories
			},
			yAxis : {
				min : 0,
				title : {
					text : '次'
				}
			},
			credits : {
				enabled : false
			},
			tooltip : {
				valueSuffix : ' 次'
			},
			series : [ ins, outs ]
		});
	},
	countExporting : function(type) {
		var form = $('#exportForm');
		form.remove();

		form = $('<form id="exportForm">');// 定义一个form表单
		form.attr('method', 'post');
		form.attr('action', 'areaIoCountExport');

		var txtMotorcade = $('<input>');
		txtMotorcade.attr('type', 'hidden');
		txtMotorcade.attr('name', 'motorcade');
		txtMotorcade.attr('value', areaIo.gridAreaIoParms.motorcade);

		var txtStart = $('<input>');
		txtStart.attr('type', 'hidden');
		txtStart.attr('name', 'start');
		txtStart.attr('value', areaIo.gridAreaIoParms.start);

		var txtEnd = $('<input>');
		txtEnd.attr('type', 'hidden');
		txtEnd.attr('name', 'end');
		txtEnd.attr('value', areaIo.gridAreaIoParms.end);

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
	detailExporting : function(motorcadeId, motorcade, start, end, type) {
		var form = $('#exportForm');
		form.remove();

		form = $('<form id="exportForm">');// 定义一个form表单
		form.attr('method', 'post');
		form.attr('action', 'areaIoDetailExport');

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
$(function() {
	var now = new Date();
	var startDate = now.addDays(-7);
	var endDate = now.addDays(1);

	areaIo.gridAreaIoParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
	areaIo.gridAreaIoParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

	areaIo.startDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : 'yyyy-MM-dd',
		cancelable : true,
		initValue : startDate.toDateTimeString('yyyy-MM-dd')
	});
	areaIo.endDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : 'yyyy-MM-dd',
		cancelable : true,
		initValue : endDate.toDateTimeString('yyyy-MM-dd')
	});
	areaIo.query();

	$('#btnQuery').click(function() {
		areaIo.gridAreaIoParms.motorcade = $('#txtMotorcade').val();
		areaIo.gridAreaIoParms.start = areaIo.startDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
		areaIo.gridAreaIoParms.end = areaIo.endDate.getValue().toDateTimeString('yyyy-MM-dd 00:00:00');
		areaIo.query();
	});
	$('#btnExportExcel').click(function() {
		areaIo.countExporting('excel');
	});

	$('#btnExportPdf').click(function() {
		areaIo.countExporting('pdf');
	});
})