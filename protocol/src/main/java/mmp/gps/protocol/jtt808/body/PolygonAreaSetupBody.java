package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolygonAreaSetupBody implements IPacket {

    private long id;
    private int flag;
    private GpsTime startTime;
    private GpsTime endTime;
    private int maxSpeed;
    private short overspeedSeconds;
    private int total;
    private List points;


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

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getPoints() {
        return this.points;
    }

    public void setPoints(List points) {
        this.total = points == null ? 0 : points.size();
        this.points = points;
    }

    public int size() {
        int size = 6;
        if ((this.flag & 1) == 1) {
            size += 12;
        }

        if ((this.flag & 2) == 2) {
            size += 3;
        }

        size += this.points == null ? 0 : this.points.size() * 8;
        return size;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.id = io.getUint();
        this.flag = io.getUshort();
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

        this.total = io.getUshort();
        this.points = new ArrayList(this.total);

        while (io.hasRemaining()) {
            PolygonAreaPointInfo info = new PolygonAreaPointInfo();
            info.from(io.getBytes(8));
            this.points.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.id);
        io.putUshort(this.flag);
        if ((this.flag & 1) == 1) {
            io.put(this.startTime.array());
            io.put(this.endTime.array());
        }

        if ((this.flag & 2) == 2) {
            io.putUshort(this.maxSpeed);
            io.putUbyte(this.overspeedSeconds);
        }

        io.putUshort(this.total);
        if (this.points != null) {
            Iterator var2 = this.points.iterator();

            while (var2.hasNext()) {
                PolygonAreaPointInfo info = (PolygonAreaPointInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
