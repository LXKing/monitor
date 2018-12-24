package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.InstructResultMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.CameraShootingImmediatelyReplyBody;

public class CameraShootingImmediatelyReplyHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(2053)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        CameraShootingImmediatelyReplyBody body = new CameraShootingImmediatelyReplyBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InstructResultDto result = new InstructResultDto();
        result.number = raw.getDn();
        result.id = raw.getMsn();
        switch (body.getResult()) {
            case 0:
                result.result = "成功";
                break;
            case 1:
                result.result = "失败";
            case 2:
            default:
                break;
            case 3:
                result.result = "通道不支持";
        }

        bulk.add(result);
        InstructResultMessage message = new InstructResultMessage();
        message.number = raw.getDn();
        message.id = result.id;
        message.result = result.result;
        Pushers.getCurrent().push(packet.getNumber(), "device.instruct.result", message);
    }
}
