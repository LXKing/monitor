package com.rayton.gps.dao.baseinfo.device;

import java.sql.Date;

public class DeviceSearchInfo {

    private String id;
    private String deviceNumber;
    private String phoneNumber;
    private String model;
    private String factoryName;
    private String factoryNumber;


    //
    private String SN;
    private Date INSTOCKDATE;
    private Date OUTSTOCKDATE;
    private Date ACTIVATIONDATE;
    private String IMEI;
    private String LIFECYCLE;
    private Date LIFEEXPIRATIONDATE;
    //


    @Override
    public String toString() {
        return "DeviceSearchInfo{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", " +
                "phoneNumber='" + phoneNumber + '\'' + ", model='" + model + '\'' + ", factoryName='" + factoryName +
                '\'' + ", factoryNumber='" + factoryNumber + '\'' + ", SN='" + SN + '\'' + ", INSTOCKDATE=" +
                INSTOCKDATE + ", OUTSTOCKDATE=" + OUTSTOCKDATE + ", ACTIVATIONDATE=" + ACTIVATIONDATE + ", IMEI='" +
                IMEI + '\'' + ", LIFECYCLE='" + LIFECYCLE + '\'' + ", LIFEEXPIRATIONDATE=" + LIFEEXPIRATIONDATE + '}';
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public Date getINSTOCKDATE() {
        return INSTOCKDATE;
    }

    public void setINSTOCKDATE(Date INSTOCKDATE) {
        this.INSTOCKDATE = INSTOCKDATE;
    }

    public Date getOUTSTOCKDATE() {
        return OUTSTOCKDATE;
    }

    public void setOUTSTOCKDATE(Date OUTSTOCKDATE) {
        this.OUTSTOCKDATE = OUTSTOCKDATE;
    }

    public Date getACTIVATIONDATE() {
        return ACTIVATIONDATE;
    }

    public void setACTIVATIONDATE(Date ACTIVATIONDATE) {
        this.ACTIVATIONDATE = ACTIVATIONDATE;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getLIFECYCLE() {
        return LIFECYCLE;
    }

    public void setLIFECYCLE(String LIFECYCLE) {
        this.LIFECYCLE = LIFECYCLE;
    }

    public Date getLIFEEXPIRATIONDATE() {
        return LIFEEXPIRATIONDATE;
    }

    public void setLIFEEXPIRATIONDATE(Date LIFEEXPIRATIONDATE) {
        this.LIFEEXPIRATIONDATE = LIFEEXPIRATIONDATE;
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
