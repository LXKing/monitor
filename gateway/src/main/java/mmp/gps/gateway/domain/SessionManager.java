package mmp.gps.gateway.domain;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

public class SessionManager {

    ConcurrentHashMap sessions = new ConcurrentHashMap();
    ConcurrentHashMap sessionid = new ConcurrentHashMap();
    private static SessionManager sessionManager = new SessionManager();


    public static SessionManager getCurrent() {
        return sessionManager;
    }

    public IoSession get(Object number) {
        return (IoSession) this.sessions.get(number);
    }

    public void put(Object number, IoSession session) {
        this.sessions.put(number, session);
    }

    public void putSession(IoSession session, Object number) {
        this.sessionid.put(session, number);
    }

    public Object getid(IoSession session) {
        return this.sessionid.get(session);
    }

    public void remove(Object number) {
        this.sessions.remove(number);
    }
}
