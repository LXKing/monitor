package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.InstructInfo;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.protocol.jtt808.JTT808Packet;
import org.apache.mina.core.session.IoSession;

public class TerminalQueryAttributesReplyHandler implements ICommandHandler {

    public Object getName() {
        return Integer.valueOf(263);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet packet = (JTT808Packet) raw;

        try {
            ReplyValidator ex = (ReplyValidator) session.getAttribute("instructs");
            String key = packet.getNumber() + 263;
            InstructInfo info = ex.get(key);
            if (info != null) {
                ex.remove(key);
                DataSender.getCurrent().send(packet.getNumber(), 1, info.getPlateSerialNumber(), info.getCommand(), data);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }
}
