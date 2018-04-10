/**
 * 数据字典管理
 */
window.dictionary = {
    gridDictionary: undefined,
    gridDictionaryParams: {
        kind: 1,
        grid: true
    },
    list: function () {
        dictionary.gridDictionary = $("#gridDictionary").ligerGrid({
            columns: [{
                display: '名称',
                name: 'name',
                align: 'left',
                width: 500,
                id: 'pname'
            }, {
                display: '编码',
                name: 'code',
                align: 'left',
                width: 100
            }, {
                display: '序号',
                name: 'index',
                align: 'left',
                width: 100
            }],
            tree: {
                columnId: 'pname',
                idField: 'id',
                parentIDField: 'pid'
            },
            root: 'rows',
            record: 'total',
            usePager: false,
            url: '../dictionary/list',
            width: 'auto',
            height: '100%',
            heightDiff: 30,
            rownumbers: true,
            rowDraggable: true,
            parms: dictionary.gridDictionaryParams,
            onStopDrag: function (drag) {
                var row = drag.rows[0];
                if (drag.parent) {
                    if (row.id == drag.parent.id)
                        return false;
                }
                return true;
            },
            onRowDragDrop: function (data) {
                var row = data.rows[0];
                if (!row)
                    return;

                if (data.parent) {
                    if (row.pid == data.parent.id)
                        return;
                    dictionary.move(row.id, data.parent.id);
                    row.pid = data.parent.id;
                } else {
                    dictionary.move(row.id, null);
                    row.pid = null;
                }
            }
        });
    },
    create: function (kind, pid) {
        urlParms = {
            kind: kind
        };
        if (pid)
            urlParms.pid = pid;
        var url = '../dictionary/create.form';

        var op = {
            url: url,
            urlParms: urlParms,
            width: 450,
            height: 250,
            isHidden: false,
            title: '创建数据字典项',
            onLoaded: function () {
                dictionary.dialog.frame.window.validateCreate();
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
                            dictionary.gridDictionary.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        dictionary.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../dictionary/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 450,
            height: 250,
            isHidden: false,
            title: '修改数据字典项',
            onLoaded: function () {
                dictionary.dialog.frame.window.validateEdit();
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
                            dictionary.gridDictionary.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        dictionary.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('将删除用户所有相关资料，且不可恢复，真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../dictionary/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    dictionary.gridDictionary.reload();
            });
        });
    },
    move: function (id, pid) {
        $.post('../dictionary/move', {
            id: id,
            pid: pid
        }, function (data) {
            common.checkData(data, true, '移动字典项成功！');
        });
    }
}
$(function () {
    dictionary.list();
    $('#txtDictionaryKind').change(function () {
        dictionary.gridDictionaryParams.kind = $('#txtDictionaryKind').val();
        dictionary.gridDictionary.reload();
    });

    $('#btnCreateRootDictionary').click(function () {
        if (!dictionary.gridDictionary)
            return;
        var kind = $('#txtDictionaryKind').val();
        dictionary.create(kind, null);
    });
    $('#btnCreateSubDictionary').click(function () {
        if (!dictionary.gridDictionary)
            return;
        var kind = $('#txtDictionaryKind').val();
        var row = dictionary.gridDictionary.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择上级数据行！');
            return;
        }

        dictionary.create(kind, row.id);
    });
    $('#btnCreateSameDictionary').click(function () {
        if (!dictionary.gridDictionary)
            return;
        var kind = $('#txtDictionaryKind').val();
        var row = dictionary.gridDictionary.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择同级数据行！');
            return;
        }

        dictionary.create(kind, row.pid);
    });
    $('#btnEditDictionary').click(function () {
        if (!dictionary.gridDictionary)
            return;

        var row = dictionary.gridDictionary.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        dictionary.edit(row.id);
    });
    $('#btnRemoveDictionary').click(function () {
        if (!dictionary.gridDictionary)
            return;

        var row = dictionary.gridDictionary.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        dictionary.remove(row.id);
    });
    $('#btnQueryDictionary').click(function () {
        if (!dictionary.gridDictionary)
            return;
        dictionary.gridDictionaryParams.kind = $('#txtDictionaryKind').val();
        dictionary.gridDictionary.reload();
    });
});