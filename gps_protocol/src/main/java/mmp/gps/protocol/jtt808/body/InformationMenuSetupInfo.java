package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class InformationMenuSetupInfo implements IPacket {

    private short id;
    private int contentSize;
    private String content;


    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public int getContentSize() {
        return this.contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int size() {
        return 3 + (this.content == null ? 0 : this.content.getBytes(Charsets.GBK).length);
    }

    public void from(ByteIO io) {
        this.id = io.getUbyte();
        this.contentSize = io.getUshort();
        this.content = new String(io.getBytes(this.contentSize), Charsets.GBK);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.from(io);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.id);
        io.putUshort(this.contentSize);
        io.put(this.content.getBytes(Charsets.GBK));
        return io.array();
    }
}
