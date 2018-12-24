package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CircularAreaRemoveBody implements IPacket {

    private byte total;
    private List idList;


    public byte getTotal() {
        return this.total;
    }

    public void setTotal(byte total) {
        this.total = total;
    }

    public List getIdList() {
        return this.idList;
    }

    public void setIdList(List idList) {
        this.idList = idList;
    }

    public int size() {
        return 1 + (this.idList == null ? 0 : this.idList.size() * 4);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.total = io.get();
        this.idList = new ArrayList(this.total);

        while (io.hasRemaining()) {
            this.idList.add(Long.valueOf(io.getUint()));
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put((byte) (this.idList == null ? 0 : this.idList.size()));
        if (this.idList != null) {
            Iterator var2 = this.idList.iterator();

            while (var2.hasNext()) {
                Long id = (Long) var2.next();
                io.putUint(id.longValue());
            }
        }

        return io.array();
    }
}
