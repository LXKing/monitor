package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.InstructInfo;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.protocol.jtt808.JTT808Packet;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class DriverInfoReportHandler implements ICommandHandler {

    private static Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Integer.valueOf(1794);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet report = (JTT808Packet) raw;
        byte[] msg = HandlerUtil.sendPlatformUniversalReply(session, report);
        cmdLog.debug("回复驾驶员信息上报：" + IoBuffer.wrap(msg).getHexDump());

        try {
            ReplyValidator ex = (ReplyValidator) session.getAttribute("instructs");
            String key = report.getNumber() + 1794;
            InstructInfo info = ex.get(key);
            if (info != null) {
                ex.remove(key);
                DataSender.getCurrent().send(report.getNumber(), 1, info.getPlateSerialNumber(), info.getCommand(), data);
            } else {
                DataSender.getCurrent().send(report.getNumber(), 1, "", "", data);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }
}
