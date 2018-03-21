package com.edata.monitor.dao.baseinfo.device;

public class DeviceSearchInfo {

    private String id;
    private String deviceNumber;
    private String phoneNumber;
    private String model;
    private String factoryName;
    private String factoryNumber;

    @Override
    public String toString() {
        return "DeviceSearchInfo{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", " +
                "phoneNumber='" + phoneNumber + '\'' + ", model='" + model + '\'' + ", factoryName='" + factoryName +
                '\'' + ", factoryNumber='" + factoryNumber + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }
}
