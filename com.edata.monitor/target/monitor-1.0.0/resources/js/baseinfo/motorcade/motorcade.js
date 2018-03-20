/**
 * 车队管理
 */
window.motorcade = {
	gridMotorcade : null,
	list : function() {
		motorcade.gridMotorcade = $("#motorcadeFrame #gridMotorcade").ligerGrid({
			columns : [ {
				display : '名称',
				name : 'name',
				align : 'left',
				width : 300
			}, {
				display : '类型',
				name : 'type',
				align : 'left',
				width : 150
			}, {
				display : '上级可见否',
				name : 'parentVisible',
				width : 80,
				render : function(row) {
					if (row.parentVisible === true)
						return '可见';
					else if (row.parentVisible === false)
						return '不可见';
					return '';
				}
			}, {
				display : '说明',
				name : 'remark',
				align : 'left',
				width : 'auto'
			} ],
			root : 'rows',
			record : 'total',
			usePager : false,
			heightDiff : 30,
			url : '../motorcade/list',
			width : 'auto',
			height : '100%',
			rownumbers : true,
			parms : {
				grid : true
			}
		});
	},
	create : function() {
		var url = '../motorcade/create.form';
		var op = {
			url : url,
			width : 550,
			height : 300,
			isHidden : false,
			title : '创建车队',
			onLoaded : function() {
				motorcade.dialog.frame.window.validateCreate();
				motorcade.dialog.frame.window.resetLigerui();
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
							motorcade.gridMotorcade.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		motorcade.dialog = $.ligerDialog.open(op);
	},
	edit : function(id) {
		var url = '../motorcade/edit.form';
		var op = {
			url : url,
			urlParms : {
				id : id
			},
			width : 550,
			height : 300,
			isHidden : false,
			title : '修改车队',
			onLoaded : function() {
				motorcade.dialog.frame.window.validateEdit();
				motorcade.dialog.frame.window.resetLigerui();
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
							motorcade.gridMotorcade.reload();
					});
				}
			}, {
				text : '取消',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ]
		};
		motorcade.dialog = $.ligerDialog.open(op);
	},
	remove : function(id) {
		$.ligerDialog.confirm('将删除车队所有相关资料，且不可恢复，真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../motorcade/delete', {
				id : id
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					motorcade.gridMotorcade.reload();
			});
		});
	}
};
$(function() {
	motorcade.list();

	$('#motorcadeFrame #btnRefreshMotorcade').click(function() {
		motorcade.list();
	});

	$('#motorcadeFrame #btnCreateMotorcade').click(function() {
		motorcade.create();
	});
	$('#motorcadeFrame #btnEditMotorcade').click(function() {
		if (!motorcade.gridMotorcade)
			return;

		var row = motorcade.gridMotorcade.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		motorcade.edit(row.id);
	});
	$('#motorcadeFrame #btnRemoveMotorcade').click(function() {
		if (!motorcade.gridMotorcade)
			return;

		var row = motorcade.gridMotorcade.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		motorcade.remove(row.id);
	});
});