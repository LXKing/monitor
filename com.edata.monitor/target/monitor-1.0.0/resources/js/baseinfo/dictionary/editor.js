/**
 * 修改字典项
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                remote: {
                    url: '../dictionary/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        kind: function () {
                            return $('#enums').val();
                        },
                        id: null,
                        checkId: false
                    }
                }
            }
        },
        messages: {
            name: {
                remote: '该字典数据项已存在'
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
                    url: '../dictionary/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        kind: function () {
                            return $('#enums').val();
                        },
                        id: function () {
                            return $('#id').val();
                        },
                        checkId: true
                    }
                }
            }
        },
        messages: {
            name: {
                remote: '该数据字典项已存在'
            }
        }
    });

    var result = v.checkForm();
    v.showErrors();
    return result;
}