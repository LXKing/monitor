package mmp.gps.gateway.codec.phoenix;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class Phoenix808Decoder extends CumulativeProtocolDecoder {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private int bufferSize;


    public Phoenix808Decoder(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        in.mark();
        IoBuffer buffer = IoBuffer.allocate(this.bufferSize);

        byte readover;
        while (in.remaining() > 0) {
            readover = in.get();
            if (readover == 126) {
                buffer.put(readover);
                break;
            }
        }

        while (in.remaining() > 0) {
            readover = in.get();
            if (readover != 126) {
                buffer.put(readover);
                break;
            }
        }

        boolean readover1 = false;

        while (in.remaining() > 0) {
            byte data = in.get();
            if (data == 126) {
                buffer.put(data);
                readover1 = true;
                break;
            }

            buffer.put(data);
        }

        if (readover1) {
            buffer.flip();
            byte[] data1 = new byte[buffer.limit()];
            buffer.get(data1);
            out.write(data1);
            return in.remaining() > 0;
        } else {
            in.reset();
            return false;
        }
    }
}
