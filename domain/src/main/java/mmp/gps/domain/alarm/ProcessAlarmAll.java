package mmp.gps.domain.alarm;

import org.hibernate.validator.constraints.NotBlank;

public class ProcessAlarmAll {

    private String deviceNumbers;
    private String userMethod;
    @NotBlank
    private String userRemark;

    @Override
    public String toString() {
        return "ProcessAlarmAll{" + "deviceNumbers='" + deviceNumbers + '\'' + ", userMethod='" + userMethod + '\'' + ", userRemark='" + userRemark + '\'' + '}';
    }

    public String getDeviceNumbers() {
        return deviceNumbers;
    }

    public void setDeviceNumbers(String deviceNumbers) {
        this.deviceNumbers = deviceNumbers;
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
