package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class PulseBody implements IPacket {

    private DrivingTime time = new DrivingTime();
    private int coefficient;


    public DrivingTime getTime() {
        return this.time;
    }

    public void setTime(DrivingTime time) {
        this.time = time;
    }

    public int getCoefficient() {
        return this.coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int size() {
        return 8;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.time.from(io.getBytes(6));
        this.coefficient = io.getUshort();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.time.array());
        io.putUshort(this.coefficient);
        return data;
    }
}
