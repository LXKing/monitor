/**
 * 故障查询
 */
window.fault = {
	startDate : null,
	endDate : null,
	device : null,
	gridFaults : null,
	gridFaultsParams : {
		number : '',
		start : new Date().addDays(-1).toDateTimeString('yyyy-MM-dd hh:mm:ss'),
		end : new Date().toDateTimeString('yyyy-MM-dd hh:mm:ss')
	},
	gridDevices : null,
	gridDevicesParams : {
		filter : ''
	},
	dialogSearchDevices : null,
	chart : null,
	analyze : function(data) {
		var labels = [];
		var times = {};
		for (var i = 0; i < data.total; i++) {
			var item = data.rows[i];

			var time = item.time.toDate();

			var name = time.toDateTimeString('dd');
			labels.push(name);

			if (times.hasOwnProperty(name))
				times[name] = times[name] + 1;
			else
				times[name] = 1;
		}

		var list = [];
		for ( var key in times) {
			list.push(times[key]);
		}

		$('#chartContainer').highcharts({
			chart : {
				type : 'column',
				zoomType : 'x'
			},
			title : {
				text : '故障分析'
			},

			xAxis : {
				categories : labels
			},
			yAxis : [ {
				min : 0,
				title : {
					style : {
						color : '#AA4643'
					},
					text : '次数'
				},
				labels : {
					style : {
						color : '#AA4643'
					}
				}
			} ],
			tooltip : {
				shared : true
			},
			credits : {
				enabled : false
			},
			series : [ {
				name : '故障数',
				data : list
			} ]
		});

	}
};
$(function() {
	$("#layout").ligerLayout({
		topHeight : 80,
		rightWidth : 300,
		allowTopResize : false
	});
	$('#btnFault').addClass('select');
	var number = $('#txtDeviceNumber').text();
	if (number.length > 0) {
		$.post('./common/device/info', {
			number : number
		}, function(data) {
			fault.device = data;
			fault.gridFaultsParams.number = data.number;

			common.setCookie('device_number', data.number);
			common.setCookie('device_name', data.name);

			$('#txtDeviceNumber').text(data.number);
			$('#txtDeviceName').text(data.name);
			if (fault.gridFaults)
				fault.gridFaults.reload();
		});
	} else {
		var number = common.getCookie('device_number');
		var name = common.getCookie('device_name');
		if (number && name) {
			fault.device = {
				number : number,
				name : name
			};
			fault.gridFaultsParams.number = number;
			$('#txtDeviceNumber').text(number);
			$('#txtDeviceName').text(name);
		}
	}

	var startTime = common.getCookie('time_start');
	startTime = startTime == null ? '' : startTime;

	startTime = startTime.length > 0 ? startTime.toDate().toDateTimeString(
			'yyyy-MM-dd hh:mm') : new Date().addDays(-1).toDateTimeString(
			'yyyy-MM-dd hh:mm');
	fault.gridFaultsParams.start = startTime + ':00';

	var endTime = common.getCookie('time_end');
	endTime = endTime == null ? '' : endTime;

	endTime = endTime.length > 0 ? endTime.toDate().toDateTimeString(
			'yyyy-MM-dd hh:mm') : new Date()
			.toDateTimeString('yyyy-MM-dd hh:mm');
	fault.gridFaultsParams.end = endTime + ':00';

	fault.startDate = $("#txtStartDate").ligerDateEditor(
			{
				showTime : true,
				format : 'yyyy-MM-dd hh:mm',
				label : '开始时间',
				labelWidth : 60,
				labelAlign : 'left',
				initValue : new Date().getShortDate().toDateTimeString(
						'yyyy-MM-dd hh:mm'),
				onChangeDate : function(value) {
					var time = (value + ':00').toDate().toDateTimeString(
							'yyyy-MM-dd hh:mm:ss');
					common.setCookie('time_start', time);
					fault.gridFaultsParams.start = time;
				}
			});
	fault.endDate = $("#txtEndDate").ligerDateEditor(
			{
				showTime : true,
				format : 'yyyy-MM-dd hh:mm',
				label : '结束时间',
				labelWidth : 60,
				labelAlign : 'left',
				initValue : new Date().toDateTimeString('yyyy-MM-dd hh:mm'),
				onChangeDate : function(value) {
					var time = (value + ':00').toDate().toDateTimeString(
							'yyyy-MM-dd hh:mm:ss');
					common.setCookie('time_end', time);
					fault.gridFaultsParams.end = time;
				}
			});

	$('#btnQuery').click(function() {
		if (!fault.device) {
			$.ligerDialog.warn('未发现设备信息！');
			return;
		}
		if (fault.gridFaults)
			fault.gridFaults.reload();
	});

	$('#btnSelectDevice').click(function() {
		if (!fault.gridDevices)
			return;
		var row = fault.gridDevices.getSelected();
		if (row) {
			fault.device = {
				number : row.number,
				name : row.name
			};
			fault.gridFaultsParams.number = row.number;

			common.setCookie('device_number', row.number);
			common.setCookie('device_name', row.name);

			$('#txtDeviceNumber').text(row.number);
			$('#txtDeviceName').text(row.name);
			if (fault.dialogSearchDevices)
				fault.dialogSearchDevices.hide();
		}

	});

	$('#btnSearchDevices').click(function() {
		fault.gridDevicesParams.filter = $('#txtDeviceFilter').val();

		var op = {
			target : $('#dialogSearchDevices'),
			width : 800,
			height : 410,
			title : '选择设备',
		};
		fault.dialogSearchDevices = $.ligerDialog.open(op);

		if (!fault.gridDevices) {
			fault.gridDevices = $("#gridDevices").ligerGrid({
				columns : [ {
					display : '设备名',
					name : 'name'
				}, {
					display : '设备号',
					name : 'number'
				}, {
					display : 'SIM卡',
					name : 'sim'
				}, {
					display : '服务开始日期',
					name : 'serviceStartTime',
					type : 'date'
				}, {
					display : '服务结束日期',
					name : 'serviceEndTime',
					type : 'date'
				}, {
					display : '入网时间',
					name : 'createTime',
					type : 'date'
				} ],
				pageSize : 30,
				pageParmName : 'pageIndex',
				pagesizeParmName : 'pageSize',
				root : 'rows',
				record : 'total',
				url : './common/device/search',
				parms : fault.gridDevicesParams,
				width : '99%',
				height : 300
			});
		} else {
			fault.gridDevices.reload();
		}
	});
	fault.gridFaults = $("#gridFaults").ligerGrid({
		columns : [ {
			display : '时间',
			name : 'time',
			width : 130,
			frozen : true
		}, {
			display : '等级',
			name : 'level',
			width : 80
		}, {
			display : '系统分类',
			name : 'systemId',
			width : 150
		}, {
			display : '适用品牌',
			name : 'brand',
			width : 180
		}, {
			display : '传感器范畴',
			name : 'sensors',
			width : 180
		}, {
			display : '英文故障描述',
			name : 'descriptionE',
			width : 250
		}, {
			display : '中文故障描述',
			name : 'descriptionC',
			width : 250
		}, {
			display : '解决方案',
			name : 'solution',
			width : 250
		} ],
		usePager : false,
		fixedCellHeight : false,
		root : 'rows',
		record : 'total',
		url : './common/fault/query',
		parms : fault.gridFaultsParams,
		height : '100%',
		onSuccess : fault.analyze
	});
});