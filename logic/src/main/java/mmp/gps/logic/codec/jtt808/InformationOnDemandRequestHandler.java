package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.InformationOnDemandMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.InformationOnDemandRequestBody;

public class InformationOnDemandRequestHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(771)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        InformationOnDemandRequestBody body = new InformationOnDemandRequestBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InformationOnDemandMessage message = new InformationOnDemandMessage();
        message.number = raw.getDn();
        message.type = body.getType();
        message.action = body.getAction();
        Pushers.getCurrent().push(packet.getNumber(), "device.information.on.demand.request", message);
    }
}
