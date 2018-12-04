package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class TerminalControlRequestBody implements IPacket {

    private short command;
    private byte[] parameters;


    public short getCommand() {
        return this.command;
    }

    public void setCommand(short command) {
        this.command = command;
    }

    public byte[] getParameters() {
        return this.parameters;
    }

    public void setParameter(byte[] parameters) {
        this.parameters = parameters;
    }

    public int size() {
        return this.command != 1 && this.command != 2 ? 1 : 1 + (this.parameters == null ? 0 : this.parameters.length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.command = io.getUbyte();
        if (io.hasRemaining()) {
            this.parameters = io.getRemaining();
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUbyte(this.command);
        if (this.command == 1 || this.command == 2) {
            io.put(this.parameters);
        }

        return io.array();
    }
}
