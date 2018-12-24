package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.codec.kangkaisi.beans.MemMsInfo;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class SchoolRequestHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 138);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        KangkaisiPacket request = (KangkaisiPacket) raw;
        RawBody rawBody = (RawBody) request.getBody();
        MemMsInfo memMsInfo = (MemMsInfo) SessionManager.getCurrent().getid(session);
        this.cmdLog.debug("校时包：" + IoBuffer.wrap(data).getHexDump() + " => " + memMsInfo.getId());
        byte[] msg = HandlerUtil.sendSchoolReply(session, request);
        this.cmdLog.debug("回复校时信息：" + IoBuffer.wrap(msg).getHexDump());
    }
}
