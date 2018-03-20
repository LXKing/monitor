/**
 * 地图设置
 */
window.mapOption = {
	map : null,
	onMapLoaded : function(map) {
		mapOption.map = map;
		mapOption.reset();
	},
	reset : function() {
		$.get('../mapOption/query', function(r) {
			if (r.code && r.code != 0) {
				common.tip('error', r.error, 3000);
				return;
			}
			if (!r.lng)
				return;
			mapOption.map.convertor(r.lng, r.lat, function(center) {
				mapOption.map.setCenter(center);
				mapOption.map.setZoom(r.zoom);
			});
		});
	},
	save : function() {
		var center = mapOption.map.getCenter();
		center = common.converter.bd09_gcj02_wgs84(center.lat, center.lng);
		var zoom = mapOption.map.getZoom();
		$.post('../mapOption/save', {
			lat : center.lat,
			lng : center.lng,
			zoom : zoom
		}, function(r) {
			if (r.code == 0)
				common.tip('success', '地图设置保存成功！', 3000);
			else
				common.tip('error', r.error, 3000);
		});
	},
	resize : function() {
		var height = $(document).height();
		$('#divAllMap').height(height - 32);
	}
};
$(function() {
	mapOption.resize();

	webMap.events.onMapLoadCompleted['optionMap'] = mapOption.onMapLoaded;
	webMap.createMap('optionMap');

	$('#btnReloadMapOtions').click(function() {
		mapOption.reset();
	});

	$('#btnSaveMapOtions').click(function() {
		mapOption.save();
	});
});