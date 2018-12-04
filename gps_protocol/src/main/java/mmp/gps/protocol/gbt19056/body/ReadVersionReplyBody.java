package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class ReadVersionReplyBody implements IPacket {

    private short year;
    private short edit;


    public short getYear() {
        return this.year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getEdit() {
        return this.edit;
    }

    public void setEdit(short edit) {
        this.edit = edit;
    }

    public int size() {
        return 2;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.year = io.getUbyte();
        this.edit = io.getUbyte();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUbyte(this.year);
        io.putUbyte(this.edit);
        return data;
    }
}
