/**
 * 路线区域
 */
window.routeArea = {
	gridSection : {},
	gridVehicles : {},
	flagsCheckBoxList : null,
	gridRouteArea : null,
	gridRouteAreaParams : {
		filter : ''
	},
	map : null,
	polyline : null,
	clearOverlays : function() {
		if (routeArea.map)
			routeArea.map.mapObject.clearOverlays();
	},
	redrawOverlay : function(points) {
		routeArea.clearOverlays();
		var list = [];
		for (var x = 0; x < points.length; x++) {
			var item = points[x];
			var point = new BMap.Point(item.lng, item.lat);
			point.data = item;
			list.push(point);
		}
		routeArea.polyline = new BMap.Polyline(list, {
			strokeColor : "red", // 折线颜色
			strokeWeight : 5, // 折线的宽度，以像素为单位。
			strokeOpacity : 0.6, // 折线的透明度，取值范围0 - 1。
			strokeStyle : 'solid' // 折线的样式，solid或dashed。
		});
		routeArea.map.mapObject.addOverlay(routeArea.polyline);
		routeArea.map.mapObject.setViewport(list);
	},
	onMapLoaded : function(map) {
		routeArea.map = map;
		$.get('../mapOption/query', function(r) {
			if (!r.lng)
				return;
			routeArea.map.convertor(r.lng, r.lat, function(center) {
				routeArea.map.setCenter(center);
				routeArea.map.setZoom(r.zoom);
			});
		});
	},
	showDetail : function(row, detailPanel, callback) {
		var routeAreaId = row.id;
		var panel = $('<div></div>').css('margin', 5);
		$(detailPanel).append(panel);

		var divTab = $('<div></div>');
		panel.append(divTab);

		var divSection = $('<div title="路段"></div>');
		var divVehicle = $('<div title="车辆" lselected="true"></div>');
		divTab.append(divSection);
		divTab.append(divVehicle);

		var gridSection = $('<div></div>');
		divSection.append(gridSection);
		var gridVehicle = $('<div></div>');
		divVehicle.append(gridVehicle);

		divTab.ligerTab();

		var columns = [ {
			display : '路段名称',
			name : 'name',
			width : 300
		}, {
			display : '备注',
			name : 'remark'
		} ];
		if ($('#removeSection_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(rw) {
					var html = '<a href="#" onclick="routeArea.removeSection(' + row.id + ',' + rw.id + ')">删除</a>';
					return html;
				}
			});
		}
		routeArea.gridSection[routeAreaId] = gridSection.ligerGrid({
			columns : columns,
			width : '95%',
			url : '../routeArea/sections',
			columnWidth : 100,
			onAfterShowData : callback,
			root : 'rows',
			record : 'total',
			usePager : false,
			parms : {
				routeId : row.id
			}
		});
		columns = [ {
			display : '车牌号',
			name : 'plateNumber',
			align : 'left',
			width : 150,
			frozen : true
		}, {
			display : '设备号',
			name : 'deviceNumber',
			align : 'left',
			width : 150
		}, {
			display : '绑定时间',
			name : 'time',
			align : 'left',
			type : 'date',
			width : 150
		} ];
		if ($('#removeVehicle_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(rw) {
					var html = '<a href="#" onclick="routeArea.removeVehicle(\'' + row.id + '\',\'' + rw.deviceNumber + '\')">删除</a>';
					return html;
				}
			});
		}
		routeArea.gridVehicles[routeAreaId] = gridVehicle.ligerGrid({
			columns : columns,
			url : '../routeArea/vehicles',
			columnWidth : 100,
			onAfterShowData : callback,
			root : 'rows',
			record : 'total',
			pageParmName : 'pageIndex',
			pagesizeParmName : 'pageSize',
			parms : {
				routeAreaId : routeAreaId
			}
		});
	},
	addSection : function(routeAreaId) {
		routeArea.addSectionParms = routeArea.addSectionParms || {};
		routeArea.addSectionParms.routeAreaId = routeAreaId;
		if (!routeArea.dialogRouteSelectSection) {
			routeArea.gridSectionInRouteParms = {
				filter : ''
			};
			routeArea.dialogRouteSelectSection = $.ligerDialog.open({
				title : '选择路段',
				width : 750,
				height : 400,
				target : $("#dialogRouteSelectSection")
			});
			routeArea.gridSectionInRoute = $("#dialogRouteSelectSection #gridSectionInRoute55c6d38e1d19b641500578f7").ligerGrid({
				columns : [ {
					display : '名称',
					name : 'name',
					align : 'left',
					width : 200
				}, {
					display : '说明',
					name : 'remark',
					align : 'left',
					width : 300
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../sectionArea/query',
				width : 'auto',
				height : 300,
				pageSize : 30,
				rownumbers : true,
				checkbox : true,
				parms : routeArea.gridSectionInRouteParms
			});

			$('#dialogRouteSelectSection #btnQuerySectionArea').click(function() {
				routeArea.gridSectionInRouteParms.filter = $('#dialogRouteSelectSection #txtSectionAreaFilter').val();
				routeArea.gridSectionInRoute.reload();
			});

			$('#dialogRouteSelectSection #btnSelectSectionArea').click(function() {
				var rows = routeArea.gridSectionInRoute.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				routeArea.dialogRouteSelectSection.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].id);
				}
				$.post('../routeArea/addSections', {
					routeId : routeArea.addSectionParms.routeAreaId,
					list : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "添加路段成功！");
					if (result === true)
						routeArea.gridRouteArea.reload();
				});
			});
		} else
			routeArea.dialogRouteSelectSection.show();
	},
	removeSection : function(routeId, sectionId) {
		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../routeArea/removeSection', {
				routeId : routeId,
				sectionId : sectionId
			}, function(data) {
				var result = common.checkData(data, "", "删除路段成功！");
				if (result === true)
					routeArea.gridRouteArea.reload();
			});
		});
	},
	addVehicle : function(routeAreaId) {
		routeArea.addVehicleParms = routeArea.addVehicleParms || {};
		routeArea.addVehicleParms.routeAreaId = routeAreaId;

		if (!routeArea.dialogSelectVehicle) {
			routeArea.gridVehicleInRouteAreaParms = {
				filter : ''
			};
			routeArea.dialogSelectVehicle = $.ligerDialog.open({
				title : '选择车辆',
				width : 800,
				height : 400,
				target : $("#dialogRouteAreaSelectVehicle")
			});
			routeArea.gridVehicleInRouteArea = $("#gridVehicleInRouteArea").ligerGrid({
				columns : [ {
					display : '车牌号',
					name : 'plateNumber',
					align : 'left',
					width : 150,
					frozen : true
				}, {
					display : '车牌颜色',
					name : 'plateColor',
					align : 'left',
					width : 50
				}, {
					display : '设备号',
					name : 'deviceNumber',
					align : 'left',
					width : 150
				}, {
					display : '电话号码',
					name : 'phoneNumber',
					align : 'left',
					width : 100
				}, {
					display : '安装日期',
					name : 'installDate',
					align : 'left',
					type : 'date',
					width : 80
				}, {
					display : '年检日期',
					name : 'annualSurveyDate',
					align : 'left',
					type : 'date',
					width : 80
				}, {
					display : '车队',
					name : 'motorcade',
					align : 'left',
					width : 100
				}, {
					display : '备注',
					name : 'remark',
					align : 'left',
					width : 100
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../vehicle/query',
				width : 'auto',
				height : 300,
				pageSize : 30,
				rownumbers : true,
				checkbox : true,
				parms : routeArea.gridVehicleInRouteAreaParms
			});

			$('#dialogRouteAreaSelectVehicle #btnQueryVehicle').click(function() {
				routeArea.gridVehicleInRouteAreaParms.filter = $('#dialogRouteAreaSelectVehicle #txtVehicleFilter').val();
				routeArea.gridVehicleInRouteArea.reload();
			});

			$('#dialogRouteAreaSelectVehicle #btnSelectVehicle').click(function() {
				var rows = routeArea.gridVehicleInRouteArea.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				routeArea.dialogSelectVehicle.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].deviceNumber);
				}
				$.post('../routeArea/addVehicles', {
					routeAreaId : routeArea.addVehicleParms.routeAreaId,
					vehicles : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "添加车辆成功！");
					if (result === true) {
						if (routeArea.addVehicleParms.routeAreaId in routeArea.gridVehicles)
							routeArea.gridVehicles[routeArea.addVehicleParms.routeAreaId].reload();
					}
				});
			});
		} else
			routeArea.dialogSelectVehicle.show();
	},
	removeVehicle : function(routeAreaId, number) {
		routeArea.removeVehicleParms = routeArea.removeVehicleParms || {};
		routeArea.removeVehicleParms.routeAreaId = routeAreaId;
		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../routeArea/removeVehicle', {
				routeAreaId : routeAreaId,
				number : number
			}, function(data) {
				var result = common.checkData(data, "", "删除车辆成功！");
				if (result === true) {
					if (routeArea.removeVehicleParms.routeAreaId in routeArea.gridVehicles)
						routeArea.gridVehicles[routeArea.removeVehicleParms.routeAreaId].reload();
				}
			});
		});
	},
	query : function() {
		var columns = [ {
			display : '名称',
			name : 'name',
			align : 'left',
			width : 200
		}, {
			display : '终端计算否',
			name : 'deviceCatch',
			width : 80,
			render : function(row) {
				if (row.deviceCatch === true)
					return '是';
				else if (row.deviceCatch === false)
					return '否';
				return '';
			}
		}, {
			display : '说明',
			name : 'remark',
			align : 'left'
		} ];
		if ($('#addSections_auth').val() == 'true') {
			columns.push({
				display : '路段',
				isAllowHide : false,
				width : 120,
				render : function(row) {
					return '<a href="#" onclick="routeArea.addSection(' + row.id + ')">添加路段</a>';
				}
			});
		}
		if ($('#addVehicles_auth').val() == 'true') {
			columns.push({
				display : '车辆',
				isAllowHide : false,
				width : 120,
				render : function(row) {
					return '<a href="#" onclick="routeArea.addVehicle(\'' + row.id + '\')">绑定车辆</a>';
				}
			});
		}
		if (!routeArea.gridRouteArea)
			routeArea.gridRouteArea = $("#routeAreaFrame #gridRouteArea").ligerGrid({
				columns : columns,
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../routeArea/query',
				width : '100%',
				height : 274,
				pageSize : 30,
				parms : routeArea.gridRouteAreaParams,
				onSelectRow : function(row) {
					var items = [];
					for (var x = 0; x < row.sections.length; x++) {
						var list = row.sections[x].points;
						for (var y = 0; y < list.length; y++) {
							var item = list[y];
							items.push(item);
						}
					}
					routeArea.map.translate(items, 0, function(points) {
						var path = [];
						for (var y = 0; y < points.length; y++) {
							var p = points[y];
							path.push({
								lng : p.olng,
								lat : p.olat
							});
						}
						routeArea.redrawOverlay(path);
					})
				},
				onSuccess : function() {
					routeArea.clearOverlays();
				},
				detail : {
					onShowDetail : routeArea.showDetail,
					height : 200
				}
			});
		else {
			routeArea.gridRouteArea.changePage('first');
			routeArea.gridRouteArea.reload();
		}
	},
	create : function() {
		var url = '../routeArea/create.form';
		$.get(url, function(html) {
			var op = {
				url : url,
				allowClose : false,
				width : 750,
				height : 300,
				isHidden : false,
				title : '创建路线区域',
				onLoaded : function() {
					routeArea.dialog.frame.window.validateCreate();
					routeArea.dialog.frame.window.resetLigerui();
				},
				buttons : [ {
					text : '确定',
					onclick : function(item, dialog) {
						dialog.frame.window.resetFlag();
						if (dialog.frame.window.validateCreate() == false) {
							return;
						}

						var form = $('form', dialog.frame.document);
						var formData = form.serialize();
						$.post(url, formData, function(data) {
							var result = common.form.save(dialog, data, op);
							if (result === true)
								routeArea.gridRouteArea.reload();
							else
								dialog.frame.window.resetLigerui();
						});
					}
				}, {
					text : '取消',
					onclick : function(item, dialog) {
						routeArea.map.mapObject.removeOverlay();
						dialog.close();
					}
				} ]
			};
			routeArea.dialog = $.ligerDialog.open(op);
		});
	},
	edit : function(id) {
		var url = '../routeArea/edit.form';
		var op = {
			url : url,
			urlParms : {
				id : id
			},
			width : 750,
			height : 300,
			isHidden : false,
			title : '修改路线区域',
			onLoaded : function() {
				routeArea.dialog.frame.window.validateEdit();
				routeArea.dialog.frame.window.resetLigerui();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					dialog.frame.window.resetFlag();
					if (dialog.frame.window.validateEdit() == false) {
						return;
					}

					var form = $('form', dialog.frame.document);
					var formData = form.serialize();
					$.post(url, formData, function(data) {
						var result = common.form.save(dialog, data, op);
						if (result === true)
							routeArea.gridRouteArea.reload();
						else
							dialog.frame.window.resetLigerui();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		routeArea.dialog = $.ligerDialog.open(op);
	},
	remove : function(id) {
		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../routeArea/delete', {
				id : id
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					routeArea.gridRouteArea.reload();
			});
		});
	},
	resize : function() {
		var height = $(document).height();
		$('#routeAreaFrame #divRouteAreaAllMap').height(height - 300);
	}
};
$(function() {
	routeArea.resize();
	routeArea.query();
	webMap.events.onMapLoadCompleted['routeAreaMap'] = routeArea.onMapLoaded;
	webMap.createMap('routeAreaMap');
	$('#routeAreaFrame #btnQueryRouteArea').click(function() {
		routeArea.gridRouteAreaParams.filter = $('#routeAreaFrame #txtRouteAreaFilter').val();
		routeArea.query();
	});

	$('#routeAreaFrame #btnCreateRouteArea').click(function() {
		routeArea.create();
	});

	$('#routeAreaFrame #btnEditRouteArea').click(function() {
		if (!routeArea.gridRouteArea)
			return;

		var row = routeArea.gridRouteArea.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		routeArea.edit(row.id);
	});
	$('#routeAreaFrame #btnRemoveRouteArea').click(function() {
		if (!routeArea.gridRouteArea)
			return;

		var row = routeArea.gridRouteArea.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		routeArea.remove(row.id);
	});
	$(window).resize(function() {
		routeArea.resize();
	});
});