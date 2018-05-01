/**
 *监控中心
 */

function Loc() {
   var addDiyDoms=function(treeId, treeNode) {
        var spaceWidth = 15;
        var liObj = $("#" + treeNode.tId );
        var aObj = $("#" + treeNode.tId  + "_a");
        var switchObj = $("#" + treeNode.tId  + "_switch");
        var spanObj = $("#" + treeNode.tId  + "_span");
        aObj.attr('title', '');
        //  aObj.append('<div class="diy"></div>');
        var div = $(liObj).find('div').eq(0);
        switchObj.remove();
        spanObj.remove();
        // icoObj.remove();
        // div.append(switchObj);
        // div.append(spanObj);
        var spaceStr = "<span>";
        // switchObj.before(spaceStr);
        var editStr = '';
        editStr += '<div class="diy">' + treeNode.name + '</div>';
        aObj.append(editStr);
    };

   var setting={
       view: {
           showLine: false,
           showIcon: true,
           addDiyDom: locate.addDiyDom
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
           beforeClick: locate.beforeClick,
           // onClick: zTreeOnClick,
           // onCheck: zTreeOnCheck
       }

   };
   var beforeClick=function(treeId, treeNode){
        if(treeNode.type ==0)
        {  // console.log(treeNode);
            $("#libuoonton").remove();
            var treeid = treeNode.tId;
            var butong ="<li class='libuoonton' id='libuoonton'> <input type='button' onclick='dataTreedian()' value='点名'></input> " +
                "<input type='button' value='资料'></input><input type='button' value='轨迹' onclick='guiji()'></input> <input type='button' value='回放' onclick='huifang()'></input></li>";
            //  $("#"+treeid).append(butong);
            var id = treeNode.id;
            if(!Arrayis(id)[1]){
                adddatalist.push(fromdevices(alldevices[id]));
                treeids.push(id);
                var tabledata ={list:adddatalist};
                var html = template("tpl", tabledata);
                $('#content_data').html(html);
            }
            // chushudataadd(id,true,true);
            // qiehuan(id);
        }
    };

}


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

    setting : {
        view: {
            showLine: false,
            showIcon: true,
            addDiyDom: locate.addDiyDom
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
            beforeClick: locate.beforeClick,
            // onClick: zTreeOnClick,
            // onCheck: zTreeOnCheck
        }

    },
    beforeClick:function(treeId, treeNode){
        if(treeNode.type ==0)
        {  // console.log(treeNode);
            $("#libuoonton").remove();
            var treeid = treeNode.tId;
            var butong ="<li class='libuoonton' id='libuoonton'> <input type='button' onclick='dataTreedian()' value='点名'></input> " +
                "<input type='button' value='资料'></input><input type='button' value='轨迹' onclick='guiji()'></input> <input type='button' value='回放' onclick='huifang()'></input></li>";
            //  $("#"+treeid).append(butong);
            var id = treeNode.id;
            if(!Arrayis(id)[1]){
                adddatalist.push(fromdevices(alldevices[id]));
                treeids.push(id);
                var tabledata ={list:adddatalist};
                var html = template("tpl", tabledata);
                $('#content_data').html(html);
            }
            // chushudataadd(id,true,true);
            // qiehuan(id);
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
    Treelist: {},


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
        console.log( " 地图加载成功" );
        locate.loadGroups();
    },
    drawMapLayers: function () {

    },
    //onSelectRow请求,但是事实上不是这个,他们连接的桥梁
    drawingOpen : function(callback) {

    },
    overlayComplete : function(e) {

    },
    /**
     * 加载组别与车辆树
     */
    loadGroups : function() {
        console.log( " 加载数据" );
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
                var list = [];
                for (var x = 0; x < data.length; x++) {
                    var item = data[x];
                    if (item.type === 0) {
                        item.s=gpsDataParser.parseStatus(item);
                        list.push(item);
                        onlines += item.o
                        locate.devices[item.dn] = item;
                        locate.Treelist.push(fromdevices(item));
                        if (item.o == 1) {
                            locate.onlinedevices[item.dn] = item;
                        } else {
                            locate.offlinedevices[item.dn] = item;
                        }
                    } else {
                        fleets[item.id] = item;
                        locate.Treelist.push(fromfleet(item));
                    }
                }
                locate.loadTree();
            },
        });
    },
    //初始化树
    loadTree:function() {
        $.fn.zTree.init($("#dataTree"), locate.setting, locate.Treelist);
        // treenodemove();
        //添加表头
        var li_head = '<span style="width: 100%;background: #c9c9c9"></span>' ;
        var rows = $("#dataTree").find('li');
        if (rows.length > 0) {
        } else {
            $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
        }
    },

}
$(function(){
    //创建地图
    webMap.events.onMapLoadCompleted['locateMap'] = locate.onMapLoaded;
    webMap.createMap("locateMap");

});




