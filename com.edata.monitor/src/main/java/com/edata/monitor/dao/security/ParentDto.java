package com.edata.monitor.dao.security;

import java.sql.Date;

public class ParentDto {
    public String companyId;
    public Date serviceStartTime;
    public Date serviceEndTime;
    public boolean enable;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ParentDto{" + "companyId='" + companyId + '\'' + ", serviceStartTime=" + serviceStartTime + ", " +
                "serviceEndTime=" + serviceEndTime + ", enable=" + enable + '}';
    }
}
