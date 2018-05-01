/*设置树的属性*/
var alldevices = {};
var fleets = {};
var treeids =[];
var onlinedevices = [];
var offlinedevices = [];
var listdata=[];
var listdataflashing =[];
var adddatalist = [];
var form,table, layer, laypage,upload,bitopen=true;
var data;
var count=0;
var setting = {
    view: {
        showLine: false,
        showIcon: true,
        addDiyDom: addDiyDom
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
        beforeClick: beforeClick,
        onClick: zTreeOnClick,
        onCheck: zTreeOnCheck
    }

};
var setting2 = {
    view: {
        showLine: false,
        showIcon: true,
        addDiyDom: addDiyDoms
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    /* check: {
         enable: true,
     },*/

};


var listpoint =[];
$(function () {
    layui.use(['form', 'table', 'layer', 'laypage','upload'], function () {
        form = layui.form;
        table = layui.table;
        layer = layui.layer;
        laypage = layui.laypage;
        upload = layui.upload;
    });
    // quertyicon();
    maplistdata();
    $("#lishiguiji").click(function () {
        $("#box_mc").css("display","none");
        $("#box_upDown").css("display","none");
        $(".content_box .map-info .map-contents").css("height","100%");
    });
    /* var icon = new BMap.Icon('/gps_web/resources/new/image/tubiaoyuan.png', new BMap.Size(69,65), {
         imageOffset: new BMap.Size(0, 0),
         infoWindowAnchor: new BMap.Size(0, 0),
         anchor: new BMap.Size(30, 30)
     });
     locate.markers = new BMap.Marker(new BMap.Point("116.404", "39.915"), {
         icon: icon
     });
     var markers1 = new BMap.Marker(new BMap.Point("113.429139", "23.169492"), {
         icon: icon
     });

     var markers2 = new BMap.Marker(new BMap.Point("113.262232", "23.154345"), {
         icon: icon
     });

     var markers3 = new BMap.Marker(new BMap.Point("106.486654", "29.490295"), {
         icon: icon
     });
     var map1 = new BMap.Map("loadmapone");            // 创建Map实例
     var point1 = new BMap.Point(113.429139, 23.169492);
     map1.centerAndZoom(point1,15);
     map1.enableScrollWheelZoom();                  //启用滚轮放大缩小
     map1.addOverlay(markers1);
     //加载第二张地图
     var map2 = new BMap.Map("loadmaptwo");            // 创建Map实例
     var point2 = new BMap.Point(113.262232, 23.154345);
     map2.centerAndZoom(point2,15);
     map2.enableScrollWheelZoom();
     map2.addOverlay(markers2);

     var map3 = new BMap.Map("loadmapthree");            // 创建Map实例
     var point3 = new BMap.Point(106.486654, 29.490295);
     map3.centerAndZoom(point3,15);
     map3.enableScrollWheelZoom();                  //启用滚轮放大缩小
     map3.addOverlay(markers3);

     //加载第二张地图
     var map4 = new BMap.Map("loadmapfrou");            // 创建Map实例
     var point4 = new BMap.Point(116.404, 39.915);
     map4.centerAndZoom(point4,15);
     map4.enableScrollWheelZoom();
     map4.addOverlay(locate.markers);*/
    /*初始化布局*/
    /* var ss = window.screen.width;
     if(ss>1800){
         $(".bottom_infos").attr("class", "bottom_info");
         $("#finishingTask").attr("class", "thwidth");
         $("#leftul").attr("class", "leftul");
         $(".left_bottom ul li").css("margin-left","6%");
     }
     $('body').css('zoom', 'reset');
     $(document).keydown(function (event) {
         //event.metaKey mac的command键
         if ((event.ctrlKey === true || event.metaKey === true) && (event.which === 61 || event.which === 107 || event.which === 173 || event.which === 109 || event.which === 187 || event.which === 189)) {
             event.preventDefault();
         }
     });
     $(window).bind('mousewheel DOMMouseScroll', function (event) {
         if (event.ctrlKey === true || event.metaKey) {
             event.preventDefault();
         }
     });*/
    //启动计时器
    // timer();
    // timedCount(20);
    // queryguiji();
});


/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
    var li_head ;
    var i = 0;

    if(treeNode.pId ==null){
        var aObj = $("#" + treeNode.tId  + "_a");
        var switchObj = $("#" + treeNode.tId  + "_switch");
        var spanObj = $("#" + treeNode.tId  + "_span");
        var editStr = '';
        li_head = '<span style="width: 100%;background: #59B9C7;position: absolute;height: 3px;margin-top: 16px;left:0px"></span>' ;
        aObj.before(li_head);
    }
    if(treeNode.level ==1){
    }if(treeNode.pId != null && treeNode.type ==0){
        i++;
        var imgage  ="";
        var stylecolor="";
        if(treeNode.o==0){
            imgage ="<img src='/image/lixiandian.png'/>";
            stylecolor = "style= 'color:#A0A0A0'"
            if(treeNode.sp>0){
                stylecolor = "style= 'color:#4EA7F7'"
            }
        }else{
            imgage ="<img src='/image/zaixian.png'/>";
            stylecolor="style= 'color:#74CB63'";
        }
        var spaceWidth = 15;
        var liObj = $("#" + treeNode.tId );
        var aObj = $("#" + treeNode.tId  + "_a");
        var switchObj = $("#" + treeNode.tId  + "_switch");
        var spanObj = $("#" + treeNode.tId  + "_span");

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
        editStr += '<div class="diy divnema"'+ stylecolor+'>' + treeNode.na + '</div>';
        editStr += '<div class="diy">' + (treeNode.sp == null ? '&nbsp;' : treeNode.sp ) + '</div>';
        editStr += '<div class="diy">' + (treeNode.m == null ? '&nbsp;' : treeNode.m ) + '</div>';
        editStr += '<div class="diy">' + /*(treeNode. == "" ? '&nbsp;' : "已停" )*/"已停" + '</div>';
        aObj.append(editStr);
    }
}
function addDiyDoms(treeId, treeNode) {
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
}
/**
 * 查询数据
 */
