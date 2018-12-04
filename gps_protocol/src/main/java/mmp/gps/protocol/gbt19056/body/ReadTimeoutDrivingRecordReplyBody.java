package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadTimeoutDrivingRecordReplyBody implements IPacket {

    private List timeouts = new ArrayList();


    public List getTimeouts() {
        return this.timeouts;
    }

    public void setTimeouts(List timeouts) {
        this.timeouts = timeouts;
    }

    public int size() {
        return this.timeouts == null ? 0 : this.timeouts.size() * 50;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            TimeoutDrivingInfo info = new TimeoutDrivingInfo();
            info.from(io.getBytes(50));
            this.timeouts.add(info);
        }

    }

    public byte[] array() {
        if (this.timeouts != null && this.timeouts.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.timeouts.iterator();

            while (var3.hasNext()) {
                TimeoutDrivingInfo info = (TimeoutDrivingInfo) var3.next();
                io.put(info.array());
            }

            return data;
        } else {
            return null;
        }
    }
}
