package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.domain.gateway.InstructStatusRequest;
import mmp.gps.gateway.codec.kangkaisi.beans.MemMsInfo;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.InstructInfo;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket79;
import mmp.gps.protocol.kangkaisi.body.OnlineInstructionReplyBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class TerminalcOnlineInstructionReplyHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 33);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        try {
            KangkaisiPacket79 e = (KangkaisiPacket79) raw;
            RawBody rawBody = (RawBody) e.getBody();
            MemMsInfo memMsInfo = (MemMsInfo) SessionManager.getCurrent().getid(session);
            OnlineInstructionReplyBody onlineInstructionReplyBody = new OnlineInstructionReplyBody();
            onlineInstructionReplyBody.from(rawBody.getRaw());
            this.cmdLog.debug("在线指令：" + IoBuffer.wrap(data).getHexDump() + "回复：" + onlineInstructionReplyBody + " =>" + memMsInfo.getId());
            ReplyValidator instructs = (ReplyValidator) session.getAttribute("instructs");
//         String key = memMsInfo.getId() + 263;
            String key = memMsInfo.getId() + onlineInstructionReplyBody.getServerFlag();
            this.cmdLog.debug("key ：" + key);
            if (instructs == null) {
                this.cmdLog.debug("instructs==null");
            }

            InstructInfo info = instructs.get(key);
            this.cmdLog.debug("回复：" + info.getCommand() + " ==>" + info.getPlateSerialNumber());
            if (info != null) {
                instructs.remove(key);
                DataSender.getCurrent().send(new InstructStatusRequest(info.getPlateSerialNumber(), memMsInfo.getId(), onlineInstructionReplyBody.getContent()));
            }
        } catch (Exception var11) {
            ExcepPrinter.print(var11);
        }

    }
}
