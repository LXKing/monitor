package com.rayton.gps.dao.baseinfo.device;

import java.sql.Timestamp;

/**
 * 用户组
 *
 * @author 生
 */
public class DeviceGroup {

    private String id;
    private String pid;
    private String userId;
    private String name;
    private String remark;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "DeviceGroup{" + "id='" + id + '\'' + ", pid='" + pid + '\'' + ", userId='" + userId + '\'' + ", " +
                "name='" + name + '\'' + ", remark='" + remark + '\'' + ", editTime=" + editTime + '}';
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
     * 获取父ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父ID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
