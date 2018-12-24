package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class TerminalUpgradeResultReportBody implements IPacket {

    private byte type;
    private byte result;


    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getResult() {
        return this.result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public int size() {
        return 2;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.get();
        this.result = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.type);
        io.put(this.result);
        return io.array();
    }
}
