package mmp.gps.domain.company;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 公司资料
 */
public class Company {

    private String id;

    private String PID;
    private String ZIPCODE;
    private String FAX;
    private String EMAIL;
    private String fullName;
    @NotEmpty
    private String shortName;
    private String organCode;
    @JSONField(format = "yyyy-MM-dd")
    private Date registDate;
    private String corporation;
    private String ondutyPhone;
    private String officeAddress;
    private String registAddress;
    private boolean parentVisible;
    private Timestamp editTime;

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getZIPCODE() {
        return ZIPCODE;
    }

    public void setZIPCODE(String ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }

    @Override
    public String toString() {
        return "Company{" + "id='" + id + '\'' + ", PID='" + PID + '\'' + ", ZIPCODE='" + ZIPCODE + '\'' + ", " + "fullName='" + fullName + '\'' + ", shortName='" + shortName + '\'' + ", organCode='" + organCode + '\'' + ", registDate=" + registDate + ", corporation='" + corporation + '\'' + ", ondutyPhone='" + ondutyPhone + '\'' + ", officeAddress='" + officeAddress + '\'' + ", registAddress='" + registAddress + '\'' + ", parentVisible=" + parentVisible + ", editTime=" + editTime + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取组织机构编号
     */
    public String getOrganCode() {
        return organCode;
    }

    /**
     * 设置组织机构编号
     */
    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    /**
     * 获取注册日期
     */
    public Date getRegistDate() {
        return registDate;
    }

    /**
     * 设置注册日期
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    /**
     * 获取法人代表
     */
    public String getCorporation() {
        return corporation;
    }

    /**
     * 设置法人代表
     */
    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    /**
     * 获取24小时值班电话
     */
    public String getOndutyPhone() {
        return ondutyPhone;
    }

    /**
     * 设置24小时值班电话
     */
    public void setOndutyPhone(String ondutyPhone) {
        this.ondutyPhone = ondutyPhone;
    }

    /**
     * 获取办公地址
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * 设置办公地址
     */
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    /**
     * 获取注册地址
     */
    public String getRegistAddress() {
        return registAddress;
    }

    /**
     * 设置注册地址
     */
    public void setRegistAddress(String registAddress) {
        this.registAddress = registAddress;
    }

    /**
     * 获取上级可见否
     */
    public boolean isParentVisible() {
        return parentVisible;
    }

    /**
     * 设置上级可见否
     */
    public void setParentVisible(boolean parentVisible) {
        this.parentVisible = parentVisible;
    }

    /**
     * 获取公司记录时间戳
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 设置公司记录时间戳
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }
}
