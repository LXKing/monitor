var shitbar = `
     <div class="layui-form layui-form-pane demoTable" id="mmpxxx" style="width: 100%;">
                <div class="layui-inline sbox" style="width: 20%" >
                    <input class="layui-input" name="id" id="tableReload" autocomplete="off" style="color: black;">
                </div>
                <button class="layui-btn  layui-bg-blue " data-type="reload">搜索</button>
 
                    
     </div>
    `;


function nav_to(url, title) {
    jQuery("#tableTitle").text(title);
    jQuery("#tableMain").remove();
    jQuery("#chart").show();
    if (url === "/statistics/historyOnlineOfflineCount") {
        renderHistoryOnlineOfflineCount(url);
    } else if (url === "/statistics/historyOnlineTimeCount") {
        Ren_historyOnlineTimeCount(url);
    }
    else if (url === "/statistics/currentOnlineOfflineCount") {
        Ren_currentOnlineOfflineCount(url);
    }
    else if (url === "/statistics/mileageOilCount") {
        Ren_mileageOilCount(url);
    }
    else if (url === "/statistics/vehicleAlarmCount") {
        Ren_vehicleAlarmCount(url);
    }
    else if (url === "/statistics/vehicleFatigueDrivingCount") {
        Ren_vehicleFatigueDrivingCount(url);
    }
    else if (url === "/statistics/vehicleOverspeedCount") {
        Ren_vehicleOverspeedCount(url);
    }
    else if (url === "/statistics/sectionOverspeedCount") {
        Ren_sectionOverspeedCount(url);
    }
    else if (url === "/statistics/areaOverspeedCount") {
        Ren_areaOverspeedCount(url);
    }
    else if (url === "/statistics/timeoutParkingCount") {
        Ren_timeoutParkingCount(url);
    }
    else if (url === "/statistics/routeDeviationCount") {
        Ren_routeDeviationCount(url);
    }
    else if (url === "/statistics/areaIoCount") {
        Ren_areaIoCount(url);
    }
    else if (url === "/statistics/operateLog") {
        Ren_operateLog(url);
    }
    else {
        jQuery(".layui-form").hide();
        jQuery("#shitBar").hide();
        console.log(url);
        alert("开发中");
        return;
    }
    jQuery("#shitBar").show();
    if (window.top !== window.self) {
        // alert("mmp")
        jQuery(".ssss").remove();

        jQuery("body").on('click', function (event) {
            jQuery("a.layui-layer-btn0").css("display", "none");
            jQuery("a.layui-btn-danger").css("display", "none");

        });
    }

}


$(function () {
    $.ajax({
        url: "/statistics/menus", success: function (res) {

            console.log(document.getElementById('view'));
            console.log(document.getElementById('nav2'));
            console.log(res);
            console.log(template("nav2", {
                data: res
            }));

            document.getElementById('view').innerHTML = template("nav2", {
                data: res
            });
        }
    });


    $("#view").on("click", ".cccc", function () {
        // console.log(this)
        var fuck = $(this).parent().find("dl");
        // fuck.is(":hidden") ? fuck.show("normal") : fuck.hide("normal");
        if (fuck.is(":hidden")) {
            var sss = $(this);
            fuck.show("normal", function () {
                sss.parent().addClass("layui-nav-itemed");
            });


        } else {
            var sss = $(this);
            fuck.hide("normal", function () {
                sss.parent().removeClass("layui-nav-itemed");
            });


        }


    });
});



