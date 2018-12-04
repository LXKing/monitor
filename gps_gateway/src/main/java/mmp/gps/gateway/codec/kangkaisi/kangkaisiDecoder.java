package mmp.gps.gateway.codec.kangkaisi;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class kangkaisiDecoder extends CumulativeProtocolDecoder {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private int bufferSize;


    public kangkaisiDecoder(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        in.mark();
        IoBuffer buffer = IoBuffer.allocate(this.bufferSize);

        byte readover;
        byte data;
        while (in.remaining() > 0) {
            readover = in.get();
            if (readover == 120 || readover == 121) {
                in.mark();
                in.remaining();
                data = in.get();
                if (data == readover) {
                    buffer.put(readover);
                    buffer.put(data);
                    break;
                }

                in.reset();
            }
        }

        while (in.remaining() > 0) {
            readover = in.get();
            if (readover != 120 && readover != 121) {
                buffer.put(readover);
                break;
            }

            in.mark();
            in.remaining();
            data = in.get();
            if (data != readover) {
                buffer.put(readover);
                in.reset();
            }
        }

        boolean readover1 = false;

        while (in.remaining() > 0) {
            data = in.get();
            if (data == 13) {
                buffer.put(data);
                in.mark();
                in.remaining();
                byte item2 = in.get();
                if (item2 == 10) {
                    buffer.put(item2);
                    readover1 = true;
                    break;
                }

                in.reset();
            } else {
                buffer.put(data);
            }
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
