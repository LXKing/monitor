/**
 * 地图图层设置
 */
window.setting = {
	isChanged : false,
	gridMapLayer : null,
	gridMapLayerParms : {
		filter : ''
	},
	query : function() {
		var columns = [ {
			display : '名称',
			name : 'name',
			align : 'left',
			width : 200
		}, {
			display : '可见否',
			name : 'visible',
			width : 80,
			render : function(row) {
				if (row.visible === true)
					return '是';
				else if (row.visible === false)
					return '否';
				return '';
			}
		}, {
			display : '说明',
			name : 'remark',
			align : 'left'
		} ];
		if (!setting.gridMapLayer)
			setting.gridMapLayer = $("#gridMapLayer").ligerGrid({
				columns : columns,
				root : 'rows',
				record : 'total',
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				url : '../mapLayer/query',
				width : '100%',
				height : '100%',
				pageSize : 30,
				parms : setting.gridMapLayerParms
			});
		else {
			setting.gridMapLayer.reload();
		}
	},
	visible : function(row, visible) {
		$.post('../mapLayer/visible', {
			mapLayerId : row.id,
			visible : visible
		}, function(r) {
			var result = common.checkData(r, "", "地图图层设置成功！");
			if (result === true) {
				setting.isChanged = true;
				row.visible = visible;
				setting.gridMapLayer.reRender({
					rowData : row
				});
			}
		});
	}
};
$(function() {
	$('#btnQueryMapLayer').click(function() {
		setting.gridMapLayerParams.filter = $('#txtMapLayerFilter').val();
		setting.query();
	});
	$('#btnShowMapLayer').click(function() {
		if (!setting.gridMapLayer)
			return;

		var row = setting.gridMapLayer.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		setting.visible(row, true);
	});
	$('#btnHideMapLayer').click(function() {
		if (!setting.gridMapLayer)
			return;

		var row = setting.gridMapLayer.getSelected();
		if (!row) {
			$.ligerDialog.error('请选择数据行！');
			return;
		}
		setting.visible(row, false);
	});
});