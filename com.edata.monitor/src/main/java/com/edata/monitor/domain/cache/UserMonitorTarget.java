package com.edata.monitor.domain.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

/**
 * 用户监控对象
 * 
 * @author yangzs
 *
 */
public class UserMonitorTarget {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private boolean valid = true;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	private List<MonitorTarget> motorcades = new ArrayList<MonitorTarget>();

	public List<MonitorTarget> getMotorcades() {
		return motorcades;
	}

	private Map<String, MonitorTarget> devices = new ConcurrentHashMap<String, MonitorTarget>();

	/**
	 * Key:设备号
	 */
	public Map<String, MonitorTarget> getDevices() {
		return devices;
	}
}