function Treedata(str) {
    var json = [];
    var isfleet = {};
    for (devices in alldevices) {
        var property = devices;
        var item = alldevices[devices];

        if (item.na.indexOf(str) != -1) {
            json.push(fromdevices(item));
            if (isfleet[item.pid] === undefined) {
                var fleet = fleets[item.pid];
                isfleet[item.pid] = fleet;
                json.push(fromfleet(fleet));
            }
        }
    }
    $("#dataTree").empty();
    //初始化树
    $.fn.zTree.init($("#dataTree"), setting, json);
    //添加表头
    var li_head = '<span style="width: 100%;background: #c9c9c9"></span>' ;

    var rows = $("#dataTree").find('li');
    if (rows.length > 0) {
        //  rows.eq(0).before(li_head);
    } else {
        //  $("#dataTree").append(li_head);
        $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
    }
}
/*加载树数据
*初始化
* */
function query(isforce) {
    var alllist = [];
    var onlinelist = [];
    var offlinelist = [];
    var list = [];
    $.ajax({
        url: "/locate/groupVehicles", //请求地址
        dataType: "json", //数据格式
        data: {
            force: isforce
        },
        type: "post", //请求方式
        async: false, //是否异步请求
        success: function (data) { //如何发送成功
            data = data;
            // console.log(data);
            //总车辆
            bottomcardata(data);
            //匹配闪烁点
            bit_Change_location(data);
            var onlines = 0;
            var total = 0;
            for (var x = 0; x < data.length; x++) {
                var item = data[x];
                if (item.type === 0) {
                    item.s=gpsDataParser.parseStatus(item);
                    list.push(item);
                    //console.log(item.id);
                    alldevices[item.id] = item;
                    total += 1
                    onlines += item.o
                    alllist.push(fromdevices(item));
                    if (item.o == 1) {
                        onlinelist.push(fromdevices(item));
                    } else {
                        offlinelist.push(fromdevices(item));
                    }
                } else {
                    fleets[item.id] = item;
                    alllist.push(fromfleet(item));
                    onlinelist.push(fromfleet(item));
                    offlinelist.push(fromfleet(item));
                }
            }
            //初始化加载
            for(var tree in list){
                chushudataadd(list[tree].id,true,false);

            }
            //  $("#finishingTask").bootstrapTable("load", list);
            /* var tabledata ={list:list};
              var html = template("tpl", tabledata);*/
            // $('#content_data').html(html);
            /*  $('#txtAllVehicles').text(total);//全部
              $('#txtOnlineVehicles').text(onlines);//在线
              $('#txtOfflineVehicles').text(total - onlines);//离线
              console.log(alllist);
              console.log(onlinelist);
              console.log(offlinelist);*/
        },
    })
    //初始化树
    $.fn.zTree.init($("#offlinedataTree"), setting, offlinelist);
    $.fn.zTree.init($("#dataTree"), setting, alllist);
    $.fn.zTree.init($("#onlinedataTree"), setting, onlinelist);
    treenodemove();
    //添加表头
    var li_head = '<span style="width: 100%;background: #c9c9c9"></span>' ;
    var rows = $("#dataTree").find('li');
    if (rows.length > 0) {
        //  rows.before(li_head)
    } else {
        // $("#dataTree").append(li_head);
        $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
    }
    if(isforce ==true){
        setTimeout("mobileflashing("+3+")",700);
    }
}
/*
* 加载左侧底下的数据
* */
function bottomcardata(data) {
    var online=0 ;
    var offline=0;
    var sp = 0;
    for(var i in data){ data[i].type==0?(data[i].o==0?(offline++,offlinedevices.push(data[i])):(online++,sp += data[i].sp,onlinedevices.push(data[i]))):""}

    count  = offline+online;
    $(".cs_top span:nth-child(1)").html("总车辆:"+count);
    $(".cs_top span:nth-child(2)").html("当前在线率:"+(online/count).toFixed(2) *100+"%");
    $(".cs_middle span:nth-child(1)").html("在线:"+online);
    $(".cs_middle span:nth-child(2)").html("离线:"+offline);
    // $(".cs_bottom span:nth-child(1)").html("任务:"+data.);
    console.log(sp+"sp");
    $(".cs_bottom span:nth-child(2)").html("行驶:"+sp);

}

