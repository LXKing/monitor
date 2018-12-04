package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class TemporaryPositionTrackingControlBody implements IPacket {

    private int interval;
    private long seconds;


    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public int size() {
        return this.interval > 0 ? 6 : 2;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.interval = io.getUshort();
        if (this.interval > 0) {
            this.seconds = io.getUint();
        }

    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.interval);
        if (this.interval > 0) {
            io.putUint(this.seconds);
        }

        return io.array();
    }
}
