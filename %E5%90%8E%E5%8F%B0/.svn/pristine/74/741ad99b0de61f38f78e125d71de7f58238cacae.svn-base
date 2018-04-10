/**
 * 地图图层
 */
window.mapLayer = {
    gridCircleArea: {},
    gridRectangleArea: {},
    gridPolygonArea: {},
    gridRouteArea: {},
    gridPoi: {},
    gridMapLayer: null,
    gridMapLayerParams: {
        filter: ''
    },
    detailRows: {},
    map: null,
    clearOverlays: function () {
        if (mapLayer.map)
            mapLayer.map.mapObject.clearOverlays();
    },
    currentMapLayer: null,
    redrawOverlay: function (mapLayerInfo) {
        mapLayer.currentMapLayer = mapLayerInfo;
        mapLayer.clearOverlays();
        // 画圆
        var circles = mapLayerInfo.circleAreas;
        if (circles) {
            for (var x = 0; x < circles.length; x++) {
                var circle = circles[x];
                mapLayer.map.convertor(circle.lng, circle.lat, function (p, ctx) {
                    if (mapLayer.currentMapLayer != ctx.info)
                        return;
                    var center = new BMap.Point(p.lng, p.lat);
                    var circleArea = new BMap.Circle(center, ctx.area.radius, {
                        strokeColor: "blue",
                        fillColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 0.6,
                        fillOpacity: 0.2,
                        strokeStyle: 'solid'
                    });
                    mapLayer.map.mapObject.addOverlay(circleArea);

                    var label = new BMap.Label(ctx.area.name, {
                        position: center
                    })
                    mapLayer.map.mapObject.addOverlay(label);
                }, {
                    area: circle,
                    info: mapLayerInfo
                });
            }
        }
        // 画矩形
        var rectangles = mapLayerInfo.rectangleAreas;
        if (rectangles) {
            for (var x = 0; x < rectangles.length; x++) {
                var rectangle = rectangles[x];
                var points = [{
                    lng: rectangle.ullng,
                    lat: rectangle.ullat
                }, {
                    lng: rectangle.brlng,
                    lat: rectangle.ullat
                }, {
                    lng: rectangle.brlng,
                    lat: rectangle.brlat
                }, {
                    lng: rectangle.ullng,
                    lat: rectangle.brlat
                }];
                mapLayer.map.translate(points, 0, function (list, ctx) {
                    if (mapLayer.currentMapLayer != ctx.info)
                        return;
                    var wn = new BMap.Point(list[0].olng, list[0].olat);
                    var ne = new BMap.Point(list[1].olng, list[1].olat);
                    var ew = new BMap.Point(list[2].olng, list[2].olat);
                    var sw = new BMap.Point(list[3].olng, list[3].olat);
                    var path = [];
                    path.push(wn);
                    path.push(ne);
                    path.push(ew);
                    path.push(sw);
                    var rectangleArea = new BMap.Polygon(path, {
                        strokeColor: "blue",
                        fillColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 0.6,
                        fillOpacity: 0.2,
                        strokeStyle: 'solid'
                    });
                    mapLayer.map.mapObject.addOverlay(rectangleArea);

                    var label = new BMap.Label(ctx.area.name, {
                        position: rectangleArea.getBounds().getCenter()
                    })
                    mapLayer.map.mapObject.addOverlay(label);
                }, {
                    area: rectangle,
                    info: mapLayerInfo
                });
            }
        }
        // 画多边形
        var polygons = mapLayerInfo.polygonAreas;
        if (polygons) {
            for (var x = 0; x < polygons.length; x++) {
                var polygon = polygons[x];
                mapLayer.map.translate(polygon.points, 0, function (list, ctx) {
                    if (mapLayer.currentMapLayer != ctx.info)
                        return;
                    var path = [];
                    for (var y = 0; y < list.length; y++) {
                        var p = list[y];
                        path.push({
                            lat: p.olat,
                            lng: p.olng
                        });
                    }
                    var polygonArea = new BMap.Polygon(path, {
                        strokeColor: "blue",
                        fillColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 0.6,
                        fillOpacity: 0.2,
                        strokeStyle: 'solid',
                        enableMassClear: true
                    });
                    mapLayer.map.mapObject.addOverlay(polygonArea);

                    var label = new BMap.Label(ctx.area.name, {
                        position: polygonArea.getBounds().getCenter()
                    })
                    mapLayer.map.mapObject.addOverlay(label);
                }, {
                    area: polygon,
                    info: mapLayerInfo
                });
            }
        }
        // 画路线
        var routes = mapLayerInfo.routeAreas;
        if (routes) {
            for (var x = 0; x < routes.length; x++) {
                var route = routes[x];
                for (var y = 0; y < route.sections.length; y++) {
                    var section = route.sections[y];
                    mapLayer.map.translate(section.points, 0, function (list, ctx) {
                        if (mapLayer.currentMapLayer != ctx.info)
                            return;
                        var path = [];
                        for (var x = 0; x < list.length; x++) {
                            var item = list[x];
                            var point = new BMap.Point(item.olng, item.olat);
                            path.push(point);
                        }
                        var sectionArea = new BMap.Polyline(path, {
                            strokeColor: "red", // 折线颜色
                            strokeWeight: 5, // 折线的宽度，以像素为单位。
                            strokeOpacity: 0.6, // 折线的透明度，取值范围0 - 1。
                            strokeStyle: 'solid' // 折线的样式，solid或dashed。
                        });
                        mapLayer.map.mapObject.addOverlay(sectionArea);
                    }, {
                        info: mapLayerInfo
                    });
                }
            }
        }
        // 画兴趣点
        var pois = mapLayerInfo.pois;
        if (pois) {
            for (var x = 0; x < pois.length; x++) {
                var row = pois[x];
                mapLayer.map.convertor(row.lng, row.lat, function (point, ctx) {
                    if (mapLayer.currentMapLayer != ctx.info)
                        return;
                    var p = new BMap.Point(point.lng, point.lat);
                    var poi = new BMap.Marker(p, {
                        strokeColor: "blue",
                        fillColor: "red",
                        strokeWeight: 2,
                        strokeOpacity: 0.6,
                        fillOpacity: 0.2,
                        strokeStyle: 'solid'
                    });
                    var label = new BMap.Label(ctx.area.name, {
                        position: p,
                        offset: new BMap.Size(20, 15)
                    })
                    poi.setLabel(label);
                    mapLayer.map.mapObject.addOverlay(poi);
                }, {
                    area: row,
                    info: mapLayerInfo
                });
            }
        }
    },
    onMapLoaded: function (map) {
        mapLayer.map = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            mapLayer.map.convertor(r.lng, r.lat, function (center) {
                mapLayer.map.setCenter(center);
                mapLayer.map.setZoom(r.zoom);
            });
        });
    },
    showDetail: function (row, detailPanel, callback) {
        var mapLayerId = row.id;
        mapLayer.detailRows[mapLayerId] = row;
        var panel = $('<div></div>').css('margin', 5);
        $(detailPanel).append(panel);

        var divTab = $('<div></div>');
        panel.append(divTab);

        var divCircleArea = $('<div title="圆形区域"></div>');
        var divRectangleArea = $('<div title="矩形区域"></div>');
        var divPolygonArea = $('<div title="多边形区域"></div>');
        var divRouteArea = $('<div title="路线"></div>');
        var divPoi = $('<div title="兴趣点" lselected="true"></div>');
        divTab.append(divCircleArea);
        divTab.append(divRectangleArea);
        divTab.append(divPolygonArea);
        divTab.append(divRouteArea);
        divTab.append(divPoi);

        var gridCircleArea = $('<div></div>');
        divCircleArea.append(gridCircleArea);
        var gridRectangleArea = $('<div></div>');
        divRectangleArea.append(gridRectangleArea);
        var gridPolygonArea = $('<div></div>');
        divPolygonArea.append(gridPolygonArea);
        var gridRouteArea = $('<div></div>');
        divRouteArea.append(gridRouteArea);
        var gridPoi = $('<div></div>');
        divPoi.append(gridPoi);

        divTab.ligerTab();

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
        if ($('#removeCircleArea_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="mapLayer.removeCircleArea(\'' + row.id + '\',' + rw.id + ')">删除</a>';
                    return html;
                }
            });
        }
        mapLayer.gridCircleArea[mapLayerId] = gridCircleArea.ligerGrid({
            columns: columns,
            width: '95%',
            url: '../mapLayer/circleAreas',
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            usePager: false,
            parms: {
                mapLayerId: mapLayerId
            }
        });
        columns = [{
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
        if ($('#removeRectangleArea_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="mapLayer.removeRectangleArea(\'' + row.id + '\',' + rw.id + ')">删除</a>';
                    return html;
                }
            });
        }
        mapLayer.gridRectangleArea[mapLayerId] = gridRectangleArea.ligerGrid({
            columns: columns,
            url: '../mapLayer/rectangleAreas',
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            usePager: false,
            parms: {
                mapLayerId: mapLayerId
            }
        });
        columns = [{
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
        if ($('#removePolygonArea_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="mapLayer.removePolygonArea(\'' + row.id + '\',' + rw.id + ')">删除</a>';
                    return html;
                }
            });
        }
        mapLayer.gridPolygonArea[mapLayerId] = gridPolygonArea.ligerGrid({
            columns: columns,
            url: '../mapLayer/polygonAreas',
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            usePager: false,
            parms: {
                mapLayerId: mapLayerId
            }
        });
        columns = [{
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
        if ($('#removeRouteArea_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="mapLayer.removeRouteArea(\'' + row.id + '\',' + rw.id + ')">删除</a>';
                    return html;
                }
            });
        }
        mapLayer.gridRouteArea[mapLayerId] = gridRouteArea.ligerGrid({
            columns: columns,
            url: '../mapLayer/routeAreas',
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            usePager: false,
            parms: {
                mapLayerId: mapLayerId
            }
        });
        columns = [{
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
            align: 'left'
        }];
        if ($('#removePoi_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 100,
                render: function (rw) {
                    var html = '<a href="#" onclick="mapLayer.removePoi(\'' + row.id + '\',' + rw.id + ')">删除</a>';
                    return html;
                }
            });
        }
        mapLayer.gridPoi[mapLayerId] = gridPoi.ligerGrid({
            columns: columns,
            url: '../mapLayer/pois',
            columnWidth: 100,
            onAfterShowData: callback,
            root: 'rows',
            record: 'total',
            usePager: false,
            parms: {
                mapLayerId: mapLayerId
            }
        });
    },
    addCircleArea: function (mapLayerId) {
        mapLayer.addCircleAreaParms = mapLayer.addCircleAreaParms || {};
        mapLayer.addCircleAreaParms.mapLayerId = mapLayerId;
        if (!mapLayer.dialogMapLayerSelectCircleArea) {
            mapLayer.gridCircleAreaInMapLayerParms = {
                filter: ''
            };
            mapLayer.dialogMapLayerSelectCircleArea = $.ligerDialog.open({
                title: '选择圆形区域',
                width: 750,
                height: 400,
                target: $("#dialogMapLayerSelectCircleArea")
            });
            mapLayer.gridCircleAreaInMapLayer = $("#gridCircleAreaInMapLayer").ligerGrid({
                columns: [{
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
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../circleArea/query',
                width: 'auto',
                height: 300,
                pageSize: 30,
                rownumbers: true,
                checkbox: true,
                parms: mapLayer.gridCircleAreaInMapLayerParms
            });

            $('#btnQueryCircleArea').click(function () {
                mapLayer.gridCircleAreaInMapLayerParms.filter = $('txtCircleAreaFilter').val();
                mapLayer.gridCircleAreaInMapLayerParms.reload();
            });

            $('#btnSelectCircleArea').click(function () {
                var rows = mapLayer.gridCircleAreaInMapLayer.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                mapLayer.dialogMapLayerSelectCircleArea.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].id);
                }
                $.post('../mapLayer/addCircleAreas', {
                    mapLayerId: mapLayer.addCircleAreaParms.mapLayerId,
                    list: list
                }, function (data) {
                    var result = common.checkData(data, "", "添加圆形区域成功！");
                    if (result === true) {
                        mapLayer.resetMapLayer(mapLayer.addCircleAreaParms.mapLayerId);
                        if (mapLayer.addCircleAreaParms.mapLayerId in mapLayer.gridCircleArea)
                            mapLayer.gridCircleArea[mapLayer.addCircleAreaParms.mapLayerId].reload();
                    }
                });
            });
        } else
            mapLayer.dialogMapLayerSelectCircleArea.show();
    },
    removeCircleArea: function (mapLayerId, areaId) {
        mapLayer.removeCircleAreaParms = mapLayer.removeCircleAreaParms || {};
        mapLayer.removeCircleAreaParms.mapLayerId = mapLayerId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../mapLayer/removeCircleArea', {
                mapLayerId: mapLayerId,
                areaId: areaId
            }, function (data) {
                var result = common.checkData(data, "", "删除圆形区域成功！");
                if (result === true) {
                    mapLayer.resetMapLayer(mapLayer.removeCircleAreaParms.mapLayerId);
                    if (mapLayer.removeCircleAreaParms.mapLayerId in mapLayer.gridCircleArea)
                        mapLayer.gridCircleArea[mapLayer.removeCircleAreaParms.mapLayerId].reload();
                }
            });
        });
    },
    addRectangleArea: function (mapLayerId) {
        mapLayer.addRectangleAreaParms = mapLayer.addRectangleAreaParms || {};
        mapLayer.addRectangleAreaParms.mapLayerId = mapLayerId;

        if (!mapLayer.dialogMapLayerSelectRectangleArea) {
            mapLayer.gridRectangleAreaInMapLayerParms = {
                filter: ''
            };
            mapLayer.dialogMapLayerSelectRectangleArea = $.ligerDialog.open({
                title: '选择矩形区域',
                width: 800,
                height: 400,
                target: $("#dialogMapLayerSelectRectangleArea")
            });
            mapLayer.gridRectangleAreaInMapLayer = $("#gridRectangleAreaInMapLayer").ligerGrid({
                columns: [{
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
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../rectangleArea/query',
                width: 'auto',
                height: 300,
                pageSize: 30,
                rownumbers: true,
                checkbox: true,
                parms: mapLayer.gridRectangleAreaInMapLayerParms
            });

            $('btnQueryRectangleArea').click(function () {
                mapLayer.gridRectangleAreaInMapLayerParms.filter = $('#txtRectangleAreaFilter').val();
                mapLayer.gridRectangleAreaInMapLayer.reload();
            });

            $('#btnSelectRectangleArea').click(function () {
                var rows = mapLayer.gridRectangleAreaInMapLayer.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                mapLayer.dialogMapLayerSelectRectangleArea.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].id);
                }
                $.post('../mapLayer/addRectangleAreas', {
                    mapLayerId: mapLayer.addRectangleAreaParms.mapLayerId,
                    list: list
                }, function (data) {
                    var result = common.checkData(data, "", "添加矩形区域成功！");
                    if (result === true) {
                        mapLayer.resetMapLayer(mapLayer.addRectangleAreaParms.mapLayerId);
                        if (mapLayer.addRectangleAreaParms.mapLayerId in mapLayer.gridRectangleArea)
                            mapLayer.gridRectangleArea[mapLayer.addRectangleAreaParms.mapLayerId].reload();
                    }
                });
            });
        } else
            mapLayer.dialogMapLayerSelectRectangleArea.show();
    },
    removeRectangleArea: function (mapLayerId, areaId) {
        mapLayer.removeRectangleAreaParms = mapLayer.removeRectangleAreaParms || {};
        mapLayer.removeRectangleAreaParms.mapLayerId = mapLayerId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../mapLayer/removeRectangleArea', {
                mapLayerId: mapLayerId,
                areaId: areaId
            }, function (data) {
                var result = common.checkData(data, "", "删除矩形区域成功！");
                if (result === true) {
                    mapLayer.resetMapLayer(mapLayer.removeRectangleAreaParms.mapLayerId);
                    if (mapLayer.removeRectangleAreaParms.mapLayerId in mapLayer.gridRectangleArea)
                        mapLayer.gridRectangleArea[mapLayer.removeRectangleAreaParms.mapLayerId].reload();
                }
            });
        });
    },
    addPolygonArea: function (mapLayerId) {
        mapLayer.addPolygonAreaParms = mapLayer.addPolygonAreaParms || {};
        mapLayer.addPolygonAreaParms.mapLayerId = mapLayerId;

        if (!mapLayer.dialogMapLayerSelectPolygonArea) {
            mapLayer.gridPolygonAreaInMapLayerParms = {
                filter: ''
            };
            mapLayer.dialogMapLayerSelectPolygonArea = $.ligerDialog.open({
                title: '选择多边形区域',
                width: 800,
                height: 400,
                target: $("#dialogMapLayerSelectPolygonArea")
            });
            mapLayer.gridPolygonAreaInMapLayer = $("#gridPolygonAreaInMapLayer").ligerGrid({
                columns: [{
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
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../polygonArea/query',
                width: 'auto',
                height: 300,
                pageSize: 30,
                rownumbers: true,
                checkbox: true,
                parms: mapLayer.gridPolygonAreaInMapLayerParms
            });

            $('btnQueryPolygonArea').click(function () {
                mapLayer.gridPolygonAreaInMapLayerParms.filter = $('#txtPolygonAreaFilter').val();
                mapLayer.gridPolygonAreaInMapLayer.reload();
            });

            $('#btnSelectPolygonArea').click(function () {
                var rows = mapLayer.gridPolygonAreaInMapLayer.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                mapLayer.dialogMapLayerSelectPolygonArea.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].id);
                }
                $.post('../mapLayer/addPolygonAreas', {
                    mapLayerId: mapLayer.addPolygonAreaParms.mapLayerId,
                    list: list
                }, function (data) {
                    var result = common.checkData(data, "", "添加多边形区域成功！");
                    if (result === true) {
                        mapLayer.resetMapLayer(mapLayer.addPolygonAreaParms.mapLayerId);
                        if (mapLayer.addPolygonAreaParms.mapLayerId in mapLayer.gridPolygonArea)
                            mapLayer.gridPolygonArea[mapLayer.addPolygonAreaParms.mapLayerId].reload();
                    }
                });
            });
        } else
            mapLayer.dialogMapLayerSelectPolygonArea.show();
    },
    removePolygonArea: function (mapLayerId, areaId) {
        mapLayer.removePolygonAreaParms = mapLayer.removePolygonAreaParms || {};
        mapLayer.removePolygonAreaParms.mapLayerId = mapLayerId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../mapLayer/removePolygonArea', {
                mapLayerId: mapLayerId,
                areaId: areaId
            }, function (data) {
                var result = common.checkData(data, "", "删除多边形区域成功！");
                if (result === true) {
                    mapLayer.resetMapLayer(mapLayer.removePolygonAreaParms.mapLayerId);
                    if (mapLayer.removePolygonAreaParms.mapLayerId in mapLayer.gridPolygonArea)
                        mapLayer.gridPolygonArea[mapLayer.removePolygonAreaParms.mapLayerId].reload();
                }
            });
        });
    },
    addRouteArea: function (mapLayerId) {
        mapLayer.addRouteAreaParms = mapLayer.addRouteAreaParms || {};
        mapLayer.addRouteAreaParms.mapLayerId = mapLayerId;

        if (!mapLayer.dialogMapLayerSelectRouteArea) {
            mapLayer.gridRouteAreaInMapLayerParms = {
                filter: ''
            };
            mapLayer.dialogMapLayerSelectRouteArea = $.ligerDialog.open({
                title: '选择路线',
                width: 800,
                height: 400,
                target: $("#dialogMapLayerSelectRouteArea")
            });
            mapLayer.gridRouteAreaInMapLayer = $("#gridRouteAreaInMapLayer").ligerGrid({
                columns: [{
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
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../routeArea/query',
                width: 'auto',
                height: 300,
                pageSize: 30,
                rownumbers: true,
                checkbox: true,
                parms: mapLayer.gridRouteAreaInMapLayerParms
            });

            $('btnQueryRouteArea').click(function () {
                mapLayer.gridRouteAreaInMapLayerParms.filter = $('#txtRouteAreaFilter').val();
                mapLayer.gridRouteAreaInMapLayer.reload();
            });

            $('#btnSelectRouteArea').click(function () {
                var rows = mapLayer.gridRouteAreaInMapLayer.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                mapLayer.dialogMapLayerSelectRouteArea.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].id);
                }
                $.post('../mapLayer/addRouteAreas', {
                    mapLayerId: mapLayer.addRouteAreaParms.mapLayerId,
                    list: list
                }, function (data) {
                    var result = common.checkData(data, "", "添加路线成功！");
                    if (result === true) {
                        mapLayer.resetMapLayer(mapLayer.addRouteAreaParms.mapLayerId);
                        if (mapLayer.addRouteAreaParms.mapLayerId in mapLayer.gridRouteArea)
                            mapLayer.gridRouteArea[mapLayer.addRouteAreaParms.mapLayerId].reload();
                    }
                });
            });
        } else
            mapLayer.dialogMapLayerSelectRouteArea.show();
    },
    removeRouteArea: function (mapLayerId, areaId) {
        mapLayer.removeRouteAreaParms = mapLayer.removeRouteAreaParms || {};
        mapLayer.removeRouteAreaParms.mapLayerId = mapLayerId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../mapLayer/removeRouteArea', {
                mapLayerId: mapLayerId,
                areaId: areaId
            }, function (data) {
                var result = common.checkData(data, "", "删除路线成功！");
                if (result === true) {
                    mapLayer.resetMapLayer(mapLayer.removeRouteAreaParms.mapLayerId);
                    if (mapLayer.removeRouteAreaParms.mapLayerId in mapLayer.gridRouteArea)
                        mapLayer.gridRouteArea[mapLayer.removeRouteAreaParms.mapLayerId].reload();
                }
            });
        });
    },
    addPoi: function (mapLayerId) {
        mapLayer.addPoiParms = mapLayer.addPoiParms || {};
        mapLayer.addPoiParms.mapLayerId = mapLayerId;

        if (!mapLayer.dialogMapLayerSelectPoi) {
            mapLayer.gridPoiInMapLayerParms = {
                filter: ''
            };
            mapLayer.dialogMapLayerSelectPoi = $.ligerDialog.open({
                title: '选择兴趣点',
                width: 800,
                height: 400,
                target: $("#dialogMapLayerSelectPoi")
            });
            mapLayer.gridPoiInMapLayer = $("#gridPoiInMapLayer").ligerGrid({
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
                    align: 'left'
                }],
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../poi/query',
                width: 'auto',
                height: 300,
                pageSize: 30,
                rownumbers: true,
                checkbox: true,
                parms: mapLayer.gridPoiInMapLayerParms
            });

            $('btnQueryPoi').click(function () {
                mapLayer.gridPoiInMapLayerParms.filter = $('#txtPoiFilter').val();
                mapLayer.gridPoiInMapLayer.reload();
            });

            $('#btnSelectPoi').click(function () {
                var rows = mapLayer.gridPoiInMapLayer.getSelectedRows();
                if (!rows || rows.length <= 0) {
                    $.ligerDialog.error('请选择数据行！');
                    return;
                }
                mapLayer.dialogMapLayerSelectPoi.hide();
                var list = [];
                for (var x = 0; x < rows.length; x++) {
                    list.push(rows[x].id);
                }
                $.post('../mapLayer/addPois', {
                    mapLayerId: mapLayer.addPoiParms.mapLayerId,
                    list: list
                }, function (data) {
                    var result = common.checkData(data, "", "添加兴趣点成功！");
                    if (result === true) {
                        mapLayer.resetMapLayer(mapLayer.addPoiParms.mapLayerId);
                        if (mapLayer.addPoiParms.mapLayerId in mapLayer.gridPoi)
                            mapLayer.gridPoi[mapLayer.addPoiParms.mapLayerId].reload();
                    }
                });
            });
        } else
            mapLayer.dialogMapLayerSelectPoi.show();
    },
    removePoi: function (mapLayerId, areaId) {
        mapLayer.removePoiParms = mapLayer.removePoiParms || {};
        mapLayer.removePoiParms.mapLayerId = mapLayerId;
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../mapLayer/removePoi', {
                mapLayerId: mapLayerId,
                areaId: areaId
            }, function (data) {
                var result = common.checkData(data, "", "删除兴趣点成功！");
                if (result === true) {
                    mapLayer.resetMapLayer(mapLayer.removePoiParms.mapLayerId);
                    if (mapLayer.removePoiParms.mapLayerId in mapLayer.gridPoi)
                        mapLayer.gridPoi[mapLayer.removePoiParms.mapLayerId].reload();
                }
            });
        });
    },
    resetMapLayer: function (mapLayerId) {
        $.post('../mapLayer/mapLayerInfo', {
            mapLayerId: mapLayerId
        }, function (info) {
            var detail = mapLayer.detailRows[mapLayerId];
            if (!detail)
                return;

            detail.circleAreas = info.circleAreas;
            detail.rectangleAreas = info.rectangleAreas;
            detail.polygonAreas = info.polygonAreas;
            detail.routeAreas = info.routeAreas;
            detail.pois = info.pois;

            var row = mapLayer.gridMapLayer && mapLayer.gridMapLayer.getSelected();
            if (row && row.id == mapLayerId) {
                mapLayer.redrawOverlay(detail);
            }
        });
    },
    query: function () {
        var columns = [{
            display: '名称',
            name: 'name',
            align: 'left',
            width: 200
        }, {
            display: '可见否',
            name: 'visible',
            width: 80,
            render: function (row) {
                if (row.visible === true)
                    return '是';
                else if (row.visible === false)
                    return '否';
                return '';
            }
        }, {
            display: '说明',
            name: 'remark',
            align: 'left'
        }];
        if ($('#addCircleAreas_auth').val() == 'true') {
            columns.push({
                display: '圆形区域',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    return '<a href="#" onclick="mapLayer.addCircleArea(\'' + row.id + '\')">添加圆形区域</a>';
                }
            });
        }
        if ($('#addRectangleAreas_auth').val() == 'true') {
            columns.push({
                display: '矩形区域',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    return '<a href="#" onclick="mapLayer.addRectangleArea(\'' + row.id + '\')">添加矩形区域</a>';
                }
            });
        }
        if ($('#addPolygonAreas_auth').val() == 'true') {
            columns.push({
                display: '多边形区域',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    return '<a href="#" onclick="mapLayer.addPolygonArea(\'' + row.id + '\')">添加多边形区域</a>';
                }
            });
        }
        if ($('#addRouteAreas_auth').val() == 'true') {
            columns.push({
                display: '路线',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    return '<a href="#" onclick="mapLayer.addRouteArea(\'' + row.id + '\')">添加路线</a>';
                }
            });
        }
        if ($('#addPois_auth').val() == 'true') {
            columns.push({
                display: '兴趣点',
                isAllowHide: false,
                width: 100,
                render: function (row) {
                    return '<a href="#" onclick="mapLayer.addPoi(\'' + row.id + '\')">添加兴趣点</a>';
                }
            });
        }
        if (!mapLayer.gridMapLayer)
            mapLayer.gridMapLayer = $("#mapLayerFrame #gridMapLayer").ligerGrid({
                columns: columns,
                root: 'rows',
                record: 'total',
                pageParmName: 'pageIndex',
                pagesizeParmName: 'pageSize',
                url: '../mapLayer/query',
                width: '100%',
                height: 274,
                pageSize: 30,
                parms: mapLayer.gridMapLayerParams,
                onSelectRow: function (row) {
                    mapLayer.redrawOverlay(row);
                },
                onSuccess: function () {
                    mapLayer.detailRows = {};
                    mapLayer.clearOverlays();
                },
                detail: {
                    onShowDetail: mapLayer.showDetail,
                    height: 200
                }
            });
        else {
            mapLayer.gridMapLayer.changePage('first');
            mapLayer.gridMapLayer.reload();
        }
    },
    create: function () {
        var url = '../mapLayer/create.form';
        $.get(url, function (html) {
            var op = {
                url: url,
                allowClose: false,
                width: 750,
                height: 300,
                isHidden: false,
                title: '创建地图图层',
                onLoaded: function () {
                    mapLayer.dialog.frame.window.validate();
                },
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (dialog.frame.window.validate() == false) {
                            return;
                        }

                        var form = $('form', dialog.frame.document);
                        var formData = form.serialize();
                        $.post(url, formData, function (data) {
                            var result = common.form.save(dialog, data, op);
                            if (result === true)
                                mapLayer.gridMapLayer.reload();
                        });
                    }
                }, {
                    text: '取消',
                    onclick: function (item, dialog) {
                        mapLayer.map.mapObject.removeOverlay();
                        dialog.close();
                    }
                }]
            };
            mapLayer.dialog = $.ligerDialog.open(op);
        });
    },
    edit: function (id) {
        var url = '../mapLayer/edit.form';
        var op = {
            url: url,
            urlParms: {
                id: id
            },
            width: 750,
            height: 300,
            isHidden: false,
            title: '修改地图图层',
            onLoaded: function () {
                mapLayer.dialog.frame.window.validate();
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    if (dialog.frame.window.validate() == false) {
                        return;
                    }

                    var form = $('form', dialog.frame.document);
                    var formData = form.serialize();
                    $.post(url, formData, function (data) {
                        var result = common.form.save(dialog, data, op);
                        if (result === true)
                            mapLayer.gridMapLayer.reload();
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        mapLayer.dialog = $.ligerDialog.open(op);
    },
    remove: function (id) {
        $.ligerDialog.confirm('真地要删除所选项吗？', function (yes, value) {
            if (!yes)
                return;
            $.post('../mapLayer/delete', {
                id: id
            }, function (data) {
                var result = common.form.remove(data);
                if (result === true)
                    mapLayer.gridMapLayer.reload();
            });
        });
    },
    resize: function () {
        var height = $(document).height();
        $('#mapLayerFrame #divMapLayerAllMap').height(height - 300);
    }
};
$(function () {
    mapLayer.resize();
    mapLayer.query();
    webMap.events.onMapLoadCompleted['mapLayerMap'] = mapLayer.onMapLoaded;
    webMap.createMap('mapLayerMap');
    $('#mapLayerFrame #btnQueryMapLayer').click(function () {
        mapLayer.gridMapLayerParams.filter = $('#mapLayerFrame #txtMapLayerFilter').val();
        mapLayer.query();
    });

    $('#mapLayerFrame #btnCreateMapLayer').click(function () {
        mapLayer.create();
    });

    $('#mapLayerFrame #btnEditMapLayer').click(function () {
        if (!mapLayer.gridMapLayer)
            return;

        var row = mapLayer.gridMapLayer.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        mapLayer.edit(row.id);
    });
    $('#mapLayerFrame #btnRemoveMapLayer').click(function () {
        if (!mapLayer.gridMapLayer)
            return;

        var row = mapLayer.gridMapLayer.getSelected();
        if (!row) {
            $.ligerDialog.error('请选择数据行！');
            return;
        }
        mapLayer.remove(row.id);
    });
});