package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.contract.IPacket;

public class CameraShootingImmediatelyRequestBody implements IPacket {

    private byte channelId;
    private int actionFlag;
    private int seconds;
    private byte saveFlag;
    private byte resolution;
    private byte quality;
    private short brightness;
    private byte contrast;
    private byte saturation;
    private short chroma;


    public byte getChannelId() {
        return this.channelId;
    }

    public void setChannelId(byte channelId) {
        this.channelId = channelId;
    }

    public int getActionFlag() {
        return this.actionFlag;
    }

    public void setActionFlag(int actionFlag) {
        this.actionFlag = actionFlag;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public byte getSaveFlag() {
        return this.saveFlag;
    }

    public void setSaveFlag(byte saveFlag) {
        this.saveFlag = saveFlag;
    }

    public byte getResolution() {
        return this.resolution;
    }

    public void setResolution(byte resolution) {
        this.resolution = resolution;
    }

    public byte getQuality() {
        return this.quality;
    }

    public void setQuality(byte quality) {
        this.quality = quality;
    }

    public short getBrightness() {
        return this.brightness;
    }

    public void setBrightness(short brightness) {
        this.brightness = brightness;
    }

    public byte getContrast() {
        return this.contrast;
    }

    public void setContrast(byte contrast) {
        this.contrast = contrast;
    }

    public byte getSaturation() {
        return this.saturation;
    }

    public void setSaturation(byte saturation) {
        this.saturation = saturation;
    }

    public short getChroma() {
        return this.chroma;
    }

    public void setChroma(short chroma) {
        this.chroma = chroma;
    }

    public int size() {
        return 12;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.channelId = io.get();
        this.actionFlag = io.getUshort();
        this.seconds = io.getUshort();
        this.saveFlag = io.get();
        this.resolution = io.get();
        this.quality = io.get();
        this.brightness = io.getUbyte();
        this.contrast = io.get();
        this.saturation = io.get();
        this.chroma = io.getUbyte();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.put(this.channelId);
        io.putUshort(this.actionFlag);
        io.putUshort(this.seconds);
        io.put(this.saveFlag);
        io.put(this.resolution);
        io.put(this.quality);
        io.putUbyte(this.brightness);
        io.put(this.contrast);
        io.put(this.saturation);
        io.putUbyte(this.chroma);
        return io.array();
    }
}
