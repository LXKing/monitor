package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class RectangularAreaInfo implements IPacket {

    private long id;
    private int flag;
    private long ulLat;
    private long ulLng;
    private long brLat;
    private long brLng;
    private GpsTime startTime;
    private GpsTime endTime;
    private int maxSpeed;
    private short overSpeedSeconds;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getUlLat() {
        return this.ulLat;
    }

    public void setUlLat(long ulLat) {
        this.ulLat = ulLat;
    }

    public long getUlLng() {
        return this.ulLng;
    }

    public void setUlLng(long ulLng) {
        this.ulLng = ulLng;
    }

    public long getBrLat() {
        return this.brLat;
    }

    public void setBrLat(long brLat) {
        this.brLat = brLat;
    }

    public long getBrLng() {
        return this.brLng;
    }

    public void setBrLng(long brLng) {
        this.brLng = brLng;
    }

    public GpsTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(GpsTime startTime) {
        this.startTime = startTime;
    }

    public GpsTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(GpsTime endTime) {
        this.endTime = endTime;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public short getOverSpeedSeconds() {
        return this.overSpeedSeconds;
    }

    public void setOverSpeedSeconds(short overSpeedSeconds) {
        this.overSpeedSeconds = overSpeedSeconds;
    }

    public int size() {
        int size = 22;
        if ((this.flag & 1) == 1) {
            size += 12;
        }

        if ((this.flag & 2) == 2) {
            size += 3;
        }

        return size;
    }

    public void from(ByteIO io) {
        this.id = io.getUint();
        this.flag = io.getUshort();
        this.ulLat = io.getUint();
        this.ulLng = io.getUint();
        this.brLat = io.getUint();
        this.brLng = io.getUint();
        if ((this.flag & 1) == 1) {
            this.startTime = new GpsTime();
            this.startTime.from(io.getBytes(6));
            this.endTime = new GpsTime();
            this.endTime.from(io.getBytes(6));
        }

        if ((this.flag & 2) == 2) {
            this.maxSpeed = io.getUshort();
            this.overSpeedSeconds = io.getUbyte();
        }

    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.from(io);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.id);
        io.putUshort(this.flag);
        io.putUint(this.ulLat);
        io.putUint(this.ulLng);
        io.putUint(this.brLat);
        io.putUint(this.brLng);
        if ((this.flag & 1) == 1) {
            io.put(this.startTime.array());
            io.put(this.endTime.array());
        }

        if ((this.flag & 2) == 2) {
            io.putUshort(this.maxSpeed);
            io.putUbyte(this.overSpeedSeconds);
        }

        return io.array();
    }
}
