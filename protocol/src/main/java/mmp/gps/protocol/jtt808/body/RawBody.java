package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.contract.IPacket;

public class RawBody implements IPacket {

    private byte[] raw;


    public byte[] getRaw() {
        return this.raw;
    }

    public void setRaw(byte[] raw) {
        this.raw = raw;
    }

    public int size() {
        return this.raw == null ? 0 : this.raw.length;
    }

    public void from(byte[] src) {
        this.raw = src;
    }

    public byte[] array() {
        return this.raw;
    }
}
