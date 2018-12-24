package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class MultimediaDataUploadBody implements IPacket {

    private long id;
    private byte mediaType;
    private byte formatType;
    private byte eventType;
    private byte channelId;
    private LocationInformationBaseInfo baseInfo;
    private byte[] data;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(byte mediaType) {
        this.mediaType = mediaType;
    }

    public byte getFormatType() {
        return this.formatType;
    }

    public void setFormatType(byte formatType) {
        this.formatType = formatType;
    }

    public byte getEventType() {
        return this.eventType;
    }

    public void setEventType(byte eventType) {
        this.eventType = eventType;
    }

    public byte getChannelId() {
        return this.channelId;
    }

    public void setChannelId(byte channelId) {
        this.channelId = channelId;
    }

    public LocationInformationBaseInfo getBaseInfo() {
        return this.baseInfo;
    }

    public void setBaseInfo(LocationInformationBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int size() {
        return 36 + (this.data == null ? 0 : this.data.length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.id = io.getUint();
        this.mediaType = io.get();
        this.formatType = io.get();
        this.eventType = io.get();
        this.channelId = io.get();
        this.baseInfo = new LocationInformationBaseInfo();
        this.baseInfo.from(io.getBytes(28));
        this.data = io.getRemaining();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.id);
        io.put(this.mediaType);
        io.put(this.formatType);
        io.put(this.eventType);
        io.put(this.channelId);
        io.put(this.baseInfo.array());
        io.put(this.data);
        return io.array();
    }
}
