package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class QuestionReplyBody implements IPacket {

    private int responseSerialNumber;
    private short answerId;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public short getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(short answerId) {
        this.answerId = answerId;
    }

    public int size() {
        return 3;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.answerId = io.getUbyte();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.responseSerialNumber);
        io.putUbyte(this.answerId);
        return data;
    }
}
