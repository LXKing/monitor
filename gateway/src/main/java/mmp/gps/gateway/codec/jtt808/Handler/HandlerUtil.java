package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.InstructInfo;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.PlatformUniversalReplyBody;
import mmp.gps.protocol.jtt808.body.RawBody;
import org.apache.mina.core.session.IoSession;

class HandlerUtil {

    public static byte[] sendPlatformUniversalReply(IoSession session, JTT808Packet raw) {
        PlatformUniversalReplyBody body = new PlatformUniversalReplyBody();
        body.setResponseSerialNumber(raw.getSerialNumber());
        body.setResponseCommand(raw.getCommand());
        body.setResult((byte) 0);
        JTT808Packet reply = new JTT808Packet(body);
        reply.setBody(body);
        reply.setCommand('\u8001');
        reply.setSerialNumber(JTT808Util.getSerialNumber());
        reply.setNumber(raw.getNumber());
        byte[] msg = reply.array();
        session.write(msg);
        return msg;
    }

    public static void processReplySerialNumberPacket(IoSession session, JTT808Packet raw, byte[] data) {
        try {
            ReplyValidator ex = (ReplyValidator) session.getAttribute("instructs");
            byte[] rawData = ((RawBody) raw.getBody()).getRaw();
            Integer serialNumber = new Integer((rawData[0] & 255) << 8 | rawData[1] & 255);
            InstructInfo info = ex.get(raw.getNumber() + serialNumber);
            if (info != null) {
                ex.remove(raw.getNumber() + serialNumber);
                DataSender.getCurrent().send(raw.getNumber(), 1, info.getPlateSerialNumber(), info.getCommand(), data);
            } else {
                DataSender.getCurrent().send(raw.getNumber(), 1, "", "", data);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}
