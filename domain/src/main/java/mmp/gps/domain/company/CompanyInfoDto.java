package mmp.gps.domain.company;

import java.sql.Date;

public class CompanyInfoDto {
    public String id;
    public boolean enable;
    public Date serviceStartDate;
    public Date serviceEndDate;
    public Date createTime;
    public String remark;
    public String fullName;
    public String shortName;
    public String officeAddress;
    public String ondutyPhone;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    @Override
    public String toString() {
        return "CompanyInfoDto{" + "enable=" + enable + '}';
    }
}
