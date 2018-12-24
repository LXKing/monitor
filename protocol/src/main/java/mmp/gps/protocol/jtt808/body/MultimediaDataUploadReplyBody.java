package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultimediaDataUploadReplyBody implements IPacket {

    private long multimediaId;
    private short total;
    private List list;


    public long getMultimediaId() {
        return this.multimediaId;
    }

    public void setMultimediaId(long multimediaId) {
        this.multimediaId = multimediaId;
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
        this.total = (short) (list == null ? 0 : list.size());
        this.list = list;
    }

    public int size() {
        return this.list != null && this.list.size() > 0 ? 5 + (this.list == null ? 0 : this.list.size() * 2) : 4;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.multimediaId = io.getUint();
        if (io.hasRemaining()) {
            this.total = io.getUbyte();
            if (this.total > 0) {
                this.list = new ArrayList(this.total);

                while (io.hasRemaining()) {
                    this.list.add(Integer.valueOf(io.getUshort()));
                }
            }
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.multimediaId);
        if (this.list != null && this.list.size() > 0) {
            io.putUbyte(this.total);
            Iterator var2 = this.list.iterator();

            while (var2.hasNext()) {
                Integer id = (Integer) var2.next();
                io.putUshort(id.intValue());
            }
        }

        return io.array();
    }
}
