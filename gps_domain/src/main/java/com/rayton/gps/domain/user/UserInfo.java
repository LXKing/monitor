package com.rayton.gps.domain.user;

public class UserInfo {
    private String id;
    private String account;
    private String name;
    private java.sql.Date serviceStartTime;
    private java.sql.Date serviceEndTime;
    private boolean enabled;
    private String pushUrl;
    private String remark;
    private java.util.Date createTime;

    /**
     * 获取用户id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     */
    public void setAccount(String account) {
        this.account = account;
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
     * 获取服务开始时间
     */
    public java.sql.Date getServiceStartTime() {
        return serviceStartTime;
    }

    /**
     * 设置服务开始时间
     */
    public void setServiceStartTime(java.sql.Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    /**
     * 获取服务结束时间
     */
    public java.sql.Date getServiceEndTime() {
        return serviceEndTime;
    }

    /**
     * 设置服务结束时间
     */
    public void setServiceEndTime(java.sql.Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    /**
     * 获取启用否
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 设置启用否
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取推送路径
     */
    public String getPushUrl() {
        return pushUrl;
    }

    /**
     * 设置推送路径
     */
    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    /**
     * 设置备注
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
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

}
