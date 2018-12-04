package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class Locate implements IPacket {

    private int lng;
    private int lat;
    private short alt;


    public int getLng() {
        return this.lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public int getLat() {
        return this.lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public short getAlt() {
        return this.alt;
    }

    public void setAlt(short alt) {
        this.alt = alt;
    }

    public int size() {
        return 10;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.lng = io.getInt();
        this.lat = io.getInt();
        this.alt = io.getShort();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putInt(this.lng);
        io.putInt(this.lat);
        io.putShort(this.alt);
        return data;
    }
}
