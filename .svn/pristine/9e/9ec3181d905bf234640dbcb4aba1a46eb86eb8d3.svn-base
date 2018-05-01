function Loc() {
    var addDiyDom = function (treeId, treeNode) {
        var li_head;
        var i = 0;
        if (treeNode.pId == null) {
            var aObj = $("#" + treeNode.tId + "_a");
            var switchObj = $("#" + treeNode.tId + "_switch");
            var spanObj = $("#" + treeNode.tId + "_span");
            var editStr = '';
            li_head = '<span style="width: 100%;background: #59B9C7;position: absolute;height: 3px;margin-top: 16px;left:0px"></span>';
            aObj.before(li_head);
        }
        if (treeNode.level == 1) {
        }
        if (treeNode.pId != null && treeNode.type == 0) {
            i++;
            var imgage = "";
            var stylecolor = "";
            if (treeNode.o == 0) {
                imgage = "<img src='/image/lixiandian.png'/>";
                stylecolor = "style= 'color:#A0A0A0'";

            } else {
                imgage = "<img src='/image/zaixian.png'/>";
                stylecolor = "style= 'color:#74CB63'";
                if (treeNode.sp > 0) {
                    stylecolor = "style= 'color:#4EA7F7'";
                }
            }
            var spaceWidth = 15;
            var liObj = $("#" + treeNode.tId);
            var aObj = $("#" + treeNode.tId + "_a");
            var switchObj = $("#" + treeNode.tId + "_switch");
            var spanObj = $("#" + treeNode.tId + "_span");

            aObj.attr('title', '');
            // aObj.append('<div class="diy"></div>');
            //  var div = $(liObj).find('div').eq(0);
            switchObj.remove();
            spanObj.remove();
            // icoObj.remove();
            // div.append(switchObj);
            // div.append(spanObj);
            //var spaceStr = "<span>";
            // switchObj.before(spaceStr);
            var editStr = '';
            editStr += '<div class="diy divnema"' + stylecolor + '>' + treeNode.na + '</div>';
            editStr += '<div class="diy">' + (treeNode.sp == null ? '&nbsp;' : treeNode.sp ) + '</div>';
            editStr += '<div class="diy">' + (treeNode.m == null ? '&nbsp;' : treeNode.m ) + '</div>';
            editStr += '<div class="diy">' + /*(treeNode. == "" ? '&nbsp;' : "已停" )*/"已停" + '</div>';
            aObj.append(editStr);
        }
    };

    var beforeClick = function (treeId, treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("dataTree");
        var nodes = treeObj.getSelectedNodes();
        for (var i=0, l=nodes.length; i < l; i++) {
            treeObj.checkNode(nodes[i], true, true);
        }
        if (treeNode.type == 0) {  // console.log(treeNode);
            $("#libuoonton").remove();

            var treeid = treeNode.tId;
            var butong = "<li class='libuoonton' id='libuoonton'> <input type='button' onclick='dataTreedian()' value='点名'></input> " +
                "<input type='button' value='资料'></input><input type='button' value='轨迹' onclick='guiji()'></input> <input type='button' value='回放' onclick='huifang()'></input></li>";
            $("#" + treeid).append(butong);
            var dn = treeNode.dn;

            locate.showLocate(locate.devices[dn]);

            console.log("点击了"+dn+" +++");
            var device = locate.follow[dn];
            if (!device || device==null || device=="undefined"){
                locate.follow[dn] = locate.devices[dn];
            }else{
                device=null;
                locate.follow[dn] = device;
            }

            var adddatalist=[];
            for ( var i in locate.follow) {
                var item = locate.follow[i];
                if(item!=null){
                    adddatalist.push(item);
                }
            }
            var tabledata ={list:adddatalist};
            var html = template("tpl", tabledata);
            $('#content_data').html(html);





            // if(!Arrayis(id)[1]){
            //     adddatalist.push(fromdevices(alldevices[id]));
            //     treeids.push(id);
            //     var tabledata ={list:adddatalist};
            //     var html = template("tpl", tabledata);
            //     $('#content_data').html(html);
            // }
            // chushudataadd(id,true,true);
            // qiehuan(id);
        }

    };
    //勾选父节点
    var zTreeOnClick = function (event, treeId, treeNode) {
        //  alert(treeNode.tId + ", " + treeNode.name);
        /* var treeObj = $.fn.zTree.getZTreeObj("tree");
         var nodes = treeObj.getCheckedNodes(true);*/
        //console.log(treeObj)
    };
//勾选树节点
    var zTreeOnCheck = function (event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("dataTree"), treeNode;
        var id = treeNode.id;
        var childrendata = treeNode.children == undefined ? undefined : typecar(treeNode.children);

        if (childrendata != undefined) {
            treeNode.checked == true ? adddataztree(childrendata) : removecarteam(childrendata);
        } else {
            //被勾选到
            treeNode.checked == true ? beforeClick(treeId, treeNode) : removercar(treeNode);
        }
        // alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
    };
    return {
        addDiyDom: addDiyDom,
        beforeClick: beforeClick,
        zTreeOnClick: zTreeOnClick,
        zTreeOnCheck: zTreeOnCheck
    };
}

