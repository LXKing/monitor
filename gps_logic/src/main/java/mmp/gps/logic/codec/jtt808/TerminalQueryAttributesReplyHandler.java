package mmp.gps.logic.codec.jtt808;

import mmp.gps.common.util.Charsets;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.InstructResultMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.TerminalQueryAttributesReplyBody;

public class TerminalQueryAttributesReplyHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(263)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        TerminalQueryAttributesReplyBody body = new TerminalQueryAttributesReplyBody();
        JTT808Packet packet = new JTT808Packet(body);
        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder result = new StringBuilder();
        int type = body.getType();
        result.append("终端类型:\r\n");
        result.append((type & 1) == 1 ? "适用客运车辆" : "不适用客运车辆").append("\r\n");
        result.append((type >> 1 & 1) == 1 ? "适用危险品车辆" : "不适用危险品车辆").append("\r\n");
        result.append((type >> 2 & 1) == 1 ? "适用普通货运车辆" : "不适用普通货运车辆").append("\r\n");
        result.append((type >> 3 & 1) == 1 ? "适用出租车辆" : "不适用出租车辆").append("\r\n");
        result.append((type >> 6 & 1) == 1 ? "支持硬盘录像" : "不支持硬盘录像").append("\r\n");
        result.append((type >> 7 & 1) == 1 ? "分体机" : "一体机").append("\r\n");
        result.append("终端制造商编码:" + new String(body.getFactoryId(), Charsets.GBK) + "\r\n");
        result.append("终端型号:" + new String(body.getModel(), Charsets.GBK) + "\r\n");
        result.append("终端ID:" + new String(body.getDeviceId(), Charsets.GBK) + "\r\n");
        result.append("ICCID:" + new String(body.getICCID(), Charsets.GBK) + "\r\n");
        result.append("硬件版本号:" + body.getHardwareVer() + "\r\n");
        result.append("固件版本号:" + body.getFirmwareVer() + "\r\n");
        result.append("GNSS模块属性:\r\n");
        byte gnss = body.getGnssModule();
        result.append((gnss & 1) == 1 ? "支持GPS定位" : "不支持GPS定位").append("\r\n");
        result.append((gnss >> 1 & 1) == 1 ? "支持北斗定位" : "不支持北斗定位").append("\r\n");
        result.append((gnss >> 2 & 1) == 1 ? "支持GLONASS定位" : "不支持GLONASS定位").append("\r\n");
        result.append((gnss >> 3 & 1) == 1 ? "支持Galileo定位" : "不支持Galileo定位").append("\r\n");
        result.append("通信模块属性:\r\n");
        byte cm = body.getCommunicationModule();
        result.append((cm & 1) == 1 ? "支持GPRS通信" : "不支持GPRS通信").append("\r\n");
        result.append((cm >> 1 & 1) == 1 ? "支持CDMA通信" : "不支持CDMA通信").append("\r\n");
        result.append((cm >> 2 & 1) == 1 ? "支持TD-SCDMA通信" : "不支持TD-SCDMA通信").append("\r\n");
        result.append((cm >> 3 & 1) == 1 ? "支持WCDMA通信" : "不支持WCDMA通信").append("\r\n");
        result.append((cm >> 4 & 1) == 1 ? "支持CDMA2000通信" : "不支持CDMA2000通信").append("\r\n");
        result.append((cm >> 5 & 1) == 1 ? "支持TD-LTE通信" : "不支持TD-LTE通信").append("\r\n");
        result.append((cm >> 7 & 1) == 1 ? "支持其他通信方式" : "不支持其他通信方式").append("\r\n");
        InstructResultDto dto = new InstructResultDto();
        dto.number = raw.getDn();
        dto.id = raw.getMsn();
        dto.result = result.toString();
        bulk.add(dto);
        InstructResultMessage message = new InstructResultMessage();
        message.number = raw.getDn();
        message.id = dto.id;
        message.result = dto.result;
        Pushers.getCurrent().push(packet.getNumber(), "device.instruct.result", message);
    }
}
