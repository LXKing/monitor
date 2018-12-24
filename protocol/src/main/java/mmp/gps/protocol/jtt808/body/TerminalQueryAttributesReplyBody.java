package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TerminalQueryAttributesReplyBody implements IPacket {

    private int type;
    private byte[] factoryId;
    private byte[] model;
    private byte[] deviceId;
    private byte[] ICCID;
    private byte hardwareVerSize;
    private String hardwareVer;
    private byte firmwareVerSize;
    private String firmwareVer;
    private byte gnssModule;
    private byte communicationModule;


    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getFactoryId() {
        return this.factoryId;
    }

    public void setFactoryId(byte[] factoryId) {
        this.factoryId = factoryId;
    }

    public byte[] getModel() {
        return this.model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

    public byte[] getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(byte[] deviceId) {
        this.deviceId = deviceId;
    }

    public byte[] getICCID() {
        return this.ICCID;
    }

    public void setICCID(byte[] iCCID) {
        this.ICCID = iCCID;
    }

    public byte getHardwareVerSize() {
        return this.hardwareVerSize;
    }

    public void setHardwareVerSize(byte hardwareVerSize) {
        this.hardwareVerSize = hardwareVerSize;
    }

    public String getHardwareVer() {
        return this.hardwareVer;
    }

    public void setHardwareVer(String hardwareVer) {
        this.hardwareVerSize = (byte) (hardwareVer == null ? 0 : hardwareVer.getBytes(Charsets.GBK).length);
        this.hardwareVer = hardwareVer;
    }

    public byte getFirmwareVerSize() {
        return this.firmwareVerSize;
    }

    public void setFirmwareVerSize(byte firmwareVerSize) {
        this.firmwareVerSize = firmwareVerSize;
    }

    public String getFirmwareVer() {
        return this.firmwareVer;
    }

    public void setFirmwareVer(String firmwareVer) {
        this.firmwareVerSize = (byte) (firmwareVer == null ? 0 : firmwareVer.getBytes(Charsets.GBK).length);
        this.firmwareVer = firmwareVer;
    }

    public byte getGnssModule() {
        return this.gnssModule;
    }

    public void setGnssModule(byte gnssModule) {
        this.gnssModule = gnssModule;
    }

    public byte getCommunicationModule() {
        return this.communicationModule;
    }

    public void setCommunicationModule(byte communicationModule) {
        this.communicationModule = communicationModule;
    }

    public int size() {
        return 45 + this.hardwareVerSize + 1 + this.firmwareVerSize + 1 + 1;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.type = io.getUshort();
        this.factoryId = io.getBytes(5);
        this.model = io.getBytes(20);
        this.deviceId = io.getBytes(7);
        this.ICCID = io.getBytes(10);
        this.hardwareVerSize = io.get();
        this.hardwareVer = new String(io.getBytes(this.hardwareVerSize), Charsets.GBK);
        this.firmwareVerSize = io.get();
        this.firmwareVer = new String(io.getBytes(this.firmwareVerSize), Charsets.GBK);
        this.gnssModule = io.get();
        this.communicationModule = io.get();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(this.size());
        io.putUshort(this.type);
        io.put(this.factoryId);
        io.put(this.model);
        io.put(this.deviceId);
        io.put(this.ICCID);
        io.put(this.hardwareVerSize);
        if (this.hardwareVer != null) {
            io.put(this.hardwareVer.getBytes(Charsets.GBK));
        }

        io.put(this.firmwareVerSize);
        if (this.firmwareVer != null) {
            io.put(this.firmwareVer.getBytes(Charsets.GBK));
        }

        io.put(this.gnssModule);
        io.put(this.communicationModule);
        return io.array();
    }
}
