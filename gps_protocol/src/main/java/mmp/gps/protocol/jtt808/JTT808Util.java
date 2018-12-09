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
        // 从消息头开始，同后一字节异或，直到校验码前一个字节，占用一个字节
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

        // ByteBuffer类是在Java NIO中常常使用的一个缓冲区类
        // 缓冲区(Buffer)就是在内存中预留指定大小的存储空间用来对输入/输出(I/O)的数据作临时存储
        // 减少实际的物理读写次数
        // 缓冲区在创建时就被分配内存，这块内存区域一直被重用，可以减少动态分配和回收内存的次数
        // 缓冲区直接为通道(Channel)服务，写入数据到通道或从通道读取数据


        // 缓冲区的数据会存放在data数组中，data数组或buff缓冲区任何一方中数据的改动都会影响另一方
        ByteBuffer in = ByteBuffer.wrap(data);

        // allocate()方法分配了一段内存空间，作为缓存，allocate方法对缓存自动清零
        ByteBuffer out = ByteBuffer.allocate(data.length);

        // remaining() return limit - position;
        // 返回limit和position之间相对位置差
        while (in.remaining() > 0) {
            // 相对读，从position位置读取一个byte，并将position+1，为下次读写作准备
            byte result = in.get();
            // 0x7d 标识位转义还原
            if (result == 125) {
                if (in.remaining() > 0) {
                    byte right = in.get();
                    // 0x7d 后紧跟一个 0x02
                    if (right == 2) {
                        // 相对写，向position的位置写入一个byte，并将position+1，为下次读写作准备
                        // 0x7e 标识位
                        out.put((byte) 126);
                    }
                    // 0x7d 后紧跟一个 0x01
                    else if (right == 1) {
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
        // 读取
        byte[] result1 = new byte[out.limit()];
        out.get(result1);
        return result1;
    }

}
