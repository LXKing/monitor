/**
 * 设备数据查询
 */
window.deviceData = {
	reset : function(deviceNumber, plateNumber) {
		deviceData.deviceNumber = deviceNumber;
		deviceData.plateNumber = plateNumber;
	},
	layout : function() {
		var height = $(document).height();
		$('#deviceDataLayout').ligerLayout({
			leftWidth : 240,
			allowLeftResize : false,
			allowLeftCollapse : false,
			height : height
		});

		deviceData.deviceDataPages = $('#deviceDataPages').ligerTab({
			height : height,
			changeHeightOnResize : true,
			showSwitch : true,
			ShowSwitchInTab : true
		});

		$('#divDeviceDataControl > ul').ligerTree({
			checkbox : false,
			url : '../deviceData/menus',
			idFieldName : 'id',
			parentIDFieldName : 'pid',
			ajaxType : 'get',
			needCancel : false,
			onSelect : function(node) {
				if (node.data.leaf === false)
					return;
				var height = $(document).height();
				deviceData.deviceDataPages.addTabItem({
					tabid : node.data.id,
					url : node.data.url,
					text : node.data.text,
					showClose : true,
					height : height - 30
				});
			}
		});
	}
}
$(function() {
	deviceData.layout();
	var deviceNumber = $('#txtDeviceNumber').val();
	var plateNumber = $('#txtPlateNumber').val();
	deviceData.reset(deviceNumber, plateNumber);
	$.get('../common/device/vehicle', {
		number : deviceNumber
	}, function(vehicle) {
		deviceData.vehicle = vehicle;
	})
});