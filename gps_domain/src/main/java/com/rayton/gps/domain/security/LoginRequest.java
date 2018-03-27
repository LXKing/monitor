package com.rayton.gps.domain.security;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 登录认证请求
 *
 * @author 生
 */
public class LoginRequest {
    @NotNull
    @Size(min = 3, max = 50)
    private String account;
    @NotNull
    @Size(min = 6, max = 50)
    private String pwd;

    /**
     * 获取帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置帐号
     *
     * @param account 帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
