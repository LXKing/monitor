/**
 * 矩形区域
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                remote: {
                    url: '../rectangleArea/exist',
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
                    url: '../rectangleArea/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#rectangleAreaEditor #id").val();
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
    $('#rectangleAreaEditor #flag').val(value);
}

function resetLigerui(rectangle) {
    if (rectangle) {
        // getSouthWest() Point 返回矩形区域的西南角。
        // getNorthEast() Point 返回矩形区域的东北角。
        var bounds = rectangle.getBounds();
        var sw = bounds.getSouthWest();
        sw = common.converter.bd09_gcj02_wgs84(sw.lat, sw.lng);
        var ne = bounds.getNorthEast();
        ne = common.converter.bd09_gcj02_wgs84(ne.lat, ne.lng);
        $("#rectangleAreaEditor #ullat").val(ne.lat);
        $("#rectangleAreaEditor #ullng").val(sw.lng);
        $("#rectangleAreaEditor #brlat").val(sw.lat);
        $("#rectangleAreaEditor #brlng").val(ne.lng);
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
    var flag = $('#rectangleAreaEditor #flag').val();
    var flags = common.numberToBits(flag);
    flagsCheckBoxList.setValue(flags);

    $('#rectangleAreaEditor #startTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#rectangleAreaEditor #startTime").val()
    });

    $('#rectangleAreaEditor #endTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#rectangleAreaEditor #endTime").val()
    });
}