package mmp.gps.domain.device;

import java.util.List;

/**
 * 设备统计请求
 */
public class DeviceCountOnlineRequest {
    private List<String> numbers;
    private int timeOut;

    /**
     * 获取设备号列表
     */
    public List<String> getNumbers() {
        return numbers;
    }

    /**
     * 设置设备号列表
     */
    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    /**
     * 获取超时值
     */
    public int getTimeOut() {
        return timeOut;
    }

    /**
     * 设置超时值,单位：秒
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

}
