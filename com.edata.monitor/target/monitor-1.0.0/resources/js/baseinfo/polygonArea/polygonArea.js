/**
 * 多边形区域
 */
window.polygonArea = {
    gridVehicles: {},
    flagsCheckBoxList: null,
    gridPolygonArea: null,
    gridPolygonAreaParams: {
        filter: ''
    },
    map: null,
    drawingManager: null,
    polygen: null,
    overlayComplete: function (e) {
        polygonArea.create(e.overlay);
    },
    clearOverlays: function () {
        if (polygonArea.polygen)
            polygonArea.polygen.disableEditing();
        if (polygonArea.map)
            polygonArea.map.mapObject.clearOverlays();
        if (polygonArea.drawingManager)
            polygonArea.drawingManager.close();
    },
    redrawOverlay: function (path) {
        polygonArea.clearOverlays();
        polygonArea.polygen = new BMap.Polygon(path, {
            strokeColor: "blue",
            fillColor: "red",
            strokeWeight: 2,
            strokeOpacity: 0.6,
            fillOpacity: 0.2,
            strokeStyle: 'solid'
        });
        polygonArea.map.mapObject.addOverlay(polygonArea.polygen);
        polygonArea.map.mapObject.panTo(polygonArea.polygen.getBounds().getCenter());
    },
    onMapLoaded: function (map) {
        polygonArea.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            polygonArea.map.convertor(r.lng, r.lat, function (center) {
                polygonArea.map.setCenter(center);
                polygonArea.map.setZoom(r.zoom);
            });
        });
        if ($('#create_auth').val() == 'false')
            return;
        $.getScript("http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js").done(function () {
            var styleOptions = {
                strokeColor: "blue", // 边线颜色。
                fillColor: "red", // 填充颜色。当参数为空时，多边形将没有填充效果。
                strokeWeight: 2, // 边线的宽度，以像素为单位。
                strokeOpacity: 0.6, // 边线透明度，取值范围0 - 1。
                fillOpacity: 0.2, // 填充的透明度，取值范围0 - 1。
                strokeStyle: 'solid' // 边线的样式，solid或dashed。
            }
            // 实例化鼠标绘制工具
            polygonArea.drawingManager = new BMapLib.DrawingManager(map.mapObject, {
                isOpen: false, // 是否开启绘制模式
                enableDrawingTool: true, // 是否显示工具栏
                drawingToolOptions: {
                    anchor: BMAP_ANCHOR_TOP_LEFT, // 位置
                    offset: new BMap.Size(150, 20), // 偏离值
                    drawingModes: [BMAP_DRAWING_POLYGON]
                },
                polygenOptions: styleOptions, // 圆的样式
                polylineOptions: styleOptions, // 线的样式
                polygonOptions: styleOptions, // 多边形的样式
                rectangleOptions: styleOptions
                // 矩形的样式
            });
            // 添加鼠标绘制工具监听事件，用于获取绘制结果
            polygonArea.drawingManager.addEventListener('overlaycomplete', polygonArea.overlayComplete);
        });
    },
    showDetail: function (row, detailPanel, callback) {
        var polygonAreaId = row.id;
        var grid = document.createElement('div');
        $(detailPanel).append(grid);
        var columns = [{
            display: '车牌号',
            name: 'plateNumber',
            align: 'left',
            width: 150,
            frozen: true
        }, {
            display: '设备号',
            name: 'deviceNumber',
            align: 'left',
            width: 150
        }, {
            display: '绑定时间',
            name: 'time',
            align: 'left',
            type: 'date',
            width: 150
        }];
        if ($('#removeVehicle_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="polygonArea.removeVehicle(\'' + row.id + '\',\'' + rw.deviceNumber + '\')">删除</a>';
                    return html;
                }
            });
        }
        polygonArea.gridVehicles[polygonAreaId] = $(grid).css('margin', 5).ligerGrid({
            columns: columns,
            url: '../polygonArea/vehicles',
            title: '车辆列表',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            parms: {
                polygonAreaId: polygonAreaId
            }
        });
    },
    addVehicle: function (polygonAreaId) {
        polygonArea.addVehicleParms = polygonArea.addVehicleParms || {};
        polygonArea.addVehicleParms.polygonAreaId = polygonAreaId;

        if (!polygonArea.dialogSelectVehicle) {
            polygonArea.gridVehicleInPolygonAreaParms = {
                filter: ''
            };
            polygonArea.dialogSelectVehicle = $.ligerDialog.open({
                title: '选择车辆',
                width: 800,
                height: 400,
                target: $("#dialogPolygonAreaSelectVehicle")
            });
            polygonArea.gridVehicleInPolygonArea = $("#gridVehicleInPolygonArea").ligerGrid({
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
                parms: polygonArea.gridVehicleInPolygonAreaParms
            });

            $('#dialogPolygonAreaSelectVehicle #btnQueryVehicle').click(function () {
                polygonArea.gridVehicleInPolygonAreaParms.filter = $('#dialogPolygonAreaSelectVehicle #txtVehicleFilter').val();
                polygonArea.gridVehicleInPolygonArea.reload();
            });

            $('#dialogPolygonAreaSelectVehicle #btnSelectVehicle').click(function () {
                var rows = polygonArea.gridVehicleInPolygonArea.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                polygonArea.dialogSelectVehicle.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].deviceNumber);
                }
                $.post('../polygonArea/addVehicles', {
                    polygonAreaId: polygonArea.addVehicleParms.polygonAreaId,
                    vehicles: JSON.stringify(list)
                }, function (data) {
                    var result = common.checkData(data, "", "添加车辆成功！");
                    if (result === true) {
                        if (polygonArea.addVehicleParms.polygonAreaId in polygonArea.gridVehicles)
                            polygonArea.gridVehicles[polygonArea.addVehicleParms.polygonAreaId].reload();
                    }
                });
            });
        } else
            polygonArea.dialogSelectVehicle.show();
    },
    removeVehicle: function (polygonAreaId, number) {
        polygonArea.removeVehicleParms = polygonArea.removeVehicleParms || {};
        polygonArea.removeVehicleParms.polygonAreaId = polygonAreaId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../polygonArea/removeVehicle', {
                polygonAreaId: polygonAreaId,
                number: number
            }, function (data) {
                var result = common.checkData(data, "", "删除车辆成功！");
                if (result === true) {
                    if (polygonArea.removeVehicleParms.polygonAreaId in polygonArea.gridVehicles)
                        polygonArea.gridVehicles[polygonArea.removeVehicleParms.polygonAreaId].reload();
                }
            });
        });
    },
    query: function () {
        var columns = [{
            display: '名称',
            name: 'name',
            align: 'left',
            width: 200
        }, {
            display: '终端计算否',
            name: 'deviceCatch',
            width: 80,
            render: function (row) {
                if (row.deviceCatch === true)
                    return '是';
                else if (row.deviceCatch === false)
                    return '否';
                return '';
            }
        }, {
            display: '说明',
            name: 'remark',
            align: 'left'
        }];
        if ($('#addVehicles_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 80,
                render: function (row) {
                    var html = '<a href="#" onclick="polygonArea.addVehicle(\'' + row.id + '\')">绑定车辆</a>';
                    return html;
                }
            });
        }
        if (!polygonArea.gridPolygonArea)
            polygonArea.gridPolygonArea = $("#polygonAreaFrame #gridPolygonArea").ligerGrid({
                columns: columns,
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../polygonArea/query',
                width: '100%',
                height: 274,
                pageSize: 30,
                parms: polygonArea.gridPolygonAreaParams,
                onSelectRow: function (row) {
                    polygonArea.map.translate(row.points, 0, function (points) {
                        var path = [];
                        for (var y = 0; y < points.length; y++) {
                            var p = points[y];
                            path.push({
                                lng: p.olng,
                                lat: p.olat
                            });
                        }
                        polygonArea.redrawOverlay(path);
                    });
                },
                onSuccess: function () {
                    polygonArea.clearOverlays();
                },
                detail: {
                    onShowDetail: polygonArea.showDetail,
                    height: 'auto'
                }
            });
        else {
            polygonArea.gridPolygonArea.changePage('first');
            polygonArea.gridPolygonArea.reload();
        }
    },
    create: function (polygen) {
        var url = '../polygonArea/create.form';
        var op = {
            url: url,
            allowClose: false,
            width: 750,
            height: 500,
            isHidden: false,
            title: '创建多边形区域',
            onLoaded: function () {
                polygonArea.dialog.frame.window.validateCreate();
                polygonArea.dialog.frame.window.resetLigerui(polygen);
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    dialog.frame.window.resetFlag();
                    if (dialog.frame.window.validateCreate() == false) {
                        return;
                    }

                    var form = $('form', dialog.frame.document);
                    var formData = form.serialize();
                    $.post(url, formData, function (data) {
                        var result = common.form.save(dialog, data, op);
                        if (result === true)
                            polygonArea.gridPolygonArea.reload();
                        else
                            dialog.frame.window.resetLigerui(polygen);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    polygonArea.map.mapObject.removeOverlay(polygen);
                    dialog.close();
                }
            }]
        };
        polygonArea.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../polygonArea/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '修改多边形区域',
            onLoaded: function () {
                polygonArea.dialog.frame.window.validateEdit();
                polygonArea.dialog.frame.window.resetLigerui(polygonArea.polygen);
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    dialog.frame.window.resetFlag();
                    if (dialog.frame.window.validateEdit() == false) {
                        return;
                    }

                    var form = $('form', dialog.frame.document);
                    var formData = form.serialize();
                    $.post(url, formData, function (data) {
                        var result = common.form.save(dialog, data, op);
                        if (result === true)
                            polygonArea.gridPolygonArea.reload();
                        else
                            dialog.frame.window.resetLigerui(polygonArea.polygen);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        polygonArea.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../polygonArea/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    polygonArea.gridPolygonArea.reload();
            });
        });
    },
    resize: function () {
        var height = $(document).height();
        $('#polygonAreaFrame #divPolygonAreaAllMap').height(height - 300);
    }
};
$(function () {
    polygonArea.resize();
    polygonArea.query();
    webMap.events.onMapLoadCompleted['polygonAreaMap'] = polygonArea.onMapLoaded;
    webMap.createMap('polygonAreaMap');
    $('#polygonAreaFrame #btnQueryPolygonArea').click(function () {
        polygonArea.gridPolygonAreaParams.filter = $('#polygonAreaFrame #txtPolygonAreaFilter').val();
        polygonArea.query();
    });

    $('#polygonAreaFrame #btnEditPolygonArea').click(function () {
        if (!polygonArea.gridPolygonArea)
            return;

        var row = polygonArea.gridPolygonArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        polygonArea.edit(row.id);
    });
    $('#polygonAreaFrame #btnRemovePolygonArea').click(function () {
        if (!polygonArea.gridPolygonArea)
            return;

        var row = polygonArea.gridPolygonArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        polygonArea.remove(row.id);
    });
    $('#polygonAreaFrame #btnEditGraph').click(function () {
        if (!polygonArea.gridPolygonArea)
            return;

        var row = polygonArea.gridPolygonArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (polygonArea.polygen)
            polygonArea.polygen.enableEditing();
    });
    $('#polygonAreaFrame #btnApplyGraph').click(function () {
        if (!polygonArea.gridPolygonArea)
            return;

        var row = polygonArea.gridPolygonArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (!polygonArea.polygen)
            return;
        polygonArea.polygen.disableEditing();
        polygonArea.edit(row.id);
    });
    $(window).resize(function () {
        polygonArea.resize();
    });
});