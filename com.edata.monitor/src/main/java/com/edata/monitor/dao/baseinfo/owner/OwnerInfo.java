package com.edata.monitor.dao.baseinfo.owner;

import java.sql.Date;

public class OwnerInfo {
    private String id;
    private String ownerName;
    private String phone;
    private String idType;
    private String idNumber;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private boolean enable;
    private Date createTime;
    private String remark;

    @Override
    public String toString() {
        return "OwnerInfo{" + "id='" + id + '\'' + ", ownerName='" + ownerName + '\'' + ", phone='" + phone + '\'' +
                ", idType='" + idType + '\'' + ", idNumber='" + idNumber + '\'' + ", serviceStartDate=" +
                serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", enable=" + enable + ", createTime=" +
                createTime + ", remark='" + remark + '\'' + '}';
    }

    /**
     * 获取记录唯一编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录唯一编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 获取电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取证件类型
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 设置证件类型
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * 获取证件编号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置证件编号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取服务开始时间
     */
    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    /**
     * 设置服务开始时间
     */
    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    /**
     * 获取服务结束时间
     */
    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    /**
     * 设置服务结束时间
     */
    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    /**
     * 获取启用否
     */


    /**
     * 设置启用否
     */

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取入网时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置入网时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
