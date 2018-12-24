package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class ElectronicWaybillReportBody implements IPacket {

    private long size;
    private byte[] content;


    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int size() {
        return 4 + (this.content == null ? 0 : this.content.length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.size = io.getUint();
        if (this.size > 0L) {
            this.content = io.getRemaining();
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.size);
        io.put(this.content);
        return io.array();
    }
}
