/**
 * 驾驶员管理
 */
window.driver = {
	gridVehicles : {},
	gridDriver : null,
	gridDriverParams : {
		filter : ''
	},
	listVehicle : function(row, detailPanel, callback) {
		var driverId = row.id;
		var grid = document.createElement('div');
		$(detailPanel).append(grid);
		var columns = [ {
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
		} ];
		if ($('#removeVehicle_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(rw) {
					var html = '<a href="#" onclick="driver.removeVehicle(\'' + row.id + '\',\'' + rw.id + '\')">删除</a>';
					return html;
				}
			});
		}
		driver.gridVehicles[driverId] = $(grid).css('margin', 5).ligerGrid({
			columns : columns,
			width : '98%',
			// height : 300,
			url : '../driver/vehicles',
			title : '车辆列表',
			showTitle : true,
			columnWidth : 100,
			onAfterShowData : callback,
			root : 'rows',
			record : 'total',
			usePager : false,
			parms : {
				driverId : driverId
			}
		});
	},
	addVehicle : function(driverId) {
		driver.addVehicleParms = driver.addVehicleParms || {};
		driver.addVehicleParms.driverId = driverId;

		if (!driver.dialogSelectVehicle) {
			driver.gridVehicleInDriverParms = {
				filter : ''
			};
			driver.dialogSelectVehicle = $.ligerDialog.open({
				title : '选择车辆',
				width : 800,
				height : 400,
				target : $("#dialogDriverSelectVehicle")
			});
			driver.gridVehicleInDriver = $("#dialogDriverSelectVehicle #gridVehicleInDriver55c6cc891d19b64134b972ed").ligerGrid({
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
				parms : driver.gridVehicleInDriverParms
			});

			$('#dialogDriverSelectVehicle #btnQueryVehicle').click(function() {
				driver.gridVehicleInDriverParms.filter = $('#dialogDriverSelectVehicle #txtVehicleFilter').val();
				driver.gridVehicleInDriver.reload();
			});

			$('#dialogDriverSelectVehicle #btnSelectVehicle').click(function() {
				var rows = driver.gridVehicleInDriver.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				driver.dialogSelectVehicle.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].id);
				}
				$.post('../driver/addVehicles', {
					driverId : driver.addVehicleParms.driverId,
					vehicles : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "添加车辆成功！");
					if (result === true) {
						if (driver.addVehicleParms.driverId in driver.gridVehicles)
							driver.gridVehicles[driver.addVehicleParms.driverId].reload();
					}
				});
			});
		} else
			driver.dialogSelectVehicle.show();
	},
	removeVehicle : function(driverId, vehicleId) {
		driver.removeVehicleParms = driver.removeVehicleParms || {};
		driver.removeVehicleParms.driverId = driverId;
		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../driver/removeVehicle', {
				driverId : driverId,
				vehicleId : vehicleId
			}, function(data) {
				var result = common.checkData(data, "", "删除车辆成功！");
				if (result === true) {
					if (driver.removeVehicleParms.driverId in driver.gridVehicles)
						driver.gridVehicles[driver.removeVehicleParms.driverId].reload();
				}
			});
		});
	},
	query : function() {
		var columns = [ {
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
			align : 'left'
		} ];
		if ($('#addVehicles_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(row) {
					var html = '<a href="#" onclick="driver.addVehicle(\'' + row.id + '\')">分配车辆</a>';
					return html;
				}
			});
		}
		if (!driver.gridDriver)
			driver.gridDriver = $("#driverFrame #gridDriver").ligerGrid({
				columns : columns,
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../driver/query',
				width : 'auto',
				height : '100%',
				pageSize : 30,
				parms : driver.gridDriverParams,
				detail : {
					onShowDetail : driver.listVehicle,
					height : 'auto'
				}
			});
		else {
			driver.gridDriver.changePage('first');
			driver.gridDriver.reload();
		}
	},
	create : function() {
		var url = '../driver/create.form';
		var op = {
			url : url,
			width : 800,
			height : 300,
			isHidden : false,
			title : '创建驾驶员',
			onLoaded : function() {
				driver.dialog.frame.window.validateCreate();
				driver.dialog.frame.window.resetLigerui();
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
							driver.gridDriver.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		driver.dialog = $.ligerDialog.open(op);
	},
	edit : function(id) {
		var url = '../driver/edit.form';

		var op = {
			url : url,
			urlParms : {
				id : id
			},
			width : 800,
			height : 300,
			isHidden : false,
			title : '修改驾驶员',
			onLoaded : function() {
				driver.dialog.frame.window.validateEdit();
				driver.dialog.frame.window.resetLigerui();
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
							driver.gridDriver.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		driver.dialog = $.ligerDialog.open(op);
	},
	remove : function(id) {
		$.ligerDialog.confirm('将删除驾驶员所有相关资料，且不可恢复，真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../driver/delete', {
				id : id
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					driver.gridDriver.reload();
			});
		});
	}
};
$(function() {
	driver.query();
	$('#driverFrame #btnQueryDriver').click(function() {
		if (!driver.gridDriver)
			return;
		driver.gridDriverParams.filter = $('#driverFrame #txtDriverFilter').val();
		driver.query();
	});
	$('#driverFrame #btnCreateDriver').click(function() {
		driver.create();
	});
	$('#driverFrame #btnEditDriver').click(function() {
		if (!driver.gridDriver)
			return;

		var row = driver.gridDriver.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		driver.edit(row.id);
	});
	$('#driverFrame #btnRemoveDriver').click(function() {
		if (!driver.gridDriver)
			return;

		var row = driver.gridDriver.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		driver.remove(row.id);
	});
});