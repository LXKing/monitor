package com.edata.monitor.dao.baseinfo.user;

import java.sql.Date;

/**
 * 用户信息类
 *
 * @author 生
 */
public class UserInfo {
    private String id;
    private String name;
    private boolean enable;
    private String phone;
    private Date ServiceStartDate;
    private Date serviceEndDate;
    private String remark;
    private Date createTime;

    @Override
    public String toString() {
        return "UserInfo{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", enable=" + enable + ", phone='" +
                phone + '\'' + ", ServiceStartDate=" + ServiceStartDate + ", serviceEndDate=" + serviceEndDate + ", "
                + "remark='" + remark + '\'' + ", createTime=" + createTime + '}';
    }

    /**
     * 获取ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     */
    public void setId(String id) {
        this.id = id;
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
     * 获取是否启用
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
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
     * 获取服务开始时间
     */
    public Date getServiceStartDate() {
        return ServiceStartDate;
    }

    /**
     * 设置服务开始时间
     */
    public void setServiceStartDate(Date serviceStartDate) {
        ServiceStartDate = serviceStartDate;
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
     * 获取注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
