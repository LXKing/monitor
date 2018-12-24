package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadLoginLogoutRecordReplyBody implements IPacket {

    private List logouts = new ArrayList();


    public List getLogouts() {
        return this.logouts;
    }

    public void setLogouts(List logouts) {
        this.logouts = logouts;
    }

    public int size() {
        return this.logouts == null ? 0 : this.logouts.size() * 25;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            LogOutInfo info = new LogOutInfo();
            info.from(io.getBytes(25));
            this.logouts.add(info);
        }

    }

    public byte[] array() {
        if (this.logouts != null && this.logouts.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.logouts.iterator();

            while (var3.hasNext()) {
                LogOutInfo info = (LogOutInfo) var3.next();
                io.put(info.array());
            }

            return data;
        } else {
            return null;
        }
    }
}
