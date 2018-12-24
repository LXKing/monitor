package mmp.gps.gateway.codec.jtt808;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class JTT808ProtocolCodecFactory implements ProtocolCodecFactory {

    private JTT808Decoder decoder = null;
    private JTT808Encoder encoder = new JTT808Encoder();


    public JTT808ProtocolCodecFactory(int bufferSize) {
        this.decoder = new JTT808Decoder(bufferSize);
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return this.encoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return this.decoder;
    }
}
