package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class RoutePointInfo implements IPacket {

    private long pointId;
    private long sectionId;
    private long lat;
    private long lng;
    private short width;
    private short flag;
    private int maxTime;
    private int minTime;
    private int maxSpeed;
    private short overspeedSeconds;


    public long getPointId() {
        return this.pointId;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }

    public long getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

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

    public short getWidth() {
        return this.width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getFlag() {
        return this.flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public int getMaxTime() {
        return this.maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMinTime() {
        return this.minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
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
            size += 4;
        }

        if ((this.flag & 2) == 2) {
            size += 3;
        }

        return size;
    }

    public void from(ByteIO io) {
        this.pointId = io.getUint();
        this.sectionId = io.getUint();
        this.lat = io.getUint();
        this.lng = io.getUint();
        this.width = io.getUbyte();
        this.flag = io.getUbyte();
        if ((this.flag & 1) == 1) {
            this.maxTime = io.getUshort();
            this.minTime = io.getUshort();
        }

        if ((this.flag & 2) == 2) {
            this.maxSpeed = io.getUshort();
            this.overspeedSeconds = io.getUbyte();
        }

    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.from(io);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.pointId);
        io.putUint(this.sectionId);
        io.putUint(this.lat);
        io.putUint(this.lng);
        io.putUbyte(this.width);
        io.putUbyte(this.flag);
        if ((this.flag & 1) == 1) {
            io.putUshort(this.maxTime);
            io.putUshort(this.minTime);
        }

        if ((this.flag & 2) == 2) {
            io.putUshort(this.maxSpeed);
            io.putUbyte(this.overspeedSeconds);
        }

        return io.array();
    }
}
