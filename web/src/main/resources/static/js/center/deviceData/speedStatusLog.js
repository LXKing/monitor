/**
 * 速度状态日志
 */
window.speedStatusLog = {
	gridSpeedStatusLogParms : {
		start : null,
		end : null
	},
	query : function() {
		if (!speedStatusLog.gridSpeedStatusLog) {
			speedStatusLog.gridSpeedStatusLog = $("#gridSpeedStatusLog").ligerGrid({
				columns : [ {
					display : '开始时间',
					name : 'startTime',
					type : 'date'
				}, {
					display : '结束时间',
					name : 'endTime'
				}, {
					display : '状态',
					name : 'state'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/speedStatusLog/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : speedStatusLog.gridSpeedStatusLogParms,
				onSelectRow : function(row) {
					speedStatusLog.drawChart(row);
				}
			});
		} else {
			speedStatusLog.gridSpeedStatusLog.changePage('first');
			speedStatusLog.gridSpeedStatusLog.reload();
		}
	},
	drawChart : function(row) {
		var time = row.startTime.toDate().getTime();
		var speedLine = {
			type : 'line',
			name : '记录速度',
			data : []
		};
		var referLine = {
			type : 'line',
			name : '参考速度',
			data : []
		}
		for (var x = 0; x < row.content.length / 2; x++) {
			var speed = row.content[x * 2];
			var refer = row.content[x * 2 + 1];

			speedLine.data.push({
				x : time + x * 1000,
				y : speed
			});

			referLine.data.push({
				x : time + x * 1000,
				y : refer
			});
		}

		$('#chart').highcharts({
			chart : {
				zoomType : 'x'
			},
			title : {
				text : '速度状态日志',
				x : -20
			},
			subtitle : {
				text : '开始时间：' + row.startTime + " 结束时间：" + row.endTime,
				x : -20
			},
			xAxis : {
				type : 'datetime',
				tickPixelInterval : 50
			},
			yAxis : {
				min : 0,
				title : {
					text : '速度(km/h)'
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},
			legend : {
				enabled : false
			},
			tooltip : {
				shared : true,
				formatter : function() {
					var html = [];
					for (var i = 0; i < this.points.length; i++) {
						var p = this.points[i];
						if (html.length == 0)
							html.push('时间:' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', p.x));
						html.push('<br/>' + p.series.name + ':' + p.y + 'km/h');
					}
					return html.join('');
				}
			},
			plotOptions : {
				line : {
					lineWidth : 1
				}
			},
			credits : {
				enabled : false
			// 禁用版权信息
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'middle',
				borderWidth : 0
			},
			series : [ speedLine, referLine ]
		});
	}
}
$(function() {
	speedStatusLog.vehicle = parent.deviceData.vehicle;
	speedStatusLog.deviceNumber = parent.deviceData.deviceNumber;
	speedStatusLog.plateNumber = parent.deviceData.plateNumber;

	speedStatusLog.gridSpeedStatusLogParms.deviceNumber = speedStatusLog.deviceNumber;
	var endDate = new Date().addDays(1);
	speedStatusLog.gridSpeedStatusLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
	var startDate = endDate.addDays(-7);
	speedStatusLog.gridSpeedStatusLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

	speedStatusLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd")
	});

	speedStatusLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd")
	});

	speedStatusLog.query();

	speedStatusLog.drawChart({
		content : [],
		startTime : new Date().toDateTimeString(),
		endTime : new Date().toDateTimeString()
	});

	$('#btnQuery').click(function() {
		var start = speedStatusLog.txtStartDate.getValue();
		var end = speedStatusLog.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		speedStatusLog.gridSpeedStatusLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
		speedStatusLog.gridSpeedStatusLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
		speedStatusLog.query();
	});
});