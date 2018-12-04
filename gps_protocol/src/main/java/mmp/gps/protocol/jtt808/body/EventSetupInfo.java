package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class EventSetupInfo implements IPacket {

    private byte type;
    private short id;
    private short contentSize;
    private String content;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getContentSize() {
        return this.contentSize;
    }

    public void setContentSize(short contentSize) {
        this.contentSize = contentSize;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int size() {
        return this.type == 4 ? 1 : 2 + (this.content == null ? 0 : this.content.getBytes(Charsets.GBK).length);
    }

    public void from(ByteIO io) {
        this.id = io.getUbyte();
        if (this.type != 4) {
            this.contentSize = io.getUbyte();
            this.content = new String(io.getBytes(this.contentSize), Charsets.GBK);
        }

    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.from(io);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.id);
        if (this.type != 4) {
            io.putUbyte(this.contentSize);
            io.put(this.content.getBytes(Charsets.GBK));
        }

        return io.array();
    }
}
