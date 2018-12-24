/**
 * 修改sim卡
 */
function validateCreate() {
	var v = $('form').validate({
		rules : {
			phoneNumber : {
				remote : {
					url : '../simcard/exist',
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
			phoneNumber : {
				remote : '该sim卡已存在'
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
			phoneNumber : {
				remote : {
					url : '../simcard/exist',
					type : "post",
					dataType : "json",
					data : {
						id : function() {
							return $("#simcardEditor #id").val();
						},
						checkId : true
					}
				}
			}
		},
		messages : {
			phoneNumber : {
				remote : '该sim卡已存在'
			}
		}
	});

	var result = v.checkForm();
	v.showErrors();
	return result;
}
function resetLigerui() {
	$('#simcardEditor #openDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#simcardEditor #openDate").val().replace(' 00:00:00', '')
	});

	$('#simcardEditor #purchaseDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#simcardEditor #purchaseDate").val().replace(' 00:00:00', '')
	});

	$('#simcardEditor #expireDate').ligerDateEditor({
		format : "yyyy-MM-dd",
		cancelable : true,
		initValue : $("#simcardEditor #expireDate").val().replace(' 00:00:00', '')
	});

	$("#simcardEditor #speechType").ligerComboBox({
		width : 100,
		slide : false,
		selectBoxWidth : 100,
		selectBoxHeight : 240,
		valueField : 'name',
		textField : 'name',
		initValue : $('#simcardEditor  #speechType').val(),
		initText : $('#simcardEditor  #speechType').val(),
		url : '../dictionary/list',
		parms : {
			kind : 7,
			grid : false
		}
	});
}