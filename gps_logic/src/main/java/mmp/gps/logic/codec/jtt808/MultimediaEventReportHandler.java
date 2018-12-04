package mmp.gps.logic.codec.jtt808;

import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.MultimediaEventReportMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.MultimediaEventReportBody;

public class MultimediaEventReportHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(2048)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        MultimediaEventReportBody body = new MultimediaEventReportBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MultimediaEventReportMessage message = new MultimediaEventReportMessage();
        message.number = raw.getDn();
        message.channelId = body.getChannelId();
        message.eventType = body.getEventType();
        message.formatType = body.getFormatType();
        message.mediaId = body.getId();
        message.mediaType = body.getMediaType();
        Pushers.getCurrent().push(packet.getNumber(), "device.multimedia.event.report", message);
    }
}
