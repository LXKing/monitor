package mmp.gps.protocol.gbt19056.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadSpeedRecordReplyBody implements IPacket {

    private List blocks;


    public List getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List blocks) {
        this.blocks = blocks;
    }

    public int size() {
        return this.blocks == null ? 0 : this.blocks.size() * 126;
    }

    public void from(byte[] src) {
        if (src != null && src.length > 0) {
            this.blocks = new ArrayList();
            ByteIO io = new ByteIO(src);

            while (io.hasRemaining()) {
                SpeedRecordInfo info = new SpeedRecordInfo();
                info.from(io.getBytes(126));
                this.blocks.add(info);
            }

        }
    }

    public byte[] array() {
        if (this.blocks != null && this.blocks.size() > 0) {
            byte[] data = new byte[this.size()];
            ByteIO io = new ByteIO(data);
            Iterator var3 = this.blocks.iterator();

            while (var3.hasNext()) {
                SpeedRecordInfo info = (SpeedRecordInfo) var3.next();
                io.put(info.array());
            }

            return data;
        } else {
            return null;
        }
    }
}
