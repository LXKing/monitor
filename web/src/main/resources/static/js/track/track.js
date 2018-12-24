/**
 * 设备管理
 */
window.track = {
	startDate : undefined,
	endDate : undefined,
	device : undefined,
	webMap : undefined, // 地图
	gridTracks : undefined, // 位置数据表格
	gridDevicesParams : {
		filter : ''
	},
	gridDevices : undefined,
	infoWindow : undefined, // 信息窗口
	marker : undefined, // 标注
	polyline : undefined, // 线段
	downloadTimeOut : undefined, // 下载句柄
	positionCount : undefined, // 位置数据总数
	positions : [], // 位置数据列表
	pageIndex : 0, // 页码记数器
	playInterval : undefined, // 播放线程
	playIndex : 0, // 播放记数器
	playStatus : 'init', // 播放状态:初始化(init),播放中(play),暂停(pause),停止(stop)
	pointChecking : false,
	rsetButtons : function() {
		$("#btnStop").removeClass("mon-button");
		$("#btnStop").addClass("mon-slice");
		$("#btnPause").removeClass("mon-button");
		$("#btnPause").addClass("mon-slice");
		$("#btnPlay").removeClass("mon-slice");
		$("#btnPlay").addClass("mon-button");
	},
	playProgress : function() {
		if (this.positionCount && this.positionCount > 0) {
			var ratio = this.playIndex / this.positionCount;
			var width = Math.round(ratio * 100);
			if (width > 100)
				width = 100;
			var ratioText = width + '%';
			$('#txtProgress').css('width', ratioText);
		}
	},

	selectDate : function() {
		var dateStart = new Date().addDays(-1)
		var dateEnd = new Date();

		var start = track.startDate.getValue();
		var end = track.endDate.getValue();
		if (start == null || end == null)
			return null;
		else {
			dateStart = start;
			dateEnd = end;
		}

		if (dateStart.getOffDays(dateEnd) > 7) {
			$.ligerDialog.alert('时间范围不超过7天', 'warn');
			track.rsetButtons();
			return null;
		}

		return {
			start : dateStart,
			end : dateEnd
		};
	},
	mapLoadCompleted : function(map) {
		track.webMap = map;
		track.polyline = webMap.createPolyline({
			map : map
		});
		track.infoWindow = webMap.createInfoWindow({
			map : map,
			data : undefined,
			allowQueryAddress : false
		});

		var number = $('#txtDeviceNumber').text();
		if (number.length > 0) {
			$.post('./common/device/info', {
				number : number
			}, function(data) {
				track.device = data;
				common.setCookie('device_number', data.number);
				common.setCookie('device_name', data.name);
				$('#txtDeviceNumber').text(data.number);
				$('#txtDeviceName').text(data.name);
			});
		} else {
			var number = common.getCookie('device_number');
			var name = common.getCookie('device_name');
			if (number && name) {
				track.device = {
					number : number,
					name : name
				};
				$('#txtDeviceNumber').text(number);
				$('#txtDeviceName').text(name);
			}
		}
	},
	download : function(number, start, end, pageIndex, pageSize) {
		// 对时间需要计算
		$
				.post(
						'./common/track/load',
						{
							number : number,
							start : start,
							end : end,
							pageIndex : pageIndex,
							pageSize : pageSize
						},
						function(list) {
							if ((track.playStatus === 'play' || track.playStatus === 'pause')
									&& list.length > 0) {
								track.webMap.translate(list, function(items) {
									for (var i = 0; i < items.length; i++) {
										track.positions.push(items[i]);
									}
								});
							}

							if (list && list.length > 0) {
								if (track.playStatus === 'play'
										|| track.playStatus === 'pause') {
									track.pageIndex++;
									window.track.downloadTimeOut = setTimeout(
											function() {
												track.download(number, start,
														end, track.pageIndex,
														pageSize)
											}, 3000);
								}
							}
						});
	},
	stopPlay : function() {
		track.playStatus = 'stop';
		track.pageIndex = 1;
		track.positions = [];
		track.playIndex = 0;
		if (track.playInterval)
			clearInterval(track.playInterval);
	},
	startPlay : function(start, end) {
		track.playStatus = 'play';
		track.pageIndex = 1;
		track.positions = [];
		track.playIndex = 0;
		var grid = track.gridTracks;
		grid.loadData({
			Rows : []
		});

		if (track.polyline) {
			track.polyline.points = [];
			track.polyline.refresh();
		}

		if (track.playInterval)
			clearInterval(track.playInterval);

		track.download(track.device.number, start, end, track.pageIndex, 20);
		track.playInterval = setInterval(function() {
			if (track.positions.length > 0 && track.playStatus === 'play') {
				if (track.playIndex < track.positions.length) {
					if (track.pointChecking === false)
						track.drawPoint(track.playIndex);
					track.playIndex++;
					track.playProgress();
					var isPlayRaw = $('#txtStepPlay').is(':checked');
					if (!isPlayRaw)
						track.checkNextPoint();
				} else if (track.playIndex >= track.positionCount) {
					track.stopPlay();
					track.rsetButtons();
				}
			}
		}, 1000);
	},
	checkNextPoint : function() {
		track.pointChecking = true;
		var v1 = $('#txtDistance').val();
		var distance = parseInt(v1);
		var v2 = $('#txtSpeed').val()
		var speed = parseInt(v2);

		var isDistanceCheck = $('#checkDistance').is(':checked');
		var isSpeedCheck = $('#checkSpeed').is(':checked');

		while (track.playIndex < track.positions.length) {
			var prev = track.positions[track.playIndex - 1];
			var curr = track.positions[track.playIndex];

			if (prev.lat == curr.lat && prev.lng == curr.lng) {// 相同点过滤
				track.playIndex++;
				track.playProgress();
				continue;
			} else if (isDistanceCheck === true && isSpeedCheck == true) {
				if ((track.webMap.getDistance(prev, curr) < distance)
						|| curr.sp < speed) {
					track.playIndex++;
					track.playProgress();
					continue;
				}
			} else if (isDistanceCheck === true) {
				if (track.webMap.getDistance(prev, curr) < distance) {
					track.playIndex++;
					track.playProgress();
					continue;
				}
			} else if (isSpeedCheck == true) {
				if (curr.sp < speed) {
					track.playIndex++;
					track.playProgress();
					continue;
				}
			}
			track.pointChecking = false;
			return;
		}
	},
	drawPoint : function(index) {
		if (!track.polyline)
			return;
		var pos = track.positions[index];

		track.webMap.queryAddress(pos.olng, pos.olat, function(address) {
			pos.addr = address;
			pos.n = track.device.name;

			var marker = track.marker;
			if (!marker) {
				marker = track.marker = webMap.createMarker({
					map : track.webMap,
					data : pos,
					allowShowLabel : false,
					infoWindow : track.infoWindow
				});
				marker.openInfoWindow();
			} else {
				marker.data.a = pos.a;
				marker.data.d = pos.d;
				marker.data.lat = pos.lat;
				marker.data.lng = pos.lng;
				marker.data.olat = pos.olat;
				marker.data.olng = pos.olng;
				marker.data.o = pos.o;
				marker.data.sp = pos.sp;
				marker.data.s = pos.s;
				marker.data.t = pos.t;
				marker.data.addr = pos.addr;

				marker.refresh();
			}

			track.polyline.addPoint(pos.olng, pos.olat);
			track.webMap.panTo(pos.olng, pos.olat);

			var grid = track.gridTracks;
			var rowdata = {};
			$.extend(rowdata, marker.data);
			grid.addRow(rowdata, grid.rows[0], true);
			if (grid.rows.length > 200) {
				grid.loadData({
					Rows : []
				});
				track.polyline.points.splice(0, 100);
				track.polyline.refresh();
			}
		});
	}
};

