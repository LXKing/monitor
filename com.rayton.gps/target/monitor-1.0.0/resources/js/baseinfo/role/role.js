/**
 * 角色管理
 */
window.role = {
    gridRole: null,
    list: function () {
        role.gridRole = $("#roleFrame #gridRole").ligerGrid({
            columns: [{
                display: '名称',
                name: 'name',
                align: 'left',
                width: 300
            }, {
                display: '说明',
                name: 'remark',
                align: 'left',
                width: 'auto'
            }],
            root: 'rows',
            record: 'total',
            usePager: false,
            heightDiff: 30,
            url: '../role/list',
            width: 'auto',
            height: '100%'
        });
    },
    create: function () {
        var url = '../role/create.form';
        var op = {
            url: url,
            width: 550,
            height: 200,
            isHidden: false,
            title: '创建角色',
            onLoaded: function () {
                role.dialog.frame.window.validateCreate();
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
                            role.gridRole.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        role.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../role/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 550,
            height: 200,
            isHidden: false,
            title: '修改角色',
            onLoaded: function () {
                role.dialog.frame.window.validateEdit();
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
                            role.gridRole.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        role.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('将删除角色所有相关资料，且不可恢复，真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../role/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    role.gridRole.reload();
            });
        });
    },
    authorize: function (id) {
        var url = '../role/authorize.form';
        var op = {
            url: url,
            width: 850,
            height: 530,
            isHidden: false,
            title: '角色授权',
            onLoaded: function () {
                role.dialog.frame.window.resetLigerui(id);
            },
            buttons: [{
                text: '仅选择节点',
                onclick: function (item, dialog) {
                    dialog.frame.window.selectNodeOnly();
                }
            }, {
                text: '保存',
                onclick: function (item, dialog) {
                    dialog.frame.window.save(id, function (r) {
                        common.checkData(r, true, '数据保存成功！')
                        dialog.close();
                    });
                }
            }, {
                text: '关闭',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        role.dialog = $.ligerDialog.open(op);
    }
};
$(function () {
    role.list();

    $('#roleFrame #btnRefreshRole').click(function () {
        role.list();
    });

    $('#roleFrame #btnCreateRole').click(function () {
        role.create();
    });
    $('#roleFrame #btnEditRole').click(function () {
        if (!role.gridRole)
            return;

        var row = role.gridRole.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        role.edit(row.id);
    });
    $('#roleFrame #btnRemoveRole').click(function () {
        if (!role.gridRole)
            return;

        var row = role.gridRole.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        role.remove(row.id);
    });
    $('#btnAuthorizeRole').click(function () {
        if (!role.gridRole)
            return;

        var row = role.gridRole.getSelected();
        console.log(row);
        console.log(row.id)
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        role.authorize(row.id);
    });
});
