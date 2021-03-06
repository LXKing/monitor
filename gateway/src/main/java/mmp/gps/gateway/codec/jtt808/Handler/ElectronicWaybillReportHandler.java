package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.protocol.jtt808.JTT808Packet;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class ElectronicWaybillReportHandler implements ICommandHandler {

    private static Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Integer.valueOf(1793);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        JTT808Packet report = (JTT808Packet) raw;
        byte[] msg = HandlerUtil.sendPlatformUniversalReply(session, report);
        cmdLog.debug("回复电子运单报告：" + IoBuffer.wrap(msg).getHexDump());
        DataSender.getCurrent().send(report.getNumber(), 1, "", "", data);
    }
}
