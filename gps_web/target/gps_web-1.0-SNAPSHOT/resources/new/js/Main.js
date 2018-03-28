/**
 *
 */
/*function setMinWidth(width) {
            var bWidth = ($(window).width() <= width) ? width : '100%';
            $('body').width(bWidth);

            // 窗口放大缩小
            $(window).resize(function () {

                var bWidth = ($(window).width() <= width) ? width : '100%';
                $('body').width(bWidth);

            });
        }*/


$(function () {
    /*	var ss = window.screen.width-20;
        var dd = window.screen.height - 205;
    $(".headtop").attr("style"," width: "+ss+"px");
    $(".bodyall").attr("style"," width: "+ss+"px;height:"+dd+"px")
    console.log(window.screen.width);
    console.log(window.screen.height);
    console.log(window.innerWidth);
    console.log(window.innerHeight);*/

});
//window.onresize 事件可用于检测页面是否触发了放大或缩小。	 
$(window).on('resize', function () {
    // isScale();
});
//判断PC端浏览器缩放比例不是100%时的情况


//阻止pc端浏览器缩放js代码
//由于浏览器菜单栏属于系统软件权限，没发控制，我们着手解决ctrl/cammond + +/- 或 Windows下ctrl + 滚轮 缩放页面的情况，只能通过js来控制了
// jqeury version
$(document).ready(function () {
    // chrome 浏览器直接加上下面这个样式就行了，但是ff不识别
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

var zTreeNodes, onlinezTreeNodes, offlinezTreeNodes;
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
};

/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
    if (treeNode.pId == null) {


    }
    else {
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
        var spaceStr = "<span style='width:" + (spaceWidth * treeNode.level) + "px'><img src='/resources/new/image/lixiandian.png'/></span>";
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
                    console.log(item);
                    total += 1
                    onlines += item.o
                    if (x === 0) {
                        jsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';
                        if (item.o == 1) {
                            onlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                        } else {
                            offlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                        }
                    } else {
                        jsonstr += ',{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';
                        if (item.o == 1) {
                            if (onlinejsonstr === "") {
                                onlinejsonstr += '{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                            } else {
                                onlinejsonstr += ',{"CONTACT_USER":"' + item.sp + '","SECTOR_NAME":"' + item.na + '", "CONTACT_PHONE":"已停", "ORG_ID":"' + item.pid + '","id":"' + item.id + '","pId":"' + item.pid + '","name":"' + item.na + '",  "CORP_CAT":"' + item.m + '" ,"iconClose":"/image/budingwei.png", "iconOpen":"/image/budingwei.png"}';

                            }
                        } else {
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
    //初始化列表
    zTreeNodes = datajson;
    onlinezTreeNodes = onlinedatajson;
    offlinezTreeNodes = offlinedatajson;
    //初始化树
    $.fn.zTree.init($("#offlinedataTree"), setting, offlinezTreeNodes);
    $.fn.zTree.init($("#dataTree"), setting, zTreeNodes);
    $.fn.zTree.init($("#onlinedataTree"), setting, onlinezTreeNodes);
    //$.fn.zTree.init($("#offlinedataTree"), setting, offlinezTreeNodes);
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
    // bangding();
})
	

 