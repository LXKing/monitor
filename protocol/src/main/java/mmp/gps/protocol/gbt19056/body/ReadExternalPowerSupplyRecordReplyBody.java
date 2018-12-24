package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Tuple;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExternalPowerSupplyRecordReplyBody implements IPacket {

    private List logs = new ArrayList();


    public List getLogs() {
        return this.logs;
    }

    public void setLogs(List logs) {
        this.logs = logs;
    }

    public int size() {
        return this.logs == null ? 0 : this.logs.size() * 7;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            DrivingTime time = new DrivingTime();
            time.from(io.getBytes(6));
            byte action = io.get();
            this.logs.add(Tuple.of(time, Byte.valueOf(action)));
        }

    }

    public byte[] array() {
        if (this.logs != null && this.logs.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.logs.iterator();

            while (var3.hasNext()) {
                Tuple tuple = (Tuple) var3.next();
                io.put(((DrivingTime) tuple.e).array());
                io.put(((Byte) tuple.t).byteValue());
            }

            return data;
        } else {
            return null;
        }
    }
}
