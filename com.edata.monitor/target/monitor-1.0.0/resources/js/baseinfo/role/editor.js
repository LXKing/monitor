/**
 * 修改角色
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                remote: {
                    url: '../role/exist',
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
            name: {
                remote: '该角色已存在'
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
            name: {
                remote: {
                    url: '../role/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#roleEditor #id").val();
                        },
                        checkId: true
                    }
                }
            }
        },
        messages: {
            name: {
                remote: '该角色已存在'
            }
        }
    });

    var result = v.checkForm();
    v.showErrors();
    return result;
}