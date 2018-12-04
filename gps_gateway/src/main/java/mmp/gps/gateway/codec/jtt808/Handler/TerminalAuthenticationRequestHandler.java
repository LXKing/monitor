package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.protocol.jtt808.JTT808Packet;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class TerminalAuthenticationRequestHandler implements ICommandHandler {

    private static Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Integer.valueOf(258);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet request = (JTT808Packet) raw;
        byte[] msg = HandlerUtil.sendPlatformUniversalReply(session, request);
        cmdLog.debug("回复终端鉴权请求：" + IoBuffer.wrap(msg).getHexDump());
        DataSender.getCurrent().send(request.getNumber(), 1, "", "", data);
    }
}
