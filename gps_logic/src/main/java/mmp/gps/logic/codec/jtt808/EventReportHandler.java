package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.EventReportMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.EventReportBody;

public class EventReportHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(769)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        EventReportBody body = new EventReportBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventReportMessage message = new EventReportMessage();
        message.number = raw.getDn();
        message.id = body.getId();
        Pushers.getCurrent().push(packet.getNumber(), "device.event.report", message);
    }
}
