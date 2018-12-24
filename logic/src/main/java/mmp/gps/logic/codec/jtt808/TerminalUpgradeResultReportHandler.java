package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.UpgradeResultReportMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.TerminalUpgradeResultReportBody;

public class TerminalUpgradeResultReportHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(264)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        TerminalUpgradeResultReportBody body = new TerminalUpgradeResultReportBody();
        JTT808Packet packet = new JTT808Packet();
        packet.setBody(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UpgradeResultReportMessage message = new UpgradeResultReportMessage();
        message.number = raw.getDn();
        message.type = body.getType();
        message.result = body.getResult();
        Pushers.getCurrent().push(packet.getNumber(), "device.upgrade.result.report", message);
    }
}
