package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

public class OnlineInstructionReplyBody implements IPacket {

    private long serverFlag;
    private short code;
    private String content;


    public long getServerFlag() {
        return this.serverFlag;
    }

    public void setServerFlag(long serverFlag) {
        this.serverFlag = serverFlag;
    }

    public short getCode() {
        return this.code;
    }

    public void setCode(short code) {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "OnlineInstructionReplyBody [ServerFlag=" + this.serverFlag + ", Code=" + this.code + ", Content=" + this.content + "]";
    }

    public int size() {
        byte[] numbers = null;
        if (this.code == 1) {
            numbers = this.content.getBytes();
        }

        return 5 + numbers.length;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.serverFlag = io.getUint();
        this.code = io.getUbyte();
        byte[] bi = io.getBytes(src.length - 1 - 4);
        this.content = new String(bi, 0, bi.length);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.serverFlag);
        io.putUbyte(this.code);
        io.put(this.content.getBytes());
        return io.array();
    }
}
