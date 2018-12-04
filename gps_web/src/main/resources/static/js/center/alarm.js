/**
 * 报警查询
 */
window.alarm = {
	/**
	 * 地图
	 */
	webMap : undefined,
	/**
	 * 地图信息窗口
	 */
	infoWindow : undefined,
	/**
	 * 报警数据表格
	 */
	gridAlarms : undefined,
	gridProcessedAlarmsParms : {},
	onMapLoaded : function(map) {
		alarm.webMap = map;
		$.get('../mapOption/query', function(r) {
			if (!r.lng)
				return;
			alarm.webMap.convertor(r.lng, r.lat, function(center) {
				alarm.webMap.setCenter(center);
				alarm.webMap.setZoom(r.zoom);
			});
		});
		alarm.infoWindow = webMap.createInfoWindow({
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

				html.push('<div style="margin-left:15px;display:inline-block;">');
				html.push('<div class="mon-icon-h-x16 i-16-satellite"></div>')
				html.push('<span>(' + this.data.sat + ')</span>');
				html.push('<div class="mon-icon-h-x16 i-16-signal' + gpsDataParser.parseNet(this.data) + '"></div>');
				html.push('</div>');

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
		$.get('../alarm/maptools.panel', function(html) {
			var divTools = $('<div style="cursor:pointer;border:1px solid gray;border-radius:5px;background-color:white;opacity:0.8;"></div>');
			divTools.html(html);
			map.setCustomControl(divTools[0], {
				anchor : "top_left",
				offset : {
					x : 80,
					y : 10
				}
			});

			$.get('../user/getOption', {
				type : 2
			}, function(r) {
				if (r.show == 'none') {
					$('#divMapToobars').hide();
					$('#btnToggleButton div').removeClass("i-16-toleft");
					$('#btnToggleButton div').addClass("i-16-toright");
				}
			});

			$('#btnToggleButton').click(function() {
				$('#divMapToobars').toggle();
				if ($('#divMapToobars').css('display') == "none") {
					$('#btnToggleButton div').removeClass("i-16-toleft");
					$('#btnToggleButton div').addClass("i-16-toright");
					$.post('../user/saveOption', {
						type : 2,
						options : {
							show : 'none'
						}
					});
				} else {
					$('#btnToggleButton div').removeClass("i-16-toright");
					$('#btnToggleButton div').addClass("i-16-toleft");
					$.post('../user/saveOption', {
						type : 2,
						options : {
							show : 'inline-block'
						}
					});
				}
			});

			$('#btnDistance').click(function() {
				if (alarm.webMap)
					alarm.webMap.distance();
			});

			$('#btnFullScreen').click(function() {
				var element = document.getElementById("alarmMap");

				var data = $(this).data('full');
				if (!data || data === false) {
					if (element.requestFullScreen) {
						element.requestFullScreen();
					} else if (element.webkitRequestFullScreen) {
						element.webkitRequestFullScreen();
					} else if (element.mozRequestFullScreen) {
						element.mozRequestFullScreen();
					}

					$("#btnFullScreen > div").removeClass('i-16-expand');
					$("#btnFullScreen > div").addClass('i-16-collapsed');
					$("#btnFullScreen > span").text('还原');
					$(this).data('full', true);
				} else {
					if (document.exitFullscreen) {
						document.exitFullscreen();
					} else if (document.webkitExitFullscreen) {
						document.webkitExitFullscreen();
					} else if (document.msExitFullscreen) {
						document.msExitFullscreen();
					} else if (document.mozCancelFullScreen) {
						document.mozCancelFullScreen();
					}

					$("#btnFullScreen > div").removeClass('i-16-collapsed');
					$("#btnFullScreen > div").addClass('i-16-expand');
					$("#btnFullScreen > span").text('全屏');
					$(this).data('full', false);
				}
			});

			$('#btnLayers').click(function() {
				alarm.mapLayerSetting();
			});
			$('#btnZoomout').click(function() {
				alarm.webMap.zoomout();
			});
			$('#btnZoomin').click(function() {
				alarm.webMap.zoomin();
			});
		});
		setTimeout(function() {
			alarm.drawMapLayers();
		}, 1000 * 10);
	},
	mapLayerSetting : function() {
		var url = '../mapLayer/setting.form';
		var op = {
			url : url,
			allowClose : false,
			width : 750,
			height : 300,
			isHidden : false,
			title : '地图图层设置',
			onLoaded : function() {
				alarm.dialog.frame.window.setting.query();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.setting.isChanged == true) {
						alarm.drawMapLayers();
					}
					dialog.close();
				}
			} ]
		};
		alarm.dialog = $.ligerDialog.open(op);
	},
	/**
	 * 绘画地图图层
	 */
	drawMapLayers : function() {
		// 清除地图图层
		if (alarm.mapLayers) {
			for ( var id in alarm.mapLayers) {
				var layer = alarm.mapLayers[id];
				for (var x = 0; x < layer.length; x++) {
					var overlay = layer[x];
					alarm.webMap.removeOverlay(overlay);
					overlay.label && alarm.webMap.removeOverlay(overlay.label);
				}
			}
		}
		// 重新加载
		alarm.mapLayers = {};
		$.get('../mapLayer/query', {
			filter : ''
		}, function(page) {
			for (var index = 0; index < page.rows.length; index++) {
				var mapLayerInfo = page.rows[index];
				if (mapLayerInfo.visible == false)
					continue;
				alarm.mapLayers[mapLayerInfo.id] = [];
				// 画圆
				var circles = mapLayerInfo.circleAreas;
				if (circles) {
					for (var x = 0; x < circles.length; x++) {
						var circle = circles[x];
						alarm.webMap.convertor(circle.lng, circle.lat, function(p, ctx) {
							var overlay = alarm.webMap.drawingCircle(p, ctx.area.radius, ctx.area.name);
							alarm.mapLayers[ctx.info.id].push(overlay);
						}, {
							area : circle,
							info : mapLayerInfo
						});
					}
				}
				// 画矩形
				var rectangles = mapLayerInfo.rectangleAreas;
				if (rectangles) {
					for (var x = 0; x < rectangles.length; x++) {
						var rectangle = rectangles[x];
						var points = [ {
							lng : rectangle.ullng,
							lat : rectangle.ullat
						}, {
							lng : rectangle.brlng,
							lat : rectangle.ullat
						}, {
							lng : rectangle.brlng,
							lat : rectangle.brlat
						}, {
							lng : rectangle.ullng,
							lat : rectangle.brlat
						} ];
						alarm.webMap.translate(points, 0, function(list, ctx) {
							var wn = {
								lng : list[0].olng,
								lat : list[0].olat
							};
							var ne = {
								lng : list[1].olng,
								lat : list[1].olat
							};
							var ew = {
								lng : list[2].olng,
								lat : list[2].olat
							};
							var sw = {
								lng : list[3].olng,
								lat : list[3].olat
							};
							var path = [];
							path.push(wn);
							path.push(ne);
							path.push(ew);
							path.push(sw);

							var overlay = alarm.webMap.drawPolygon(path, ctx.area.name);
							alarm.mapLayers[ctx.info.id].push(overlay);
						}, {
							area : rectangle,
							info : mapLayerInfo
						});
					}
				}
				// 画多边形
				var polygons = mapLayerInfo.polygonAreas;
				if (polygons) {
					for (var x = 0; x < polygons.length; x++) {
						var polygon = polygons[x];
						alarm.webMap.translate(polygon.points, 0, function(list, ctx) {
							var path = [];
							for (var y = 0; y < list.length; y++) {
								var p = list[y];
								path.push({
									lat : p.olat,
									lng : p.olng
								});
							}
							var overlay = alarm.webMap.drawPolygon(path, ctx.area.name);
							alarm.mapLayers[ctx.info.id].push(overlay);
						}, {
							area : polygon,
							info : mapLayerInfo
						});
					}
				}
				// 画路线
				var routes = mapLayerInfo.routeAreas;
				if (routes) {
					for (var x = 0; x < routes.length; x++) {
						var route = routes[x];
						for (var y = 0; y < route.sections.length; y++) {
							var section = route.sections[y];
							alarm.webMap.translate(section.points, 0, function(list, ctx) {
								var path = [];
								for (var x = 0; x < list.length; x++) {
									var item = list[x];
									path.push({
										lng : item.olng,
										lat : item.olat
									});
								}
								var overlay = alarm.webMap.drawPolyline(path);
								alarm.mapLayers[ctx.info.id].push(overlay);
							}, {
								info : mapLayerInfo
							});
						}
					}
				}
				// 画兴趣点
				var pois = mapLayerInfo.pois;
				if (pois) {
					for (var x = 0; x < pois.length; x++) {
						var row = pois[x];
						alarm.webMap.convertor(row.lng, row.lat, function(point, ctx) {
							var overlay = alarm.webMap.drawPoi(point, ctx.area.name);
							alarm.mapLayers[ctx.info.id].push(overlay);
						}, {
							area : row,
							info : mapLayerInfo
						});
					}
				}
			}
		});
	},
	showAlarm : function(row) {
		row.marker = alarm.vehicle.marker;
		alarm.webMap.convertor(row.lng, row.lat, function(point) {
			alarm.webMap.queryAddress(point.lng, point.lat, function(address) {
				row.olng = point.lng;
				row.olat = point.lat;
				row.addr = address;
				row.na = alarm.plateNumber;
				var marker = alarm.marker;
				if (!marker) {
					marker = alarm.marker = webMap.createMarker({
						map : alarm.webMap,
						data : row,
						allowRotate : alarm.vehicle.rotate === 1,
						allowShowLabel : false,
						infoWindow : alarm.infoWindow
					});
					marker.openInfoWindow();
				} else {
					marker.data = row;
					marker.refresh();
				}

				alarm.webMap.panTo(row.olng, row.olat);
			});
		});
	},
	/**
	 * 未处理报警
	 */
	untreated : function() {
		alarm.gridUntreatedAlarms = $("#gridUntreatedAlarms").ligerGrid({
			columns : [ {
				display : '',
				isAllowHide : false,
				align : 'left',
				width : 'auto',
				headerRender : function(column) {
					return '<div class="mon-left">报警位置数据</div>';
				},
				render : function(row) {
					var html = [];
					html.push('<div style="cursor:pointer; padding:3px;">');
					html.push(' <div>状态：<span>' + gpsDataParser.parseStatus(row) + '</span></div>');
					html.push(' <div>' + '时间' + '：' + row.gt + ' ' + '速度' + '：' + row.sp + '</div>');
					html.push(' <div class="error">' + gpsDataParser.parseAlarm(row) + '</div>');
					html.push('</div>');
					return html.join('');
				}
			} ],
			onSelectRow : function(rowdata, rowid, rowobj) {
				if (alarm.gridProcessedAlarms) {
					var row = alarm.gridProcessedAlarms.getSelected();
					row && alarm.gridProcessedAlarms.unselect(row);
				}

				alarm.showAlarm(rowdata);
			},
			fixedCellHeight : false,
			alternatingRow : false,
			usePager : false,
			heightDiff : 30,
			// pageParmName : 'pageIndex',
			// pagesizeParmName : 'pageSize',
			root : 'rows',
			record : 'total',
			url : '../alarm/untreated',
			parms : {
				deviceNumber : alarm.deviceNumber
			},
			width : '100%',
			height : '100%'
		});
	},
	/**
	 * 已处理报警
	 */
	processed : function() {
		alarm.gridProcessedAlarmsParms.deviceNumber = alarm.deviceNumber;
		alarm.gridProcessedAlarmsParms.start = alarm.txtStartTime.getValue().toDateTimeString();
		alarm.gridProcessedAlarmsParms.end = alarm.txtEndTime.getValue().toDateTimeString();
		if (!alarm.gridProcessedAlarms)
			alarm.gridProcessedAlarms = $("#gridProcessedAlarms").ligerGrid({
				columns : [ {
					display : '',
					isAllowHide : false,
					align : 'left',
					width : 430,
					headerRender : function(column) {
						return '<div class="mon-left">报警位置数据</div>';
					},
					render : function(row) {
						var html = [];
						html.push('<div style="cursor:pointer; padding:3px;">');
						html.push(' <div>状态：<span>' + gpsDataParser.parseStatus(row) + '</span></div>');
						html.push(' <div>' + '时间：' + row.gt + ' 速度：' + row.sp + '</div>');
						html.push(' <div class="error">' + gpsDataParser.parseAlarm(row) + '</div>');
						html.push(' <div>处理人：' + row.userName + ' 处理时间：' + row.userTime + '</div>')
						html.push(' <div>处理方式：' + row.userMethod + '</div>')
						html.push(' <div>处理内容：' + row.userRemark + '</div>')
						html.push('</div>');
						return html.join('');
					}
				} ],
				onSelectRow : function(rowdata, rowid, rowobj) {
					if (alarm.gridUntreatedAlarms) {
						var row = alarm.gridUntreatedAlarms.getSelected();
						row && alarm.gridUntreatedAlarms.unselect(row);
					}
					alarm.showAlarm(rowdata);
				},
				fixedCellHeight : false,
				alternatingRow : false,
				pageSize : 30,
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				root : 'rows',
				record : 'total',
				url : '../alarm/processed',
				parms : alarm.gridProcessedAlarmsParms,
				width : '100%',
				height : '100%'
			});
		else {
			alarm.gridProcessedAlarms.changePage('first');
			alarm.gridProcessedAlarms.reload();
		}
	},
	reset : function(deviceNumber, plateNumber) {
		$('#divAlarmMapControl #txtPlateNumber').text(plateNumber);
		alarm.deviceNumber = deviceNumber;
		alarm.plateNumber = plateNumber;
		alarm.untreated();
		alarm.processed();
	},
	layout : function() {
		var height = $(document).height();
		$("#alarmLayout").ligerLayout({
			rightWidth : 450,
			allowRightResize : false,
			allowRightCollapse : false,
			height : height
		});

		alarm.alarmPages = $("#divAlarmMapControl #alarmPages").ligerTab({
		// height : left
		});
	},
	processAlarmOnce : function(alarmId) {
		var url = '../alarm/processonce.form';
		var op = {
			url : url,
			urlParms : {
				alarmId : alarmId
			},
			width : 550,
			height : 250,
			isHidden : false,
			title : '处理报警',
			onLoaded : function() {
				var form = $('form', alarm.processAlarmOnceDialog.frame.document);
				if (form.length <= 0)
					return;
				alarm.processAlarmOnceDialog.frame.window.validate();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					var form = $('form', dialog.frame.document);
					if (form.length <= 0) {
						dialog.close();
						return;
					}
					if (dialog.frame.window.validate() == false) {
						return;
					}

					var formData = form.serialize();
					$.post(url, formData, function(data) {
						var result = common.form.save(dialog, data, op);
						if (result === true)
							alarm.gridUntreatedAlarms.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		alarm.processAlarmOnceDialog = $.ligerDialog.open(op);
	},
	processAlarmAll : function() {
		var url = '../alarm/processall.form';
		var op = {
			url : url,
			width : 550,
			height : 250,
			isHidden : false,
			title : '全部处理',
			onLoaded : function() {
				alarm.processAlarmAllDialog.frame.window.validate();
				alarm.processAlarmAllDialog.frame.window.setNumbers(alarm.deviceNumber);
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.validate() == false) {
						return;
					}

					var form = $('form', dialog.frame.document);
					var formData = form.serialize();
					$.post(url, formData, function(data) {
						var result = common.form.save(dialog, data, op);
						if (result === true)
							alarm.gridUntreatedAlarms.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		alarm.processAlarmAllDialog = $.ligerDialog.open(op);
	}
};
$(function() {
	alarm.layout();
	webMap.events.onMapLoadCompleted['alarmMap'] = alarm.onMapLoaded;
	webMap.createMap("alarmMap");

	var endDate = new Date();
	var startDate = endDate.addDays(-1);

	alarm.txtStartTime = $('#divAlarmMapControl #txtStartTime').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	alarm.txtEndTime = $('#divAlarmMapControl #txtEndTime').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	$('#divAlarmMapControl #btnRefreshUntreateAlarm').click(function() {
		if (!alarm.gridUntreatedAlarms)
			return;
		alarm.gridUntreatedAlarms.reload();
	});

	$('#divAlarmMapControl #btnProcessOnceAlarm').click(function() {
		if (!alarm.gridUntreatedAlarms)
			return;

		var row = alarm.gridUntreatedAlarms.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}

		alarm.processAlarmOnce(row.id);
	});

	$('#divAlarmMapControl #btnProcessAllAlarm').click(function() {
		if (!alarm.gridUntreatedAlarms)
			return;
		var rows = alarm.gridUntreatedAlarms.getData();
		if (!rows || rows.length <= 0) {
			return;
		}
		$.ligerDialog.confirm('真地要处理所有报警吗？', function(yes, value) {
			if (!yes)
				return;
			alarm.processAlarmAll();
		});
	});

	$('#btnAlarmDownloadTracks').click(function() {
		alarm.processed();
	});

	var deviceNumber = $('#txtDeviceNumber').val();
	var plateNumber = $('#txtPlateNumber').val();
	alarm.reset(deviceNumber, plateNumber);
	$.get('../common/device/vehicle', {
		number : deviceNumber
	}, function(vehicle) {
		alarm.vehicle = vehicle;
	})
});