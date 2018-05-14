//多车跟踪
$(function () {
    //（1屏）
    // 创建地图实例
    // setTimeout(function(){
    //     var map = new BMap.Map("ft_map");
    //     // console.log("地图的加载");
    // }, 1200);

    var map = new BMap.Map("ft_map");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 4);
    //开启鼠标滚轮缩放
    map.enableScrollWheelZoom(true);


    // 1屏 左图
    function ZoomControl0(){
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
        this.defaultOffset = new BMap.Size(10, 10);
    }
    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControl0.prototype = new BMap.Control();
    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    ZoomControl0.prototype.initialize = function(map){
        var img0 = $("<img/>").attr("src","../static/dc_file/image/s_one.png").css("width","45px");
        // 添加DOM元素到地图中
        map.getContainer().appendChild(img0[0]);
        // 将DOM元素返回
        return img0[0];
    }
    // 创建控件
    var myZoomCtrl0 = new ZoomControl0();
    // 添加到地图当中
    map.addControl(myZoomCtrl0);

    // 1屏 右图
    function ZoomControl1(){
    // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
        this.defaultOffset = new BMap.Size(20, 20);
    }
    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControl1.prototype = new BMap.Control();
    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    ZoomControl1.prototype.initialize = function(map){
        var div0 = $("<div></div>").addClass("cut_sn");
        //.css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px'})
        var img1 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css("width","20px");
        div0.append(img1);
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div0[0]);
        // 将DOM元素返回
        return div0[0];
    }
    // 创建控件
    var myZoomCtrl1 = new ZoomControl1();
    // 添加到地图当中
    map.addControl(myZoomCtrl1);

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

$(function () {
    //(2屏）
    // 创建地图实例
    var map2 = new BMap.Map("sd_map");
    map2.centerAndZoom(new BMap.Point(116.404, 39.915), 4);
    //开启鼠标滚轮缩放
    map2.enableScrollWheelZoom(true);

})
// // 1屏 左图
//     function ZoomControl3{
//         // 默认停靠位置和偏移量
//         this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
//         this.defaultOffset = new BMap.Size(10, 10);
//     }
// // 通过JavaScript的prototype属性继承于BMap.Control
//     ZoomControl3.prototype = new BMap.Control();
// // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
//     ZoomControl3.prototype.initialize = function(map){
//         var img3 = $("<img/>").attr("src","../static/dc_file/image/s_one.png");
//         img3.css("width","45px");
//         // 添加DOM元素到地图中
//         map2.getContainer().appendChild(img3[0]);
//         // 将DOM元素返回
//         return img3[0];
//     }
// // 创建控件
//     var myZoomCtrl3 = new ZoomControl3();
// // 添加到地图当中
//     map2.addControl(myZoomCtrl3);
//
// // 1屏 右图
//     function ZoomControl4(){
//         // 默认停靠位置和偏移量
//         this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
//         this.defaultOffset = new BMap.Size(20, 20);
//     }
// // 通过JavaScript的prototype属性继承于BMap.Control
//     ZoomControl4.prototype = new BMap.Control();
// // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
//     ZoomControl4.prototype.initialize = function(map){
//         var div1 = $("<div></div>").addClass("cut_sn");
//         //.css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px'})
//         var img4 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css("width","20px");
//         div1.append(img4);
//         // 添加DOM元素到地图中
//         map2.getContainer().appendChild(div1[0]);
//         // 将DOM元素返回
//         return div1[0];
//     }
// // 创建控件
//     var myZoomCtrl4 = new ZoomControl4();
// // 添加到地图当中
//     map2.addControl(myZoomCtrl4);
// })



// $(".map-contents3>.s_map>.cut_sn").click(function () {
//     var flag_0 = $(this).parent().siblings(".s_map").css("display")=="block";
//     var flag_ = $(this).parent().siblings(".s_map").css("display")=="none";
//     if(flag_0){
//         $(this).parent().css("width","100%").css("height","100%").siblings(".s_map").css("display","none");
//     }else if(flag_){
//         $(this).parent().css("width","49.9%").css("height","49.9%").siblings(".s_map").css("display","block");
//     }
// })