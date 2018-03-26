/**
 * 修改用户
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            account: {
                required: true,
                rangelength: [1, 20],
                remote: {
                    url: '../user/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: null,
                        checkId: false
                    }
                }
            },
            accountName: {
                required: true,
                rangelength: [1, 20]
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
                required: true,
                remote: {
                    url: '../user/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $('#userEditor #id').val();
                        },
                        checkId: true
                    }
                }
            },
            accountName: {
                required: true,
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
    $('#userEditor #serviceStartDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#userEditor #serviceStartDate").val().replace(' 00:00:00', '')
    });

    $('#userEditor #serviceEndDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#userEditor #serviceEndDate").val().replace(' 00:00:00', '')
    });
}