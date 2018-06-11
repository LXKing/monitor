package com.rayton.gps.dao.cache;

import com.rayton.gps.dao.cache.association.MonitorTarget;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户监控对象
 *
 * @author yangzs
 */
public class UserMonitorTarget {

    private String userId;
    private boolean valid = true;
    private List<MonitorTarget> motorcades = new ArrayList<MonitorTarget>();
    private Map<String, MonitorTarget> devices = new ConcurrentHashMap<String, MonitorTarget>();

    @Override
    public String toString() {
        return "UserMonitorTarget{" + "userId='" + userId + '\'' + ", valid=" + valid + ", motorcades=" + motorcades + ", devices=" + devices + '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<MonitorTarget> getMotorcades() {
        return motorcades;
    }

    /**
     * Key:设备号
     */
    public Map<String, MonitorTarget> getDevices() {
        return devices;
    }
}
