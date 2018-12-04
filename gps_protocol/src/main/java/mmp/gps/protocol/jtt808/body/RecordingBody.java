package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class RecordingBody implements IPacket {

    private byte action;
    private int seconds;
    private byte saveFlag;
    private byte rate;


    public byte getAction() {
        return this.action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public byte getSaveFlag() {
        return this.saveFlag;
    }

    public void setSaveFlag(byte saveFlag) {
        this.saveFlag = saveFlag;
    }

    public byte getRate() {
        return this.rate;
    }

    public void setRate(byte rate) {
        this.rate = rate;
    }

    public int size() {
        return 5;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.action = io.get();
        this.seconds = io.getUshort();
        this.saveFlag = io.get();
        this.rate = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.action);
        io.putUshort(this.seconds);
        io.put(this.saveFlag);
        io.put(this.rate);
        return io.array();
    }
}
