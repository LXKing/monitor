/**
 * 当前上线率统计
 */
window.currentVehicleOnline = {
	gridCurrentVehicleOnlineParms : {
		motorcade : ''
	},
	query : function() {
		if (!currentVehicleOnline.gridCurrentVehicleOnline)
			currentVehicleOnline.gridCurrentVehicleOnline = $("#gridCurrentVehicleOnline").ligerGrid({
				columns : [ {
					display : '车队',
					name : 'motorcade',
					align : 'left',
					width : 150
				}, {
					display : '车辆总数',
					name : 'total'
				}, {
					display : '上线数量',
					name : 'online'
				}, {
					display : '上线率',
					name : 'onlineRate',
					render : function(row) {
						return row.onlineRate + '%';
					}
				}, {
					display : '下线数量',
					name : 'offline'
				}, {
					display : '下线率',
					name : 'offlineRate',
					render : function(row) {
						return row.offlineRate + '%';
					}
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../statistics/currentOnlineOfflineCount',
				width : '100%',
				height : '100%',
				pageSize : 30,
				parms : currentVehicleOnline.gridCurrentVehicleOnlineParms,
				detail : {
					onShowDetail : currentVehicleOnline.showDetail,
					height : 'auto'
				},
				onSuccess : function(data) {
					currentVehicleOnline.drawChart(data);
				}
			});
		else {
			currentVehicleOnline.gridCurrentVehicleOnline.changePage('first');
			currentVehicleOnline.gridCurrentVehicleOnline.reload();
		}
	},
	showDetail : function(row, detailPanel, callback) {
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
				display : '在线情况',
				name : 'online',
				render : function(row) {
					if (row.online == true)
						return '上线';
					else
						return '下线';
				}
			} ],
			toolbar : {
				items : [ {
					text : '导出Excel',
					click : function() {
						currentVehicleOnline.detailExporting(row.motorcadeId, row.motorcade, 'excel');
					},
					img : '../resources/css/x16/excel.png'
				}, {
					text : '导出Pdf',
					click : function() {
						currentVehicleOnline.detailExporting(row.motorcadeId, row.motorcade, 'pdf');
					},
					img : '../resources/css/x16/pdf.png'
				} ]
			},
			toolbarShowInLeft : false,
			width : '95%',
			url : '../statistics/currentOnlineOfflineDetail',
			title : '车辆上线率明细',
			showTitle : true,
			columnWidth : 100,
			onAfterShowData : callback,
			root : 'rows',
			record : 'total',
			pageParmName : 'pageIndex',
			pagesizeParmName : 'pageSize',
			parms : {
				motorcadeId : row.motorcadeId,
				motorcade : row.motorcade
			}
		});
	},
	drawChart : function(data) {
		var rows = data.rows;
		var categories = [];
		var onlines = {
			name : '上线',
			data : []
		};
		var offlines = {
			name : '下线',
			data : []
		};

		for (var x = 0; x < rows.length; x++) {
			categories.push(rows[x].motorcade);
			onlines.data.push(rows[x].online);
			offlines.data.push(rows[x].offline);
		}
		$('#chart').highcharts({
			chart : {
				type : 'column'
			},
			title : {
				text : '车辆当前上线统计'
			},
			xAxis : {
				categories : categories
			},
			yAxis : {
				min : 0,
				title : {
					text : '辆'
				}
			},
			credits : {
				enabled : false
			},
			tooltip : {
				valueSuffix : ' 辆'
			},
			series : [ onlines, offlines ]
		});
	},
	countExporting : function(type) {
		var form = $('#exportForm');
		form.remove();

		form = $('<form id="exportForm">');// 定义一个form表单
		form.attr('method', 'post');
		form.attr('action', 'currentOnlineOfflineCountExport');

		var txtMotorcade = $('<input>');
		txtMotorcade.attr('type', 'hidden');
		txtMotorcade.attr('name', 'motorcade');
		txtMotorcade.attr('value', currentVehicleOnline.gridCurrentVehicleOnlineParms.motorcade);

		var txtType = $('<input>');
		txtType.attr('type', 'hidden');
		txtType.attr('name', 'type');
		txtType.attr('value', type);

		$('body').append(form);// 将表单放置在web中
		form.append(txtMotorcade);
		form.append(txtType);
		form.submit();// 表单提交
	},
	detailExporting : function(motorcadeId, motorcade, type) {
		var form = $('#exportForm');
		form.remove();

		form = $('<form id="exportForm">');// 定义一个form表单
		form.attr('method', 'post');
		form.attr('action', 'currentOnlineOfflineDetailExport');

		var txtMotorcadeId = $('<input>');
		txtMotorcadeId.attr('type', 'hidden');
		txtMotorcadeId.attr('name', 'motorcadeId');
		txtMotorcadeId.attr('value', motorcadeId);

		var txtMotorcade = $('<input>');
		txtMotorcade.attr('type', 'hidden');
		txtMotorcade.attr('name', 'motorcade');
		txtMotorcade.attr('value', motorcade);

		var txtType = $('<input>');
		txtType.attr('type', 'hidden');
		txtType.attr('name', 'type');
		txtType.attr('value', type);

		$('body').append(form);// 将表单放置在web中
		form.append(txtMotorcadeId);
		form.append(txtMotorcade);
		form.append(txtType);
		form.submit();// 表单提交
	}
}
$(function() {
	currentVehicleOnline.query();

	$('#btnQuery').click(function() {
		currentVehicleOnline.gridCurrentVehicleOnlineParms.motorcade = $('#txtMotorcade').val();
		currentVehicleOnline.query();
	});

	$('#btnExportExcel').click(function() {
		currentVehicleOnline.countExporting('excel');
	});

	$('#btnExportPdf').click(function() {
		currentVehicleOnline.countExporting('pdf');
	});
})