/*转换数据*/
/*function fromdevices(item) {
    var device = new Object();
    device.CONTACT_USER = item.sp; //速度
    device.SECTOR_NAME = item.na;//车辆号
    device.CONTACT_PHONE = "已停";//里程
    device.ORG_ID = item.pid;//父id
    device.id = item.id;
    device.pId = item.pid;
    device.name = item.na;
    device.CORP_CAT = item.m;
    device.iconClose = "/image/zaixian.png";
    device.iconOpen = "/image/zaixian.png";
    device.stust = item.o;
    device.type=item.type;
    return device;
}*/
/*转换格式数据*/
function fromfleet(item) {
    var fleet = new Object();
    fleet.ORG_ID = item.id;
    fleet.id = item.id;
    fleet.pId =item.pid;  //往上找的
    fleet.open = bitopen;
    fleet.name = item.na;
    fleet.stust = item.o;
    fleet.type=item.type;
    bitopen =false;
    return fleet;
}
$(function () {

    //初始化数据
    //query(false);
    $("#inputsrecsh").keydown(function () {//给输入框绑定按键事件
        if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            //  Treedata($(" #inputsrecsh ").val());
        }
    });
    $("#shuaxin").click(function () {
        query(true);
    })
})
//刷新
function refresh() {
    query(true);
}
function convertor(lng, lat, callback) {    // 纠偏
    $.ajax({
        async: false,
        url: "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&mode=1&x=" + lng + "&y=" + lat,
        type: "GET",
        dataType: 'jsonp',
        jsonp: 'callback',
        data: null,
        timeout: 5000,
        beforeSend: function () {

        },
        success: function (json) {// 客户端jquery预先定义好的callback函数,成功获取跨域服务器上的json数据后,会动态执行这个callback函数
            var point = new BMap.Point(json[0].x, json[0].y);
            callback(point);
        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        error: function (xhr) {
            // jsonp
            // 方式此方法不被触发.原因可能是dataType如果指定为jsonp的话,就已经不是ajax事件了
            // 请求出错处理
        }
    });
}
function queryAddress(point, callback) {   //获取位置
    var gc = new BMap.Geocoder();
    gc.getLocation(point, function (rs) {
        if (!rs)
            return '';
        var addComp = rs.addressComponents;
        var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
        if (address.length < 2) {
            address = "经度:" + 0 + ",纬度：" + 0;
        } else {
            var surround = rs.surroundingPois;
            for (var i = 0; i < surround.length; i++) {
                address += ",离" + surround[i].title + "约" + Math.round(locate.webMap.getDistance(point, surround[i].point)) + "米";
            }
        }
        callback(address);
    }, {
        poiRadius: 1000,
        numPois: 2
    });
}
//清除
function tool() {
    locate.webMap.mapObject.clearOverlays();
    query(true);
}
function toolnav() {
    $("#toolnav_ul").toggle(500);
}
//测距
function  ranging() {
    //console.log(BMapLib);
//添加测量面积工具
    //var measureAreaTool = createMeasureAreaTool(locate.map);
    //面积测量
    var tool = new BMapLib.DistanceTool(locate.webMap.mapObject);
    tool.open(); // 开启鼠标测距
}
//放大
function amplification(){
    window.locate.webMap.mapObject.zoomIn()
}
//缩小
function narrow() {
    window.locate.webMap.mapObject.zoomOut()
}
//打印地图
function  Print_map() {
    var myDate = new Date(); //获取时间
    var imagelayout="PNG"//获取导出的图片格式
    var targetDom = $("#bodyall_map");
    html2canvas(targetDom, { //要截图的区域ID
        background:"#fff",
        onrendered: function(canvas) {
            var Imgurl = canvas.toDataURL("image/png"); //画布转成图片
            //以下代码为下载此图片功能
            var triggerDownload =
                $("<a>").attr("href", Imgurl).attr("download", "" + myDate.getTime() + "."+imagelayout+"").appendTo("body");
            triggerDownload[0].click();
            triggerDownload.remove(); //下载图片到本地
        }
    });
}
//默认视野
function default_view(){
    var lng =116.404  ;
    var lat =39.915;
    var cent=15;
    $.ajax({
        url: "/viewport/get", //请求地址
        dataType: "json", //数据格式
        type: "Get", //请求方式
        success: function (data) {
            lng =data.lng;
            lat = data.alt;
            cent = data.level
        }

    });
    var point = new BMap.Point(lng, lat);
    locate.webMap.mapObject.centerAndZoom(point, cent);
}
//  刷新地图
function Refreshmap() {
    var pointfull = new BMap.Point("113.429136", "23.169302");
    Setcenter(pointfull, 4);
    query(true);
    //   mobileflashing(2);
    /*  locate.webMap.mapObject.clearOverlays();*/
    // locate.webMap.mapObject.reset();
}
//全屏
function Full_screen(){
    var element = document.getElementById("locateMap");
    element.webkitRequestFullScreen();
}
function cutDiv(){
    var myDate = new Date(); //获取时间
    var imagelayout="PNG"//获取导出的图片格式
    var divContent = document.getElementById("bodyall_map").innerHTML;
    var data = "data:image/svg+xml," +
        "<svg xmlns='http://www.w3.org/2000/svg' width='200' height='200'>" +
        "<foreignObject width='100%' height='100%'>" +
        "<div xmlns='http://www.w3.org/1999/xhtml' style='font-size:16px;font-family:Helvetica'>" +
        divContent +
        "</div>" +
        "</foreignObject>" +
        "</svg>";
    var img = new Image();
    img.src = data;
    var canvas = document.createElement("canvas");
    var ctx =  canvas.getContext("2d");
    img.crossOrigin="anonymous";
    img.src = "data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg'></svg>";
    ctx.drawImage(img, 0, 0);
    var canvasbase = canvas.toDataURL("image/png");
    var triggerDownload =
        $("<a>").attr("href", canvasbase).attr("download", "" + myDate.getTime() + "."+imagelayout+"").appendTo("body");
    triggerDownload[0].click();
    triggerDownload.remove(); //下载图片到本地
}
/**
 *
 * 触发点名
 */
function  dataTreedian() {
    for(var tree in treeid){
        chushudataadd(treeid[tree],false,true);
    }
}
/*定时刷新*/
function timer(){
    var t2 = window.setInterval("dataTreedian()",20000);
}
/*点击树节点获取对象*/
function beforeClick(treeId, treeNode) {
    //  console.log(locate.markers[treeNode.dn]);
    //BMap.Convertor.translate(BDPoint,0,translateCallback); //真实经纬度转成百度坐标
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
        qiehuan(id);
    }
}
//点击表格定位切换
function tables(id) {
    qiehuan(id);
}
/**
 * 切换图片定位
 */
function  qiehuan(id) {
    var device = alldevices[id];
    var geoc = new BMap.Geocoder();

    convertor(device.lng, device.lat, function (point) {
        Setcenter(point, 14);
    });
    var icon = new BMap.Icon('/static/image/tubiaoyuan.png', new BMap.Size(69,65), {
        imageOffset: new BMap.Size(0, 0),
        infoWindowAnchor: new BMap.Size(0, 0),
        anchor: new BMap.Size(30, 30)
    });
    var icon3 = new BMap.Icon('/static/image/defaultsmine.png', new BMap.Size(69,65), {
        imageOffset: new BMap.Size(0, 0),
        infoWindowAnchor: new BMap.Size(0, 0),
        anchor: new BMap.Size(30, 30)
    });
    //标注设置属性
    var labe2 = new BMap.Label("<span style='margin-left:5px'>"+device.sp+"km</span> <span style='position: relative;top: 40px; right: 45px; color: #0489D6;padding: 5px;'>"+device.na+" </span>",{offset:new BMap.Size(10,20)});
    labe2.setStyle({
        border: "none",
        height : "10px",
        lineHeight : "20px",
        margin:"0px 0px 0px 5px "
    });
    var labe3 = new BMap.Label("<span style='color:#0489D6;position: relative; right: 30px;'>"+device.na+"</span>" ,{offset:new BMap.Size(10,20)});
    labe3.setStyle({
        border: "none",
        height : "10px",
        lineHeight : "20px",
        margin:"0px 0px 0px 5px "
    });
    var allOverlay = locate.webMap.mapObject.getOverlays();
    for(var i  =0 ; i<allOverlay.length;i++){
        if(allOverlay[i].getTitle()==device.id){
            allOverlay[i].setIcon(icon);
            var label = allOverlay[i].getLabel()
            label.setContent("");//设置标签内容为空
            label.setStyle({borderWidth:"0px"});
            allOverlay[i].setLabel(labe2);
        }else{
            /*  allOverlay[i].setIcon(icon3);
              var label = allOverlay[i].getLabel();
              label.setContent("");//设置标签内容为空
              label.setStyle({borderWidth:"0px"});
              allOverlay[i].setLabel(labe3);*/
        }
    }

}

