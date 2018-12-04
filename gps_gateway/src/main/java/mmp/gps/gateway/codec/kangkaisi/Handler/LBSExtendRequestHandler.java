package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.LBSExtendBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class LBSExtendRequestHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 40);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        KangkaisiPacket request = (KangkaisiPacket) raw;
        RawBody rawBody = (RawBody) request.getBody();
        LBSExtendBody lbsExtendBody = new LBSExtendBody();
        lbsExtendBody.from(rawBody.getRaw());
        this.cmdLog.debug("LBS ：" + lbsExtendBody);
    }
}
