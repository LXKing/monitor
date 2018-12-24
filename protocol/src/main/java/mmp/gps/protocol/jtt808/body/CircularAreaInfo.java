package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class CircularAreaInfo implements IPacket {

    private long id;
    private int flag;
    private long centerLat;
    private long centerLng;
    private long radius;
    private GpsTime startTime;
    private GpsTime endTime;
    private int maxSpeed;
    private short overspeedSeconds;


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

    public long getCenterLat() {
        return this.centerLat;
    }

    public void setCenterLat(long centerLat) {
        this.centerLat = centerLat;
    }

    public long getCenterLng() {
        return this.centerLng;
    }

    public void setCenterLng(long centerLng) {
        this.centerLng = centerLng;
    }

    public long getRadius() {
        return this.radius;
    }

    public void setRadius(long radius) {
        this.radius = radius;
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

    public short getOverspeedSeconds() {
        return this.overspeedSeconds;
    }

    public void setOverspeedSeconds(short overspeedSeconds) {
        this.overspeedSeconds = overspeedSeconds;
    }

    public int size() {
        int size = 18;
        if ((this.flag & 1) == 1) {
            size += 12;
        }

        if ((this.flag & 2) == 2) {
            size += 3;
        }

        return size;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.id = io.getUint();
        this.flag = io.getUshort();
        this.centerLat = io.getUint();
        this.centerLng = io.getUint();
        this.radius = io.getUint();
        if ((this.flag & 1) == 1) {
            this.startTime = new GpsTime();
            this.startTime.from(io.getBytes(6));
            this.endTime = new GpsTime();
            this.endTime.from(io.getBytes(6));
        }

        if ((this.flag & 2) == 2) {
            this.maxSpeed = io.getUshort();
            this.overspeedSeconds = io.getUbyte();
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.id);
        io.putUshort(this.flag);
        io.putUint(this.centerLat);
        io.putUint(this.centerLng);
        io.putUint(this.radius);
        if ((this.flag & 1) == 1) {
            io.put(this.startTime.array());
            io.put(this.endTime.array());
        }

        if ((this.flag & 2) == 2) {
            io.putUshort(this.maxSpeed);
            io.putUbyte(this.overspeedSeconds);
        }

        return io.array();
    }
}
