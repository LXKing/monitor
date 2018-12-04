package mmp.gps.protocol.kangkaisi;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ByteIO {

    private ByteBuffer buffer;


    public Buffer flip() {
        return this.buffer.flip();
    }

    public boolean hasRemaining() {
        return this.buffer.hasRemaining();
    }

    public int position() {
        return this.buffer == null ? -1 : this.buffer.position();
    }

    public void position(int index) {
        this.buffer.position(index);
    }

    public int limit() {
        return this.buffer.limit();
    }

    public Buffer limit(int newLimit) {
        return this.buffer.limit(newLimit);
    }

    public Buffer reset() {
        return this.buffer.reset();
    }

    public Buffer clear() {
        return this.buffer.clear();
    }

    public int size() {
        return this.buffer == null ? -1 : this.buffer.limit();
    }

    public int remaing() {
        return this.buffer.remaining();
    }

    public ByteIO(int capacity) {
        this.buffer = ByteBuffer.allocate(capacity);
    }

    public ByteIO(byte[] src) {
        this.buffer = ByteBuffer.wrap(src);
    }

    public ByteBuffer get(byte[] dst) {
        return this.buffer.get(dst);
    }

    public ByteBuffer get(byte[] dst, int offset, int length) {
        return this.buffer.get(dst, offset, length);
    }

    public byte get() {
        return this.buffer.get();
    }

    public byte get(int index) {
        return this.buffer.get(index);
    }

    public short getUbyte() {
        return (short) (this.get() & 255);
    }

    public short getUbyte(int index) {
        return (short) (this.get(index) & 255);
    }

    public short getShort() {
        return this.buffer.getShort();
    }

    public short getShort(int index) {
        return this.buffer.getShort(index);
    }

    public int getUshort() {
        return this.getShort() & '\uffff';
    }

    public int getUshort(int index) {
        return this.getShort(index) & '\uffff';
    }

    public int getMint() {
        byte b1 = this.get();
        byte b2 = this.get();
        byte b3 = this.get();
        int ret = b1 << 16 & 16711680 | b2 << 8 & '\uff00' | b3 & 255;
        if ((b1 & 128) == 128) {
            ret |= -16777216;
        }

        return ret;
    }

    public int getMint(int index) {
        byte b1 = this.get(index);
        byte b2 = this.get(index + 1);
        byte b3 = this.get(index + 2);
        int ret = b1 << 16 & 16711680 | b2 << 8 & '\uff00' | b3 & 255;
        if ((b1 & 128) == 128) {
            ret |= -16777216;
        }

        return ret;
    }

    public int getUmint() {
        short b1 = this.getUbyte();
        short b2 = this.getUbyte();
        short b3 = this.getUbyte();
        return b1 << 16 | b2 << 8 | b3;
    }

    public int getUmint(int index) {
        short b1 = this.getUbyte(index);
        short b2 = this.getUbyte(index + 1);
        short b3 = this.getUbyte(index + 2);
        return b1 << 16 | b2 << 8 | b3;
    }

    public int getInt() {
        return this.buffer.getInt();
    }

    public int getInt(int index) {
        return this.buffer.getInt(index);
    }

    public long getUint() {
        return (long) this.getInt() & 4294967295L;
    }

    public long getUint(int index) {
        return (long) this.getInt(index) & 4294967295L;
    }

    public long getLong() {
        return this.buffer.getLong();
    }

    public long getLong(int index) {
        return this.buffer.getLong(index);
    }

    public BigInteger getUlong() {
        return BigInteger.valueOf(this.getLong()).and(new BigInteger("FFFFFFFFFFFFFFFF", 16));
    }

    public BigInteger getUlong(int index) {
        return BigInteger.valueOf(this.getLong(index)).and(new BigInteger("FFFFFFFFFFFFFFFF", 16));
    }

    public byte[] getBytes(int size) {
        byte[] tmp = new byte[size];
        this.get(tmp);
        return tmp;
    }

    public byte[] getRemaining() {
        if (this.buffer.remaining() <= 0) {
            return null;
        } else {
            byte[] remaining = new byte[this.buffer.remaining()];
            this.buffer.get(remaining);
            return remaining;
        }
    }

    public ByteIO put(byte[] src) {
        return src == null ? this : this.put(src, 0, src.length);
    }

    public ByteIO put(byte[] src, int offset, int length) {
        this.buffer.put(src, offset, length);
        return this;
    }

    public ByteIO put(byte value) {
        this.buffer.put(value);
        return this;
    }

    public ByteIO put(int index, byte value) {
        this.buffer.put(index, value);
        return this;
    }

    public ByteIO putUbyte(short value) {
        this.buffer.put((byte) (value & 255));
        return this;
    }

    public ByteIO putUbyte(int index, short value) {
        this.buffer.put(index, (byte) (value & 255));
        return this;
    }

    public ByteIO putShort(short value) {
        this.buffer.putShort(value);
        return this;
    }

    public ByteIO putShort(int index, short value) {
        this.buffer.putShort(index, value);
        return this;
    }

    public ByteIO putUshort(int value) {
        this.buffer.putShort((short) value);
        return this;
    }

    public ByteIO putUshort(int index, int value) {
        this.buffer.putShort(index, (short) value);
        return this;
    }

    public ByteIO putMint(int value) {
        byte b1 = (byte) (value >> 16);
        byte b2 = (byte) (value >> 8);
        byte b3 = (byte) value;
        this.put(b1).put(b2).put(b3);
        return this;
    }

    public ByteIO putMint(int index, int value) {
        byte b1 = (byte) (value >> 16);
        byte b2 = (byte) (value >> 8);
        byte b3 = (byte) value;
        this.put(index, b1).put(index + 1, b2).put(index + 2, b3);
        return this;
    }

    public ByteIO putInt(int value) {
        this.buffer.putInt(value);
        return this;
    }

    public ByteIO putInt(int index, int value) {
        this.buffer.putInt(index, value);
        return this;
    }

    public ByteIO putUint(long value) {
        this.buffer.putInt((int) (value & -1L));
        return this;
    }

    public ByteIO putUint(int index, long value) {
        this.buffer.putInt(index, (int) (value & -1L));
        return this;
    }

    public ByteIO putLong(long value) {
        this.buffer.putLong(value);
        return this;
    }

    public ByteIO putLong(int index, long value) {
        this.buffer.putLong(index, value);
        return this;
    }

    public ByteIO putUlong(BigInteger value) {
        this.buffer.putLong(value.and(new BigInteger("ffffffffffffffff", 16)).longValue());
        return this;
    }

    public ByteIO putUlong(int index, BigInteger value) {
        this.buffer.putLong(index, value.and(new BigInteger("ffffffffffffffff", 16)).longValue());
        return this;
    }

    public byte[] array() {
        return this.buffer.array();
    }
}
