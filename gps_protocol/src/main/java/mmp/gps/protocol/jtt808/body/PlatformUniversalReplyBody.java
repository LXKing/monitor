package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class PlatformUniversalReplyBody implements IPacket {

    private int responseSerialNumber;
    private int responseCommand;
    private byte result;


    public int getResponseSerialNumber() {
        return this.responseSerialNumber;
    }

    public void setResponseSerialNumber(int responseSerialNumber) {
        this.responseSerialNumber = responseSerialNumber;
    }

    public int getResponseCommand() {
        return this.responseCommand;
    }

    public void setResponseCommand(int responseCommand) {
        this.responseCommand = responseCommand;
    }

    public byte getResult() {
        return this.result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public int size() {
        return 5;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.responseSerialNumber = io.getUshort();
        this.responseCommand = io.getUshort();
        this.result = io.get();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.responseSerialNumber);
        io.putUshort(this.responseCommand);
        io.put(this.result);
        return io.array();
    }
}
