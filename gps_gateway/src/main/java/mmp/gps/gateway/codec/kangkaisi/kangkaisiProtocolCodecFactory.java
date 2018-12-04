package mmp.gps.gateway.codec.kangkaisi;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class kangkaisiProtocolCodecFactory implements ProtocolCodecFactory {

    private kangkaisiDecoder decoder = null;
    private kangkaisiEncoder encoder = new kangkaisiEncoder();


    public kangkaisiProtocolCodecFactory(int bufferSize) {
        this.decoder = new kangkaisiDecoder(bufferSize);
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return this.encoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return this.decoder;
    }
}
