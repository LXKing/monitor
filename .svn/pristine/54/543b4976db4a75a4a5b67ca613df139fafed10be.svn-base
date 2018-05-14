window.cardriving={
    webMap: undefined,
    onMapLoaded:function (map) {
        console.log("地图加载成功!!!");
        cardriving.webMap= map;
        var img0 = $("<img/>").attr("src","../static/dc_file/image/s_one.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
        $("#ft_map").append(img0);
        var div0 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
        var img1 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
        div0.append(img1);
        $("#ft_map").append(div0);

        var img3 = $("<img/>").attr("src","../static/dc_file/image/s_two.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
        $("#sd_map").append(img3);
        var div1 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
        var img4 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
        div1.append(img4);
        $("#sd_map").append(div1);

        var img5 = $("<img/>").attr("src","../static/dc_file/image/s_three.png").css({'width':'45px','position':'absolute','left':'10px','top':'10px'});
        $("#td_map").append(img5);
        var div2 = $("<div></div>").addClass("cut_sn").css({ 'width': '25px','height': '25px','backgroundColor': '#028ad6','textAlign': 'center','paddingTop': '3px','position':'absolute','right':'20px','top':'20px'});
        var img6 = $("<img/>").attr("src","../static/dc_file/image/fangda-suoxiao-icon.png").css({'width':'20px'});
        div2.append(img6);
        $("#td_map").append(div2);

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
    }
}

$(function(){
         //创建地图 1
    (function () {
        setTimeout(function(){
            webMap.events.onMapLoadCompleted['ft_map'] = cardriving.onMapLoaded;
            webMap.createMap("ft_map");
        }, 1200);
    })()

        // console.log(webMap);
    })

// $(function () {
//     //创建地图 2
// setTimeout(function(){
//     webMap.events.onMapLoadCompleted['sd_map'] = cardriving.onMapLoaded;
//     webMap.createMap("sd_map");
// }, 1000);
// })
//
// $(function () {
//     //创建地图 3
// setTimeout(function(){
//     webMap.events.onMapLoadCompleted['td_map'] = cardriving.onMapLoaded;
//     webMap.createMap("td_map");
// }, 1000);
// })
//
// $(function () {
//     //创建地图 4
// setTimeout(function(){
//     webMap.events.onMapLoadCompleted['fh_map'] = cardriving.onMapLoaded;
//     webMap.createMap("fh_map");
// }, 1000);
// })
