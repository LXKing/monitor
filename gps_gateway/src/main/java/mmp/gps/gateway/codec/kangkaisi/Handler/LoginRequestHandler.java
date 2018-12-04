package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.codec.kangkaisi.beans.MemMsInfo;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.TerminalAuthenticationRequestBody;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.LoginBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class LoginRequestHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 1);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        KangkaisiPacket request = (KangkaisiPacket) raw;
        RawBody rawBody = (RawBody) request.getBody();
        LoginBody loginBody = new LoginBody();
        loginBody.from(rawBody.getRaw());
        String NumberID = loginBody.getNumberID().substring(3);
        MemMsInfo memMsInfo = new MemMsInfo();
        memMsInfo.setId(NumberID);
        memMsInfo.setTcp(session);
        this.cmdLog.debug("登录：" + IoBuffer.wrap(data).getHexDump() + "---数据---" + loginBody + " =>" + NumberID);
        SessionManager.getCurrent().putSession(session, memMsInfo);
        byte[] msg = HandlerUtil.sendPlatformUniversalReply(session, request);
        this.cmdLog.debug("回复登录：" + IoBuffer.wrap(msg).getHexDump());

        try {
            TerminalAuthenticationRequestBody e = new TerminalAuthenticationRequestBody();
            e.setAuthenticationCode(NumberID);
            JTT808Packet reply = new JTT808Packet(e);
            reply.setBody(e);
            reply.setCommand(258);
            reply.setSerialNumber(JTT808Util.getSerialNumber());
            reply.setNumber(NumberID);
            byte[] datamsg = reply.array();
            datamsg = JTT808Util.antisense(datamsg);
            JTT808Packet packet = new JTT808Packet();
            packet.setBody(new mmp.gps.protocol.jtt808.body.RawBody());
            packet.from(datamsg);
            this.cmdLog.debug("转换：" + IoBuffer.wrap(datamsg).getHexDump() + " -------" + packet);
            DataSender.getCurrent().send(NumberID, 1, "", "", datamsg);
        } catch (Exception var14) {
            ExcepPrinter.print(var14);
        }

    }
}
