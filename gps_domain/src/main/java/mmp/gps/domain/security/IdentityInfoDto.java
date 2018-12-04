package mmp.gps.domain.security;

import java.util.Date;

/**
 * 身份认证信息传输类
 */
public class IdentityInfoDto {
    /**
     * 用户id
     */
    public String id;
    /**
     * 账号
     */
    public String account;
    /**
     * 密码
     */
    public String password;
    /**
     * 用户名称
     */
    public String name;
    /**
     * 服务开始时间
     */
    public Date serviceStartDate;
    /**
     * 服务结束时间
     */
    public Date serviceEndDate;
    /**
     * 启用
     */
    public boolean enabled;
}
