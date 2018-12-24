package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class MultimediaDataRetrievalRequestBody implements IPacket {

    private byte mediaType;
    private byte channelId;
    private byte eventType;
    private GpsTime startTime;
    private GpsTime endTime;


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

    public GpsTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(GpsTime startTime) {
        this.startTime = startTime;
    }

    public GpsTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(GpsTime endTime) {
        this.endTime = endTime;
    }

    public int size() {
        return 15;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.mediaType = io.get();
        this.channelId = io.get();
        this.eventType = io.get();
        this.startTime = new GpsTime();
        this.startTime.from(io.getBytes(6));
        this.endTime = new GpsTime();
        this.endTime.from(io.getBytes(6));
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.mediaType);
        io.put(this.channelId);
        io.put(this.eventType);
        io.put(this.startTime.array());
        io.put(this.endTime.array());
        return io.array();
    }
}
