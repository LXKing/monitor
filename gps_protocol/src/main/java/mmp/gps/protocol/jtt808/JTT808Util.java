package mmp.gps.protocol.jtt808;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class JTT808Util {

    private static AtomicInteger serialNumber = new AtomicInteger(65536);


    public static int getSerialNumber() {
        int index = serialNumber.incrementAndGet();
        if (index > '\uffff') {
            index = serialNumber.getAndSet(0);
        }

        return index;
    }

    public static void xor(byte[] raw) {
        byte A = 0;
        int length = raw.length - 2;

        for (int i = 1; i < length; ++i) {
            A ^= raw[i];
        }

        raw[length] = A;
    }

    public static byte[] escape(byte[] data) {
        int length = data.length;
        ByteBuffer buffer = ByteBuffer.allocate(length * 2);
        buffer.put(data[0]);

        for (int raw = 1; raw < length - 1; ++raw) {
            byte item = data[raw];
            if (item == 126) {
                buffer.put((byte) 125);
                buffer.put((byte) 2);
            } else if (item == 125) {
                buffer.put(item);
                buffer.put((byte) 1);
            } else {
                buffer.put(item);
            }
        }

        buffer.put(data[length - 1]);
        buffer.flip();
        byte[] var5 = new byte[buffer.limit()];
        buffer.get(var5);
        return var5;
    }

    public static byte[] antisense(byte[] data) {
        ByteBuffer in = ByteBuffer.wrap(data);
        ByteBuffer out = ByteBuffer.allocate(data.length);

        while (in.remaining() > 0) {
            byte result = in.get();
            if (result == 125) {
                if (in.remaining() > 0) {
                    byte right = in.get();
                    if (right == 2) {
                        out.put((byte) 126);
                    } else if (right == 1) {
                        out.put((byte) 125);
                    } else {
                        out.put(result);
                        out.put(right);
                    }
                } else {
                    out.put(result);
                }
            } else {
                out.put(result);
            }
        }

        out.flip();
        byte[] result1 = new byte[out.limit()];
        out.get(result1);
        return result1;
    }

}
