package mmp.gps.logic.codec.jtt808;

import mmp.gps.common.util.DateFormats;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.InstructResultMessage;
import mmp.gps.domain.push.MultimediaDataRetrievalMessage;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.LocationInformationBaseInfo;
import mmp.gps.protocol.jtt808.body.MultimediaDataRetrievalReplyBody;
import mmp.gps.protocol.jtt808.body.MultimediaDataRetrievalReplyInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class MultimediaDataRetrievalReplyHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(2050)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        MultimediaDataRetrievalReplyBody body = new MultimediaDataRetrievalReplyBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InstructResultDto result = new InstructResultDto();
        result.number = raw.getDn();
        result.id = raw.getMsn();
        result.result = "设备：成功";
        bulk.add(result);
        InstructResultMessage message = new InstructResultMessage();
        message.number = raw.getDn();
        message.id = result.id;
        message.result = result.result;
        ArrayList medias = new ArrayList();
        Iterator var9 = body.getList().iterator();

        while (var9.hasNext()) {
            MultimediaDataRetrievalReplyInfo info = (MultimediaDataRetrievalReplyInfo) var9.next();
            LocationInformationBaseInfo baseInfo = info.getBaseInfo();
            MultimediaDataRetrievalMessage media = new MultimediaDataRetrievalMessage();
            media.number = raw.getDn();
            media.alarms = baseInfo.getAlarms();
            media.altitude = baseInfo.getAltitude();
            media.angle = baseInfo.getAngle();
            media.channelId = info.getChannelId();
            media.eventType = info.getEventType();
            String gpstimeString = baseInfo.getGpstime().toString();

            try {
                media.gpstime = DateFormats.DateTimeFormat.parse(gpstimeString);
            } catch (ParseException var15) {
                continue;
            }

            media.lat = (double) baseInfo.getLat() * 1.0E-6D;
            media.lng = (double) baseInfo.getLng() * 1.0E-6D;
            media.speed = (float) baseInfo.getSpeed() * 0.1F;
            media.mediaId = info.getMediaId();
            media.mediaType = info.getMediaType();
            medias.add(media);
        }

        Pushers.getCurrent().push(packet.getNumber(), "device.multimedia.retrieval.reply", medias);
    }
}
