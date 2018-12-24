package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TelephoneInfo implements IPacket {

    private byte flag;
    private byte phoneSize;
    private String phone;
    private byte nameSize;
    private String name;


    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public byte getPhoneSize() {
        return this.phoneSize;
    }

    public void setPhoneSize(byte phoneSize) {
        this.phoneSize = phoneSize;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getNameSize() {
        return this.nameSize;
    }

    public void setNameSize(byte nameSize) {
        this.nameSize = nameSize;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int size() {
        return 2 + (this.phone == null ? 0 : this.phone.getBytes(Charsets.GBK).length) + 1 + (this.name == null ? 0 : this.name.getBytes(Charsets.GBK).length);
    }

    public void from(ByteIO io) {
        this.flag = io.get();
        this.phoneSize = io.get();
        this.phone = new String(io.getBytes(this.phoneSize), Charsets.GBK);
        this.nameSize = io.get();
        this.name = new String(io.getBytes(this.nameSize), Charsets.GBK);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.from(io);
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.flag);
        io.put(this.phoneSize);
        io.put(this.phone.getBytes(Charsets.GBK));
        io.put(this.nameSize);
        io.put(this.name.getBytes(Charsets.GBK));
        return io.array();
    }
}
