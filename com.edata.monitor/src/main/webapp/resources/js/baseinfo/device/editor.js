/**
 * 修改终端
 */
function validateCreate() {
    var v = $('form').validate({
        rules: {
            deviceNumber: {
                remote: {
                    url: '../device/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: -1,
                        checkId: false
                    }
                }
            },
            account: {
                remote: {
                    url: '../user/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: -1,
                        checkId: false
                    }
                }
            }
        },
        messages: {
            deviceNumber: {
                remote: '该设备已存在'
            },
            account: {
                remote: '该帐号存在'
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
            deviceNumber: {
                remote: {
                    url: '../device/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $('#deviceEditor #id').val();
                        },
                        checkId: true
                    }
                }
            },
            account: {
                remote: {
                    url: '../user/exist',
                    type: "post",
                    dataType: "json",
                    data: {
                        id: function () {
                            return $('#deviceEditor #id').val();
                        },
                        checkId: true
                    }
                }
            }
        },
        messages: {
            deviceNumber: {
                remote: '该设备号已存在'
            },
            account: {
                remote: '该帐号已存在'
            }
        }
    });

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function resetLigerui() {
    $('#deviceEditor #serviceStartDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#deviceEditor #serviceStartDate").val().replace(' 00:00:00', '')
    });

    $('#deviceEditor #serviceEndDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#deviceEditor #serviceEndDate").val().replace(' 00:00:00', '')
    });

    $('#deviceEditor #warranty').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#deviceEditor #warranty").val().replace(' 00:00:00', '')
    });

    $('#deviceEditor #purchaseDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#deviceEditor #purchaseDate").val().replace(' 00:00:00', '')
    });

    $('#deviceEditor #installDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#deviceEditor #installDate").val().replace(' 00:00:00', '')
    });
    $("#deviceEditor #model").ligerComboBox({
        width: 150,
        slide: false,
        selectBoxWidth: 500,
        selectBoxHeight: 200,
        valueField: 'name',
        textField: 'name',
        initValue: $('#deviceEditor #model').val(),
        initText: $('#deviceEditor #model').val(),
        grid: getGridDictionary(),
        parms: {
            kind: 8,
            grid: true
        }
    });
    $("#deviceEditor #factoryName").ligerComboBox({
        width: 200,
        slide: false,
        selectBoxWidth: 200,
        selectBoxHeight: 240,
        valueField: 'name',
        textField: 'name',
        initValue: $('#deviceEditor #factoryName').val(),
        initText: $('#deviceEditor #factoryName').val(),
        url: '../dictionary/list',
        parms: {
            kind: 9,
            grid: false
        }
    });
    var phoneNumber = $("#deviceEditor #phoneNumber").ligerComboBox({
        width: 150,
        slide: false,
        selectBoxWidth: 500,
        selectBoxHeight: 200,
        valueField: 'id',
        textField: 'phoneNumber',
        valueFieldID: 'simcardId',
        initValue: $('#deviceEditor #simcardId').val(),
        initText: $('#deviceEditor #phoneNumber').val(),
        condition: {
            fields: [{
                name: 'phoneNumber',
                label: '电话号码'
            }]
        },
        conditionSearchClick: function (data) {
            var rules = data.rules;
            var value = '';
            if (rules && rules.length > 0) {
                value = rules[0]['value'];
            }
            data.grid.options.parms = {
                phoneNumber: value
            };
            data.grid.reload();
        },
        grid: getGridSimcard()
    });

    function getGridDictionary() {
        var options = {
            columns: [{
                display: '名称',
                name: 'name',
                align: 'left',
                width: 500,
                id: 'pname'
            }, {
                display: '编码',
                name: 'code',
                align: 'left'
            }],
            tree: {
                columnId: 'pname',
                idField: 'id',
                parentIDField: 'pid'
            },
            root: 'rows',
            usePager: false,
            rownumbers: false,
            url: '../dictionary/list',
            width: 'auto',
            height: '100%'
        };
        return options;
    }
    ;

    function getGridSimcard() {
        var options = {
            columns: [{
                display: '电话号码',
                name: 'phoneNumber',
                align: 'left',
                frozen: true,
                width: 100
            }, {
                display: '语音类型',
                name: 'speechType',
                width: 100
            }, {
                display: '开通短信否',
                name: 'openSMS',
                width: 80,
                render: function (row) {
                    if (row.openSMS === true)
                        return '已开通';
                    else if (row.openSMS === false)
                        return '未开通';
                    return '';
                }
            }, {
                display: '运营商',
                name: 'carrierOperator',
                width: 100
            }, {
                display: '备注',
                name: 'remark'
            }],
            switchPageSizeApplyComboBox: false,
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            url: '../simcard/free',
            pageSize: 30
        };
        return options;
    }
}