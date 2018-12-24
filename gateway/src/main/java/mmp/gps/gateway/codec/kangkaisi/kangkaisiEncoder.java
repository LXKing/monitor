package mmp.gps.gateway.codec.kangkaisi;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class kangkaisiEncoder implements ProtocolEncoder {

    private static Logger cnsle = Logger.getLogger("cnsle");


    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer.wrap((byte[]) message);
        out.write(buffer);
    }

    public void dispose(IoSession session) throws Exception {
    }
}
