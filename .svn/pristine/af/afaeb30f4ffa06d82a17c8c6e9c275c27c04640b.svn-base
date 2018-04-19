/**
 *
 */
window.locate={

    /**
     * 地图
     */

    opts:undefined,
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
     * 所有未处理的报警记录
     */
    alarms: [],
    /**
     * 地图图层
     */
    mapLayers: null,
    /**
     * 设备列表
     */
    devices: {},
    /**
     * 设备点
     */
    devicespoint: [],
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
      /*  /!**
         * 刷新数量提示
         *!/
        refresh: function () {
            var total = this.multimediaEventReprot.length;
            total += this.deviceEventReport.length;
            total += this.deviceUpgradeResultReport.length;

            $('#txtMessageTotal').text(total);
            if (total > 0)
                $('#txtMessageTotal').show();
            else
                $('#txtMessageTotal').hide();
        },*/
        /**
         * 读取未读多媒体事件报告
         */
     /*   readMultmediaEventReport: function (id) {
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
        },*/
        /**
         * 读取终端事件报告
         */
       /* readDeviceEventReport: function (id) {
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
        },*/
       /* readDeviceUpgradeResultReport: function (id) {
            function removeDeviceUpgradeResultReport(id) {
                var index = -1;
                for (var x = 0; x < locate.unread.deviceUpgradeResultReport.length; x++) {
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
        },*/
        /**
         * 提取多媒体
         */
       /* pickupMultmedia: function (eventId, deviceNumber, mediaId) {
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
        }*/
    },
    /**
     * 最后位置表格
     */
    gridLatests: undefined,
    /**
     * 组别与车辆树
     */
    groupVehicles: undefined,
    //在地图中显示位置？

    isInsidePolygon: function (pt, poly) {
        for (var c = false, i = -1, l = poly.length, j = l - 1; ++i < l; j = i)
            ((poly[i].lat <= pt.lat && pt.lat < poly[j].lat) || (poly[j].lat <= pt.lat && pt.lat < poly[i].lat))
            && (pt.lng < (poly[j].lng - poly[i].lng) * (pt.lat - poly[i].lat) / (poly[j].lat - poly[i].lat) + poly[i].lng) && (c = !c);
        return c;
    },
    showLocate: function (data) {
        //对数据data处理标注, 传过来的data lng和lat 加工处理成olng和olat
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
    onMapLoaded: function (map) {
        locate.webMap = map;
        //query(false);
      /*  $.get('../mapOption/query', function (r) {
            if (!r.lng)
                return;
            locate.webMap.convertor(r.lng, r.lat, function (center) {
                locate.webMap.setCenter(center);
                locate.webMap.setZoom(r.zoom);
            });
        });*/
       query(false);
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

        window.common = {
            debug: function (obj) {
                obj.toString();
            },
            guid: function () {
                var guid = "";
                for (var i = 1; i <= 32; i++) {
                    var n = Math.floor(Math.random() * 16.0).toString(16);
                    guid += n;
                    if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
                        guid += "-";
                }
                return guid;
            },
            bitsToNumber: function (bits) {
                /**
                 * 将;分隔的开关位合成32位整数
                 */
                if (!bits)
                    return 0;
                var flag = 0;
                var list = bits.split(';');
                for (var i = 0; i < list.length; i++) {
                    var item = list[i];
                    var bit = parseInt(item);
                    flag = flag | (1 << bit);
                }

                return flag;
            },
            /**
             * 将32位整数按位以;分隔组成字符串
             */
            numberToBits: function (number) {

                var bits = [];
                for (var i = 0; i < 32; i++) {
                    if ((number >>> i & 0x01) == 0x01)
                        bits.push(i);
                }

                return bits.join(';');
            },
            /**
             * 取几位小数点
             */
            round: function (number, decimal) {
                return Math.round(number * Math.pow(10, decimal)) / Math.pow(10, decimal);
            },
            /**
             * 将毫秒数转成时段
             */
            timespan: function (milliseconds) {
                var days = Math.floor(milliseconds / 1000 / 60 / 60 / 24);
                var hours = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000) / 1000 / 60 / 60);
                var minutes = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000) / 1000 / 60);
                var seconds = Math.floor((milliseconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000 - minutes * 60 * 1000) / 1000);
                var timespans = [];
                if (days > 0)
                    timespans.push(days + "天");
                if (hours > 0)
                    timespans.push(hours + "小时");
                if (minutes > 0)
                    timespans.push(minutes + "分");
                if (seconds > 0)
                    timespans.push(seconds + "秒");

                return timespans.join(':');
            },
            /**
             * 显示提示窗口
             *
             * @param type
             *            类型：warn、success、error、question
             * @param content
             *            内容
             * @param seconds
             *            停留秒数
             */
            tip: function (type, content, seconds) {
                alert("此区域无车辆");
                /*tip = $.ligerDialog.tip({
                    type: type,
                    content: content
                });*/
            /*    setTimeout(function () {
                    tip.close();
                }, seconds * 1000);*/
            },
            form: {
                check: function (op) {
                    if ($('#' + op.formName).length <= 0)
                        return false;
                    var v = $('#' + op.formName).validate(op.rules);
                    var result = v.checkForm();
                    v.showErrors();
                    return result;
                },
                update: function (op) {
                    $('#' + op.div).empty();
                    $('#' + op.div).html(op.html);
                    if (op.formName)
                        this.check(op);
                    if (op.onloaded) {
                        op.onloaded();
                    }
                },
                save: function (dialog, data, op) {
                    var tip = undefined;
                    var result = false;
                    var type = typeof (data);
                    if (type == 'string') {
                        if (typeof data == 'string') {
                            dialog.frame.document.write(data);
                            dialog.frame.document.close()
                        }
                    } else if (data.code > 0) {
                        tip = $.ligerDialog.tip({
                            type: 'error',
                            content: data.error
                        });
                    } else {
                        tip = $.ligerDialog.tip({
                            type: 'success',
                            content: '数据保存成功!'
                        });
                        dialog.close();
                        result = true;
                    }
                    if (tip)
                        setTimeout(function () {
                            tip.close();
                        }, 3000);

                    return result;
                },
                remove: function (data) {
                    var tip = undefined;
                    var result = false;
                    if (data.code > 0) {
                        tip = $.ligerDialog.tip({
                            type: 'error',
                            content: data.error
                        });
                    } else {
                        tip = $.ligerDialog.tip({
                            type: 'success',
                            content: '数据删除成功!'
                        });
                        result = true;
                    }
                    if (tip)
                        setTimeout(function () {
                            tip.close();
                        }, 3000);

                    return result;
                }
            },
            checkData: function (data, error, success) {
                var tip = undefined;
                var result = false;
                if (data.code > 0 && error) {
                    tip = $.ligerDialog.tip({
                        type: 'error',
                        content: data.error
                    });
                } else {
                    if (success)
                        tip = $.ligerDialog.tip({
                            type: 'success',
                            content: success
                        });
                    result = true;
                }
                if (tip)
                    setTimeout(function () {
                        tip.close();
                    }, 3000);

                return result;
            },
            showDialog: function (op) {
                // 添加html的Div
                var divId = common.guid();
                op.div = divId;
                var $divId = '#' + divId;
                var div = '<div id="' + divId + '"></div>';
                $('#dialogs').append($(div));
                // 更新div的内容
                // $($divId).html(o.html);
                op.target = $('#' + divId);

                var dialog = $.ligerDialog.open(op);
                this.form.update(op);

                return dialog;
            },
            setCookie: function (name, value) {
                document.cookie = name + "=" + escape(value);
            },
            // 读取cookie
            getCookie: function (name) {
                var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
                if (arr != null)
                    return unescape(arr[2]);
                return null;

            },
            // 删除cookie
            delCookie: function (name) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval = readCookie(name);
                if (cval != null)
                    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
            }

        };

      /*  $.get('../locate/maptools.panel', function (html) {
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
        });*/
      /*  locate.loadGroups();*/
    /*    setTimeout(function () {
            locate.drawMapLayers();
        }, 1000 * 10);*/
       // locate.setCustomControl();
    },
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
        $.get(   {
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

    //onSelectRow请求,但是事实上不是这个,他们连接的桥梁
    drawingOpen : function(callback) {
        if (!this.drawingManager) {
            var styleOptions = {
                strokeColor : "blue", // 边线颜色。
                // fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
                strokeWeight : 2, // 边线的宽度，以像素为单位。
                strokeOpacity : 0.6, // 边线透明度，取值范围0 - 1。
                fillOpacity : 0.2, // 填充的透明度，取值范围0 - 1。
                strokeStyle : 'solid' // 边线的样式，solid或dashed。
            }
            // 实例化鼠标绘制工具
            this.drawingManager = new BMapLib.DrawingManager(this.mapObject, {
                isOpen : false, // 是否开启绘制模式
                enableDrawingTool : false, // 是否显示工具栏
                drawingToolOptions : {
                    anchor : BMAP_ANCHOR_TOP_RIGHT, // 位置
                    offset : new BMap.Size(10, 40), // 偏离值
                    drawingModes : [ BMAP_DRAWING_CIRCLE, BMAP_DRAWING_POLYGON, BMAP_DRAWING_RECTANGLE ]
                },
                circleOptions : styleOptions, // 圆的样式
                polylineOptions : styleOptions, // 线的样式
                polygonOptions : styleOptions, // 多边形的样式
                rectangleOptions : styleOptions
                // 矩形的样式
            });
            // 添加鼠标绘制工具监听事件，用于获取绘制结果
            this.drawingManager.addEventListener('overlaycomplete', callback);
        }
        this.drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
        this.drawingManager.open();
    },
    overlayComplete : function(e) {
        locate.webMap.removeOverlay(e.overlay);
        locate.webMap.drawingClose();
        var insides = [];

        for (var attr in locate.devicespoint) {
            var device = locate.devicespoint[attr];
            var pt = {
                lng : device.lng,
                lat : device.lat
            };
            if (locate.isInsidePolygon(pt, e.overlay.getPath())) {
                insides.push(device);
              $("#panelqucar").toggle(500);
                console.log(insides);

                return;
            }
        }
        if (insides.length <= 0) {
            common.tip('info', '此区域无车辆！', 3);
            return;
        }
      /*  locate.loadLatests({
            rows : insides
        });*/
    },
    /**
     * 关闭手绘面板
     */
    drawingClose : function(callback) {
        if (!this.drawingManager) {
            return;
        }
        this.drawingManager.close();
    },
}
$(function(){
    //创建地图
    webMap.events.onMapLoadCompleted['locateMap'] = locate.onMapLoaded;
    webMap.createMap("locateMap");

    // webMap.events.onMapLoadCompleted['locateMap'] = locate.onMapLoaded;
    // webMap.createMap("locateMap");
});
function zhenshi(){
    var data = [{a:0,ad0:0,  ad1 :  0 ,aid: 0,alt: 0,d : 120,dn: "120187322661",exs:0,gt : "2018-03-29 07:17:36",
        icon
            :
            "../resources/css/x16/online.png",
        id
            :
            "5aab54147b0368691e55c68e",
        iid
            :
            0,
        iof
            :
            0,
        ios
            :
            0,
        iot
            :
            0,
        lat
            :
            23.168067999999998,
        lng
            :
            113.4284,
        m
            :
            0,
        marker
            :
            "00.png",
        na
            :
            "京B37A95",
        net
            :
            24,
        o
            :
            1,
        oid
            :
            0,
        oil
            :
            0,
        olat
            :
            23.171507254676,
        olng
            :
            113.4404338996,
        ovt
            :
            0,
        pid
            :
            "5a72abd83d769a75b6309dee",
        rf
            :
            0,
        rid
            :
            0,
        rotate
            :
            1,
        rt
            :
            0,
        s
            :
            6,
        sat
            :
            0,
        sp
            :
            0,
        st
            :
            "2018-03-29 15:17:38",
        treedataindex
            :
            2,
        type
            :
            0,
        val
            :
            1,
        vss
            :
            0,
        __id
            :
            "r1001",
        __index
            :
            0,
        __nextid
            :
            "r1002",
        __previd
            :
            -1,
        __status
            :
            "add"}]
    locate.showLocate(data);
}

function marker(){
    locate.map = new BMap.Map('locateMap');
    locate.map.enableScrollWheelZoom();
    var point = new BMap.Point(116.404, 39.915);
    var marker = new BMap.Marker(point);
    locate.map.centerAndZoom(point, 15);
   // locate.map.addOverlay(marker);
}
function biaozhu(){
    var opts = {
        width : 200,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title : "海底捞王府井店" , // 信息窗口标题
        enableMessage:true,//设置允许信息窗发送短息
        message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
    }
    var point = new BMap.Point(116.412222, 39.912345);
    var marker = new BMap.Marker(point);  // 创建标注
    locate.map.addOverlay(marker);
    var infoWindow = new BMap.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts);  // 创建信息窗口对象
    marker.addEventListener("click", function(){
        locate.map.openInfoWindow(infoWindow,point); //开启信息窗口
    });// 将标注添加到地图中
    //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}




