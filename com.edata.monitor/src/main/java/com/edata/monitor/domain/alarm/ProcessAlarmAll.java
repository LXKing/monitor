package com.edata.monitor.domain.alarm;

import org.hibernate.validator.constraints.NotBlank;

public class ProcessAlarmAll {
	private String deviceNumbers;

	public String getDeviceNumbers() {
		return deviceNumbers;
	}

	public void setDeviceNumbers(String deviceNumbers) {
		this.deviceNumbers = deviceNumbers;
	}

	private String userMethod;

	public String getUserMethod() {
		return userMethod;
	}

	public void setUserMethod(String userMethod) {
		this.userMethod = userMethod;
	}

	@NotBlank
	private String userRemark;

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
}
