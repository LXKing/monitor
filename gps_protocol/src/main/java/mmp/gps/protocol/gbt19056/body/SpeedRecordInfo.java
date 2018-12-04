package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class SpeedRecordInfo implements IPacket {

    private DrivingTime time = new DrivingTime();
    private byte[] content;


    public DrivingTime getTime() {
        return this.time;
    }

    public void setTime(DrivingTime time) {
        this.time = time;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int size() {
        return 126;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.time.from(io.getBytes(6));
        this.content = io.getBytes(120);
    }

    public byte[] array() {
        byte[] msg = new byte[this.size()];
        ByteIO io = new ByteIO(msg);
        io.put(this.time.array());
        io.put(this.content);
        return msg;
    }
}
