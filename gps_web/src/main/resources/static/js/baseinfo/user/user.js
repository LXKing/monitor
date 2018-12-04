/**
 * 用户管理
 */
window.user = {
	gridMonitors : {},
	gridRoles : {},
	gridUser : undefined,
	gridUserParams : {
		filter : ''
	},
	showDetail : function(row, detailPanel, callback) {
		var userId = row.id;
		var panel = $('<div></div>').css('margin', 5);
		$(detailPanel).append(panel);

		var divTab = $('<div></div>');
		panel.append(divTab);

		var divMonitor = $('<div title="监控" lselected="true"></div>');
		var divRole = $('<div title="角色"></div>');
		divTab.append(divMonitor);
		divTab.append(divRole);

		var gridMonitor = $('<div>owner</div>');
		divMonitor.append(gridMonitor);
		var gridRole = $('<div>driver</div>');
		divRole.append(gridRole);

		divTab.ligerTab();

		var columns = [ {
			display : '名称',
			name : 'name',
			align : 'left',
			width : 150
		}, {
			display : '类型',
			name : 'type',
			align : 'left',
			width : 100
		}, {
			display : '说明',
			name : 'remark',
			align : 'left',
			width : 150
		} ];
		if ($('#removeMonitor_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(row) {
					var html = '<a href="#" onclick="user.removeMonitor(\'' + userId + '\',\'' + row.id + '\')">删除</a>';
					return html;
				}
			});
		}

		user.gridMonitors[userId] = gridMonitor.ligerGrid({
			columns : columns,
			root : 'rows',
			record : 'total',
			url : '../user/monitors',
			width : 'auto',
			height : 150,
			usePager : false,
			parms : {
				userId : userId
			}
		});

		columns = [ {
			display : '名称',
			name : 'name',
			align : 'left',
			width : 200
		}, {
			display : '说明',
			name : 'remark',
			align : 'left',
			width : 150
		} ];
		if ($('#removeRole_auth').val() == 'true') {
			columns.push({
				display : '操作',
				isAllowHide : false,
				width : 100,
				render : function(row) {
					var html = '<a href="#" onclick="user.removeRole(\'' + userId + '\',\'' + row.id + '\')">删除</a>';
					return html;
				}
			});
		}
		user.gridRoles[userId] = gridRole.ligerGrid({
			columns : columns,
			root : 'rows',
			record : 'total',
			url : '../user/roles',
			width : 'auto',
			height : 150,
			usePager : false,
			parms : {
				userId : userId
			}
		});
	},
	addMonitor : function(userId) {
		user.addMonitorParms = user.addMonitorParms || {};
		user.addMonitorParms.userId = userId;

		if (!user.dialogUserSelectMonitor) {
			user.gridUserSelectMonitorParms = {
				filter : ''
			};
			user.dialogUserSelectMonitor = $.ligerDialog.open({
				title : '选择监控对象',
				width : 800,
				height : 400,
				target : $("#dialogUserSelectMonitor")
			});
			user.gridUserSelectMonitor = $("#dialogUserSelectMonitor #gridUserSelectMonitor55c80c4d1d19b6424f084014").ligerGrid({
				columns : [ {
					display : '名称',
					name : 'name',
					align : 'left',
					width : 150
				}, {
					display : '类型',
					name : 'type',
					align : 'left',
					width : 100
				}, {
					display : '备注',
					name : 'remark',
					align : 'left',
					width : 150
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../user/targets',
				width : 'auto',
				height : 300,
				pageSize : 30,
				rownumbers : true,
				checkbox : true,
				parms : user.gridUserSelectMonitorParms
			});

			$('#dialogUserSelectMonitor #btnQueryMonitor').click(function() {
				user.gridUserSelectMonitorParms.filter = $('#dialogUserSelectMonitor #txtMonitorFilter').val();
				user.gridUserSelectMonitor.reload();
			});

			$('#dialogUserSelectMonitor #btnSelectMonitor').click(function() {
				var rows = user.gridUserSelectMonitor.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				user.dialogUserSelectMonitor.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].id);
				}
				$.post('../user/addMonitors', {
					userId : user.addMonitorParms.userId,
					targets : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "添加监控对象！");
					if (result === true) {
						if (user.addMonitorParms.userId in user.gridMonitors)
							user.gridMonitors[user.addMonitorParms.userId].reload();
					}
				});
			});
		} else
			user.dialogUserSelectMonitor.show();
	},
	removeMonitor : function(userId, targetId) {
		user.removeMonitorParms = user.removeMonitorParms || {};
		user.removeMonitorParms.userId = userId;

		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../user/removeMonitor', {
				userId : userId,
				targetId : targetId
			}, function(data) {
				var result = common.checkData(data, "", "删除监控对象成功！");
				if (result === true) {
					if (user.removeMonitorParms.userId in user.gridMonitors)
						user.gridMonitors[user.removeMonitorParms.userId].reload();
				}
			});
		});
	},
	addRole : function(userId) {
		user.addRoleParms = user.addRoleParms || {};
		user.addRoleParms.userId = userId;

		if (!user.dialogUserSelectRole) {
			user.dialogUserSelectRole = $.ligerDialog.open({
				title : '选择角色',
				width : 800,
				height : 400,
				target : $("#dialogUserSelectRole")
			});
			user.gridUserSelectRole = $("#dialogUserSelectRole #gridUserSelectRole55c80c4d1d19b6424f084015").ligerGrid({
				columns : [ {
					display : '名称',
					name : 'name',
					align : 'left',
					width : 200
				}, {
					display : '说明',
					name : 'remark',
					align : 'left',
					width : 150
				} ],
				root : 'rows',
				record : 'total',
				userPager : false,
				url : '../role/list',
				width : 'auto',
				height : 300,
				rownumbers : true,
				checkbox : true
			});

			$('#dialogUserSelectRole #btnRefreshRole').click(function() {
				user.gridUserSelectRole.reload();
			});

			$('#dialogUserSelectRole #btnSelectRole').click(function() {
				var rows = user.gridUserSelectRole.getSelectedRows();
				if (!rows || rows.length <= 0) {
					$.ligerDialog.error('请选择数据行！');
					return;
				}
				user.dialogUserSelectRole.hide();
				var list = [];
				for (var x = 0; x < rows.length; x++) {
					list.push(rows[x].id);
				}
				$.post('../user/addRoles', {
					userId : user.addRoleParms.userId,
					roles : JSON.stringify(list)
				}, function(data) {
					var result = common.checkData(data, "", "添加角色成功！");
					if (result === true) {
						if (user.addRoleParms.userId in user.gridRoles)
							user.gridRoles[user.addRoleParms.userId].reload();
					}
				});
			});
		} else
			user.dialogUserSelectRole.show();
	},
	removeRole : function(userId, roleId) {
		user.removeRoleParms = user.removeRoleParms || {};
		user.removeRoleParms.userId = userId;

		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../user/removeRole', {
				userId : userId,
				roleId : roleId
			}, function(data) {
				var result = common.checkData(data, "", "删除角色成功！");
				if (result === true) {
					if (user.removeRoleParms.userId in user.gridRoles)
						user.gridRoles[user.removeRoleParms.userId].reload();
				}
			});
		});
	},
	query : function() {
		var columns = [ {
			display : '名称',
			name : 'name',
			align : 'left',
			width : 150,
			id : 'pname'
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
				if (row.enable === true)
					return '启用';
				else if (row.enable === false)
					return '禁用';
				return '';
			}
		}, {
			display : '电话',
			name : 'phone',
			align : 'left',
			width : 150
		}, {
			display : '注册时间',
			name : 'createTime',
			type : 'date',
			width : 100
		}, {
			display : '说明',
			name : 'remark',
			align : 'left',
			width : 100
		} ];
		if ($('#addMonitors_auth').val() == 'true') {
			columns.push({
				display : '监控对象',
				isAllowHide : false,
				width : 80,
				render : function(row) {
					var html = '<a href="#" onclick="user.addMonitor(\'' + row.id + '\')">添加监控对象</a>';
					return html;
				}
			});
		}
		if ($('#addRoles_auth').val() == 'true') {
			columns.push({
				display : '角色',
				isAllowHide : false,
				width : 80,
				render : function(row) {
					var html = '<a href="#" onclick="user.addRole(\'' + row.id + '\')">添加角色</a>';
					return html;
				}
			});
		}

		if (!user.gridUser)
			user.gridUser = $("#userFrame #gridUser").ligerGrid({
				columns : columns,
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../user/query',
				width : 'auto',
				height : '100%',
				pageSize : 30,
				parms : user.gridUserParams,
				detail : {
					onShowDetail : user.showDetail,
					height : 'auto'
				}
			});
		else {
			user.gridUser.changePage('first');
			user.gridUser.reload();
		}
	},
	create : function() {
		var url = '../user/create.form';
		var op = {
			url : url,
			width : 850,
			height : 350,
			isHidden : false,
			title : '创建用户',
			onLoaded : function() {
				user.dialog.frame.window.validateCreate();
				user.dialog.frame.window.resetLigerui();
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
							user.gridUser.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		user.dialog = $.ligerDialog.open(op);
	},
	edit : function(id) {
		var url = '../user/edit.form';
		var op = {
			url : url,
			urlParms : {
				id : id
			},
			width : 850,
			height : 350,
			isHidden : false,
			title : '修改用户',
			onLoaded : function() {
				user.dialog.frame.window.validateEdit();
				user.dialog.frame.window.resetLigerui();
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
							user.gridUser.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		user.dialog = $.ligerDialog.open(op);
	},
	remove : function(id) {
		$.ligerDialog.confirm('将删除用户所有相关资料，且不可恢复，真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../user/delete', {
				id : id
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					user.gridUser.reload();
			});
		});
	},
	move : function(id, groupId) {
		$.post('../user/move', {
			id : id,
			groupId : groupId
		}, function(data) {
			common.checkData(data, true, '移动用户成功！');
		});
	}
}
$(function() {
	user.query();

	$('#userFrame #btnCreateUser').click(function() {
		user.create();
	});
	$('#userFrame #btnEditUser').click(function() {
		if (!user.gridUser)
			return;

		var row = user.gridUser.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		user.edit(row.id);
	});
	$('#userFrame #btnRemoveUser').click(function() {
		if (!user.gridUser)
			return;

		var row = user.gridUser.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		user.remove(row.id);
	});
	$('#userFrame #btnQueryUser').click(function() {
		if (!user.gridUser)
			return;
		user.gridUserParams.filter = $('#userFrame #txtUserFilter').val();
		user.query();
	});
});