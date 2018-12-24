/**
 * 行驶记录仪
 */
$(function() {
	$.post('../deviceData/drivingRecorder', {
		deviceNumber : parent.deviceData.deviceNumber
	}, function(o) {
		$('#number').text(o.number || parent.deviceData.deviceNumber);
		$('#plateNumber').text(o.plateNumber || '未采集');
		$('#license').text(o.license || '未采集');
		$('#plateType').text(o.plateType || '未采集');
		$('#vehicleIdCode').text(o.vehicleIdCode || '未采集');
		$('#cccCode').text(o.cccCode || '未采集');
		$('#initialMileage').text(o.initialMileage || 0 + 'km');
		$('#totalMileage').text(o.totalMileage || 0 + 'km');
		$('#revision').text(o.revision || '未采集');
		$('#pulseFactor').text(o.pulseFactor);
		$('#model').text(o.model || '未采集');
		$('#serialNumber').text(o.serialNumber);
		$('#productionDate').text(o.productionDate || '未采集');
		$('#d0').text(o.d0 || '未采集');
		$('#d1').text(o.d1 || '未采集');
		$('#d2').text(o.d2 || '未采集');
		$('#d3').text(o.d3 || '未采集');
		$('#d4').text(o.d4 || '未采集');
		$('#d5').text(o.d5 || '未采集');
		$('#d6').text(o.d6 || '未采集');
		$('#d7').text(o.d7 || '未采集');
	});
});