package mmp.gps.domain.faultcode;

public class FaultCodeInfo {
    private String id;
    private short level;
    private short modeId;
    private short code1;
    private short code2;
    private short code3;
    private String pcode;
    private String contentC;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getContentC() {
        return contentC;
    }

    public void setContentC(String contentC) {
        this.contentC = contentC;
    }

}
