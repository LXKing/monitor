package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class PhoneCallBackBody implements IPacket {

    private byte flag;
    private String phone;


    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int size() {
        if (this.phone != null && this.phone.length() > 20) {
            this.phone = this.phone.substring(0, 19);
        }

        return 1 + (this.phone == null ? 0 : this.phone.getBytes(Charsets.GBK).length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.flag = io.get();
        if (io.hasRemaining()) {
            this.phone = new String(io.getRemaining(), Charsets.GBK);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.flag);
        if (this.phone != null && this.phone.length() > 20) {
            this.phone = this.phone.substring(0, 19);
        }

        if (this.phone != null) {
            io.put(this.phone.getBytes(Charsets.GBK));
        }

        return io.array();
    }
}
