/**
 * 定位查询
 */
window.web = {
    /**
     * 服务器时间戳
     */
    serverTimestamp: null,
    /**
     * 网络连接
     */
    websocket: null,
    /**
     * 心跳时间
     */
    HeartbeatTime: new Date(),


    /**
     * 处理实时位置数据
     */
    handleDeviceRealtimeTrack: function (item) {

    },
    /**
     * 处理上下线通知
     */
    handleGatewayStatisticsDeviceOnlineOffline: function (item) {

    },
    /**
     * 多媒体上传进度
     */
    handleDeviceMultimediaUploadPercent: function (item) {

    },
    /**
     * 多媒体事件报告
     */
    handleDeviceMultimediaEventReport: function (item) {

    },
    /**
     * 终端事件报告
     */
    handleDeviceEventReport: function (item) {

    },
    /**
     * 处理终端升级结查报告
     */
    handleDeviceUpgradeResultReport: function (item) {

    },
    /**
     * 处理终端指令结果报告
     */
    handleDeviceInstructResult: function (item) {

    },
    /**
     * 网络连接
     */
    connect: function () {
        var httpRootPath = window.location.protocol+"//"+window.location.hostname;
        var wsRootPath = httpRootPath.replace("http", "ws");
        if ('WebSocket' in window) {
            web.websocket = new WebSocket(wsRootPath + "/webSocketServer");
        } else if ('MozWebSocket' in window) {
            web.websocket = new MozWebSocket(wsRootPath + "/webSocketServer");
        } else {
            web.websocket = new SockJS(httpRootPath + "/sockjs/webSocketServer");
        }
        web.websocket.onopen = function (e) {
            if (console)
                console.log('已建立websocket连接......');
        };
        web.websocket.onmessage = function (e) {
            try {

                console.log(e.data);
                var list = eval(e.data);
                for (var x = 0; x < list.length; x++) {
                    var item = list[x];
                    if (!item.kind)
                        continue;
                    switch (item.kind) {
                        case 'server.timestamp':
                            if (!web.serverTimestamp)
                                web.serverTimestamp = item.data;
                            else
                                web.refreshGroups();
                            break;
                        case 'device.realtime.track':
                            web.handleDeviceRealtimeTrack(item);
                            break;
                        case 'gateway.statistics.device.onlineoffline':
                            web.handleGatewayStatisticsDeviceOnlineOffline(item);
                            break;
                        case 'device.multimedia.upload.percent':
                            web.handleDeviceMultimediaUploadPercent(item);
                            break;
                        case 'device.multimedia.event.report':
                            web.handleDeviceMultimediaEventReport(item);
                            break;
                        case 'device.event.report':
                            web.handleDeviceEventReport(item);
                            break;
                        case 'device.upgrade.result.report':
                            web.handleDeviceUpgradeResultReport(item);
                            break;
                        case 'device.instruct.result':
                            web.handleDeviceInstructResult(item);
                            break;
                        default:
                            break;
                    }
                }

            } catch (e) {
                if (console)
                    console.log(e);
            }
        };
        web.websocket.onerror = function (e) {
            if (console)
                console.log('websocket连接错误......');
        };
        web.websocket.onclose = function (e) {
            if (console)
                console.log('已断开websocket连接......');
        };
    },

};
$(function () {


    web.connect();
    window.setInterval(function () {
        if (web.websocket) {
            var state = web.websocket.readyState;
            if (state != 1) {
                web.connect();
            } else {
                var now = new Date();
                var time = now.getTime() - web.HeartbeatTime.getTime()
                if (time > 2 * 60 * 1000) {
                    web.HeartbeatTime = now;
                    if (web.serverTimestamp)
                        web.websocket.send(web.serverTimestamp);
                    else
                        web.websocket.send("OK");
                }
            }
        } else {
            web.connect();
        }
    }, 1000 * 30);
});