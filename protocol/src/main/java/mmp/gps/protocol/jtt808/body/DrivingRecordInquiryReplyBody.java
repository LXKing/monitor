package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class DrivingRecordInquiryReplyBody implements IPacket {

    private int responseSerialNumber;
    private short command;
    private byte[] data;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public short getCommand() {
        return this.command;
    }

    public void setCommand(short command) {
        this.command = command;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int size() {
        return 3 + (this.data == null ? 0 : this.data.length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.command = io.getUbyte();
        this.data = io.getRemaining();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.responseSerialNumber);
        io.putUbyte(this.command);
        io.put(this.data);
        return io.array();
    }
}
