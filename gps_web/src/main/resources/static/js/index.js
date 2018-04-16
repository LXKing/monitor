$(function(){
    $('.map-car-info>a').click(function(){
        console.log(123);
        $(this).addClass('current').siblings().removeClass('current');
        $('.map-car-details>div').eq($(this).index()).css('display','block').siblings('div').css('display','none');
    })
});
   