package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class SetMileageRequestBody implements IPacket {

    private long mileage;


    public long getMileage() {
        return this.mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public int size() {
        return 4;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.mileage = io.getUint();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUint(this.mileage);
        return data;
    }
}
