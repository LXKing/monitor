/**
 *
 */

var devices = {}

$(function () {

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
    });

});


function sousuo() {
    if ($("#imgclass").hasClass("glyphicon-chevron-down")) {
        $(".bottomb").hide(500);
        $(".bottomhead").hide(500);
        $("#Rightbody_centen ").attr("class", "Rightbody_centenguodu");
        $("#bottomRigth ").attr("class", "bottomRigthguodu");
        $("#imgclass").removeClass("glyphicon-chevron-down");
        $("#imgclass").addClass("glyphicon-chevron-up");
    } else {
        $("#imgclass").removeClass("glyphicon-chevron-up");
        $("#Rightbody_centen").attr("class", "Rightbody_centen");
        $("#bottomRigth").attr("class", "bottomRigth");
        $("#imgclass").addClass("glyphicon-chevron-down");
        $(".bottomb").show(500);
        $(".bottomhead").show(500);
    }


}

//测试table表格绑定数据
function bangding() {
    var data = [{id: "1", name: "我", city: "广州", youbian: "123"},
        {id: "2", name: "你", city: "深圳", youbian: "456"},
        {id: "3", name: "他", city: "湛江", youbian: "789"},
        {id: "4", name: "我", city: "广州", youbian: "123"},
        {id: "5", name: "你", city: "深圳", youbian: "456"},
        {id: "6", name: "他", city: "湛江", youbian: "789"},
        {id: "7", name: "我", city: "广州", youbian: "123"},
        {id: "8", name: "你", city: "深圳", youbian: "456"},
        {id: "9", name: "他", city: "湛江", youbian: "789"},
        {id: "10", name: "我", city: "广州", youbian: "123"},
        {id: "11", name: "你", city: "深圳", youbian: "456"},
        {id: "12", name: "他", city: "湛江", youbian: "789"},
    ]
    $("#finishingTask").bootstrapTable("load", data);
}

/*   通过uro                                           返回的json数据
 *  $.getJSON("/HYZY/Resource/EnshrineBoundEmp", function (data) {
            var cells = 6,
                rows = data.length,
                i, j, row,
                columns = [],
                mydata = [];
            columns.push({
                field: 'field' + 0,
                title: '序号'
            });
            columns.push({
                field: 'field' + 1,
                title: '企业名称'
            });
            columns.push({
                field: 'field' + 2,
                title: '规模'
            });
            columns.push({
                field: 'field' + 3,
                title: '公司性质'
            });
            columns.push({
                field: 'field' + 4,
                title: '主要行业'
            });
            columns.push({
                field: 'field' + 5,
                title: '公司地址'
            });
            columns.push({
                field: 'field' + 6,
                title: '电子邮箱'
            });
            columns.push({
                field: 'field' + 7,
                title: '选择'
            });
            for (i = 0; i < rows; i++) {
                row = {};
                for (j = 0; j < cells; j++) {
                    row['field' + 0] = i + 1;
                    row['field' + 1] = "<a href='" + data[i].EnterpriseLink + "'>" + data[i].Enterprise + "</a>";
                    row['field' + 2] = data[i].EnterpriseScale;
                    row['field' + 3] = data[i].EnterpriseProperty;
                    row['field' + 4] = data[i].IndustryMC;
                    row['field' + 5] = data[i].Location;
                    row['field' + 6] = data[i].EmailAddress;
                    row['field' + 7] = "<div class='btn-group' style='margin-top:-5px'><button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' aria-expanded='false'>菜单<span class='caret'></span></button>" + "<ul class='dropdown-menu' role='menu'><li><a data-toggle='modal' data-target='.bs-example-modal-lg' onclick='CollectTalentsMessage(" + data[i].EnterpriseID + ")'>公司介绍</a></li><li><a onclick='EnshrineRemove(" + data[i].RecruitingEnterpriseD + ");self.location.reload();'>删除</a></li><li><a>取消</a></li></ul>" + "</div>";
                }
                mydata.push(row);
            }                           销毁表格
            $('#CostAccountantTable').bootstrapTable('destroy').bootstrapTable({
               动态绑定头
                columns: columns,
                绑定数据
                data: mydata
            });
        });
 *
 *
 * */

function soushuoleft() {
    if ($("#imgeleft").hasClass("glyphicon-chevron-left")) {
        $("#leftbody").hide(500);
        $(".sousuotubiao").attr("class", "sousuotubiaoguodu");
        $(".Rightbody").attr("style", "width:99%");
        $("#imgeleft").removeClass("glyphicon-chevron-left");
        $("#imgeleft").addClass("glyphicon-chevron-right");
    } else {

        $("#leftbody").show(500);
        $(".sousuotubiaoguodu").attr("class", "sousuotubiao");
        $(".Rightbody").attr("style", "width:80%");
        $("#imgeleft").addClass("glyphicon-chevron-left");
        $("#imgeleft").removeClass("glyphicon-chevron-right");
    }


}

