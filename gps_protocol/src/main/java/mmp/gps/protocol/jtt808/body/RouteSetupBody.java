package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteSetupBody implements IPacket {

    private long id;
    private int flag;
    private GpsTime startTime;
    private GpsTime endTime;
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
        this.total = points.size();
        this.points = points;
    }

    public int size() {
        int size = 6;
        if ((this.flag & 1) == 1) {
            size += 12;
        }

        int pointsSize = 0;

        RoutePointInfo info;
        for (Iterator var3 = this.points.iterator(); var3.hasNext(); pointsSize += info.size()) {
            info = (RoutePointInfo) var3.next();
        }

        size += pointsSize;
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

        this.total = io.getUshort();
        this.points = new ArrayList();

        while (io.hasRemaining()) {
            RoutePointInfo info = new RoutePointInfo();
            info.from(io);
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

        io.putUshort(this.total);
        if (this.points != null) {
            Iterator var2 = this.points.iterator();

            while (var2.hasNext()) {
                RoutePointInfo info = (RoutePointInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
