package mmp.gps.gateway.handler;

import mmp.gps.gateway.codec.RawDataParsers;
import mmp.gps.gateway.contract.IRawDataParser;
import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Tsc808Handler extends IoHandlerAdapter {

    static Logger cnsleLog = Logger.getLogger("cnsle");
    static Logger cmLogger = Logger.getLogger("cmd");
    int poolSize = Runtime.getRuntime().availableProcessors();
    private int netKind;


    public Tsc808Handler(int netKind, int idleTime) {
        this.netKind = netKind;
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        try {
            if (message instanceof IoBuffer) {
                IoBuffer e = (IoBuffer) message;
                byte[] tmp = e.array();
                int t = Index(tmp, (byte) 126);
                byte[] data = new byte[t + 1];
                System.arraycopy(tmp, 0, data, 0, t + 1);
                data = JTT808Util.antisense(data);
                cnsleLog.debug("收到：" + IoBuffer.wrap(data).getHexDump());
                JTT808Packet packet = new JTT808Packet();
                packet.setBody(new RawBody());
                packet.from(data);
                IRawDataParser parser = RawDataParsers.get(3);
                if (parser == null) {
                    return;
                }

                parser.parse(session, packet, data);
            }
        } catch (Exception var9) {
            ExcepPrinter.print(var9);
        }

    }

    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    public static int Index(byte[] Arr, byte key) {
        for (int i = 1; i < Arr.length; ++i) {
            if (Arr[i] == key) {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        byte[] data = new byte[]{(byte) 126, (byte) 2, (byte) 0, (byte) 0, (byte) 49, (byte) 2, (byte) 23, (byte) 80, (byte) 1, (byte) 0, (byte) 1, (byte) 6, (byte) -128, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 97, (byte) -106, (byte) -15};
        int i = Index(data, (byte) -15);
        System.out.println("Index " + i + data.length);
    }
}
