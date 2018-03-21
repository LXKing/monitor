/**
 * 设备管理
 */
window.device = {
    gridDevice: undefined,
    gridDeviceParams: {
        filter: ''
    },
    query: function () {
        if (!device.gridDevice)
            device.gridDevice = $("#deviceFrame #gridDevice").ligerGrid({
                columns: [{
                    display: '设备号',
                    name: 'deviceNumber',
                    frozen: true,
                    align: 'left'
                }, {
                    display: '电话号码',
                    name: 'phoneNumber',
                    align: 'left'
                }, {
                    display: '出厂号',
                    name: 'factoryNumber',
                    align: 'left'
                }, {
                    display: '型号',
                    name: 'model',
                    align: 'left'
                }, {
                    display: '服务开始时间',
                    name: 'serviceStartDate',
                    align: 'left',
                    type: 'date',
                    width: 100
                }, {
                    display: '服务结束时间',
                    name: 'serviceEndDate',
                    align: 'left',
                    type: 'date',
                    width: 100
                }, {
                    display: '启用否',
                    name: 'enable',
                    width: 80,
                    render: function (row) {
                        if (row.enable === true)
                            return '启用';
                        else if (row.enable === false)
                            return '禁用';
                        return '';
                    }
                }, {
                    display: '保修期',
                    name: 'warranty',
                    type: 'date',
                    width: 100
                }, {
                    display: '说明',
                    name: 'remark',
                    align: 'left',
                    width: 100
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../device/query',
                width: 'auto',
                height: '100%',
                pageSize: 30,
                rowDraggable: true,
                rownumbers: true,
                parms: device.gridDeviceParams
            });
        else {
            device.gridDevice.changePage('first');
            device.gridDevice.reload();
        }
    },
    create: function () {
        var url = '../device/create.form';
        var op = {
            url: url,
            width: 850,
            height: 630,
            isHidden: false,
            title: '创建设备',
            onLoaded: function () {
                device.dialog.frame.window.validateCreate();
                device.dialog.frame.window.resetLigerui();
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
                            device.gridDevice.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        device.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../device/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 850,
            height: 630,
            isHidden: false,
            title: '修改设备',
            onLoaded: function () {
                device.dialog.frame.window.validateEdit();
                device.dialog.frame.window.resetLigerui();
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
                            device.gridDevice.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        device.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../device/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    device.gridDevice.reload();
            });
        });
    }
}
$(function () {
    if (console)
        console.log('设备管理');
    device.query();

    $('#deviceFrame #btnCreateDevice').click(function () {
        device.create();
    });
    $('#deviceFrame #btnEditDevice').click(function () {
        if (!device.gridDevice)
            return;

        var row = device.gridDevice.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        device.edit(row.id);
    });
    $('#deviceFrame #btnRemoveDevice').click(function () {
        if (!device.gridDevice)
            return;

        var row = device.gridDevice.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        device.remove(row.id);
    });
    $('#deviceFrame #btnQueryDevice').click(function () {
        if (!device.gridDevice)
            return;
        device.gridDeviceParams.filter = $('#deviceFrame #txtDeviceFilter').val();
        device.query();
    });
});