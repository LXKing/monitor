/**
 * 车主管理
 */
window.owner = {
    gridVehicles: {},
    gridOwner: null,
    gridOwnerParams: {
        filter: ''
    },
    listVehicle: function (row, detailPanel, callback) {
        var ownerId = row.id;
        var grid = document.createElement('div');
        $(detailPanel).append(grid);
        var columns = [{
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
            width: 100
        }, {
            display: '安装日期',
            name: 'installDate',
            align: 'left',
            type: 'date',
            width: 80
        }, {
            display: '年检日期',
            name: 'annualSurveyDate',
            align: 'left',
            type: 'date',
            width: 80
        }, {
            display: '车队',
            name: 'motorcade',
            align: 'left',
            width: 100
        }, {
            display: '备注',
            name: 'remark',
            align: 'left',
            width: 100
        }];
        if ($('#removeVehicle_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="owner.removeVehicle(\'' + row.id + '\',\'' + rw.id + '\')">删除</a>';
                    return html;
                }
            });
        }
        owner.gridVehicles[ownerId] = $(grid).css('margin', 5).ligerGrid({
            columns: columns,
            url: '../owner/vehicles',
            title: '车辆列表',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            usePager: false,
            parms: {
                ownerId: ownerId
            }
        });
    },
    addVehicle: function (ownerId) {
        owner.addVehicleParms = owner.addVehicleParms || {};
        owner.addVehicleParms.ownerId = ownerId;

        if (!owner.dialogSelectVehicle) {
            owner.gridVehicleInOwnerParms = {
                filter: ''
            };
            owner.dialogSelectVehicle = $.ligerDialog.open({
                title: '选择车辆',
                width: 800,
                height: 400,
                target: $("#dialogOwnerSelectVehicle")
            });
            owner.gridVehicleInOwner = $("#dialogOwnerSelectVehicle #gridVehicleInOwner55c6cc891d19b64134b972ec").ligerGrid({
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
                    width: 100
                }, {
                    display: '安装日期',
                    name: 'installDate',
                    align: 'left',
                    type: 'date',
                    width: 80
                }, {
                    display: '年检日期',
                    name: 'annualSurveyDate',
                    align: 'left',
                    type: 'date',
                    width: 80
                }, {
                    display: '车队',
                    name: 'motorcade',
                    align: 'left',
                    width: 100
                }, {
                    display: '备注',
                    name: 'remark',
                    align: 'left',
                    width: 100
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../vehicle/query',
                width: 'auto',
                height: 300,
                pageSize: 30,
                rownumbers: true,
                checkbox: true,
                parms: owner.gridVehicleInOwnerParms
            });

            $('#dialogOwnerSelectVehicle #btnQueryVehicle').click(function () {
                owner.gridVehicleInOwnerParms.filter = $('#dialogOwnerSelectVehicle #txtVehicleFilter').val();
                owner.gridVehicleInOwner.reload();
            });

            $('#dialogOwnerSelectVehicle #btnSelectVehicle').click(function () {
                var rows = owner.gridVehicleInOwner.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                owner.dialogSelectVehicle.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].id);
                }
                $.post('../owner/addVehicles', {
                    ownerId: owner.addVehicleParms.ownerId,
                    vehicles: JSON.stringify(list)
                }, function (data) {
                    var result = common.checkData(data, "", "添加车辆成功！");
                    if (result === true) {
                        if (owner.addVehicleParms.ownerId in owner.gridVehicles)
                            owner.gridVehicles[owner.addVehicleParms.ownerId].reload();
                    }
                });
            });
        } else
            owner.dialogSelectVehicle.show();
    },
    removeVehicle: function (ownerId, vehicleId) {
        owner.removeVehicleParms = owner.removeVehicleParms || {};
        owner.removeVehicleParms.ownerId = ownerId;

        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../owner/removeVehicle', {
                ownerId: ownerId,
                vehicleId: vehicleId
            }, function (data) {
                var result = common.checkData(data, "", "删除车辆成功！");
                if (result === true) {
                    if (owner.removeVehicleParms.ownerId in owner.gridVehicles)
                        owner.gridVehicles[owner.removeVehicleParms.ownerId].reload();
                }
            });
        });
    },
    query: function () {
        var columns = [{
            display: '姓名',
            name: 'ownerName',
            align: 'left',
            width: 100
        }, {
            display: '电话',
            name: 'phone',
            align: 'left',
            width: 100
        }, {
            display: '证件类型',
            name: 'idType',
            align: 'left',
            width: 100
        }, {
            display: '证件编号',
            name: 'idNumber',
            align: 'left',
            width: 150
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
            display: '开户时间',
            name: 'createTime',
            type: 'date',
            width: 100
        }, {
            display: '说明',
            name: 'remark',
            align: 'left',
            width: 100
        }];
        if ($('#addVehicles_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    var html = '<a href="#" onclick="owner.addVehicle(\'' + row.id + '\')">分配车辆</a>';
                    return html;
                }
            });
        }
        if (!owner.gridOwner)
            owner.gridOwner = $("#ownerFrame #gridOwner").ligerGrid({
                columns: columns,
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../owner/query',
                width: 'auto',
                height: '100%',
                pageSize: 30,
                parms: owner.gridOwnerParams,
                detail: {
                    onShowDetail: owner.listVehicle
                }
            });
        else {
            owner.gridOwner.changePage('first');
            owner.gridOwner.reload();
        }
    },
    create: function () {
        var url = '../owner/create.form';
        var op = {
            url: url,
            width: 850,
            height: 430,
            isHidden: false,
            title: '创建车主',
            onLoaded: function () {
                owner.dialog.frame.window.validateCreate();
                owner.dialog.frame.window.resetLigerui();
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
                            owner.gridOwner.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        owner.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../owner/edit.form';

        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 850,
            height: 430,
            isHidden: false,
            title: '修改车主',
            onLoaded: function () {
                owner.dialog.frame.window.validateEdit();
                owner.dialog.frame.window.resetLigerui();
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
                            owner.gridOwner.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        owner.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('将删除车主所有相关资料，且不可恢复，真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../owner/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    owner.gridOwner.reload();
            });
        });
    }
};
$(function () {
    if (console)
        console.log('车主管理');
    owner.query();
    $('#ownerFrame #btnQueryOwner').click(function () {
        if (!owner.gridOwner)
            return;
        owner.gridOwnerParams.filter = $('#ownerFrame #txtOwnerFilter').val();
        owner.query();
    });
    $('#ownerFrame #btnCreateOwner').click(function () {
        owner.create();
    });
    $('#ownerFrame #btnEditOwner').click(function () {
        if (!owner.gridOwner)
            return;

        var row = owner.gridOwner.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        owner.edit(row.id);
    });
    $('#ownerFrame #btnRemoveOwner').click(function () {
        if (!owner.gridOwner)
            return;

        var row = owner.gridOwner.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        owner.remove(row.id);
    });
});