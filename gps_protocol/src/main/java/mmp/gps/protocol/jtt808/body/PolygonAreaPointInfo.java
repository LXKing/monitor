package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class PolygonAreaPointInfo implements IPacket {

    private long lat;
    private long lng;


    public long getLat() {
        return this.lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return this.lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public int size() {
        return 8;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.lat = io.getUint();
        this.lng = io.getUint();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.lat);
        io.putUint(this.lng);
        return io.array();
    }
}
