package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.domain.gateway.RawData;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.PacketResendRequestBody;
import mmp.gps.protocol.jtt808.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class DataFrames {

    private static Logger cmdLog = Logger.getLogger("cmd");
    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private static ConcurrentHashMap dataFrames = new ConcurrentHashMap();


    public static Map getDataFrames() {
        return dataFrames;
    }

    public static void put(JTT808Packet packet, byte[] raw) {
        int command = packet.getCommand();
        if (command == 2049) {
            MultimediaUtil.checkFrame(packet, raw);
        } else {
            String number = packet.getNumber();
            int page = packet.getPage();
            int pages = packet.getPages();
            String key = number + command;
            DataFrame frame = (DataFrame) dataFrames.getOrDefault(key, (Object) null);
            if (frame == null) {
                frame = new DataFrame();
                frame.setNumber(number);
                frame.setCommand(command);
                frame.setPage(page);
                frame.setPages(pages);
                frame.getAfter().put(Integer.valueOf(page), raw);
                dataFrames.put(key, frame);
                ScheduledFuture rawData = service.scheduleAtFixedRate(new DataFrameFuture(key), (long) ((int) ((double) pages * 1.5D)), (long) ((int) ((double) pages * 1.5D)), TimeUnit.SECONDS);
                frame.setFuture(rawData);
            }

            if (page == 1) {
                frame.setTop(raw);
                frame.setSerialNumber(packet.getSerialNumber());
            } else {
                frame.getAfter().put(Integer.valueOf(page), raw);
            }

            frame.setLastUploadTime(new Date());
            if (frame.isCompleted()) {
                frame.getFuture().cancel(true);
                dataFrames.remove(number);
                reply(frame);
                RawData rawData1 = new RawData(frame.getNumber(), 1, (String) null, (String) null, frame.getTop());
                Iterator var10 = frame.getAfter().entrySet().iterator();

                while (var10.hasNext()) {
                    Entry entry = (Entry) var10.next();
                    rawData1.addAfter((byte[]) entry.getValue());
                }

                DataSender.getCurrent().send(rawData1);
            }

        }
    }

    private static void reply(DataFrame frame) {
        IoSession session = SessionManager.getCurrent().get(frame.getNumber());
        if (session != null) {
            RawBody body = new RawBody();
            JTT808Packet raw = new JTT808Packet(body);

            try {
                raw.from(frame.getTop());
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            HandlerUtil.sendPlatformUniversalReply(session, raw);
        }
    }

    public static void resend(DataFrame frame) {
        if (frame.getTop() != null) {
            ArrayList items = new ArrayList();

            for (int body = 2; body <= frame.getPages(); ++body) {
                if (!frame.getAfter().containsKey(Integer.valueOf(body))) {
                    items.add(Integer.valueOf(body));
                }
            }

            PacketResendRequestBody var7 = new PacketResendRequestBody();
            var7.setPages(items);
            var7.setMatchSerialNumber(frame.getSerialNumber());
            var7.setTotal((short) items.size());
            JTT808Packet reply = new JTT808Packet(var7);
            reply.setCommand('\u8003');
            reply.setSerialNumber(JTT808Util.getSerialNumber());
            reply.setNumber(frame.getNumber());
            byte[] message = reply.array();
            IoSession session = SessionManager.getCurrent().get(frame.getNumber());
            if (session != null) {
                int netKind = ((Integer) session.getAttribute("netkind")).intValue();
                if (netKind == 1) {
                    session.write(message);
                } else {
                    session.write(message, session.getRemoteAddress());
                }

                cmdLog.debug("下发补传分包请求：" + frame.getNumber() + IoBuffer.wrap(message).getHexDump());
            }

        }
    }
}