var zTreeNodes;
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
        beforeClick: beforeClick
    }
};

/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
    if (treeNode.pId == null) {


    } else {
        var spaceWidth = 15;
        var liObj = $("#" + treeNode.tId);
        var aObj = $("#" + treeNode.tId + "_a");
        var switchObj = $("#" + treeNode.tId + "_switch");
        var icoObj = $("#" + treeNode.tId + "_ico");
        var spanObj = $("#" + treeNode.tId + "_span");
        aObj.attr('title', '');
        aObj.append('<div class="diy swich"></div>');
        var div = $(liObj).find('div').eq(0);
        switchObj.remove();
        spanObj.remove();
        icoObj.remove();
        div.append(switchObj);
        div.append(spanObj);
        var spaceStr = "<span style='width:" + (spaceWidth * treeNode.level) + "px'><img src='/gps_web/resources/new/image/lixiandian.png'/></span>";
        switchObj.before(spaceStr);
        var editStr = '';
        editStr += '<div class="diy"> ' + (treeNode.CONTACT_USER == null ? '&nbsp;' : treeNode.CONTACT_USER) + '</div>';
        var corpCat = '<div title="' + treeNode.CORP_CAT + '">' + treeNode.CORP_CAT + '</div>';
        editStr += '<div class="diy">' + (treeNode.CORP_CAT == '-' ? '&nbsp;' : corpCat) + '</div>';
        editStr += '<div class="diy">' + (treeNode.CONTACT_PHONE == null ? '&nbsp;' : treeNode.CONTACT_PHONE) + '</div>';
        aObj.append(editStr);
    }

}

/**
 * 查询数据
 */

function Treedata() {
    $("#dataTree").empty();
    var data = [
        {"ORG_ID": "1", "id": "o1", "pId": "onull", "open": true, "name": "默认组 (4)"},
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Axxxxx",
            "CONTACT_PHONE": "已停",
            "ORG_ID": "1",
            "id": "s1",
            "pId": "o1",
            "name": "粤Axxxxx",
            "CORP_CAT": "160"
        },
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Bxxxxx",
            "CONTACT_PHONE": "已坏",
            "ORG_ID": "1",
            "id": "s38",
            "pId": "o1",
            "name": "粤Bxxxxx",
            "CORP_CAT": "160"
        },
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Cxxxxx",
            "CONTACT_PHONE": "维修",
            "ORG_ID": "1",
            "id": "s61",
            "pId": "o1",
            "name": "粤Cxxxxx",
            "CORP_CAT": "160"
        },

        {"ORG_ID": "2", "id": "o2", "pId": "onull", "open": true, "name": "默认组 (5)"},
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Axxxxx",
            "CONTACT_PHONE": "已停",
            "ORG_ID": "2",
            "id": "s1",
            "pId": "o2",
            "name": "粤Axxxxx",
            "CORP_CAT": "160"
        },
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Bxxxxx",
            "CONTACT_PHONE": "已坏",
            "ORG_ID": "2",
            "id": "s38",
            "pId": "o2",
            "name": "粤Bxxxxx",
            "CORP_CAT": "160"
        },
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Cxxxxx",
            "CONTACT_PHONE": "维修",
            "ORG_ID": "2",
            "id": "s61",
            "pId": "o2",
            "name": "粤Cxxxxx",
            "CORP_CAT": "160"
        },

        {"ORG_ID": "3", "id": "o3", "pId": "onull", "open": true, "name": "默认组 (6)"},
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Axxxxx",
            "CONTACT_PHONE": "已停",
            "ORG_ID": "3",
            "id": "s1",
            "pId": "o3",
            "name": "粤Axxxxx",
            "CORP_CAT": "160"
        },
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Bxxxxx",
            "CONTACT_PHONE": "已坏",
            "ORG_ID": "3",
            "id": "s38",
            "pId": "o3",
            "name": "粤Bxxxxx",
            "CORP_CAT": "160"
        },
        {
            "CONTACT_USER": "10.0",
            "SECTOR_NAME": "粤Cxxxxx",
            "CONTACT_PHONE": "维修",
            "ORG_ID": "3",
            "id": "s61",
            "pId": "o3",
            "name": "粤Cxxxxx",
            "CORP_CAT": "160"
        },

    ];
    zTreeNodes = data;
    //初始化树
    $.fn.zTree.init($("#dataTree"), setting, zTreeNodes);
    //添加表头
    var li_head = ' <li class="head"><div class="diy">车牌号</div><div class="diy">速度</div><div class="diy">里程</div>' +
        '<div class="diy">运行状态</div></li>';
    var rows = $("#dataTree").find('li');
    if (rows.length > 0) {
        rows.eq(1).before(li_head)
    } else {
        $("#dataTree").append(li_head);
        $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
    }
}

