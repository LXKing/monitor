$(function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //日期时间选择器
        laydate.render({
            elem: '#allStartTime'
            , type: 'datetime'
            , value: new Date()
            , theme: '#0488d1'
            , change: function (value, date, endDate) {
                replay.txtStartTime = value;

            }
        })
        //日期时间选择器
        laydate.render({
            elem: '#allEndTime'
            , type: 'datetime'
            , value: new Date()
            , theme: '#0488d1'
            , change: function (value, date, endDate) {
                replay.txtEndTime = value;

            }
        });

//     //日期时间选择器
//     laydate.render({
//         elem: '#burstStartTime'
//         ,type: 'datetime'
//         ,change: function(value, date, endDate){
//             replay.txtStartTime =value;
//
//         }
//     });
//
//
//
//
//     //日期时间选择器
//     laydate.render({
//         elem: '#burstEndTime'
//         ,type: 'datetime'
//         ,change: function(value, date, endDate){
//             replay.txtStartTime =value;
//
// }
//     });

        //日期时间选择器
        laydate.render({
            elem: '#txtEndTime'
            , type: 'datetime'
            , change: function (value, date, endDate) {
                replay.txtEndTime = value;

            }
        });


    });


    window.replay = {

        /**
         * 设备号
         */
        deviceNumber: null,
        /**
         * 分页下载时的页大小
         */
        pageSize: 100,
        /**
         * 播放状态:初始化(init),下载中(loading),下载完成(loaded), 播放中(play),暂停(pause),停止(stop)
         */
        playStatus: 'init',
        /**
         * setTimeout
         */
        timer: null,

        /**
         * 分页的当前页
         */
        layerpageindex: null,

        pagelength: null,

        /**
         * 播放速度
         */
        playAccelerate: 1000,
        /**
         * 位置数据总数
         */
        trackCount: 0,
        /**
         * 位置数据
         */
        tracks: [],
        /**
         * 正在播放位置数据
         */
        playtracks: [],

        numberstr: null,

        /**
         * 正在播放的tihs
         */
        playthis: null,
        /**
         * 正在播放位置数据
         */
        drivingedtracks: [],

        /**
         * 页序号
         */
        pageIndex: 0,
        /**
         * 地图
         */
        webMap: null,
        /**
         * 信息窗口
         */
        infoWindow: null,
        /**
         * 行程
         */
        trips: {
            list: [],
            days: {}
        },
        /**
         * 停车
         */
        parks: {
            list: [],
            days: {}
        },
        /**
         * 报警
         */
        alarms: {
            list: [],
            days: {}
        },
        /**
         * 线段
         */
        speeds: [],

        /**
         * 地图加载完成回调
         */
        webMap: undefined,

        onMapLoaded: function (map) {
            replay.webMap = map;
            replay.infoWindow = webMap.createInfoWindow({
                map: map,
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

                    return html.join("");
                }
            });


        },

        /**
         * 数据下载完成
         */
        downloadCompleted: function () {
            replay.playStatus = 'loaded';
            this.clear();
            this.trips = {
                list: [],
                days: {}
            };
            this.parks = {
                list: [],
                days: {}
            };
            this.alarms = {
                list: [],
                days: {}
            };
            this.speeds = [];

            var acc = null;
            /**
             * 行程
             */
            var trip = {
                data: []
            };
            /**
             * 速度计
             */
            var velocimeter = {
                total: 0,
                times: 0
            };
            /**
             * 里程计
             */
            var milemeter = {
                start: this.tracks[0].m,
                befor: 0,
                reset: null,
                end: this.tracks[this.tracks.length - 1].m
            };
            /**
             * 油量计
             */
            oilmeter = {
                total: 0,
                befor: null
            };
            /**
             * 行驶时间
             */
            var runtimes = 0;

            for (var x = 0; x < this.tracks.length; x++) {
                var item = this.tracks[x];
                item.na = this.plateNumber;

                if (item.a !== 0) {
                    var date = item.gt.toDate().toShortDateString();
                    this.alarms.days[date] = this.alarms.days[date] || [];
                    this.alarms.days[date].push(item);
                    this.alarms.list.push(item);
                }
                this.speeds.push({
                    x: item.gt.toDate().getTime(),
                    y: item.sp
                });

                // 速度
                if (item.sp > 0) {
                    velocimeter.total += item.sp;
                    velocimeter.times++;
                }
                // 里程是否有归零
                if (!item.reset)
                    milemeter.befor = item.m;
                if (item.m < milemeter.befor && !item.reset)
                    milemeter.reset = item.m;

                // 统计油耗
                if (oilmeter.befor) {
                    var oil = oilmeter.befor - item.oil;
                    if (oil > 0)
                        oilmeter.total += oil;
                }
                oilmeter.befor = item.oil;

                // 分析
                var accStatus = gpsDataParser.parseAcc(item);
                console.log("accStatus : " + accStatus);
                if (!trip.acc) {
                    trip.acc = accStatus;
                    trip.timeStart = item.gt;
                    trip.pointStart = {
                        lng: item.olng,
                        lat: item.olat
                    };
                    trip.mileageStart = item.m;
                    trip.oilStart = item.oil;
                } else if (trip.acc === accStatus) {
                    trip.data.push(item);
                    // 查检最后一条记录
                    if (x == this.tracks.length - 1) {
                        trip.timeEnd = item.gt;
                        trip.pointEnd = {
                            lng: item.olng,
                            lat: item.olat
                        };
                        trip.mileageEnd = item.m;
                        trip.oilEnd = item.oil;
                        if (trip.acc == '点火') {// 行驶中
                            var date = trip.timeStart.toDate().toShortDateString();
                            this.trips.days[date] = this.trips.days[date] || [];
                            this.trips.days[date].push(trip);
                            this.trips.list.push(trip);

                            // 统计行驶时间
                            var end = trip.timeEnd.toDate().getTime();
                            var start = trip.timeStart.toDate().getTime();
                            runtimes += end - start;
                        } else {// 停车中
                            var date = trip.timeStart.toDate().toShortDateString();
                            this.parks.days[date] = this.parks.days[date] || [];
                            this.parks.days[date].push(trip);
                            this.parks.list.push(trip);
                            // 找出上一个行程
                            if (this.trips.list.length > 0) {
                                var prev = this.trips.list[this.trips.list.length - 1]
                                prev.timeNext = item.gt;
                            }
                        }
                    }
                } else {
                    trip.timeEnd = item.gt;
                    trip.pointEnd = {
                        lng: item.olng,
                        lat: item.olat
                    };
                    trip.mileageEnd = item.m;
                    trip.oilEnd = item.oil;
                    if (trip.acc == '点火') {// 行程结束
                        trip.data.push(item);
                        var date = trip.timeStart.toDate().toShortDateString();
                        this.trips.days[date] = this.trips.days[date] || [];
                        this.trips.days[date].push(trip);
                        this.trips.list.push(trip);

                        // 统计行驶时间
                        var end = trip.timeEnd.toDate().getTime();
                        var start = trip.timeStart.toDate().getTime();
                        runtimes += end - start;
                    } else {// 行程开始
                        trip.data.push(item);
                        var date = trip.timeStart.toDate().toShortDateString();
                        this.parks.days[date] = this.parks.days[date] || [];
                        this.parks.days[date].push(trip);
                        this.parks.list.push(trip);
                        // 找出上一个行程
                        if (this.trips.list.length > 0) {
                            var prev = this.trips.list[this.trips.list.length - 1]
                            prev.timeNext = item.gt;
                        }
                    }
                    trip = {
                        data: []
                    };
                    trip.acc = accStatus;
                    trip.timeStart = item.gt;
                    trip.pointStart = {
                        lng: item.olng,
                        lat: item.olat
                    };
                    trip.mileageStart = item.m;
                    trip.oilStart = item.oil;
                    // 找出上一个停车点
                    if (trip.acc == '点火' && this.parks.list.length > 0) {
                        var prev = this.parks.list[this.parks.list.length - 1]
                        trip.data.push(prev.data[0]);
                    }
                    trip.data.push(item);

                }
            }
            $("#driving_li li").remove();
            $("#dri_all li").remove();

            // 清除地图
            this.webMap.clearOverlays();
            var points = this.webMap.makePoints(this.tracks);
            // 画线
            webMap.createPolyline({
                map: this.webMap,
                points: points,
                color: 'blue'
            })

            // 标注起点,终点,停车点
            webMap.createMarker({
                map: this.webMap,
                data: this.tracks[0],
                allowShowLabel: false,
                infoWindow: replay.infoWindow,
                icon: {
                    url: '../static/img/center/startpoint.png',
                    offset: 0
                },
                iconAnchor: {
                    x: 16,
                    y: 32
                },
                zIndex: 500
            });
            webMap.createMarker({
                map: this.webMap,
                data: this.tracks[this.tracks.length - 1],
                allowShowLabel: false,
                infoWindow: replay.infoWindow,
                icon: {
                    url: '../static/img/center/endpoint.png',
                    offset: 0
                },
                iconAnchor: {
                    x: 16,
                    y: 32
                },
                zIndex: 500
            });
            for (var x = 0; x < this.parks.list.length; x++) {
                var park = this.parks.list[x];
                if (park.data && park.data.length > 0) {
                    webMap.createMarker({
                        map: this.webMap,
                        data: park.data[0],
                        allowShowLabel: false,
                        infoWindow: replay.infoWindow,
                        icon: {
                            url: '../static/img/center/parkpoint.png',
                            offset: 0
                        },
                        iconAnchor: {
                            x: 16,
                            y: 32
                        }
                    });
                }
            }

            replay.webMap.setViewport(this.tracks);


            // 更新平均速度
            var averageSpeed = velocimeter.times == 0 ? 0 : velocimeter.total / velocimeter.times;

            // 更新里程
            var mileage = milemeter.end - milemeter.start;
            if (milemeter.reset) {
                mileage = milemeter.befor - milemeter.start;
                mileage += milemeter.end;
            }

            var averageOil = 0;
            if (mileage > 0) {
                averageOil = oilmeter.total / mileage * 100;
            }


            // 更新行程表
            for (var x in this.trips.days) {
                var daytrips = this.trips.days[x];
                // var newRow = [];
                // newRow.push('<tr>');
                // newRow.push('<td>');
                // newRow.push('	<div>');
                // newRow.push('		<b>' + date + '行程记录</b>');
                // newRow.push('		<hr />');
                // newRow.push('	</div>');
                // newRow.push('</td>');
                // newRow.push('</tr>');
                // var row = newRow.join('');
                // $("#gridReplayTrack").append($(row));
                updateGridReplayTrackRow(daytrips);
            }

            /**
             * 更新行程表
             */
            function updateGridReplayTrackRow(daytrips) {
                for (var index = 0; index < daytrips.length; index++) {
                    var trip = daytrips[index];
                    parseTrip(index, trip);
                }
            }

            parseAllTrip(this.tracks);

            /**
             * 解析所有行程
             */
            function parseAllTrip(tracks) {
                // 更新平均速度
                var averageSpeed = velocimeter.times == 0 ? 0 : velocimeter.total / velocimeter.times;
                //   $('#txtAverageSpeed').text(common.round(averageSpeed, 1) + "km/h");
                // 更新行驶时长
                //   $('#txtRunTime').text(common.timespan(runtimes));
                // 更新里程
                var mileage = milemeter.end - milemeter.start;
                if (milemeter.reset) {
                    mileage = milemeter.befor - milemeter.start;
                    mileage += milemeter.end;
                }
                //  $('#txtRunMileage').text(common.round(mileage, 1) + 'km');
                // 更新油耗
                //  $('#txtRunOil').text(common.round(oilmeter.total, 1) + 'L');
                var averageOil = 0;
                if (mileage > 0) {
                    averageOil = oilmeter.total / mileage * 100;
                }
                // $('#txtAverageOil').text(common.round(averageOil, 1) + 'L/100km');

                var dates = replay.selectDate();
                // var startDate = dates.start.toDateTimeString();
                // console.log(startDate);
                // if (!dates) {
                //     layer.msg('<div style="color: #0C0C0C">请选择时间段！</div>', {icon: 4});
                //
                //     return;
                // }
                // if (dates.valid === false)
                //     return;
                // var startDate = dates.start.toDateTimeString();
                // var endDate = dates.end.toDateTimeString();
                var newRow = [];
                //这个开始
                newRow.push(' <li>');
                newRow.push(' <div class="stTime">' + dates.start.toDateTimeString("MM-dd hh:mm:ss") + '</div>');
                newRow.push(' <div class="stAdd"><span name="from"></span></div>');
                newRow.push('   <div class="edTime">' + dates.end.toDateTimeString("MM-dd hh:mm:ss") + '</div>');
                newRow.push('   <div class="edAdd"><span name="to"></span></div>');
                newRow.push('  <div class="dataList">');
                newRow.push(' <table border="0" cellpadding="0" cellspacing="0">');
                newRow.push('  <thead>');
                newRow.push('   <td>行车里程</td>');
                newRow.push('   <td>平均速度</td>');
                newRow.push('   <td>最高速度</td>');
                newRow.push('    <td>行车油耗</td>');
                newRow.push('    <td>行驶时长</td>');

                newRow.push('   </thead>');
                newRow.push('    <tbody>');
                newRow.push('   <tr>');
                newRow.push('     <td>' + common.round(mileage, 1) + '(km)</td>');
                newRow.push('     <td>' + common.round(averageSpeed, 1) + '(km/h)</td>');
                newRow.push('     <td>' + common.round(averageSpeed, 1) + '(km/h)</td>');
                newRow.push('     <td>' + common.round(oilmeter.total, 1) + '(L)</td>');
                newRow.push('     <td>' + common.timespan(runtimes) + '</td>');

                newRow.push('   <tr>');
                newRow.push('   </tbody>');
                newRow.push('   </table>');
                newRow.push('    </div>');
                newRow.push('   <div class="drivingPro" style="display: none;">');
                newRow.push('   <span>');
                newRow.push('   <img src="./static/image/replay/click_bo.png" alt="" class="iplay">');
                newRow.push('   <button class="fuckP">平滑</button>  ');
                newRow.push('  </span>');
                newRow.push('   <span>');
                newRow.push('    <div class="layui-progress" style="top: 3px;" >');
                newRow.push('     <div class="layui-progress-bar layui-bg-blue" lay-percent="50%"></div>');
                newRow.push('     </div>');
                newRow.push('     </span>');
                newRow.push('    <span>');
                newRow.push('    <img src="./static/image/replay/noclick_qian.png" alt="" class="deceleration" >');
                newRow.push('    </span>');
                newRow.push('    <span>');
                newRow.push('     <img src="./static/image/replay/click_hou.png" alt="" class="accelerate">');
                newRow.push('      </span>');
                newRow.push('       <span class="speed">1X</span>');
                newRow.push('        </div>');

                newRow.push(' </li>');
                //结束
                var row = $(newRow.join(''));

                $("#dri_all").append(row);
                var trip = {
                    data: tracks
                };
                row.data('trip', trip);

                replay.webMap.queryAddress(tracks[0].olng, tracks[0].olat, function (addressStart, row) {
                    row.find('span[name="from"]').text(addressStart);
                }, row);
                replay.webMap.queryAddress(tracks[tracks.length - 1].olng, tracks[tracks.length - 1].olat, function (addressEnd, row) {
                    row.find('span[name="to"]').text(addressEnd);
                }, row);
            }

            /**
             * 解析行程
             */
            function parseTrip(index, trip) {
                var timeStart = trip.timeStart.toDate().getTime();
                var timeEnd = trip.timeEnd.toDate().getTime();
                var milliseconds = timeEnd - timeStart;
                var runtimes = common.timespan(milliseconds);
                trip.runtimes = runtimes;

                trip.paktimes = '正正行驶中......';
                if (trip.timeNext) {
                    var timeNext = trip.timeNext.toDate().getTime();
                    milliseconds = timeNext - timeEnd;
                    var paktimes = common.timespan(milliseconds);
                    trip.paktimes = paktimes;
                }
                /**
                 * 速度计
                 */
                var velocimeter = {
                    total: 0,
                    times: 0
                };
                /**
                 * 油量计
                 */
                oilmeter = {
                    total: 0,
                    befor: null
                };

                /**
                 * 里程计
                 */
                var milemeter = {
                    start: trip.data[0].m,
                    befor: 0,
                    reset: null,
                    end: trip.data[trip.data.length - 1].m
                };

                trip.mileages = 0;
                if (trip.data) {
                    for (var index = 0; index < trip.data.length; index++) {
                        var item = trip.data[index];
                        // 速度
                        if (item.sp > 0) {
                            velocimeter.total += item.sp;
                            velocimeter.times++;
                        }
                        // 里程是否有归零
                        if (!item.reset)
                            milemeter.befor = item.m;
                        if (item.m < milemeter.befor && !item.reset)
                            milemeter.reset = item.m;

                        // 统计油耗
                        if (oilmeter.befor) {
                            var oil = oilmeter.befor - item.oil;
                            if (oil > 0)
                                oilmeter.total += oil;
                        }
                        oilmeter.befor = item.oil;
                    }
                }

                // 更新平均速度
                var averageSpeed = velocimeter.times == 0 ? 0 : velocimeter.total / velocimeter.times;
                trip.averageSpeed = common.round(averageSpeed, 1);

                // 更新里程
                var mileage = milemeter.end - milemeter.start;
                if (milemeter.reset) {
                    mileage = milemeter.befor - milemeter.start;
                    mileage += milemeter.end;
                }
                trip.mileages = common.round(mileage, 1);
                trip.oils = common.round(oilmeter.total, 1);
                // 更新油耗
                var averageOil = 0;
                if (mileage > 0) {
                    averageOil = oilmeter.total / mileage * 100;
                }
                trip.averageOil = common.round(averageOil, 1);

                var newRow = [];
//这个开始
                newRow.push(' <li>');
                newRow.push(' <div class="stTime">' + trip.timeStart.toDate().toDateTimeString("MM-dd hh:mm:ss") + '</div>');
                newRow.push(' <div class="stAdd"><span name="from"></span></div>');
                newRow.push('   <div class="edTime">' + trip.timeEnd.toDate().toDateTimeString("MM-dd hh:mm:ss") + '</div>');
                newRow.push('   <div class="edAdd"><span name="to"></span></div>');
                newRow.push('  <div class="dataList">');
                newRow.push(' <table border="0" cellpadding="0" cellspacing="0">');
                newRow.push('  <thead>');
                newRow.push('   <td>行车里程</td>');
                newRow.push('   <td>平均速度</td>');
                newRow.push('   <td>最高速度</td>');
                newRow.push('    <td>行车油耗</td>');
                newRow.push('    <td>行驶时长</td>');
                newRow.push('   </thead>');
                newRow.push('    <tbody>');
                newRow.push('   <tr>');
                newRow.push('     <td>' + trip.mileages + '(km)</td>');
                newRow.push('     <td>' + trip.averageSpeed + '(km/h)</td>');
                newRow.push('     <td>' + trip.mileages + '(km/h)</td>');
                newRow.push('     <td>' + trip.oils + '(L)</td>');
                newRow.push('     <td>' + trip.runtimes + '</td>');
                newRow.push('   <tr>');
                newRow.push('   </tbody>');
                newRow.push('   </table>');
                newRow.push('    </div>');
                newRow.push('   <div class="drivingPro" style="display: none;">');
                newRow.push('   <span>');
                newRow.push('   <img src="./static/image/replay/click_bo.png" alt="" class="iplay">');
                newRow.push('   <button class="fuckP">平滑</button>  ');
                newRow.push('  </span>');
                newRow.push('   <span>');


                newRow.push('    <div class="layui-progress" style="top: 3px;" >');
                newRow.push('     <div class="layui-progress-bar layui-bg-blue" lay-percent="50%"></div>');
                newRow.push('     </div>');
                newRow.push('     </span>');
                newRow.push('    <span>');
                newRow.push('    <img src="./static/image/replay/noclick_qian.png" alt="" class="deceleration" >');
                newRow.push('    </span>');
                newRow.push('    <span>');
                newRow.push('     <img src="./static/image/replay/click_hou.png" alt="" class="accelerate">');
                newRow.push('      </span>');
                newRow.push('       <span class="speed">1X</span>');
                newRow.push('        </div>');

                newRow.push(' </li>');


                var row = $(newRow.join(''));

                $("#driving_li").append(row);
                row.data('trip', trip);


                replay.webMap.queryAddress(trip.pointStart.lng, trip.pointStart.lat, function (addressStart, row) {
                    row.find('span[name="from"]').text(addressStart);
                }, row);
                replay.webMap.queryAddress(trip.pointEnd.lng, trip.pointEnd.lat, function (addressEnd, row) {
                    row.find('span[name="to"]').text(addressEnd);
                }, row);
            }

            // 更新停车表
            $("#gridReplayPark tr").remove();
            for (var x in this.parks.days) {
                // var daytrips = this.parks.days[x];
                // var newRow = [];
                // newRow.push('<tr>');
                // newRow.push('<td>');
                // newRow.push('	<div style = "background-color: #00ade1;">');
                // newRow.push('		<b>' + date + '停车记录</b>');
                // newRow.push('		<hr />');
                // newRow.push('	</div>');
                // newRow.push('</td>');
                // newRow.push('</tr>');
                // var row = newRow.join('');
                // $("#gridReplayPark").append($(row));
                updateGridReplayParkRow(daytrips);
            }

            /**
             * 更新停车表
             */
            function updateGridReplayParkRow(daytrips) {
                for (var index = 0; index < daytrips.length; index++) {
                    var trip = daytrips[index];
                    parsePark(trip);
                }
            }

            /**
             * 解析停车
             */
            function parsePark(trip) {
                var timeStart = trip.timeStart.toDate().getTime();
                var timeEnd = trip.timeEnd.toDate().getTime();
                var milliseconds = timeEnd - timeStart;
                var paktimes = common.timespan(milliseconds);
                trip.paktimes = paktimes;

                var newRow = [];
                newRow.push('<tr>');
                newRow.push('<td>');
                newRow.push('	<div><div class="mon-icon-h-x16 i-16-parkpoint"></div>');
                newRow.push('		<span style="color:red;">' + trip.timeStart.toDate().toDateTimeString('hh:mm:ss') + '-'
                    + trip.timeEnd.toDate().toDateTimeString('hh:mm:ss') + '</span>');
                newRow.push('		<span style="color:#999999;">停车:<span style="color:red;">' + trip.paktimes + '</span></span>');
                newRow.push('		<span style="color:#999999;">地点:<span name="address" style="color:#999999;"></span></span>');
                newRow.push('	</div><hr>');
                newRow.push('</td>');
                newRow.push('</tr>');
                var row = $(newRow.join(''));
                $("#gridReplayPark").append(row);
                row.data('trip', trip);
                row.on("click", function () {
                    $("#gridReplayPark").find(".tr-selected").removeClass('tr-selected');
                    $(this).addClass('tr-selected');
                    var trip = $(this).data('trip');
                    if (trip) {
                        // 画点
                        if (replay.selectedPark)
                            replay.selectedPark.dispose();
                        if (trip.data && trip.data.length > 0) {
                            replay.selectedPark = webMap.createMarker({
                                map: replay.webMap,
                                data: trip.data[0],
                                allowShowLabel: false,
                                infoWindow: replay.infoWindow,
                                icon: {
                                    url: '../static/img/car.png',
                                    offset: 0
                                },
                                iconAnchor: {
                                    x: 16,
                                    y: 32
                                }
                            });
                            replay.selectedPark.openInfoWindow();
                        }
                    }
                });
                replay.webMap.queryAddress(trip.pointStart.lng, trip.pointStart.lat, function (address, row) {
                    row.find('span[name="address"]').text(address);
                }, row);
            }

            // 更新报警表
            $("#gridReplayAlarm tr").remove();
            for (var x in replay.alarms.days) {
                var dayalarms = replay.alarms.days[x];
                // var newRow = [];
                // newRow.push('<tr>');
                // newRow.push('<td>');
                // newRow.push('	<div>');
                // newRow.push('		<b>' + date + '报警记录</b>');
                // newRow.push('		<hr />');
                // newRow.push('	</div>');
                // newRow.push('</td>');
                // newRow.push('</tr>');
                // var row = newRow.join('');
                // $("#gridReplayAlarm").append($(row));
                updateGridReplayAlarmRow(dayalarms);
            }

            /**
             * 更新报警表
             */
            function updateGridReplayAlarmRow(dayalarms) {
                for (var index = 0; index < dayalarms.length; index++) {
                    var alarm = dayalarms[index];
                    parseAlarm(alarm);
                }
            }

            /**
             * 解析报警
             */
            function parseAlarm(alarm) {
                var newRow = [];
                newRow.push('<tr>');
                newRow.push('<td>');
                newRow.push('	<div><div class="mon-icon-h-x16 i-16-alarmpoint"></div>');
                newRow.push('		<span>' + alarm.gt.toDate().toDateTimeString('hh:mm:ss') + '</span><br />');
                newRow.push('		<span style="color:#999999;">报警:<span style="color:red;">' + gpsDataParser.parseAlarm(alarm) + '</span></span><br />');
                newRow.push('		<span style="color:#999999;">地点:<span name="address" style="color:#999999;"></span></span>');
                newRow.push('	</div>');
                newRow.push('</td>');
                newRow.push('</tr>');
                var row = $(newRow.join(''));
                $("#gridReplayAlarm").append(row);
                row.data('alarm', alarm);
                row.live("click", function () {
                    $("#gridReplayAlarm").find(".tr-selected").removeClass('tr-selected');
                    $(this).addClass('tr-selected');
                    var alarm = $(this).data('alarm');
                    if (alarm) {
                        // 画点
                        if (replay.selectedAlarm)
                            replay.selectedAlarm.dispose();
                        if (alarm) {
                            replay.selectedAlarm = webMap.createMarker({
                                map: replay.webMap,
                                data: alarm,
                                allowShowLabel: false,
                                infoWindow: replay.infoWindow,
                                icon: {
                                    url: '../resources/images/alarmpoint.png',
                                    offset: 0
                                },
                                iconAnchor: {
                                    x: 16,
                                    y: 32
                                }
                            });
                            replay.selectedAlarm.openInfoWindow();
                        }
                    }
                });
                replay.webMap.queryAddress(alarm.olng, alarm.olat, function (address, row) {
                    row.find('span[name="address"]').text(address);
                }, row);
            }

            // replay.resetSpeedChart(replay.speeds);
            // replay.resetButtons(1, 2);
        },
        download: function (number, start, end, pageIndex, pageSize) {
            replay.playStatus = 'loading';
            // 对时间需要计算
            $.post('../replay/load', {
                number: number,
                start: start,
                end: end,
                pageIndex: pageIndex,
                pageSize: pageSize
            }, function (list) {
                if (list && list.length > 0) {
                    replay.webMap.translate(list, 0, function () {
                        replay.pageIndex++;
                        replay.tracks = replay.tracks.concat(list);
                        //  这个没有进度条
                        replay.playProgress();
                        replay.download(number, start, end, replay.pageIndex, replay.pageSize);
                    });
                }
            });
        },
        /**
         * 下载进度
         */
        playProgress: function () {
            if (replay.trackCount && replay.trackCount > 0) {
                var ratio = replay.tracks.length / replay.trackCount;
                var width = Math.round(ratio * 100);
                if (width > 100)
                    width = 100;
                var ratioText = width + '%';
                // $('#divProcess > div').css('width', ratioText);
            }
            if (replay.tracks.length >= replay.trackCount) {
                replay.downloadCompleted();
                // $('#divReplayMapControl #divProcess').hide();
                // $('#divReplayMapControl #txtProcess').attr('max', replay.tracks.length - 1);
                // $('#divReplayMapControl #txtProcess').val(0);
                // $('#divReplayMapControl #txtProcess').show();
            }
        },


        clear: function () {
            // $('#group2 span').text('');
            $('#gridReplayTrack tr').remove();
            // $('#gridReplayPark tr').remove();
            // $('#gridReplayAlarm tr').remove();
            if (replay.webMap)
                replay.webMap.clearOverlays();
            // replay.repalyPages.setHeader("pageReplayTrip", "行程");
            // replay.repalyPages.setHeader("pageReplayStop", "停车");
            // replay.repalyPages.setHeader("pageReplayAlarm", "报警");
            //replay.resetButtons(1);
            replay.vehicleMarker = null;
            replay.index = 0
            // $('#divReplayMapControl #divProcess').show();
            // $('#divReplayMapControl #divProcess div').width('0%');
            // $('#divReplayMapControl #txtProcess').hide();
            // replay.resetSpeedChart();
        },
        // 行驶过的画线
        drivinged: function () {
            var trip = replay.drivingedtracks;
            if (trip && trip.length > 0) {
                var points = replay.webMap.makePoints(trip);
                if (replay.selecteddrivingedtracksPolyline)
                    replay.selecteddrivingedtracksPolyline.clear();
                // 画线
                replay.selecteddrivingedtracksPolyline = webMap.createPolyline({
                    map: replay.webMap,
                    points: points,
                    color: 'yellow'
                })

                // replay.webMap.panTo(trip[0].olng, trip[0].olat);
            }


        },

        play: function () {
            
            if (replay.tracks && replay.tracks.length <= 0)
                return;
            if (replay.playtracks && replay.playtracks.length <= 0) {
                console.log("没有数据");
                replay.playtracks = replay.tracks;
            }

            replay.index = replay.index || 1;
            var me = this;

            function move() {
                clearTimeout(replay.timer);
                if (replay.playStatus !== 'play'){
                    return;
                }
                if (me.index >= replay.playtracks.length) {
                    replay.playing();
                    console.log("sjl");
                    $("#dri_all").find(".iplay").removeClass("playing");
                    $("#dri_all").find(".iplay").attr("src", "./static/image/replay/click_bo.png");
                    $("#driving_li").find(".iplay").removeClass("playing");
                    $("#driving_li").find(".iplay").attr("src", "./static/image/replay/click_bo.png");
                    replay.index = 0;
                    // $("#dmMiddle").animate({"scrollTop": "0px"},1);
                    // $(document).off("animate");
                    $("#dmMiddle").stop(true);
                    return;
                }
                var marker = replay.vehicleMarker;
                var row = replay.playtracks[me.index];
                row.marker = replay.vehicle.marker;
                if (!marker) {
                    marker = replay.vehicleMarker = webMap.createMarker({
                        map: replay.webMap,
                        data: row,
                        allowShowLabel: false,
                        allowRotate: true,
                        infoWindow: replay.infoWindow
                    });
                    marker.openInfoWindow();
                } else {
                    marker.data = row;
                    marker.refresh();
                    if (replay.infoWindow.owner != marker) {
                        marker.openInfoWindow();
                    }

                    else
                        replay.webMap.panTo(marker.data.olng, marker.data.olat);
                }
                //  $('#divReplayMapControl #txtProcess').val(me.index);

                replay.drivingedtracks.push(row);
                replay.drivinged();
                replay.playing();
                var count = me.index % replay.pagelength;
                $("#map_list > tr:nth-child(" + count + ")").css("backgroundColor", "");
                $("#map_list > tr:nth-child(" + (count + 1) + ")").css("backgroundColor", "#ffe8e0");
                $("#dmMiddle").animate({"scrollTop": 14 * count+'px'});
                me.index++;

                console.log("me.index % replay.pagelength" + me.index % replay.pagelength + "me.index:"+me.index+"replay.pagelength:"+replay.pagelength);
                //  翻页
                if (me.index % replay.pagelength == 0) {

                    console.log("分？？？？？？" + replay.pagelength);
                    //  翻页


                    $(".layui-box > a.layui-laypage-next > em").click();
                    // $("#layui-laypage-1 > a.layui-laypage-next > em").click();
                    $("#dmMiddle").animate({"scrollTop": "0px"});
                }
                replay.timer = setTimeout(move, replay.playAccelerate);
            }

            replay.timer = setTimeout(move, replay.playAccelerate);

        },


        playing: function () {//进度条
            if (replay.index <= replay.playtracks.length) {
                var ratio = replay.index / replay.playtracks.length;

                var width = Math.round(ratio * 100);
                if (width > 100)
                    width = 100;
                var ratioText = width + '%';
                console.log("ratioText" + ratioText);
                layui.use('element', function () {
                    var $ = layui.jquery
                        , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
                    element.progress('demo', ratioText)
                });
            }
        },


        /**
         * 选取时间
         */
        selectDate: function () {
            var dateStart = new Date().addDays(-1);
            var dateEnd = new Date();

            var start = replay.txtStartTime;
            var end = replay.txtEndTime;

            console.log(start + " ++++" + end);
            if (start == null || end == null)
                return null;
            else {
                dateStart = new Date(start);
                dateEnd = new Date(end);
            }

            if (dateStart.getOffDays(dateEnd) > 7) {
                console.log('时间范围不超过7天');

                this.resetButtons(1);
                return {
                    start: dateStart,
                    end: dateEnd,
                    valid: false
                };
            }

            return {
                start: dateStart,
                end: dateEnd,
                valid: true
            };
        },
    }


    function hqreplay() {  //获取轨迹数量
        var myDate = new Date()
        $.post('../replay/count', {
            number: "10189415505",
            start: myDate,
            end: myDate
        }, function (r) {

        });
    }

    $(function () {


        setTimeout(function () {
            webMap.events.onMapLoadCompleted['replayMap'] = replay.onMapLoaded;
            webMap.createMap("replayMap");
            // console.log("地图的加载");
        }, 1000);


        $(".dl_info ul").delegate("li", "click", function () {
            var flag1 = $(this).css("height") == 200 + 'px';
            var temp1 = $(this).css("height") == 245 + 'px';
            if (flag1) {
                $(this).css("height", "245px").css("backgroundColor", "#ffe8e0").siblings("li").css("height", "200px").css("backgroundColor", "#f1f1f1");
                $(this).children(".drivingPro").css("display", "block");
                $(this).siblings("li").children(".drivingPro").css("display", "none");
                $(this).children(".drivingTime").css("display", "block");
                $(this).siblings("li").children(".drivingTime").css("display", "none");
            } else if (temp1) {
                $(this).css("height", "200px").css("backgroundColor", "#f1f1f1");
                $(this).children(".drivingPro").css("display", "none");
                $(this).children(".drivingTime").css("display", "none");
            }
            var trip = $(this).data('trip');
            // var html = template("tpl_1", trip);
            // $('#map_list').html(html);


            if (trip && trip.data && trip.data.length > 0) {
                var points = replay.webMap.makePoints(trip.data);
                if (replay.selectedPolyline)
                    replay.selectedPolyline.clear();
                // 画线
                replay.selectedPolyline = webMap.createPolyline({
                    map: replay.webMap,
                    points: points,
                    color: 'red'
                })
                replay.webMap.panTo(trip.data[0].olng, trip.data[0].olat);
            }

        });


        $(".dl_info ul").delegate(".fuckP", "click", function (e) {
            e.stopPropagation();


            var trip = $(this).parent().parent().parent().data('trip');
            var mmp;

            replay.playtracks = trip.data;

            layer.open({
                type: 1,
                title: '平滑播放'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 0, //0-6的动画形式，-1不开启

                area: ['1000px', '500px'], //宽高
                content: '<div id="map_canvas" style="width: 100%;height: 100%;"></div>',
                btn: ['播放', '暂停'] //只是为了演示
                , yes: function () {

                    mmp.start();

                }
                , btn2: function () {
                    mmp.pause();
                    return false;
                },
                success: function (layero, index) {


                    var map = new BMap.Map('map_canvas');
                    map.enableScrollWheelZoom();
                    // map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);
                    var lushu;
                    // 实例化一个驾车导航用来生成路线


                    var arrPois = replay.playtracks;

                    // var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
                    //     scale: 0.6,//图标缩放大小
                    //     strokeColor: '#fff',//设置矢量图标的线填充颜色
                    //     strokeWeight: '1',//设置线宽
                    // });
                    // var icons = new BMap.IconSequence(sy, '10', '30');

                    var pois = [];
                    for (var a of arrPois) {
                        // console.log(a.lat)
                        pois.push(new BMap.Point(a.lng, a.lat));
                    }

                    var polyline = new BMap.Polyline(pois, {
                        enableEditing: false,//是否启用线编辑，默认为false
                        enableClicking: false,//是否响应点击事件，默认为true
                        strokeWeight: '3',//折线的宽度，以像素为单位
                        strokeOpacity: 0.8,//折线的透明度，取值范围0 - 1
                        strokeColor: "red" //折线颜色
                    });

                    map.addOverlay(polyline);


                    map.addOverlay(new BMap.Polyline(arrPois, {strokeColor: '#111'}));
                    map.setViewport(arrPois);
                    // console.log(arrPois);
                    // console.log(JSON.stringify(arrPois));
                    lushu = new BMapLib.LuShu(map, arrPois, {
                        defaultContent: "",//"从天安门到百度大厦"
                        autoView: true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                        icon: new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52, 26), {anchor: new BMap.Size(27, 13)}),
                        speed: 4500,
                        enableRotation: true,//是否设置marker随着道路的走向进行旋转
                    });

                    mmp = lushu;

                }
            });


            // alert(JSON.stringify(replay.playtracks))
            // replay.play();
        });

        $(".dl_info ul").delegate(".iplay", "click", function (e) {
            e.stopPropagation();
            var trip = $(this).parent().parent().parent().data('trip');
            console.log("replay.index" + replay.index);
            if (!$(this).hasClass("playing")) {
                if (replay.playAccelerate === undefined) {
                    replay.playAccelerate = 1000;
                }


                $("#dri_all").find(".iplay").removeClass("playing");
                $("#dri_all").find(".iplay").attr("src", "./static/image/replay/click_bo.png");
                $("#dri_all").find(".layui-progress").attr('lay-filter', "");

                $("#driving_li").find(".iplay").removeClass("playing");
                $("#driving_li").find(".iplay").attr("src", "./static/image/replay/click_bo.png");
                $("#driving_li").find(".layui-progress").attr('lay-filter', "");

                $(this).addClass("playing");
                $(this).parent().next().children().attr('lay-filter', "demo");
                // $(this).attr("src", this.src);
                replay.index = 0;
                replay.playtracks = trip.data;
                // $(this).addClass("playing");
                replay.playStatus = 'loading';
                replay.drivingedtracks = [];
            }
            replay.playtracks = trip.data;
            console.log(replay.playStatus);
            //  下载中(loading),下载完成(loaded), 播放中(play),暂停(pause)


            if (replay.playStatus == 'loading') {
                layui.use(['laypage', 'layer'], function () {
                    var laypage = layui.laypage
                        , layer = layui.layer;
                    laypage.render({
                        elem: 'replaypaging'
                        , count: trip.data.length
                        , theme: '#0488d1'
                        , prev: '<em><</em>'
                        , next: '<em>></em>'
                        , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                        , jump: function (obj) {

                            var curr = obj.curr;
                            var limit = obj.limit;
                            replay.layerpageindex = curr;
                            var templateData = trip.data.slice((curr - 1) * limit, curr * limit);
                            var html = template("tpl_1", templateData);
                            $('#map_list').html(html);
                            replay.pagelength = limit;

                        }
                    });
                });
                replay.playStatus = 'play';
                $(this).attr('src', "./static/image/replay/pause.png");
            } else if (replay.playStatus == 'pause') {
                replay.playStatus = 'play';
                $(this).attr('src', "./static/image/replay/pause.png");
            } else if (replay.playStatus == 'play') {
                replay.playStatus = 'pause';
                $(this).attr('src', "./static/image/replay/click_bo.png");
            }
            // replay.playthis=$(this);
            replay.play();


            // console.log($(this));
        })

        $(".dl_info ul").delegate(".accelerate", "click", function (e) {
            e.stopPropagation();

            if (replay.playAccelerate === 125 / 2) {

                return;
                // replay.playAccelerate=1000;
                // $(this).parent().next().text("1X");
                // $(this).attr('src',"./static/image/replay/noclick.png");
            } else {
                replay.playAccelerate = replay.playAccelerate / 2;
                if (replay.playAccelerate === 500) {

                    $(this).parent().next().text("2X")
                    // $(this).parent().prev().children().attr('src', "./static/image/replay/click_qian.png");
                } else if (replay.playAccelerate === 250) {
                    $(this).parent().next().text("4X")
                } else if (replay.playAccelerate === 125) {
                    $(this).parent().next().text("8X")
                } else if (replay.playAccelerate === 125 / 2) {
                    $(this).parent().next().text("16X")
                    // $(this).attr('src', "./static/image/replay/noclick_hou.png");
                } else if (replay.playAccelerate === 1000) {
                    $(this).parent().next().text("1X")
                    // $(this).attr('src', "./static/image/replay/noclick_qian.png");
                }

            }
            // alert(replay.playAccelerate)
        })

        $(".dl_info ul").delegate(".deceleration", "click", function (e) {
            e.stopPropagation();

            if (replay.playAccelerate === 1000) {
                return;
            } else {
                replay.playAccelerate = replay.playAccelerate * 2;
                if (replay.playAccelerate === 250) {
                    $(this).parent().next().next().text("4X")
                } else if (replay.playAccelerate === 125) {
                    $(this).parent().next().next().text("8X")
                } else if (replay.playAccelerate === 125 / 2) {
                    $(this).parent().next().next().text("16X")
                    // $(this).parent().next().children().attr('src', "./static/image/replay/click_hou.png");
                } else if (replay.playAccelerate === 500) {
                    $(this).parent().next().next().text("2X")
                    // $(this).attr('src', "./static/image/replay/noclick_qian.png");
                } else if (replay.playAccelerate === 1000) {
                    $(this).parent().next().next().text("1X")
                    // $(this).attr('src', "./static/image/replay/noclick_qian.png");
                }


            }
            // alert(replay.playAccelerate)
        })

        $("#map_list").delegate("tr", "click", function () {
            var str2 = $(this).find("td").first().text();
            console.log("str2: " + str2 + "replay.layerpageindex: " + replay.layerpageindex + "replay.pagelength: " + replay.pagelength);
            var sum = (replay.layerpageindex - 1) * replay.pagelength + parseInt(str2);
            console.log("sum" + sum);
            var alarm = replay.playtracks[sum - 1];
            if (alarm) {
                // 画点
                if (replay.selectedAlarm)
                    replay.selectedAlarm.dispose();
                if (alarm) {
                    replay.selectedAlarm = webMap.createMarker({
                        map: replay.webMap,
                        data: alarm,
                        allowShowLabel: false,
                        allowRotate: true,
                        infoWindow: replay.infoWindow,
                        icon: {
                            url: '../static/img/car.png',
                            offset: 0
                        },
                        iconAnchor: {
                            x: 16,
                            y: 32
                        }
                    });
                    replay.selectedAlarm.openInfoWindow();
                }
            }

        })


        $('#divReplayMapControl #btnRelayStart').click(function () {
            if ($(this).hasClass('mon-slice'))
                return;
            // replay.resetButtons(null, null, 3, 4);
            replay.play();
        });
        $('#divReplayMapControl #btnReplayPause').click(function () {
            if ($(this).hasClass('mon-slice'))
                return;
            // replay.resetButtons(null, 1, null, 4);
            replay.playStatus = 'pause';
        });
        $('#divReplayMapControl #btnReplayStop').click(function () {
            if ($(this).hasClass('mon-slice'))
                return;
            replay.resetButtons(1, 2);
            replay.playStatus = 'stop';
        });
        $('#query').click(function () {

            var numberstr = $('#number').val();
            var datatype = $('#datatype').val();

            console.log("numberstr" + numberstr);
            if (numberstr == "") {
                numberstr = "120187322620";
            }
            console.log("datatype" + datatype);
            switch (datatype) {
                case "1":
                    var dateStart = new Date().addDays(-1);
                    var dateEnd = new Date();
                    console.log("dateStart" + dateStart.toDateTimeString());
                    replay.txtStartTime = dateStart.toDateTimeString();
                    replay.txtEndTime = dateEnd.toDateTimeString();
                    break;
                case "2":
                    var dateStart = new Date().addDays(-14);
                    var dateEnd = new Date().addDays(-7);
                    replay.txtStartTime = dateStart.toDateTimeString();
                    replay.txtEndTime = dateEnd.toDateTimeString();
                    break;
                case "3":
                    var dateStart = new Date().addDays(-7);
                    var dateEnd = new Date().addDays();
                    replay.txtStartTime = dateStart.toDateTimeString();
                    replay.txtEndTime = dateEnd.toDateTimeString();
                    break;
                case "4":
                    var dateStart = new Date().addDays(-2);
                    var dateEnd = new Date().addDays();
                    replay.txtStartTime = dateStart.toDateTimeString();
                    replay.txtEndTime = dateEnd.toDateTimeString();
                    break;
                case "5":
                    var dateStart = new Date().addDays(-2);
                    var dateEnd = new Date().addDays(-1);
                    replay.txtStartTime = dateStart.toDateTimeString();
                    replay.txtEndTime = dateEnd.toDateTimeString();
                    break;
                case "6":
                    replay.txtStartTime = $("#allStartTime").val();
                    replay.txtEndTime = $("#allEndTime").val();
                    var d1 = new Date(replay.txtStartTime.replace(/\-/g, "\/"));
                    var d2 = new Date(replay.txtEndTime.replace(/\-/g, "\/"));

                    if (d1 >= d2) {
                        layer.msg('<div style="color: #0C0C0C">开始时间不能大于结束时间！</div>', {icon: 4});
                        // layer.open({
                        //     type: 1,
                        //     skin: 'layui-layer-demo', //样式类名
                        //     closeBtn: 0, //不显示关闭按钮
                        //     anim: 2,
                        //     shadeClose: true, //开启遮罩关闭
                        //     content: '开始时间不能大于结束时间！'
                        // });

                        return;
                    }

                    // var dateStart = new Date().addDays(-2);
                    // var dateEnd = new Date().addDays(-1);
                    // replay.txtStartTime =dateStart.toDateTimeString();
                    // replay.txtEndTime =dateEnd.toDateTimeString();
                    break;

            }

            if ($(this).hasClass('mon-slice'))
                return;
            replay.tracks = [];
            replay.pageIndex = 1;

            var dates = replay.selectDate();
            var startDate = dates.start.toDateTimeString();
            console.log(startDate);
            if (!dates) {
                layer.msg('<div style="color: #0C0C0C">请选择时间段！</div>', {icon: 4});

                return;
            }
            if (dates.valid === false)
                return;
            var startDate = dates.start.toDateTimeString();
            var endDate = dates.end.toDateTimeString();


            $.post('../replay/count', {
                number: numberstr,
                start: startDate,
                end: endDate
            }, function (r) {
                // 获取历史数据总数量
                replay.trackCount = r.total;
                if (replay.trackCount <= 0) {
                    layer.msg('<div style="color: #0C0C0C">此时间段无数据！</div>', {icon: 4});

                    return;
                }
                $('#divReplayMapControl #divProcess').show();
                $('#divReplayMapControl #txtProcess').hide();
                replay.download(numberstr, startDate, endDate, 1, replay.pageSize);
            });


        });


        $('#divReplayMapControl #btnReplayAccelerate').click(function () {
            if (replay.playAccelerate === 200) {
                replay.playAccelerate = 1000;
            } else {
                replay.playAccelerate = replay.playAccelerate - 200;
            }
            console.log(" replay.playAccelerate " + replay.playAccelerate);

        });

// btnReplayAccelerate

        $.get('../common/device/vehicle', {
            number: replay.numberstr
        }, function (vehicle) {
            replay.vehicle = vehicle;
            // alert(JSON.stringify(vehicle))
        });

    })
});











