package com.rayton.gps.domain.faultcode;

/**
 * 解析故障码信息
 *
 * @author 生
 */
public class ParseFaultCodeResponse {
    private short level;
    private String pcode;
    private String brand;
    private String contentC;
    private String contentE;
    private String solution;
    private String sensors;
    private byte clear;
    private boolean notifyOwner;
    private boolean notifyAdvisor;

    /**
     * 获取故障级别
     */
    public short getLevel() {
        return level;
    }

    /**
     * 设置故障级别
     */
    public void setLevel(short level) {
        this.level = level;
    }

    /**
     * 获取简码
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * 设置简码
     */
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    /**
     * 获取适用品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置适用品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取中文故障内容
     */
    public String getContentC() {
        return contentC;
    }

    /**
     * 设置中文故障内容
     */
    public void setContentC(String contentC) {
        this.contentC = contentC;
    }

    /**
     * 获取英文故障内容
     */
    public String getContentE() {
        return contentE;
    }

    /**
     * 设置英文故障内容
     */
    public void setContentE(String contentE) {
        this.contentE = contentE;
    }

    /**
     * 获取解决方案
     */
    public String getSolution() {
        return solution;
    }

    /**
     * 设置解决方案
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * 获取传感器范畴
     */
    public String getSensors() {
        return sensors;
    }

    /**
     * 设置传感器范畴
     */
    public void setSensors(String sensors) {
        this.sensors = sensors;
    }

    /**
     * 获取清除条件
     */
    public byte getClear() {
        return clear;
    }

    /**
     * 设置清除条件
     */
    public void setClear(byte clear) {
        this.clear = clear;
    }

    /**
     * 获取是否通知车主
     */
    public boolean isNotifyOwner() {
        return notifyOwner;
    }

    /**
     * 设置是否通知车主
     */
    public void setNotifyOwner(boolean notifyOwner) {
        this.notifyOwner = notifyOwner;
    }

    /**
     * 获取是否通知技师
     */
    public boolean isNotifyAdvisor() {
        return notifyAdvisor;
    }

    /**
     * 设置是否通知技师
     */
    public void setNotifyAdvisor(boolean notifyAdvisor) {
        this.notifyAdvisor = notifyAdvisor;
    }
}
