/**
 * 位置记录
 */
window.locateLog = {
	map : null,
	gridLocateLogParms : {
		start : null,
		end : null
	},
	onMapLoaded : function(map) {
		locateLog.map = map;
	},
	query : function() {
		if (!locateLog.gridLocateLog) {
			locateLog.gridLocateLog = $("#gridLocateLog").ligerGrid({
				columns : [ {
					display : '时间',
					name : 'time',
					type : 'date',
					width : 120
				}, {
					display : '经度',
					name : 'lng',
					width : 100
				}, {
					display : '纬度',
					name : 'lat',
					width : 100
				}, {
					display : '速度(km/h)',
					name : 'speed',
					width : 100
				}, {
					display : '地点',
					name : 'addr'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/locateLog/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				fixedCellHeight : false,
				parms : locateLog.gridLocateLogParms,
				onSelectRow : function(row) {
					locateLog.showLocate(row);
				}
			});
		} else {
			locateLog.gridLocateLog.changePage('first');
			locateLog.gridLocateLog.reload();
		}
	},
	showLocate : function(row) {
		locateLog.map.clearOverlays();
		locateLog.map.convertor(row.lng, row.lat, function(point) {
			locateLog.map.queryAddress(point.lng, point.lat, function(address, row) {
				row.addr = address;
				locateLog.gridLocateLog.reRender({
					rowdata : row
				});
			}, row);
			locateLog.map.drawPoi({
				lng : point.lng,
				lat : point.lat
			});
			locateLog.map.panTo(point.lng, point.lat);
		});
	}
};
$(function() {
	webMap.events.onMapLoadCompleted['locateLogMap'] = locateLog.onMapLoaded;
	webMap.createMap("locateLogMap");

	locateLog.vehicle = parent.deviceData.vehicle;
	locateLog.deviceNumber = parent.deviceData.deviceNumber;
	locateLog.plateNumber = parent.deviceData.plateNumber;

	locateLog.gridLocateLogParms.deviceNumber = locateLog.deviceNumber;
	var endDate = new Date().addDays(1);
	locateLog.gridLocateLogParms.end = endDate.toDateTimeString("yyyy-MM-dd 00:00:00");
	var startDate = endDate.addDays(-7);
	locateLog.gridLocateLogParms.start = startDate.toDateTimeString("yyyy-MM-dd 00:00:00");

	locateLog.txtStartDate = $('#txtStartDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd")
	});

	locateLog.txtEndDate = $('#txtEndDate').ligerDateEditor({
		width : 100,
		format : "yyyy-MM-dd",
		cancelable : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd")
	});

	locateLog.query();

	$('#btnQuery').click(function() {
		var start = locateLog.txtStartDate.getValue();
		var end = locateLog.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		locateLog.gridLocateLogParms.start = start.toDateTimeString("yyyy-MM-dd 00:00:00");
		locateLog.gridLocateLogParms.end = end.toDateTimeString("yyyy-MM-dd 00:00:00");
		locateLog.query();
	});
});