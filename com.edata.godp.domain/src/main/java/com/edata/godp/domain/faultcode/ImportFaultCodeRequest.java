package com.edata.godp.domain.faultcode;

public class ImportFaultCodeRequest {
    private short level;
    private short modeId;
    private short code1;
    private short code2;
    private short code3;
    private String brand;
    private String pcode;
    private String contentC;
    private String contentE;
    private String solution;
    private byte clear;
    private boolean notifyOwner;
    private boolean notifyAdvisor;
    private String sensors;

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public short getModeId() {
        return modeId;
    }

    public void setModeId(short modeId) {
        this.modeId = modeId;
    }

    public short getCode1() {
        return code1;
    }

    public void setCode1(short code1) {
        this.code1 = code1;
    }

    public short getCode2() {
        return code2;
    }

    public void setCode2(short code2) {
        this.code2 = code2;
    }

    public short getCode3() {
        return code3;
    }

    public void setCode3(short code3) {
        this.code3 = code3;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getContentC() {
        return contentC;
    }

    public void setContentC(String contentC) {
        this.contentC = contentC;
    }

    public String getContentE() {
        return contentE;
    }

    public void setContentE(String contentE) {
        this.contentE = contentE;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public byte getClear() {
        return clear;
    }

    public void setClear(byte clear) {
        this.clear = clear;
    }

    public boolean isNotifyOwner() {
        return notifyOwner;
    }

    public void setNotifyOwner(boolean notifyOwner) {
        this.notifyOwner = notifyOwner;
    }

    public boolean isNotifyAdvisor() {
        return notifyAdvisor;
    }

    public void setNotifyAdvisor(boolean notifyAdvisor) {
        this.notifyAdvisor = notifyAdvisor;
    }

    public String getSensors() {
        return sensors;
    }

    public void setSensors(String sensors) {
        this.sensors = sensors;
    }
}
