package com.edata.godp.dao.gateway;

import java.util.Date;

/**
 * 故障信息传输类
 *
 * @author 生
 */
public class FaultDto {
    /**
     * 设备id
     */
    public String number;
    /**
     * GPS时间
     */
    public Date time;
    /**
     * 系统id
     */
    public short sysId;
    /**
     * 模式id
     */
    public short modeId;
    /**
     * 第一字节
     */
    public short code1;
    /**
     * 第二字节
     */
    public short code2;
    /**
     * 第三字节
     */
    public short code3;

}
