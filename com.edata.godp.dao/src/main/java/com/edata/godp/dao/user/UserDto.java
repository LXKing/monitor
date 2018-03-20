package com.edata.godp.dao.user;

import java.sql.Timestamp;

public class UserDto {
    /**
     * 用户id
     */
    public String id;
    /**
     * 帐号
     */
    public String account;
    /**
     * 密码
     */
    public String password;
    /**
     * 名称
     */
    public String name;
    /**
     * 服务开始时间
     */
    public java.sql.Date serviceStartDate;
    /**
     * 服务结束时间
     */
    public java.sql.Date serviceEndDate;
    /**
     * 启用
     */
    public boolean enabled;
    /**
     * 推送路径
     */
    public String pushUrl;
    /**
     * 邮箱
     */
    public String email;
    /**
     * 电话
     */
    public String phone;
    /**
     * 联系人
     */
    public String contact;
    /**
     * 备注
     */
    public String remark;
    /**
     * 创建时间
     */
    public java.util.Date createTime;
    /**
     * 时间戳
     */
    public Timestamp editTime;

}
