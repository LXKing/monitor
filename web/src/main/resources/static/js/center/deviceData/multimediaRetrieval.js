/**
 * 图片
 */
window.multimediaRetrieval = {
	deviceNumber : null,
	plateNumber : null,
	gridMultimediaRetrieval : null,
	gridMultimediaRetrievalParms : {
		deviceNumber : null,
		start : null,
		end : null
	},
	map : null,
	onMapLoaded : function(map) {
		multimediaRetrieval.map = map;
		$.get('../mapOption/query', function(r) {
			if (!r.lng)
				return;
			multimediaRetrieval.map.convertor(r.lng, r.lat, function(center) {
				multimediaRetrieval.map.setCenter(center);
				multimediaRetrieval.map.setZoom(r.zoom);
			});
		});
		multimediaRetrieval.infoWindow = webMap.createInfoWindow({
			map : map,
			data : undefined,
			width : 260,
			allowQueryAddress : true,
			makeTitle : function() {
				if (!this.data)
					return '';

				var html = [];
				html.push('<div style="margin:0px;padding:0px">');
				// 第一行
				html.push('<div style="height:18px;margin:2px;">');

				html.push('<b>');
				html.push(this.data.na);
				html.push(this.data.sp > 0 ? '[行驶]' : '[静止]');
				html.push('</b>');

				html.push('</div>');

				return html.join('');
			},
			makeContent : function() {
				var alarm = gpsDataParser.parseAlarm(this.data);
				var html = [];
				html.push('<hr/><div style="margin:0px;padding:0px">');
				// 第一行
				html.push('<div class="display-label">');
				html.push('<b>时间:</b>');
				if (this.data.gt)
					html.push(this.data.gt.toDate().toDateTimeString('MM-dd hh:mm:ss'));
				else
					html.push("00-00 00:00:00");
				html.push('[定位],');
				if (this.data.st)
					html.push(this.data.st.toDate().toDateTimeString('MM-dd hh:mm:ss'));
				else
					html.push("00:00:00");
				html.push('[接收]');
				html.push("</div>");

				// 第二行
				html.push('<div class="display-label">');
				html.push('<b>定位:</b>');
				html.push(gpsDataParser.parseLocateType(this.data));
				html.push('[' + gpsDataParser.parseDirection(this.data) + ']');
				html.push('&nbsp;&nbsp;<b>状态:</b>');
				html.push(gpsDataParser.parseAcc(this.data));
				html.push('</div>');

				// 第三行
				html.push('<div class="display-label">');
				html.push('<b>里程:</b>');
				html.push(common.round(this.data.m, 3) + 'km');
				html.push('&nbsp;&nbsp;<b>速度:</b>');
				html.push(common.round(this.data.sp, 1) + 'km/h');
				html.push('</div>');

				// 第四行
				if (alarm.length > 0) {
					html.push('<div class="display-label">');
					html.push('<b>报警:</b>');
					html.push('<span style="color:red;">');
					html.push(alarm);
					html.push('</span>');
					html.push('</div>');
				}

				// 第五行
				html.push('<div style="margin:2px;">');
				html.push('<b>位置:</b>');
				html.push(this.data.addr);
				html.push('</div>');

				return html.join("");
			}
		});
	},
	showMultimediaRetrieval : function(row) {
		row.marker = multimediaRetrieval.vehicle.marker;
		multimediaRetrieval.map.convertor(row.lng, row.lat, function(point) {
			multimediaRetrieval.map.queryAddress(point.lng, point.lat, function(address) {
				row.olng = point.lng;
				row.olat = point.lat;
				row.addr = address;
				row.na = multimediaRetrieval.plateNumber;
				var marker = multimediaRetrieval.marker;
				if (!marker) {
					marker = multimediaRetrieval.marker = webMap.createMarker({
						map : multimediaRetrieval.map,
						data : row,
						allowRotate : multimediaRetrieval.vehicle.rotate === 1,
						allowShowLabel : false,
						infoWindow : multimediaRetrieval.infoWindow
					});
					marker.openInfoWindow();
				} else {
					marker.data = row;
					marker.refresh();
				}

				multimediaRetrieval.map.panTo(row.olng, row.olat);
			});
		});
	},
	query : function() {
		if (!multimediaRetrieval.gridMultimediaRetrieval) {
			multimediaRetrieval.gridMultimediaRetrieval = $("#gridMultimediaRetrieval").ligerGrid({
				columns : [ {
					display : '时间',
					name : 'gt',
					align : 'left',
					width : 120
				}, {
					display : '媒体id',
					name : 'mediaId',
					align : 'left',
					width : 50
				}, {
					display : '媒体类型',
					name : 'mediaType',
					align : 'left',
					width : 50
				}, {
					display : '格式类型',
					name : 'formatType',
					align : 'left',
					width : 50
				}, {
					display : '事件类型',
					name : 'eventType',
					width : 80
				}, {
					display : '通道',
					name : 'channelId',
					width : 50
				}, {
					display : '速度(km/h)',
					name : 'sp',
					width : 60,
					render : function(row) {
						if (row.sp === 0)
							return '0';
						return common.round(row.sp, 1) + '';
					}
				}, {
					display : '方向',
					name : 'd',
					width : 40,
					render : function(row) {
						return gpsDataParser.parseDirection(row);
					}
				}, {
					display : '状态',
					name : 's',
					width : 150,
					render : function(row) {
						return gpsDataParser.parseStatus(row);
					}
				}, {
					display : '报警',
					name : 'a',
					width : 80,
					render : function(row) {
						return gpsDataParser.parseAlarm(row);
					}
				}, {
					display : '操作',
					isAllowHide : false,
					width : 100,
					render : function(row) {
						var html = '<a href="#" onclick="multimediaRetrieval.pickup(\'' + row.number + '\',' + row.mediaId + ')">提取</a>';
						return html;
					}
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../deviceData/multimediaRetrieval/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : multimediaRetrieval.gridMultimediaRetrievalParms,
				onSelectRow : function(row) {
					multimediaRetrieval.showMultimediaRetrieval(row);
				}
			});
		} else {
			multimediaRetrieval.gridMultimediaRetrieval.changePage('first');
			multimediaRetrieval.gridMultimediaRetrieval.reload();
		}
	},
	pickup : function(deviceNumber, mediaId) {
		$.post('../deviceData/pickupMultmedia', {
			deviceNumber : deviceNumber,
			mediaId : mediaId
		}, function(r) {
			if (r.code == 0) {
				common.tip('success', '指令已发送！', 3);
			} else
				common.tip('error', r.error, 3);
		});
	},
	layout : function() {
		var height = $(document).height();
		var centerHeight = height * 0.7;
		var bottomHeight = height - centerHeight;
		$('#divCenter').height(centerHeight);
		$('#divBottom').height(bottomHeight);
	}
}
$(function() {
	multimediaRetrieval.layout();
	multimediaRetrieval.vehicle = parent.deviceData.vehicle;
	multimediaRetrieval.deviceNumber = parent.deviceData.deviceNumber;
	multimediaRetrieval.plateNumber = parent.deviceData.plateNumber;

	webMap.events.onMapLoadCompleted['multimediaRetrievalMap'] = multimediaRetrieval.onMapLoaded;
	webMap.createMap("multimediaRetrievalMap");

	multimediaRetrieval.gridMultimediaRetrievalParms.deviceNumber = multimediaRetrieval.deviceNumber;
	var endDate = new Date().addDays(1);;
	multimediaRetrieval.gridMultimediaRetrievalParms.end = endDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");
	var startDate = endDate.addDays(-1);
	multimediaRetrieval.gridMultimediaRetrievalParms.start = startDate.toDateTimeString("yyyy-MM-dd hh:mm:ss");

	multimediaRetrieval.txtStartDate = $('#txtStartDate').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	multimediaRetrieval.txtEndDate = $('#txtEndDate').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	multimediaRetrieval.query();

	$('#btnQuery').click(function() {
		var start = multimediaRetrieval.txtStartDate.getValue();
		var end = multimediaRetrieval.txtEndDate.getValue();
		if (start == null || end == null) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		multimediaRetrieval.gridMultimediaRetrievalParms.start = start.toDateTimeString("yyyy-MM-dd hh:mm:ss");
		multimediaRetrieval.gridMultimediaRetrievalParms.end = end.toDateTimeString("yyyy-MM-dd hh:mm:ss");
		multimediaRetrieval.query();
	});
});