$(function() {
	$("#layout").ligerLayout({
		topHeight : 80,
		rightWidth : 400,
		allowTopResize : false
	});
	$('#btnTrack').addClass('select');

	$('#txtProgress').width(0);

	webMap.events.onMapLoadCompleted = track.mapLoadCompleted;
	webMap.createMap("mapContainer");

	var startTime = $("#txtStartDate").val();
	if (startTime.length <= 0) {
		var time_start = common.getCookie('time_start');
		startTime = time_start == null ? '' : time_start;
	}

	startTime = startTime.length > 0 ? startTime.toDate().toDateTimeString(
			'yyyy-MM-dd hh:mm') : new Date().addDays(-1).toDateTimeString(
			'yyyy-MM-dd hh:mm');

	var endTime = $("#txtEndDate").val();
	if (endTime.length <= 0) {
		var time_end = common.getCookie('time_end');
		endTime = time_end == null ? '' : time_end;
	}
	endTime = endTime.length > 0 ? endTime.toDate().toDateTimeString(
			'yyyy-MM-dd hh:mm') : new Date()
			.toDateTimeString('yyyy-MM-dd hh:mm');

	track.startDate = $("#txtStartDate").ligerDateEditor(
			{
				showTime : true,
				format : 'yyyy-MM-dd hh:mm',
				label : '开始时间',
				labelWidth : 60,
				labelAlign : 'left',
				initValue : startTime,
				onChangeDate : function(value) {
					var time = (value + ':00').toDate().toDateTimeString(
							'yyyy-MM-dd hh:mm:ss');
					common.setCookie('time_start', time);
				}
			});
	track.endDate = $("#txtEndDate").ligerDateEditor(
			{
				showTime : true,
				format : 'yyyy-MM-dd hh:mm',
				label : '结束时间',
				labelWidth : 60,
				labelAlign : 'left',
				initValue : endTime,
				onChangeDate : function(value) {
					var time = (value + ':00').toDate().toDateTimeString(
							'yyyy-MM-dd hh:mm:ss');
					common.setCookie('time_end', time);
				}
			});

	track.gridTracks = $("#gridTracks")
			.ligerGrid(
					{
						data : {
							Rows : []
						},
						usePager : false,
						fixedCellHeight : false,
						alternatingRow : false,
						columns : [ {
							display : '',
							isAllowHide : false,
							align : 'left',
							width : '380',
							headerRender : function(column) {
								return '<div class="mon-left">历史位置数据</div>';
							},
							render : function(row) {
								var html = [];
								html
										.push('<div style="cursor:pointer; padding:3px;">');
								html.push(' <div>');
								html.push(' 状态：<span>'
										+ gpsDataParser.parseStatus(row)
										+ '</span></div>');
								html.push(' <div>');
								html.push(' <div>' + '时间' + '：' + row.t + ' '
										+ '速度' + '：' + row.sp);
								html.push(' </div>');
								html.push(' <div>' + row.addr);
								html.push(' </div>');
								html.push('</div>');
								return html.join('');
							}
						} ],
						width : 'auto',
						height : '100%'
					});

	$('#btnPlay').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;

		if (!track.device) {
			$.ligerDialog.warn('未发现设备信息！');
			return;
		}

		$('#btnStop').removeClass('mon-slice');
		$('#btnStop').addClass('mon-button');
		$('#btnPause').removeClass('mon-slice');
		$('#btnPause').addClass('mon-button');
		$(this).removeClass('mon-button');
		$(this).addClass('mon-slice');

		if (track.playStatus == 'pause') {
			track.playStatus = 'play';
			return;
		}

		var dates = track.selectDate();
		if (!dates)
			return;
		var startDate = dates.start.toDateTimeString();
		var endDate = dates.end.toDateTimeString();

		$.post('./common/track/count', {
			number : track.device.number,
			start : startDate,
			end : endDate
		}, function(r) {
			// 获取历史数据总数量
			track.positionCount = r.total;
			if (track.positionCount <= 0) {
				$.ligerDialog.error('此时间段无数据！');
				track.stopPlay();
				track.rsetButtons();
				return;
			}
			track.startPlay(startDate, endDate);
		});
	});

	$('#btnPause').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;

		track.playStatus = 'pause';
		track.rsetButtons();
	});

	$('#btnStop').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;

		track.stopPlay();
		track.rsetButtons();
	});

	$('#btnDistance').click(function() {
		track.webMap.distance();
	});
	$('#btnSelectDevice').click(function() {
		if (!track.gridDevices)
			return;
		var row = track.gridDevices.getSelected();
		if (row) {
			track.device = {
				number : row.number,
				name : row.name
			};
			common.setCookie('device_number', row.number);
			common.setCookie('device_name', row.name);
			$('#txtDeviceNumber').text(row.number);
			$('#txtDeviceName').text(row.name);
			if (track.dialogSearchDevices)
				track.dialogSearchDevices.hide();
		}

	});

	$('#btnSearchDevices').click(function() {
		track.gridDevicesParams.filter = $('#txtDeviceFilter').val();

		var op = {
			target : $('#dialogSearchDevices'),
			width : 800,
			height : 410,
			title : '选择设备',
		};
		track.dialogSearchDevices = $.ligerDialog.open(op);

		if (!track.gridDevices) {
			track.gridDevices = $("#gridDevices").ligerGrid({
				columns : [ {
					display : '设备名',
					name : 'name'
				}, {
					display : '设备号',
					name : 'number'
				}, {
					display : 'SIM卡',
					name : 'sim'
				}, {
					display : '服务开始日期',
					name : 'serviceStartTime',
					type : 'date'
				}, {
					display : '服务结束日期',
					name : 'serviceEndTime',
					type : 'date'
				}, {
					display : '入网时间',
					name : 'createTime',
					type : 'date'
				} ],
				pageSize : 30,
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				root : 'rows',
				record : 'total',
				url : './common/device/search',
				parms : track.gridDevicesParams,
				width : '99%',
				height : 300
			});
		} else {
			track.gridDevices.reload();
		}

	});
});
