package mmp.gps.gateway.handler;

import mmp.gps.gateway.codec.RawDataParsers;
import mmp.gps.gateway.codec.kangkaisi.beans.MemMsInfo;
import mmp.gps.gateway.contract.IRawDataParser;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.gateway.statistics.OnlineStatistics;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket79;
import mmp.gps.protocol.kangkaisi.body.LoginBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class kangkaisiHandler extends IoHandlerAdapter {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private int netKind;


    public kangkaisiHandler(int netKind, int idleTime) {
        this.netKind = netKind;
    }

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.close(true);
    }

    public void sessionCreated(IoSession session) throws Exception {
        ReplyValidator instructs = new ReplyValidator();
        session.setAttribute("netkind", Integer.valueOf(this.netKind));
        session.setAttribute("instructs", instructs);
        session.setAttribute("protocolkind", Integer.valueOf(2));
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        Object number = session.getAttribute("number");
        byte[] data = (byte[]) message;
        if (number == null) {
            cnsle.debug("收到：" + IoBuffer.wrap(data).getHexDump());
        } else {
            cnsle.debug("收到：" + IoBuffer.wrap(data).getHexDump() + "=>" + number);
        }

        IRawDataParser parser1;
        if (data[0] == 120) {
            KangkaisiPacket packet = null;

            try {
                packet = new KangkaisiPacket();
                packet.setBody(new RawBody());
                packet.from(data);
            } catch (Exception var8) {

            }

            if (packet.getState() == 1) {
                if (number == null) {
                    RawBody parser = (RawBody) packet.getBody();
                    if (packet.getProtocolNumber() == 1) {
                        LoginBody loginBody = new LoginBody();
                        loginBody.from(parser.getRaw());
                        String number1 = loginBody.getNumberID().substring(3);
                        session.setAttribute("number", number1);
                        SessionManager.getCurrent().put(number1, session);
                        OnlineStatistics.offlineCount(number1);
                    }

                    cnsle.debug("康凯斯连接" + session.getRemoteAddress().toString() + "=>" + packet.getSerialNumber());
                }

                parser1 = RawDataParsers.get(2);
                if (parser1 == null) {
                    return;
                }

                parser1.parse(session, packet, data);
            } else {
                cnsle.debug("数据不完整");
            }
        } else if (data[0] == 121) {
            KangkaisiPacket79 packet1 = new KangkaisiPacket79();
            packet1.setBody(new RawBody());
            packet1.from(data);
            parser1 = RawDataParsers.get(2);
            if (parser1 == null) {
                return;
            }

            parser1.parse(session, packet1, data);
        }

    }

    public void messageSent(IoSession session, Object message) throws Exception {
    }

    public void sessionClosed(IoSession session) throws Exception {
        Object number = session.getAttribute("number");
        if (number != null) {
            cnsle.debug("康凯斯断开" + session.getRemoteAddress().toString() + "=>" + number);
            IoSession now = SessionManager.getCurrent().get(number);
            if (now == session) {
                SessionManager.getCurrent().remove(number);
                OnlineStatistics.onlineCount(number);
            }

        }
    }

    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        MemMsInfo memMsInfo = (MemMsInfo) SessionManager.getCurrent().getid(session);
        cnsle.debug("会话空闲断开 =>" + memMsInfo.getId());
        session.close(true);
    }
}