function query() {

    var datajson, onlinedatajson, offlinedatajson;

    $.ajax({
        url: "../locate/groupVehicles", //请求地址
        dataType: "json", //数据格式
        data: {
            force: false
        },
        type: "post", //请求方式
        async: false, //是否异步请求
        success: function (data) { //如何发送成功
            console.log(data);
            var onlines = 0;
            var list = [];
            var jsonstr = "", onlinejsonstr = "", offlinejsonstr = "";
            var total = 0;
            for (var x = 0; x < data.length; x++) {
                var item = data[x];
                if (item.type === 0) {
                    list.push(item);
                    console.log(item.id);

                    devices[item.id] = item;
                    total += 1
                    onlines += item.o
                    if (x === 0) {
                        if (item.o == 1) {
                            jsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/zaixian.png", "iconOpen":"/image/zaixian.png"}';
                            onlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/zaixian.png", "iconOpen":"/image/zaixian.png"}';
                        } else {
                            jsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';
                            offlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                        }
                    } else {
                        if (item.o == 1) {
                            jsonstr += ',{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/zaixian.png", "iconOpen":"/image/zaixian.png"}';

                            if (onlinejsonstr === "") {
                                onlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/zaixian.png", "iconOpen":"/image/zaixian.png"}';

                            } else {
                                onlinejsonstr += ',{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/zaixian.png", "iconOpen":"/image/zaixian.png"}';

                            }
                        } else {
                            jsonstr += ',{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                            if (offlinejsonstr === "") {
                                offlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                            } else {
                                offlinejsonstr += ',{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                            }
                        }
                    }
                } else {
                    if (x === 0) {
                        jsonstr += '{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        onlinejsonstr += '{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        offlinejsonstr += '{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                    } else {
                        jsonstr += ',{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        if (onlinejsonstr === "") {
                            onlinejsonstr += '{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        } else {
                            onlinejsonstr += ',{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        }
                        if (offlinejsonstr === "") {
                            offlinejsonstr += '{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        } else {
                            offlinejsonstr += ',{"ORG_ID":"' + item.id + '","id":"' + item.id + '","pId":"onull","open":true,"name":"' + item.na + '"}';
                        }

                    }
                }
            }
            $("#finishingTask").bootstrapTable("load", list);
            $('#txtAllVehicles').text(total);
            $('#txtOnlineVehicles').text(onlines);
            $('#txtOfflineVehicles').text(total - onlines);
            console.log(jsonstr);
            console.log(onlinejsonstr);
            console.log(offlinejsonstr);
            datajson = eval('[' + jsonstr + ']');
            onlinedatajson = eval('[' + onlinejsonstr + ']');
            offlinedatajson = eval('[' + offlinejsonstr + ']');
        },
    })


    zTreeNodes = datajson;
    onlinezTreeNodes = onlinedatajson;
    offlinezTreeNodes = offlinedatajson;
    console.log(offlinezTreeNodes);
    //初始化树
    $.fn.zTree.init($("#offlinedataTree"), setting, offlinezTreeNodes);
    $.fn.zTree.init($("#dataTree"), setting, zTreeNodes);
    $.fn.zTree.init($("#onlinedataTree"), setting, onlinezTreeNodes);
    //添加表头
    var li_head = ' <li class="head"><div class="diy">车牌号</div><div class="diy">速度</div><div class="diy">里程</div>' +
        '<div class="diy">运行状态</div></li>';
    var rows = $("#dataTree").find('li');
    if (rows.length > 0) {
        rows.eq(1).before(li_head)
    } else {
        $("#dataTree").append(li_head);
        $("#dataTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
    }
}

/**
 * 根据权限展示功能按钮
 * @param treeNode
 * @returns {string}
 */
function formatHandle(treeNode) {
    /*    var htmlStr = '';
        htmlStr += '<div class="icon_div"><a class="icon_view" title="查看"  href="javascript:view(\'' + treeNode.id + '\')"></a></div>';
        htmlStr += '<div class="icon_div"><a class="icon_edit" title="修改" href="javascript:edit(\'' + treeNode.id + '\')"></a></div>';
        htmlStr += '<div class="icon_div"><a class="icon_add" title="添加子部门" href="javascript:addSector(\'' + treeNode.id + '\')"></a></div>';
        htmlStr += '<div class="icon_div"><a class="icon_del" title="删除" href="javascript:del(\'' + treeNode.id + '\')"></a></div>';
        return htmlStr;*/
}

$(function () {
    //初始化数据
    query();
    //bangding();
})

$("#inputsrecsh").keydown(function () {//给输入框绑定按键事件
    if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码


        Treedata();
    }
});
$("#shuaxin").click(function () {
    alert("刷新成功");
})

function carlist() {
    alert("车辆列表点击成功");

}

function beforeClick(treeId, treeNode) {
    //  console.log(treeNode);z

    var device = devices[treeNode.id];
    console.log("device.lng" + device.lng + "device.lat" + device.lat);
    var point = new BMap.Point(device.lng, device.lat);

    var geoc = new BMap.Geocoder();

alert(device);
console.log(device);

    // center.replay(, );
var shit="replay/replay.iframe?deviceNumber=" + device.dn + '&plateNumber=' + device.na;
    window.open(shit);


    $.ajax({
        async: false,
        url: "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&mode=1&x=" + device.lng + "&y=" + device.lat,
        type: "GET",
        dataType: 'jsonp',
        jsonp: 'callback',
        data: null,
        timeout: 5000,
        beforeSend: function () {

        },
        success: function (json) {// 客户端jquery预先定义好的callback函数,成功获取跨域服务器上的json数据后,会动态执行这个callback函数
            var point = new BMap.Point(json[0].x, json[0].y);
            geoc.getLocation(point, function (rs) {

                if (!rs)
                    return '';

                var addComp = rs.addressComponents;
                var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
                if (address.length < 2) {
                    address = "经度:" + 0 + ",纬度：" + 0;
                } else {
                    var surround = rs.surroundingPois;
                    for (var i = 0; i < surround.length; i++) {
                        address += ",离" + surround[i].title + "约" + Math.round(locate.map.getDistance(point, surround[i].point)) + "米";
                    }
                }
                if (device != null) {
                    console.log(address);
                    var sContent = "<div style='margin:0px;padding:0px;border-top: 1px solid #c9c9c9;'>" +
                        "<div class='display-label'> <b>时间:</b> " + device.gt.toDate().toDateTimeString('MM-dd hh:mm:ss') + "[定位]," + device.st.toDate().toDateTimeString('MM-dd hh:mm:ss') + "[接收]</div>" +
                        "<div class='display-label'> <b>定位:</b>" + gpsDataParser.parseLocateType(device) + "[" + gpsDataParser.parseDirection(device) + " ]&nsbp&nsbp<b>状态:</b>" + gpsDataParser.parseAcc(device) + "</div>" +
                        "<div class='display-label'><b>里程:</b> " + device.m + "km&nsbp&nsbp<b>速度:</b>" + device.sp + "km</div>" +
                        "<div style='margin:2px;'><b>位置:</b>" + address + "</div>" +
                        "<div>" +
                        "<div class='mon-button'></div>" +
                        "" +
                        "</div>"
                    "</div>"
                    ;

                    var data = [{
                        lng: device.lng,
                        lat: device.lat,
                        na: sContent
                    }
                    ]
                    showGpsinfo(data, device);
                }

            }, {
                poiRadius: 1000,
                numPois: 2
            });
        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        error: function (xhr) {
            // jsonp
            // 方式此方法不被触发.原因可能是dataType如果指定为jsonp的话,就已经不是ajax事件了
            // 请求出错处理
        }
    });


    locate.map.centerAndZoom(point, 12);
}


/*
 * 根据x和y,加地址显示弹窗
 * */
function showGpsinfo(data, device) {
    var data_info = [];
    var title = "";

    title += '<div style="margin:0px;padding:0px">';
    title += '<div style="height:30px;margin:2px;">';
    title += '<b>';
    title += device.na;
    title += '</b>';
    title += '<div style="margin-left:15px;display:inline-block;">';
    title += '<div> <img  src="/gps_web/resources/new/image/xiaoxi.png" /></div>';
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
    locate.marker = new BMap.Marker(new BMap.Point(data_info[0], data_info[1]));
    var content = data_info[2];
    locate.map.addOverlay(locate.marker);
    addClickHandler(content, locate.marker);
    /*for(var i=0;i<data_info.length;i++){
        var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
        var content = data_info[i][2];
        locate.map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(content,marker);
    }*/
}


function addClickHandler(content, marker) {
    marker.addEventListener("click", function (e) {
            openInfo(content, e)
        }
    );
}

function openInfo(content, e) {
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    locate.map.centerAndZoom(point, 12);
    var infoWindow = new BMap.InfoWindow(content, locate.opts);  // 创建信息窗口对象
    locate.map.openInfoWindow(infoWindow, point); //开启信息窗口

}


