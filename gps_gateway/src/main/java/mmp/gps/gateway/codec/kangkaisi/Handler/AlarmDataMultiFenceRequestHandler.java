package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.AlarmDataMultiFenceBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class AlarmDataMultiFenceRequestHandler implements ICommandHandler {

    private Logger cnsle = Logger.getLogger("cnsle");
    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 39);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        KangkaisiPacket request = (KangkaisiPacket) raw;
        RawBody rawBody = (RawBody) request.getBody();
        AlarmDataMultiFenceBody alarmDataBody = new AlarmDataMultiFenceBody();
        alarmDataBody.from(rawBody.getRaw());
    }
}
