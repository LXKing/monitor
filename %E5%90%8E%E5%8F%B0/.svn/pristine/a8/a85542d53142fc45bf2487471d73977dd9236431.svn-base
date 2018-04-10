/**
 * 修改多边形区域
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                remote: {
                    url: '../polygonArea/exist',
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
                    url: '../polygonArea/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#polygonAreaEditor #id").val();
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
    $('#polygonAreaEditor #flag').val(value);
}

function resetLigerui(polygen) {
    if (polygen) {
        var path = polygen.getPath();
        var points = [];
        for (var x = 0; x < path.length; x++) {
            var item = path[x];
            var point = common.converter.bd09_gcj02_wgs84(item.lat, item.lng);
            points.push(point);
        }

        var json = JSON.stringify(points);
        $("#polygonAreaEditor #path").val(json);
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
    var flag = $('#polygonAreaEditor #flag').val();
    var flags = common.numberToBits(flag);
    flagsCheckBoxList.setValue(flags);

    $('#polygonAreaEditor #startTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#polygonAreaEditor #startTime").val()
    });

    $('#polygonAreaEditor #endTime').ligerDateEditor({
        format: "yyyy-MM-dd hh:mm:ss",
        cancelable: true,
        showTime: true,
        width: 150,
        initValue: $("#polygonAreaEditor #endTime").val()
    });
}