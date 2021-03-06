package mmp.gps.domain.security;

import java.util.Date;

public class OperateLog {


    // @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    // @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")

    // @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String companyId;
    private String userId;
    private String user;
    private String action;

    @Override
    public String toString() {
        return "OperateLog{" + "time=" + time + ", companyId='" + companyId + '\'' + ", userId='" + userId + '\'' + "," + " user='" + user + '\'' + ", action='" + action + '\'' + '}';
    }


    // @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
