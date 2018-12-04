package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.push.DataTransferMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.DataTransferToServerBody;
import mmp.gps.domain.gateway.RawData;
public class DataTransferToServerHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(2304)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        DataTransferToServerBody body = new DataTransferToServerBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataTransferMessage message = new DataTransferMessage();
        message.number = raw.getDn();
        message.type = body.getType();
        message.data = body.getData();
        Pushers.getCurrent().push(packet.getNumber(), "device.data.transfer", message);
    }
}