/*
* 计时器
* */
function  timedCount(c) {
    var t;
    c = c<= 0 ? 20: c;
    $('#txtOnlineVehicles').text(String(c));
    c--;
    t=setTimeout("timedCount("+c+")",1000);
}
/*
 加载数据   传来的id ,判断是不是第一次穿来的数据 true是第一次 自己设置中心点
* */
function chushudataadd(id,change,Number){
    var device = alldevices[id];
    var geoc = new BMap.Geocoder();
    convertor(device.lng, device.lat, function (point) {
        device.lng = point.lng;
        device.lat=point.lat;
        locate.devicespoint.push(device);
        //  console.log(locate.devicespoint);
        change==true?(Number==true?Setcenter(point, 14):""):"";
        queryAddress(point, function (address) {
            if (device != null) {
                //  console.log(address);
                var sContent = "<div style='margin:0px;padding:0px;border-top: 1px solid #c9c9c9;'>" +
                    "<div class='display-label'> <b>时间:</b> " + device.st  + "</div>" +
                    "<div class='display-label'> <b>定位:</b>" + gpsDataParser.parseLocateType(device) + "[" + gpsDataParser.parseDirection(device) +"]</div>"+
                    "<div class='display-label'><b>状态:</b>" + gpsDataParser.parseAcc(device) + "</div>" +
                    "<div class='display-label'><b>里程:</b> " + device.m + "km</div>"+
                    "<div class='display-label'><b>速度:</b>" + device.sp + "km</div>" +
                    "<div style='margin:2px;'><b>位置:</b>" + address + "</div>" +
                    "<div class='mon_button' id='The_alarm'>" +
                    "<span>街景</span> " +
                    "<span>追踪</span><span>轨迹</span><span>详情</span><span > 告警</span><b></b>"    +
                    "<span>指令</span><span>轨迹</span><span>详情</span><span>告警</span>"+
                    "</div>" +
                    "</div>";
                var data = [{
                    lng: point.lng,
                    lat: point.lat,
                    na: sContent
                }
                ]
                showGpsinfo(data, device,change);
            }
        });
    });
}
//根据点设置中心点
function Setcenter(point, Number) {
    locate.webMap.mapObject.centerAndZoom(point, Number);
}
//处理位置是否移动过   listdataflashing   存储变动过的数据
function bit_Change_location(data) {
    listdataflashing = [];
    //如果大于0,表示有数据
    if(listdata.length>0){
        //处理每条数据的id是否匹配
        for(var i =0;i<listdata[0].length;i++){
            for(var j =0;j<data.length;j++){
                if(data[j].type ==0 && listdata[0][i].id==data[j].id ){
                    //匹配成功计算经纬度是否一致
                    if(listdata[0][i].lat==data[j].lat && listdata[0][i].lng ==data[j].lng){
                        //一致,没变,不用处理
                    }else{
                        listdataflashing.push(listdata[0][i]);
                    }
                }
            }
        }
    }
    listdata =[];
    listdata.push(data);
}
//计算位置是否移动过,来一闪一闪功能
function mobileflashing(c) {

    var allOverlay = locate.webMap.mapObject.getOverlays();
    //获取地图上的点
    if(listpoint.length>0){
        for(k in listpoint){
            locate.webMap.mapObject.removeOverlay(allOverlay[k]);
        }}else{
        for (var i = 0; i < allOverlay.length ; i++){
            for(j in listdataflashing){
                if(allOverlay[i].getTitle() == listdataflashing[j].id){
                    listpoint.push(i);
                    locate.webMap.mapObject.removeOverlay(allOverlay[i]);
                }else {
                    //更换图片
                    /* var icon = new BMap.Icon('/gps_web/resources/new/image/car.png', new BMap.Size(69,65), {
                         imageOffset: new BMap.Size(0, 0),
                         infoWindowAnchor: new BMap.Size(0, 0),
                         anchor: new BMap.Size(30, 30)
                     });
                     allOverlay[i].setIcon(icon);*/
                }
            }}
    }
    c=c-1;
    setTimeout("showpoint("+c+")",700);
}
//显示
function showpoint(c) {
    if(c>0){
        for(var tree in listdataflashing){
            chushudataadd(listdataflashing[tree].id,true,false);
        }
        setTimeout("mobileflashing("+c+")",700);
    }else{
        for(var tree in listdataflashing){
            chushudataadd(listdataflashing[tree].id,true,false);
        }
        clearTimeout();
    }
}

//测距
function areacar() {
    locate.webMap.drawingOpen(locate.overlayComplete);
}
//测试打印
function printmap() {
    var headhtml = "<html><head><title></title></head><body>";
    var foothtml = "</body>";
    // 获取div中的html内容
    var newhtml = document.all.item("box_map").innerHTML;
    // 获取div中的html内容，jquery写法如下
    // var newhtml= $("#" + printpage).html();

    // 获取原来的窗口界面body的html内容，并保存起来
    var oldhtml = document.body.innerHTML;

    // 给窗口界面重新赋值，赋自己拼接起来的html内容
    document.body.innerHTML = headhtml + newhtml + foothtml;
    // 调用window.print方法打印新窗口
    window.print();

    // 将原来窗口body的html值回填展示
    document.body.innerHTML = oldhtml;
    location.reload();
    //Print('#locateMap');
}
/*
 * 根据x和y,加地址显示弹窗
 * */
