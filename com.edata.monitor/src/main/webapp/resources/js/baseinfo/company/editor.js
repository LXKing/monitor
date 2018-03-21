/**
 * 修改企业
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            account: {
                remote: {
                    url: '../user/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: null,
                        checkId: false
                    }
                }
            }
        },
        messages: {
            account: {
                remote: '该用户已存在'
            }
        }
    });

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function validateEdit() {
    var v = $('form').validate({
        rules: {
            account: {
                remote: {
                    url: '../user/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#id").val();
                        },
                        checkId: true
                    }
                }
            }
        },
        messages: {
            account: {
                remote: '该用户已存在'
            }
        }
    });

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function resetLigerui() {
    $('#serviceStartDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#serviceStartDate").val().replace(' 00:00:00', '')
    });

    $('#serviceEndDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#serviceEndDate").val().replace(' 00:00:00', '')
    });

    $('#registDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#registDate").val().replace(' 00:00:00', '')
    });
}