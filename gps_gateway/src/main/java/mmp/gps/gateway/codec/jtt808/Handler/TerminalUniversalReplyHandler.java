package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.protocol.jtt808.JTT808Packet;
import org.apache.mina.core.session.IoSession;

public class TerminalUniversalReplyHandler implements ICommandHandler {

    public Object getName() {
        return Integer.valueOf(1);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet packet = (JTT808Packet) raw;
        HandlerUtil.processReplySerialNumberPacket(session, packet, data);
    }
}
