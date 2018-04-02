/**
 *
 */
window.locate={
    locate :marker,
    map :null,
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
    //在地图中显示位置？


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
    onMapLoaded:function(map){
        locate.web=map;
        locate:infoWindow =webMap.createInfoWindow({
            map:locate.web,
            data:undefined,
            width:260,
            allowQueryAddress:true,
            makeTitle:function(){
                if(!this.data)return '';
                var html=[];
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
        locate.loadGroups();
        setTimeout(function () {
            locate.drawMapLayers();
        }, 1000 * 10);
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
    loadGroups: function () {
        /*   locate.groupVehicles = $("#groupVehicles").ligerTree({
               url: '../locate/groupVehicles',
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
           });*/
    },
    //onSelectRow请求,但是事实上不是这个,他们连接的桥梁
}
$(function(){
    marker();
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
    locate.map.addOverlay(marker);
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


