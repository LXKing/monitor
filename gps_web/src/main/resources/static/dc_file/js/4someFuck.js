/*设置树的属性*/
var alldevices = {};//根据id查询数据类型是0
var alldevicespidandlocatName = [];
var fleets = {};//pid的存储
var treeids = [];//定义关注的存储id
var Timerefreshid = [];//存储id定时刷新
var onlinedevices = [];
var offlinedevices = [];
var listdata = [];//
var listdataflashing = [];//
var adddatalist = [];//添加数据列表
var addNamelist = [];//添加名称
var form, table, layer, laypage, upload, bitopen = true;//layer的属性
var count = 0;//数据的总数
var suncount = 0;//每次加载子id的总数
var treecount = 0;//节点总数
var datasour = [];//数据源
var overlays = [];//存储测距的数据
var clickFlag = null;//是否点击标识（定时器编号）
var Serialnumber = 0; //编号
var Clickidstorage = [];
var insides = []; //查到全部的
var finishingTaskonlinedata = []; //在线
var finishingTaskofflinedata = [];//离线
var timerresetTime = null;
var queryicondata = [];
var isopenbyid = [];
var refreshmapid = [];
var directionid = [];
var speed_limit =100;
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
        chkboxType: {"Y": "s", "N": "ps"},
    },
    callback: {
        beforeClick: beforeClick,
        onCheck: zTreeOnCheck,
        beforeDblClick: beforeDblClick,
    },


};
var setting2 = {
    view: {
        showLine: false,
        showIcon: true,
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: 0
        }
    },
};


var listpoint = [];
$(function () {
    layui.use(['form', 'table', 'layer', 'laypage', 'upload'], function () {
        form = layui.form;
        table = layui.table;
        layer = layui.layer;
        laypage = layui.laypage;
        upload = layui.upload;
    });
    // quertyicon();
    maplistdata();
    //启动计时器
    // timer();
    resetTime(21);
    // timedCount(20);
    // queryguiji();
});

/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
    var li_head;
    if (treeNode.pId == null) {
        var aObj = $("#" + treeNode.tId + "_a");
        var switchObj = $("#" + treeNode.tId + "_switch");
        var spanObj = $("#" + treeNode.tId + "_span");
        var editStr = '';
        li_head = '<span style="width: 100%;background: #59B9C7;position: absolute;height: 3px;margin-top: 16px;left:0px"></span>';
        aObj.before(li_head);
    }
    treeNode.level == 0 ? Serialnumber = 0 : "";
    if (treeNode.level == 1) {
    }
    if (treeNode.pId != null && treeNode.type == 0) {
        Serialnumber = Serialnumber + 1;
        // i++;
        var imgage = "";
        var stylecolor = "";
        var runstaust = "";
        if(treeNode.o==0){
            //imgage ="<img src='/image/lixiandian.png'/>";
            stylecolor = "style= 'color:#a4a4a4'"
            runstaust="离线";
        }else{
            //  imgage ="<img src='/image/zaixian.png'/>";
            stylecolor="style= 'color:#2ac42a'";
            runstaust="在线";
            if(treeNode.sp>0){
                stylecolor = "style= 'color:#4fa8f8'"
                runstaust="行驶";
                if(treeNode.sp>=speed_limit){
                    stylecolor = "style= 'color:fa6561'"
                    runstaust="报警";
                }
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
        editStr += '<div class="diy widthmin">' + intonumber(Serialnumber) + '</div>';
        editStr += '<div class="diy divnema"' + stylecolor + '>' + treeNode.na + '</div>';
        //js: Math.ceil(7/2)
        editStr += '<div class="diy">' + treeNode.sp + '</div>';
        editStr += '<div class="diy mileage">' + (treeNode.m == null ? '&nbsp;' : treeNode.m) + '</div>';
        editStr += '<div class="diy">' + runstaust + '</div>';
        aObj.append(editStr);
    }
}

//转成00编号
function intonumber(varlue) {
    var newnumberbyte = varlue / 100;
    var newnumber = newnumberbyte.toString().split(".");
    //  console.log(newnumber)
    var infonew = newnumber[0] + newnumber[1];
    return infonew;
}

/*加载树数据
*初始化
* */
function getdataStr() {
    // console.log(datasour);
    var strs = [];
    for (var i = 0; i < datasour.length; i++) {
        if (typeof(datasour[i].dn) != "undefined") {
            strs.push(datasour[i].dn);
            //  console.log(datasour[i].dn);
        }
    }
    // console.log(strs);
    $("#number").keyup(function () {
        var t_value = $("#number").val();
        if (t_value.length == 0) {
            $("#cm_cardwarper").html("");
            return;
        }
        var fr_arr = [];
        for (var i = 0; i < strs.length; i++) {
            if (strs[i].indexOf(t_value) == 0) {
                fr_arr.push(strs[i]);
            }
        }
        //console.log(fr_arr);
        if (fr_arr.length > 0) {
            var divDom = $("<div></div>").attr("id", "pop").css("width", $("#number").width()).css("zIndex", "999");
            var ulDom = $("<ul></ul>");
            divDom.append(ulDom);
            for (var i = 0; i < fr_arr.length; i++) {
                var add_li = $("<li></li>").addClass("li_this").text(fr_arr[i]);
                ulDom.append(add_li);
            }

            $("#cm_cardwarper").html(divDom);
        }
        $(".li_this").mouseover(function () {
            $(this).css("backgroundColor", "#dedede");
        });
        $(".li_this").mouseout(function () {
            $(this).css("backgroundColor", "#fff");
        });
        $(".li_this").click(function () {
            $("#number").val($(this).text());
            $(this).parents("#pop").remove();
        });
    });
}

function query(isforce, torefresh, loc) {
    var alllist = [];
    var onlinelist = [];
    var offlinelist = [];
    var counlist = [];
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
            datasour = data;
            getdataStr();


            window.fucking = function () {
                return data;
            }
            cnmmmmm();
            window.fuckShitArr = data;


            //  data = falsedata();
            // console.log(data);
            //总车辆
               bottomcardata(data);
            //匹配闪烁点
            bit_Change_location(data);
            var onlines = 0;
            var total = 0;
            for (var x = 0; x < data.length; x++) {
                var item = data[x];
                counlist.push(item.id);
                if (item.type === 0) {
                    item.s = gpsDataParser.parseStatus(item);
                    list.push(item);
                    //console.log(item.id);
                    alldevices[item.id] = item;
                    addNamelist.push(item.na);
                    total += 1
                    onlines += item.o
                    alllist.push(fromdevices(item));
                    if (item.o == 1) {
                        onlinelist.push(fromdevices(item));
                    } else {
                        offlinelist.push(fromdevices(item));
                    }
                } else {
                    //alldevicespid.push()
                    fleets[item.id] = item;
                    alllist.push(fromfleet(item));
                    onlinelist.push(fromfleet(item));
                    offlinelist.push(fromfleet(item));
                }
            }
            //初始化加载


            for (var aaa in window.webMap.maps) {
                // console.log(   window.mapG[aaa])
                // console.log(    aaa)
                // console.log(  window.mapG)
                // console.log( aaa)
                var mmmp = window.mapG[aaa];

                qiehuan(mmmp.mmp, mmmp)
                // mmmp.webMap.mapObject.clearOverlays();
                // quertyicon(mmmp);
                // mmmp.devicespoint = [];
                // for (var tree in list) {
                //     chushudataadd(list[tree].id, true, false, mmmp);
                //     Timerefreshid.push(list[tree].id);
                // }

            }

        },
    })
    if (torefresh == false) {
        alllist = Set_whether_open(alllist);
    }
    else {
        //初始化所属公司和所属车队
         datacompanyandcar(datasour, counlist);

    }
    bitopen = true;
    Serialnumber = 0;
    $.fn.zTree.init($("#dataTree"), setting, alllist);
    treenodemove(true);
    var rows = $("#dataTree").find('li');
    if (rows.length > 0) {
    } else {
        $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
    }
    //用户手动刷新
    if (isforce == true) {
        var point = new BMap.Point("104.863586", "34.866505");

        for (var aaa in window.webMap.maps) {
            // console.log(   window.mapG[aaa])
            // console.log(    aaa)
            // console.log(  window.mapG)
            // console.log( aaa)
            var mmmp = window.mapG[aaa];

            Setcenter(point, 5, mmmp);

        }


        //  setTimeout("mobileflashing("+3+")",700);
    } else {
        //整理思路,  手动刷新为true 否则为false
    }

    // treenodemovebackground();
    //表格里的数据
    if (treeids.length > 0) {
        //  console.log(alldevices);
        adddatalist = [];
        for (var i = 0; i < datasour.length; i++) {
            for (var j = 0; j < treeids.length; j++) {
                if (datasour[i].id == treeids[j]) {
                    for (var z = 0; z < alldevicespidandlocatName.length; z++) {
                        if (datasour[i].id == alldevicespidandlocatName[z].id) {
                            datasour[i].locatName = alldevicespidandlocatName[z].locatName;

                        }
                    }
                    adddatalist.push(returnteamName(fromdevices(datasour[i])));
                    refreshmapid[datasour[i].id] = datasour[i].id;
                    //  console.log(refreshmapid);
                }
            }
        }
       // var tabledata = {list: adddatalist};
       // var html = template("tpl", tabledata);
       // $('#content_data').html(html);
        tdmousever();
    }
}
//绑定下拉框的所属公司与所属车队
function datacompanyandcar(data,counlist) {
    var form = layui.form;
    var car =[]
    var company =[];
    $("#Company").empty();
    $("#team").empty();
    $("#Companypanelqucar").empty();
    $("#Companononline").empty();
    $("#Companonoffline").empty();
    $("#Companonlocat").empty();
    document.getElementById('Company').add(new Option("全部", 0));
    document.getElementById('team').add(new Option("全部", 0));

    for(var i = 0 ;i<data.length;i++){
        // var ispid =  istopPid(data[i].pid,counlist);根据最高顶级

        if(data[i].type==2){
            //  car.push(data[i]);  //绑定顶级值
            document.getElementById('Company').add(new Option(data[i].na, data[i].id));//绑定DropDownList的value值，text值
        }

        if(data[i].type==1){
            //     company.push(data[i]);
            document.getElementById('team').add(new Option(data[i].na, data[i].id));//绑定DropDownList的value值，text值

        }
    }
    form.render();
    form.on('select(test)', function(data){
        bitopen =true;
        var id = data.value;
        var datasyuan = fleets[id];
        var listdatas =[];
        var datas =datasour;
        if(data.value!="0"){
            $("#team").empty();
            for(var i = 0;i<datas.length;i++){
                if(datas[i].pid==data.value && datas[i].type!=2){
                    document.getElementById('team').add(new Option(datas[i].na, datas[i].id));
                    //获取属于这个公司的车队与车的数据
                    listdatas.push(datas[i]);
                }
                else if(datas[i].pid==data.value && datas[i].type==0){
                    $("#team").html("没有车队了。");
                    listdatas.push(datas[i]);
                }
            }
            // listdatas.push(datasyuan);
            listdatas  = recursiveteam(listdatas,datas);
            $("#dataTree").empty();
            Serialnumber=0;
            $.fn.zTree.init($("#dataTree"), setting,listdatas);
            treenodemove(true);
        }else {
            $("#team").empty();
            document.getElementById('team').add(new Option("全部", 0));
            for(var i = 0 ;i<datas.length;i++){
                if(datas[i].type==1){
                    document.getElementById('team').add(new Option(datas[i].na, datas[i].id));//绑定DropDownList的value值，text值
                }
            }
            countcar();
        }

        // ifuptype(listdatas);
        form.render();
        //treenodemove(true);
        console.log(datas);
    });
    form.on('select(teamss)',function (data) {
        bitopen =true;
        var listdatas =[];
        var id = data.value;
        if(data.value!="0"){
            var datas =datasour;

            var datasyuan = fleets[id];
            listdatas.push(datasyuan);
            listdatas  =   recursiveteam(listdatas,datas);
        }else{
            for (var i = 0; i < datasour.length; i++) {
                if(datasour[i].type==0){
                    listdatas.push(fromdevices(datasour[i]));
                }else{
                    listdatas.push(fromfleet(datasour[i]));
                }
            }
        }
        $("#dataTree").empty();
        Serialnumber=0;
        $.fn.zTree.init($("#dataTree"), setting,listdatas);
        treenodemove(true)})

}
//车队的类型添加车  参数:车队的数据与整个数据  返回车队与车的数据
function recursiveteam(datateamlist,data) {
    var countlist=[];
    for(var j =0;j<datateamlist.length;j++){
        countlist.push(fromfleet(datateamlist[j]));
        for(var i =0 ;i<data.length;i++){
            if(data[i].pid==datateamlist[j].id && data[i].type==0){
                countlist.push(fromdevices(data[i]));
            }
        }}
    return countlist;
}
/*转换格式数据*/
function fromfleet(item) {
    var fleet = new Object();
    fleet.ORG_ID = item.id;
    fleet.id = item.id;
    fleet.pId = item.pid;  //往上找的
    fleet.open = bitopen;
    fleet.name = item.na;
    fleet.stust = item.o;
    fleet.type = item.type;
    bitopen = false;
    return fleet;
}

