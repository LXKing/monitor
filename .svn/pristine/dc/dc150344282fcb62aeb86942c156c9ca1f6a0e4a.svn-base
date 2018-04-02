/**
 *
 */
window.gpsDataParser = {
    parseLocateType: function (data) {
        var s = data.s;
        if ((s >> 18 & 0x1) === 0x01)
            return 'GPS';
        if ((s >> 19 & 0x1) === 0x01)
            return '北斗';
        if ((s >> 20 & 0x1) === 0x01)
            return 'GLONASS';
        if ((s >> 21 & 0x1) === 0x01)
            return 'Galileo';
        return 'GPS';
    },
    parseDirection: function (data) {
        var a = data.d;
        if (a > 360 - 22.5 || a <= 22.5)
            return '正北';
        if (a > 45 - 22.5 && a < 45 + 22.5)
            return '东北';
        if (a > 90 - 22.5 && a < 90 + 22.5)
            return '正东';
        if (a > 135 - 22.5 && a < 135 + 22.5)
            return '东南';
        if (a > 180 - 22.5 && a < 180 + 22.5)
            return '正南';
        if (a > 225 - 22.5 && a < 225 + 22.5)
            return '西南';
        if (a > 270 - 22.5 && a < 270 + 22.5)
            return '正西';
        if (a > 315 - 22.5 && a < 315 + 22.5)
            return '西北';
    },
    parseAcc: function (data) {
        var s = data.s;
        // 调试后请改回 s & 1===1
        return s & 1 === 1 ? '点火' : '熄火';
    }
};

