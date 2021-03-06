package mmp.gps.domain.gateway;

import java.util.Date;

/**
 * 设备上线下线通知数据传输类
 */
public class DeviceOnlineOfflineReportDto {
    /**
     * 设备号
     */
    public String number;

    /**
     * 时间
     */
    public Date time;

    /**
     * 上线：true，离线：false
     */
    public boolean on;

    /**
     * 在线时长：秒
     */
    public int onTime;

    /**
     * 获取离线时长：秒
     */
    public int offTime;
}