$(function () {
    //初始化数据

    //创建地图
    // webMap.createMap("locateMap");
    // webMap.events.onMapLoadCompleted['locateMap'] = locate.onMapLoaded;

    window.mapG = {};
    window.mapG["fh_map"] = cloneObj(locatedddddd);

    window.mapG["ft_map"] = cloneObj(locatedddddd);
    window.mapG["sd_map"] = cloneObj(locatedddddd);
    window.mapG["td_map"] = cloneObj(locatedddddd);


    var a = webMap.createMap("ft_map");
    console.log(a)
    window.webMap.events.onMapLoadCompleted['ft_map'] = window.mapG["ft_map"].onMapLoaded;

    console.log(window.mapG["ft_map"].onMapLoaded)
    var img0 = $("<img/>").attr("src","../static/dc_file/image/s_one.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
    $("#ft_map").append(img0);
    var div0 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
    var img1 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
    div0.append(img1);
    $("#ft_map").append(div0);


    var b = webMap.createMap("sd_map");
    console.log(b)
    webMap.events.onMapLoadCompleted['sd_map'] = window.mapG["sd_map"].onMapLoaded;

    var img3 = $("<img/>").attr("src","../static/dc_file/image/s_two.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
    $("#sd_map").append(img3);
    var div1 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
    var img4 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
    div1.append(img4);
    $("#sd_map").append(div1);

    var c = webMap.createMap("td_map");
    console.log(c)

    webMap.events.onMapLoadCompleted['td_map'] = window.mapG["td_map"].onMapLoaded;
    var img5 = $("<img/>").attr("src","../static/dc_file/image/s_three.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
    $("#td_map").append(img5);
    var div2 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
    var img6 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
    div2.append(img6);
    $("#td_map").append(div2);

    var d = webMap.createMap("fh_map");
    console.log(d)
    webMap.events.onMapLoadCompleted['fh_map'] = window.mapG["fh_map"].onMapLoaded;
    var img7 = $("<img/>").attr("src","../static/dc_file/image/s_four.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
    $("#fh_map").append(img7);
    var div3 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
    var img8 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
    div3.append(img8);
    $("#fh_map").append(div3);

    $(".map-contents3>.s_map>.cut_sn").click(function () {
        var flag_0 = $(this).parent().siblings(".s_map").css("display")=="block";
        var flag_ = $(this).parent().siblings(".s_map").css("display")=="none";
        if(flag_0){
            $(this).parent().css("width","100%").css("height","100%").siblings(".s_map").css("display","none");
        }else if(flag_){
            $(this).parent().css("width","49.9%").css("height","49.9%").siblings(".s_map").css("display","block");
        }
    })
})

//刷新
function refresh() {
    //第二个参数为true就是重新加载
    //  alert("改功能正在开发");
    query(true, true);
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

function bottomcardata(data) {
    var online=0 ;
    var offline=0;
    var sp = 0;
    for(var i in data){ data[i].type==0?(data[i].o==0?(offline++,offlinedevices.push(data[i]),sp += data[i].sp):(online++,onlinedevices.push(data[i])))
        :""}
    count  = offline+online;
    $(".cs_top span:nth-child(1)").html("总车辆:"+count);
    $(".cs_top span:nth-child(2)").html("在线率:"+ Math.round((online/count).toFixed(2) *100)+"%");
    $(".cs_middle span:nth-child(1)").html("在线:"+online);
    $(".cs_middle span:nth-child(2)").html("离线:"+offline);
    // $(".cs_bottom span:nth-child(1)").html("任务:"+data.);
    // console.log(sp+"sp");
    $(".cs_bottom span:nth-child(2)").html("行驶:"+sp*10+"km/h");

}

function queryAddress(point, callback, loc) {   //获取位置
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
                address += ",离" + surround[i].title + "约" + Math.round(loc.webMap.getDistance(point, surround[i].point)) + "米";
            }
        }
        callback(address);
    }, {
        poiRadius: 1000,
        numPois: 2
    });
}

//
// //清除
// function tool() {
//     var getmapoverlays = locate.webMap.mapObject.getOverlays();
//     for (var i = 0; i < getmapoverlays.length; i++) {
//         if (getmapoverlays[i].content != undefined) {
//             //  console.log(getmapoverlays[i].content.indexOf("总长"));
//             locate.webMap.mapObject.removeOverlay(getmapoverlays[i]);
//         }
//         if (getmapoverlays[i].V != undefined) {
//             //  console.log(getmapoverlays[i].V.title);
//             if (getmapoverlays[i].V.title == "清除本次测距") {
//                 locate.webMap.mapObject.removeOverlay(getmapoverlays[i]);
//             }
//         }
//     }
//     // console.log(getmapoverlays);
//     for (var i = 0; i < overlays.length; i++) {
//         locate.webMap.mapObject.removeOverlay(overlays[i]);
//     }
//     overlays = [];
//     // locate.webMap.mapObject.clearOverlays();
//     ;
// }
//
// //点击工具栏
// function toolnav() {
//     $("#toolnav_ul").toggle(500);
// }
//
// //测距
// function ranging() {
//     overlays = [];
//     //console.log(BMapLib);
// //添加测量面积工具
//     //var measureAreaTool = createMeasureAreaTool(locate.map);
//     //面积测量
//     var tool = new BMapLib.DistanceTool(locate.webMap.mapObject);
//     tool.addEventListener("addpoint", function (e) {
//         rangingcaing(e)
//     });
//     tool.addEventListener("drawend", function (e) {
//         rangingcallback(e)
//     });
//     tool.open(); // 开启鼠标测距
// }
//
// //测距的回调函数
// function rangingcallback(e) {
//     for (var i = 0; i < e.overlays.length; i++) {
//         overlays.push(e.overlays[i]);
//
//     }
//     for (var i = 0; i < e.points.length; i++) {
//         overlays.push(e.points[i]);
//     }
//     // console.log(e);
// }
//
// //测距中的数据
// function rangingcaing(e) {
//     overlays.push(e.point.disLabel);
//     //  console.log(e);
// }
//
// //放大
// function amplification() {
//     window.locate.webMap.mapObject.zoomIn()
// }
//
// //缩小
// function narrow() {
//     window.locate.webMap.mapObject.zoomOut()
// }
//
// //打印地图
// function Print_map() {
//     var myDate = new Date(); //获取时间
//     var imagelayout = "PNG"//获取导出的图片格式
//     var targetDom = $("#bodyall_map");
//     html2canvas(targetDom, { //要截图的区域ID
//         background: "#fff",
//         onrendered: function (canvas) {
//             var Imgurl = canvas.toDataURL("image/png"); //画布转成图片
//             //以下代码为下载此图片功能
//             var triggerDownload =
//                 $("<a>").attr("href", Imgurl).attr("download", "" + myDate.getTime() + "." + imagelayout + "").appendTo("body");
//             triggerDownload[0].click();
//             triggerDownload.remove(); //下载图片到本地
//         }
//     });
// }
//
// //默认视野
// function default_view() {
//     var lng = 116.404;
//     var lat = 39.915;
//     var cent = 15;
//     $.ajax({
//         url: "/viewport/get", //请求地址
//         dataType: "json", //数据格式
//         type: "Get", //请求方式
//         success: function (data) {
//             lng = data.lng;
//             lat = data.alt;
//             cent = data.level
//         }
//
//     });
//     var point = new BMap.Point(lng, lat);
//     locate.webMap.mapObject.centerAndZoom(point, cent);
// }

//  刷新地图
function Refreshmap() {
    var pointfull = new BMap.Point("113.429136", "23.169302");

    for (var aaa in window.webMap.maps) {
        // console.log(   window.mapG[aaa])
        // console.log(    aaa)
        // console.log(  window.mapG)
        // console.log( aaa)
        var mmmp = window.mapG[aaa];


        Setcenter(pointfull, 4, mmmp);
    }


    // 第二个参数 是只刷数据不刷结构的 保留false否则为true
    query(false, false);
    treenodemove(true);
    //   mobileflashing(2);
    /*  locate.webMap.mapObject.clearOverlays();*/
    // locate.webMap.mapObject.reset();
}


//双击获取
function beforeDblClick(treeId, treeNode) {
    if (treeNode == null) {
        return;
    }
    if (clickFlag) {//取消上次延时未执行的方法
        clickFlag = clearTimeout(clickFlag);
    }
    // layer.msg("点名成功,该功能正在开发");
    return false;
}


//点击树节点是为true的时候
function beforeClickistrue(treeNode, loc) {
    // alert("ss")
    //  console.log(locate.markers[treeNode.dn]);
    //BMap.Convertor.translate(BDPoint,0,translateCallback); //真实经纬度转成百度坐标
    if (treeNode.type == 0) {  // console.log(treeNode);
        $("#libuoonton").remove();
        var treeid = treeNode.tId;
        var butong = `<li class='libuoonton' id='libuoonton'> 
<input type='button' onclick='dataTreedian(${treeNode.dn})' value='点名'/> ` +
            `<input type='button' value='资料'/>
<input type='button' value='跟踪' data='${treeNode.id}' class="placeInto" /> 
  </li>`;
        $("#" + treeid).append(butong);
        var id = treeNode.id;
        var iscunzai = Clickidstorage.indexOf(id);
        if (iscunzai == -1) {
            //不存在
            Clickidstorage.push(id);
        }
        if (!Arrayis(id)[1]) {
            adddatalist.push(returnteamName(fromdevices(alldevices[id])));
            treeids.push(id);
            var i = adddatalist.length - 1;
            var point = new BMap.Point(adddatalist[i].lng, adddatalist[i].lat);
            queryAddress(point, function (address) {
                adddatalist[i].locatName = address;
             //   var tabledata = {list: adddatalist};
              //  var html = template("tpl", tabledata);
              //  $('#content_data').html(html);
                tdmousever();
            }, loc);
        }
        if (refreshmapid[id] != undefined) {
            //  console.log(refreshmapid[id]);
            //   Repeat_annotation(id);

        }
        // chushudataadd(id,true,true);

    }
}


$("#dataTree").on("click", ".placeInto", function () {

    var cnm = $(this).attr("data");

    var mmmmm = `
<div><button data="ft_map" dataid=${cnm} class="spliterMapShit">置于分屏1</button></div>
<div><button data="sd_map" dataid=${cnm} class="spliterMapShit">置于分屏2</button></div>
<div><button data="td_map" dataid=${cnm} class="spliterMapShit">置于分屏3</button></div>
<div><button data="fh_map" dataid=${cnm} class="spliterMapShit" >置于分屏4</button></div>
`;
    $(this).webuiPopover({title: '选择分屏', content: mmmmm, closeable: true});

    $(document).off("click", ".spliterMapShit");
    $(document).on("click", ".spliterMapShit", function () {

        var id = $(this).attr("dataid");
        var ll = $(this).attr("data");

        var loc = window.mapG[ll];
        console.log(id)
        console.log(ll)
        console.log(loc.webMap.mapObject)

        qiehuan(id, loc);

    });


});

/*点击树节点获取对象*/
function beforeClick(treeId, treeNode, isCheck, loc) {
    if (clickFlag) {//取消上次延时未执行的方法
        clickFlag = clearTimeout(clickFlag);
    }
    clickFlag = setTimeout(function () {
        if (treeNode.pId == null) {
            var childrendata = treeNode.children == undefined ? undefined : typecar(treeNode.children);
            if (childrendata != undefined) {
                // treeNode.checked==true?adddataztree(childrendata):removecarteam(childrendata);
            }
        } else {
            var treeObj1 = $.fn.zTree.getZTreeObj("dataTree");
            var istrueorfalse = treeNode.checked;
            if (typeof isCheck == "boolean") {
                //如果是boolean 证明点击传过来的, 要相反
                for (var aaa in window.webMap.maps) {
                    var mmmp = window.mapG[aaa];
                    beforeClickistrue(treeNode, mmmp);
                }
            } else {
                if (istrueorfalse == true) {
                    istrueorfalse = false;
                    for (var aaa in window.webMap.maps) {
                        var mmmp = window.mapG[aaa];
                        removercar(treeNode, mmmp)
                    }
                } else {
                    istrueorfalse = true;
                    for (var aaa in window.webMap.maps) {
                        var mmmp = window.mapG[aaa];
                        beforeClickistrue(treeNode, mmmp);
                    }
                }
                treeObj1.checkNode(treeNode, !treeNode.checked, istrueorfalse);
            }
        }
    }, 200);
    clearTimeout();
}

//点击表格定位切换
function tables(id, e, loc) {
    // qiehuan(id, loc);
    $("#content_data tr").removeAttr("style");
    $(e).css("background", "#FFDECF");
}

/**
 * 切换图片定位
 */
function qiehuan(id, loc) {
    if(id==null) return ;
    var device = alldevices[id];

    convertor(device.lng, device.lat, function (point) {
        Setcenter(point, 14, loc);
    });

    var icon = new BMap.Icon('/static/image/pointimage/attentionicon.png', new BMap.Size(69, 70), {
        imageOffset: new BMap.Size(0, 0),
        infoWindowAnchor: new BMap.Size(0, 0),
        /* anchor: new BMap.Size(30, 30)*/
    });


    var labe5 = new BMap.Label("<div class='showlabelsp'> " +
        "<span  class='labelsp'  > " + Math.round(device.sp * 10) + "</span>" +
        " <span class='labelunit'   >km/h</span>" +
        " <span class='labelna'  >" + device.na + " </span> </div> ",
        {offset: new BMap.Size(10, 20)});
    labe5.setStyle({
        border: "none",
        height: "10px",
        lineHeight: "20px",
        margin: "0px 0px 0px 0px "
    });

    loc.webMap.mapObject.clearOverlays();
    // quertyicon(mmmp);
    loc.mmp = id;
    // for (var tree in list) {
    //     chushudataadd(list[tree].id, true, false, mmmp);
    //     Timerefreshid.push(list[tree].id);
    // }


    var point = new BMap.Point(device.lng, device.lat);

    var marker2 = new BMap.Marker(point, {
        icon: icon
    });

    marker2.setLabel(labe5);
    marker2.setRotation(device.d);
    marker2.setTitle("0");
    // 创建标注
    loc.webMap.mapObject.addOverlay(marker2);

    // for (var aaa in window.webMap.maps) {

        var mmmp = loc.webMap.mapObject;



        // mmmp.centerAndZoom(new BMap.Point(104.822339, 37.839088),10);

    // }

}

/*
 加载数据   传来的id ,判断是不是第一次穿来的数据 true是第一次 自己设置中心点
* */
function chushudataadd(id, change, Number, loc) {
    var device = alldevices[id];
    var geoc = new BMap.Geocoder();
    convertor(device.lng, device.lat, function (point) {
        device.lng = point.lng;
        device.lat = point.lat;
        loc.devicespoint.push(device);
        //  console.log(locate.devicespoint);

        queryAddress(point, function (address) {
            if (device != null) {
                //  console.log(address);
                var sContent = "<div style='margin:0px;padding:0px;border-top: 1px solid #c9c9c9;'>" +
                    "<div class='display-label'> <b>时间:</b> " + device.st + "</div>" +
                    "<div class='display-label'> <b>定位:</b>" + gpsDataParser.parseLocateType(device) + "[" + gpsDataParser.parseDirection(device) + "]</div>" +
                    "<div class='display-label'><b>状态:</b>" + gpsDataParser.parseAcc(device) + "</div>" +
                    "<div class='display-label'><b>里程:</b> " + device.m + "km</div>" +
                    "<div class='display-label'><b>速度:</b>" + device.sp + "km</div>" +
                    "<div style='margin:2px;'><b>位置:</b>" + address + "</div>" +
                    "<div class='mon_button' id='The_alarm'>" +
                    "<span>街景</span> " +
                    "<span>追踪</span><span>轨迹</span><span>详情</span><span > 告警</span><b></b>" +
                    "<span>指令</span><span>轨迹</span><span>详情</span><span>告警</span>" +
                    "</div>" +
                    "</div>";
                var data = [{
                    lng: point.lng,
                    lat: point.lat,
                    na: sContent
                }
                ]
                var datalocatName = {locatName: address, id: id};
                alldevicespidandlocatName.push(datalocatName);
                alldevices[id].locatName = address;
                showGpsinfo(data, device, change, loc);
            }
        }, loc);
    });
}

//根据点设置中心点
function Setcenter(point, Number, loc) {
    loc.webMap.mapObject.centerAndZoom(point, Number);
}

//处理位置是否移动过   listdataflashing   存储变动过的数据
function bit_Change_location(data) {
    listdataflashing = [];
    //如果大于0,表示有数据
    if (listdata.length > 0) {
        //处理每条数据的id是否匹配
        for (var i = 0; i < listdata[0].length; i++) {
            for (var j = 0; j < data.length; j++) {
                if (data[j].type == 0 && listdata[0][i].id == data[j].id) {
                    //匹配成功计算经纬度是否一致
                    if (listdata[0][i].lat == data[j].lat && listdata[0][i].lng == data[j].lng) {
                        //一致,没变,不用处理
                    } else {
                        listdataflashing.push(listdata[0][i]);
                    }
                }
            }
        }
    }
    listdata = [];
    listdata.push(data);
}

//计算位置是否移动过,来一闪一闪功能
function mobileflashing(c, loc) {
    var allOverlay = loc.webMap.mapObject.getOverlays();
    //获取地图上的点
    if (listpoint.length > 0) {
        for (k in listpoint) {
            loc.webMap.mapObject.removeOverlay(allOverlay[k]);
        }
    } else {
        for (var i = 0; i < allOverlay.length; i++) {
            for (j in listdataflashing) {
                if (allOverlay[i].getTitle() == listdataflashing[j].id) {
                    listpoint.push(i);
                    loc.webMap.mapObject.removeOverlay(allOverlay[i]);
                } else {
                    //更换图片
                    /* var icon = new BMap.Icon('/gps_web/resources/new/image/car.png', new BMap.Size(69,65), {
                         imageOffset: new BMap.Size(0, 0),
                         infoWindowAnchor: new BMap.Size(0, 0),
                         anchor: new BMap.Size(30, 30)
                     });
                     allOverlay[i].setIcon(icon);*/
                }
            }
        }
    }
    c = c - 1;
    setTimeout("showpoint(" + c + ")", 700);
}

//显示
function showpoint(c, loc) {
    if (c > 0) {
        for (var tree in listdataflashing) {
            chushudataadd(listdataflashing[tree].id, true, false, loc);
        }
        setTimeout("mobileflashing(" + c + ")", 700);
    } else {
        for (var tree in listdataflashing) {
            chushudataadd(listdataflashing[tree].id, true, false, loc);
        }
        clearTimeout();
    }
}

/*
 * 根据x和y,加地址显示弹窗
 * */
function showGpsinfo(data, device, change, loc) {
    Repeat_annotation(device.id, loc);
    var data_info = [];
    var title = "";
    title += '<div style="margin:0px;padding:0px">';
    title += '<div style="height:30px;margin:2px;">';
    title += '<b>';
    title += device.na;
    title += '</b>';
    title += '<div style="margin-left:15px;display:inline-block;">';
    title += '<div> </div>';
    /*<img  src="/image/xiaoxi.png" />*/
    title += '</div>';
    title += '</div>';
    title += '</div>';
    for (var i = 0; i < data.length; i++) {
        data_info.push(data[0].lng);
        data_info.push(data[0].lat);
        data_info.push(data[0].na);//内容
    }
    loc.opts = {
        width: 360,     // 信息窗口宽度
        height: 0,     // 信息窗口高度
        title: title, // 信息窗口标题
        enableMessage: true//设置允许信息窗发送短息
    };
    var icon;
    var label;
    var labe2;
    var iscunlie = Clickidstorage.indexOf(device.id);
    if (iscunlie == -1) {
        icon = new BMap.Icon('/static/image/pointimage/defaultsmine.png', new BMap.Size(20, 26), {
            imageOffset: new BMap.Size(0, 0),
            infoWindowAnchor: new BMap.Size(0, 0),
        });
        label = new BMap.Label("<span style='color:#0489D6;position: relative; right: 30px;'>" + device.na + "</span>", {offset: new BMap.Size(10, 20)});
        label.setStyle({
            border: "none",
            height: "10px",
            lineHeight: "20px",
            margin: "0px 0px 0px 10px "
        });
        loc.markers = new BMap.Marker(new BMap.Point(data_info[0], data_info[1]), {
            icon: icon
        });
        // locate.markers.setLabel(label);
    } else {
        //偏移值要处理一下设。
        icon = new BMap.Icon('/static/image/pointimage/attentionicon.png', new BMap.Size(69, 70), {
            imageOffset: new BMap.Size(0, 0),
            infoWindowAnchor: new BMap.Size(0, 0),
            /* anchor: new BMap.Size(30, 30)  iconbigshow  iconbigspload*/
        });
        var labe2 = new BMap.Label("<div class='showlabelsp'> " +
            "<span  class='labelsp'  > " + Math.round(device.sp * 10) + "</span>" +
            " <span class='labelunit'   >km/h</span>" +
            " <span class='labelna'  >" + device.na + " </span> </div> ",
            {offset: new BMap.Size(10, 20)});
        labe2.setStyle({
            border: "none",
            height: "10px",
            lineHeight: "20px",
            margin: "0px 0px 50px 5px "
        });

        loc.markers = new BMap.Marker(new BMap.Point(data_info[0], data_info[1]), {
            icon: icon
        });
        loc.markers.setLabel(labe2);
    }

    //注入标注

    //标注设置属性"<span>12km</span> <span style='position: relative;top: 25px; background: #fff;padding: 5px;'>"+
    //给每个标注的标题加入id
    loc.markers.setTitle(device.id);
    // console.log("方向"+device.d);
    var content = data_info[2];
    loc.markers.setRotation(device.d);
    //添加标注
    loc.webMap.mapObject.addOverlay(loc.markers);
    addClickHandler(content, loc.markers, loc.opts);
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
function addClickHandler(content, marker, title) {
    marker.addEventListener("click", function (e) {
            if (clickFlag) {//取消上次延时未执行的方法
                clickFlag = clearTimeout(clickFlag);
            }
            clickFlag = setTimeout(function () {
                openInfo(content, e, title, loc);
                //  console.log(e);
            }, 200)
        }
    );

    marker.addEventListener("dblclick", function (e) {
        if (clickFlag) {//取消上次延时未执行的方法
            clickFlag = clearTimeout(clickFlag);
        }
        icon = new BMap.Icon('/static/image/pointimage/ENSBattentionicon.png', new BMap.Size(69, 70), {
            imageOffset: new BMap.Size(0, 0),
            infoWindowAnchor: new BMap.Size(0, 0),
            /* anchor: new BMap.Size(30, 30)  iconbigshow  iconbigspload*/
        });


        var id = this.getTitle();
        var device = alldevices[id];
        var label = this.getLabel();
        var labe2 = new BMap.Label("<div class='showlabelsp'> " +
            "<span  class='labelsp'  > " + Math.round(device.sp * 10) + "</span>" +
            " <span class='labelunit'   >km/h</span>" +
            " <span class='labelna'  >" + device.na + " </span> </div> ",
            {offset: new BMap.Size(10, 20)});
        labe2.setStyle({
            border: "none",
            height: "10px",
            lineHeight: "20px",
            margin: "0px 0px 50px 5px "
        });

        if (label != null) {
            //  console.log(label);
        } else {
            this.setLabel(labe2);
        }
        if (directionid.length > 0) {
            removeiconbyid(directionid[0].id, directionid[0].icon, loc);
            directionid = [];
        }
        directionid.push({id: id, icon: this.getIcon()});
        this.setIcon(icon);
        this.setRotation(device.d);


    });
}

/**
 * 弹窗
 * @param content
 * @param e
 */
function openInfo(content, e, title, loc) {
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    //locate.map.centerAndZoom(point, 12);
    var infoWindow = new BMap.InfoWindow(content, title);  // 创建信息窗口对象
    loc.webMap.mapObject.openInfoWindow(infoWindow, point); //开启信息窗口
}

/**
 *  关闭面板的车辆信息
 * @type {Element}
 */
function penlarareacar() {
    $("#panelqucar").toggle(500);
}


//监听 标注
function markserver(loc) {
    loc.webMap.mapObject.setDefaultCursor("-webkit-zoom-in");
    loc.webMap.mapObject.addEventListener("click", showInfo);
}

//监听位置服务光标事件 上次图片等
// function showInfo(e) {
//
//     var html = '<br /><div class="layui-upload-list" style="width:40%; margin:0 auto;">'
//         + ' <input type="text" id="inputname" name="title" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input"> />'
//         + '<img class="layui-upload-img ui-border-radius" id="Picture" name="file"  src="/static/image/pointimage/alarmpoint.png">'
//         + '</div><br />'
//         + '<div style="width:40%;height:20px;margin:0 auto;text-align:center;">'
//         + '<button class="layui-btn layui-btn-primary" style="width:100%" id="SelectPicture">选择图标</button>'
//         + '<button class="layui-btn" style="width:100%;margin-top:10px;margin-left:0px;"  id="UpdatePictures">保存图片</button>'
//         + '<button class="ui-btn-lg ui-btn-primary" id="UploadPictures" style="display: none" >确定选择</button>'
//         + '</div>';
//
//     layer.open({
//         type: 1, //此处以iframe举例
//         title: ['选择上传图片'],//['标头'，'样式'];  title:false取消标题
//         content: html,//text内容或html内容
//         offset: 'auto',
//         area: ['500px', '260px'],
//         shade: 0.3,
//         maxmin: false,
//         //btn: ['确定选择', '取消'],
//         //yes: function (index1) {
//         //}
//     });
//
//
// //选择图片
//     upload.render({
//         elem: '#SelectPicture',
//         url: '/icon/poi/upload'
//         , data: {
//             name: function () {
//
//                 return $('#inputname').val();
//
//             },
//             lng: function () {
//                 return e.point.lng;
//             },
//             lat: function () {
//                 return e.point.lat;
//             }
//         }
//         , auto: false
//         , multiple: false
//         , accept: 'images'
//         , size: 5120
//         , bindAction: '#UploadPictures'
//         , before: function () {
//             //loadIndex = myloadAlertlayer1('上传中……');
//         }
//         , choose: function (obj) {
//             obj.preview(function (index, file, result) {
//                 //对上传失败的单个文件重新上传，一般在某个事件中使用
//                 //  console.log(file);
//
//                 $('#Picture').attr('src', result); //图片链接（base64）
//             });
//         }
//         , done: function (res, index, upload) {
//             // layer.close(loadIndex);
//             if (res.error == "ok...") {
//                 //询问框
//                 layer.open({
//                     title: '提示',
//                     content: '<span style="color:#38FB00">上传成功</span>',
//                     shade: 0.3,//遮罩
//                     area: ['270px', '180px'],
//
//                     fixed: true, //固定
//                     shadeClose: false//是否点击遮罩关闭
//                     , btn: ['关闭']
//                     , yes: function (index) {//确定
//                         layer.close(index);//隐藏
//                     }, end: function (index) {
//                         layer.close(index);
//                         layer.closeAll();
//
//                     }
//                 });
//             }
//             quertyicon();
//             //location.reload();
//             // alert(res)
//         }, error: function (index, upload) {//上传图片失败
//             // myAlertlayer1('提示', '上传图片失败！');
//         }
//     });
//     //保存图片
//     $('#UpdatePictures').click(function () {
//         var src = $('#Picture').attr('src');
//         if (src != "" && src != null) {
//             $('#UploadPictures').click();
//         } else {
//             myAlertlayer1('提示', '未检测到有图片');
//         }
//     });
//     var point = new BMap.Point(e.point.lng, e.point.lat);
//     var marker = new BMap.Marker(point);// 创建标注
//     locate.webMap.mapObject.addOverlay(marker);             // 将标注添加到地图中
//     locate.webMap.mapObject.setDefaultCursor("pointer");
//     locate.webMap.mapObject.removeEventListener("click", showInfo);
// }
// //
// // //定义临时数据
// // function falsedata() {
// //     var data = [{
// //         "dn": "12123212321",
// //         "na": "京B37A01",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 1,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067bc7b03682842ea3c8b",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "120187322661",
// //         "na": "京B37A95",
// //         "gt": "2018-04-18 01:26:41",
// //         "st": "2018-04-18 09:26:42",
// //         "val": 1,
// //         "lng": 113.429546,
// //         "lat": 23.168944,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 163,
// //         "a": 0,
// //         "s": 6,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 24,
// //         "sat": 0,
// //         "id": "5aab54147b0368691e55c68e",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "10189415505",
// //         "na": "京B37A93",
// //         "gt": "2018-04-16 19:12:52",
// //         "st": "2018-04-16 19:13:44",
// //         "val": 1,
// //         "lng": 112.50104,
// //         "lat": 37.80122,
// //         "alt": 0,
// //         "sp": 7.0,
// //         "d": 257,
// //         "a": 0,
// //         "s": 786435,
// //         "o": 0,
// //         "m": 749.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 8,
// //         "sat": 1,
// //         "id": "5aa741df7b03680b92edf012",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": " ",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "12345678911",
// //         "na": "京B37A02",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5abc94297b03682842ea3567",
// //         "pid": "5abc94017b03682842ea3566",
// //         "type": 0,
// //         "icon": " ",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "888888888888888",
// //         "na": "京B37A03",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067e97b03682842ea3c8f",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": " ",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "3333333333333",
// //         "na": "京B37A04",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067ad7b03682842ea3c8a",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "1111111111111",
// //         "na": "京B37A05",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067947b03682842ea3c88",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "5555555555555",
// //         "na": "京B37A06",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067c77b03682842ea3c8c",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "22222222222",
// //         "na": "京B37A06",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067a17b03682842ea3c89",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "13311012700",
// //         "na": "京B37A94",
// //         "gt": "2018-03-23 09:35:38",
// //         "st": "2018-03-23 09:35:39",
// //         "val": 1,
// //         "lng": 113.429116,
// //         "lat": 23.169497,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 59,
// //         "a": 0,
// //         "s": 3,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5a74182b3d769a7dcc878d5d",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "99999999999999",
// //         "na": "京B37A07",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067f57b03682842ea3c90",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "6666666666666",
// //         "na": "京B37A09",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067d17b03682842ea3c8d",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": "777777777777",
// //         "na": "京B37A08",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5ad067de7b03682842ea3c8e",
// //         "pid": "5a72abd83d769a75b6309dee",
// //         "type": 0,
// //         "icon": "",
// //         "marker": "00.png",
// //         "rotate": 1
// //     }, {
// //         "dn": null,
// //         "na": "第一车队",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5a72abd83d769a75b6309dee",
// //         "pid": "5a72c8733d769a75b6309ff0",
// //         "type": 1,
// //         "icon": "",
// //         "marker": null,
// //         "rotate": 0
// //     }, {
// //         "dn": null,
// //         "na": "锐讯易通",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5a72ab073d769a75b6309dec",
// //         "pid": "5a72c8733d769a75b6309ff0",
// //         "type": 2,
// //         "icon": "",
// //         "marker": null,
// //         "rotate": 0
// //     }, {
// //         "dn": null,
// //         "na": "测试公司",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5abc8e257b03682842ea33e9",
// //         "pid": "5a72c8733d769a75b6309ff0",
// //         "type": 2,
// //         "icon": "",
// //         "marker": null,
// //         "rotate": 0
// //     }, {
// //         "dn": null,
// //         "na": "测试公司的测试车队",
// //         "gt": null,
// //         "st": null,
// //         "val": 0,
// //         "lng": 0.0,
// //         "lat": 0.0,
// //         "alt": 0,
// //         "sp": 0.0,
// //         "d": 0,
// //         "a": 0,
// //         "s": 0,
// //         "o": 0,
// //         "m": 0.0,
// //         "oil": 0.0,
// //         "vss": 0.0,
// //         "ovt": 0,
// //         "oid": 0,
// //         "iot": 0,
// //         "iid": 0,
// //         "iof": 0,
// //         "rid": 0,
// //         "rt": 0,
// //         "rf": 0,
// //         "aid": 0,
// //         "exs": 0,
// //         "ios": 0,
// //         "ad0": 0,
// //         "ad1": 0,
// //         "net": 0,
// //         "sat": 0,
// //         "id": "5abc94017b03682842ea3566",
// //         "pid": "5abc8e257b03682842ea33e9",
// //         "type": 1,
// //         "icon": "",
// //         "marker": null,
// //         "rotate": 0
// //     }];
// //     return data;
// //     //  console.log(data);
// // }

//地图列表
function maplistdata() {
    var list = [];
    $.ajax({
        url: "/dictionary/list", //请求地址
        dataType: "json", //数据格式
        data: {
            kind: 5,
            grid: true,
        },
        type: "post", //请求方式
        async: false, //是否异步请求
        success: function (data) {

            for (i in data.rows) {
                list.push(mapTransformdata(data.rows[i]));
            }
            //  console.log(data);
            $.fn.zTree.init($("#dataTreedis"), setting2, list);
            /* var htmlmap = {list:data.rows}
            $("#maplist_tpl").html(htmlmap);*/
        }
    })
}

//转换数据地图列表的
function mapTransformdata(datas) {
    var pids = datas.pid;
    if (pids == undefined) {
        pids = 0;
    }
    var data = new Object();
    data.pid = pids;
    data.name = datas.name;
    data.id = datas.id;
    data.open = false;
    return data;
}

//信息框

//初始化标注
function quertyicon(loc) {
    var imagepath = "/stat";
    // var datalist=[];
    $.ajax({
        url: "/icon/poi/query", //请求地址*/
        dataType: "json", //数据格式
        type: "GET", //请求方式
        async: false, //是否异步请求
        success: function (data) {
            var allimage = data.msg;
            if (data.rows.length > 0) {
                for (var datas in data.rows) {
                    // markloadlocet(data.rows[datas].lng, data.rows[datas].alt, data.rows[datas].name, true, data.rows[datas].showName, loc);
                    //datalist.push(data.rows[datas].showName);
                }
                queryicondata = data.rows;
              //  var tabledata = {list: data.rows};
              //  var html = template("tpllocat", tabledata);
              //  $('#locat_Data').html(html);
            }
        }
    })
}

/*
  标注
 参数:经纬度,图片,是否启用纠偏
 */
function markloadlocet(lnga, lata, image, isrectifying, showName, loc) {
    var lng = lnga;
    var lat = lata;
    if (isrectifying == true) {
        var point = new BMap.Point(lng, lat);
        var myIcon = new BMap.Icon("/static/icons/" + image, new BMap.Size(20, 20));
        var marker2 = new BMap.Marker(point, {
            icon: myIcon
        });
        var labe3 = new BMap.Label("<span style='color:#0489D6;position: relative; right: 20px;'>" + showName + "</span>", {offset: new BMap.Size(10, 20)});
        labe3.setStyle({
            border: "none",
            height: "10px",
            lineHeight: "20px",
            margin: "0px 0px 0px 5px "
        });
        marker2.setLabel(labe3);
        marker2.setTitle("0");
        // 创建标注
        loc.webMap.mapObject.addOverlay(marker2);


    }
}

/*
* 树节点移入展示按钮
* */
function treenodemove(statsdemove) {
    var butong = "<li class='libuoonton' id='libuoonton'> <input type='button' onclick='dataTreedian()' value='点名'></input> " +
        "<input type='button' value='资料'></input>  </li>";
    // $(".level0").
    $(".ztree li").on("mouseover", function (e) {
        e.stopPropagation();
        //  stopBubble(e);
        //$("#libuoonton").remove();
        // $(".libuoonton").remove();
        $(this).parent().removeAttr("style");
        // console.log(this);
        var isName = $(this).find("a");
        isName.length == count ? "" : e.stopPropagation();
        for (var i = 0; i < isName.length; i++) {
            //  console.log(isName[0].childNodes[1]);
            if (isName[i].childNodes.length > 3) {
                if (isName[i].childNodes[2].className == "diy divnema") {
                    $(isName[i]).parent().css("background", "#");
                    return;
                    // $(isName[i]).parent().append(butong);
                }
            }
        }
    });
    $(".ztree li").on("mouseleave", function (e) {
        e.stopPropagation();
        $(this).removeAttr("style");
        $(this).find("li").removeAttr("style");
        //    $("#libuoonton").remove();
        //  $(".libuoonton").remove();
    })
    /*  $(".ztree li").hover(function (e) {
          $("#libuoonton").remove();
          $(".libuoonton").remove();
          // console.log(this);
          var isName =$(this).find("a");
          //  console.log(isName);1
          if(statsdemove==true) {
              if(isName.length==count ){

              }else{

                  for(var i=0;i<isName.length;i++){
                      //  console.log(isName[0].childNodes[1]);
                      if(isName[i].childNodes[1].className=="diy divnema"){
                          $(isName[i]).parent().append(butong);
                          return;
                          //$(isName[i]).parent().css("height","50px")
                      }
                  }
              }}else {
              if(isName.length==count ||isName.length== suncount||isName.length==suncount+1){

              }else{

                  for(var i=0;i<isName.length;i++){
                      //  console.log(isName[0].childNodes[1]);
                      if(isName[i].childNodes[1].className=="diy divnema"){
                          $(isName[i]).parent().append(butong);
                          return;
                          //$(isName[i]).parent().css("height","50px")
                      }
                  }

              }
          }
      });*/
}

//判断一个元素是否存在数组里
function Arrayis(id) {
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
}

//侧面,隐藏,显示。
function hidden_bottom() {
    $(".cs_bottom").toggle(500);
    $(".cs_middle").toggle(500);
    var current= $(".car-info").get(0).offsetHeight;
    console.log($(".cs_top span:nth-child(3)").text().trim());
    $(".cs_top span:nth-child(3)").text().trim()=="隐藏"?
        ($(".cs_top span:nth-child(3)").addClass("hide_it").text("显示"), $(".car-info").css("height",(current+50)+"px")):
        ($(".cs_top span:nth-child(3)").text("隐藏").removeClass("hide_it"),$(".car-info").css("height",(current-50)+"px"));

}


//勾选树节点
function zTreeOnCheck(event, treeId, treeNode, loc) {
    var zTree = $.fn.zTree.getZTreeObj("dataTree");
    var id = treeNode.id;
    var childrendata = treeNode.children == undefined ? undefined : typecar(treeNode.children);

    if (childrendata != undefined) {
        treeNode.checked == true ? adddataztree(childrendata) : removecarteam(childrendata);
    } else {
        //被勾选到

        for (var aaa in window.webMap.maps) {

            var mmmp = window.mapG[aaa];

            treeNode.checked == true ? beforeClick(treeId, treeNode, true, mmmp) : removercar(treeNode, mmmp);


        }

    }
    // alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
};

//勾选某车队
function adddataztree(childrendata) {
    for (var i = 0; i < childrendata.length; i++) {
        var dataxhuanuhan = childrendata[i].id;
        if (!Arrayis(dataxhuanuhan)[1]) {
            adddatalist.push(returnteamName(childrendata[i]));
            treeids.push(dataxhuanuhan);
        }
    }
   // var tabledata = {list: adddatalist};
   // var html = template("tpl", tabledata);
   // $('#content_data').html(html);
}

//移除关注的某车队
function removecarteam(childrendata) {
    for (var i = 0; i < childrendata.length; i++) {
        var listis = Arrayis(childrendata[i].id);
        //存在
        if (listis[1]) {
            //把存在的移除掉
            //treeids.splice(listis[0]);
            //adddatalist.splice(jQuery.inArray(childrendata[i],adddatalist),1);
            /*    treeids.splice(jQuery.inArray(childrendata[i].id,treeids),listis[0]);*/
            removercarliang(childrendata[i].id);
            removerarr(treeids, childrendata[i].id);
        }
    }
   // var tabledata = {list: adddatalist};
  //  var html = template("tpl", tabledata);
   // $('#content_data').html(html);
}

//移除某个车辆
function removercar(treeNode, loc) {
    //弹窗位置变化bug
    var setStyle = {
        border: "none",
        height: "10px",
        lineHeight: "20px",
        margin: "0px 0px 0px 10px "
    };

    var icon3 = new BMap.Icon('/static/image/pointimage/defaultsmine.png', new BMap.Size(69, 65), {
        imageOffset: new BMap.Size(0, 0),
        infoWindowAnchor: new BMap.Size(0, 0),
        /*anchor: new BMap.Size(30, 30)*/
    });
    var allOverlay = loc.webMap.mapObject.getOverlays();
    if (treeids.length > 0) {
        for (i in allOverlay) {
            for (k  in treeids) {
                if (allOverlay[i].getTitle() == treeNode.id) {
                    var label = allOverlay[i].getLabel();
                    if (label != null) {
                        label.setContent("");//设置标签内容为空
                        label.setStyle = setStyle;
                    }
                    allOverlay[i].setIcon(icon3);
                }
            }
        }
    }
    var listis = Arrayis(treeNode.id);
    if (listis[1]) {
        removercarliang(treeNode.id);
        removerarr(treeids, treeNode.id);
    }
    //var tabledata = {list: adddatalist};
   // var html = template("tpl", tabledata);
   // $('#content_data').html(html);
}

//在线车辆
function Onlinecar() {
    bitopen = true;
    //目前假数据,真数据需要修改
    // var data =falsedata();
    var data = datasour;
    $("#dataTree").empty();
    var listdatacer = [];
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        if (item.type === 0) {
            //console.log(item.id);
            if (item.o == 1) {
                listdatacer.push(fromdevices(item));
            }
        } else {
            listdatacer.push(fromfleet(item));
        }
    }
    Serialnumber = 0;
    $.fn.zTree.init($("#dataTree"), setting, listdatacer);
    treenodemove(true);
}

//离线车辆
function offlinecar() {
    bitopen = true;
    $("#dataTree").empty();
    //var data =falsedata();
    var data = datasour;
    var listdatacer = [];
    suncount = 0;
    treecount = 0;
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        if (item.type === 0) {
            //console.log(item.id);
            if (item.o != 1) {
                suncount = suncount + 1;
                listdatacer.push(fromdevices(item));
            }
        } else {
            treecount = treecount + 1;
            listdatacer.push(fromfleet(item));
        }
    }
    count = listdatacer.length;
    Serialnumber = 0;
    $.fn.zTree.init($("#dataTree"), setting, listdatacer);
    treenodemove(false);
}

