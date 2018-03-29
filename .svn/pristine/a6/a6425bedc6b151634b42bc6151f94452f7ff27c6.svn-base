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
    var ss = window.screen.width + 14;
    $(".headtop").attr("style", " width: " + ss + "px");
    $(".bodyall").attr("style", " width: " + window.screen.width + "px;height:" + window.screen.height + "px")

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

var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    edit: {
        enable: false
    }
};

var zNodes = [
    {id: 1, pId: 0, name: "父节点1", open: true},
    {id: 11, pId: 1, name: "父节点11"},
    {id: 111, pId: 11, name: "叶子节点111"},
    {id: 112, pId: 11, name: "叶子节点112"},
    {id: 113, pId: 11, name: "叶子节点113"},
    {id: 114, pId: 11, name: "叶子节点114"},
    {id: 12, pId: 1, name: "父节点12"},
    {id: 121, pId: 12, name: "叶子节点121"},
    {id: 122, pId: 12, name: "叶子节点122"},
    {id: 123, pId: 12, name: "叶子节点123"},
    {id: 124, pId: 12, name: "叶子节点124"},
    {id: 13, pId: 1, name: "父节点13", isParent: true},
    {id: 2, pId: 0, name: "父节点2"},
    {id: 21, pId: 2, name: "父节点21", open: true},
    {id: 211, pId: 21, name: "叶子节点211"},
    {id: 212, pId: 21, name: "叶子节点212"},
    {id: 213, pId: 21, name: "叶子节点213"},
    {id: 214, pId: 21, name: "叶子节点214"},
    {id: 22, pId: 2, name: "父节点22"},
    {id: 221, pId: 22, name: "叶子节点221"},
    {id: 222, pId: 22, name: "叶子节点222"},
    {id: 223, pId: 22, name: "叶子节点223"},
    {id: 224, pId: 22, name: "叶子节点224"},
    {id: 23, pId: 2, name: "父节点23"},
    {id: 231, pId: 23, name: "叶子节点231"},
    {id: 232, pId: 23, name: "叶子节点232"},
    {id: 233, pId: 23, name: "叶子节点233"},
    {id: 234, pId: 23, name: "叶子节点234"},
    {id: 3, pId: 0, name: "父节点3", isParent: true}
];

$(document).ready(function () {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: "new node" + (newCount++)});
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};

 