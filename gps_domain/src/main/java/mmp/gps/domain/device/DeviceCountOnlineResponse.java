package mmp.gps.domain.device;

/**
 * 设备统计应答
 */
public class DeviceCountOnlineResponse {
    private int total;
    private int online;
    private int offline;

    /**
     * 获取设备总数
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置设备总数
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取在线总数
     */
    public int getOnline() {
        return online;
    }

    /**
     * 设置在线总数
     */
    public void setOnline(int online) {
        this.online = online;
    }

    /**
     * 获取离线总数
     */
    public int getOffline() {
        return offline;
    }

    /**
     * 设置在线总数
     */
    public void setOffline(int offline) {
        this.offline = offline;
    }
}
