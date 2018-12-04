package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Tuple;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocateInfo implements IPacket {

    private DrivingTime time = new DrivingTime();
    private List blocks = new ArrayList();


    public DrivingTime getTime() {
        return this.time;
    }

    public void setTime(DrivingTime time) {
        this.time = time;
    }

    public List getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List blocks) {
        this.blocks = blocks;
    }

    public int size() {
        return 666;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.time.from(io.getBytes(6));

        while (io.hasRemaining()) {
            Locate locate = new Locate();
            locate.from(io.getBytes(10));
            short speed = io.getUbyte();
            this.blocks.add(Tuple.of(locate, Short.valueOf(speed)));
        }

    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.put(this.time.array());
        Iterator var3 = this.blocks.iterator();

        while (var3.hasNext()) {
            Tuple block = (Tuple) var3.next();
            io.put(((Locate) block.e).array());
            io.putUbyte(((Short) block.t).shortValue());
        }

        return data;
    }
}