window.gpsDataParser.parseStatus = function (data) {
    var states = [];
    var state = parseInt(data.s);

    states.push((state & 1) == 1 ? '点火' : '熄火');
    states.push((state >> 1 & 1) == 1 ? '已定位' : '未定位');
    // 4
    // 0:运营状态;1:停运状态
    states.push((state >> 4 & 1) == 1 ? '运营' : '停运');
    // 5
    // 0:经纬度未经保密插件加密;1:经纬度已经保密插件加密
    // 6-7
    // 保留
    // 8-9
    // 00:空车;01:半载;10:保留;11:满载 (可用于客车的空、重车及货车的空载、满载状态表示,人工输入或传感器 获取)
    if ((state >> 8 & 3) == 3)
        states.push('满载');
    if ((state >> 8 & 1) == 1)
        states.push('半载');
    if ((state >> 8 & 3) == 0)
        states.push('空车');
    // 10
    // 0:车辆油路正常;1:车辆油路断开
    states.push((state >> 10 & 1) === 1 ? '油路断开' : '油路正常');
    // 11
    // 0:车辆电路正常;1:车辆电路断开
    states.push((state >> 11 & 1) == 1 ? '电路断开' : '电路正常');
    // 12
    // 0:车门解锁;1:车门加锁
    states.push((state >> 12 & 1) == 1 ? '车门加锁' : '车门解锁');
    // 13
    // 0:门 1 关;1:门 1 开(前门)
    states.push((state >> 13 & 1) == 1 ? '前门开' : '前门关');
    // 14
    // 0:门 2 关;1:门 2 开(中门)
    states.push((state >> 14 & 1) == 1 ? '中门开' : '中门关');
    // 15
    // 0:门 3 关;1:门 3 开(后门)
    states.push((state >> 15 & 1) == 1 ? '后门开' : '后门关');
    // 16
    // 0:门 4 关;1:门 4 开(驾驶席门)
    states.push((state >> 16 & 1) == 1 ? '驾驶席门开' : '驾驶席门关');
    // 17
    // 0:门 5 关;1:门 5 开(自定义)
    states.push((state >> 17 & 1) == 1 ? '门5开' : '门5关');

    return states.join(';');
};
window.gpsDataParser.parseAlarm = function (data) {

    var alarm = parseInt(data.a);
    var alarms = [];
    // 0 1：紧急报警，触动报警开关后触发
    if (alarm & 1 == 1) {
        alarms.push('紧急报警');
    }
    // 1 1：超速报警
    if (alarm >> 1 & 1 == 1) {
        alarms.push('超速报警');
    }
    // 2 1：疲劳驾驶
    if (alarm >> 2 & 1 == 1) {
        alarms.push('疲劳驾驶');
    }
    // 3 1：危险预警
    if (alarm >> 3 & 1 == 1) {
        alarms.push('危险预警');
    }
    // 4 1：GPS模块故障
    if (alarm >> 4 & 1 == 1) {
        alarms.push('GPS模块故障');
    }
    // 5 1：GPS天线未接或剪断
    if (alarm >> 5 & 1 == 1) {
        alarms.push('GPS天线未接或剪断');
    }
    // 6 1：GPS天线短路
    if (alarm >> 6 & 1 == 1) {
        alarms.push('GPS天线短路');
    }
    // 7 1：终端主电欠压
    if (alarm >> 7 & 1 == 1) {
        alarms.push('终端主电欠压');
    }
    // 8 1：终端主电掉电
    if (alarm >> 8 & 1 == 1) {
        alarms.push('终端主电掉电');
    }
    // 9 1：终端LCD或显示器故障
    if (alarm >> 9 & 1 == 1) {
        alarms.push('终端LCD或显示器故障');
    }
    // 10 1：TTS 模块故障
    if (alarm >> 10 & 1 == 1) {
        alarms.push('TTS 模块故障');
    }
    // 11 1：摄像头故障
    if (alarm >> 11 & 1 == 1) {
        alarms.push('摄像头故障');
    }
    // 12 1：道路运输证IC卡模块故障
    if (alarm >> 12 & 1 == 1) {
        alarms.push('道路运输证IC卡模块故障');
    }
    // 13 1：超速预警
    if (alarm >> 13 & 1 == 1) {
        alarms.push('超速预警');
    }
    // 14 1：疲劳驾驶预警
    if (alarm >> 14 & 1 == 1) {
        alarms.push('疲劳驾驶预警');
    }
    // 18 1：当天累计驾驶超时
    if (alarm >> 18 & 1 == 1) {
        alarms.push('当天累计驾驶超时');
    }
    // 19 1：超时停车
    if (alarm >> 19 & 1 == 1) {
        alarms.push('超时停车');
    }
    // 20 1：进出区域
    if (alarm >> 20 & 1 == 1) {
        alarms.push('进出区域');
    }
    // 21 1：进出路线
    if (alarm >> 21 & 1 == 1) {
        alarms.push('进出路线');
    }
    // 22 1：路段行驶时间不足/过长
    if (alarm >> 22 & 1 == 1) {
        alarms.push('路段行驶时间不足/过长');
    }
    // 23 1：路线偏离报警
    if (alarm >> 23 & 1 == 1) {
        alarms.push('路线偏离报警');
    }
    // 24 1：车辆VSS故障
    if (alarm >> 24 & 1 == 1) {
        alarms.push('车辆VSS故障');
    }
    // 25 1：车辆油量异常
    if (alarm >> 25 & 1 == 1) {
        alarms.push('车辆油量异常');
    }
    // 26 1：车辆被盗
    if (alarm >> 26 & 1 == 1) {
        alarms.push('车辆被盗');
    }
    // 27 1：车辆非法点火
    if (alarm >> 27 & 1 == 1) {
        alarms.push('车辆非法点火');
    }
    // 28 1：车辆非法位移
    if (alarm >> 28 & 1 == 1) {
        alarms.push('车辆非法位移');
    }
    // 29 1：碰撞预警
    if (alarm >> 29 & 1 == 1) {
        alarms.push('碰撞预警');
    }
    // 30 1：侧翻预警
    if (alarm >> 30 & 1 == 1) {
        alarms.push('侧翻预警');
    }
    // 31 1：非法开门报警
    if (alarm >> 31 & 1 == 1) {
        alarms.push('非法开门报警');
    }
    var from = data.from === 1 ? '[平台]' : '[终端]';
    if (alarms.length > 0)
        return from + alarms.join(',');
    else
        return '';
};
window.gpsDataParser.parseIcon = function (data) {
    var icon = {
        url: '../resources/icons/' + (data.marker || '00.png'),
        offset: 0
    };
    if (data.a > 0)
        icon.offset = 96;
    else if (data.o === 0)
        icon.offset = 0;
    else if (data.sp > 0)
        icon.offset = 64;
    else
        icon.offset = 32;
    return icon;
}

window.gpsDataParser.parseNet = function (data) {
    var net = common.round(data.net / 30 * 5, 0);
    net == net > 5 ? 5 : net;
    return net;
}

window.gpsDataParser.parseIconColor = function (data) {
    var color = 'black';
    if (data.a > 0)
        color = 'red';
    else if (data.o === 0)
        color = 'black';
    else if (data.sp > 0)
        color = 'green';
    else
        color = 'blue';
    return color;
}
