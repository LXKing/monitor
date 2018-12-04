package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TerminalQuerySpecificParametersBody implements IPacket {

    private short total;
    private List keys;


    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getKeys() {
        return this.keys;
    }

    public void setKeys(List keys) {
        this.keys = keys;
    }

    public int size() {
        return 1 + (this.keys == null ? 0 : this.keys.size() * 4);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.total = io.getUbyte();
        this.keys = new ArrayList(this.total);

        while (io.hasRemaining()) {
            this.keys.add(Long.valueOf(io.getUint()));
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte((short) (this.keys == null ? 0 : this.keys.size()));
        if (this.keys == null) {
            return io.array();
        } else {
            Iterator var2 = this.keys.iterator();

            while (var2.hasNext()) {
                Long key = (Long) var2.next();
                io.putUint(key.longValue());
            }

            return io.array();
        }
    }
}
