package mmp.gps.gateway.test;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPClientHandler extends IoHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TCPClientHandler.class);
    private final byte[] values;


    public TCPClientHandler(byte[] values) {
        this.values = values;
    }

    public void sessionOpened(IoSession session) {
        session.write(this.values);
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        Object number = session.getAttribute("number");
        byte[] data = (byte[]) message;
        System.out.println("收到：" + IoBuffer.wrap(data).getHexDump());
    }
}
