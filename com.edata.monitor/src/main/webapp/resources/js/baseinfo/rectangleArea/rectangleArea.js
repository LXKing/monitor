/**
 * 矩形区域
 */
window.rectangleArea = {
    gridVehicles: {},
    flagsCheckBoxList: null,
    gridRectangleArea: null,
    gridRectangleAreaParams: {
        filter: ''
    },
    map: null,
    drawingManager: null,
    rectangle: null,
    overlayComplete: function (e) {
        rectangleArea.create(e.overlay);
    },
    clearOverlays: function () {
        if (rectangleArea.rectangle)
            rectangleArea.rectangle.disableEditing();
        if (rectangleArea.map)
            rectangleArea.map.mapObject.clearOverlays();
        if (rectangleArea.drawingManager)
            rectangleArea.drawingManager.close();
    },
    redrawRectangle: function (ullat, ullng, brlat, brlng) {
        rectangleArea.clearOverlays();
        var points = [{
            lng: ullng,
            lat: ullat
        }, {
            lng: brlng,
            lat: ullat
        }, {
            lng: brlng,
            lat: brlat
        }, {
            lng: ullng,
            lat: brlat
        }];
        rectangleArea.map.translate(points, 0, function (list, ctx) {
            var wn = new BMap.Point(list[0].olng, list[0].olat);
            var ne = new BMap.Point(list[1].olng, list[1].olat);
            var ew = new BMap.Point(list[2].olng, list[2].olat);
            var sw = new BMap.Point(list[3].olng, list[3].olat);
            var path = [];
            path.push(wn);
            path.push(ne);
            path.push(ew);
            path.push(sw);

            rectangleArea.rectangle = new BMap.Polygon(path, {
                strokeColor: "blue",
                fillColor: "red",
                strokeWeight: 2,
                strokeOpacity: 0.6,
                fillOpacity: 0.2,
                strokeStyle: 'solid'
            });
            rectangleArea.map.mapObject.addOverlay(rectangleArea.rectangle);
            rectangleArea.map.mapObject.panTo(rectangleArea.rectangle.getBounds().getCenter());
        });
    },
    onMapLoaded: function (map) {
        rectangleArea.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            rectangleArea.map.convertor(r.lng, r.lat, function (center) {
                rectangleArea.map.setCenter(center);
                rectangleArea.map.setZoom(r.zoom);
            });
        });
        if ($('#create_auth').val() == 'false')
            return;
        $.getScript("http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js").done(function () {
            var styleOptions = {
                strokeColor: "blue", // 边线颜色。
                fillColor: "red", // 填充颜色。当参数为空时，矩形将没有填充效果。
                strokeWeight: 2, // 边线的宽度，以像素为单位。
                strokeOpacity: 0.6, // 边线透明度，取值范围0 - 1。
                fillOpacity: 0.2, // 填充的透明度，取值范围0 - 1。
                strokeStyle: 'solid' // 边线的样式，solid或dashed。
            }
            // 实例化鼠标绘制工具
            rectangleArea.drawingManager = new BMapLib.DrawingManager(rectangleArea.map.mapObject, {
                isOpen: false, // 是否开启绘制模式
                enableDrawingTool: true, // 是否显示工具栏
                drawingToolOptions: {
                    anchor: BMAP_ANCHOR_TOP_LEFT, // 位置
                    offset: new BMap.Size(150, 20), // 偏离值
                    drawingModes: [BMAP_DRAWING_RECTANGLE]
                },
                rectangleOptions: styleOptions, // 圆的样式
                polylineOptions: styleOptions, // 线的样式
                polygonOptions: styleOptions, // 多边形的样式
                rectangleOptions: styleOptions
                // 矩形的样式
            });
            // 添加鼠标绘制工具监听事件，用于获取绘制结果
            rectangleArea.drawingManager.addEventListener('overlaycomplete', rectangleArea.overlayComplete);
        });
    },
    showDetail: function (row, detailPanel, callback) {
        var rectangleAreaId = row.id;
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
                    var html = '<a href="#" onclick="rectangleArea.removeVehicle(\'' + row.id + '\',\'' + rw.deviceNumber + '\')">删除</a>';
                    return html;
                }
            });
        }
        rectangleArea.gridVehicles[rectangleAreaId] = $(grid).css('margin', 5).ligerGrid({
            columns: columns,
            url: '../rectangleArea/vehicles',
            title: '车辆列表',
            showTitle: true,
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            pageParmName: 'pageIndex',
            pagesizeParmName: 'pageSize',
            parms: {
                rectangleAreaId: rectangleAreaId
            }
        });
    },
    addVehicle: function (rectangleAreaId) {
        rectangleArea.addVehicleParms = rectangleArea.addVehicleParms || {};
        rectangleArea.addVehicleParms.rectangleAreaId = rectangleAreaId;

        if (!rectangleArea.dialogSelectVehicle) {
            rectangleArea.gridVehicleInRectangleAreaParms = {
                filter: ''
            };
            rectangleArea.dialogSelectVehicle = $.ligerDialog.open({
                title: '选择车辆',
                width: 800,
                height: 400,
                target: $("#dialogRectangleAreaSelectVehicle")
            });
            rectangleArea.gridVehicleInRectangleArea = $("#gridVehicleInRectangleArea").ligerGrid({
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
                parms: rectangleArea.gridVehicleInRectangleAreaParms
            });

            $('#dialogRectangleAreaSelectVehicle #btnQueryVehicle').click(function () {
                rectangleArea.gridVehicleInRectangleAreaParms.filter = $('#dialogRectangleAreaSelectVehicle #txtVehicleFilter').val();
                rectangleArea.gridVehicleInRectangleArea.reload();
            });

            $('#dialogRectangleAreaSelectVehicle #btnSelectVehicle').click(function () {
                var rows = rectangleArea.gridVehicleInRectangleArea.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                rectangleArea.dialogSelectVehicle.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].deviceNumber);
                }
                $.post('../rectangleArea/addVehicles', {
                    rectangleAreaId: rectangleArea.addVehicleParms.rectangleAreaId,
                    vehicles: JSON.stringify(list)
                }, function (data) {
                    var result = common.checkData(data, "", "添加车辆成功！");
                    if (result === true) {
                        if (rectangleArea.addVehicleParms.rectangleAreaId in rectangleArea.gridVehicles)
                            rectangleArea.gridVehicles[rectangleArea.addVehicleParms.rectangleAreaId].reload();
                    }
                });
            });
        } else
            rectangleArea.dialogSelectVehicle.show();
    },
    removeVehicle: function (rectangleAreaId, number) {
        rectangleArea.removeVehicleParms = rectangleArea.removeVehicleParms || {};
        rectangleArea.removeVehicleParms.rectangleAreaId = rectangleAreaId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../rectangleArea/removeVehicle', {
                rectangleAreaId: rectangleAreaId,
                number: number
            }, function (data) {
                var result = common.checkData(data, "", "删除车辆成功！");
                if (result === true) {
                    if (rectangleArea.removeVehicleParms.rectangleAreaId in rectangleArea.gridVehicles)
                        rectangleArea.gridVehicles[rectangleArea.removeVehicleParms.rectangleAreaId].reload();
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
            display: '左上点纬度',
            name: 'ullat',
            align: 'left',
            width: 150
        }, {
            display: '左上点经度',
            name: 'ullng',
            align: 'left',
            width: 150
        }, {
            display: '右下点纬度',
            name: 'brlat',
            align: 'left',
            width: 150
        }, {
            display: '右下点经度',
            name: 'brlng',
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
                    var html = '<a href="#" onclick="rectangleArea.addVehicle(\'' + row.id + '\')">绑定车辆</a>';
                    return html;
                }
            });
        }
        if (!rectangleArea.gridRectangleArea)
            rectangleArea.gridRectangleArea = $("#rectangleAreaFrame #gridRectangleArea").ligerGrid({
                columns: columns,
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../rectangleArea/query',
                width: '100%',
                height: 274,
                pageSize: 30,
                parms: rectangleArea.gridRectangleAreaParams,
                onSelectRow: function (row) {
                    rectangleArea.redrawRectangle(row.ullat, row.ullng, row.brlat, row.brlng);
                },
                onSuccess: function () {
                    rectangleArea.clearOverlays();
                },
                detail: {
                    onShowDetail: rectangleArea.showDetail,
                    height: 'auto'
                }
            });
        else {
            rectangleArea.gridRectangleArea.changePage('first');
            rectangleArea.gridRectangleArea.reload();
        }
    },
    create: function (rectangle) {
        var url = '../rectangleArea/create.form';
        var op = {
            url: url,
            allowClose: false,
            width: 750,
            height: 500,
            isHidden: false,
            title: '创建矩形区域',
            onLoaded: function () {
                rectangleArea.dialog.frame.window.validateCreate();
                rectangleArea.dialog.frame.window.resetLigerui(rectangle);
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
                            rectangleArea.gridRectangleArea.reload();
                        else
                            dialog.frame.window.resetLigerui(rectangle);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    rectangleArea.map.mapObject.removeOverlay(rectangle);
                    dialog.close();
                }
            }]
        };
        rectangleArea.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../rectangleArea/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 500,
            isHidden: false,
            title: '修改矩形区域',
            onLoaded: function () {
                rectangleArea.dialog.frame.window.validateEdit();
                rectangleArea.dialog.frame.window.resetLigerui(rectangleArea.rectangle);
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
                            rectangleArea.gridRectangleArea.reload();
                        else
                            dialog.frame.window.resetLigerui(rectangleArea.rectangle);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        rectangleArea.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../rectangleArea/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    rectangleArea.gridRectangleArea.reload();
            });
        });
    },
    resize: function () {
        var height = $(document).height();
        $('#rectangleAreaFrame #divRectangleAreaAllmap').height(height - 300);
    }
};
$(function () {
    rectangleArea.resize();
    rectangleArea.query();
    webMap.events.onMapLoadCompleted["rectangleAreaMap"] = rectangleArea.onMapLoaded;
    webMap.createMap("rectangleAreaMap");

    $('#rectangleAreaFrame #btnQueryRectangleArea').click(function () {
        rectangleArea.gridRectangleAreaParams.filter = $('#rectangleAreaFrame #txtRectangleAreaFilter').val();
        rectangleArea.query();
    });

    $('#rectangleAreaFrame #btnEditRectangleArea').click(function () {
        if (!rectangleArea.gridRectangleArea)
            return;

        var row = rectangleArea.gridRectangleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        rectangleArea.edit(row.id);
    });
    $('#rectangleAreaFrame #btnRemoveRectangleArea').click(function () {
        if (!rectangleArea.gridRectangleArea)
            return;

        var row = rectangleArea.gridRectangleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        rectangleArea.remove(row.id);
    });
    $('#rectangleAreaFrame #btnEditGraph').click(function () {
        if (!rectangleArea.gridRectangleArea)
            return;

        var row = rectangleArea.gridRectangleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (rectangleArea.rectangle)
            rectangleArea.rectangle.enableEditing();
    });
    $('#rectangleAreaFrame #btnApplyGraph').click(function () {
        if (!rectangleArea.gridRectangleArea)
            return;

        var row = rectangleArea.gridRectangleArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (!rectangleArea.rectangle)
            return;
        rectangleArea.rectangle.disableEditing();
        rectangleArea.edit(row.id);
    });
    $(window).resize(function () {
        rectangleArea.resize();
    });
});