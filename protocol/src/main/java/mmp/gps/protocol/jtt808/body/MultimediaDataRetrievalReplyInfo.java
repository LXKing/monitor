package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class MultimediaDataRetrievalReplyInfo implements IPacket {

    private long mediaId;
    private byte mediaType;
    private byte channelId;
    private byte eventType;
    private LocationInformationBaseInfo baseInfo;


    public long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public byte getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(byte mediaType) {
        this.mediaType = mediaType;
    }

    public byte getChannelId() {
        return this.channelId;
    }

    public void setChannelId(byte channelId) {
        this.channelId = channelId;
    }

    public byte getEventType() {
        return this.eventType;
    }

    public void setEventType(byte eventType) {
        this.eventType = eventType;
    }

    public LocationInformationBaseInfo getBaseInfo() {
        return this.baseInfo;
    }

    public void setBaseInfo(LocationInformationBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public int size() {
        return 35;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.mediaId = io.getUint();
        this.mediaType = io.get();
        this.channelId = io.get();
        this.eventType = io.get();
        this.baseInfo = new LocationInformationBaseInfo();
        this.baseInfo.from(io.getBytes(28));
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.mediaId);
        io.put(this.mediaType);
        io.put(this.channelId);
        io.put(this.eventType);
        io.put(this.baseInfo.array());
        return io.array();
    }
}
