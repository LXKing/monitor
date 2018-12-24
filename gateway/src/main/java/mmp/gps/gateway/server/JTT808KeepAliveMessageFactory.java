package mmp.gps.gateway.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/*
* UDP
* */
public class JTT808KeepAliveMessageFactory implements KeepAliveMessageFactory {

    public boolean isRequest(IoSession session, Object message) {
        return false;
    }

    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    public Object getRequest(IoSession session) {
        if (session.isBothIdle()) {
            session.close(true);
        }

        return null;
    }

    public Object getResponse(IoSession session, Object request) {
        return null;
    }
}
