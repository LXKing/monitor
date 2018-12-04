package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class ReadMileageReplyBody implements IPacket {

    private DrivingTime currentTime = new DrivingTime();
    private DrivingTime installTime = new DrivingTime();
    private long initialMileage;
    private long totalMileage;


    public DrivingTime getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(DrivingTime currentTime) {
        this.currentTime = currentTime;
    }

    public DrivingTime getInstallTime() {
        return this.installTime;
    }

    public void setInstallTime(DrivingTime installTime) {
        this.installTime = installTime;
    }

    public long getInitialMileage() {
        return this.initialMileage;
    }

    public void setInitialMileage(long initialMileage) {
        this.initialMileage = initialMileage;
    }

    public long getTotalMileage() {
        return this.totalMileage;
    }

    public void setTotalMileage(long totalMileage) {
        this.totalMileage = totalMileage;
    }

    public int size() {
        return 20;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.currentTime.from(io.getBytes(6));
        this.installTime.from(io.getBytes(6));
        this.initialMileage = io.getUint();
        this.totalMileage = io.getUint();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.currentTime.array());
        io.put(this.installTime.array());
        io.putUint(this.initialMileage);
        io.putUint(this.totalMileage);
        return data;
    }
}
