/**
 * 车辆管理
 */
window.vehicle = {
	gridOwners : {},// 用于存放车主表格
	gridDrivers : {},// 用于存放驾驶员表格
	gridVehicle : undefined,
	gridVehicleParams : {
		filter : ''
	},
	showDetail : function(row, detailPanel, callback) {
		var vehicleId = row.id;
		var panel = $('<div></div>').css('margin', 5);
		$(detailPanel).append(panel);

		var divTab = $('<div></div>');
		panel.append(divTab);

		var divOwner = $('<div title="车主" lselected="true"></div>');
		var divDriver = $('<div title="驾驶员"></div>');
		divTab.append(divOwner);
		divTab.append(divDriver);

		var gridOwner = $('<div>owner</div>');
		divOwner.append(gridOwner);
		var gridDriver = $('<div>driver</div>');
		divDriver.append(gridDriver);

		divTab.ligerTab();

		var columns = [ {
			display : '姓名',
			name : 'ownerName',
			align : 'left',
			width : 100
		}, {
			display : '电话',
			name : 'phone',
			align : 'left',
			width : 100
		}, {
			display : '证件类型',
			name : 'idType',
			align : 'left',
			width : 100
		}, {
			display : '证件编号',
			name : 'idNumber',
			align : 'left',
			width : 150
		}, {
			display : '服务开始时间',
			name : 'serviceStartDate',
			align : 'left',
			type : 'date',
			width : 100
		}, {
			display : '服务结束时间',
			name : 'serviceEndDate',
			align : 'left',
			type : 'date',
			width : 100
		}, {
			display : '启用否',
			name : 'enabled',
			width : 80,
			render : function(row) {
				if (row.enabled === true)
					return '启用';
				else if (row.enabled === false)
					return '禁用';
				return '';
			}
		}, {
			display : '开户时间',
			name : 'createTime',
			type : 'date',
			width : 100
		}, {
			display : '备注',
			name : 'remark',
			align : 'left',
			width : 100
		} ];
		if ($('#removeOwner_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(row) {
					var html = '<a href="#" onclick="vehicle.removeOwner(\'' + vehicleId + '\',\'' + row.id + '\')">删除</a>';
					return html;
				}
			});
		}
		vehicle.gridOwners[vehicleId] = gridOwner.ligerGrid({
			columns : columns,
			root : 'rows',
			record : 'total',
			url : '../vehicle/owners',
			width : 'auto',
			height : 150,
			usePager : false,
			parms : {
				vehicleId : vehicleId
			}
		});
		columns = [ {
			display : '姓名',
			name : 'name',
			align : 'left',
			width : 150
		}, {
			display : '性别',
			name : 'sex',
			width : 50
		}, {
			display : '电话',
			name : 'phone',
			align : 'left',
			width : 150
		}, {
			display : '驾驶证号',
			name : 'drivingLicenseNumber',
			align : 'left',
			width : 150
		}, {
			display : '备注',
			name : 'remark',
			align : 'left',
			width : 100
		} ];
		if ($('#removeDriver_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(row) {
					var html = '<a href="#" onclick="vehicle.removeDriver(\'' + vehicleId + '\',\'' + row.id + '\')">删除</a>';
					return html;
				}
			});
		}
		vehicle.gridDrivers[vehicleId] = gridDriver.ligerGrid({
			columns : columns,
			root : 'rows',
			record : 'total',
			url : '../vehicle/drivers',
			width : 'auto',
			height : 150,
			usePager : false,
			parms : {
				vehicleId : vehicleId
			}
		});
	},
	addOwner : function(vehicleId) {
		vehicle.addOwnerParms = vehicle.addOwnerParms || {};
		vehicle.addOwnerParms.vehicleId = vehicleId;

		if (!vehicle.dialogVehicleSelectOwner) {
			vehicle.gridVehicleInOwnerParms = {
				filter : ''
			};
			vehicle.dialogVehicleSelectOwner = $.ligerDialog.open({
				title : '选择车主',
				width : 800,
				height : 400,
				target : $("#dialogVehicleSelectOwner")
			});
			vehicle.gridVehicleInOwner = $("#dialogVehicleSelectOwner #gridVehicleInOwner55c6cc891d19b64134b972ea").ligerGrid({
				columns : [ {
					display : '姓名',
					name : 'ownerName',
					align : 'left',
					width : 100
				}, {
					display : '电话',
					name : 'phone',
					align : 'left',
					width : 100
				}, {
					display : '证件类型',
					name : 'idType',
					align : 'left',
					width : 100
				}, {
					display : '证件编号',
					name : 'idNumber',
					align : 'left',
					width : 150
				}, {
					display : '服务开始时间',
					name : 'serviceStartTime',
					align : 'left',
					type : 'date',
					width : 100
				}, {
					display : '服务结束时间',
					name : 'serviceEndTime',
					align : 'left',
					type : 'date',
					width : 100
				}, {
					display : '启用否',
					name : 'enabled',
					width : 80,
					render : function(row) {
						if (row.enabled === true)
							return '启用';
						else if (row.enabled === false)
							return '禁用';
						return '';
					}
				}, {
					display : '开户时间',
					name : 'createTime',
					type : 'date',
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
				url : '../owner/query',
				width : 'auto',
				height : 300,
				pageSize : 30,
				rownumbers : true,
				checkbox : true,
				parms : vehicle.gridVehicleInOwnerParms
			});

			$('#dialogVehicleSelectOwner #btnQueryOwner').click(function() {
				vehicle.gridVehicleInOwnerParms.filter = $('#dialogVehicleSelectOwner #txtOwnerFilter').val();
				vehicle.gridVehicleInOwner.reload();
			});

			$('#dialogVehicleSelectOwner #btnSelectOwner').click(function() {
				var rows = vehicle.gridVehicleInOwner.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				vehicle.dialogVehicleSelectOwner.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].id);
				}
				$.post('../vehicle/addOwners', {
					vehicleId : vehicle.addOwnerParms.vehicleId,
					owners : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "绑定车主成功！");
					if (result === true) {
						if (vehicle.addOwnerParms.vehicleId in vehicle.gridOwners)
							vehicle.gridOwners[vehicle.addOwnerParms.vehicleId].reload();
					}
				});
			});
		} else
			vehicle.dialogVehicleSelectOwner.show();
	},
	removeOwner : function(vehicleId, ownerId) {
		vehicle.removeOwnerParms = vehicle.removeOwnerParms || {};
		vehicle.removeOwnerParms.vehicleId = vehicleId;

		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../vehicle/removeOwner', {
				vehicleId : vehicleId,
				ownerId : ownerId
			}, function(data) {
				var result = common.checkData(data, "", "删除车辆成功！");
				if (result === true) {
					if (vehicle.removeOwnerParms.vehicleId in vehicle.gridOwners)
						vehicle.gridOwners[vehicle.removeOwnerParms.vehicleId].reload();
				}
			});
		});
	},
	addDriver : function(vehicleId) {
		vehicle.addDriverParms = vehicle.addDriverParms || {};
		vehicle.addDriverParms.vehicleId = vehicleId;

		if (!vehicle.dialogVehicleSelectDriver) {
			vehicle.gridVehicleInDriverParms = {
				filter : ''
			};
			vehicle.dialogVehicleSelectDriver = $.ligerDialog.open({
				title : '选择驾驶员',
				width : 800,
				height : 400,
				target : $("#dialogVehicleSelectDriver")
			});
			vehicle.gridVehicleInDriver = $("#dialogVehicleSelectDriver #gridVehicleInDriver55c6cc891d19b64134b972eb").ligerGrid({
				columns : [ {
					display : '姓名',
					name : 'name',
					align : 'left',
					width : 150
				}, {
					display : '性别',
					name : 'sex',
					width : 50
				}, {
					display : '电话',
					name : 'phone',
					align : 'left',
					width : 150
				}, {
					display : '驾驶证号',
					name : 'drivingLicenseNumber',
					align : 'left',
					width : 150
				}, {
					display : '备注',
					name : 'remark',
					align : 'left',
					width : 'auto'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../driver/query',
				width : 'auto',
				height : 300,
				pageSize : 30,
				rownumbers : true,
				checkbox : true,
				parms : vehicle.gridVehicleInDriverParms
			});

			$('#dialogVehicleSelectDriver #btnQueryDriver').click(function() {
				vehicle.gridVehicleInDriverParms.filter = $('#dialogVehicleSelectDriver #txtDriverFilter').val();
				vehicle.gridVehicleInDriver.reload();
			});

			$('#dialogVehicleSelectDriver #btnSelectDriver').click(function() {
				var rows = vehicle.gridVehicleInDriver.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				vehicle.dialogVehicleSelectDriver.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].id);
				}
				$.post('../vehicle/addDrivers', {
					vehicleId : vehicle.addDriverParms.vehicleId,
					drivers : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "绑定驾驶员成功！");
					if (result === true) {
						if (vehicle.addDriverParms.vehicleId in vehicle.gridDrivers)
							vehicle.gridDrivers[vehicle.addDriverParms.vehicleId].reload();
					}
				});
			});
		} else
			vehicle.dialogVehicleSelectDriver.show();
	},
	removeDriver : function(vehicleId, driverId) {
		vehicle.removeDriverParms = vehicle.removeDriverParms || {};
		vehicle.removeDriverParms.vehicleId = vehicleId;

		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../vehicle/removeDriver', {
				vehicleId : vehicleId,
				driverId : driverId
			}, function(data) {
				var result = common.checkData(data, "", "删除驾驶员成功！");
				if (result === true) {
					if (vehicle.removeDriverParms.vehicleId in vehicle.gridDrivers)
						vehicle.gridDrivers[vehicle.removeDriverParms.vehicleId].reload();
				}
			});
		});
	},
	query : function() {
		var columns = [ {
			display : '车牌号',
			name : 'plateNumber',
			align : 'left',
			width : 150
		}, {
			display : '车牌颜色',
			name : 'plateColor',
			align : 'left',
			width : 50
		}, {
			display : '设备号',
			name : 'deviceNumber',
			align : 'left',
			width : 100
		}, {
			display : '电话号码',
			name : 'phoneNumber',
			align : 'left',
			width : 100
		}, {
			display : '安装日期',
			name : 'installDate',
			type : 'date',
			width : 100
		}, {
			display : '年检日期',
			name : 'annualSurveyDate',
			type : 'date',
			width : 100
		}, {
			display : '车队',
			name : 'motorcade',
			align : 'left',
			width : 150
		}, {
			display : '车辆图标',
			name : 'marker',
			align : 'left',
			width : 150,
			render : function(row) {
				return '<img src="../resources/icons/' + row.marker + '" />';
			}
		}, {
			display : '备注',
			name : 'remark',
			align : 'left',
			width : 100
		} ];
		if ($('#addOwners_auth').val() == 'true') {
			columns.push({
				display : '车主',
				width : 80,
				isAllowHide : false,
				render : function(row) {
					var html = '<a href="#" onclick="vehicle.addOwner(\'' + row.id + '\')">绑定车主</a>';
					return html;
				}
			});
		}
		if ($('#addDrivers_auth').val() == 'true') {
			columns.push({
				display : '驾驶员',
				width : 80,
				isAllowHide : false,
				render : function(row) {
					var html = '<a href="#" onclick="vehicle.addDriver(\'' + row.id + '\')">绑定驾驶员</a>';
					return html;
				}
			});
		}
		if (!vehicle.gridVehicle)
			vehicle.gridVehicle = $("#vehicleFrame #gridVehicle").ligerGrid({
				columns : columns,
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../vehicle/query',
				width : 'auto',
				height : '100%',
				pageSize : 30,
				rowHeight : 32,
				parms : vehicle.gridVehicleParams,
				detail : {
					onShowDetail : vehicle.showDetail,
					height : 'auto'
				}
			});
		else {
			vehicle.gridVehicle.changePage('first');
			vehicle.gridVehicle.reload();
		}
	},
	create : function() {
		var url = '../vehicle/create.form';
		var op = {
			url : url,
			width : 850,
			height : 480,
			isHidden : false,
			title : '创建车辆',
			onLoaded : function() {
				vehicle.dialog.frame.window.validateCreate();
				vehicle.dialog.frame.window.resetLigerui();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.validateCreate() == false) {
						return;
					}

					var form = $('form', dialog.frame.document);
					var formData = form.serialize();
					$.post(url, formData, function(data) {
						var result = common.form.save(dialog, data, op);
						if (result === true)
							vehicle.gridVehicle.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		vehicle.dialog = $.ligerDialog.open(op);
	},
	edit : function(id) {
		var url = '../vehicle/edit.form';
		var op = {
			url : url,
			urlParms : {
				id : id
			},
			width : 850,
			height : 480,
			isHidden : false,
			title : '修改车辆',
			onLoaded : function() {
				vehicle.dialog.frame.window.validateEdit();
				vehicle.dialog.frame.window.resetLigerui();
			},
			buttons : [ {
				text : '确定',
				onclick : function(item, dialog) {
					if (dialog.frame.window.validateEdit() == false) {
						return;
					}

					var form = $('form', dialog.frame.document);
					var formData = form.serialize();
					$.post(url, formData, function(data) {
						var result = common.form.save(dialog, data, op);
						if (result === true)
							vehicle.gridVehicle.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ],
		};
		vehicle.dialog = $.ligerDialog.open(op);
	},
	remove : function(id) {
		$.ligerDialog.confirm('将删除车辆所有相关资料，且不可恢复，真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../vehicle/delete', {
				id : id
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					vehicle.gridVehicle.reload();
			});
		});
	}
}
$(function() {
	vehicle.query();

	$('#vehicleFrame #btnCreateVehicle').click(function() {
		vehicle.create();
	});
	$('#vehicleFrame #btnEditVehicle').click(function() {
		if (!vehicle.gridVehicle)
			return;

		var row = vehicle.gridVehicle.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		vehicle.edit(row.id);
	});
	$('#vehicleFrame #btnRemoveVehicle').click(function() {
		if (!vehicle.gridVehicle)
			return;

		var row = vehicle.gridVehicle.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		vehicle.remove(row.id);
	});
	$('#vehicleFrame #btnQueryVehicle').click(function() {
		if (!vehicle.gridVehicle)
			return;
		vehicle.gridVehicleParams.filter = $('#vehicleFrame #txtVehicleFilter').val();
		vehicle.query();
	});
});