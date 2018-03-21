package com.edata.monitor.dao.baseinfo.user;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 用户类
 *
 * @author 生
 */
public class User {
    private String password;
    private String id;
    private String pid;
    private String companyId;
    private int kind;
    @NotBlank
    private String account;
    @NotBlank
    private String name;
    private String email;
    private String phone;
    private String contact;
    private boolean enable;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private Date createTime;
    private String remark;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "User{" + "password='" + password + '\'' + ", id='" + id + '\'' + ", pid='" + pid + '\'' + ", " +
                "companyId='" + companyId + '\'' + ", kind=" + kind + ", account='" + account + '\'' + ", name='" +
                name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", contact='" + contact +
                '\'' + ", enable=" + enable + ", serviceStartDate=" + serviceStartDate + ", serviceEndDate=" +
                serviceEndDate + ", createTime=" + createTime + ", remark='" + remark + '\'' + ", editTime=" +
                editTime + '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
     * 获取用户类型：1公司，2公司用户，3车主，4设备
     */
    public int getKind() {
        return kind;
    }

    /**
     * 设置用户类型：1公司，2公司用户，3车主，4设备
     */
    public void setKind(int kind) {
        this.kind = kind;
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
     * 获取邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
     * 获取联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
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
     * 获取服务开始时间
     */
    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    /**
     * 设置服务开始时间
     */
    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
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
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
