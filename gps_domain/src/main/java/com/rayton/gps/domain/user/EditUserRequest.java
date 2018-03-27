package com.rayton.gps.domain.user;

import java.sql.Timestamp;

public class EditUserRequest extends CreateUserRequest {
    private String id;
    private Timestamp editTime;

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
