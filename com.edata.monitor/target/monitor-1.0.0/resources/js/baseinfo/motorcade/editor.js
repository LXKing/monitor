/**
 * 修改车队
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                remote: {
                    url: '../motorcade/exist',
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
                remote: '该车队已存在'
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
                    url: '../motorcade/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#motorcadeEditor #id").val();
                        },
                        checkId: true
                    }
                }
            }
        },
        messages: {
            name: {
                remote: '该车队已存在'
            }
        }
    });

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function resetLigerui() {
    $("#motorcadeEditor #type").ligerComboBox({
        width: 150,
        slide: false,
        selectBoxWidth: 150,
        selectBoxHeight: 240,
        valueField: 'name',
        textField: 'name',
        initValue: $('#motorcadeEditor #type').val(),
        initText: $('#motorcadeEditor #type').val(),
        url: '../dictionary/list',
        parms: {
            kind: 10,
            grid: false
        }
    });
}