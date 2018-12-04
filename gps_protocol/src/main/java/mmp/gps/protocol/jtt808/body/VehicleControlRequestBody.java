package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.contract.IPacket;

public class VehicleControlRequestBody implements IPacket {

    private short flag;


    public short getFlag() {
        return this.flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public int size() {
        return 1;
    }

    public void from(byte[] src) {
        this.flag = (short) (src[0] & 255);
    }

    public byte[] array() {
        return new byte[]{(byte) (this.flag & 255)};
    }
}
