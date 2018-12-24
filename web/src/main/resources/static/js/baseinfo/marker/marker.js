/**
 * 车辆图标
 */
window.marker = {
	/**
	 * 刷新图标列表
	 */
	refresh : function() {
		if (!marker.gridMarker)
			marker.gridMarker = $("#gridMarker").ligerGrid({
				columns : [ {
					display : '名称',
					name : 'name',
					align : 'left',
					width : 200
				}, , {
					display : '图片',
					name : 'icon',
					width : 300,
					render : function(row) {
						var html = [];
						html.push('<div>');
						html.push('<img src="../resources/icons/' + row.name + '">');
						html.push("</div>");
						return html.join('');
					}
				}, {
					display : '上传时间',
					name : 'time'
				} ],
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../marker/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				rowHeight : 34,
				rownumbers : true
			});
		else {
			marker.gridMarker.changePage('first');
			marker.gridMarker.reload();
		}
	},
	upload : function() {
		var url = '../marker/upload.form';
		var op = {
			url : url,
			width : 450,
			height : 300,
			isHidden : false,
			title : '上传车辆图标'
		};
		marker.dialog = $.ligerDialog.open(op);
	},
	remove : function(name) {
		$.ligerDialog.confirm('真地要删除所选项吗？', function(yes, value) {
			if (!yes)
				return;
			$.post('../marker/delete', {
				name : name
			}, function(data) {
				var result = common.form.remove(data);
				if (result === true)
					marker.gridMarker.reload();
			});
		});
	}
}
$(function() {
	marker.refresh();

	$('#btnRefreshMarker').click(function() {
		marker.refresh();
	});
	$('#btnCreateMarker').click(function() {
		marker.upload();
	});
	$('#btnRemoveMarker').click(function() {
		if (!marker.gridMarker)
			return;

		var row = marker.gridMarker.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		marker.remove(row.name);
	});
})