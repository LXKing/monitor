package com.rayton.gps.dao.permission;

import java.sql.Timestamp;

/**
 * 权限类
 *
 * @author yangzs
 */
public class Permission {

    private String id;
    private String name;
    private String remark;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "Permission{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", remark='" + remark + '\'' + ", " +
                "editTime=" + editTime + '}';
    }

    /**
     * 获取唯一编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编号
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
