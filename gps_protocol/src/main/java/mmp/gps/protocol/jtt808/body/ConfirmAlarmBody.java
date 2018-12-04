package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class ConfirmAlarmBody implements IPacket {

    private int alarmId;
    private long type;


    public int getAlarmId() {
        return this.alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public long getType() {
        return this.type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public int size() {
        return 6;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.alarmId = io.getUshort();
        this.type = io.getUint();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.alarmId);
        io.putUint(this.type);
        return io.array();
    }
}
