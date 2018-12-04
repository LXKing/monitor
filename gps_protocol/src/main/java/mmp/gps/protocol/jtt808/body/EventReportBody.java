package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.contract.IPacket;

public class EventReportBody implements IPacket {

    private short id;


    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public int size() {
        return 1;
    }

    public void from(byte[] src) {
        this.id = (short) (src[0] & 255);
    }

    public byte[] array() {
        return new byte[]{(byte) (this.id & 255)};
    }
}
