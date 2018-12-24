package mmp.gps.gateway.codec.jtt808.Handler;

import mmp.gps.common.util.ObjectId;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.LocationInformationBaseInfo;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

class Multimedia {

    private String objId = ObjectId.next();
    private String number;
    private int pages;
    private JTT808Packet first;
    private ConcurrentHashMap frames = new ConcurrentHashMap();
    private Date lastUploadTime;
    private ScheduledFuture future;
    private byte mediaType;
    private byte formatType;
    private byte eventType;
    private byte channelId;
    private LocationInformationBaseInfo baseInfo;


    public String getObjId() {
        return this.objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public JTT808Packet getFirst() {
        return this.first;
    }

    public void setFirst(JTT808Packet first) {
        this.first = first;
    }

    public ConcurrentHashMap getFrames() {
        return this.frames;
    }

    public void setFrames(ConcurrentHashMap frames) {
        this.frames = frames;
    }

    public Date getLastUploadTime() {
        return this.lastUploadTime;
    }

    public void setLastUploadTime(Date lastUploadTime) {
        this.lastUploadTime = lastUploadTime;
    }

    public boolean isCompleted() {
        return this.first != null && this.frames.size() == this.pages - 1;
    }

    public ScheduledFuture getFuture() {
        return this.future;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
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
}
