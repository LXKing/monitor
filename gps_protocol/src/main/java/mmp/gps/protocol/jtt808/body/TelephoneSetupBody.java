package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TelephoneSetupBody implements IPacket {

    private byte type;
    private short total;
    private List list;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short total) {
        this.total = total;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int size() {
        int listSize = 0;
        TelephoneInfo info;
        if (this.list != null) {
            for (Iterator var2 = this.list.iterator(); var2.hasNext(); listSize += info.size()) {
                info = (TelephoneInfo) var2.next();
            }
        }

        return 2 + listSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.get();
        this.total = io.getUbyte();
        this.list = new ArrayList();

        while (io.hasRemaining()) {
            TelephoneInfo info = new TelephoneInfo();
            info.from(io);
            this.list.add(info);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.type);
        io.putUbyte((short) (this.list == null ? 0 : this.list.size()));
        if (this.list != null) {
            Iterator var2 = this.list.iterator();

            while (var2.hasNext()) {
                TelephoneInfo info = (TelephoneInfo) var2.next();
                io.put(info.array());
            }
        }

        return io.array();
    }
}
