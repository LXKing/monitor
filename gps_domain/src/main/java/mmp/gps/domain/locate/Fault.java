package mmp.gps.domain.locate;

import java.util.Date;

public class Fault {

    /**
     * 设备id
     */
    private String number;
    /**
     * GPS时间
     */
    private Date time;
    /**
     * 系统id
     */
    private short sysId;
    /**
     * 模式id
     */
    private short modeId;
    /**
     * 第一字节
     */
    private short code1;
    /**
     * 第二字节
     */
    private short code2;
    /**
     * 第三字节
     */
    private short code3;

    @Override
    public String toString() {
        return "Fault{" + "number='" + number + '\'' + ", time=" + time + ", sysId=" + sysId + ", modeId=" + modeId + ", code1=" + code1 + ", code2=" + code2 + ", code3=" + code3 + '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public short getSysId() {
        return sysId;
    }

    public void setSysId(short sysId) {
        this.sysId = sysId;
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
}
