package com.edata.godp.domain.security;

import java.util.Date;

/**
 * 登录认证应答
 *
 * @author 生
 */
public class LoginResponse {
    private String token;
    private String account;
    private String id;
    private String name;
    private Date serviceStartTime;
    private Date serviceEndTime;

    /**
     * 获取令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置令牌
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

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
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取服务开始时间
     */
    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    /**
     * 设置服务开始时间
     *
     * @param serviceStartTime 服务开始时间
     */
    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    /**
     * 获取服务结束时间
     */
    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    /**
     * 设置服务结束时间
     *
     * @param serviceEndTime 服务结束时间
     */
    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }
}
