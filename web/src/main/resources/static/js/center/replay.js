/**
 * 轨迹回放
 */

window.replay = {
	/**
	 * 设备号
	 */
	deviceNumber : null,
	/**
	 * 分页下载时的页大小
	 */
	pageSize : 100,
	/**
	 * 播放状态:初始化(init),下载中(loading),下载完成(loaded), 播放中(play),暂停(pause),停止(stop)
	 */
	playStatus : 'init',
	/**
	 * 位置数据总数
	 */
	trackCount : 0,
	/**
	 * 位置数据
	 */
	tracks : [],
	/**
	 * 页序号
	 */
	pageIndex : 0,
	/**
	 * 地图
	 */
	webMap : null,
	/**
	 * 信息窗口
	 */
	infoWindow : null,
	/**
	 * 行程
	 */
	trips : {
		list : [],
		days : {}
	},
	/**
	 * 停车
	 */
	parks : {
		list : [],
		days : {}
	},
	/**
	 * 报警
	 */
	alarms : {
		list : [],
		days : {}
	},
	/**
	 * 线段
	 */
	speeds : [],
	/**
	 * 地图加载完成回调
	 */
	onMapLoaded : function(map) {
		replay.webMap = map;
		$.get('../mapOption/query', function(r) {
			if (!r.lng)
				return;
			replay.webMap.convertor(r.lng, r.lat, function(center) {
				replay.webMap.setCenter(center);
				replay.webMap.setZoom(r.zoom);
			});
		});
		replay.infoWindow = webMap.createInfoWindow({
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
		$.get('../replay/maptools.panel', function(html) {
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
				if (replay.webMap)
					replay.webMap.distance();
			});

			$('#btnFullMarkers').click(function() {
				if (!replay.tracks)
					return;
				var list = [];
				for (var x = 0; x < replay.tracks.length; x++) {
					var item = replay.tracks[x];
					list.push({
						olng : item.olng,
						olat : item.olat
					});
				}
				if (list.length <= 0)
					return;
				replay.webMap.setViewport(list);
			});

			$('#btnFullScreen').click(function() {
				var element = document.getElementById("replayMap");

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
				replay.mapLayerSetting();
			});
			$('#btnZoomout').click(function() {
				replay.webMap.zoomout();
			});
			$('#btnZoomin').click(function() {
				replay.webMap.zoomin();
			});
		});
		setTimeout(function() {
			replay.drawMapLayers();
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
				replay.dialog.frame.window.setting.query();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.setting.isChanged == true) {
						replay.drawMapLayers();
					}
					dialog.close();
				}
			} ]
		};
		replay.dialog = $.ligerDialog.open(op);
	},
	/**
	 * 绘画地图图层
	 */
	drawMapLayers : function() {
		// 清除地图图层
		if (replay.mapLayers) {
			for ( var id in replay.mapLayers) {
				var layer = replay.mapLayers[id];
				for (var x = 0; x < layer.length; x++) {
					var overlay = layer[x];
					replay.webMap.removeOverlay(overlay);
					overlay.label && replay.webMap.removeOverlay(overlay.label);
				}
			}
		}
		// 重新加载
		replay.mapLayers = {};
		$.get('../mapLayer/query', {
			filter : ''
		}, function(page) {
			for (var index = 0; index < page.rows.length; index++) {
				var mapLayerInfo = page.rows[index];
				if (mapLayerInfo.visible == false)
					continue;
				replay.mapLayers[mapLayerInfo.id] = [];
				// 画圆
				var circles = mapLayerInfo.circleAreas;
				if (circles) {
					for (var x = 0; x < circles.length; x++) {
						var circle = circles[x];
						replay.webMap.convertor(circle.lng, circle.lat, function(p, ctx) {
							var overlay = replay.webMap.drawingCircle(p, ctx.area.radius, ctx.area.name);
							replay.mapLayers[ctx.info.id].push(overlay);
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
						replay.webMap.translate(points, 0, function(list, ctx) {
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

							var overlay = replay.webMap.drawPolygon(path, ctx.area.name);
							replay.mapLayers[ctx.info.id].push(overlay);
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
						replay.webMap.translate(polygon.points, 0, function(list, ctx) {
							var path = [];
							for (var y = 0; y < list.length; y++) {
								var p = list[y];
								path.push({
									lat : p.olat,
									lng : p.olng
								});
							}
							var overlay = replay.webMap.drawPolygon(path, ctx.area.name);
							replay.mapLayers[ctx.info.id].push(overlay);
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
							replay.webMap.translate(section.points, 0, function(list, ctx) {
								var path = [];
								for (var x = 0; x < list.length; x++) {
									var item = list[x];
									path.push({
										lng : item.olng,
										lat : item.olat
									});
								}
								var overlay = replay.webMap.drawPolyline(path);
								replay.mapLayers[ctx.info.id].push(overlay);
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
						replay.webMap.convertor(row.lng, row.lat, function(point, ctx) {
							var overlay = replay.webMap.drawPoi(point, ctx.area.name);
							replay.mapLayers[ctx.info.id].push(overlay);
						}, {
							area : row,
							info : mapLayerInfo
						});
					}
				}
			}
		});
	},
	/**
	 * 重设按钮可操作否
	 */
	resetButtons : function(download, start, pause, stop) {
		if (download) {
			$("#btnReplayDownloadTracks").removeClass("mon-slice");
			$("#btnReplayDownloadTracks").addClass("mon-button");
		} else {
			$("#btnReplayDownloadTracks").removeClass("mon-button");
			$("#btnReplayDownloadTracks").addClass("mon-slice");
		}
		if (start) {
			$("#btnRelayStart").removeClass("mon-slice");
			$("#btnRelayStart").addClass("mon-button");
		} else {
			$("#btnRelayStart").removeClass("mon-button");
			$("#btnRelayStart").addClass("mon-slice");
		}
		if (pause) {
			$("#btnReplayPause").removeClass("mon-slice");
			$("#btnReplayPause").addClass("mon-button");
		} else {
			$("#btnReplayPause").removeClass("mon-button");
			$("#btnReplayPause").addClass("mon-slice");
		}
		if (stop) {
			$("#btnReplayStop").removeClass("mon-slice");
			$("#btnReplayStop").addClass("mon-button");
		} else {
			$("#btnReplayStop").removeClass("mon-button");
			$("#btnReplayStop").addClass("mon-slice");
		}
	},
	/**
	 * 下载进度
	 */
	playProgress : function() {
		if (replay.trackCount && replay.trackCount > 0) {
			var ratio = replay.tracks.length / replay.trackCount;
			var width = Math.round(ratio * 100);
			if (width > 100)
				width = 100;
			var ratioText = width + '%';
			$('#divProcess > div').css('width', ratioText);
		}
		if (replay.tracks.length >= replay.trackCount) {
			replay.downloadCompleted();
			$('#divReplayMapControl #divProcess').hide();
			$('#divReplayMapControl #txtProcess').attr('max', replay.tracks.length - 1);
			$('#divReplayMapControl #txtProcess').val(0);
			$('#divReplayMapControl #txtProcess').show();
		}
	},
	resetSpeedChart : function(data) {
		// 更新速度图表
		$('#speedChart').highcharts(
				{
					chart : {
						zoomType : 'x'
					},
					title : {
						text : ''
					},
					xAxis : {
						type : 'datetime',
						tickPixelInterval : 50
					},
					yAxis : {
						min : 0,
						title : {
							text : ''
						}
					},
					legend : {
						enabled : false
					},
					tooltip : {
						formatter : function() {
							return this.series.name + ':' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' + '速度:'
									+ Highcharts.numberFormat(this.y, 1) + 'km/h';
						}
					},
					plotOptions : {
						line : {
							lineWidth : 1
						},
						series : {
							turboThreshold : 0
						}
					},
					credits : {
						enabled : false
					// 禁用版权信息
					},
					series : [ {
						type : 'line',
						name : '时间',
						data : data
					} ]
				});
	},
	/**
	 * 选取时间
	 */
	selectDate : function() {
		var dateStart = new Date().addDays(-1)
		var dateEnd = new Date();

		var start = replay.txtStartTime.getValue();
		var end = replay.txtEndTime.getValue();
		if (start == null || end == null)
			return null;
		else {
			dateStart = start;
			dateEnd = end;
		}

		if (dateStart.getOffDays(dateEnd) > 7) {
			$.ligerDialog.alert('时间范围不超过7天', 'warn');
			this.resetButtons(1);
			return {
				start : dateStart,
				end : dateEnd,
				valid : false
			};
		}

		return {
			start : dateStart,
			end : dateEnd,
			valid : true
		};
	},
	/**
	 * 数据下载完成
	 */
	downloadCompleted : function() {
		replay.playStatus = 'loading';
		this.clear();
		this.trips = {
			list : [],
			days : {}
		};
		this.parks = {
			list : [],
			days : {}
		};
		this.alarms = {
			list : [],
			days : {}
		};
		this.speeds = [];

		var acc = null;
		/**
		 * 行程
		 */
		var trip = {
			data : []
		};
		/**
		 * 速度计
		 */
		var velocimeter = {
			total : 0,
			times : 0
		};
		/**
		 * 里程计
		 */
		var milemeter = {
			start : this.tracks[0].m,
			befor : 0,
			reset : null,
			end : this.tracks[this.tracks.length - 1].m
		};
		/**
		 * 油量计
		 */
		oilmeter = {
			total : 0,
			befor : null
		};
		/**
		 * 行驶时间
		 */
		var runtimes = 0;

		for (var x = 0; x < this.tracks.length; x++) {
			var item = this.tracks[x];
			item.na = this.plateNumber;

			if (item.a !== 0) {
				var date = item.gt.toDate().toShortDateString();
				this.alarms.days[date] = this.alarms.days[date] || [];
				this.alarms.days[date].push(item);
				this.alarms.list.push(item);
			}
			this.speeds.push({
				x : item.gt.toDate().getTime(),
				y : item.sp
			});

			// 速度
			if (item.sp > 0) {
				velocimeter.total += item.sp;
				velocimeter.times++;
			}
			// 里程是否有归零
			if (!item.reset)
				milemeter.befor = item.m;
			if (item.m < milemeter.befor && !item.reset)
				milemeter.reset = item.m;

			// 统计油耗
			if (oilmeter.befor) {
				var oil = oilmeter.befor - item.oil;
				if (oil > 0)
					oilmeter.total += oil;
			}
			oilmeter.befor = item.oil;

			// 分析
			var accStatus = gpsDataParser.parseAcc(item);
			if (!trip.acc) {
				trip.acc = accStatus;
				trip.timeStart = item.gt;
				trip.pointStart = {
					lng : item.olng,
					lat : item.olat
				};
				trip.mileageStart = item.m;
				trip.oilStart = item.oil;
			} else if (trip.acc === accStatus) {
				trip.data.push(item);
				// 查检最后一条记录
				if (x == this.tracks.length - 1) {
					trip.timeEnd = item.gt;
					trip.pointEnd = {
						lng : item.olng,
						lat : item.olat
					};
					trip.mileageEnd = item.m;
					trip.oilEnd = item.oil;
					if (trip.acc == '点火') {// 行驶中
						var date = trip.timeStart.toDate().toShortDateString();
						this.trips.days[date] = this.trips.days[date] || [];
						this.trips.days[date].push(trip);
						this.trips.list.push(trip);

						// 统计行驶时间
						var end = trip.timeEnd.toDate().getTime();
						var start = trip.timeStart.toDate().getTime();
						runtimes += end - start;
					} else {// 停车中
						var date = trip.timeStart.toDate().toShortDateString();
						this.parks.days[date] = this.parks.days[date] || [];
						this.parks.days[date].push(trip);
						this.parks.list.push(trip);
						// 找出上一个行程
						if (this.trips.list.length > 0) {
							var prev = this.trips.list[this.trips.list.length - 1]
							prev.timeNext = item.gt;
						}
					}
				}
			} else {
				trip.timeEnd = item.gt;
				trip.pointEnd = {
					lng : item.olng,
					lat : item.olat
				};
				trip.mileageEnd = item.m;
				trip.oilEnd = item.oil;
				if (trip.acc == '点火') {// 行程结束
					trip.data.push(item);
					var date = trip.timeStart.toDate().toShortDateString();
					this.trips.days[date] = this.trips.days[date] || [];
					this.trips.days[date].push(trip);
					this.trips.list.push(trip);

					// 统计行驶时间
					var end = trip.timeEnd.toDate().getTime();
					var start = trip.timeStart.toDate().getTime();
					runtimes += end - start;
				} else {// 行程开始
					trip.data.push(item);
					var date = trip.timeStart.toDate().toShortDateString();
					this.parks.days[date] = this.parks.days[date] || [];
					this.parks.days[date].push(trip);
					this.parks.list.push(trip);
					// 找出上一个行程
					if (this.trips.list.length > 0) {
						var prev = this.trips.list[this.trips.list.length - 1]
						prev.timeNext = item.gt;
					}
				}
				trip = {
					data : []
				};
				trip.acc = accStatus;
				trip.timeStart = item.gt;
				trip.pointStart = {
					lng : item.olng,
					lat : item.olat
				};
				trip.mileageStart = item.m;
				trip.oilStart = item.oil;
				// 找出上一个停车点
				if (trip.acc == '点火' && this.parks.list.length > 0) {
					var prev = this.parks.list[this.parks.list.length - 1]
					trip.data.push(prev.data[0]);
				}
				trip.data.push(item);

			}
		}

		// 清除地图
		this.webMap.clearOverlays();
		var points = this.webMap.makePoints(this.tracks);
		// 画线
		webMap.createPolyline({
			map : this.webMap,
			points : points,
			color : 'blue'
		})

		// 标注起点,终点,停车点
		webMap.createMarker({
			map : this.webMap,
			data : this.tracks[0],
			allowShowLabel : false,
			infoWindow : replay.infoWindow,
			icon : {
				url : '../resources/images/startpoint.png',
				offset : 0
			},
			iconAnchor : {
				x : 16,
				y : 32
			},
			zIndex : 500
		});
		webMap.createMarker({
			map : this.webMap,
			data : this.tracks[this.tracks.length - 1],
			allowShowLabel : false,
			infoWindow : replay.infoWindow,
			icon : {
				url : '../resources/images/endpoint.png',
				offset : 0
			},
			iconAnchor : {
				x : 16,
				y : 32
			},
			zIndex : 500
		});
		for (var x = 0; x < this.parks.list.length; x++) {
			var park = this.parks.list[x];
			if (park.data && park.data.length > 0) {
				webMap.createMarker({
					map : this.webMap,
					data : park.data[0],
					allowShowLabel : false,
					infoWindow : replay.infoWindow,
					icon : {
						url : '../resources/images/parkpoint.png',
						offset : 0
					},
					iconAnchor : {
						x : 16,
						y : 32
					}
				});
			}
		}

		replay.webMap.setViewport(this.tracks);

		replay.repalyPages.setHeader("pageReplayTrip", "行程(" + this.trips.list.length + ")");
		replay.repalyPages.setHeader("pageReplayStop", "停车(" + this.parks.list.length + ")");
		replay.repalyPages.setHeader("pageReplayAlarm", "报警(" + this.alarms.list.length + ")");

		// 更新平均速度
		var averageSpeed = velocimeter.times == 0 ? 0 : velocimeter.total / velocimeter.times;
		$('#txtAverageSpeed').text(common.round(averageSpeed, 1) + "km/h");
		// 更新行驶时长
		$('#txtRunTime').text(common.timespan(runtimes));
		// 更新里程
		var mileage = milemeter.end - milemeter.start;
		if (milemeter.reset) {
			mileage = milemeter.befor - milemeter.start;
			mileage += milemeter.end;
		}
		$('#txtRunMileage').text(common.round(mileage, 1) + 'km');
		// 更新油耗
		$('#txtRunOil').text(common.round(oilmeter.total, 1) + 'L');
		var averageOil = 0;
		if (mileage > 0) {
			averageOil = oilmeter.total / mileage * 100;
		}
		$('#txtAverageOil').text(common.round(averageOil, 1) + 'L/100km');

		// 更新行程表
		for ( var x in this.trips.days) {
			var daytrips = this.trips.days[x];
			var newRow = [];
			newRow.push('<tr>');
			newRow.push('<td>');
			newRow.push('	<div>');
			newRow.push('		<b>' + date + '行程记录</b>');
			newRow.push('		<hr />');
			newRow.push('	</div>');
			newRow.push('</td>');
			newRow.push('</tr>');
			var row = newRow.join('');
			$("#gridReplayTrack").append($(row));
			updateGridReplayTrackRow(daytrips);
		}
		/**
		 * 更新行程表
		 */
		function updateGridReplayTrackRow(daytrips) {
			for (var index = 0; index < daytrips.length; index++) {
				var trip = daytrips[index];
				parseTrip(trip);
			}
		}
		/**
		 * 解析行程
		 */
		function parseTrip(trip) {
			var timeStart = trip.timeStart.toDate().getTime();
			var timeEnd = trip.timeEnd.toDate().getTime();
			var milliseconds = timeEnd - timeStart;
			var runtimes = common.timespan(milliseconds);
			trip.runtimes = runtimes;

			trip.paktimes = '正正行驶中......';
			if (trip.timeNext) {
				var timeNext = trip.timeNext.toDate().getTime();
				milliseconds = timeNext - timeEnd;
				var paktimes = common.timespan(milliseconds);
				trip.paktimes = paktimes;
			}
			/**
			 * 速度计
			 */
			var velocimeter = {
				total : 0,
				times : 0
			};
			/**
			 * 油量计
			 */
			oilmeter = {
				total : 0,
				befor : null
			};

			/**
			 * 里程计
			 */
			var milemeter = {
				start : trip.data[0].m,
				befor : 0,
				reset : null,
				end : trip.data[trip.data.length - 1].m
			};

			trip.mileages = 0;
			if (trip.data) {
				for (var index = 0; index < trip.data.length; index++) {
					var item = trip.data[index];
					// 速度
					if (item.sp > 0) {
						velocimeter.total += item.sp;
						velocimeter.times++;
					}
					// 里程是否有归零
					if (!item.reset)
						milemeter.befor = item.m;
					if (item.m < milemeter.befor && !item.reset)
						milemeter.reset = item.m;

					// 统计油耗
					if (oilmeter.befor) {
						var oil = oilmeter.befor - item.oil;
						if (oil > 0)
							oilmeter.total += oil;
					}
					oilmeter.befor = item.oil;
				}
			}

			// 更新平均速度
			var averageSpeed = velocimeter.times == 0 ? 0 : velocimeter.total / velocimeter.times;
			trip.averageSpeed = common.round(averageSpeed, 1);

			// 更新里程
			var mileage = milemeter.end - milemeter.start;
			if (milemeter.reset) {
				mileage = milemeter.befor - milemeter.start;
				mileage += milemeter.end;
			}
			trip.mileages = common.round(mileage, 1);
			trip.oils = common.round(oilmeter.total, 1);
			// 更新油耗
			var averageOil = 0;
			if (mileage > 0) {
				averageOil = oilmeter.total / mileage * 100;
			}
			trip.averageOil = common.round(averageOil, 1);

			var newRow = [];
			newRow.push('<tr>');
			newRow.push('<td>');
			newRow.push('	<div>');
			newRow.push('		<div><div class="mon-icon-h-x16 i-16-startpoint"></div>' + trip.timeStart.toDate().toDateTimeString("hh:mm:ss")
					+ '从：<span name="from"></span></div>');
			newRow.push('		<div><div class="mon-icon-h-x16 i-16-endpoint"></div>' + trip.timeEnd.toDate().toDateTimeString("hh:mm:ss")
					+ '到：<span name="to"></span></div>');
			newRow.push('		<div style="border:1px solid gray;"><table>');
			newRow.push('			<tr>');
			newRow.push('				<td style="width: 225px;">');
			newRow.push('					行驶时长：<span>' + trip.runtimes + '</span>');
			newRow.push('				</td>');
			newRow.push('				<td>');
			newRow.push('					行驶里程:<span>' + trip.mileages + 'km</span>');
			newRow.push('				</td>');
			newRow.push('			</tr>');
			newRow.push('			<tr>');
			newRow.push('				<td>');
			newRow.push('					行驶油耗：<span>' + trip.oils + 'L</span>');
			newRow.push('				</td>');
			newRow.push('				<td>');
			newRow.push('					平均速度:<span>' + trip.averageSpeed + 'km/h</span>');
			newRow.push('				</td>');
			newRow.push('			</tr>');
			newRow.push('			<tr>');
			newRow.push('				<td>');
			newRow.push('					平均油耗：<span>' + trip.averageOil + 'L/100km</span>');
			newRow.push('				</td>');
			newRow.push('			</tr>');
			newRow.push('		</table></div>');
			newRow.push('		<div>停留时间:' + trip.paktimes + '</div>');
			newRow.push('	</div>');
			newRow.push('</td>');
			newRow.push('</tr>');
			var row = $(newRow.join(''));

			$("#gridReplayTrack").append(row);
			row.data('trip', trip);
			row.live("click", function() {
				$("#gridReplayTrack").find(".tr-selected").removeClass('tr-selected');
				$(this).addClass('tr-selected');
				var trip = $(this).data('trip');
				if (trip && trip.data && trip.data.length > 0) {
					var points = replay.webMap.makePoints(trip.data);
					if (replay.selectedPolyline)
						replay.selectedPolyline.clear();
					// 画线
					replay.selectedPolyline = webMap.createPolyline({
						map : replay.webMap,
						points : points,
						color : 'red'
					})
					replay.webMap.panTo(trip.data[0].olng, trip.data[0].olat);
				}
			});
			replay.webMap.queryAddress(trip.pointStart.lng, trip.pointStart.lat, function(addressStart, row) {
				row.find('span[name="from"]').text(addressStart);
			}, row);
			replay.webMap.queryAddress(trip.pointEnd.lng, trip.pointEnd.lat, function(addressEnd, row) {
				row.find('span[name="to"]').text(addressEnd);
			}, row);
		}
		// 更新停车表
		$("#gridReplayPark tr").remove();
		for ( var x in this.parks.days) {
			var daytrips = this.parks.days[x];
			var newRow = [];
			newRow.push('<tr>');
			newRow.push('<td>');
			newRow.push('	<div>');
			newRow.push('		<b>' + date + '停车记录</b>');
			newRow.push('		<hr />');
			newRow.push('	</div>');
			newRow.push('</td>');
			newRow.push('</tr>');
			var row = newRow.join('');
			$("#gridReplayPark").append($(row));
			updateGridReplayParkRow(daytrips);
		}
		/**
		 * 更新停车表
		 */
		function updateGridReplayParkRow(daytrips) {
			for (var index = 0; index < daytrips.length; index++) {
				var trip = daytrips[index];
				parsePark(trip);
			}
		}
		/**
		 * 解析停车
		 */
		function parsePark(trip) {
			var timeStart = trip.timeStart.toDate().getTime();
			var timeEnd = trip.timeEnd.toDate().getTime();
			var milliseconds = timeEnd - timeStart;
			var paktimes = common.timespan(milliseconds);
			trip.paktimes = paktimes;

			var newRow = [];
			newRow.push('<tr>');
			newRow.push('<td>');
			newRow.push('	<div><div class="mon-icon-h-x16 i-16-parkpoint"></div>');
			newRow.push('		<span style="color:red;">' + trip.timeStart.toDate().toDateTimeString('hh:mm:ss') + '-'
					+ trip.timeEnd.toDate().toDateTimeString('hh:mm:ss') + '</span>');
			newRow.push('		<span>停车:<span style="color:red;">' + trip.paktimes + '</span></span>');
			newRow.push('		<span>地点:<span name="address"></span></span>');
			newRow.push('	</div>');
			newRow.push('</td>');
			newRow.push('</tr>');
			var row = $(newRow.join(''));
			$("#gridReplayPark").append(row);
			row.data('trip', trip);
			row.live("click", function() {
				$("#gridReplayPark").find(".tr-selected").removeClass('tr-selected');
				$(this).addClass('tr-selected');
				var trip = $(this).data('trip');
				if (trip) {
					// 画点
					if (replay.selectedPark)
						replay.selectedPark.dispose();
					if (trip.data && trip.data.length > 0) {
						replay.selectedPark = webMap.createMarker({
							map : replay.webMap,
							data : trip.data[0],
							allowShowLabel : false,
							infoWindow : replay.infoWindow,
							icon : {
								url : '../resources/images/parkpoint.png',
								offset : 0
							},
							iconAnchor : {
								x : 16,
								y : 32
							}
						});
						replay.selectedPark.openInfoWindow();
					}
				}
			});
			replay.webMap.queryAddress(trip.pointStart.lng, trip.pointStart.lat, function(address, row) {
				row.find('span[name="address"]').text(address);
			}, row);
		}
		// 更新报警表
		$("#gridReplayAlarm tr").remove();
		for ( var x in replay.alarms.days) {
			var dayalarms = replay.alarms.days[x];
			var newRow = [];
			newRow.push('<tr>');
			newRow.push('<td>');
			newRow.push('	<div>');
			newRow.push('		<b>' + date + '报警记录</b>');
			newRow.push('		<hr />');
			newRow.push('	</div>');
			newRow.push('</td>');
			newRow.push('</tr>');
			var row = newRow.join('');
			$("#gridReplayAlarm").append($(row));
			updateGridReplayAlarmRow(dayalarms);
		}
		/**
		 * 更新报警表
		 */
		function updateGridReplayAlarmRow(dayalarms) {
			for (var index = 0; index < dayalarms.length; index++) {
				var alarm = dayalarms[index];
				parseAlarm(alarm);
			}
		}
		/**
		 * 解析报警
		 */
		function parseAlarm(alarm) {
			var newRow = [];
			newRow.push('<tr>');
			newRow.push('<td>');
			newRow.push('	<div><div class="mon-icon-h-x16 i-16-alarmpoint"></div>');
			newRow.push('		<span>' + alarm.gt.toDate().toDateTimeString('hh:mm:ss') + '</span><br />');
			newRow.push('		<span>报警:<span style="color:red;">' + gpsDataParser.parseAlarm(alarm) + '</span></span><br />');
			newRow.push('		<span>地点:<span name="address"></span></span>');
			newRow.push('	</div>');
			newRow.push('</td>');
			newRow.push('</tr>');
			var row = $(newRow.join(''));
			$("#gridReplayAlarm").append(row);
			row.data('alarm', alarm);
			row.live("click", function() {
				$("#gridReplayAlarm").find(".tr-selected").removeClass('tr-selected');
				$(this).addClass('tr-selected');
				var alarm = $(this).data('alarm');
				if (alarm) {
					// 画点
					if (replay.selectedAlarm)
						replay.selectedAlarm.dispose();
					if (alarm) {
						replay.selectedAlarm = webMap.createMarker({
							map : replay.webMap,
							data : alarm,
							allowShowLabel : false,
							infoWindow : replay.infoWindow,
							icon : {
								url : '../resources/images/alarmpoint.png',
								offset : 0
							},
							iconAnchor : {
								x : 16,
								y : 32
							}
						});
						replay.selectedAlarm.openInfoWindow();
					}
				}
			});
			replay.webMap.queryAddress(alarm.olng, alarm.olat, function(address, row) {
				row.find('span[name="address"]').text(address);
			}, row);
		}
		replay.resetSpeedChart(replay.speeds);
		replay.resetButtons(1, 2);
	},
	download : function(number, start, end, pageIndex, pageSize) {
		replay.playStatus = 'loading';
		// 对时间需要计算
		$.post('../replay/load', {
			number : number,
			start : start,
			end : end,
			pageIndex : pageIndex,
			pageSize : pageSize
		}, function(list) {
			if (list && list.length > 0) {
				replay.webMap.translate(list, 0, function() {
					replay.pageIndex++;
					replay.tracks = replay.tracks.concat(list);
					replay.playProgress();
					replay.download(number, start, end, replay.pageIndex, replay.pageSize);
				});
			}
		});
	},
	layout : function() {
		var height = $(document).height();
		$("#replayLayout").ligerLayout({
			rightWidth : 450,
			allowRightResize : false,
			allowRightCollapse : false,
			height : height
		});
		// 设置内容高度
		var h1 = $('#group1').height();
		var h2 = $('#group2').height();
		var h3 = $('#group3').height();
		var left = height - h1 - h2 - h3 - 60;

		$('#group4').height(left);

		replay.repalyPages = $("#divReplayMapControl #repalyPages").ligerTab({
			height : left - 30
		});

		$('.content').height(left - 40);
	},
	reset : function(deviceNumber, plateNumber) {
		$('#divReplayMapControl #txtPlateNumber').text(plateNumber);
		replay.deviceNumber = deviceNumber;
		replay.plateNumber = plateNumber;
		replay.clear();
		replay.playStatus = 'init';
	},
	clear : function() {
		$('#group2 span').text('');
		$('#gridReplayTrack tr').remove();
		$('#gridReplayPark tr').remove();
		$('#gridReplayAlarm tr').remove();
		if (replay.webMap)
			replay.webMap.clearOverlays();
		replay.repalyPages.setHeader("pageReplayTrip", "行程");
		replay.repalyPages.setHeader("pageReplayStop", "停车");
		replay.repalyPages.setHeader("pageReplayAlarm", "报警");
		replay.resetButtons(1);
		replay.vehicleMarker = null;
		replay.index = 0
		$('#divReplayMapControl #divProcess').show();
		$('#divReplayMapControl #divProcess div').width('0%');
		$('#divReplayMapControl #txtProcess').hide();
		replay.resetSpeedChart();
	},
	play : function() {
		if (replay.tracks && replay.tracks.length <= 0)
			return;
		replay.playStatus = 'play';
		replay.index = replay.index || 1;
		var me = this;
		function move() {
			if (replay.playStatus !== 'play')
				return;
			if (me.index >= replay.tracks.length) {
				replay.resetButtons(1, 2);
				return;
			}
			var marker = replay.vehicleMarker;
			var row = replay.tracks[me.index];
			row.marker = replay.vehicle.marker;
			if (!marker) {
				marker = replay.vehicleMarker = webMap.createMarker({
					map : replay.webMap,
					data : row,
					allowShowLabel : false,
					allowRotate : replay.vehicle.rotate === 1,
					infoWindow : replay.infoWindow
				});
				marker.openInfoWindow();
			} else {
				marker.data = row;
				marker.refresh();
				if (replay.infoWindow.owner != marker)
					marker.openInfoWindow();
				else
					replay.webMap.panTo(marker.data.olng, marker.data.olat);
			}
			$('#divReplayMapControl #txtProcess').val(me.index);
			me.index++;
			setTimeout(move, 1000);
		}

		setTimeout(move, 1000);
	},
	whereVehilce : function() {
		var clause = function(rowdata, rowindex) {
			var key = $("#txtReplayVehicleFilter").val();
			return rowdata.dn.indexOf(key) > -1 || rowdata.na.indexOf(key) > -1;
		};
		return clause;
	}
};
$(function() {
	replay.layout();
	webMap.events.onMapLoadCompleted['replayMap'] = replay.onMapLoaded;
	webMap.createMap("replayMap");

	var endDate = new Date();
	var startDate = endDate.addDays(-1);

	replay.txtStartTime = $('#divReplayMapControl #txtStartTime').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '开始时间',
		labelWidth : 65,
		initValue : startDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});
	replay.txtEndTime = $('#divReplayMapControl #txtEndTime').ligerDateEditor({
		format : "yyyy-MM-dd hh:mm",
		cancelable : true,
		showTime : true,
		label : '结束时间',
		labelWidth : 65,
		initValue : endDate.toDateTimeString("yyyy-MM-dd hh:mm")
	});

	$('#divReplayMapControl #btnReplayDownloadTracks').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;
		replay.tracks = [];
		replay.pageIndex = 1;
		var dates = replay.selectDate();
		if (!dates) {
			$.ligerDialog.error('请选择时间段！');
			return;
		}
		if (dates.valid === false)
			return;
		var startDate = dates.start.toDateTimeString();
		var endDate = dates.end.toDateTimeString();
		$.post('../replay/count', {
			number : replay.deviceNumber,
			start : startDate,
			end : endDate
		}, function(r) {
			// 获取历史数据总数量
			replay.trackCount = r.total;
			if (replay.trackCount <= 0) {
				$.ligerDialog.error('此时间段无数据！');
				return;
			}
			$('#divReplayMapControl #divProcess').show();
			$('#divReplayMapControl #txtProcess').hide();
			replay.download(replay.deviceNumber, startDate, endDate, 1, replay.pageSize);
		});
	});
	$('#divReplayMapControl #btnRelayStart').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;
		replay.resetButtons(null, null, 3, 4);
		replay.play();
	});
	$('#divReplayMapControl #btnReplayPause').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;
		replay.resetButtons(null, 1, null, 4);
		replay.playStatus = 'pause';
	});
	$('#divReplayMapControl #btnReplayStop').click(function() {
		if ($(this).hasClass('mon-slice'))
			return;
		replay.resetButtons(1, 2);
		replay.playStatus = 'stop';
	});
	$('#divReplayMapControl #txtProcess').change(function() {
		replay.index = parseInt($(this).val());
	});
	$('#divReplayMapControl #btnReplayFindVehicle').click(function() {
		var devices = locate.devices;
		var vehicles = [];
		for ( var x in devices) {
			var device = devices[x];
			var vehicle = $.extend({}, device);

			vehicles.push(vehicle);
		}
		if (!replay.dialogReplaySearchVehicle) {
			replay.dialogReplaySearchVehicle = $.ligerDialog.open({
				title : '选择车辆',
				width : 800,
				height : 400,
				target : $("#dialogReplaySearchVehicle")
			});
		} else
			replay.dialogReplaySearchVehicle.show();

		if (!replay.gridReplaySearchVehicle) {
			replay.gridReplaySearchVehicle = $("#gridReplaySearchVehicle").ligerGrid({
				columns : [ {
					display : '定位状态',
					isAllowHide : false,
					width : 60,
					frozen : true,
					render : function(row) {
						var img = (row.o == 1 ? "mon-icon-h-x16 i-16-online" : "mon-icon-h-x16 i-16-offline");
						return '<div><div class="' + img + '"></div>' + gpsDataParser.parseLocateType(row) + '</div>';
					}
				}, {
					display : '车牌号',
					name : 'na',
					align : 'left',
					frozen : true,
					width : 100
				}, {
					display : '终端号',
					name : 'dn',
					align : 'left',
					width : 100
				}, {
					display : '速度(km/h)',
					name : 'sp',
					align : 'left',
					width : 60,
					render : function(row) {
						if (row.sp === 0)
							return '0';
						return common.round(row.sp, 1) + '';
					}
				}, {
					display : '方向',
					name : 'sp',
					width : 40,
					render : function(row) {
						return gpsDataParser.parseDirection(row);
					}
				}, {
					display : '里程(km)',
					name : 'm',
					align : 'left',
					width : 50
				}, {
					display : '状态',
					name : 's',
					align : 'left',
					width : 500,
					render : function(row) {
						return gpsDataParser.parseStatus(row);
					}
				}, {
					display : '地址',
					name : 'addr',
					align : 'left',
					width : 200
				}, {
					display : '最后定位时间',
					name : 'gt',
					width : 120
				}, {
					display : '数据接收时间',
					name : 'st',
					width : 120
				} ],
				usePager : false,
				root : 'rows',
				width : 'auto',
				height : 300,
				data : {
					rows : vehicles
				},
				alternatingRow : false
			});
		} else {
			replay.gridReplaySearchVehicle.options.data = {
				rows : vehicles
			};
			replay.gridReplaySearchVehicle.loadData(replay.whereVehilce());
		}

	});

	$('#btnReplaySearchVehicle').click(function() {
		if (!replay.gridReplaySearchVehicle)
			return;
		replay.gridReplaySearchVehicle.loadData(replay.whereVehilce());
	});
	$('#btnReplaySelectVehicle').click(function() {
		if (!replay.gridReplaySearchVehicle)
			return;
		var row = replay.gridReplaySearchVehicle.getSelectedRow();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		replay.dialogReplaySearchVehicle.hide();
		replay.reset(row.dn, row.na);
	});

	var deviceNumber = $('#txtDeviceNumber').val();
	var plateNumber = $('#txtPlateNumber').val();
	replay.reset(deviceNumber, plateNumber);

	$.get('../common/device/vehicle', {
		number : deviceNumber
	}, function(vehicle) {
		replay.vehicle = vehicle;
	})
});