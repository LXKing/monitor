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
    //$("#bottomRigth")
}

 