//总车辆
function countcar() {
    bitopen = true;
    $("#dataTree").empty();
    // var data =falsedata();
    var data = datasour;
    var listdatacer = [];
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        if (item.type === 0) {
            //console.log(item.id)
            listdatacer.push(fromdevices(item));
        } else {
            listdatacer.push(fromfleet(item));
        }
    }
    Serialnumber = 0;
    $.fn.zTree.init($("#dataTree"), setting, listdatacer);
    treenodemove(true);
}

//转换刚加载数据
function fromdevices(data) {
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
    datastart.sp = Math.round(data.sp * 10);
    datastart.st = data.st;
    datastart.type = data.type;
    datastart.val = data.val;
    datastart.vss = data.vss;
    datastart.team = 0;
    datastart.locatName = data.locatName;
    return datastart;

}

//移除存储id
function removerarr(arr, val) {
    var index = arr.indexOf(val);
    if (index > -1) {
        arr.splice(index, 1);
    }
}

//移除车辆
function removercarliang(id) {
    for (var i = 0; i < adddatalist.length; i++) {
        if (adddatalist[i].id == id) {
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
    for (var i = 0; i < data.length; i++) {
        if (data[i].type == 0) {
            return data;
        } else {
        }
        return typecar(data[i].children);
        if (data[i].type == 0) {
            return data;
        } else {
        }
    }
}


//点击位置服务的名称
function locatpoint(id, lng, lat, loc) {
    var point = new BMap.Point(lng, lat);
    Setcenter(point, 15, loc);
}

//根据车往上找车队返回他的数据带着上级,参数车,整个数据
function screshcar(car, data) {
    var countlist = [];
    var indexofid = [];
    for (var i = 0; i < car.length; i++) {
        //加载所有的车
        countlist.push(fromdevices(car[i]));
        for (var j = 0; j < data.length; j++) {
            //这个车的车队
            if (car[i].pid == data[j].id) {
                if (countlist.length > 0) {
                    //如果这个车的车队已经出现过.
                    var isshow = indexofid.indexOf(data[j].id);
                    if (isshow == -1) {
                        indexofid.push(data[j].id);
                        countlist.push(fromfleet(data[j]));
                    }
                    // console.log(indexofid);
                }
            }
        }

    }
    return countlist;
}

//根据车转换数据返回车与方向
function returnteamName(data) {
    var name = [];
    var listdata = fleets[data.pId];
    data.team = listdata.na;
    data.d = gpsDataParser.parseDirection(data);
    return data;
}

//初始化搜索
function textscresh() {
    $("#textscresh").on("keyup",function (event) {
        var len = datasour.length;
        var arr = [];
        var Name = $("#textscresh").val();
        if(Name==""){
            $("#dataTree").empty();
            Serialnumber=0;
            bitopen=true;
            for (var i = 0; i < datasour.length; i++) {
                if(datasour[i].type==0){
                    arr.push(fromdevices(datasour[i]));
                }else{
                    arr.push(fromfleet(datasour[i]));
                }
            }
            $.fn.zTree.init($("#dataTree"), setting, arr);
            treenodemove(true);
            return ;
        }
        var reg = new RegExp(Name);
        for(var i=0;i<len;i++){
            //如果字符串中不包含目标字符会返回-1
            if(datasour[i].na.match(reg)){
                if(datasour[i].type==0){
                    arr.push(datasour[i] );
                }
            }
        }

        var arrs=   screshcar(arr,datasour);
        if(arrs.length>0){
            $("#dataTree").empty();
            Serialnumber=0;
            bitopen=true;
            $.fn.zTree.init($("#dataTree"), setting, arrs);
            treenodemove(true);
        }
        //  $.fn.zTree.init($("#dataTree"), setting,listdatacer);
    });
    $("#textscreshcar").on("keyup", function (event) {

        var Name = $("#textscreshcar").val();
        if (Name == "" || Name.length == 0) {
            $("#finishingTask").bootstrapTable("load", insides);
        } else {
            var reg = new RegExp(Name);
            //全部
            var listid = [];
            var listdata = [];
            var trcount = $("#finishingTask tbody").find("tr");
            for (var i = 0; i < trcount.length; i++) {
                if ($(trcount[i].children[1]).text().trim().match(reg)) {

                    listid.push(alldevices[$(trcount[i].children[0]).text()]);
                }
            }
            $("#finishingTask").bootstrapTable("load", listid);

        }
    });
    $("#textscreshcaronline").on("keyup", function (event) {
        //在线
        var Name = $("#textscreshcaronline").val();
        if (Name == "" || Name.length == 0) {
            $("#finishingTaskonline").bootstrapTable("load", finishingTaskonlinedata);
        } else {
            var reg = new RegExp(Name);
            //全部
            var listid = [];
            var listdata = [];
            var trcount = $("#finishingTaskonline tbody").find("tr");
            for (var i = 0; i < trcount.length; i++) {
                if ($(trcount[i].children[1]).text().trim().match(reg)) {

                    listid.push(alldevices[$(trcount[i].children[0]).text()]);
                }
            }
            $("#finishingTaskonline").bootstrapTable("load", listid);

        }

    });
    $("#textscreshcaroffline").on("keyup", function (event) {
        //离线

        var Name = $("#textscreshcaroffline").val();
        if (Name == "" || Name.length == 0) {
            $("#finishingTaskoffline").bootstrapTable("load", finishingTaskofflinedata);
        } else {
            var reg = new RegExp(Name);
            //全部
            var listid = [];
            var listdata = [];
            var trcount = $("#finishingTaskoffline tbody").find("tr");
            for (var i = 0; i < trcount.length; i++) {
                if ($(trcount[i].children[1]).text().trim().match(reg)) {

                    listid.push(alldevices[$(trcount[i].children[0]).text()]);
                }
            }
            $("#finishingTaskoffline").bootstrapTable("load", listid);

        }

    });
    $("#textscreshlocat").on("keyup", function (event) {
        //离线

        var Name = $("#textscreshlocat").val();
        if (Name == "" || Name.length == 0) {

           // var tabledata = {list: queryicondata};
          //  var html = template("tpllocat", tabledata);
           // $('#locat_Data').html(html);
        } else {
            var reg = new RegExp(Name);
            //全部
            var listid = [];
            var listdata = [];
            var trcount = $("#locat_Data").find("tr");
            for (var i = 0; i < trcount.length; i++) {
                if ($(trcount[i].children[1]).text().trim().match(reg)) {
                    var arrlist = trcount[i].attributes[0].nodeValue;
                    arrlist = arrlist.substring(12);
                    arrlist = arrlist.replace(")", "");
                    arrlist = arrlist.split(",");
                    /* for(var i =0;i<queryicondata.length;i++){
                         if( queryicondata[i].showName.trim() ==(trcount[i].children[1]).text().trim()){}

                     }*/
                    var Name = {
                        showName: $(trcount[i].children[1]).text().trim(),
                        id: arrlist[0],
                        lng: arrlist[1],
                        alt: arrlist[2]
                    };
                    listid.push(Name);
                }
            }
          //  var tabledata = {list: listid};
         //   var html = template("tpllocat", tabledata);
          //  $('#locat_Data').html(html);
        }
    });
}

//单纯分钟和秒倒计时
function resetTime(time) {
    var t = time;

    function countDown() {
        t--;
        //  console.log( t+"秒");
        $("#countdowntime").html(t);

        if (t == 0) {
            t = time;
            query(false, false);
        }
    }

    timerresetTime = setInterval(countDown, 1000);
}

//
// //地图导航栏收缩
// function mapnavigationshrinkage() {
//     //last-child最后一个元素
//     $(".nav_ul ul li:not(:last-child)").toggle(500);
//     if ($(".nav_ul").hasClass("val_ul_hidden")) {
//         $(".nav_ul").removeClass("val_ul_hidden");
//         $("#map_shrinkage").attr('src', "/static/image/pointimage/xiaohungdeng.png");
//         console.log("1");
//     } else {
//         $("#map_shrinkage").attr('src', "/static/image/pointimage/t2_1.png");
//         $(".nav_ul").addClass("val_ul_hidden");
//         console.log("2");
//     }
// }

//车辆列表移入地理位置的时候
function tdmousever() {
    $("#content_data tr td:last-child").mousemove(function(e){
        $(this).children(".locatNamejingwei").css("display", "none");
        $(this).children(".locatName").css("display", "block");

    })

    $("#content_data tr td:last-child").mouseout(function(e){
        $(this).children(".locatNamejingwei").css("display", "block");
        $(this).children(".locatName").css("display", "none");
    })

}

// //选择计时器的时间
// function choosetime() {
//     clearTimeout(timerresetTime);
//     var opt = $("#status").val();
//     var optint = Number(opt);
//     resetTime(optint);
// }

//递归树形是否为打开状态
function typeisopen(data) {
    for(var i=0;i<data.length;i++){
        if(data[i].type==0){
            isopenbyid.push({id:data[i].id,isopen:data[i].checked});
        }else{
            isopenbyid.push({id:data[i].id,isopen:data[i].open});
            //看看有没有子节点   已经有子节点
            return    data.children==undefined?"":typeisopen(data.children);
        }
    }
}

//保存树形是否存在打开状态
function Set_whether_open(item) {

    var treeObj = $.fn.zTree.getZTreeObj("dataTree");
    var nodes = treeObj.getNodes();
    if (nodes.length>0) {
        //    nodes[0].name = "test";
        // treeObj.updateNode(item);
    }
    treeObj.refresh();
    isopenbyid =[];
    var treeObj1 = $.fn.zTree.getZTreeObj("dataTree");
    var sNodes = treeObj1.getNodes();
    /*console.log(sNodes[0].getCheckStatus());
    console.log(sNodes[0].getNextNode());
    console.log(sNodes[0].getParentNode());
    console.log(sNodes[0].getPreNode());*/
    var nodes = treeObj.getSelectedNodes();
    for(n in nodes){
        isopenbyid.push({id:nodes[n].id,isopen:nodes[n].checked});
    }
    //这里只循环顶级节点 ,那么顶级节点一定是父子点
    for(var i = 0 ; i<sNodes.length;i++){
        var data  =sNodes[i];//得到一个顶级父节点
        isopenbyid.push({id:data.id,isopen:data.open});
        console.log(data.children);
        //看看有没有子节点   已经有子节点

        data.children==undefined?"":typeisopen(data.children);
    }

    if(isopenbyid.length>0){
        for(i in isopenbyid){
            for( j in item){
                if(item[j].id == isopenbyid[i].id){
                    if(item[j].type==0){
                        item[j].checked = isopenbyid[i].isopen;
                        //子节点是否选中
                    }else{
                        //父节点是否打开
                        item[j].open = isopenbyid[i].isopen;
                    }
                }
            }
        }
    }

    return item;
}

// //打开状态下,赋值
// function isopen(data) {
//
//
// }

//去除重复标注
function Repeat_annotation(id, loc) {
    var allOverlay = loc.webMap.mapObject.getOverlays();
    //  console.log(allOverlay);
    for (var i = 0; i < allOverlay.length; i++) {
        if (allOverlay[i].point != null) {

            // console.log(allOverlay[i].getTitle());
            //判断地图里是否有重复的id
            if (allOverlay[i].getTitle() == id) {
                //   console.log("重复了");
                loc.webMap.mapObject.removeOverlay(allOverlay[i]);
            }
        }
        //if(allOverlay[i].getPosition().y==data.lng)
    }
}

//只有一个东南西北的图标
function removeiconbyid(id, icon, loc) {

    var allOverlay = loc.webMap.mapObject.getOverlays();
    for (var i = 0; i < allOverlay.length; i++) {
        if (allOverlay[i].getTitle() == id) {
            allOverlay[i].setIcon(icon);
            var label = allOverlay[i].getLabel();
            if (label != null) {
                label.setContent("");
            } else {

            }
        }
    }

}

//更新树形数据
// function updateshuxing(item) {
//     var treeObj = $.fn.zTree.getZTreeObj("dataTree");
//     var nodes = treeObj.getNodes();
//     console.log(nodes);
//
//     function ischildren() {
//         for (var i in nodes) {
//             if (nodes[i].type == 0) {
//                 for (var k in item) {
//                     if (item[k].type == 0 && nodes[i].id == item[k].id) {
//                         nodes[i].sp = item[k].sp;
//                         nodes[i].m = item[k].m;
//                         nodes[i].na = item[k].na;
//                     }
//                 }
//             } else {
//                 if (nodes[i].children.length > 0) {
//
//                     ischildren();
//                 }
//             }
//         }
//     }
//
//     ischildren();
//     console.log(nodes);
//     treeObj.updateNode(nodes);
// }

//更新节点赋值
function fuck(list) {
    var treeObj = $.fn.zTree.getZTreeObj("dataTree");
    var nodes = treeObj.getNodes();
    /* $.each(list, function (index, nodes) {
         console.log(nodes);

         if (nodes.type != 0) {
             fuck(nodes.children);

         }else{
                      nodes.sp = 100;
         }
     });*/
    nodes[0].children[0].name = "aaa";
    //   console.log(nodes);
    treeObj.updateNode(nodes[0]);
}


function cloneObj(obj) {
    var newObj = {};
    if (obj instanceof Array) {
        newObj = [];
    }
    for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。
        newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
    }
    return newObj;
};