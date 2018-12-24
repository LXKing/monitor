/**
 * 修改车主
 */
function validateCreate() {
	var v = $('form').validate({
		rules : {
			account : {
				remote : {
					url : '../user/exist',
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
			account : {
				remote : '该用户已存在'
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
			account : {
				remote : {
					url : '../user/exist',
					type : "post",
					dataType : "json",
					data : {
						id : function() {
							return $("#ownerEditor #id").val();
						},
						checkId : true
					}
				}
			}
		},
		messages : {
			account : {
				remote : '该用户已存在'
			}
		}
	});

	var result = v.checkForm();
	v.showErrors();
	return result;
}
function resetLigerui() {
	$('#ownerEditor #serviceStartDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#ownerEditor #serviceStartDate").val().replace(' 00:00:00', '')
	});

	$('#ownerEditor #serviceEndDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#ownerEditor #serviceEndDate").val().replace(' 00:00:00', '')
	});

	$("#ownerEditor #idType").ligerComboBox({
		width : 150,
		slide : false,
		selectBoxWidth : 150,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#ownerEditor #idType').val(),
		initText : $('#ownerEditor #idType').val(),
		url : '../dictionary/list',
		parms : {
			kind : 13,
			grid : false
		}
	});
}