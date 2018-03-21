package com.edata.monitor.dao.baseinfo.sysvid;

import java.util.Date;

public class Sysvid {
    private String sysvid;

    private Date sysvidactivationdate;

    private Date expiredate;

    private Date expirenotifydate;

    private String bussinessagent;

    public String getSysvid() {
        return sysvid;
    }

    public void setSysvid(String sysvid) {
        this.sysvid = sysvid == null ? null : sysvid.trim();
    }

    public Date getSysvidactivationdate() {
        return sysvidactivationdate;
    }

    public void setSysvidactivationdate(Date sysvidactivationdate) {
        this.sysvidactivationdate = sysvidactivationdate;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public Date getExpirenotifydate() {
        return expirenotifydate;
    }

    public void setExpirenotifydate(Date expirenotifydate) {
        this.expirenotifydate = expirenotifydate;
    }

    public String getBussinessagent() {
        return bussinessagent;
    }

    public void setBussinessagent(String bussinessagent) {
        this.bussinessagent = bussinessagent == null ? null : bussinessagent.trim();
    }
}