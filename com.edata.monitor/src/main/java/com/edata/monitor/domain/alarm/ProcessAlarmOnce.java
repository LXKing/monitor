package com.edata.monitor.domain.alarm;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

public class ProcessAlarmOnce {
	private String alarmId;

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	private Timestamp alarmTimestamp;

	public Timestamp getAlarmTimestamp() {
		return alarmTimestamp;
	}

	public void setAlarmTimestamp(Timestamp alarmTimestamp) {
		this.alarmTimestamp = alarmTimestamp;
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
