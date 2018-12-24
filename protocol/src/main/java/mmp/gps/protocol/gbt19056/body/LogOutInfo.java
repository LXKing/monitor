package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.BytesUtil;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class LogOutInfo implements IPacket {

    private DrivingTime time = new DrivingTime();
    private String license;
    private byte action;


    public DrivingTime getTime() {
        return this.time;
    }

    public void setTime(DrivingTime time) {
        this.time = time;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public byte getAction() {
        return this.action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int size() {
        return 25;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.time.from(io.getBytes(6));
        this.license = (new String(io.getBytes(18), Charsets.ASCII)).trim();
        this.action = io.get();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.time.array());
        io.put(BytesUtil.fixed(this.license, Charsets.ASCII, 18));
        io.put(this.action);
        return data;
    }
}
