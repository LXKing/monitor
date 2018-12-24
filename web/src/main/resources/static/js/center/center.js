/**
 * 监控中心
 */
window.center = {
	
	centerPages : null,
	devices : null,
	layout : function() {
		
		$('#btnLocate').addClass('select');
		$("#layout").ligerLayout({
			space : 0,
			topHeight : 80,
			allowTopResize : false
		});

		center.centerPages = $("#centerPages").ligerTab({
			onAfterRemoveTabItem : function(targettabid) {
				if (targettabid == 'pageCenterReplay') {
					delete window.replay;
				}
			}
		});
	},
	locate : function() {
		
		var height = $(".l-layout-center").height();
		center.centerPages.addTabItem({
			tabid : 'pageCenterLocate',
			url : 'locate/locate.iframe',
			text : '实时监控',
			showClose : false,
			height : height - 27
		});
	},
	replay : function(deviceNumber, plateNumber) {
		
		var height = $(".l-layout-center").height();
		var pageId = 'replay' + deviceNumber;
		if (center.centerPages.isTabItemExist(pageId)) {
			center.centerPages.selectTabItem(pageId);
		} else {
			center.centerPages.addTabItem({
				tabid : pageId,
				url : 'replay/replay.iframe?deviceNumber=' + deviceNumber + '&plateNumber=' + plateNumber,
				text : '轨迹回放:' + plateNumber,
				// showClose : false,
				height : height - 27
			});
			// replay.reset(deviceNumber, plateNumber);
		}
	},
	queryAlarm : function(deviceNumber, plateNumber) {
		
		var height = $(".l-layout-center").height();
		var pageId = 'queryAlarm' + deviceNumber;
		if (center.centerPages.isTabItemExist(pageId)) {
			center.centerPages.selectTabItem(pageId);
		} else {
			center.centerPages.addTabItem({
				tabid : pageId,
				url : 'alarm/alarm.iframe?deviceNumber=' + deviceNumber + '&plateNumber=' + plateNumber,
				text : '报警查询:' + plateNumber,
				// showClose : false,
				height : height - 27
			});
		}
	},
	queryData : function(deviceNumber, plateNumber) {
		
		var height = $(".l-layout-center").height();
		var pageId = 'queryData' + deviceNumber;
		if (center.centerPages.isTabItemExist(pageId)) {
			center.centerPages.selectTabItem(pageId);
		} else {
			center.centerPages.addTabItem({
				tabid : pageId,
				url : 'deviceData/deviceData.iframe?deviceNumber=' + deviceNumber + '&plateNumber=' + plateNumber,
				text : '数据查询:' + plateNumber,
				// showClose : false,
				height : height - 27
			});
		}
	}
}

$(function() {
	center.layout();
	center.locate();
});