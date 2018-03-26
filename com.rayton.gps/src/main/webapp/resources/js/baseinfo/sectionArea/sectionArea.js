/**
 * 路段区域
 */
window.sectionArea = {
    flagsCheckBoxList: null,
    gridSectionArea: null,
    gridSectionAreaParams: {
        filter: ''
    },
    map: null,
    drawingManager: null,
    polyline: null,
    overlayComplete: function (e) {
        sectionArea.create(e.overlay);
    },
    clearOverlays: function () {
        if (sectionArea.polyline)
            sectionArea.polyline.disableEditing();
        if (sectionArea.map)
            sectionArea.map.mapObject.clearOverlays();
        if (sectionArea.drawingManager)
            sectionArea.drawingManager.close();
    },
    redrawOverlay: function (points) {
        sectionArea.clearOverlays();
        var list = [];
        for (var x = 0; x < points.length; x++) {
            var item = points[x];
            var point = new BMap.Point(item.lng, item.lat);
            point.data = item;
            list.push(point);
        }
        sectionArea.polyline = new BMap.Polyline(list, {
            strokeColor: "red", // 折线颜色
            strokeWeight: 5, // 折线的宽度，以像素为单位。
            strokeOpacity: 0.6, // 折线的透明度，取值范围0 - 1。
            strokeStyle: 'solid' // 折线的样式，solid或dashed。
        });
        sectionArea.map.mapObject.addOverlay(sectionArea.polyline);
        sectionArea.map.mapObject.setViewport(list);
    },
    onMapLoaded: function (map) {
        sectionArea.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            sectionArea.map.convertor(r.lng, r.lat, function (center) {
                sectionArea.map.setCenter(center);
                sectionArea.map.setZoom(r.zoom);
            });
        });
        if ($('#create_auth').val() == 'false')
            return;
        $.getScript("http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js").done(function () {
            var styleOptions = {
                strokeColor: "red", // 折线颜色
                strokeWeight: 5, // 折线的宽度，以像素为单位。
                strokeOpacity: 0.6, // 折线的透明度，取值范围0 - 1。
                strokeStyle: 'solid' // 折线的样式，solid或dashed。
            }
            // 实例化鼠标绘制工具
            sectionArea.drawingManager = new BMapLib.DrawingManager(map.mapObject, {
                isOpen: false, // 是否开启绘制模式
                enableDrawingTool: true, // 是否显示工具栏
                drawingToolOptions: {
                    anchor: BMAP_ANCHOR_TOP_LEFT, // 位置
                    offset: new BMap.Size(150, 20), // 偏离值
                    drawingModes: [BMAP_DRAWING_POLYLINE]
                },
                polylineOptions: styleOptions, // 圆的样式
                polylineOptions: styleOptions, // 线的样式
                polygonOptions: styleOptions, // 路线的样式
                rectangleOptions: styleOptions
                // 矩形的样式
            });
            // 添加鼠标绘制工具监听事件，用于获取绘制结果
            sectionArea.drawingManager.addEventListener('overlaycomplete', sectionArea.overlayComplete);
        });
    },
    query: function () {
        if (!sectionArea.gridSectionArea)
            sectionArea.gridSectionArea = $("#sectionAreaFrame #gridSectionArea").ligerGrid({
                columns: [{
                    display: '名称',
                    name: 'name',
                    align: 'left',
                    width: 200
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
                url: '../sectionArea/query',
                width: '100%',
                height: 274,
                pageSize: 30,
                rownumbers: true,
                parms: sectionArea.gridSectionAreaParams,
                onSelectRow: function (row) {
                    sectionArea.map.translate(row.points, 0, function (points) {
                        var path = [];
                        for (var y = 0; y < points.length; y++) {
                            var p = points[y];
                            path.push({
                                lng: p.olng,
                                lat: p.olat
                            });
                        }
                        sectionArea.redrawOverlay(path);
                    });
                },
                onSuccess: function () {
                    sectionArea.clearOverlays();
                }
            });
        else {
            sectionArea.gridSectionArea.changePage('first');
            sectionArea.gridSectionArea.reload();
        }
    },
    create: function (polyline) {
        var url = '../sectionArea/create.form';
        var op = {
            url: url,
            allowClose: false,
            width: 750,
            height: 350,
            isHidden: false,
            title: '创建路段区域',
            onLoaded: function () {
                sectionArea.dialog.frame.window.validateCreate();
                sectionArea.dialog.frame.window.resetLigerui(polyline);
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
                            sectionArea.gridSectionArea.reload();
                        else
                            dialog.frame.window.resetLigerui(polyline);
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    sectionArea.map.mapObject.removeOverlay(polyline);
                    dialog.close();
                }
            }]
        };
        sectionArea.dialog = $.ligerDialog.open(op);
    },
    edit: function (id) {
        var url = '../sectionArea/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 350,
            isHidden: false,
            title: '修改路段区域',
            onLoaded: function () {
                sectionArea.dialog.frame.window.validateEdit();
                sectionArea.dialog.frame.window.resetLigerui(sectionArea.polyline);
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
                            sectionArea.gridSectionArea.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        sectionArea.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../sectionArea/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    sectionArea.gridSectionArea.reload();
            });
        });
    },
    resize: function () {
        var height = $(document).height();
        $('#sectionAreaFrame #divSectionAreaAllMap').height(height - 300);
    }
};
$(function () {
    sectionArea.resize();
    sectionArea.query();
    webMap.events.onMapLoadCompleted['sectionAreaMap'] = sectionArea.onMapLoaded;
    webMap.createMap('sectionAreaMap');
    $('#sectionAreaFrame #btnQuerySectionArea').click(function () {
        sectionArea.gridSectionAreaParams.filter = $('#sectionAreaFrame #txtSectionAreaFilter').val();
        sectionArea.query();
    });

    $('#sectionAreaFrame #btnEditSectionArea').click(function () {
        if (!sectionArea.gridSectionArea)
            return;

        var row = sectionArea.gridSectionArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        sectionArea.edit(row.id);
    });
    $('#sectionAreaFrame #btnRemoveSectionArea').click(function () {
        if (!sectionArea.gridSectionArea)
            return;

        var row = sectionArea.gridSectionArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        sectionArea.remove(row.id);
    });
    $('#sectionAreaFrame #btnEditGraph').click(function () {
        if (!sectionArea.gridSectionArea)
            return;

        var row = sectionArea.gridSectionArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (!sectionArea.polyline)
            return;
        sectionArea.polyline.enableEditing();
    });
    $('#sectionAreaFrame #btnApplyGraph').click(function () {
        if (!sectionArea.gridSectionArea)
            return;

        var row = sectionArea.gridSectionArea.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        if (!sectionArea.polyline)
            return;
        sectionArea.polyline.disableEditing();
        sectionArea.edit(row.id);
    });
    $(window).resize(function () {
        sectionArea.resize();
    });
});