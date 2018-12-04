package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.push.ElectronicWaybillReportMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.ElectronicWaybillReportBody;
import mmp.gps.domain.gateway.RawData;
public class ElectronicWaybillReportHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(1793)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        ElectronicWaybillReportBody body = new ElectronicWaybillReportBody();
        JTT808Packet packet = new JTT808Packet(body);
        ElectronicWaybillReportMessage message = new ElectronicWaybillReportMessage();
        message.number = raw.getDn();
        message.content = body.getContent();
        Pushers.getCurrent().push(packet.getNumber(), "device.electronic.waybill.report", message);
    }
}
