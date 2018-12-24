package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TimeoutDrivingInfo implements IPacket {

    private String license;
    private DrivingTime startTime = new DrivingTime();
    private DrivingTime endTime = new DrivingTime();
    private Locate startLocate = new Locate();
    private Locate endLocate = new Locate();


    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public DrivingTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(DrivingTime startTime) {
        this.startTime = startTime;
    }

    public DrivingTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(DrivingTime endTime) {
        this.endTime = endTime;
    }

    public Locate getStartLocate() {
        return this.startLocate;
    }

    public void setStartLocate(Locate startLocate) {
        this.startLocate = startLocate;
    }

    public Locate getEndLocate() {
        return this.endLocate;
    }

    public void setEndLocate(Locate endLocate) {
        this.endLocate = endLocate;
    }

    public int size() {
        return 50;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.license = (new String(io.getBytes(18), Charsets.ASCII)).trim();
        this.startTime.from(io.getBytes(6));
        this.endTime.from(io.getBytes(6));
        this.startLocate.from(io.getBytes(10));
        this.endLocate.from(io.getBytes(10));
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(BytesUtil.fixed(this.license, Charsets.ASCII, 18));
        io.put(this.startTime.array());
        io.put(this.endTime.array());
        io.put(this.startLocate.array());
        io.put(this.endLocate.array());
        return data;
    }
}
