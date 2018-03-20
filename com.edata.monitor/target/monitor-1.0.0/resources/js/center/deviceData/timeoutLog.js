/**
 * 超时驾驶日志
 */
window.timeoutLog = {
	map : null,
	gridTimeoutLogParms : {
		start : null,
		end : null
	},
	onMapLoaded : function(map) {
		timeoutLog.map = map;
	},
	query : function() {
		if (!timeoutLog.gridTimeoutLog) {
			timeoutLog.gridTimeoutLog = $("#gridTimeoutLog").ligerGrid({
				columns : [ {
					display : '开始时间',
					name : 'startTime',
					type : 'date',
					width : 120
				}, {
					display : '结束时间',
					name : 'endTime',
					type : 'date',
					width : 120
				}, {
					display : '驾驶证号',
					name : 'license',
					width : 150
				}, {
					display : '开始经度',
					name : 'startLng',
					width : 100
				}, {
					display : '开始纬度',
					name : 'startLat',
					width : 100
				}, {
					display : '开始地点',
					name : 'startAddr',
					width : 200
				}, {
					display : '结束经度',
					name : 'endLng',
					width : 100
				}, {
					display : '结束纬度',
					name : 'endLat',
					width : 100
				}, {
					display : '结束地点',
					name : 'endAddr',
					width : 200
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/timeoutLog/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				fixedCellHeight : false,
				parms : timeoutLog.gridTimeoutLogParms,
				onSelectRow : function(row) {
					timeoutLog.showLocate(row);
				}
			});
		} else {
			timeoutLog.gridTimeoutLog.changePage('first');
			timeoutLog.gridTimeoutLog.reload();
		}
	},
	showLocate : function(row) {
		timeoutLog.map.clearOverlays();
		timeoutLog.map.convertor(row.startLng, row.startLat, function(point1) {
			timeoutLog.map.queryAddress(point1.lng, point1.lat, function(address, row) {
				row.startAddr = address;
				timeoutLog.gridTimeoutLog.reRender({
					rowdata : row
				});
			}, row);
			var list = [];
			timeoutLog.map.drawPoi({
				lng : point1.lng,
				lat : point1.lat
			}, null, {
				url : '../resources/images/startpoint.png',
				size : {
					width : 32,
					height : 32
				},
				anchor : {
					x : 16,
					y : 32
				}
			});
			list.push({
				olng : point1.lng,
				olat : point1.lat
			});
			timeoutLog.map.convertor(row.endLng, row.endLat, function(point2) {
				timeoutLog.map.queryAddress(point2.lng, point2.lat, function(address, row) {
					row.endAddr = address;
					timeoutLog.gridTimeoutLog.reRender({
						rowdata : row
					});
				}, row);
				timeoutLog.map.drawPoi({
					lng : point2.lng,
					lat : point2.lat
				}, null, {
					url : '../resources/images/endpoint.png',
					size : {
						width : 32,
						height : 32
					},
					anchor : {
						x : 16,
						y : 32
					}
				});
				list.push({
					olng : point2.lng,
					olat : point2.lat
				});
				// 画线
				timeoutLog.map.drawPolyline([ point1, point2 ]);
				timeoutLog.map.setViewport(list);
			});
		});
	}
};
$(function() {
	webMap.events.onMapLoadCompleted['timoutLogMap'] = timeoutLog.onMapLoaded;
	webMap.createMap("timoutLogMap");

	timeoutLog.vehicle = parent.deviceData.vehicle;
	timeoutLog.deviceNumber = parent.deviceData.deviceNumber;
	timeoutLog.plateNumber = parent.deviceData.plateNumber;

	timeoutLog.gridTimeoutLogParms.deviceNumber = timeoutLog.deviceNumber;
	var endDate = new Date().addDays(1);
	timeoutLog.gridTimeoutLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
	var startDate = endDate.addDays(-7);
	timeoutLog.gridTimeoutLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

	timeoutLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd")
	});

	timeoutLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd")
	});

	timeoutLog.query();

	$('#btnQuery').click(function() {
		var start = timeoutLog.txtStartDate.getValue();
		var end = timeoutLog.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		timeoutLog.gridTimeoutLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
		timeoutLog.gridTimeoutLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
		timeoutLog.query();
	});
});