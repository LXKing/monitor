package com.rayton.gps.dao.alarm;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

public class ProcessAlarmOnce {

    private String alarmId;
    private Timestamp alarmTimestamp;
    private String userMethod;
    @NotBlank
    private String userRemark;

    @Override
    public String toString() {
        return "ProcessAlarmOnce{" + "alarmId='" + alarmId + '\'' + ", alarmTimestamp=" + alarmTimestamp + ", " +
                "userMethod='" + userMethod + '\'' + ", userRemark='" + userRemark + '\'' + '}';
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public Timestamp getAlarmTimestamp() {
        return alarmTimestamp;
    }

    public void setAlarmTimestamp(Timestamp alarmTimestamp) {
        this.alarmTimestamp = alarmTimestamp;
    }

    public String getUserMethod() {
        return userMethod;
    }

    public void setUserMethod(String userMethod) {
        this.userMethod = userMethod;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }
}
