/**
 * 兴趣点
 */
function validateCreate() {
	var v = $('form').validate({
		rules : {
			name : {
				required : true,
				rangelength : [ 1, 50 ],
				remote : {
					url : '../poi/exist',
					type : "post",
					dataType : "json",
					data : {
						id : null,
						checkId : false
					}
				}
			}
		},
		messages : {
			name : {
				remote : '该名称已存在'
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
			name : {
				required : true,
				rangelength : [ 1, 50 ],
				remote : {
					url : '../poi/exist',
					type : "post",
					dataType : "json",
					data : {
						id : function() {
							return $("#poiEditor #id").val();
						},
						checkId : true
					}
				}
			}
		},
		messages : {
			name : {
				remote : '该名称已存在'
			}
		}
	});

	var result = v.checkForm();
	v.showErrors();
	return result;
}
function resetFlag() {
	var value = flagsCheckBoxList.getValue();
	value = common.bitsToNumber(value);
	$('#circleAreaEditor #flag').val(value);
}
function resetLigerui(marker) {
	if (marker) {
		var p = marker.getPosition();
		p = common.converter.bd09_gcj02_wgs84(p.lat, p.lng);
		$("#poiEditor #lat").val(p.lat);
		$("#poiEditor #lng").val(p.lng);
	}
	$("#poiEditor #type").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#poiEditor #type').val(),
		initText : $('#poiEditor #type').val(),
		url : '../dictionary/list',
		parms : {
			kind : 12,
			grid : false
		}
	});
}