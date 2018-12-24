package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class ReadSpeedRecordRequestBody implements IPacket {

    private DrivingTime start = new DrivingTime();
    private DrivingTime end = new DrivingTime();
    private int blocks;


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

    public int getBlocks() {
        return this.blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int size() {
        return 14;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.start.from(io.getBytes(6));
        this.end.from(io.getBytes(6));
        this.blocks = io.getUshort();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.start.array());
        io.put(this.end.array());
        io.putUshort(this.blocks);
        return data;
    }
}
