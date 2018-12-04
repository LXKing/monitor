package mmp.gps.gateway.handler;

import mmp.gps.gateway.codec.RawDataParsers;
import mmp.gps.gateway.codec.jtt808.Handler.DataFrames;
import mmp.gps.gateway.contract.IRawDataParser;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.gateway.statistics.OnlineStatistics;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class JTT808Handler extends IoHandlerAdapter {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private int netKind;


    public JTT808Handler(int netKind, int idleTime) {
        this.netKind = netKind;
    }

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.close(true);
    }

    public void sessionCreated(IoSession session) throws Exception {
        ReplyValidator instructs = new ReplyValidator();
        session.setAttribute("netkind", Integer.valueOf(this.netKind));
        session.setAttribute("instructs", instructs);
        session.setAttribute("protocolkind", Integer.valueOf(1));
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        Object number = session.getAttribute("number");
        byte[] data = (byte[]) message;
        if (number == null) {
            cnsle.debug("收到：" + IoBuffer.wrap(data).getHexDump());
        } else {
            cnsle.debug("收到：" + IoBuffer.wrap(data).getHexDump() + "=>" + number);
        }

        data = JTT808Util.antisense(data);
        JTT808Packet packet = new JTT808Packet();
        packet.setBody(new RawBody());
        packet.from(data);
        if (number == null) {
            number = packet.getNumber();
            session.setAttribute("number", number);
            OnlineStatistics.offlineCount(number);
            cnsle.debug("JTT808连接" + session.getRemoteAddress().toString() + "=>" + packet.getNumber());
        }

        SessionManager.getCurrent().put(number, session);
        // 分包
        if (packet.isPaging()) {
            DataFrames.put(packet, data);
        } else {
            // 完整包
            IRawDataParser parser = RawDataParsers.get(1);
            if (parser != null) {
                parser.parse(session, packet, data);
            }
        }
    }

    public void messageSent(IoSession session, Object message) throws Exception {
    }

    public void sessionClosed(IoSession session) throws Exception {
        Object number = session.getAttribute("number");
        if (number != null) {
            cnsle.debug("JTT808断开" + session.getRemoteAddress().toString() + "=>" + number);
            IoSession now = SessionManager.getCurrent().get(number);
            if (now == session) {
                SessionManager.getCurrent().remove(number);
                OnlineStatistics.onlineCount(number);
            }

        }
    }

    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(true);
    }
}
