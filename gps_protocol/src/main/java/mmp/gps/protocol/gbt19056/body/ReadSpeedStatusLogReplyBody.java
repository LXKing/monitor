package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadSpeedStatusLogReplyBody implements IPacket {

    private List logs = new ArrayList();


    public List getLogs() {
        return this.logs;
    }

    public void setLogs(List logs) {
        this.logs = logs;
    }

    public int size() {
        return this.logs == null ? 0 : this.logs.size() * 133;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            SpeedStatusInfo log = new SpeedStatusInfo();
            log.from(io.getBytes(133));
            this.logs.add(log);
        }

    }

    public byte[] array() {
        if (this.logs != null && this.logs.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.logs.iterator();

            while (var3.hasNext()) {
                SpeedStatusInfo log = (SpeedStatusInfo) var3.next();
                io.put(log.array());
            }

            return data;
        } else {
            return null;
        }
    }
}
