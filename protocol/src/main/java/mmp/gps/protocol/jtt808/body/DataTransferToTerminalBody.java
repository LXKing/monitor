package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class DataTransferToTerminalBody implements IPacket {

    private short type;
    private byte[] data;


    public short getType() {
        return this.type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int size() {
        return 1 + (this.data == null ? 0 : this.data.length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.getUbyte();
        this.data = io.getRemaining();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.type);
        io.put(this.data);
        return io.array();
    }
}
