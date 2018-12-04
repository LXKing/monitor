package mmp.gps.common.util;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 网络字节序IO操作类
 */
public class ByteIO {
    private ByteBuffer buffer;

    /**
     * 创建新的字节操作对象
     *
     * @param capacity
     */
    public ByteIO(int capacity) {
        this.buffer = ByteBuffer.allocate(capacity);
    }

    /**
     * 创建新的字节操作对象
     *
     * @param src
     */
    public ByteIO(byte[] src) {
        this.buffer = ByteBuffer.wrap(src);
    }

    /**
     * 使limit变为当前的position的值,position变为0
     */
    public Buffer flip() {
        return buffer.flip();
    }

    /**
     * 是否还有剩余数据
     *
     * @return 是否还有剩余数据
     */
    public boolean hasRemaining() {
        return buffer.hasRemaining();
    }

    /**
     * 获取当前操作位置
     *
     * @return
     */
    public int position() {
        return buffer == null ? -1 : buffer.position();
    }

    public void position(int index) {
        buffer.position(index);
    }

    public int limit() {
        return buffer.limit();
    }

    public Buffer limit(int newLimit) {
        return buffer.limit(newLimit);
    }

    public Buffer reset() {
        return buffer.reset();
    }

    public Buffer clear() {
        return buffer.clear();
    }

    /**
     * 获取网络字节序长度
     *
     * @return
     */
    public int size() {
        return buffer == null ? -1 : buffer.limit();
    }

    /**
     * 获取剩余数据长度
     */
    public int remaing() {
        return buffer.remaining();
    }

    /**
     * 获取数据到字节数组
     *
     * @param dst
     * @return
     */
    public ByteBuffer get(byte[] dst) {
        return buffer.get(dst);
    }

    /**
     * 获取数据到字节数组
     *
     * @param dst    目标数组
     * @param offset 开始位置
     * @param length 长度
     * @return
     */
    public ByteBuffer get(byte[] dst, int offset, int length) {
        return buffer.get(dst, offset, length);
    }

    /**
     * 获取当前位置有符号8位整数
     *
     * @return
     */
    public byte get() {
        return buffer.get();
    }

    /**
     * 获取指定位置有符号8位整数
     *
     * @param index 索引位置
     * @return
     */
    public byte get(int index) {
        return buffer.get(index);
    }

    /**
     * 获取当前位置无符号8位整数
     *
     * @return
     */
    public short getUbyte() {
        return (short) (get() & 0xff);
    }

    /**
     * 获取指定位置无符号8位整数
     *
     * @param index 索引位置
     * @return
     */
    public short getUbyte(int index) {
        return (short) (get(index) & 0xff);
    }

    /**
     * 从当前位置获取有符号16位整数
     *
     * @return
     */
    public short getShort() {
        return buffer.getShort();
    }

    /**
     * 从指定位置获取有符号16位整数
     *
     * @param index 索引位置
     * @return
     */
    public short getShort(int index) {
        return buffer.getShort(index);
    }

    /**
     * 从当前位置获取无符号16位整数
     *
     * @return
     */
    public int getUshort() {
        return getShort() & 0xffff;
    }

    /**
     * 从指定位置获取无符号16位整数
     *
     * @param index 索引位置
     * @return
     */
    public int getUshort(int index) {
        return getShort(index) & 0xffff;
    }

    /**
     * 从当前位置获取有符号24位整数
     *
     * @return
     */
    public int getMint() {
        byte b1 = get();
        byte b2 = get();
        byte b3 = get();
        int ret = b1 << 16 & 0xff0000 | b2 << 8 & 0xff00 | b3 & 0xff;
        // Check to see if the medium int is negative (high bit in b1 set)
        if ((b1 & 0x80) == 0x80) {
            // Make the the whole int negative
            ret |= 0xff000000;
        }
        return ret;
    }

    /**
     * 从指定位置获取有符号24位整数
     *
     * @param index 索引位置
     * @return
     */
    public int getMint(int index) {
        byte b1 = get(index);
        byte b2 = get(index + 1);
        byte b3 = get(index + 2);
        int ret = b1 << 16 & 0xff0000 | b2 << 8 & 0xff00 | b3 & 0xff;
        // Check to see if the medium int is negative (high bit in b1 set)
        if ((b1 & 0x80) == 0x80) {
            // Make the the whole int negative
            ret |= 0xff000000;
        }
        return ret;
    }

    /**
     * 从当前位置获取无符号24位整数
     *
     * @return
     */
    public int getUmint() {
        int b1 = getUbyte();
        int b2 = getUbyte();
        int b3 = getUbyte();
        return b1 << 16 | b2 << 8 | b3;
    }

    /**
     * 从指定位置获取无符号24位整数
     *
     * @param index 索引位置
     * @return
     */
    public int getUmint(int index) {
        int b1 = getUbyte(index);
        int b2 = getUbyte(index + 1);
        int b3 = getUbyte(index + 2);
        return b1 << 16 | b2 << 8 | b3;
    }

    /**
     * 从当前位置获取有符号32位整数
     *
     * @return
     */
    public int getInt() {
        return buffer.getInt();
    }

    /**
     * 从指定位置获取有符号32位整数
     *
     * @param index 索引位置
     * @return
     */
    public int getInt(int index) {
        return buffer.getInt(index);
    }

    /**
     * 从当前位置获取无符号32位整数
     *
     * @return
     */
    public long getUint() {
        return getInt() & 0xffffffffL;
    }

    /**
     * 从指定位置获取无符号32位整数
     *
     * @param index 索引位置
     * @return
     */
    public long getUint(int index) {
        return getInt(index) & 0xffffffffL;
    }

