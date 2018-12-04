package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class TerminalParameterInfo implements IPacket {

    private long id;
    private short size;
    private byte[] content;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getSize() {
        return this.size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int size() {
        return 5 + (this.content == null ? 0 : this.content.length);
    }

    public void from(ByteIO io) {
        this.id = io.getUint();
        this.size = io.getUbyte();
        if (this.size > 0) {
            this.content = io.getBytes(this.size);
        }

    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.from(io);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.id);
        io.putUbyte(this.size);
        io.put(this.content);
        return io.array();
    }
}
