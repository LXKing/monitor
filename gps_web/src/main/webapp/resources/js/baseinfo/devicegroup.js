/**
 * 用户组别管理
 */
window.devicegroup = {
    gridDeviceGroup: undefined,
    list: function () {
        devicegroup.gridDeviceGroup = $('#gridDeviceGroup').ligerGrid({
            columns: [{
                display: '名称',
                name: 'name',
                align: 'left',
                width: '400',
                id: 'pname'
            }, {
                display: '说明',
                name: 'remark',
                align: 'left',
                width: 'auto'
            }],
            root: 'rows',
            record: 'total',
            usePager: false,
            url: 'devicegroup/list',
            inWindow: false,
            width: 'auto',
            height: '100%',
            rowDraggable: true,
            rownumbers: true,
            tree: {
                columnId: 'pname',
                idField: 'id',
                parentIDField: 'pid'
            },
            onStopDrag: function (drag) {
                function findPid(list, id, pid) {
                    for (var int = 0; int < list.length; int++) {
                        var r = list[int];
                        if (r.id == pid) {
                            if (r.id == id)
                                return false;
                            else
                                return findPid(list, id, r.pid);
                        }
                    }
                    return true;
                }

                var row = drag.rows[0];
                if (drag.parent) {
                    if (row.id == drag.parent.id)
                        return false;
                    data = devicegroup.gridDeviceGroup.getData();
                    return findPid(data, row.id, drag.parent.pid);
                }
            },
            onRowDragDrop: function (data) {
                var row = data.rows[0];
                if (!row)
                    return;

                if (data.parent) {
                    if (row.pid == data.parent.id)
                        return;
                    devicegroup.move(row.id, data.parent.id);
                    row.pid = data.parent.id;
                } else {
                    devicegroup.move(row.id, null);
                    row.pid = null;
                }
            }
        });
    },
    create: function (pid) {
        var url = 'devicegroup/create.form';
        $.get(url, {
            pid: pid
        }, function (html) {
            var op = {
                html: html,
                formName: 'createDeviceGroup',
                width: 450,
                height: 180,
                isHidden: false,
                title: '创建设备组',
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (!common.form.check(op))
                            return;
                        var form = $('#' + op.formName).serialize();
                        $.post(url, form, function (data) {
                            var result = common.form.save(dialog, data, op);
                            if (result === true)
                                devicegroup.gridDeviceGroup.reload();
                        });
                    }
                }, {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }],
                rules: {
                    rules: {
                        name: {
                            required: true,
                            rangelength: [1, 50],
                            remote: {
                                url: '../remote/devicegroup/exist',
                                type: "post",
                                dataType: "json",
                                data: {
                                    key: function () {
                                        return $("#name").val();
                                    },
                                    id: -1,
                                    checkId: false
                                }
                            }
                        }
                    },
                    messages: {
                        name: {
                            remote: '该组别已存在'
                        }
                    }
                }
            };
            common.showDialog(op);
        });
    },
    edit: function (id) {
        var url = 'devicegroup/edit.form';
        $.get(url, {
            id: id
        }, function (html) {
            var op = {
                html: html,
                formName: 'editDeviceGroup',
                width: 450,
                height: 180,
                isHidden: false,
                title: '修改设备组',
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (!common.form.check(op))
                            return;
                        var form = $('#' + op.formName).serialize();
                        $.post(url, form, function (data) {
                            var result = common.form.save(dialog, data, op);
                            if (result === true)
                                devicegroup.gridDeviceGroup.reload();
                        });
                    }
                }, {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }],
                rules: {
                    rules: {
                        name: {
                            required: true,
                            rangelength: [1, 50],
                            remote: {
                                url: '../remote/devicegroup/exist',
                                type: "post",
                                dataType: "json",
                                data: {
                                    key: function () {
                                        return $('#name').val();
                                    },
                                    id: function () {
                                        return $('#id').val();
                                    },
                                    checkId: true
                                }
                            }
                        }
                    },
                    messages: {
                        name: {
                            remote: '该组别已存在'
                        }
                    }
                }
            };
            common.showDialog(op);
        });
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('devicegroup/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    devicegroup.gridDeviceGroup.reload();
            });
        });
    },
    move: function (id, pid) {
        if (id === pid)
            return;
        $.post('devicegroup/move', {
            id: id,
            pid: pid
        }, function (data) {
            common.checkData(data, true, '移动组别成功！');
        });
    }
};
$(function () {
    $('#btnDeviceGroup').addClass("select");
    devicegroup.list();
    $('#btnCreateDeviceGroup').click(function () {
        devicegroup.create(null);
    });
    $('#btnCreateSubDeviceGroup').click(function () {
        if (!devicegroup.gridDeviceGroup)
            return;

        var row = devicegroup.gridDeviceGroup.getSelected();
        if (!row)
            return;
        devicegroup.create(row.id);
    });
    $('#btnEditDeviceGroup').click(function () {
        if (!devicegroup.gridDeviceGroup)
            return;

        var row = devicegroup.gridDeviceGroup.getSelected();
        if (!row)
            return;
        devicegroup.edit(row.id);
    });
    $('#btnReomveDeviceGroup').click(function () {
        if (!devicegroup.gridDeviceGroup)
            return;

        var row = devicegroup.gridDeviceGroup.getSelected();
        if (!row)
            return;
        devicegroup.remove(row.id);
    });
});