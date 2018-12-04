package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class SpeedStatusInfo implements IPacket {

    private byte flag;
    private DrivingTime start = new DrivingTime();
    private DrivingTime end = new DrivingTime();
    private byte[] content = new byte[120];


    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public DrivingTime getStart() {
        return this.start;
    }

    public void setStart(DrivingTime start) {
        this.start = start;
    }

    public DrivingTime getEnd() {
        return this.end;
    }

    public void setEnd(DrivingTime end) {
        this.end = end;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int size() {
        return 133;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.flag = io.get();
        this.start.from(io.getBytes(6));
        this.end.from(io.getBytes(6));
        io.get(this.content);
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.flag);
        io.put(this.start.array());
        io.put(this.end.array());
        io.put(this.content);
        return data;
    }
}