    /**
     * 从当前位置获取有符号64位整数
     *
     * @return
     */
    public long getLong() {
        return buffer.getLong();
    }

    /**
     * 从指定位置获取有符号64位整数
     *
     * @param index 索引位置
     * @return
     */
    public long getLong(int index) {
        return buffer.getLong(index);
    }

    /**
     * 从当前位置获取无符号64位整数
     *
     * @return
     */
    public BigInteger getUlong() {
        return BigInteger.valueOf(getLong()).and(new BigInteger("FFFFFFFFFFFFFFFF", 16));
    }

    /**
     * 从指定位置获取无符号64位整数
     *
     * @param index 索引位置
     * @return
     */
    public BigInteger getUlong(int index) {
        return BigInteger.valueOf(getLong(index)).and(new BigInteger("FFFFFFFFFFFFFFFF", 16));
    }

    /**
     * 获取指定长度的字节数组
     *
     * @param size 长度
     */
    public byte[] getBytes(int size) {
        byte[] tmp = new byte[size];
        get(tmp);
        return tmp;
    }

    /**
     * 获取剩余数据
     */
    public byte[] getRemaining() {
        if (buffer.remaining() <= 0)
            return null;
        byte[] remaining = new byte[buffer.remaining()];
        buffer.get(remaining);
        return remaining;
    }

    /**
     * 从当前位置写入字节数组
     *
     * @param src
     * @return
     */
    public ByteIO put(byte[] src) {
        if (src == null)
            return this;
        return put(src, 0, src.length);
    }

    /**
     * 从指定位置写入字节数组
     *
     * @param src    源数组
     * @param offset 索引位置
     * @param length 长度
     * @return
     */
    public ByteIO put(byte[] src, int offset, int length) {
        buffer.put(src, offset, length);
        return this;
    }

    /**
     * 从当前位置写入有符号8位整数
     *
     * @param value 值
     * @return
     */
    public ByteIO put(byte value) {
        buffer.put(value);
        return this;
    }

    /**
     * 从指定位置写入有符号8位整数
     *
     * @param index 索引位置
     * @param value 值
     * @return
     */
    public ByteIO put(int index, byte value) {
        buffer.put(index, value);
        return this;
    }

    /**
     * 从当前位置写入无符号8位整数
     *
     * @param value 值
     * @return
     */
    public ByteIO putUbyte(short value) {
        buffer.put((byte) (value & 0x00ff));
        return this;
    }

    /**
     * 从指定位置写入无符号8位整数
     *
     * @param index 索引
     * @param value
     * @return
     */
    public ByteIO putUbyte(int index, short value) {
        buffer.put(index, (byte) (value & 0x00ff));
        return this;
    }

    /**
     * 从当前位置写入有符号16位整数
     *
     * @param value
     * @return
     */
    public ByteIO putShort(short value) {
        buffer.putShort(value);
        return this;
    }

    /**
     * 从指定位置写入有符号16位整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putShort(int index, short value) {
        buffer.putShort(index, value);
        return this;
    }

    /**
     * 从当前位置写入无符号16位整数
     *
     * @param value
     * @return
     */
    public ByteIO putUshort(int value) {
        buffer.putShort((short) value);
        return this;
    }

    /**
     * 从指定位置写入无符号16位整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putUshort(int index, int value) {
        buffer.putShort(index, (short) value);
        return this;
    }

    /**
     * 从当前位置写入24位有符号整数
     *
     * @param value
     * @return
     */
    public ByteIO putMint(int value) {
        byte b1 = (byte) (value >> 16);
        byte b2 = (byte) (value >> 8);
        byte b3 = (byte) value;

        put(b1).put(b2).put(b3);

        return this;
    }

    /**
     * 从指定位置写入24位有符号整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putMint(int index, int value) {
        byte b1 = (byte) (value >> 16);
        byte b2 = (byte) (value >> 8);
        byte b3 = (byte) value;

        put(index, b1).put(index + 1, b2).put(index + 2, b3);
        return this;
    }

    /**
     * 从当前位置写入有符号32位整数
     *
     * @param value
     * @return
     */
    public ByteIO putInt(int value) {
        buffer.putInt(value);
        return this;
    }

    /**
     * 从指定位置写入有符号32位整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putInt(int index, int value) {
        buffer.putInt(index, value);
        return this;
    }

    /**
     * 从当前位置写入无符号32位整数
     *
     * @param value
     * @return
     */
    public ByteIO putUint(long value) {
        buffer.putInt((int) (value & 0x00000000ffffffff));
        return this;
    }

    /**
     * 从指定位置写入无符号32位整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putUint(int index, long value) {
        buffer.putInt(index, (int) (value & 0x00000000ffffffff));
        return this;
    }

    /**
     * 从当前位置写入有符号64位整数
     *
     * @param value
     * @return
     */
    public ByteIO putLong(long value) {
        buffer.putLong(value);
        return this;
    }

    /**
     * 从指定位置写入有符号64位整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putLong(int index, long value) {
        buffer.putLong(index, value);
        return this;
    }

    /**
     * 从当前位置写入无符号64位整数
     *
     * @param value
     * @return
     */
    public ByteIO putUlong(BigInteger value) {
        buffer.putLong(value.and(new BigInteger("ffffffffffffffff", 16)).longValue());
        return this;
    }

    /**
     * 从指定位置写入无符号64位整数
     *
     * @param index 索引位置
     * @param value
     * @return
     */
    public ByteIO putUlong(int index, BigInteger value) {
        buffer.putLong(index, value.and(new BigInteger("ffffffffffffffff", 16)).longValue());
        return this;
    }

    /**
     * 生成网络字节序
     *
     * @return
     */
    public byte[] array() {
        return buffer.array();
    }
}
