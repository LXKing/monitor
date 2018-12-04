package mmp.gps.domain.user;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.sql.Date;

public class CreateUserRequest {
    @Size(min = 3, max = 50)
    private String account;
    @Size(min = 6, max = 50)
    private String password;
    @Size(min = 3, max = 50)
    private String name;
    private Date serviceStartTime;
    private Date serviceEndTime;
    private boolean enabled;
    private String pushUrl;
    @Email
    private String email;
    private String phone;
    private String contact;
    private String remark;

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
     * 获取密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
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
    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    /**
     * 设置服务开始时间
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
     */
    public void setServiceEndTime(Date serviceEndTime) {
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
     * 获取联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
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
}
