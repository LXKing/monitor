//监听登录页下部分高度
$(function () {
    function lp_h() {
        var s_H = $(document).height();
        $(".login_page").height(s_H);
    }
    lp_h();
    $(window).resize(lp_h);
});