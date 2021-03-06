package mmp.gps.monitor.model.baseinfo;

import mmp.gps.domain.company.Company;
import mmp.gps.domain.user.User;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;

public class CompanyModel {

    private String id;
    private String fullName;
    @NotEmpty
    private String shortName;
    private String organCode;
    private Date registDate;
    private String corporation;
    private String ondutyPhone;
    private String officeAddress;
    private String registAddress;
    private boolean parentVisible;
    private Timestamp companyEditTime;
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
    private Timestamp userEditTime;

    private String ZIPCODE;
    private String FAX;
    private String MAIL;

    public String getZIPCODE() {
        return ZIPCODE;
    }

    public void setZIPCODE(String ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }


    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }

    @Override
    public String toString() {
        return "CompanyModel{" + "id='" + id + '\'' + ", fullName='" + fullName + '\'' + ", shortName='" + shortName + '\'' + ", organCode='" + organCode + '\'' + ", registDate=" + registDate + ", corporation='" + corporation + '\'' + ", ondutyPhone='" + ondutyPhone + '\'' + ", officeAddress='" + officeAddress + '\'' + ", registAddress='" + registAddress + '\'' + ", parentVisible=" + parentVisible + ", " + "companyEditTime=" + companyEditTime + ", pid='" + pid + '\'' + ", companyId='" + companyId + '\'' + ", enums=" + kind + ", account='" + account + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", contact='" + contact + '\'' + ", enable=" + enable + ", " + "serviceStartDate=" + serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", createTime=" + createTime + ", remark='" + remark + '\'' + ", userEditTime=" + userEditTime + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取组织机构编号
     */
    public String getOrganCode() {
        return organCode;
    }

    /**
     * 设置组织机构编号
     */
    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    /**
     * 获取注册日期
     */
    public Date getRegistDate() {
        return registDate;
    }

    /**
     * 设置注册日期
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    /**
     * 获取法人代表
     */
    public String getCorporation() {
        return corporation;
    }

    /**
     * 设置法人代表
     */
    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    /**
     * 获取24小时值班电话
     */
    public String getOndutyPhone() {
        return ondutyPhone;
    }

    /**
     * 设置24小时值班电话
     */
    public void setOndutyPhone(String ondutyPhone) {
        this.ondutyPhone = ondutyPhone;
    }

    /**
     * 获取办公地址
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * 设置办公地址
     */
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    /**
     * 获取注册地址
     */
    public String getRegistAddress() {
        return registAddress;
    }

    /**
     * 设置注册地址
     */
    public void setRegistAddress(String registAddress) {
        this.registAddress = registAddress;
    }

    /**
     * 获取上级可见否
     */
    public boolean isParentVisible() {
        return parentVisible;
    }

    /**
     * 设置上级可见否
     */
    public void setParentVisible(boolean parentVisible) {
        this.parentVisible = parentVisible;
    }

    /**
     * 获取公司记录时间戳
     */
    public Timestamp getCompanyEditTime() {
        return companyEditTime;
    }

    /**
     * 设置公司记录时间戳
     */
    public void setCompanyEditTime(Timestamp companyEditTime) {
        this.companyEditTime = companyEditTime;
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
    public Timestamp getUserEditTime() {
        return userEditTime;
    }

    /**
     * 设置时间戳
     */
    public void setUserEditTime(Timestamp userEditTime) {
        this.userEditTime = userEditTime;
    }

    public void fill(User user) {
        id = user.getId();
        pid = user.getPid();
        companyId = user.getCompanyId();
        kind = user.getKind();
        account = user.getAccount();
        name = user.getName();
        email = user.getEmail();
        phone = user.getPhone();
        contact = user.getContact();
        createTime = user.getCreateTime();
        enable = user.isEnable();
        userEditTime = user.getEditTime();
        serviceStartDate = user.getServiceStartDate();
        serviceEndDate = user.getServiceEndDate();
        remark = user.getRemark();
    }

    public void fill(Company company) {
        id = company.getId();
        fullName = company.getFullName();
        shortName = company.getShortName();
        organCode = company.getOrganCode();
        registDate = company.getRegistDate();
        corporation = company.getCorporation();
        ondutyPhone = company.getOndutyPhone();
        officeAddress = company.getOfficeAddress();
        registAddress = company.getRegistAddress();
        parentVisible = company.isParentVisible();
        companyEditTime = company.getEditTime();
        ZIPCODE = company.getZIPCODE();
        FAX = company.getFAX();
        MAIL = company.getEMAIL();
    }

    public User getUser() {
        User user = new User();

        user.setId(id);
        user.setPid(pid);
        user.setCompanyId(companyId);
        user.setKind(kind);
        user.setAccount(account);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setContact(contact);
        user.setCreateTime(createTime);
        user.setEnable(enable);
        user.setEditTime(userEditTime);
        user.setServiceStartDate(serviceStartDate);
        user.setServiceEndDate(serviceEndDate);
        user.setRemark(remark);

        return user;
    }

    public Company getCompany() {
        Company company = new Company();

        company.setId(id);
        company.setFullName(fullName);
        company.setShortName(shortName);
        company.setOrganCode(organCode);
        company.setRegistDate(registDate);
        company.setCorporation(corporation);
        company.setOndutyPhone(ondutyPhone);
        company.setOfficeAddress(officeAddress);
        company.setRegistAddress(registAddress);
        company.setParentVisible(parentVisible);
        company.setEditTime(companyEditTime);
        company.setZIPCODE(ZIPCODE);
        company.setFAX(FAX);
        company.setEMAIL(MAIL);
        return company;
    }
}
