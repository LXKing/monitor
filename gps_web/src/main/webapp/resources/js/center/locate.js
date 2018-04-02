/**
 * 定位查询
 */
window.locate = {
    /**
     * 服务器时间戳
     */
    serverTimestamp: null,
    /**
     * 网络连接
     */
    websocket: null,
    /**
     * 心跳时间
     */
    HeartbeatTime: new Date(),
    /**
     * 地图
     */
    webMap: undefined,
    /**
     * 信息窗口
     */
    infoWindow: undefined,
    /**
     * 显示车牌号
     */
    allowShowLabel: true,
    /**
     * 标注
     */
    markers: {}, // 标注
    /**
     * 组别与车辆总表
     */
    vehicles: null,
    /**
     * 设备列表
     */
    devices: {},
    /**
     * 所有未处理的报警记录
     */
    alarms: [],
    /**
     * 地图图层
     */
    mapLayers: null,
    /**
     * 未读消息
     */
    unread: {
        /**
         * 多媒体事件报告
         */
        multimediaEventReprot: [],
        /**
         * 事件报告
         */
        deviceEventReport: [],
        /**
         * 终端升级结果报告
         */
        deviceUpgradeResultReport: [],
        /**
         * 刷新数量提示
         */
        refresh: function () {
            var total = this.multimediaEventReprot.length;
            total += this.deviceEventReport.length;
            total += this.deviceUpgradeResultReport.length;
            console.log(this.deviceEventReport.length);
            console.log(this.deviceUpgradeResultReport.length);
            $('#txtMessageTotal').text(total);
            if (total > 0)
                $('#txtMessageTotal').show();
            else
                $('#txtMessageTotal').hide();
        },
        /**
         * 读取未读多媒体事件报告
         */
        readMultmediaEventReport: function (id) {
            function removeMultmediaEventReport(id) {
                var index = -1;
                for (var x = 0; x < locate.unread.multimediaEventReprot.length; x++) {
                    var item = locate.unread.multimediaEventReprot[x];
                    if (item.id == id) {
                        index = x;
                        break;
                    }
                }
                locate.unread.multimediaEventReprot.splice(index, 1);
                locate.gridMultimediaEventReprot.reload({
                    rows: locate.unread.multimediaEventReprot
                });
                locate.unread.refresh();
            }

            $.post('../locate/readMultmediaEventReport', {
                id: id
            }, function (r) {
                if (r.code == 0) {
                    removeMultmediaEventReport(id);
                } else
                    common.tip('error', r.error, 3);
            });
        },
        /**
         * 读取终端事件报告
         */
        readDeviceEventReport: function (id) {
            function removeDeviceEventReport(id) {
                var index = -1;
                for (var x = 0; x < locate.unread.deviceEventReport.length; x++) {
                    var item = locate.unread.deviceEventReport[x];
                    if (item.id == id) {
                        index = x;
                        break;
                    }
                }
                locate.unread.deviceEventReport.splice(index, 1);
                locate.gridDeviceEventReport.reload({
                    rows: locate.unread.deviceEventReport
                });
                locate.unread.refresh();
            }

            $.post('../locate/readDeviceEventReport', {
                id: id
            }, function (r) {
                if (r.code == 0) {
                    removeDeviceEventReport(id);
                } else
                    common.tip('error', r.error, 3);
            });
        },
        readDeviceUpgradeResultReport: function (id) {
            function removeDeviceUpgradeResultReport(id) {
                var index = -1;
                for (var x = 0; x < locate.unread.deviceUpgradeResultReport.length; x++) {
                    console.log(142);
                    var item = locate.unread.deviceUpgradeResultReport[x];
                    if (item.id == id) {
                        index = x;
                        break;
                    }
                }
                locate.unread.deviceUpgradeResultReport.splice(index, 1);
                locate.gridDeviceUpgradeResultReport.reload({
                    rows: locate.unread.deviceUpgradeResultReport
                });
                locate.unread.refresh();
            }

            $.post('../locate/readDeviceUpgradeResultReport', {
                id: id
            }, function (r) {
                if (r.code == 0) {
                    removeDeviceUpgradeResultReport(id);
                } else
                    common.tip('error', r.error, 3);
            });
        },
        /**
         * 提取多媒体
         */
        pickupMultmedia: function (eventId, deviceNumber, mediaId) {
            $.post('../deviceData/pickupMultmedia', {
                deviceNumber: deviceNumber,
                mediaId: mediaId
            }, function (r) {
                if (r.code == 0) {
                    common.tip('success', '指令已发送！', 3);
                    locate.unread.readMultmediaEventReport(eventId);
                } else
                    common.tip('error', r.error, 3);
            });
        }
    },
    /**
     * 最后位置表格
     */
    gridLatests: undefined,
    /**
     * 组别与车辆树
     */
    groupVehicles: undefined,
    /**
     * 地图加载完成回调
     */
    onMapLoaded: function (map) {
        locate.webMap = map;
        $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            locate.webMap.convertor(r.lng, r.lat, function (center) {
                locate.webMap.setCenter(center);
                locate.webMap.setZoom(r.zoom);
            });
        });
        locate.infoWindow = webMap.createInfoWindow({
            map: locate.webMap,
            data: undefined,
            width: 260,
            allowQueryAddress: true,
            makeTitle: function () {
                if (!this.data)
                    return '';

                var html = [];
                html.push('<div style="margin:0px;padding:0px">');
                // 第一行
                html.push('<div style="height:18px;margin:2px;">');

                html.push('<b>');
                html.push(this.data.na);
                html.push(this.data.sp > 0 ? '[行驶]' : '[静止]');
                html.push('</b>');

                html.push('<div style="margin-left:15px;display:inline-block;">');
                html.push('<div class="mon-icon-h-x16 i-16-satellite"></div>')
                html.push('<span>(' + this.data.sat + ')</span>');
                html.push('<div class="mon-icon-h-x16 i-16-signal' + gpsDataParser.parseNet(this.data) + '"></div>');
                html.push('</div>');

                html.push('</div>');

                return html.join('');
            },
            makeContent: function () {
                var alarm = gpsDataParser.parseAlarm(this.data);
                var html = [];
                html.push('<hr/><div style="margin:0px;padding:0px">');
                // 第一行
                html.push('<div class="display-label">');
                html.push('<b>时间:</b>');
                if (this.data.gt)
                    html.push(this.data.gt.toDate().toDateTimeString('MM-dd hh:mm:ss'));
                else
                    html.push("00-00 00:00:00");
                html.push('[定位],');
                if (this.data.st)
                    html.push(this.data.st.toDate().toDateTimeString('MM-dd hh:mm:ss'));
                else
                    html.push("00:00:00");
                html.push('[接收]');
                html.push("</div>");

                // 第二行
                html.push('<div class="display-label">');
                html.push('<b>定位:</b>');
                html.push(gpsDataParser.parseLocateType(this.data));
                html.push('[' + gpsDataParser.parseDirection(this.data) + ']');
                html.push('&nbsp;&nbsp;<b>状态:</b>');
                html.push(gpsDataParser.parseAcc(this.data));
                html.push('</div>');

                // 第三行
                html.push('<div class="display-label">');
                html.push('<b>里程:</b>');
                html.push(common.round(this.data.m, 3) + 'km');
                html.push('&nbsp;&nbsp;<b>速度:</b>');
                html.push(common.round(this.data.sp, 1) + 'km/h');
                html.push('</div>');

                // 第四行
                if (alarm.length > 0) {
                    html.push('<div class="display-label">');
                    html.push('<b>报警:</b>');
                    html.push('<span style="color:red;">');
                    html.push(alarm);
                    html.push('</span>');
                    html.push('</div>');
                }

                // 第五行
                html.push('<div style="margin:2px;">');
                html.push('<b>位置:</b>');
                html.push(this.data.addr);
                html.push('</div>');

                // 第六行
                var text = '跟踪';
                if (locate.track.deviceNumber === this.data.dn)
                    text = '停止';
                html.push('<div>');
                html.push('<a href="javascript:locate.track.start(\'' + this.data.dn
                    + '\');"><div class="mon-button"><div class="mon-icon-v-x24 i-24-track"></div><div id="btnTrack">' + text
                    + '</div></div></a>');

                if ($('#replay_auth').val() == 'true') {
                    html.push('<a href="javascript:locate.replay(\'' + this.data.dn
                        + '\');"><div class="mon-button"><div class="mon-icon-v-x24 i-24-replay"></div><div>回放</div></div></a>');
                }
                if ($('#alarm_auth').val() == 'true') {
                    html.push('<a href="javascript:locate.queryAlarm(\'' + this.data.dn
                        + '\');"><div class="mon-button"><div class="mon-icon-v-x24 i-24-alarm"></div><div>报警</div></div></a>');
                }
                if ($('#vehileinfo_auth').val() == 'true') {
                    html.push('<a href="javascript:locate.vehiceInfo(\'' + this.data.id
                        + '\');"><div class="mon-button"><div class="mon-icon-v-x24 i-24-info"></div><div>资料</div></div></a>');
                }
                if ($('#instruct_auth').val() == 'true') {
                    html.push('<a href="javascript:locate.sendInstruct(\'' + this.data.dn
                        + '\');"><div class="mon-button"><div class="mon-icon-v-x24 i-24-instruct"></div><div>指令</div></div></a>');
                }
                if ($('#deviceData_auth').val() == 'true') {
                    html.push('<a href="javascript:locate.queryData(\'' + this.data.dn
                        + '\');"><div class="mon-button"><div class="mon-icon-v-x24 i-24-querydata"></div><div>数据</div></div></a>');
                    html.push('</div>');
                }

                return html.join("");

            }
        });

        $.get('../locate/maptools.panel', function (html) {
            var divTools = $('<div style="cursor:pointer;border:1px solid gray;border-radius:5px;background-color:white;opacity:0.8;"></div>');
            divTools.html(html);
            map.setCustomControl(divTools[0], {
                anchor: "top_left",
                offset: {
                    x: 80,
                    y: 10
                }
            });

            $.get('../user/getOption', {
                type: 2
            }, function (r) {
                if (r.show == 'none') {
                    $('#divMapToobars').hide();
                    $('#btnToggleButton div').removeClass("i-16-toleft");
                    $('#btnToggleButton div').addClass("i-16-toright");
                }
            });

            $('#btnToggleButton').click(function () {
                $('#divMapToobars').toggle();
                if ($('#divMapToobars').css('display') == "none") {
                    $('#btnToggleButton div').removeClass("i-16-toleft");
                    $('#btnToggleButton div').addClass("i-16-toright");
                    $.post('../user/saveOption', {
                        type: 2,
                        options: {
                            show: 'none'
                        }
                    });
                } else {
                    $('#btnToggleButton div').removeClass("i-16-toright");
                    $('#btnToggleButton div').addClass("i-16-toleft");
                    $.post('../user/saveOption', {
                        type: 2,
                        options: {
                            show: 'inline-block'
                        }
                    });
                }
            });

            $('#btnDistance').click(function () {
                if (locate.webMap)
                    locate.webMap.distance();
            });

            $('#btnFullMarkers').click(function () {
                locate.setViewport();
            });

            $('#btnFullScreen').click(function () {
                var element = document.getElementById("locateMap");

                var data = $(this).data('full');
                if (!data || data === false) {
                    if (element.requestFullScreen) {
                        element.requestFullScreen();
                    } else if (element.webkitRequestFullScreen) {
                        element.webkitRequestFullScreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    }

                    $("#btnFullScreen > div").removeClass('i-16-expand');
                    $("#btnFullScreen > div").addClass('i-16-collapsed');
                    $("#btnFullScreen > span").text('还原');
                    $(this).data('full', true);
                } else {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.webkitExitFullscreen) {
                        document.webkitExitFullscreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    }

                    $("#btnFullScreen > div").removeClass('i-16-collapsed');
                    $("#btnFullScreen > div").addClass('i-16-expand');
                    $("#btnFullScreen > span").text('全屏');
                    $(this).data('full', false);
                }
            });

            $('#btnClear').click(function () {
                locate.markers = {};
                locate.track.clear();
                locate.webMap.clearOverlays();
                var grid = locate.gridLatests;
                if (grid) {
                    var row = grid.getSelectedRow();
                    if (row)
                        grid.unselect(row);
                }
                var node = locate.groupVehicles.getSelected();
                if (node)
                    locate.groupVehicles.cancelSelect(node.data);
            });
            $('#btnShowNumber').click(function () {
                var markers = locate.markers
                var icon = $('>div', this);
                if (icon.hasClass('i-16-checked')) {
                    icon.removeClass('i-16-checked').addClass('i-16-unchecked');
                    // 取消所有的标注
                    locate.allowShowLabel = false;
                    for (var i in markers) {
                        var item = markers[i];
                        item.removeLabel();
                    }
                } else {
                    icon.removeClass('i-16-unchecked').addClass('i-16-checked');
                    // 添加所有的标注
                    locate.allowShowLabel = true;
                    for (var i in markers) {
                        var item = markers[i];
                        item.resetLabel();
                    }
                }
            });
            $('#btnTraffic').click(function () {
                locate.webMap.traffic();
            });
            $('#btnSearchArea').click(function () {
                locate.webMap.drawingOpen(locate.overlayComplete);
            });
            $('#btnLayers').click(function () {
                locate.mapLayerSetting();
            });
            $('#btnZoomout').click(function () {
                locate.webMap.zoomout();
            });
            $('#btnZoomin').click(function () {
                locate.webMap.zoomin();
            });
        });
        locate.loadGroups();
        setTimeout(function () {
            locate.drawMapLayers();
        }, 1000 * 10);
    },


    overlayComplete: function (e) {
        locate.webMap.removeOverlay(e.overlay);
        locate.webMap.drawingClose();
        var insides = [];
        for (var attr in locate.devices) {
            var device = locate.devices[attr];
            var pt = {
                lng: device.olng,
                lat: device.olat
            };
            if (locate.isInsidePolygon(pt, e.overlay.getPath())) {
                insides.push(device);
            }
        }
        if (insides.length <= 0) {
            common.tip('info', '此区域无车辆！', 3);
            return;
        }
        locate.loadLatests({
            rows: insides
        });
    },
    /**
     * 地图图层设置
     */
    mapLayerSetting: function () {
        var url = '../mapLayer/setting.form';
        var op = {
            url: url,
            allowClose: false,
            width: 750,
            height: 300,
            isHidden: false,
            title: '地图图层设置',
            onLoaded: function () {
                locate.dialog.frame.window.setting.query();
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    if (dialog.frame.window.setting.isChanged == true) {
                        locate.drawMapLayers();
                    }
                    dialog.close();
                }
            }]
        };
        locate.dialog = $.ligerDialog.open(op);
    },
    /**
     * 绘画地图图层
     */
    drawMapLayers: function () {
        // 清除地图图层
        if (locate.mapLayers) {
            for (var id in locate.mapLayers) {
                var layer = locate.mapLayers[id];
                for (var x = 0; x < layer.length; x++) {
                    var overlay = layer[x];
                    locate.webMap.removeOverlay(overlay);
                    overlay.label && locate.webMap.removeOverlay(overlay.label);
                }
            }
        }
        // 重新加载
        locate.mapLayers = {};
        $.get('../mapLayer/query', {
            filter: ''
        }, function (page) {
            for (var index = 0; index < page.rows.length; index++) {
                var mapLayerInfo = page.rows[index];
                if (mapLayerInfo.visible == false)
                    continue;
                locate.mapLayers[mapLayerInfo.id] = [];
                // 画圆
                var circles = mapLayerInfo.circleAreas;
                if (circles) {
                    for (var x = 0; x < circles.length; x++) {
                        var circle = circles[x];
                        locate.webMap.convertor(circle.lng, circle.lat, function (p, ctx) {
                            var overlay = locate.webMap.drawingCircle(p, ctx.area.radius, ctx.area.name);
                            locate.mapLayers[ctx.info.id].push(overlay);
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
                        locate.webMap.translate(points, 0, function (list, ctx) {
                            var wn = {
                                lng: list[0].olng,
                                lat: list[0].olat
                            };
                            var ne = {
                                lng: list[1].olng,
                                lat: list[1].olat
                            };
                            var ew = {
                                lng: list[2].olng,
                                lat: list[2].olat
                            };
                            var sw = {
                                lng: list[3].olng,
                                lat: list[3].olat
                            };
                            var path = [];
                            path.push(wn);
                            path.push(ne);
                            path.push(ew);
                            path.push(sw);

                            var overlay = locate.webMap.drawPolygon(path, ctx.area.name);
                            locate.mapLayers[ctx.info.id].push(overlay);
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
                        locate.webMap.translate(polygon.points, 0, function (list, ctx) {
                            var path = [];
                            for (var y = 0; y < list.length; y++) {
                                var p = list[y];
                                path.push({
                                    lat: p.olat,
                                    lng: p.olng
                                });
                            }
                            var overlay = locate.webMap.drawPolygon(path, ctx.area.name);
                            locate.mapLayers[ctx.info.id].push(overlay);
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
                            locate.webMap.translate(section.points, 0, function (list, ctx) {
                                var path = [];
                                for (var x = 0; x < list.length; x++) {
                                    var item = list[x];
                                    path.push({
                                        lng: item.olng,
                                        lat: item.olat
                                    });
                                }
                                var overlay = locate.webMap.drawPolyline(path);
                                locate.mapLayers[ctx.info.id].push(overlay);
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
                        locate.webMap.convertor(row.lng, row.lat, function (point, ctx) {
                            var overlay = locate.webMap.drawPoi(point, ctx.area.name);
                            locate.mapLayers[ctx.info.id].push(overlay);
                        }, {
                            area: row,
                            info: mapLayerInfo
                        });
                    }
                }
            }
        });
    },
    /**
     * 计算一个点是否在多边形里
     *
     * @param {Object}
     *            pt 标注点
     * @param {Object}
     *            poly 多边形数组
     */
    isInsidePolygon: function (pt, poly) {
        for (var c = false, i = -1, l = poly.length, j = l - 1; ++i < l; j = i)
            ((poly[i].lat <= pt.lat && pt.lat < poly[j].lat) || (poly[j].lat <= pt.lat && pt.lat < poly[i].lat))
            && (pt.lng < (poly[j].lng - poly[i].lng) * (pt.lat - poly[i].lat) / (poly[j].lat - poly[i].lat) + poly[i].lng) && (c = !c);
        return c;
    },
    /**
     * 加载组别与车辆树
     */
    // loadGroups: function () {
    //     var shit;
    //
    //     $.ajax({
    //         type: "POST",
    //         url: '/locate/groupVehicles',
    //         data: {
    //             force: false
    //         },
    //         success: function (data) {
    //             shit = data;
    //             locate.vehicles = shit;
    //             console.log(shit);
    //             var onlines = 0;
    //             var list = [];
    //             for (var x = 0; x < shit.length; x++) {
    //                 var item = shit[x];
    //                 if (item.type === 0) {
    //                     list.push(item);
    //                     console.log(item);
    //                     onlines += item.o
    //                     locate.devices[item.dn] = item;
    //                 }
    //             }
    //             locate.webMap.translate(list, 0, function () {
    //                 // 共享到上级
    //                 parent.center.devices = locate.devices;
    //
    //                 locate.loadLatests({
    //                     rows: list
    //                 });
    //             });
    //             locate.resetOnlines.total = list.length;
    //             locate.resetOnlines.onlines = onlines;
    //             locate.resetOnlines.reset();
    //             locate.loadUnreadMessage();
    //
    //             locate.groupVehicles = $("#groupVehicles").ligerGrid({
    //
    //                 checkbox: true,
    //                 columns: [
    //                     {display: '主键', name: 'na', align: 'left'},
    //                     {display: '公司名', name: 'dn'}
    //                 ],
    //                 data: shit
    //
    //
    //             });
    //         }
    //
    //     });
    //
    //
    // },
    loadGroups: function () {
        locate.groupVehicles = $("#groupVehicles").ligerTree({
            url: '/locate/groupVehicles',
            parms: {
                force: false
            },
            idFieldName: 'id',
            parentIDFieldName: 'pid',
            textFieldName: 'na',
            checkbox: true,
            nodeWidth: 'auto',
            onSelect: function (node) {
                function findSub(list, data) {
                    if (data.type !== 0) {
                        var children = data.children;
                        if (children) {
                            for (var x = 0; x < children.length; x++) {
                                var item = children[x];
                                if (item.type === 0)
                                    list.push(item);
                                else
                                    findSub(list, item);
                            }
                        }
                    }
                }

                if (!node || !node.data)
                    return;
                if (node.data.type !== 0) {
                    var list = [];
                    findSub(list, node.data);
                    locate.loadLatests({
                        rows: list
                    });
                    return;
                }
                locate.showLocate(locate.devices[node.data.dn]);
            },
            onError: function (XMLHttpRequest, textStatus, errorThrown) {
                $.ligerDialog.open({
                    width: 800,
                    height: 500,
                    content: XMLHttpRequest.responseText
                });
            },
            onSuccess: function (data) {
                locate.vehicles = data;
                console.log(data);
                var onlines = 0;
                var list = [];
                for (var x = 0; x < data.length; x++) {
                    var item = data[x];
                    if (item.type === 0) {
                        list.push(item);
                        console.log(item);
                        onlines += item.o
                        locate.devices[item.dn] = item;
                    }
                }
                locate.webMap.translate(list, 0, function () {
                    // 共享到上级
                    parent.center.devices = locate.devices;

                    locate.loadLatests({
                        rows: list
                    });
                });
                locate.resetOnlines.total = list.length;
                locate.resetOnlines.onlines = onlines;
                locate.resetOnlines.reset();
                locate.loadUnreadMessage();
            }
        });
    },
    refreshGroups: function () {
        locate.groupVehicles.loading.show();
        $.post('/locate/groupVehicles', {
            force: true
        });
        location.reload();
    },
    /**
     * 加载所有未处理消息
     */
    loadUnreadMessage: function () {
        $.post('../alarm/unhandled', function (data) {
            locate.alarms = data || [];
            locate.resetAlarmIcon();
        });
        $.post('../locate/unreadMultimediaEvent', function (data) {
            locate.unread.multimediaEventReprot = data || [];
            locate.unread.refresh();
        });
        $.post('../locate/unreadDeviceEvent', function (data) {
            locate.unread.deviceEventReport = data || [];
            locate.unread.refresh();
        });
        $.post('../locate/unreadDeviceUpgradeResultReport', function (data) {
            locate.unread.deviceUpgradeResultReport = data || [];
            console.log(deviceUpgradeResultReport);
            locate.unread.refresh();
        });
    },
    /**
     * 加载最后位置数据表格数据
     */
    loadLatests: function (data) {
        var columns = [
            {
                display: '定位状态',
                isAllowHide: false,
                width: 60,
                render: function (row) {
                    var img = (row.o == 1 ? "mon-icon-h-x16 i-16-online" : "mon-icon-h-x16 i-16-offline");
                    return '<div><div class="' + img + '"></div>' + gpsDataParser.parseLocateType(row) + '</div>';
                }
            },
            {
                display: '车牌号',
                name: 'na',
                align: 'left',
                width: 100
            },
            {
                display: '终端号',
                name: 'dn',
                align: 'left',
                width: 100
            },
            {
                display: '速度(km/h)',
                name: 'sp',
                align: 'left',
                width: 60,
                render: function (row) {
                    if (row.sp === 0)
                        return '0';
                    return common.round(row.sp, 1) + '';
                }
            },
            {
                display: '方向',
                name: 'sp',
                width: 40,
                render: function (row) {
                    return gpsDataParser.parseDirection(row);
                }
            },
            {
                display: '里程(km)',
                name: 'm',
                align: 'left',
                width: 50,
                render: function (row) {
                    return common.round(row.m, 3)
                }
            },
            {
                display: '状态',
                name: 's',
                align: 'left',
                width: 150,
                render: function (row) {
                    return gpsDataParser.parseStatus(row);
                }
            },
            {
                display: '多媒体进度',
                name: 'multimediapercent',
                width: 60,
                render: function (row) {
                    var html = [];
                    html.push('<div class="metro-gray" style="height:16px;width:100%;margin-top:2px;">');
                    if (row.multimediapercent)
                        html.push('<div class="metro-blue" style="height:100%;width:' + row.multimediapercent + '%;">' + row.multimediapercent
                            + '%</div>');
                    html.push('</div>');
                    return html.join('');
                }
            },
            {
                display: '地址',
                name: 'addr',
                align: 'left',
                width: 200
            }, {
                display: '最后定位时间',
                name: 'gt',
                width: 130
            }, {
                display: '数据接收时间',
                name: 'st',
                width: 130
            }];
        if ($('#datalog_auth').val() == 'true') {
            columns.push({
                display: '操作',
                isAllowHide: false,
                width: 50,
                render: function (row) {
                    return '<a href="javascript:datalog.show(\'' + row.dn + '\')">调试</a>';
                }
            });
        }
        if (!locate.gridLatests) {
            locate.gridLatests = $("#gridLatests").ligerGrid({
                columns: columns,
                root: 'rows',
                pageSize: 20,
                width: '100%',
                height: 198,
                data: data || {},
                rowHeight: 20,
                dataAction: 'local',
                alternatingRow: false,
                onAfterShowData: function (data) {
                    for (var i = 0; i < data.rows.length; i++) {
                        var item = data.rows[i];
                        locate.devices[item.dn] = item;
                        if (item.dn in locate.markers) {
                            locate.markers[item.dn].data = item;
                            continue;
                        }

                        var marker = webMap.createMarker({
                            map: locate.webMap,
                            data: item,
                            infoWindow: locate.infoWindow,
                            allowShowLabel: locate.allowShowLabel,
                            allowRotate: item.rotate === 1
                        });
                        locate.markers[item.dn] = marker;
                    }
                },
                onSelectRow: function (rowdata, rowindex) {
                    locate.showLocate(rowdata);
                }
            });
        } else {
            locate.gridLatests.loadData(data);
            locate.gridLatests.changePage('first');
        }
    },
    /**
     * 在地图中显示位置数据
     *
     * @param data
     *            位置数据
     */
    showLocate: function (data) {
        function show(data) {
            if (locate.markers[data.dn]) {
                var marker = locate.markers[data.dn];
                marker.data = data;
                marker.refresh();
                marker.openInfoWindow();
                return;
            }

            var marker = webMap.createMarker({
                map: locate.webMap,
                data: data,
                infoWindow: locate.infoWindow,
                allowShowLabel: locate.allowShowLabel,
                allowRotate: data.rotate === 1
            });
            locate.markers[data.dn] = marker;
            marker.openInfoWindow();
        }

        if (!data.olng || !data.olat) {
            locate.webMap.convertor(data.lng, data.lat, function (point, row) {
                row.olng = point.lng;
                row.olat = point.lat;
                show(row);
            }, data);
        } else
            show(data);
    },
    /**
     * 设备最佳视图
     */
    setViewport: function () {
        if (!locate.devices)
            return;
        var list = [];
        for (var i in locate.devices) {
            var item = locate.devices[i];
            list.push({
                olng: item.olng,
                olat: item.olat
            });
        }
        if (list.length <= 0)
            return;
        locate.webMap.setViewport(list);
        // if (!locate.markers)
        // return;
        // var markers = [];
        // for ( var i in locate.markers) {
        // var item = locate.markers[i];
        // markers.push(item.marker);
        // }
        // var markerClusterer = new
        // BMapLib.MarkerClusterer(locate.webMap.mapObject, {
        // markers : markers
        // });
    },
    /**
     * 对单台设备发送指令
     */
    sendInstruct: function (number) {
        instruct.showFeatures(number);
    },
    /**
     * 重设在线、离线数据
     */
    resetOnlines: {
        total: 0,
        onlines: 0,
        reset: function () {
            $('#txtAllVehicles').text(this.total);
            $('#txtOnlineVehicles').text(this.onlines);
            $('#txtOfflineVehicles').text(this.total - this.onlines);
        }
    },
    /**
     * 查找车辆
     */
    filterVehicles: function (state) {
        var keyword = $('#txtVehicleFilter').val();
        state = state || 'all';
        if (!locate.vehicles)
            return;
        locate.groupVehicles.clear();
        var motorcades = [];
        var devices = [];
        var onlines = 0;
        var total = 0;
        for (var x = 0; x < locate.vehicles.length; x++) {
            var item = locate.vehicles[x];
            if (item.type !== 0) {
                item.children = null;
                motorcades.push(item);
            } else if (item.na.indexOf(keyword) >= 0 || item.dn.indexOf(keyword) >= 0) {
                total++;
                var device = locate.devices[item.dn];
                onlines += device.o;
                if (state === "all") {
                    devices.push(device);
                } else if (state == "online" && device.o === 1) {
                    devices.push(device);
                } else if (state == "offline" && device.o !== 1)
                    devices.push(device);
            }
        }
        locate.groupVehicles.setData(motorcades.concat(devices));
        locate.loadLatests({
            rows: devices
        });
        locate.resetOnlines.total = total;
        locate.resetOnlines.onlines = onlines;
        locate.resetOnlines.reset();
    },
    track: {
        /**
         * 用于跟踪车辆时所用的线段
         */
        polyline: null,
        /**
         * 所跟踪车辆的设备号
         */
        deviceNumber: null,
        /**
         * 清除跟踪
         */
        clear: function () {
            if (this.polyline) {
                this.polyline.clear();
            }
            this.polyline = null;
            this.deviceNumber = null;
        },
        /**
         * 跟踪车辆
         */
        start: function (deviceNumber) {
            if (this.deviceNumber === deviceNumber) {
                this.clear();
                $('#btnTrack').html('跟踪');
                return;
            }
            this.clear();
            this.polyline = webMap.createPolyline({
                map: locate.webMap
            });
            this.deviceNumber = deviceNumber;
            $('#btnTrack').html('停止');
        },
        /**
         * 绘制多线段
         */
        draw: function (data) {
            if (!this.polyline)
                return;
            if (data.dn === this.deviceNumber) {
                this.polyline.addPoint(data.olng, data.olat);
                locate.webMap.panTo(data.olng, data.olat);
            }
            if (this.polyline.points.length > 200)
                this.polyline.points.splice(0, 100);
        }
    },
    /**
     * 轨迹回放
     */
    replay: function (deviceNumber) {
        var device = locate.devices[deviceNumber];
        var plateNumber = device.na;
        // 父frame
        parent.center.replay(deviceNumber, plateNumber);
    },
    /**
     * 查询报警
     */
    queryAlarm: function (deviceNumber) {
        var device = locate.devices[deviceNumber];
        var plateNumber = device.na;
        parent.center.queryAlarm(deviceNumber, plateNumber);
    },
    /**
     * 查询数据
     */
    queryData: function (deviceNumber) {
        var device = locate.devices[deviceNumber];
        var plateNumber = device.na;
        parent.center.queryData(deviceNumber, plateNumber);
    },
    /**
     * 处理报警
     */
    processAlarmOnce: function (alarm) {
        function removeAlarm(alarm) {
            var index = -1;
            for (var x = 0; x < locate.alarms.length; x++) {
                var item = locate.alarms[x];
                if (item.id == alarm.id) {
                    index = x;
                    break;
                }
            }
            locate.alarms.splice(index, 1);
            locate.gridUnhandledAlarms.reload({
                rows: locate.alarms
            });
            locate.resetAlarmIcon();
        }

        var url = '../alarm/processonce.form';
        var op = {
            url: url,
            urlParms: {
                alarmId: alarm.id
            },
            width: 550,
            height: 250,
            isHidden: false,
            title: '处理报警',
            onLoaded: function () {
                var form = $('form', locate.processAlarmOnceDialog.frame.document);
                if (form.length <= 0)
                    return;
                locate.processAlarmOnceDialog.frame.window.validate();
            },
            buttons: [{
                text: '确定',
                onclick: function (item, dialog) {
                    var form = $('form', dialog.frame.document);
                    if (form.length <= 0) {
                        removeAlarm(alarm);
                        dialog.close();
                        return;
                    }

                    if (dialog.frame.window.validate() == false) {
                        return;
                    }

                    var formData = form.serialize();
                    $.post(url, formData, function (data) {
                        var result = common.form.save(dialog, data, op);
                        if (result === true) {
                            removeAlarm(alarm);
                        }
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        locate.processAlarmOnceDialog = $.ligerDialog.open(op);
    },
    /**
     * 处理全部报警
     */
    processAlarmAll: function () {
        if (!locate.alarms || locate.alarms.lenght <= 0)
            return;
        var devices = {};
        for (var index = 0; index < locate.alarms.length; index++) {
            var item = locate.alarms[index];
            devices[item.dn] = item;
        }
        var numbers = [];
        for (var x in devices) {
            numbers.push(x);
        }
        var url = '../alarm/processall.form';
        var op = {
            url: url,
            width: 550,
            height: 250,
            isHidden: false,
            title: '全部处理',
            onLoaded: function () {
                locate.processAlarmAllDialog.frame.window.validate();
                locate.processAlarmAllDialog.frame.window.setNumbers(numbers);
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
                        if (result === true) {
                            locate.alarms = [];
                            locate.gridUnhandledAlarms.reload({
                                rows: []
                            });
                            locate.resetAlarmIcon();
                        }
                    });
                }
            }, {
                text: '取消',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }]
        };
        locate.processAlarmAllDialog = $.ligerDialog.open(op);
    },
    /**
     * 显示车辆资料
     */
    vehiceInfo: function (vehicleId) {
        var url = '../locate/vehicleinfo.iframe';
        var op = {
            url: url,
            urlParms: {
                vehicleId: vehicleId
            },
            width: 820,
            height: 485,
            title: '车辆资料',
            isHidden: false,
            buttons: {
                text: '关闭',
                onclick: function (item, dialog) {
                    dialog.close();
                }
            }
        };
        $.ligerDialog.open(op);
    },
    /**
     * 更新位置数据
     */
    updateTrack: function (source, target) {
        target.a = source.a;
        target.d = source.d;
        target.lat = source.lat;
        target.lng = source.lng;
        target.olat = source.olat;
        target.olng = source.olng;
        target.o = source.o;
        target.sp = source.sp;
        target.s = source.s;
        target.gt = source.gt;
        target.st = source.st;
        target.m = source.m;
        target.oil = source.oil;
        target.vss = source.vss;
        target.ovt = source.ovt;
        target.oid = source.oid;
        target.iot = source.iot;
        target.iid = source.iid;
        target.iof = source.iof;
        target.rid = source.rid;
        target.rt = source.rt;
        target.rf = source.rf;
        target.aid = source.adi;
        target.exs = source.exs;
        target.ios = source.ios;
        target.ad0 = source.ad0;
        target.ad1 = source.ad1;
        target.net = source.net;
        target.sat = source.sat;
        target.from = source.from;
    },
    /**
     * 更新设备在线状态
     */
    updateOnlineStatus: function (device) {
        try {
            // 更新树中图标
            var png = device.o === 1 ? "../resources/css/x16/online.png" : "../resources/css/x16/offline.png";
            if (device.icon != png) {
                device.icon = png
                locate.groupVehicles.update(device, {
                    icon: device.icon
                });
            }
            // 刷新车辆标注
            var marker = locate.markers[device.dn];
            marker && marker.refresh();

            // 更新最后位置数据表格行
            if (locate.gridLatests.existRecord(device) === true)
                locate.gridLatests.reRender({
                    rowdata: device
                });

            // 刷新在线数量
        } catch (e) {
            if (console)
                console.log(e);
        }
    },
    /**
     * 处理实时位置数据
     */
    handleDeviceRealtimeTrack: function (item) {
        if (!locate.webMap)
            return;
        locate.webMap.convertor(item.data.lng, item.data.lat, function (point) {
            // var point = locate.webMap.correct(item.data.lng, item.data.lat);
            item.data.olat = point.lat;
            item.data.olng = point.lng;

            var device = locate.devices[item.data.dn];
            if (!device)
                return;
            // 判断时间
            try {
                var oldTime = device.gt.toDate().getTime();
                var newTime = item.data.gt.toDate().getTime();
                if (newTime < oldTime)
                    return;
            } catch (e) {

            }
            locate.updateTrack(item.data, device);

            locate.updateOnlineStatus(device);

            // 更新报警图标
            if (item.data.a !== 0) {
                item.data.na = device.na;
                locate.alarms.push(item.data);
                locate.resetAlarmIcon();
            }
            locate.track.draw(device);
        });
    },
    /**
     * 处理上下线通知
     */
    handleGatewayStatisticsDeviceOnlineOffline: function (item) {
        var device = locate.devices[item.data.number];
        if (device) {
            // 更新最后位置数据表格行
            if (item.data.on === true) {
                device.o = 1;
                locate.resetOnlines.onlines++;
            } else {
                locate.resetOnlines.onlines--;
                device.o = 0;
            }
            locate.resetOnlines.reset();
            locate.updateOnlineStatus(device);
        }
    },
    /**
     * 多媒体上传进度
     */
    handleDeviceMultimediaUploadPercent: function (item) {
        var device = locate.devices[item.data.number];
        if (device) {
            device.multimediapercent = item.data.percent;
            if (locate.gridLatests.existRecord(device) === true)
                locate.gridLatests.reRender({
                    rowdata: device
                });
        }
    },
    /**
     * 多媒体事件报告
     */
    handleDeviceMultimediaEventReport: function (item) {
        var device = locate.devices[item.data.number];
        if (device)
            item.data.plateNumber = device.na;
        else
            item.data.plateNumber = device.number;
        locate.unread.multimediaEventReprot.push(item.data);
        locate.unread.refresh();
    },
    /**
     * 终端事件报告
     */
    handleDeviceEventReport: function (item) {
        var device = locate.devices[item.data.number];
        if (device)
            item.data.plateNumber = device.na;
        else
            item.data.plateNumber = device.number;
        locate.unread.deviceEventReport.push(item.data);
        locate.unread.refresh();
    },
    /**
     * 处理终端升级结查报告
     */
    handleDeviceUpgradeResultReport: function (item) {
        var device = locate.devices[item.data.number];
        if (device)
            item.data.plateNumber = device.na;
        else
            item.data.plateNumber = device.number;
        console.log(item.data);
        locate.unread.deviceUpgradeResultReport.push(item.data);
        locate.unread.refresh();
    },
    /**
     * 处理终端指令结果报告
     */
    handleDeviceInstructResult: function (item) {
        if (!instruct || !instruct.currnetPad)
            return;
        instruct.currnetPad.updateInstructResult(item);
    },
    /**
     * 网络连接
     */
    connect: function () {
        var httpRootPath = $('#httpRootPath').val();
        var wsRootPath = httpRootPath.replace("http", "ws");
        if ('WebSocket' in window) {
            locate.websocket = new WebSocket(wsRootPath + "/webSocketServer");
        } else if ('MozWebSocket' in window) {
            locate.websocket = new MozWebSocket(wsRootPath + "/webSocketServer");
        } else {
            locate.websocket = new SockJS(httpRootPath + "/sockjs/webSocketServer");
        }
        locate.websocket.onopen = function (e) {
            if (console)
                console.log('已建立websocket连接......');
        };
        locate.websocket.onmessage = function (e) {
            try {
                var list = eval(e.data);
                for (var x = 0; x < list.length; x++) {
                    var item = list[x];
                    if (!item.kind)
                        continue;
                    switch (item.kind) {
                        case 'server.timestamp':
                            if (!locate.serverTimestamp)
                                locate.serverTimestamp = item.data;
                            else
                                locate.refreshGroups();
                            break;
                        case 'device.realtime.track':
                            locate.handleDeviceRealtimeTrack(item);
                            break;
                        case 'gateway.statistics.device.onlineoffline':
                            locate.handleGatewayStatisticsDeviceOnlineOffline(item);
                            break;
                        case 'device.multimedia.upload.percent':
                            locate.handleDeviceMultimediaUploadPercent(item);
                            break;
                        case 'device.multimedia.event.report':
                            locate.handleDeviceMultimediaEventReport(item);
                            break;
                        case 'device.event.report':
                            locate.handleDeviceEventReport(item);
                            break;
                        case 'device.upgrade.result.report':
                            locate.handleDeviceUpgradeResultReport(item);
                            break;
                        case 'device.instruct.result':
                            locate.handleDeviceInstructResult(item);
                            break;
                        default:
                            break;
                    }
                }

            } catch (e) {
                if (console)
                    console.log(e);
            }
        };
        locate.websocket.onerror = function (e) {
            if (console)
                console.log('websocket连接错误......');
        };
        locate.websocket.onclose = function (e) {
            if (console)
                console.log('已断开websocket连接......');
        };
    },
    layout: function () {
        $("#locateLayout").ligerLayout({
            leftWidth: 300,
        });

        var height = $(document).height();
        $('#locateLayout #divLocateAllMap').height(height - 208);
    },
    /**
     * 重设报警图标
     */
    resetAlarmIcon: function () {
        var div = $('#locateLayout #btnAlarm > div');
        if (locate.alarms.length > 0) {
            if (!div.hasClass('i-64-alarmon')) {
                div.removeClass('i-64-alarmoff');
                div.addClass('i-64-alarmon');
            }
        } else {
            if (!div.hasClass('i-64-alarmoff')) {
                div.removeClass('i-64-alarmon');
                div.addClass('i-64-alarmoff');
            }
        }
    },
    resize: function () {
        var height = $(document).height();
        var divCenterBottom = $('#locateLayout #divCenterBottom');
        if (divCenterBottom.css('display') == 'none') {
            $('#locateLayout #divLocateAllMap').height(height - 10);
        } else {
            $('#locateLayout #divLocateAllMap').height(height - 208);
        }
    }
};
$(function () {
    locate.layout();

    webMap.events.onMapLoadCompleted['locateMap'] = locate.onMapLoaded;
    webMap.createMap("locateMap");

    $('#locateLayout #btnSearchVehicle').click(function () {
        locate.filterVehicles();
    });

    $("#btnAllVehicles").click(function () {
        locate.filterVehicles("all");
    });

    $("#btnOlineVehicles").click(function () {
        locate.filterVehicles("online");
    });

    $("#btnOfflineVehicles").click(function () {
        locate.filterVehicles("offline");
    });

    $('#locateLayout #divMapSpliter').click(function () {
        $('#locateLayout #divCenterBottom').toggle();
        locate.resize();
    });

    $('#locateLayout #btnReplay').click(function () {
        if (!locate.groupVehicles)
            return;
        var nodes = locate.groupVehicles.getChecked();
        if (nodes && nodes.length > 0) {
            for (var index in nodes) {
                var node = nodes[index];
                if (node.data.type === 0) {
                    locate.replay(node.data.dn);
                    return;
                }
            }
        } else {
            var node = locate.groupVehicles.getSelected();
            if (!node)
                return;
            locate.replay(node.data.dn);
        }
    });

    $('#locateLayout #btnInstruct').click(function () {
        if (!locate.groupVehicles)
            return;
        var nodes = locate.groupVehicles.getChecked();
        if (nodes && nodes.length > 0) {
            var deviceNumbers = [];
            for (var index = 0; index < nodes.length; index++) {
                var node = nodes[index];
                if (node.data.type === 0)
                    deviceNumbers.push(node.data.dn);
            }
            if (deviceNumbers.length <= 0)
                return;
            instruct.deviceNumbers = deviceNumbers;
            locate.sendInstruct(deviceNumbers[0]);
        } else {
            var node = locate.groupVehicles.getSelected();
            if (!node || !node.data || node.data.type !== 0)
                return;
            locate.sendInstruct(node.data.dn);
        }
    });

    $('#locateLayout #btnDataQuery').click(function () {
        if (!locate.groupVehicles)
            return;
        var nodes = locate.groupVehicles.getChecked();
        if (nodes && nodes.length > 0) {
            for (var index in nodes) {
                var node = nodes[index];
                if (node.data.type === 0) {
                    locate.queryData(node.data.dn);
                    return;
                }
            }
        } else {
            var node = locate.groupVehicles.getSelected();
            if (!node)
                return;
            locate.queryData(node.data.dn);
        }
    });

    $(window).resize(function () {
        locate.resize();
    });

    $('#locateLayout #btnRefreshVehicle').click(function () {
        locate.refreshGroups();
    });

    $('#btnMessage').click(
        function () {
            if (!locate.dialogUnreadMessage) {
                locate.dialogUnreadMessage = $.ligerDialog.open({
                    title: '未读消息',
                    width: 800,
                    height: 400,
                    target: $("#dialogUnreadMessage")
                });
                $('#unreadPages').ligerTab({
                    onAfterSelectTabItem: function (targettabid) {
                        switch (targettabid) {
                            case 'multimediaEventReprotPage':
                                locate.gridMultimediaEventReprot.reload({
                                    rows: locate.unread.multimediaEventReprot
                                });
                                break;
                            case 'deviceEventReprotPage':
                                locate.gridDeviceEventReport.reload({
                                    rows: locate.unread.deviceEventReport
                                });
                                break;
                            case 'deviceUpgradeResultReprotPage':
                                locate.gridDeviceUpgradeResultReport.reload({
                                    rows: locate.unread.deviceUpgradeResultReport
                                });
                                break;
                        }
                    }
                });
                locate.gridMultimediaEventReprot = $("#gridMultimediaEventReprot").ligerGrid(
                    {
                        columns: [
                            {
                                display: '车牌号',
                                name: 'plateNumber',
                                width: 150,
                                align: 'left'
                            },
                            {
                                display: '时间',
                                name: 'time',
                                width: 150,
                                type: 'date'
                            },
                            {
                                display: '多媒体ID',
                                name: 'mediaId',
                                width: 50
                            },
                            {
                                display: '媒体类型',
                                name: 'mediaType',
                                width: 50
                            },
                            {
                                display: '格式类型',
                                name: 'formatType',
                                width: 50
                            },
                            {
                                display: '事件类型',
                                name: 'eventType',
                                width: 100
                            },
                            {
                                display: '通道ID',
                                name: 'channelId',
                                width: 50
                            },
                            {
                                display: '操作',
                                render: function (row) {
                                    var html = [];
                                    html.push('<a href="javascript:locate.unread.readMultmediaEventReport(\'' + row.id + '\')">知道了</a>');
                                    html.push('&nbsp;||&nbsp;');
                                    html.push('<a href="javascript:locate.unread.pickupMultmedia(\'' + row.id + '\',\'' + row.number
                                        + '\',' + row.mediaId + ')">提取</a>');
                                    return html.join('');
                                }
                            }],
                        root: 'rows',
                        width: '100%',
                        pageSizeOptions: [5, 10, 15, 20],
                        height: 300,
                        allowHideColumn: false,
                        rownumbers: true,
                        data: {
                            rows: locate.unread.multimediaEventReprot
                        }
                    });

                locate.gridDeviceEventReport = $("#gridDeviceEventReport").ligerGrid({
                    columns: [{
                        display: '车牌号',
                        name: 'plateNumber',
                        width: 150,
                        align: 'left'
                    }, {
                        display: '时间',
                        name: 'time',
                        width: 150,
                        type: 'date'
                    }, {
                        display: '事件ID',
                        name: 'eventId',
                        width: 50
                    }, {
                        display: '事件内容',
                        name: 'content',
                        width: 250
                    }, {
                        display: '操作',
                        width: 50,
                        render: function (row) {
                            return '<a href="javascript:locate.unread.readDeviceEventReport(\'' + row.id + '\')">知道了</a>';
                        }
                    }],
                    root: 'rows',
                    width: '100%',
                    pageSizeOptions: [5, 10, 15, 20],
                    height: 300,
                    allowHideColumn: false,
                    rownumbers: true,
                    data: {
                        rows: locate.unread.deviceEventReprot
                    }
                });
                locate.gridDeviceUpgradeResultReport = $("#gridDeviceUpgradeResultReport").ligerGrid({
                    columns: [{
                        display: '车牌号',
                        name: 'plateNumber',
                        width: 150,
                        align: 'left'
                    }, {
                        display: '时间',
                        name: 'time',
                        width: 150,
                        type: 'date'
                    }, {
                        display: '升级结果',
                        name: 'result',
                        width: 80
                    }, {
                        display: '升级类型',
                        name: 'type',
                        width: 180
                    }, {
                        display: '操作',
                        width: 50,
                        render: function (row) {
                            return '<a href="javascript:locate.unread.readDeviceUpgradeResultReport(\'' + row.id + '\')">知道了</a>';
                        }
                    }],
                    root: 'rows',
                    width: '100%',
                    pageSizeOptions: [5, 10, 15, 20],
                    height: 300,
                    allowHideColumn: false,
                    rownumbers: true,
                    data: {
                        rows: locate.unread.deviceUpgradeResultReprot
                    }
                });
            } else
                locate.dialogUnreadMessage.show();
        });

    $('#locateLayout #btnHandleOnceAlarm').click(function () {
        if (!locate.gridUnhandledAlarms)
            return;
        var row = locate.gridUnhandledAlarms.getSelectedRow();
        if (!row)
            return;
        locate.processAlarmOnce(row);
    });

    $('#locateLayout #btnHandleAllAlarm').click(function () {
        if (!locate.gridUnhandledAlarms)
            return;
        var rows = locate.gridUnhandledAlarms.getData();
        if (!rows || rows.length <= 0) {
            return;
        }
        $.ligerDialog.confirm('真地要处理所有报警吗？', function (yes, value) {
            if (!yes)
                return;
            locate.processAlarmAll();
        });
    });

    $('#locateLayout  #btnAlarm').click(function () {
        if (!locate.dialogHandleAlarms) {
            locate.dialogHandleAlarms = $.ligerDialog.open({
                title: '处理报警',
                width: 800,
                height: 400,
                target: $("#dialogHandleAlarms")
            });
            locate.gridUnhandledAlarms = $("#gridUnhandledAlarms").ligerGrid({
                columns: [{
                    display: '',
                    isAllowHide: false,
                    align: 'left',
                    width: 'auto',
                    headerRender: function (column) {
                        return '<div class="mon-left">报警位置数据</div>';
                    },
                    render: function (row) {
                        var html = [];
                        html.push('<div style="cursor:pointer; padding:3px;">');
                        html.push(' <div>车牌号：<b>' + row.na + '</b></div>');
                        html.push(' <div>状态：<span>' + gpsDataParser.parseStatus(row) + '</span></div>');
                        html.push(' <div>' + '时间' + '：' + row.gt + ' ' + '速度' + '：' + row.sp + '</div>');
                        html.push(' <div class="error">' + gpsDataParser.parseAlarm(row) + '</div>');
                        html.push('</div>');
                        return html.join('');
                    }
                }],
                onSelectRow: function (rowdata, rowid, rowobj) {
                    locate.showLocate(rowdata);
                },
                inWindow: false,
                fixedCellHeight: false,
                alternatingRow: false,
                root: 'rows',
                record: 'total',
                data: {
                    rows: locate.alarms
                },
                width: '100%',
                height: 300
            });
        } else {
            locate.dialogHandleAlarms.show();
            locate.gridUnhandledAlarms.loadData({
                rows: locate.alarms
            });
        }
    });

    locate.connect();
    window.setInterval(function () {
        if (locate.websocket) {
            var state = locate.websocket.readyState;
            if (state != 1) {
                locate.connect();
            } else {
                var now = new Date();
                var time = now.getTime() - locate.HeartbeatTime.getTime()
                if (time > 2 * 60 * 1000) {
                    locate.HeartbeatTime = now;
                    if (locate.serverTimestamp)
                        locate.websocket.send(locate.serverTimestamp);
                    else
                        locate.websocket.send("OK");
                }
            }
        } else {
            locate.connect();
        }
    }, 1000 * 30);
});