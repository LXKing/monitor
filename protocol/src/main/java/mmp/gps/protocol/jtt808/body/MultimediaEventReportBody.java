package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class MultimediaEventReportBody implements IPacket {

    private long id;
    private byte mediaType;
    private byte formatType;
    private byte eventType;
    private byte channelId;


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

    public int size() {
        return 8;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.id = io.getUint();
        this.mediaType = io.get();
        this.formatType = io.get();
        this.eventType = io.get();
        this.channelId = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUint(this.id);
        io.put(this.mediaType);
        io.put(this.formatType);
        io.put(this.eventType);
        io.put(this.channelId);
        return io.array();
    }
}
