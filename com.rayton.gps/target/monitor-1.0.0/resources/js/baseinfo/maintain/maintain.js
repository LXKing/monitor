/**
 * 车辆保养管理
 */

window.maintain = {
    gridMaintain: undefined,
    gridMaintainParams: {
        plateNumber: '',
        from: null,
        to: null
    },
    query: function () {
        if (!maintain.gridMaintain)
            maintain.gridMaintain = $("#maintainFrame #gridMaintain").ligerGrid({
                columns: [{
                    display: '车牌号',
                    name: 'plateNumber',
                    align: 'left',
                    width: 150,
                    frozen: true
                }, {
                    display: '保养时间',
                    name: 'doneDate',
                    align: 'left',
                    width: 100
                }, {
                    display: '保养内容',
                    name: 'content',
                    align: 'left',
                    width: 300
                }, {
                    display: '保养里程',
                    name: 'mileage',
                    align: 'left',
                    width: 100
                }, {
                    display: '保养费用',
                    name: 'fee',
                    align: 'left',
                    width: 100
                }, {
                    display: '下次保养时间',
                    name: 'nextDate',
                    align: 'left',
                    type: 'date',
                    width: 100
                }, {
                    display: '下次保养里程',
                    name: 'nextMileage',
                    align: 'left',
                    type: 'date',
                    width: 100
                }, {
                    display: '记录员',
                    name: 'userName',
                    align: 'left',
                    width: 'auto'
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../maintain/query',
                width: 'auto',
                height: '100%',
                pageSize: 30,
                rownumbers: true,
                parms: maintain.gridMaintainParams
            });
        else {
            maintain.gridMaintain.changePage('first');
            maintain.gridMaintain.reload();
        }
    },
    create: function () {
        var url = '../maintain/create.form';
        var op = {
            url: url,
            width: 750,
            height: 400,
            isHidden: false,
            title: '创建车辆保养记录',
            onLoaded: function () {
                maintain.dialog.frame.window.validateCreate();
                maintain.dialog.frame.window.resetLigerui();
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    if (dialog.frame.window.validateCreate() == false) {
                        return;
                    }

                    var form = $('form', dialog.frame.document);
                    var formData = form.serialize();
                    $.post(url, formData, function (data) {
                        var result = common.form.save(dialog, data, op);
                        if (result === true)
                            maintain.gridMaintain.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }],
        };
        maintain.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../maintain/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 400,
            isHidden: false,
            title: '修改车辆保养记录',
            onLoaded: function () {
                maintain.dialog.frame.window.validateEdit();
                maintain.dialog.frame.window.resetLigerui();
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    if (dialog.frame.window.validateEdit() == false) {
                        return;
                    }

                    var form = $('form', dialog.frame.document);
                    var formData = form.serialize();
                    $.post(url, formData, function (data) {
                        var result = common.form.save(dialog, data, op);
                        if (result === true)
                            maintain.gridMaintain.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        maintain.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('将删除车辆所有相关资料，且不可恢复，真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../maintain/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    maintain.gridMaintain.reload();
            });
        });
    }
}
$(function () {
    var now = new Date();
    var startDate = now.addDays(-30);
    var endDate = now.addDays(1);

    maintain.gridMaintainParams.from = startDate.toShortDateString();
    maintain.gridMaintainParams.to = endDate.toShortDateString();

    maintain.startDate = $('#maintainFrame #txtStartDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: maintain.gridMaintainParams.from
    });
    maintain.endDate = $('#maintainFrame #txtEndDate').ligerDateEditor({
        width: 100,
        format: "yyyy-MM-dd",
        cancelable: true,
        initValue: maintain.gridMaintainParams.to
    });
    maintain.query();

    $('#maintainFrame #btnCreateMaintain').click(function () {
        maintain.create();
    });
    $('#maintainFrame #btnEditMaintain').click(function () {
        if (!maintain.gridMaintain)
            return;

        var row = maintain.gridMaintain.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        maintain.edit(row.id);
    });
    $('#maintainFrame #btnRemoveMaintain').click(function () {
        if (!maintain.gridMaintain)
            return;

        var row = maintain.gridMaintain.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        maintain.remove(row.id);
    });
    $('#maintainFrame #btnQueryMaintain').click(function () {
        if (!maintain.gridMaintain)
            return;
        maintain.gridMaintainParams.plateNumber = $('#maintainFrame #txtMaintainFilter').val();
        maintain.gridMaintainParams.from = maintain.startDate.getValue().toShortDateString();
        maintain.gridMaintainParams.to = maintain.endDate.getValue().toShortDateString();
        maintain.query();
    });
});