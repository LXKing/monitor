package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CircularAreaSetupBody implements IPacket {

    private byte action;
    private short total;
    private List areas;


    public byte getAction() {
        return this.action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getAreas() {
        return this.areas;
    }

    public void setAreas(List areas) {
        this.total = (short) (areas == null ? 0 : areas.size());
        this.areas = areas;
    }

    public int size() {
        return 2 + (this.areas == null ? 0 : this.areas.size() * 33);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.action = io.get();
        this.total = io.getUbyte();
        this.areas = new ArrayList(this.total);

        while (io.hasRemaining()) {
            CircularAreaInfo info = new CircularAreaInfo();
            info.from(io.getBytes(33));
            this.areas.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.action);
        io.putUbyte((short) (this.areas == null ? 0 : this.areas.size()));
        if (this.areas != null) {
            Iterator var2 = this.areas.iterator();

            while (var2.hasNext()) {
                CircularAreaInfo info = (CircularAreaInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
