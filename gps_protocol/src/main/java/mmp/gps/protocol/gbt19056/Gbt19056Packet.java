package mmp.gps.protocol.gbt19056;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class Gbt19056Packet {

    public static final int ORDER_HEADER = 43637;
    public static final int REPLY_HEADER = 21882;
    private int header;
    private short command;
    private IPacket body;
    private short xor;


    public Gbt19056Packet() {
    }

    public Gbt19056Packet(IPacket body) {
        this.body = body;
    }

    public int getHeader() {
        return this.header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public short getCommand() {
        return this.command;
    }

    public void setCommand(short command) {
        this.command = command;
    }

    public IPacket getBody() {
        return this.body;
    }

    public void setBody(IPacket body) {
        this.body = body;
    }

    public short getXor() {
        return this.xor;
    }

    public void setXor(short xor) {
        this.xor = xor;
    }

    private int size() {
        return this.command == 250 && this.command == 251 ? 5 : 4 + (this.body == null ? 0 : this.body.size() + 3);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.header = io.getUshort();
        this.command = io.getUbyte();
        if (this.command == 250 && this.command == 251) {
            io.get();
        } else {
            int length = io.getUshort();
            io.get();
            this.body.from(io.getBytes(length));
        }

        this.xor = io.getUbyte();
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.header);
        io.putUbyte(this.command);
        if (this.command == 250 && this.command == 251) {
            io.put((byte) 0);
        } else {
            int length = this.body == null ? 0 : this.body.size();
            io.putUshort(length);
            io.put((byte) 0);
            if (this.body != null) {
                io.put(this.body.array());
            }
        }

        io.putUbyte(this.xor);
        Gbt19056Util.xor(data);
        return data;
    }
}
