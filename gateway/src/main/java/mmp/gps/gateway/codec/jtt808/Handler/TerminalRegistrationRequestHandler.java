package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.common.util.ObjectId;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.TerminalRegistrationReplyBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class TerminalRegistrationRequestHandler implements ICommandHandler {

    private static Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Integer.valueOf(256);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet request = (JTT808Packet) raw;
        TerminalRegistrationReplyBody body = new TerminalRegistrationReplyBody();
        body.setResult((byte) 0);
        body.setAuthenticationCode(ObjectId.next());
        body.setResponseSerialNumber(request.getSerialNumber());
        JTT808Packet reply = new JTT808Packet();
        reply.setBody(body);
        reply.setCommand('\u8100');
        reply.setSerialNumber(JTT808Util.getSerialNumber());
        reply.setNumber(request.getNumber());
        byte[] msg = reply.array();
        cmdLog.debug("回复终端注册请求：" + IoBuffer.wrap(msg).getHexDump());
        session.write(msg);
        DataSender.getCurrent().send(request.getNumber(), 1, "", "", data);
    }
}
