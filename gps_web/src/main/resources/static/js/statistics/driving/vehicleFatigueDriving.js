/**
 * 车辆疲劳驾驶
 */
window.vehicleFatigueDriving = {
	gridVehicleFatigueDrivingParms : {
		motorcade : '',
		start : null,
		end : null
	},
	query : function() {
		if (!vehicleFatigueDriving.gridVehicleFatigueDriving)
			vehicleFatigueDriving.gridVehicleFatigueDriving = $('#gridVehicleFatigueDriving').ligerGrid({
				columns : [ {
					display : '车队',
					name : 'motorcade',
					align : 'left',
					width : 150
				}, {
					display : '疲劳驾驶次数',
					name : 'times'
				}, {
					display : '疲劳合计里程',
					name : 'mileages',
					render : function(row) {
						if (row.mileages === 0)
							return '0';
						return common.round(row.mileages, 3) + '';
					}
				}, {
					display : '疲劳时长',
					name : 'duration',
					render : function(row) {
						return common.timespan(row.duration);
					}
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
				url : '../statistics/vehicleFatigueDrivingCount',
				width : '100%',
				height : '100%',
				pageSize : 30,
				parms : vehicleFatigueDriving.gridVehicleFatigueDrivingParms,
				detail : {
					onShowDetail : vehicleFatigueDriving.showDetail,
					height : 'auto'
				},
				onSuccess : function(data) {
					vehicleFatigueDriving.drawChart(data);
				}
			});
		else {
			vehicleFatigueDriving.gridVehicleFatigueDriving.changePage('first');
			vehicleFatigueDriving.gridVehicleFatigueDriving.reload();
		}
	},
	showDetail : function(row, detailPanel, callback) {
		var start = vehicleFatigueDriving.gridVehicleFatigueDrivingParms.start;
		var end = vehicleFatigueDriving.gridVehicleFatigueDrivingParms.end;
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
				display : '疲劳驾驶次数',
				name : 'times'
			}, {
				display : '疲劳合计里程',
				name : 'mileages',
				render : function(row) {
					if (row.mileages === 0)
						return '0';
					return common.round(row.mileages, 3) + '';
				}
			}, {
				display : '疲劳时长',
				name : 'duration',
				render : function(row) {
					return common.timespan(row.duration);
				}
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
						vehicleFatigueDriving.detailExporting(row.motorcadeId, row.motorcade, start, end, 'excel');
					},
					img : '../resources/css/x16/excel.png'
				}, {
					text : '导出Pdf',
					click : function() {
						vehicleFatigueDriving.detailExporting(row.motorcadeId, row.motorcade, start, end, 'pdf');
					},
					img : '../resources/css/x16/pdf.png'
				} ]
			},
			toolbarShowInLeft : false,
			width : '98%',
			url : '../statistics/vehicleFatigueDrivingDetail',
			title : '车辆疲劳驾驶明细',
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
		var motorcades = [];
		var times = {
			name : '次数',
			data : []
		};
		var mileages = {
			name : '里程',
			data : []
		};
		for (var x = 0; x < rows.length; x++) {
			motorcades.push(rows[x].motorcade);
			times.data.push(rows[x].times);
			mileages.data.push(common.round(rows[x].mileages, 3));
		}
		$('#chart').highcharts(
				{
					chart : {
						type : 'column'
					},
					title : {
						text : '车辆疲劳驾驶'
					},
					xAxis : {
						categories : motorcades
					},
					yAxis : [ {
						labels : {
							format : '{value}次',
							style : {
								color : Highcharts.getOptions().colors[0]
							}
						},
						title : {
							text : '次',
							style : {
								color : Highcharts.getOptions().colors[0]
							}
						}
					}, {
						labels : {
							format : '{value}km',
							style : {
								color : Highcharts.getOptions().colors[1]
							}
						},
						title : {
							text : 'km',
							style : {
								color : Highcharts.getOptions().colors[1]
							}
						},
						opposite : true
					} ],
					credits : {
						enabled : false
					},
					tooltip : {
						headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
						pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
								+ '<td style="padding:0"><b>{point.y}</b></td></tr>',
						footerFormat : '</table>',
						shared : true,
						useHTML : true
					},
					series : [ times, mileages ]
				});
	},
	countExporting : function(type) {
		var form = $('#exportForm');
		form.remove();

		form = $('<form id="exportForm">');// 定义一个form表单
		form.attr('method', 'post');
		form.attr('action', 'vehicleFatigueDrivingCountExport');

		var txtMotorcade = $('<input>');
		txtMotorcade.attr('type', 'hidden');
		txtMotorcade.attr('name', 'motorcade');
		txtMotorcade.attr('value', vehicleFatigueDriving.gridVehicleFatigueDrivingParms.motorcade);

		var txtStart = $('<input>');
		txtStart.attr('type', 'hidden');
		txtStart.attr('name', 'start');
		txtStart.attr('value', vehicleFatigueDriving.gridVehicleFatigueDrivingParms.start);

		var txtEnd = $('<input>');
		txtEnd.attr('type', 'hidden');
		txtEnd.attr('name', 'end');
		txtEnd.attr('value', vehicleFatigueDriving.gridVehicleFatigueDrivingParms.end);

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
		form.attr('action', 'vehicleFatigueDrivingDetailExport');

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

	vehicleFatigueDriving.gridVehicleFatigueDrivingParms.start = startDate.toDateTimeString('yyyy-MM-dd 00:00:00');
	vehicleFatigueDriving.gridVehicleFatigueDrivingParms.end = endDate.toDateTimeString('yyyy-MM-dd 00:00:00');

	vehicleFatigueDriving.startDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : 'yyyy-MM-dd',
		cancelable : true,
		initValue : startDate.toDateTimeString('yyyy-MM-dd')
	});
	vehicleFatigueDriving.endDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : 'yyyy-MM-dd',
		cancelable : true,
		initValue : endDate.toDateTimeString('yyyy-MM-dd')
	});
	vehicleFatigueDriving.query();

	$('#btnQuery').click(
			function() {
				vehicleFatigueDriving.gridVehicleFatigueDrivingParms.motorcade = $('#txtMotorcade').val();
				vehicleFatigueDriving.gridVehicleFatigueDrivingParms.start = vehicleFatigueDriving.startDate.getValue().toDateTimeString(
						'yyyy-MM-dd 00:00:00');
				vehicleFatigueDriving.gridVehicleFatigueDrivingParms.end = vehicleFatigueDriving.endDate.getValue().toDateTimeString(
						'yyyy-MM-dd 00:00:00');
				vehicleFatigueDriving.query();
			});
	$('#btnExportExcel').click(function() {
		vehicleFatigueDriving.countExporting('excel');
	});

	$('#btnExportPdf').click(function() {
		vehicleFatigueDriving.countExporting('pdf');
	});
})