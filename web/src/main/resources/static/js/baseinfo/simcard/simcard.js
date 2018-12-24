/**
 * sim卡管理
 */
window.simcard = {
	gridSimcard : null,
	gridSimcardParams : {
		filter : ''
	},
	query : function() {
		if (!simcard.gridSimcard)
			simcard.gridSimcard = $("#simcardFrame #gridSimcard").ligerGrid({
				columns : [ {
					display : '电话号码',
					name : 'phoneNumber',
					align : 'left',
					frozen : true,
					width : 150
				}, {
					display : '卡号',
					name : 'simcardNumber',
					align : 'left',
					width : 150
				}, {
					display : '语音类型',
					name : 'speechType',
					align : 'left'
				}, {
					display : '运营商',
					name : 'carrierOperator',
					align : 'left'
				}, {
					display : '购买日期',
					name : 'purchaseDate',
					type : 'date',
					align : 'left'
				}, {
					display : '预交费',
					name : 'prepayment',
					align : 'left'
				}, {
					display : '到期日期',
					name : 'expireDate',
					type : 'date'
				}, {
					display : '开通短信否',
					name : 'openSMS',
					width : 80,
					render : function(row) {
						if (row.openSMS === true)
							return '已开通';
						else if (row.openSMS === false)
							return '未开通';
						return '';
					}
				}, {
					display : '创建时间',
					name : 'createTime',
					type : 'date'
				}, {
					display : '说明',
					name : 'remark',
					align : 'left'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../simcard/query',
				width : 'auto',
				height : '100%',
				pageSize : 30,
				rownumbers : true,
				parms : simcard.gridSimcardParams
			});
		else {
			simcard.gridSimcard.changePage('first');
			simcard.gridSimcard.reload();
		}
	},
	create : function() {
		var url = '../simcard/create.form';
		var op = {
			url : url,
			width : 800,
			height : 420,
			isHidden : false,
			title : '创建sim卡',
			onLoaded : function() {
				simcard.dialog.frame.window.validateCreate();
				simcard.dialog.frame.window.resetLigerui();
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
							simcard.gridSimcard.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		simcard.dialog = $.ligerDialog.open(op);
	},
	edit : function(id) {
		var url = '../simcard/edit.form';
		var op = {
			url : url,
			urlParms : {
				id : id
			},
			width : 800,
			height : 420,
			isHidden : false,
			title : '修改sim卡',
			onLoaded : function() {
				simcard.dialog.frame.window.validateEdit();
				simcard.dialog.frame.window.resetLigerui();
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
							simcard.gridSimcard.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		simcard.dialog = $.ligerDialog.open(op);
	},
	remove : function(id) {
		$.ligerDialog.confirm('将删除sim卡所有相关资料，且不可恢复，真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../simcard/delete', {
				id : id
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					simcard.gridSimcard.reload();
			});
		});
	}
};
$(function() {
	simcard.query();

	$('#simcardFrame #btnQuerySimcard').click(function() {
		if (!simcard.gridSimcard)
			return;
		simcard.gridSimcardParams.filter = $('#simcardFrame #txtSimcardFilter').val();
		simcard.query();
	});

	$('#simcardFrame #btnCreateSimcard').click(function() {
		simcard.create();
	});
	$('#simcardFrame #btnEditSimcard').click(function() {
		if (!simcard.gridSimcard)
			return;

		var row = simcard.gridSimcard.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		simcard.edit(row.id);
	});
	$('#simcardFrame #btnRemoveSimcard').click(function() {
		if (!simcard.gridSimcard)
			return;

		var row = simcard.gridSimcard.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		simcard.remove(row.id);
	});
});