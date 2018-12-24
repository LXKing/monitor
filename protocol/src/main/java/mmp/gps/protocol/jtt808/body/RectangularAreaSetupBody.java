package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RectangularAreaSetupBody implements IPacket {

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
        int areasSize = 0;

        RectangularAreaInfo info;
        for (Iterator var2 = this.areas.iterator(); var2.hasNext(); areasSize += info.size()) {
            info = (RectangularAreaInfo) var2.next();
        }

        return 2 + areasSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.action = io.get();
        this.total = io.getUbyte();
        this.areas = new ArrayList();

        while (io.hasRemaining()) {
            RectangularAreaInfo info = new RectangularAreaInfo();
            info.from(io);
            this.areas.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.action);
        io.putUbyte(this.total);
        if (this.areas != null) {
            Iterator var2 = this.areas.iterator();

            while (var2.hasNext()) {
                RectangularAreaInfo info = (RectangularAreaInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
