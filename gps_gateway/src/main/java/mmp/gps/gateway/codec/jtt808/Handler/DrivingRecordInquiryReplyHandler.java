package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.protocol.jtt808.JTT808Packet;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class DrivingRecordInquiryReplyHandler implements ICommandHandler {

    private static Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Integer.valueOf(1792);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet reply = (JTT808Packet) raw;
        byte[] msg = HandlerUtil.sendPlatformUniversalReply(session, reply);
        cmdLog.debug("回复行驶记录数据上传：" + IoBuffer.wrap(msg).getHexDump());
        HandlerUtil.processReplySerialNumberPacket(session, reply, data);
    }
}
