/**
 * 修改路段
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            name: {
                required: true,
                rangelength: [1, 30],
                remote: {
                    url: '../sectionArea/exist',
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
                    url: '../sectionArea/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $("#sectionAreaEditor #id").val();
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
    $('#sectionAreaEditor #flag').val(value);
}

function resetLigerui(polyline) {
    if (polyline) {
        var path = polyline.getPath();

        var points = [];
        for (var x = 0; x < path.length; x++) {
            var item = path[x];
            var point = common.converter.bd09_gcj02_wgs84(item.lat, item.lng);
            item.data = item.data || {};
            item.data.lat = point.lat;
            item.data.lng = point.lng;
            item.data.index = x;

            points.push(item.data);
        }
        var json = JSON.stringify(points);
        $("#sectionAreaEditor #path").val(json);
    }
    var dataGrid = [{
        id: 0,
        name: '行驶时间'
    }, {
        id: 1,
        name: '限速'
    }, {
        id: 2,
        name: '南纬'
    }, {
        id: 3,
        name: '西经'
    }];
    flagsCheckBoxList = $("#flags").ligerCheckBoxList({
        rowSize: 10,
        data: dataGrid,
        textField: 'name'
    });
    var flag = $('#sectionAreaEditor #flag').val();
    var flags = common.numberToBits(flag);
    flagsCheckBoxList.setValue(flags);
}