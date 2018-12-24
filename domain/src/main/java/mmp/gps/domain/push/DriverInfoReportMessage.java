package mmp.gps.domain.push;

import java.util.Date;

/**
 * 驾驶员信息报告消息
 */
public class DriverInfoReportMessage {
    /**
     * 设备号
     */
    public String number;
    /**
     * IC卡状态
     */
    public String status;
    /**
     * 时间
     */
    public Date time;
    /**
     * IC卡读取结果
     */
    public String result;
    /**
     * 驾驶员姓名
     */
    public String name;
    /**
     * 证书
     */
    public String certificate;
    /**
     * 发证机构
     */
    public String issuingAgency;
    /**
     * 有效期
     */
    public Date limit;

}