function showGpsinfo(data, device,change) {
    var allOverlay = locate.webMap.mapObject.getOverlays();
    //  console.log(allOverlay);
    for(var i =0;i<allOverlay.length;i++){
        if(allOverlay[i].point !=null ) {

            // console.log(allOverlay[i].getTitle());
            //判断地图里是否有重复的id
            if (allOverlay[i].getTitle() == device.id) {
                //   console.log("重复了");
                locate.webMap.mapObject.removeOverlay(allOverlay[i]);
            }
        }
        //if(allOverlay[i].getPosition().y==data.lng)
    }
    var data_info = [];
    var title = "";
    title += '<div style="margin:0px;padding:0px">';
    title += '<div style="height:30px;margin:2px;">';
    title += '<b>';
    title += device.na;
    title += '</b>';
    title += '<div style="margin-left:15px;display:inline-block;">';
    title += '<div> </div>'; /*<img  src="/image/xiaoxi.png" />*/
    title += '</div>';
    title += '</div>';
    title += '</div>';
    for (var i = 0; i < data.length; i++) {
        data_info.push(data[0].lng);
        data_info.push(data[0].lat);
        data_info.push(data[0].na);
    }
    locate.opts = {
        width: 360,     // 信息窗口宽度
        height: 0,     // 信息窗口高度
        title: title, // 信息窗口标题
        enableMessage: true//设置允许信息窗发送短息
    };
    var icon = new BMap.Icon('/static/image/defaultsmine.png', new BMap.Size(69,65), {
        imageOffset: new BMap.Size(0, 0),
        infoWindowAnchor: new BMap.Size(0, 0),
        anchor: new BMap.Size(30, 30)
    });
    //注入标注
    locate.markers = new BMap.Marker(new BMap.Point(data_info[0], data_info[1]), {
        icon: icon
    });
    //标注设置属性"<span>12km</span> <span style='position: relative;top: 25px; background: #fff;padding: 5px;'>"+
    var label = new BMap.Label("<span style='color:#0489D6;position: relative; right: 30px;'>"+device.na+"</span>" ,{offset:new BMap.Size(10,20)});
    label.setStyle({
        border: "none",
        height : "10px",
        lineHeight : "20px",
        margin:"0px 0px 0px 5px "
    });
    //给每个标注的标题加入id
    locate.markers.setTitle(device.id) ;
    //locate.markers.setRotation(270);
    locate.markers.setLabel(label);
    var content = data_info[2];
    //添加标注
    locate.webMap.mapObject.addOverlay(locate.markers);
    addClickHandler(content, locate.markers);
    /*for(var i=0;i<data_info.length;i++){
        var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
        var content = data_info[i][2];
        locate.map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(content,marker);
    }*/
}
/**
 * 监听点击事件
 * @param content
 * @param marker
 */
function addClickHandler(content, marker) {
    marker.addEventListener("click", function (e) {
            openInfo(content, e);
            //  console.log(e);
        }
    );
}
/**
 * 弹窗
 * @param content
 * @param e
 */
function openInfo(content, e) {

    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    //locate.map.centerAndZoom(point, 12);
    var infoWindow = new BMap.InfoWindow(content, locate.opts);  // 创建信息窗口对象
    locate.webMap.mapObject.openInfoWindow(infoWindow, point); //开启信息窗口
}
/**
 * 跳转
 * @param page
 * @constructor
 */
function  Page(page) {
    var pageName  =page.innerText;
    switch (pageName){
        case "在线监控":
            window.location="/gps_web/center";
            break;
        case "司机管理":
            window.location="/gps_web/driver";
            break;
        case "多车跟踪":
            window.location="/gps_web/carstracking";
            break;
        case "统计分析":
            window.location="/gps_web/statistical";
            break;
        case "运行管理":
            window.location="/gps_web/run";
            break;
        case "报表管理":
            window.location="/gps_web/report";
            break;
        case "历史轨迹":
            window.location="/gps_web/history";
            break;
        case "信令管理":
            window.location="/gps_web/Signaling";
            break;
        case "告警管理":
            window.location="/gps_web/alarm";
            break;
        case "更多功能":
            window.location="/gps_web/More_functions";
            break;
        default:

    }
}
/**
 *  关闭面板的车辆信息
 * @type {Element}
 */
