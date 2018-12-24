/**
 * 修改车辆
 */
function validateCreate() {
	var v = $('form').validate({
		rules : {
			plateNumber : {
				remote : {
					url : '../vehicle/exist',
					type : "post",
					dataType : "json",
					data : {
						id : null,
						checkId : false
					}
				}
			},
		},
		messages : {
			plateNumber : {
				remote : '该车辆已存在'
			}
		}
	});

	var result = v.checkForm();
	v.showErrors();
	return result;
}
function validateEdit() {
	var v = $('form').validate({
		rules : {
			plateNumber : {
				remote : {
					url : '../vehicle/exist',
					type : "post",
					dataType : "json",
					data : {
						id : function() {
							return $('#vehicleEditor #id').val();
						},
						checkId : true
					}
				}
			}
		},
		messages : {
			plateNumber : {
				remote : '该车辆已存在'
			}
		}
	});

	var result = v.checkForm();
	v.showErrors();
	return result;
}
function resetLigerui() {
	$('#vehicleEditor #installDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#vehicleEditor #installDate").val().replace(' 00:00:00', '')
	});

	$('#vehicleEditor #annualSurveyDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#vehicleEditor #annualSurveyDate").val().replace(' 00:00:00', '')
	});
	function getGridDictionary() {
		var options = {
			columns : [ {
				display : '名称',
				name : 'name',
				align : 'left',
				width : 500,
				id : 'pname'
			}, {
				display : '编码',
				name : 'code',
				align : 'left'
			} ],
			tree : {
				columnId : 'pname',
				idField : 'id',
				parentIDField : 'pid'
			},
			root : 'rows',
			usePager : false,
			rownumbers : false,
			url : '../dictionary/list',
			width : 'auto',
			height : '100%'
		};
		return options;
	}
	$("#vehicleEditor #deviceNumber").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 420,
		selectBoxHeight : 200,
		valueField : 'id',
		textField : 'deviceNumber',
		valueFieldID : 'deviceId',
		initValue : $('#vehicleEditor #deviceId').val(),
		initText : $('#vehicleEditor #deviceNumber').val(),
		condition : {
			fields : [ {
				name : 'deviceNumber',
				label : '设备号'
			} ]
		},
		conditionSearchClick : function(data) {
			var rules = data.rules;
			var value = '';
			if (rules && rules.length > 0) {
				value = rules[0]['value'];
			}
			data.grid.options.parms = {
				deviceNumber : value
			};
			data.grid.reload();
		},
		grid : {
			columns : [ {
				display : '设备号',
				name : 'deviceNumber',
				frozen : true,
				align : 'left',
				width : 150
			}, {
				display : '电话号码',
				name : 'phoneNumber',
				align : 'left',
				width : 150
			}, {
				display : '出厂号',
				name : 'factoryNumber',
				align : 'left',
				width : 150
			}, {
				display : '型号',
				name : 'model',
				align : 'left',
				width : 200
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
				name : 'enable',
				width : 80,
				render : function(row) {
					if (row.enable === true)
						return '启用';
					else if (row.enable === false)
						return '禁用';
					return '';
				}
			}, {
				display : '保修期',
				name : 'warranty',
				type : 'date',
				width : 100
			}, {
				display : '说明',
				name : 'remark',
				align : 'left',
				width : 200
			} ],
			root : 'rows',
			record : 'total',
			pageParmName : 'pageIndex',
			pagesizeParmName : 'pageSize',
			url : '../device/free',
			width : 'auto',
			height : '100%',
			pageSize : 30,
			rowDraggable : true,
			rownumbers : true
		}
	});

	$("#vehicleEditor #adminArea").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 500,
		selectBoxHeight : 200,
		valueField : 'name',
		textField : 'name',
		initValue : $('#vehicleEditor #adminArea').val(),
		initText : $('#vehicleEditor #adminArea').val(),
		grid : getGridDictionary(),
		parms : {
			kind : 5,
			grid : true
		}
	});

	$("#vehicleEditor #plateColor").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#vehicleEditor #plateColor').val(),
		initText : $('#vehicleEditor #plateColor').val(),
		url : '../dictionary/list',
		parms : {
			kind : 1,
			grid : false
		}
	});
	$("#vehicleEditor #vehicleColor").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#vehicleEditor #vehicleColor').val(),
		initText : $('#vehicleEditor #vehicleColor').val(),
		url : '../dictionary/list',
		parms : {
			kind : 2,
			grid : false
		}
	});
	$("#vehicleEditor #vehicleType").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 500,
		selectBoxHeight : 200,
		valueField : 'name',
		textField : 'name',
		initValue : $('#vehicleEditor #vehicleType').val(),
		initText : $('#vehicleEditor #vehicleType').val(),
		grid : getGridDictionary(),
		parms : {
			kind : 3,
			grid : true
		}
	});
	$("#vehicleEditor #carryType").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#vehicleEditor #carryType').val(),
		initText : $('#vehicleEditor #carryType').val(),
		url : '../dictionary/list',
		parms : {
			kind : 4,
			grid : false
		}
	});
	$("#vehicleEditor #vehicleVoltage").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#vehicleEditor #vehicleVoltage').val(),
		initText : $('#vehicleEditor #vehicleVoltage').val(),
		url : '../dictionary/list',
		parms : {
			kind : 6,
			grid : false
		}
	});
	$("#vehicleEditor #motorcade").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'id',
		textField : 'name',
		valueFieldID : 'motorcadeId',
		initValue : $('#vehicleEditor #motorcadeId').val(),
		initText : $('#vehicleEditor #motorcade').val(),
		url : '../motorcade/list',
		parms : {
			grid : false
		}
	});
}