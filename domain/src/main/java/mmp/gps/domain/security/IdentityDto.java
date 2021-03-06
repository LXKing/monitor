package mmp.gps.domain.security;

import java.io.Serializable;
import java.sql.Date;

public class IdentityDto implements Serializable {
    public String id;
    public String unid;
    public String companyId;
    public String account;
    public String password;
    public String name;
    public int kind;
    public Date serviceStartTime;
    public Date serviceEndTime;
    public boolean enable;

    public String FULLNAME;
    private String[] roles;

    public String getFULLNAME() {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
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
        return "IdentityDto{" + "id='" + id + '\'' + ", unid='" + unid + '\'' + ", companyId='" + companyId + '\'' + ", account='" + account + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", " + "kind=" + kind + ", serviceStartTime=" + serviceStartTime + ", serviceEndTime=" + serviceEndTime + "," + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + " enable=" + enable + '}';
    }
}
