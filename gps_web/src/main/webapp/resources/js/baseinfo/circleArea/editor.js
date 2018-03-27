/**
 * 圆形区域
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                remote: {
                    url: '../circleArea/exist',
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
                remote: {
                    url: '../circleArea/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#circleAreaEditor #id").val();
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
    $('#circleAreaEditor #flag').val(value);
}

function resetLigerui(circle) {
    if (circle) {
        var center = circle.getCenter();
        var p = common.converter.bd09_gcj02_wgs84(center.lat, center.lng);
        $("#circleAreaEditor #lat").val(p.lat);
        $("#circleAreaEditor #lng").val(p.lng);
        var r = circle.getRadius();
        $("#circleAreaEditor #radius").val(Math.round(r));
    }

    var dataGrid = [{
        id: 0,
        name: '根据时间'
    }, {
        id: 1,
        name: '限速'
    }, {
        id: 2,
        name: '进区域报警给驾驶员'
    }, {
        id: 3,
        name: '进区域报警给平台'
    }, {
        id: 4,
        name: '出区域报警给驾驶员'
    }, {
        id: 5,
        name: '出区域报警给平台'
    }, {
        id: 6,
        name: '南纬'
    }, {
        id: 7,
        name: '西经'
    }, {
        id: 8,
        name: '禁止开门'
    }, {
        id: 14,
        name: '进区域关闭通信模块'
    }, {
        id: 15,
        name: '进区域采集GNSS详细定位数据'
    }];
    flagsCheckBoxList = $("#flags").ligerCheckBoxList({
        data: dataGrid,
        textField: 'name'
    });
    var flag = $('#circleAreaEditor #flag').val();
    var flags = common.numberToBits(flag);
    flagsCheckBoxList.setValue(flags);

    $('#circleAreaEditor #startTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#circleAreaEditor #startTime").val()
    });

    $('#circleAreaEditor #endTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#circleAreaEditor #endTime").val()
    });
}