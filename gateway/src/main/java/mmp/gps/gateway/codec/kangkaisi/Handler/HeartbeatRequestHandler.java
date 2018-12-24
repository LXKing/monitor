package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.codec.kangkaisi.beans.MemMsInfo;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.HeartbeatBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class HeartbeatRequestHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 19);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        MemMsInfo memMsInfo = (MemMsInfo) SessionManager.getCurrent().getid(session);
        KangkaisiPacket request = (KangkaisiPacket) raw;
        RawBody rawBody = (RawBody) request.getBody();
        HeartbeatBody heartbeatBody = new HeartbeatBody();
        heartbeatBody.from(rawBody.getRaw());
        this.cmdLog.debug("心跳：" + IoBuffer.wrap(data).getHexDump() + "---数据---" + heartbeatBody + " =>" + memMsInfo.getId());
        memMsInfo.setGSM(heartbeatBody.getGSM());
        memMsInfo.setVoltageLevel(heartbeatBody.getVoltageLevel());
        byte[] msg = HandlerUtil.sendHeartbeatReply(session, request);
        this.cmdLog.debug("回复心跳：" + IoBuffer.wrap(msg).getHexDump());
    }
}
