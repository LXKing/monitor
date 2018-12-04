package mmp.gps.domain.motorcade;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 车队类
 */
public class Motorcade {

    private String id;
    private String companyId;

    private String PID;
    private String COMPANY;
    private String type;
    private String name;
    private boolean parentVisible;
    private String linkMan;
    private String phone;
    private String remark;
    private Timestamp editTime;
    //
    @JSONField(format = "yyyy-MM-dd")
    private Date REGISTATIONDATE;
    private String BUSSINESSAGENT;

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }
    //

    @Override
    public String toString() {
        return "Motorcade{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", type='" + type + '\'' + "," + "" + "" + "" + "" + "" + "" + "" + "" + "" + " name='" + name + '\'' + ", parentVisible=" + parentVisible + ", " + "" + "linkMan='" + linkMan + '\'' + ", " + "phone='" + phone + '\'' + ", " + "remark='" + remark + '\'' + ", " + "editTime=" + editTime + "," + " " + "REGISTATIONDATE=" + REGISTATIONDATE + ", " + "BUSSINESSAGENT='" + BUSSINESSAGENT + '\'' + '}';
    }

    public Date getREGISTATIONDATE() {
        return REGISTATIONDATE;
    }

    public void setREGISTATIONDATE(Date REGISTATIONDATE) {
        this.REGISTATIONDATE = REGISTATIONDATE;
    }

    public String getBUSSINESSAGENT() {
        return BUSSINESSAGENT;
    }

    public void setBUSSINESSAGENT(String BUSSINESSAGENT) {
        this.BUSSINESSAGENT = BUSSINESSAGENT;
    }

    /**
     * 获取记录ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取公司ID
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取车队类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置车队类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取车队联系人
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * 设置车队联系人
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    /**
     * 获取车队联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置车队联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取时间戳
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 设置时间戳
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }
}
