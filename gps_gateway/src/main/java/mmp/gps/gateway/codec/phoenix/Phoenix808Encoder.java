package mmp.gps.gateway.codec.phoenix;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class Phoenix808Encoder implements ProtocolEncoder {

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer.wrap((byte[]) message);
        out.write(buffer);
    }

    public void dispose(IoSession session) throws Exception {
    }
}