function penlarareacar() {
    $("#panelqucar").toggle(500);
}
function Drag_the(id) {
    //获取元素

    var dv = document.getElementById(id);
    var x = 0;
    var y = 0;
    var l = 0;
    var t = 0;
    var isDown = false;
//鼠标按下事件
    dv.onmousedown = function(e) {
        //获取x坐标和y坐标
        x = e.clientX;
        y = e.clientY;

        //获取左部和顶部的偏移量
        l = dv.offsetLeft;
        t = dv.offsetTop;
        //开关打开
        isDown = true;
        //设置样式
        dv.style.cursor = 'move';
    }
//鼠标移动
    window.onmousemove = function(e) {
        if (isDown == false) {
            return;
        }
        //获取x和y
        var nx = e.clientX;
        var ny = e.clientY;
        //计算移动后的左偏移量和顶部的偏移量
        var nl = nx - (x - l);
        var nt = ny - (y - t);

        dv.style.left = nl + 'px';
        dv.style.top = nt + 'px';
    }
//鼠标抬起事件
    dv.onmouseup = function() {
        //开关关闭
        isDown = false;
        dv.style.cursor = 'default';
    }
}
function Drag_thes(id) {
    //获取元素
    var ds = document.getElementById(id);
    var x = 0;
    var y = 0;
    var l = 0;
    var t = 0;
    var isDown = false;
//鼠标按下事件
    ds.onmousedown = function(e) {
        //获取x坐标和y坐标
        x = e.clientX;
        y = e.clientY;

        //获取左部和顶部的偏移量
        l = ds.offsetLeft;
        t = ds.offsetTop;
        //开关打开
        isDown = true;
        //设置样式
        ds.style.cursor = 'move';
    }
//鼠标移动
    window.onmousemove = function(e) {
        if (isDown == false) {
            return;
        }
        //获取x和y
        var nx = e.clientX;
        var ny = e.clientY;
        //计算移动后的左偏移量和顶部的偏移量
        var nl = nx - (x - l);
        var nt = ny - (y - t);

        ds.style.left = nl + 'px';
        ds.style.top = nt + 'px';
    }
//鼠标抬起事件
    ds.onmouseup = function() {
        //开关关闭
        isDown = false;
        ds.style.cursor = 'default';
    }
}
//位置服务
function Locationservice() {
    $("#Locationservice").toggle(500);
    locate.webMap.mapObject.setDefaultCursor("default");
}
//关闭位置服务
function Locatclose() {
    $("#Locationservice").toggle(500);
}
//监听
function markserver() {
    locate.webMap.mapObject.addEventListener("click", showInfo);
}
//监听位置服务光标事件
function showInfo(e){

    var html='<br /><div class="layui-upload-list" style="width:40%; margin:0 auto;">'
        +' <input type="text" id="inputname" name="title" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input"> />'
        +'<img class="layui-upload-img ui-border-radius" id="Picture" name="file"  src="/static/image/alarmpoint.png">'
        +'</div><br />'
        +'<div style="width:40%;height:20px;margin:0 auto;text-align:center;">'
        +'<button class="layui-btn layui-btn-primary" style="width:100%" id="SelectPicture">选择图标</button>'
        +'<button class="layui-btn" style="width:100%;margin-top:10px;margin-left:0px;"  id="UpdatePictures">保存图片</button>'
        +'<button class="ui-btn-lg ui-btn-primary" id="UploadPictures" style="display: none" >确定选择</button>'
        +'</div>';

    layer.open({
        type: 1, //此处以iframe举例
        title: ['选择上传图片'],//['标头'，'样式'];  title:false取消标题
        content: html,//text内容或html内容
        offset: 'auto',
        area: ['50%', '260px'],
        shade: 0.3,
        maxmin: false,
        //btn: ['确定选择', '取消'],
        //yes: function (index1) {
        //}
    });


//选择图片
    upload.render({
        elem: '#SelectPicture',
        url: '/icon/poi/upload'
        ,data:{
            name:   function(){

                return $('#inputname').val();

            },
            lng:function () {
                return   e.point.lng;
            } ,
            lat:function () {
                return   e.point.lat;
            }
        }
        , auto: false
        , multiple: false
        , accept: 'images'
        , size: 5120
        , bindAction: '#UploadPictures'
        , before: function () {
            //loadIndex = myloadAlertlayer1('上传中……');
        }
        , choose: function (obj) {
            obj.preview(function (index, file, result) {
                //对上传失败的单个文件重新上传，一般在某个事件中使用
                //  console.log(file);

                $('#Picture').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res, index, upload) {
            // layer.close(loadIndex);
            if(res.error=="ok..."){
                //询问框
                layer.open({
                    title: '提示',
                    content: '<span style="color:#38FB00">上传成功</span>',
                    shade: 0.3,//遮罩
                    area: ['270px', '180px'],

                    fixed: true, //固定
                    shadeClose: false//是否点击遮罩关闭
                    , btn: ['关闭']
                    , yes: function (index) {//确定
                        layer.close(index);//隐藏
                    }, end: function (index) {
                        layer.close(index);
                        layer.closeAll();

                    }
                });
            }

            quertyicon();
            location.reload();
            // alert(res)
        }, error: function (index, upload) {//上传图片失败
            // myAlertlayer1('提示', '上传图片失败！');
        }
    });
    //保存图片
    $('#UpdatePictures').click(function () {
        var src = $('#Picture').attr('src');
        if (src != "" && src != null) {
            $('#UploadPictures').click();
        } else {
            myAlertlayer1('提示', '未检测到有图片');
        }
    });
    var point = new BMap.Point(e.point.lng,e.point.lat);
    var marker = new BMap.Marker(point);// 创建标注
    locate.webMap.mapObject.addOverlay(marker);             // 将标注添加到地图中
    locate.webMap.mapObject.setDefaultCursor("pointer");
    locate.webMap.mapObject.removeEventListener("click", showInfo);
}
//定义临时数据
function  falsedata() {
    var data = [{"dn":"12123212321","na":"京B37A01","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":1,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067bc7b03682842ea3c8b","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"120187322661","na":"京B37A95","gt":"2018-04-18 01:26:41","st":"2018-04-18 09:26:42","val":1,"lng":113.429546,"lat":23.168944,"alt":0,"sp":0.0,"d":163,"a":0,"s":6,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":24,"sat":0,"id":"5aab54147b0368691e55c68e","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"10189415505","na":"京B37A93","gt":"2018-04-16 19:12:52","st":"2018-04-16 19:13:44","val":1,"lng":112.50104,"lat":37.80122,"alt":0,"sp":7.0,"d":257,"a":0,"s":786435,"o":0,"m":749.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":8,"sat":1,"id":"5aa741df7b03680b92edf012","pid":"5a72abd83d769a75b6309dee","type":0,"icon":" ","marker":"00.png","rotate":1},{"dn":"12345678911","na":"京B37A02","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5abc94297b03682842ea3567","pid":"5abc94017b03682842ea3566","type":0,"icon":" ","marker":"00.png","rotate":1},{"dn":"888888888888888","na":"京B37A03","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067e97b03682842ea3c8f","pid":"5a72abd83d769a75b6309dee","type":0,"icon":" ","marker":"00.png","rotate":1},{"dn":"3333333333333","na":"京B37A04","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067ad7b03682842ea3c8a","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"1111111111111","na":"京B37A05","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067947b03682842ea3c88","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"5555555555555","na":"京B37A06","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067c77b03682842ea3c8c","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"22222222222","na":"京B37A06","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067a17b03682842ea3c89","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"13311012700","na":"京B37A94","gt":"2018-03-23 09:35:38","st":"2018-03-23 09:35:39","val":1,"lng":113.429116,"lat":23.169497,"alt":0,"sp":0.0,"d":59,"a":0,"s":3,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5a74182b3d769a7dcc878d5d","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"99999999999999","na":"京B37A07","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067f57b03682842ea3c90","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"6666666666666","na":"京B37A09","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067d17b03682842ea3c8d","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":"777777777777","na":"京B37A08","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5ad067de7b03682842ea3c8e","pid":"5a72abd83d769a75b6309dee","type":0,"icon":"","marker":"00.png","rotate":1},{"dn":null,"na":"第一车队","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5a72abd83d769a75b6309dee","pid":"5a72c8733d769a75b6309ff0","type":1,"icon":"","marker":null,"rotate":0},{"dn":null,"na":"锐讯易通","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5a72ab073d769a75b6309dec","pid":"5a72c8733d769a75b6309ff0","type":2,"icon":"","marker":null,"rotate":0},{"dn":null,"na":"测试公司","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5abc8e257b03682842ea33e9","pid":"5a72c8733d769a75b6309ff0","type":2,"icon":"","marker":null,"rotate":0},{"dn":null,"na":"测试公司的测试车队","gt":null,"st":null,"val":0,"lng":0.0,"lat":0.0,"alt":0,"sp":0.0,"d":0,"a":0,"s":0,"o":0,"m":0.0,"oil":0.0,"vss":0.0,"ovt":0,"oid":0,"iot":0,"iid":0,"iof":0,"rid":0,"rt":0,"rf":0,"aid":0,"exs":0,"ios":0,"ad0":0,"ad1":0,"net":0,"sat":0,"id":"5abc94017b03682842ea3566","pid":"5abc8e257b03682842ea33e9","type":1,"icon":"","marker":null,"rotate":0}];
    return data;
    //  console.log(data);
}
//地图列表
function maplistdata() {
    var list=[];
    $.ajax({
        url: "/dictionary/list", //请求地址
        dataType: "json", //数据格式
        data: {
            kind: 5,
            grid:true,
        },
        type: "post", //请求方式
        async: false, //是否异步请求
        success:function (data) {
            var zNodes="[{"
            for(i in data.rows){
                list.push(mapTransformdata(data.rows[i]));
                // var ss =  data.rows[i].pid;
                /* if(ss==undefined){
                     zNodes+="name:"+ss.name+",open:true"
                 }*/
            }
            //  console.log(data);
            $.fn.zTree.init($("#dataTreedis"), setting2,list);
            /* var htmlmap = {list:data.rows}
            $("#maplist_tpl").html(htmlmap);*/
        }
    })
}
//转换数据地图列表的
function  mapTransformdata(datas) {
    var pids =datas.pid;
    if(pids ==undefined){
        pids=0;
    }
    var data = new Object();
    data.pId = pids;
    data.name = datas.name;
    data.id=datas.id;
    data.open = true;
    return data;
}
//信息框
//layer 信息提示弹窗_墨绿
function myAlertlayer1(title, content) {
    layer.alert(content, {
        title: title, skin: 'layui-layer-molv', shadeClose: true
    }, function (i, d) { layer.close(i); });
}
//初始化标注
function quertyicon() {
    var imagepath = "/stat";
    // var datalist=[];
    $.ajax({
        url: "/icon/poi/query", //请求地址*/
        dataType: "json", //数据格式
        type: "GET", //请求方式
        async: false, //是否异步请求
        success:function (data) {
            var allimage =  data.msg;
            if(data.rows.length>0){
                for(var datas in data.rows){
                    markloadlocet(data.rows[datas].lng,data.rows[datas].alt, data.rows[datas].name,true);
                    //datalist.push(data.rows[datas].showName);
                }
                var tabledata ={list:data.rows};
                var html = template("tpllocat", tabledata);
                $('#locat_Data').html(html);
            }}
    })
}
/*
  标注
 参数:经纬度,图片,是否启用纠偏
 */
function markloadlocet(lnga,lata,image,isrectifying) {
    var lng = lnga;
    var lat  = lata;
    if(isrectifying == true){
        var point = new BMap.Point(lng,lat);
        var myIcon = new BMap.Icon("/static/icons/"+image, new BMap.Size(20,20));
        var marker2 = new BMap.Marker(point, {
            icon: myIcon
        });
        // 创建标注
        locate.webMap.mapObject.addOverlay(marker2);
    }
}
/*
* 树节点移入
* */
function treenodemove() {
    var butong = "<li class='libuoonton' id='libuoonton'> <input type='button' onclick='dataTreedian()' value='点名'></input> " +
        "<input type='button' value='资料'></input><input type='button' value='轨迹' onclick='guiji()'></input> <input type='button' value='回放' onclick='huifang()'></input></li>";

    $(".ztree li").hover(function (e) {

        $(".libuoonton").remove();

        console.log($(this));
        var isName =$(this).find("a");
        //  console.log(isName);
        if(isName.length==count){
        }else{
            for(var i=0;i<isName.length;i++){
                //  console.log(isName[0].childNodes[1]);
                if(isName[i].childNodes[1].className=="diy divnema"){
                    $(isName[i]).parent().append(butong);
                    //$(isName[i]).parent().css("height","50px")
                }
            }
        }
    });
}
//判断一个元素是否存在数组里
function Arrayis(id) {
    var i =treeids.indexOf(id);
    var list=[];
    if(i==-1){
        list[0] =-1;
        list[1]= false;
        return list
    }else {
        list[0] =i;
        list[1]= true;
        return list
    }
}
//侧面,隐藏,显示。
function hidden_bottom() {
    $(".cs_bottom").toggle(500);
    $(".cs_middle").toggle(500);
    $(".cs_top span:nth-child(3)").text()=="显示"? $(".cs_top span:nth-child(3)").text("隐藏"):$(".cs_top span:nth-child(3)").text("显示");

}
//勾选父节点
function zTreeOnClick(event, treeId, treeNode) {
    //  alert(treeNode.tId + ", " + treeNode.name);
    /* var treeObj = $.fn.zTree.getZTreeObj("tree");
     var nodes = treeObj.getCheckedNodes(true);*/
    //console.log(treeObj)
}
//勾选树节点
function zTreeOnCheck(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("dataTree"), treeNode;
    var id = treeNode.id;
    var childrendata = treeNode.children==undefined?undefined:typecar(treeNode.children);

    if(childrendata!=undefined){
        treeNode.checked==true?adddataztree(childrendata):removecarteam(childrendata);
    }else{
        //被勾选到
        treeNode.checked==true? beforeClick(treeId,treeNode):removercar(treeNode);
    }
    // alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
};

//勾选某车队
function adddataztree(childrendata) {
    for(var i=0;i<childrendata.length;i++){
        var dataxhuanuhan  = childrendata[i].id;
        if(!Arrayis(dataxhuanuhan)[1]){
            adddatalist.push(childrendata[i]);
            treeids.push(dataxhuanuhan);
        }
    }
    var tabledata ={list:adddatalist};
    var html = template("tpl", tabledata);
    $('#content_data').html(html);
}

//移除关注的某车队
function removecarteam(childrendata) {
    for(var i=0;i<childrendata.length;i++){
        var listis =Arrayis(childrendata[i].id);
        //存在
        if(listis[1]){
            //把存在的移除掉
            //treeids.splice(listis[0]);
            //adddatalist.splice(jQuery.inArray(childrendata[i],adddatalist),1);
            /*    treeids.splice(jQuery.inArray(childrendata[i].id,treeids),listis[0]);*/
            removercarliang(childrendata[i].id);
            removerarr(treeids,childrendata[i].id);
        }
    }
    var tabledata ={list:adddatalist};
    var html = template("tpl", tabledata);
    $('#content_data').html(html);
}
//移除某个车辆
function removercar(treeNode) {
    var listis =Arrayis(treeNode.id);
    if(listis[1]){
        removercarliang(treeNode.id);
        removerarr(treeids,treeNode.id);
    }
    var tabledata ={list:adddatalist};
    var html = template("tpl", tabledata);
    $('#content_data').html(html);
}
//在线车辆
function Onlinecar() {
    bitopen =true;
    //目前假数据,真数据需要修改
    var data =onlinedevices;
    $("#dataTree").empty();
    var listdatacer =[];
    for(var i =0;i<data.length;i++){
        var item = data[i];
        if (item.type === 0) {
            //console.log(item.id);
            if (item.o == 1) {
                listdatacer.push(fromdevices(item));
            }
        }else{
            listdatacer.push(fromfleet(item));
        }
    }
    $.fn.zTree.init($("#dataTree"), setting,listdatacer);
    treenodemove();
}
//离线车辆
function offlinecar() {
    bitopen =true;
    $("#dataTree").empty();
    var data =falsedata();
    var listdatacer =[];
    for(var i =0;i<data.length;i++){
        var item = data[i];
        if (item.type === 0) {
            //console.log(item.id);
            if (item.o != 1) {
                listdatacer.push(fromdevices(item));
            }
        }else{
            listdatacer.push(fromfleet(item));
        }
    }
    $.fn.zTree.init($("#dataTree"), setting,listdatacer);
    treenodemove();
}
//总车辆
function countcar() {
    bitopen =true;
    $("#dataTree").empty();
    var data =falsedata();
    var listdatacer =[];
    for(var i =0;i<data.length;i++){
        var item = data[i];
        if (item.type === 0) {
            //console.log(item.id)
            listdatacer.push(fromdevices(item));
        }else{
            listdatacer.push(fromfleet(item));
        }
    }
    $.fn.zTree.init($("#dataTree"), setting,listdatacer);
    treenodemove();
}
//转换刚加载数据
function fromdevices(data) {
    var datastart = new Object();
    datastart.a=data.a;
    datastart.ad0=data.ad0;
    datastart.ad1=data.ad1;
    datastart.aid=data.aid;
    datastart.alt=data.alt;
    datastart.d=data.d;
    datastart.dn=data.dn;
    datastart.exs=data.exs;
    datastart.gt=data.gt;
    datastart.icon=data.icon;
    datastart.id=data.id;
    datastart.iid=data.iid;
    datastart.iof=data.iof;
    datastart.ios=data.ios;
    datastart.iot=data.iot;
    datastart.lat=data.lat;
    datastart.lng=data.lng;
    datastart.m=data.m;
    datastart.marker=data.marker;
    datastart.na=data.na;
    datastart.net=data.net;
    datastart.o=data.o;
    datastart.oid=data.oid;
    datastart.oil=data.oil;
    datastart.ovt=data.ovt;
    datastart.pId=data.pid;
    datastart.rf=data.rf;
    datastart.rid=data.rid;
    datastart.rotate=data.rotate;
    datastart.rt=data.rt;
    datastart.s=data.s;
    datastart.sat=data.sat;
    datastart.sp=data.sp;
    datastart.st=data.st;
    datastart.type=data.type;
    datastart.val=data.val;
    datastart.vss=data.vss;

    return datastart;

}
//移除存储id
function removerarr(arr,val) {
    var index = arr.indexOf(val);
    if (index > -1) {
        arr.splice(index, 1);
    }
}
//移除车辆
function removercarliang(id) {
    for(var i =0;i<adddatalist.length;i++){
        if(adddatalist[i].id==id){
            adddatalist.splice(i, 1);
        }
    }
    /*if(arr.indexOf&&typeof(arr.indexOf)=='function'){
        var index = arr.indexOf(val);
        if(index >= 0){

        }
    }  return false;*/
}
//递归是否类型是车
function typecar(data) {
    for(var i=0;i<data.length;i++){
        if(data[i].type==0){
            return data;
        }else{
        }
        return typecar(data[i].children);
        if(data[i].type==0){
            return data;
        }else {
        }
    }
}
//点击名称定位
function locatpoint(id,lat,lng) {

    var point = new BMap.Point(lng,lat);
    Setcenter(point,10);
}