package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadLocateRecordReplyBody implements IPacket {

    private List locates = new ArrayList();


    public List getLocates() {
        return this.locates;
    }

    public void setLocates(List locates) {
        this.locates = locates;
    }

    public int size() {
        return this.locates != null && this.locates.size() > 0 ? this.locates.size() * 666 : 0;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);

        while (io.hasRemaining()) {
            LocateInfo info = new LocateInfo();
            info.from(io.getBytes(666));
            this.locates.add(info);
        }

    }

    public byte[] array() {
        if (this.locates != null && this.locates.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.locates.iterator();

            while (var3.hasNext()) {
                LocateInfo info = (LocateInfo) var3.next();
                io.put(info.array());
            }

            return data;
        } else {
            return null;
        }
    }
}
