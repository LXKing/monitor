package mmp.gps.domain.faultcode;

public class CreateFaultCodeRequest {
    private short level;
    private short modeId;
    private short code1;
    private short code2;
    private short code3;
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
     * 获取故障模式id
     */
    public short getModeId() {
        return modeId;
    }

    /**
     * 设置故障模式id
     */
    public void setModeId(short modeId) {
        this.modeId = modeId;
    }

    /**
     * 获取代码1
     */
    public short getCode1() {
        return code1;
    }

    /**
     * 设置代码1
     */
    public void setCode1(short code1) {
        this.code1 = code1;
    }

    /**
     * 获取代码2
     */
    public short getCode2() {
        return code2;
    }

    /**
     * 设置代码2
     */
    public void setCode2(short code2) {
        this.code2 = code2;
    }

    /**
     * 获取代码3
     */
    public short getCode3() {
        return code3;
    }

    /**
     * 设置代码3
     */
    public void setCode3(short code3) {
        this.code3 = code3;
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
     * 获取适合品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置适合品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取中文故障描述
     */
    public String getContentC() {
        return contentC;
    }

    /**
     * 设置中文故障描述
     */
    public void setContentC(String contentC) {
        this.contentC = contentC;
    }

    /**
     * 获取英文故障描述
     */
    public String getContentE() {
        return contentE;
    }

    /**
     * 设置英文故描述
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
