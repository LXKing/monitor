package mmp.gps.gateway.codec.phoenix;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class Phoenix808ProtocolCodecFactory implements ProtocolCodecFactory {

    private Phoenix808Decoder decoder = null;
    private Phoenix808Encoder encoder = new Phoenix808Encoder();


    public Phoenix808ProtocolCodecFactory(int bufferSize) {
        this.decoder = new Phoenix808Decoder(bufferSize);
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return this.encoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return this.decoder;
    }
}
