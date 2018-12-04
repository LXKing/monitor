package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

public class TerminalRegistrationRequestBody implements IPacket {

    private int provinceId;
    private int cityId;
    private byte[] factoryId = new byte[5];
    private byte[] model = new byte[20];
    private byte[] deviceId = new byte[7];
    private short vehiclePlateColor;
    private String vehicleId;


    public int getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return this.cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    public void setDeviceId(byte[] id) {
        this.deviceId = id;
    }

    public short getVehiclePlateColor() {
        return this.vehiclePlateColor;
    }

    public void setVehiclePlateColor(short vehiclePlateColor) {
        this.vehiclePlateColor = vehiclePlateColor;
    }

    public String getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int size() {
        return 37 + (this.vehicleId == null ? 0 : this.vehicleId.getBytes(Charsets.GBK).length);
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.provinceId = io.getUshort();
        this.cityId = io.getUshort();
        io.get(this.factoryId);
        io.get(this.model);
        io.get(this.deviceId);
        this.vehiclePlateColor = io.getUbyte();
        byte[] number = io.getRemaining();
        this.vehicleId = new String(number, Charsets.GBK);
    }

    public byte[] array() {
        byte[] data = new byte[this.size()];
        ByteIO io = new ByteIO(data);
        io.putUshort(this.provinceId);
        io.putUshort(this.cityId);
        io.put(this.factoryId);
        io.put(this.model);
        io.put(this.deviceId);
        io.putUbyte(this.vehiclePlateColor);
        if (this.vehicleId != null && this.vehicleId.length() > 0) {
            io.put(this.vehicleId.getBytes(Charsets.GBK));
        }

        return data;
    }
}
