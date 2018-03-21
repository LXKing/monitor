/**
 * 企业管理
 */
window.company = {
    gridCompany: null,
    gridCompanyParams: {
        filter: ''
    },
    query: function () {
        if (!company.gridCompany)
            company.gridCompany = $("#comapnyFrame #gridCompany").ligerGrid({
                columns: [{
                    display: '简称',
                    name: 'shortName',
                    align: 'left',
                    width: 100
                }, {
                    display: '全称',
                    name: 'fullName',
                    align: 'left',
                    width: 200
                }, {
                    display: '值班电话',
                    name: 'ondutyPhone',
                    align: 'left',
                    width: 100
                }, {
                    display: '办公地址',
                    name: 'officeAddress',
                    align: 'left',
                    width: 250
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
                    name: 'enabled',
                    width: 80,
                    render: function (row) {
                        if (row.enable === true)
                            return '启用';
                        else if (row.enable === false)
                            return '禁用';
                        return '';
                    }
                }, {
                    display: '入网时间',
                    name: 'createTime',
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
                url: '../company/query',
                width: 'auto',
                height: '100%',
                pageSize: 30,
                rownumbers: true,
                parms: company.gridCompanyParams
            });
        else {
            company.gridCompany.changePage('first');
            company.gridCompany.reload();
        }
    },
    create: function () {
        var url = '../company/create.form';
        var op = {
            url: url,
            width: 850,
            height: 530,
            isHidden: false,
            title: '创建企业',
            onLoaded: function () {
                company.dialog.frame.window.validateCreate();
                company.dialog.frame.window.resetLigerui();
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
                            company.gridCompany.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        company.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../company/edit.form'

        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 850,
            height: 530,
            isHidden: false,
            title: '修改企业',
            onLoaded: function () {
                company.dialog.frame.window.validateEdit();
                company.dialog.frame.window.resetLigerui();
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
                            company.gridCompany.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        company.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('将删除企业所有相关资料，且不可恢复，真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../company/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    company.gridCompany.reload();
            });
        });
    },
    authorize: function (id) {
        var url = '../company/authorize.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 850,
            height: 530,
            isHidden: false,
            title: '企业授权',
            onLoaded: function () {
                company.dialog.frame.window.resetLigerui(id);
            },
            buttons: [{
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
        company.dialog = $.ligerDialog.open(op);
    }
};
$(function () {
    company.query();
    $('#comapnyFrame #btnQueryCompany').click(function () {
        if (!company.gridCompany)
            return;
        company.gridCompanyParams.filter = $('#comapnyFrame #txtCompanyFilter').val();
        company.query();
    });
    $('#comapnyFrame #btnCreateCompany').click(function () {
        company.create();
    });
    $('#comapnyFrame #btnEditCompany').click(function () {
        if (!company.gridCompany)
            return;

        var row = company.gridCompany.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        company.edit(row.id);
    });
    $('#comapnyFrame #btnRemoveCompany').click(function () {
        if (!company.gridCompany)
            return;

        var row = company.gridCompany.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        company.remove(row.id);
    });

    $('#comapnyFrame #btnAuthorizeCompany').click(function () {
        if (!company.gridCompany)
            return;

        var row = company.gridCompany.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        company.authorize(row.id);
    });
});