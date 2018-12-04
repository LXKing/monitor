package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TextMessageIssuedBody implements IPacket {

    private short flag;
    private String message;


    public short getFlag() {
        return this.flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int size() {
        return 1 + (this.message == null ? 0 : this.message.getBytes(Charsets.GBK).length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.flag = io.getUbyte();
        byte[] tmp = io.getRemaining();
        if (tmp != null) {
            this.message = new String(tmp, Charsets.GBK);
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.flag);
        if (this.message != null) {
            io.put(this.message.getBytes(Charsets.GBK));
        }

        return io.array();
    }
}
