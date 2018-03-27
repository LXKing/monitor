/**
 * 修改路线
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                required: true,
                rangelength: [1, 50],
                remote: {
                    url: '../routeArea/exist',
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
                remote: '该名称已存在'
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
                required: true,
                rangelength: [1, 50],
                remote: {
                    url: '../routeArea/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#routeAreaEditor #id").val();
                        },
                        checkId: true
                    }
                }
            }
        },
        messages: {
            name: {
                remote: '该名称已存在'
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
    $('#routeAreaEditor #flag').val(value);
}

function resetLigerui() {
    var dataGrid = [{
        id: 0,
        name: '根据时间'
    }, {
        id: 2,
        name: '进路线报警给驾驶员'
    }, {
        id: 3,
        name: '进路线报警给平台'
    }, {
        id: 4,
        name: '出路线报警给驾驶员'
    }, {
        id: 5,
        name: '出路线报警给平台'
    }];
    flagsCheckBoxList = $("#flags").ligerCheckBoxList({
        data: dataGrid,
        textField: 'name'
    });
    var flag = $('#routeAreaEditor #flag').val();
    var flags = common.numberToBits(flag);
    flagsCheckBoxList.setValue(flags);

    $('#routeAreaEditor #startTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#routeAreaEditor #startTime").val()
    });

    $('#routeAreaEditor #endTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#routeAreaEditor #endTime").val()
    });
}