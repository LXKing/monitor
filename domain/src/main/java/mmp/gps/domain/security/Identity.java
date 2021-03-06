package mmp.gps.domain.security;

import java.util.Arrays;

/**
 * 身份认证
 */
public class Identity {

    private String id;
    private String companyId;
    private String unid;
    private String account;
    private String name;
    private int kind;
    private String[] roles;

    @Override
    public String toString() {
        return "Identity{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", unid='" + unid + '\'' + ", " + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "account='" + account + '\'' + ", name='" + name + '\'' + ", " + "" + "enums=" + kind + ", " + "" + "roles=" + Arrays
                .toString(roles) + '}';
    }

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
     * 获取公司id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司id
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取用户唯一id
     */
    public String getUnid() {
        return unid;
    }

    /**
     * 设置用户唯一id
     */
    public void setUnid(String unid) {
        this.unid = unid;
    }

    /**
     * 获取帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置帐号
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
     * 获取用户类型
     */
    public int getKind() {
        return kind;
    }

    /**
     * 设置用户类型
     */
    public void setKind(int kind) {
        this.kind = kind;
    }

    /**
     * 获取角色列表
     */
    public String[] getRoles() {
        return roles;
    }

    /**
     * 设置角色列表
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

}
