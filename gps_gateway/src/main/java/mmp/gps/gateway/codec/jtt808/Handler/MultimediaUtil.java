package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.common.util.DateFormats;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.domain.gateway.MultimediaDataUploadReport;
import mmp.gps.domain.gateway.MultimediaSaveRequest;
import mmp.gps.protocol.jtt808.JTT808Commands;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.LocationInformationBaseInfo;
import mmp.gps.protocol.jtt808.body.MultimediaDataUploadBody;
import mmp.gps.protocol.jtt808.body.MultimediaDataUploadReplyBody;
import mmp.gps.protocol.jtt808.body.RawBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class MultimediaUtil {

    private static Logger cmdLog = Logger.getLogger("cmd");
    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private static ConcurrentHashMap multimediaMap = new ConcurrentHashMap();


    public static ConcurrentHashMap getMultimediaMap() {
        return multimediaMap;
    }

    public static void checkFrame(JTT808Packet packet, byte[] raw) {
        String number = packet.getNumber();
        int pages = packet.getPages();
        int page = packet.getPage();
        Multimedia media = (Multimedia) multimediaMap.getOrDefault(number, (Object) null);
        if (media == null) {
            media = new Multimedia();
            media.setNumber(number);
            media.setPages(pages);
            multimediaMap.put(number, media);
            ScheduledFuture receive = service.scheduleAtFixedRate(new MediaFuture(number), (long) ((int) ((double) pages * 1.5D)), (long) ((int) ((double) pages * 1.5D)), TimeUnit.SECONDS);
            media.setFuture(receive);
        }

        if (packet.getPage() == 1) {
            MultimediaDataUploadBody var16 = new MultimediaDataUploadBody();
            JTT808Packet report = new JTT808Packet(var16);

            try {
                report.from(raw);
            } catch (Exception var15) {
                var15.printStackTrace();
            }

            media.setFirst(report);
        } else {
            media.getFrames().put(Integer.valueOf(page), packet);
        }

        media.setLastUploadTime(new Date());
        int var17 = media.getFirst() == null ? 0 : 1;
        var17 += media.getFrames().size();
        MultimediaDataUploadReport var18 = new MultimediaDataUploadReport();
        var18.mediaId = media.getObjId();
        var18.number = media.getNumber();
        var18.percent = (int) ((double) var17 * 1.0D / (double) pages * 100.0D);
        DataSender.getCurrent().send(var18);
        cmdLog.debug(String.format("多媒体：%s，上传第%s帧/共%s帧，完成%s", new Object[]{number, Integer.valueOf(page), Integer.valueOf(pages), Integer.valueOf(var18.percent)}));
        if (media.isCompleted()) {
            cmdLog.debug("多媒体：" + number + "上传完成");
            media.getFuture().cancel(true);
            multimediaMap.remove(number);
            reply(media);
            IoBuffer buffer = IoBuffer.allocate(10240).setAutoExpand(true);
            buffer.put(((MultimediaDataUploadBody) media.getFirst().getBody()).getData());

            for (int content = 2; content <= media.getPages(); ++content) {
                JTT808Packet request = (JTT808Packet) media.getFrames().get(Integer.valueOf(content));
                buffer.put(((RawBody) request.getBody()).getRaw());
            }

            buffer.flip();
            byte[] var19 = new byte[buffer.limit()];
            buffer.get(var19);
            MultimediaSaveRequest var20 = new MultimediaSaveRequest();
            LocationInformationBaseInfo baseInfo = ((MultimediaDataUploadBody) media.getFirst().getBody()).getBaseInfo();
            String gpsTimeString = baseInfo.getGpstime().toString();

            try {
                var20.setTime(DateFormats.DateTimeFormat.parse(gpsTimeString));
            } catch (Exception var14) {
                var20.setTime(new Date());
            }

            var20.setId(media.getObjId());
            var20.setAlarms(baseInfo.getAlarms());
            var20.setAltitude(baseInfo.getAltitude());
            var20.setAngle(baseInfo.getAngle());
            var20.setChannelId(media.getChannelId());
            var20.setData(var19);
            var20.setEventType(media.getEventType());
            var20.setFormatType(media.getFormatType());
            var20.setLat((double) baseInfo.getLat() * 1.0E-6D);
            var20.setLng((double) baseInfo.getLng() * 1.0E-6D);
            var20.setMediaId(((MultimediaDataUploadBody) media.getFirst().getBody()).getId());
            var20.setMediaType(media.getMediaType());
            var20.setNumber(media.getNumber());
            var20.setSpeed((float) baseInfo.getSpeed() * 0.1F);
            var20.setStatus(baseInfo.getStatus());
            DataSender.getCurrent().send(var20);
        }

    }

    public static void reply(Multimedia media) {
        JTT808Packet first = media.getFirst();
        if (first != null) {
            ArrayList items = new ArrayList();
            byte total = 0;

            for (int body = 2; body <= media.getPages(); ++body) {
                if (!media.getFrames().containsKey(Integer.valueOf(body)) && total < 125) {
                    ++total;
                    items.add(Integer.valueOf(body));
                }
            }

            MultimediaDataUploadReplyBody var9 = new MultimediaDataUploadReplyBody();
            var9.setList(items);
            var9.setMultimediaId(((MultimediaDataUploadBody) first.getBody()).getId());
            var9.setTotal(total);
            JTT808Packet reply = new JTT808Packet(var9);
            reply.setCommand(JTT808Commands.MultimediaDataUploadReply);
            reply.setSerialNumber(JTT808Util.getSerialNumber());
            reply.setNumber(media.getNumber());
            byte[] message = reply.array();
            IoSession session = SessionManager.getCurrent().get(media.getNumber());
            if (session != null) {
                int netKind = ((Integer) session.getAttribute("netkind")).intValue();
                if (netKind == 1) {
                    session.write(message);
                } else {
                    session.write(message, session.getRemoteAddress());
                }

                cmdLog.debug("回复多媒体：" + media.getNumber() + IoBuffer.wrap(message).getHexDump());
            }

        }
    }
}