var Loc = new Loc();
/**
 *监控中心
 */
window.locate = {
    locatezTree:null,
    /**
     * 地图
     */
    opts: undefined,
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
     * 关注
     */
    follow: {},

    /**
     * 组别与车辆总表
     */
    vehicles: null,
    bitopen: true,
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
    setting: {
        view: {
            showLine: false,
            showIcon: true,
            addDiyDom: Loc.addDiyDom
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
            enable: true,

        },
        callback: {
            beforeClick: Loc.beforeClick,
            onClick: Loc.zTreeOnClick,
            onCheck: Loc.zTreeOnCheck
        }

    },

    /**
     * 重设在线、离线数据,任务，行驶
     */
    resetOnlines : {
        total : 0,
        onlines : 0,
        task:0,
        travel:0,
        reset : function() {
            $(".cs_top span:nth-child(1)").html("总车辆:" + this.total);
            $(".cs_top span:nth-child(2)").html("当前在线率:" + (this.onlines / this.total).toFixed(2) * 100 + "%");
            $(".cs_middle span:nth-child(1)").html("在线:" + this.onlines);
            $(".cs_middle span:nth-child(2)").html("离线:" + this.total-this.onlines);
            $(".cs_bottom span:nth-child(1)").html("任务:"+this.task);
            $(".cs_bottom span:nth-child(2)").html("行驶:" + this.travel);
        }
    },
    /**
     * 在线设备列表
     */
    onlinedevices: {},
    /**
     * 离线设备列表
     */
    offlinedevices: {},

    /**
     * 树组件列表
     */
    Treelist: [],

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
    onMapLoaded: function (map) {
        locate.webMap = map;
        console.log(" 地图加载成功");
        locate.loadGroups();
        locate.infoWindow = webMap.createInfoWindow({
            map : locate.webMap,
            data : undefined,
            width : 260,
            allowQueryAddress : true,
            makeTitle : function() {
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
            makeContent : function() {
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
                html.push('<div class="mon_button" id="The_alarm">');
                html.push('<span>指令</span><span>轨迹</span><span>详情</span><span>告警</span>');
                html.push('</div>');
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



    },
    drawMapLayers: function () {

    },
    //onSelectRow请求,但是事实上不是这个,他们连接的桥梁
    drawingOpen: function (callback) {

    },
    overlayComplete: function (e) {

    },
    /**
     * 加载组别与车辆树
     */
    loadGroups: function () {
        console.log(" 加载数据");
        $.ajax({
            url: "/locate/groupVehicles", //请求地址
            dataType: "json", //数据格式
            data: {
                force: false
            },
            type: "post", //请求方式
            async: false, //是否异步请求
            success: function (data) { //如何发送成功
                var onlines = 0;
                var total = 0;
                var travel=0;
                var list = [];
                for (var x = 0; x < data.length; x++) {
                    var item = data[x];
                    if (item.type === 0) {
                        item.s = gpsDataParser.parseStatus(item);
                        list.push(item);
                        onlines += item.o;
                        locate.devices[item.dn] = item;
                        locate.Treelist.push(locate.fromdevices(item));
                        if (item.o == 1) {
                            locate.onlinedevices[item.dn] = item;
                            if(item.sp>0){
                                travel += item.o;
                            }
                        } else {
                            locate.offlinedevices[item.dn] = item;
                        }
                    } else {
                        // fleets[item.id] = item;
                        locate.Treelist.push(locate.fromfleet(item));
                    }
                }
                locate.resetOnlines.total =  list.length;
                locate.resetOnlines.onlines = onlines;
                locate.resetOnlines.travel = travel;
                locate.resetOnlines.reset();
                locate.loadTree();
            },
        });
    },
    /**
     * 在地图中显示位置数据
     *
     * @param data
     *            位置数据
     */
    showLocate : function(data) {
        function show(data) {
            if (locate.markers[data.dn]) {
                var marker = locate.markers[data.dn];
                marker.data = data;
                marker.refresh();
                marker.openInfoWindow();
                return;
            }

            var marker = webMap.createMarker({
                map : locate.webMap,
                data : data,
                infoWindow : locate.infoWindow,
                allowShowLabel : locate.allowShowLabel,
                allowRotate : data.rotate === 1
            });
            locate.markers[data.dn] = marker;
            marker.openInfoWindow();
        }
        if (!data.olng || !data.olat) {
            locate.webMap.convertor(data.lng, data.lat, function(point, row) {
                row.olng = point.lng;
                row.olat = point.lat;
                show(row);
            }, data);
        } else
            show(data);
    },


    //初始化树
    loadTree: function () {
        locate.locatezTree=$.fn.zTree.init($("#dataTree"), locate.setting, locate.Treelist);
        // treenodemove();
        //添加表头
        var li_head = '<span style="width: 100%;background: #c9c9c9"></span>';
        var rows = $("#dataTree").find('li');
        if (rows.length > 0) {
        } else {
            $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
        }
    },
//转换刚加载数据
    fromdevices: function (data) {
        var datastart = new Object();
        datastart.a = data.a;
        datastart.ad0 = data.ad0;
        datastart.ad1 = data.ad1;
        datastart.aid = data.aid;
        datastart.alt = data.alt;
        datastart.d = data.d;
        datastart.dn = data.dn;
        datastart.exs = data.exs;
        datastart.gt = data.gt;
        datastart.icon = data.icon;
        datastart.id = data.id;
        datastart.iid = data.iid;
        datastart.iof = data.iof;
        datastart.ios = data.ios;
        datastart.iot = data.iot;
        datastart.lat = data.lat;
        datastart.lng = data.lng;
        datastart.m = data.m;
        datastart.marker = data.marker;
        datastart.na = data.na;
        datastart.net = data.net;
        datastart.o = data.o;
        datastart.oid = data.oid;
        datastart.oil = data.oil;
        datastart.ovt = data.ovt;
        datastart.pId = data.pid;
        datastart.rf = data.rf;
        datastart.rid = data.rid;
        datastart.rotate = data.rotate;
        datastart.rt = data.rt;
        datastart.s = data.s;
        datastart.sat = data.sat;
        datastart.sp = data.sp;
        datastart.st = data.st;
        datastart.type = data.type;
        datastart.val = data.val;
        datastart.vss = data.vss;
        return datastart;
    },

    /*转换格式数据*/
    fromfleet: function (item) {
        var fleet = new Object();
        fleet.ORG_ID = item.id;
        fleet.id = item.id;
        fleet.pId = item.pid;  //往上找的
        fleet.open = locate.bitopen;
        fleet.name = item.na;
        fleet.stust = item.o;
        fleet.type = item.type;
        bitopen = false;
        return fleet;
    },
    //判断一个元素是否存在数组里
    Arrayis: function (id) {
        var i = treeids.indexOf(id);
        var list = [];
        if (i == -1) {
            list[0] = -1;
            list[1] = false;
            return list
        } else {
            list[0] = i;
            list[1] = true;
            return list
        }
    },




}
$(function () {
    //创建地图
    webMap.events.onMapLoadCompleted['locateMap'] = locate.onMapLoaded;
    webMap.createMap("locateMap");

});




