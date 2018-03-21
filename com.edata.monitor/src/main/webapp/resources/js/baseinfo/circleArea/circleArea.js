/**
 * 圆形区域
 */
window.circleArea = {
    gridVehicles: {},
    flagsCheckBoxList: null,
    gridCircleArea: null,
    gridCircleAreaParams: {
        filter: ''
    },
    map: null,
    drawingManager: null,
    circle: null,
    overlayComplete: function (e) {
        circleArea.create(e.overlay);
    },
    clearOverlays: function () {
        if (circleArea.circle)
            circleArea.circle.disableEditing();
        if (circleArea.map)
            circleArea.map.mapObject.clearOverlays();
        if (circleArea.drawingManager)
            circleArea.drawingManager.close();
    },
    redrawCircle: function (lat, lng, radius) {
        circleArea.clearOverlays();
        var point = new BMap.Point(lng, lat);
        circleArea.circle = new BMap.Circle(point, radius, {
            strokeColor: "blue",
            fillColor: "red",
            strokeWeight: 2,
            strokeOpacity: 0.6,
            fillOpacity: 0.2,
            strokeStyle: 'solid'
        });
        circleArea.map.mapObject.addOverlay(circleArea.circle);
        circleArea.map.mapObject.panTo(point);
    },
    onMapLoaded: function (map) {
        circleArea.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            circleArea.map.convertor(r.lng, r.lat, function (center) {
                circleArea.map.setCenter(center);
                circleArea.map.setZoom(r.zoom);
            });
        });
        if ($('#create_auth').val() == 'false')
            return;
        $.getScript("http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js").done(function () {
            var styleOptions = {
                strokeColor: "blue", // 边线颜色。
                fillColor: "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
                strokeWeight: 2, // 边线的宽度，以像素为单位。
                strokeOpacity: 0.6, // 边线透明度，取值范围0 - 1。
                fillOpacity: 0.2, // 填充的透明度，取值范围0 - 1。
                strokeStyle: 'solid' // 边线的样式，solid或dashed。
            }
            // 实例化鼠标绘制工具
            circleArea.drawingManager = new BMapLib.DrawingManager(map.mapObject, {
                isOpen: false, // 是否开启绘制模式
                enableDrawingTool: true, // 是否显示工具栏
                drawingToolOptions: {
                    anchor: BMAP_ANCHOR_TOP_LEFT, // 位置
                    offset: new BMap.Size(150, 20), // 偏离值
                    drawingModes: [BMAP_DRAWING_CIRCLE]
                },
                circleOptions: styleOptions, // 圆的样式
                polylineOptions: styleOptions, // 线的样式
                polygonOptions: styleOptions, // 多边形的样式
                rectangleOptions: styleOptions
                // 矩形的样式
            });
            // 添加鼠标绘制工具监听事件，用于获取绘制结果
            circleArea.drawingManager.addEventListener('overlaycomplete', circleArea.overlayComplete);
        });
    },
    showDetail: function (row, detailPanel, callback) {
        var circleAreaId = row.id;
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
                    var html = '<a href="#" onclick="circleArea.removeVehicle(\'' + row.id + '\',\'' + rw.deviceNumber + '\')">删除</a>';
                    return html;
                }
            });
        }
        circleArea.gridVehicles[circleAreaId] = $(grid).css('margin', 5).ligerGrid({
            columns: columns,
            url: '../circleArea/vehicles',
            title: '车辆列表',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            parms: {
                circleAreaId: circleAreaId
            }
        });
    },
    addVehicle: function (circleAreaId) {
        circleArea.addVehicleParms = circleArea.addVehicleParms || {};
        circleArea.addVehicleParms.circleAreaId = circleAreaId;

        if (!circleArea.dialogSelectVehicle) {
            circleArea.gridVehicleInCircleAreaParms = {
                filter: ''
            };
            circleArea.dialogSelectVehicle = $.ligerDialog.open({
                title: '选择车辆',
                width: 800,
                height: 400,
                target: $("#dialogCircleAreaSelectVehicle")
            });
            circleArea.gridVehicleInCircleArea = $("#gridVehicleInCircleArea").ligerGrid({
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
                parms: circleArea.gridVehicleInCircleAreaParms
            });

            $('#dialogCircleAreaSelectVehicle #btnQueryVehicle').click(function () {
                circleArea.gridVehicleInCircleAreaParms.filter = $('#dialogCircleAreaSelectVehicle #txtVehicleFilter').val();
                circleArea.gridVehicleInCircleArea.reload();
            });

            $('#dialogCircleAreaSelectVehicle #btnSelectVehicle').click(function () {
                var rows = circleArea.gridVehicleInCircleArea.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                circleArea.dialogSelectVehicle.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].deviceNumber);
                }
                $.post('../circleArea/addVehicles', {
                    circleAreaId: circleArea.addVehicleParms.circleAreaId,
                    vehicles: JSON.stringify(list)
                }, function (data) {
                    var result = common.checkData(data, "", "添加车辆成功！");
                    if (result === true) {
                        if (circleArea.addVehicleParms.circleAreaId in circleArea.gridVehicles)
                            circleArea.gridVehicles[circleArea.addVehicleParms.circleAreaId].reload();
                    }
                });
            });
        } else
            circleArea.dialogSelectVehicle.show();
    },
    removeVehicle: function (circleAreaId, number) {
        circleArea.removeVehicleParms = circleArea.removeVehicleParms || {};
        circleArea.removeVehicleParms.circleAreaId = circleAreaId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../circleArea/removeVehicle', {
                circleAreaId: circleAreaId,
                number: number
            }, function (data) {
                var result = common.checkData(data, "", "删除车辆成功！");
                if (result === true) {
                    if (circleArea.removeVehicleParms.circleAreaId in circleArea.gridVehicles)
                        circleArea.gridVehicles[circleArea.removeVehicleParms.circleAreaId].reload();
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
            display: '中心点纬度',
            name: 'lat',
            align: 'left',
            width: 150
        }, {
            display: '中心点经度',
            name: 'lng',
            align: 'left',
            width: 150
        }, {
            display: '半径',
            name: 'radius',
            align: 'left',
            width: 150
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
            align: 'left',
            width: 100
        }];
        if ($('#addVehicles_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    var html = '<a href="#" onclick="circleArea.addVehicle(\'' + row.id + '\')">绑定车辆</a>';
                    return html;
                }
            });
        }

        if (!circleArea.gridCircleArea)
            circleArea.gridCircleArea = $("#circleAreaFrame #gridCircleArea").ligerGrid({
                columns: columns,
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../circleArea/query',
                width: '100%',
                height: 274,
                pageSize: 30,
                parms: circleArea.gridCircleAreaParams,
                onSelectRow: function (row) {
                    circleArea.map.convertor(row.lng, row.lat, function (point, ctx) {
                        circleArea.redrawCircle(point.lat, point.lng, ctx.radius);
                    }, row);
                    // var point = common.converter.gcj_encrypt(row.lat,
                    // row.lng);
                    // point = common.converter.bd_encrypt(point.lat,
                    // point.lng);
                    // circleArea.redrawCircle(point.lat, point.lng,
                    // row.radius);
                },
                onSuccess: function () {
                    circleArea.clearOverlays();
                },
                detail: {
                    onShowDetail: circleArea.showDetail,
                    height: 'auto'
                }
            });
        else {
            circleArea.gridCircleArea.changePage('first');
            circleArea.gridCircleArea.reload();
        }
    },
    create: function (circle) {
        var url = '../circleArea/create.form';
        var op = {
            allowClose: false,
            url: url,
            width: 750,
            height: 500,
            isHidden: false,
            title: '创建圆形区域',
            onLoaded: function () {
                circleArea.dialog.frame.window.validateCreate();
                circleArea.dialog.frame.window.resetLigerui(circle);
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
                            circleArea.gridCircleArea.reload();
                        else
                            dialog.frame.window.resetLigerui(circle);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    circleArea.map.mapObject.removeOverlay(circle);
                    dialog.close();
                }
            }]
        };
        circleArea.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../circleArea/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '修改圆形区域',
            onLoaded: function () {
                circleArea.dialog.frame.window.validateEdit();
                circleArea.dialog.frame.window.resetLigerui(circleArea.circle);
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
                            circleArea.gridCircleArea.reload();
                        else
                            dialog.frame.window.resetLigerui(circleArea.circle);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        circleArea.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../circleArea/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    circleArea.gridCircleArea.reload();
            });
        });
    },
    resize: function () {
        var height = $(document).height();
        $('#circleAreaFrame #divCircleAreaAllMap').height(height - 300);
    }
};
$(function () {
    circleArea.resize();
    circleArea.query();
    webMap.events.onMapLoadCompleted['circleAreaMap'] = circleArea.onMapLoaded;
    webMap.createMap('circleAreaMap');

    $('#circleAreaFrame #btnQueryCircleArea').click(function () {
        circleArea.gridCircleAreaParams.filter = $('#circleAreaFrame #txtCircleAreaFilter').val();
        circleArea.query();
    });

    $('#circleAreaFrame #btnEditCircleArea').click(function () {
        if (!circleArea.gridCircleArea)
            return;

        var row = circleArea.gridCircleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        circleArea.edit(row.id);
    });
    $('#circleAreaFrame #btnRemoveCircleArea').click(function () {
        if (!circleArea.gridCircleArea)
            return;

        var row = circleArea.gridCircleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        circleArea.remove(row.id);
    });
    $('#circleAreaFrame #btnEditGraph').click(function () {
        if (!circleArea.gridCircleArea)
            return;

        var row = circleArea.gridCircleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (circleArea.circle)
            circleArea.circle.enableEditing();
    });
    $('#circleAreaFrame #btnApplyGraph').click(function () {
        if (!circleArea.gridCircleArea)
            return;

        var row = circleArea.gridCircleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (!circleArea.circle)
            return;
        circleArea.circle.disableEditing();
        circleArea.edit(row.id);
    });

    $(window).resize(function () {
        circleArea.resize();
    });
});