package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class InformationOnDemandRequestBody implements IPacket {

    private short type;
    private byte action;


    public short getType() {
        return this.type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public byte getAction() {
        return this.action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int size() {
        return 2;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.getUbyte();
        this.action = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.type);
        io.put(this.action);
        return io.array();
    }
}
