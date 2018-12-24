package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;

public class ServerReplySchoolBody implements IPacket {

    private GpsTime time;


    public GpsTime getTime() {
        return this.time;
    }

    public void setTime(GpsTime time) {
        this.time = time;
    }

    public String toString() {
        return "ServerReplySchoolBody [time=" + this.time + "]";
    }

    public int size() {
        return 6;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.time = new GpsTime();
        this.time.from(io.getBytes(6));
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.time.array());
        return io.array();
    }
}
