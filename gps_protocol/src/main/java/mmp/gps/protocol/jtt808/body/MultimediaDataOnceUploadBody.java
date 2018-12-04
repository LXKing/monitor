package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class MultimediaDataOnceUploadBody implements IPacket {

    private long mediaId;
    private byte action;


    public long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public byte getAction() {
        return this.action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int size() {
        return 5;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.mediaId = io.getUint();
        this.action = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.mediaId);
        io.put(this.action);
        return io.array();
    }
}
