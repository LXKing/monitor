/**
 * 兴趣点
 */
window.poi = {
    flagsCheckBoxList: null,
    gridPoi: null,
    gridPoiParams: {
        filter: ''
    },
    map: null,
    drawingManager: null,
    marker: null,
    overlayComplete: function (e) {
        poi.create(e.overlay);
    },
    clearOverlays: function () {
        if (poi.marker)
            poi.marker.disableDragging();
        if (poi.map)
            poi.map.mapObject.clearOverlays();
        if (poi.drawingManager)
            poi.drawingManager.close();
    },
    redrawOverlay: function (lat, lng, radius) {
        poi.clearOverlays();
        var point = new BMap.Point(lng, lat);
        poi.marker = new BMap.Marker(point, radius, {
            strokeColor: "blue",
            fillColor: "red",
            strokeWeight: 2,
            strokeOpacity: 0.6,
            fillOpacity: 0.2,
            strokeStyle: 'solid'
        });
        poi.map.mapObject.addOverlay(poi.marker);
        poi.map.mapObject.panTo(point);
    },
    onMapLoaded: function (map) {
        poi.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            poi.map.convertor(r.lng, r.lat, function (center) {
                poi.map.setCenter(center);
                poi.map.setZoom(r.zoom);
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
            poi.drawingManager = new BMapLib.DrawingManager(map.mapObject, {
                isOpen: false, // 是否开启绘制模式
                enableDrawingTool: true, // 是否显示工具栏
                drawingToolOptions: {
                    anchor: BMAP_ANCHOR_TOP_LEFT, // 位置
                    offset: new BMap.Size(150, 20), // 偏离值
                    drawingModes: [BMAP_DRAWING_MARKER]
                },
                markerOptions: styleOptions, // 圆的样式
                polylineOptions: styleOptions, // 线的样式
                polygonOptions: styleOptions, // 多边形的样式
                rectangleOptions: styleOptions
                // 矩形的样式
            });
            // 添加鼠标绘制工具监听事件，用于获取绘制结果
            poi.drawingManager.addEventListener('overlaycomplete', poi.overlayComplete);
        });
    },
    query: function () {
        if (!poi.gridPoi)
            poi.gridPoi = $("#poiFrame #gridPoi").ligerGrid({
                columns: [{
                    display: '名称',
                    name: 'name',
                    align: 'left',
                    frozen: true,
                    width: 200
                }, {
                    display: '类型',
                    name: 'type',
                    align: 'left',
                    width: 150
                }, {
                    display: '纬度',
                    name: 'lat',
                    align: 'left',
                    width: 150
                }, {
                    display: '经度',
                    name: 'lng',
                    align: 'left',
                    width: 150
                }, {
                    display: '说明',
                    name: 'remark',
                    align: 'left',
                    width: 'auto'
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../poi/query',
                width: '100%',
                height: 274,
                pageSize: 30,
                rownumbers: true,
                parms: poi.gridPoiParams,
                onSelectRow: function (row) {
                    poi.map.convertor(row.lng, row.lat, function (point, ctx) {
                        poi.redrawOverlay(point.lat, point.lng);
                    }, row);
                },
                onSuccess: function () {
                    poi.clearOverlays();
                }
            });
        else {
            poi.gridPoi.changePage('first');
            poi.gridPoi.reload();
        }
    },
    create: function (marker) {
        var url = '../poi/create.form';
        var op = {
            url: url,
            allowClose: false,
            width: 750,
            height: 260,
            isHidden: false,
            title: '创建兴趣点',
            onLoaded: function () {
                poi.dialog.frame.window.validateCreate();
                poi.dialog.frame.window.resetLigerui(marker);
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
                            poi.gridPoi.reload();
                        else
                            poi.dialog.frame.window.resetLigerui(marker);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    poi.map.mapObject.removeOverlay(marker);
                    dialog.close();
                }
            }]
        };
        poi.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../poi/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 260,
            isHidden: false,
            title: '修改兴趣点',
            onLoaded: function () {
                poi.dialog.frame.window.validateEdit();
                poi.dialog.frame.window.resetLigerui(poi.marker);
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
                            poi.gridPoi.reload();
                        else
                            poi.dialog.frame.window.resetLigerui(poi.marker);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        poi.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../poi/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    poi.gridPoi.reload();
            });
        });
    },
    resize: function () {
        var height = $(document).height();
        $('#poiFrame #divPoiAllMap').height(height - 300);
    }
};
$(function () {
    poi.resize();
    poi.query();
    webMap.events.onMapLoadCompleted['poiMap'] = poi.onMapLoaded;
    webMap.createMap('poiMap');
    $('#poiFrame #btnQueryPoi').click(function () {
        poi.gridPoiParams.filter = $('#poiFrame #txtPoiFilter').val();
        poi.query();
    });

    $('#poiFrame #btnEditPoi').click(function () {
        if (!poi.gridPoi)
            return;

        var row = poi.gridPoi.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        poi.edit(row.id);
    });
    $('#poiFrame #btnRemovePoi').click(function () {
        if (!poi.gridPoi)
            return;

        var row = poi.gridPoi.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        poi.remove(row.id);
    });
    $('#poiFrame #btnEditGraph').click(function () {
        if (!poi.gridPoi)
            return;

        var row = poi.gridPoi.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (poi.marker)
            poi.marker.enableDragging();
    });
    $('#poiFrame #btnApplyGraph').click(function () {
        if (!poi.gridPoi)
            return;

        var row = poi.gridPoi.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (!poi.marker)
            return;
        poi.marker.disableDragging();
        poi.edit(row.id);
    });
    $(window).resize(function () {
        poi.resize();
    });
});