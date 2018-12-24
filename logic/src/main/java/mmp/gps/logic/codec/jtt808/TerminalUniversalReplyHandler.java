package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.InstructResultMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.TerminalUniversalReplyBody;

public class TerminalUniversalReplyHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(1)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        TerminalUniversalReplyBody body = new TerminalUniversalReplyBody();
        JTT808Packet packet = new JTT808Packet();
        packet.setBody(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte type = body.getResult();
        InstructResultDto result = new InstructResultDto();
        result.number = raw.getDn();
        result.id = raw.getMsn();
        result.result = "设备：" + (type == 0 ? "成功" : (type == 1 ? "失败" : "不支持"));
        bulk.add(result);
        InstructResultMessage message = new InstructResultMessage();
        message.id = result.id;
        message.number = result.number;
        message.result = result.result;
        Pushers.getCurrent().push(packet.getNumber(), "device.instruct.result", message);
    }
}
