package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.GpsTime;
import mmp.gps.protocol.kangkaisi.body.ServerReplySchoolBody;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.session.IoSession;

public class HandlerUtil {

    public static byte[] sendPlatformUniversalReply(IoSession session, KangkaisiPacket raw) {
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket();
        kangkaisiPacket.setProtocolNumber((short) 1);
        kangkaisiPacket.setSerialNumber(JTT808Util.getSerialNumber());
        byte[] msg = kangkaisiPacket.array();
        session.write(msg);
        return msg;
    }

    public static byte[] sendHeartbeatReply(IoSession session, KangkaisiPacket raw) {
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket();
        kangkaisiPacket.setProtocolNumber((short) 19);
        kangkaisiPacket.setSerialNumber(JTT808Util.getSerialNumber());
        byte[] msg = kangkaisiPacket.array();
        session.write(msg);
        return msg;
    }

    public static byte[] sendSchoolReply(IoSession session, KangkaisiPacket raw) {
        ServerReplySchoolBody serverReplySchoolBody = new ServerReplySchoolBody();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time2 = dateFormat.format(date);
        GpsTime gpsTime = GpsTime.parse(time2);
        serverReplySchoolBody.setTime(gpsTime);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(serverReplySchoolBody);
        kangkaisiPacket.setProtocolNumber((short) 138);
        kangkaisiPacket.setSerialNumber(JTT808Util.getSerialNumber());
        byte[] msg = kangkaisiPacket.array();
        session.write(msg);
        return msg;
    }
}
