package mmp.gps.logic.codec.jtt808;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.TerminalParameterInfo;
import mmp.gps.protocol.jtt808.body.TerminalQueryParametersReplyBody;

import java.util.Iterator;
import java.util.List;

public class TerminalQueryParametersReplyHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(260)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        TerminalQueryParametersReplyBody body = new TerminalQueryParametersReplyBody();
        JTT808Packet packet = new JTT808Packet(body);
        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder result = new StringBuilder();
        List parameters = body.getParameters();
        Iterator dto = parameters.iterator();

        while (dto.hasNext()) {
            TerminalParameterInfo message = (TerminalParameterInfo) dto.next();
            byte[] content = message.getContent();
            long value;
            ByteIO io;
            ByteIO io1;
            byte value1;
            short value2;
            String value3;
            int value4;
            switch ((int) message.getId()) {
                case 1:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("终端心跳发送间隔:" + value + "秒\r\n");
                    break;
                case 2:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("TCP消息应答超时时间:" + value + "秒\r\n");
                    break;
                case 3:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("TCP消息重传次数:" + value + "次\r\n");
                    break;
                case 4:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("UDP消息应答超时时间:" + value + "秒\r\n");
                    break;
                case 5:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("UDP消息重传次数:" + value + "次\r\n");
                    break;
                case 6:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("SMS消息应答超时时间:" + value + "秒\r\n");
                    break;
                case 7:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("SMS消息重传次数:" + value + "次\r\n");
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 30:
                case 31:
                case 35:
                case 36:
                case 37:
                case 38:
                case 42:
                case 43:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                default:
                    break;
                case 16:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("主服务器 APN,无线通信拨号访问点:" + value3 + "\r\n");
                    break;
                case 17:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("主服务器无线通信拨号用户名:" + value3 + "\r\n");
                    break;
                case 18:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("主服务器无线通信拨号密码:" + value3 + "\r\n");
                    break;
                case 19:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("主服务器地址,IP或域名:" + value3 + "\r\n");
                    break;
                case 20:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("备份服务器APN,无线通信拨号访问点:" + value3 + "\r\n");
                    break;
                case 21:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("备份服务器无线通信拨号用户名:" + value3 + "\r\n");
                    break;
                case 22:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("备份服务器无线通信拨号密码:" + value3 + "\r\n");
                    break;
                case 23:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("备份服务器地址,IP或域名:" + value3 + "\r\n");
                    break;
                case 24:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("服务器TCP端口:" + value4 + "\r\n");
                    break;
                case 25:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("服务器UDP端口:" + value4 + "\r\n");
                    break;
                case 26:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("道路运输证IC卡认证主服务器IP地址或域名:" + value3 + "\r\n");
                    break;
                case 27:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("道路运输证IC卡认证主服务器TCP端口:" + value4 + "\r\n");
                    break;
                case 28:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("道路运输证IC卡认证主服务器UDP端口:" + value4 + "\r\n");
                    break;
                case 29:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("道路运输证IC卡认证备份服务器IP地址或域名:" + value3 + "\r\n");
                    break;
                case 32:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = (int) io.getUint();
                    }

                    result.append("位置汇报策略:");
                    switch (value4) {
                        case 0:
                            result.append("定时汇报\r\n");
                            continue;
                        case 1:
                            result.append("定距汇报\r\n");
                            continue;
                        default:
                            result.append("定时和定距汇报\r\n");
                            continue;
                    }
                case 33:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = (int) io.getUint();
                    }

                    result.append("位置汇报方案:");
                    if (value4 == 0) {
                        result.append("根据ACC状态\r\n");
                    } else {
                        result.append("根据登录状态和ACC状态, 先判断登录状态,若登录再根据ACC状态\r\n");
                    }
                    break;
                case 34:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("驾驶员未登录汇报时间间隔:" + value + "秒\r\n");
                    break;
                case 39:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("休眠时汇报时间间隔:" + value + "秒\r\n");
                    break;
                case 40:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("紧急报警时汇报时间间隔:" + value + "秒\r\n");
                    break;
                case 41:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("缺省时间汇报间隔:" + value + "秒\r\n");
                    break;
                case 44:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("缺省距离汇报间隔:" + value + "米\r\n");
                    break;
                case 45:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("驾驶员未登录汇报距离间隔:" + value + "米\r\n");
                    break;
                case 46:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("休眠时汇报距离间隔:" + value + "米\r\n");
                    break;
                case 47:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("紧急报警时汇报距离间隔:" + value + "米\r\n");
                    break;
                case 48:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("拐点补传角度:" + value + "度\r\n");
                    break;
                case 49:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("电子围栏半径:" + value4 + "米\r\n");
                    break;
                case 64:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("监控平台电话号码:" + value3 + "\r\n");
                    break;
                case 65:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("复位电话号码:" + value3 + "\r\n");
                    break;
                case 66:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("恢复出厂设置电话号码:" + value3 + "\r\n");
                    break;
                case 67:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("监控平台SMS电话号码:" + value3 + "\r\n");
                    break;
                case 68:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("接收终端SMS文本报警号码:" + value3 + "\r\n");
                    break;
                case 69:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("终端电话接听策略:");
                    if (value == 0L) {
                        result.append("自动接听\r\n");
                    } else {
                        result.append("ACCON时自动接听，OFF时手动接听\r\n");
                    }
                    break;
                case 70:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("每次最长通话时间:");
                    if (value == 0L) {
                        result.append("不允许通话");
                    } else if (value == -1L) {
                        result.append("通话时间不限制");
                    } else {
                        result.append(value + "秒");
                    }

                    result.append("\r\n");
                    break;
                case 71:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("当月最长通话时间:");
                    if (value == 0L) {
                        result.append("不允许通话");
                    } else if (value == -1L) {
                        result.append("通话时间不限制");
                    } else {
                        result.append(value + "秒");
                    }

                    result.append("\r\n");
                    break;
                case 72:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("监听电话号码:" + value3 + "\r\n");
                    break;
                case 73:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("监管平台特权短信号码:" + value3 + "\r\n");
                    break;
                case 80:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("报警屏蔽:\r\n");
                    this.parseAlarmWitch(result, value, "开", "关");
                    break;
                case 81:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("报警发送文本SMS开关:\r\n");
                    this.parseAlarmWitch(result, value, "开", "关");
                    break;
                case 82:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("报警拍摄开关:\r\n");
                    this.parseAlarmWitch(result, value, "拍摄", "禁拍");
                    break;
                case 83:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("报警拍摄存储标志:\r\n");
                    this.parseAlarmWitch(result, value, "存储", "上传");
                    break;
                case 84:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("关键标志:\r\n");
                    this.parseAlarmWitch(result, value, "关键报警", "普通报警");
                    break;
                case 85:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("最高速度:" + value + "公里/小时\r\n");
                    break;
                case 86:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("超速持续时间:" + value + "秒\r\n");
                    break;
                case 87:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("连续驾驶时间门限:" + value + "秒\r\n");
                    break;
                case 88:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("当天累计驾驶时间门限:" + value + "秒\r\n");
                    break;
                case 89:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("最小休息时间:" + value + "秒\r\n");
                    break;
                case 90:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("最长停车时间:" + value + "秒\r\n");
                    break;
                case 91:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("超速报警预警差值:" + (double) value4 * 0.1D + "公里/小时\r\n");
                    break;
                case 92:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("疲劳驾驶预警差值:" + value4 + "秒\r\n");
                    break;
                case 93:
                    value1 = 0;
                    byte io2 = 0;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value1 = io1.get();
                        io2 = io1.get();
                    }

                    result.append("碰撞报警参数设置:\r\n");
                    result.append("碰撞时间-" + value1 * 4 + "毫秒\r\n");
                    result.append("碰撞加速度-" + (double) io2 * 0.1D + "g\r\n");
                    break;
                case 94:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("侧翻报警参数设置:侧翻角度" + value4 + "度\r\n");
                    break;
                case 100:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("定时拍照控制:\r\n");
                    this.parseTimingCamera(result, value);
                    break;
                case 101:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("定距拍照控制:\r\n");
                    this.parseDistanceCamera(result, value);
                    break;
                case 112:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("图像/视频质量:" + value + "\r\n");
                    break;
                case 113:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("亮度:" + value + "\r\n");
                    break;
                case 114:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("对比度:" + value + "\r\n");
                    break;
                case 115:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("饱和度:" + value + "\r\n");
                    break;
                case 116:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("色度:" + value + "\r\n");
                    break;
                case 128:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("车辆里程表读数:" + (double) value * 0.1D + "公里\r\n");
                    break;
                case 129:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("车辆所在的省域ID:" + value4 + "\r\n");
                    break;
                case 130:
                    value4 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value4 = io.getUshort();
                    }

                    result.append("车辆所在的市域ID:" + value4 + "\r\n");
                    break;
                case 131:
                    value3 = "";
                    if (content != null) {
                        value3 = new String(content, Charsets.GBK);
                    }

                    result.append("机动车号牌:" + value3 + "\r\n");
                    break;
                case 132:
                    value2 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value2 = io.getUbyte();
                    }

                    result.append("车牌颜色:" + value2 + "\r\n");
                    break;
                case 144:
                    value2 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value2 = io.getUbyte();
                    }

                    result.append("GNSS定位模式:\r\n");
                    result.append("GPS定位-" + ((value2 & 1) == 1 ? "启用" : "禁用")).append("\r\n");
                    result.append("北斗定位-" + ((value2 >> 1 & 1) == 1 ? "启用" : "禁用")).append("\r\n");
                    result.append("Glonass定位-" + ((value2 >> 2 & 1) == 1 ? "启用" : "禁用")).append("\r\n");
                    result.append("Galileo定位-" + ((value2 >> 3 & 1) == 1 ? "启用" : "禁用")).append("\r\n");
                    break;
                case 145:
                    value1 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value1 = io.get();
                    }

                    result.append("GNSS波特率:");
                    switch (value1) {
                        case 0:
                            result.append("4800");
                            break;
                        case 1:
                            result.append("9600");
                            break;
                        case 2:
                            result.append("19200");
                            break;
                        case 3:
                            result.append("38400");
                            break;
                        case 4:
                            result.append("57600");
                            break;
                        case 5:
                            result.append("115200");
                    }

                    result.append("\r\n");
                    break;
                case 146:
                    value1 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value1 = io.get();
                    }

                    result.append("GNSS模块详细定位数据输出频率:");
                    switch (value1) {
                        case 0:
                            result.append("500ms");
                            break;
                        case 1:
                            result.append("1000ms");
                            break;
                        case 2:
                            result.append("2000ms");
                            break;
                        case 3:
                            result.append("3000ms");
                            break;
                        case 4:
                            result.append("4000ms");
                    }

                    result.append("\r\n");
                    break;
                case 147:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("GNSS模块详细定位数据采集频率:" + value + "秒\r\n");
                    break;
                case 148:
                    value1 = 0;
                    if (content != null) {
                        io = new ByteIO(content);
                        value1 = io.get();
                    }

                    result.append("GNSS模块详细定位数据上传方式:");
                    switch (value1) {
                        case 0:
                            result.append("本地存储,不上传");
                            break;
                        case 1:
                            result.append("按时间间隔上传");
                            break;
                        case 2:
                            result.append("按距离间隔上传");
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        default:
                            break;
                        case 11:
                            result.append("按累计时间上传,达到传输时间后自动停止上传");
                            break;
                        case 12:
                            result.append("按累计距离上传,达到距离后自动停止上传");
                            break;
                        case 13:
                            result.append("按累计条数上传,达到上传条数后自动停止上传");
                    }

                    result.append("\r\n");
                    break;
                case 149:
                    value = 0L;
                    if (content != null) {
                        io1 = new ByteIO(content);
                        value = io1.getUint();
                    }

                    result.append("GNSS模块详细定位数据上传设置:" + value + "\r\n");
            }
        }

        InstructResultDto dto1 = new InstructResultDto();
        dto1.number = raw.getDn();
        dto1.id = raw.getMsn();
        dto1.result = result.toString();
        bulk.add(dto1);
        InstructResultMessage message1 = new InstructResultMessage();
        message1.id = dto1.id;
        message1.number = dto1.number;
        message1.result = dto1.result;
        Pushers.getCurrent().push(message1.number, "device.instruct.result", message1);
    }

    private void parseAlarmWitch(StringBuilder s, long flag, String trueString, String falseString) {
        s.append("紧急报警-" + ((flag & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("超速报警-" + ((flag >> 1 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("疲劳驾驶-" + ((flag >> 2 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("危险预警-" + ((flag >> 3 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("GNSS模块发生故障-" + ((flag >> 4 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("GNSS天线未接或被剪断-" + ((flag >> 5 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("GNSS天线短路-" + ((flag >> 6 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("终端主电源欠压-" + ((flag >> 7 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("终端主电源掉电-" + ((flag >> 8 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("终端LCD或显示器故障-" + ((flag >> 9 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("TTS模块故障-" + ((flag >> 10 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("摄像头故障-" + ((flag >> 11 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("道路运输证IC卡模块故障-" + ((flag >> 12 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("超速预警-" + ((flag >> 13 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("疲劳驾驶预警-" + ((flag >> 14 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("当天累计驾驶超时-" + ((flag >> 18 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("超时停车-" + ((flag >> 19 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("进出区域-" + ((flag >> 20 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("进出路线-" + ((flag >> 21 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("路段行驶时间不足/过长-" + ((flag >> 22 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("路线偏离报警-" + ((flag >> 23 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("车辆VSS故障-" + ((flag >> 24 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("车辆油量异常-" + ((flag >> 25 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("车辆被盗-" + ((flag >> 26 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("车辆非法点火-" + ((flag >> 27 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("车辆非法位移-" + ((flag >> 28 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("碰撞预警-" + ((flag >> 29 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("侧翻预警-" + ((flag >> 30 & 1L) == 1L ? trueString : falseString)).append("\r\n");
        s.append("非法开门报警-" + ((flag >> 31 & 1L) == 1L ? trueString : falseString)).append("\r\n");
    }

    private void parseTimingCamera(StringBuilder s, long flag) {
        s.append("摄像通道1定时拍照开关标志-" + ((flag & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道2定时拍照开关标志-" + ((flag >> 1 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道3定时拍照开关标志-" + ((flag >> 2 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道4定时拍照开关标志-" + ((flag >> 3 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道5定时拍照开关标志-" + ((flag >> 4 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道1定时拍照存储标志-" + ((flag >> 8 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道2定时拍照存储标志-" + ((flag >> 9 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道3定时拍照存储标志-" + ((flag >> 10 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道4定时拍照存储标志-" + ((flag >> 11 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道5定时拍照存储标志-" + ((flag >> 12 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("定时时间间隔-" + (flag >> 17) + ((flag >> 16 & 1L) == 1L ? "分" : "秒"));
    }

    private void parseDistanceCamera(StringBuilder s, long flag) {
        s.append("摄像通道1定距拍照开关标志-" + ((flag & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道2定距拍照开关标志-" + ((flag >> 1 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道3定距拍照开关标志-" + ((flag >> 2 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道4定距拍照开关标志-" + ((flag >> 3 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道5定距拍照开关标志-" + ((flag >> 4 & 1L) == 1L ? "允许" : "不允许")).append("\r\n");
        s.append("摄像通道1定距拍照存储标志-" + ((flag >> 8 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道2定距拍照存储标志-" + ((flag >> 9 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道3定距拍照存储标志-" + ((flag >> 10 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道4定距拍照存储标志-" + ((flag >> 11 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("摄像通道5定距拍照存储标志-" + ((flag >> 12 & 1L) == 1L ? "上传" : "存储")).append("\r\n");
        s.append("定距距离间隔-" + (flag >> 17) + ((flag >> 16 & 1L) == 1L ? "公里" : "米"));
    }
}
