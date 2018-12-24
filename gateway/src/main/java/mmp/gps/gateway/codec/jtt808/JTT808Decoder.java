package mmp.gps.gateway.codec.jtt808;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class JTT808Decoder extends CumulativeProtocolDecoder {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private int bufferSize;


    public JTT808Decoder(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        in.mark();
        IoBuffer buffer = IoBuffer.allocate(this.bufferSize);

        byte readover;
        while (in.remaining() > 0) {
            readover = in.get();
            // 0x7e 标识位 头
            if (readover == 126) {
                buffer.put(readover);
                // 跳出循环
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
            // 0x7e 标识位 尾
            if (data == 126) {
                buffer.put(data);
                // 到尾了
                readover1 = true;
                break;
            }

            buffer.put(data);
        }

        if (readover1) {
            /*
                limit = position;position = 0;mark = -1;
                翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，
                翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态
            */
            buffer.flip();
            byte[] data1 = new byte[buffer.limit()];
            // 从position位置开始相对读，读length个byte，并写入dst下标从offset到offset+length的区域
            buffer.get(data1);
            out.write(data1);
            return in.remaining() > 0;
        } else {
            in.reset();
            return false;
        }
    }
}
