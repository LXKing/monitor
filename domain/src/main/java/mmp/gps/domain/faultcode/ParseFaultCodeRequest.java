package mmp.gps.domain.faultcode;

/**
 * 解析故障码请求
 */
public class ParseFaultCodeRequest {
    private short modeId;
    private short code1;
    private short code2;
    private short code3;

    /**
     * 获取故障码模式Id
     */
    public short getModeId() {
        return modeId;
    }

    /**
     * 设置故障码模式Id
     */
    public void setModeId(short modeId) {
        this.modeId = modeId;
    }

    /**
     * 获取故障码字节1
     */
    public short getCode1() {
        return code1;
    }

    /**
     * 设置故障码字节1
     */
    public void setCode1(short code1) {
        this.code1 = code1;
    }

    /**
     * 获取故障码字节2
     */
    public short getCode2() {
        return code2;
    }

    /**
     * 设置故障码字节2
     */
    public void setCode2(short code2) {
        this.code2 = code2;
    }

    /**
     * 获取故障码字节3
     */
    public short getCode3() {
        return code3;
    }

    /**
     * 设置故障码字节3
     */
    public void setCode3(short code3) {
        this.code3 = code3;
    }

}
