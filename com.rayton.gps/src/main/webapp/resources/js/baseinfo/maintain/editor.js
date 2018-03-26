/**
 * 修改驾驶员
 */
function validateCreate() {
    var v = $('form').validate();

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function validateEdit() {
    var v = $('form').validate();

    var result = v.checkForm();
    v.showErrors();
    return result;
}

function resetLigerui() {
    $('#maintainEditor #doneDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#maintainEditor #doneDate").val().replace(' 00:00:00', '')
    });

    $('#maintainEditor #nextDate').ligerDateEditor({
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: $("#maintainEditor #nextDate").val().replace(' 00:00:00', '')
    });

    $("#maintainEditor #plateNumber").ligerComboBox({
        width: 150,
        slide: false,
        selectBoxWidth: 500,
        selectBoxHeight: 200,
        valueField: 'id',
        textField: 'plateNumber',
        valueFieldID: 'vehicleId',
        initValue: $('#maintainEditor #vehicleId').val(),
        initText: $('#maintainEditor #plateNumber').val(),
        condition: {
            fields: [{
                name: 'plateNumber',
                label: '车牌号'
            }]
        },
        conditionSearchClick: function (data) {
            var rules = data.rules;
            var value = '';
            if (rules && rules.length > 0) {
                value = rules[0]['value'];
            }
            data.grid.options.parms = {
                plateNumber: value
            };
            data.grid.reload();
        },
        grid: {
            columns: [{
                display: '车牌号',
                name: 'plateNumber',
                align: 'left',
                width: 150,
                frozen: true
            }, {
                display: '车牌颜色',
                name: 'plateColor',
                align: 'left',
                width: 50
            }, {
                display: '设备号',
                name: 'deviceNumber',
                align: 'left',
                width: 150
            }, {
                display: '电话号码',
                name: 'phoneNumber',
                align: 'left',
                width: 150
            }, {
                display: '安装日期',
                name: 'installDate',
                align: 'left',
                type: 'date',
                width: 100
            }, {
                display: '年检日期',
                name: 'annualSurveyDate',
                align: 'left',
                type: 'date',
                width: 100
            }, {
                display: '车队',
                name: 'motorcade',
                align: 'left',
                width: 160
            }, {
                display: '备注',
                name: 'remark',
                align: 'left',
                width: 200
            }],
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            url: '../vehicle/search',
            width: 'auto',
            height: '100%',
            pageSize: 30,
            rownumbers: true
        }
    });

    $('#maintainEditor #type').ligerComboBox({
        width: 150,
        slide: false,
        selectBoxWidth: 150,
        selectBoxHeight: 240,
        valueField: 'name',
        textField: 'name',
        initValue: $('#maintainEditor #type').val(),
        initText: $('#maintainEditor #type').val(),
        url: '../dictionary/list',
        parms: {
            kind: 11,
            grid: false
        }
    });
}