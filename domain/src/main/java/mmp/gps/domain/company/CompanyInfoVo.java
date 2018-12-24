package mmp.gps.domain.company;

import java.util.Date;
import java.util.List;

public class CompanyInfoVo {

    private String now;

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    /**
     * 公司id
     */
    private String id;
    /**
     * 集团id（即公司的上级id）
     */
    private String pId;
    /**
     * 集团名称
     */
    private String companyPName;
    /**
     * 公司名称
     */
    private String fullName;
    /**
     * 公司地址
     */
    private String officeAddress;
    /**
     * 手机
     */
    private String phone;
    /**
     * 联系人
     */
    private String contact;

    /**
     * 值班电话
     */
    private String ondutyphone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 注册日期
     */
    private Date registdate;

    private List<CompanyInfoVo> nodes;

    public List<CompanyInfoVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<CompanyInfoVo> nodes) {
        this.nodes = nodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOndutyphone() {
        return ondutyphone;
    }

    public void setOndutyphone(String ondutyphone) {
        this.ondutyphone = ondutyphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistdate() {
        return registdate;
    }

    public void setRegistdate(Date registdate) {
        this.registdate = registdate;
    }

    public String getCompanyPName() {
        return companyPName;
    }

    public void setCompanyPName(String companyPName) {
        this.companyPName = companyPName;
    }


    @Override
    public String toString() {
        return "CompanyInfoVo{" + "id='" + id + '\'' + ", pId='" + pId + '\'' + ", companyPName='" + companyPName + '\'' + ", fullName='" + fullName + '\'' + ", officeAddress='" + officeAddress + '\'' + ", phone='" + phone + '\'' + ", contact='" + contact + '\'' + ", ondutyphone='" + ondutyphone + '\'' + ", email='" + email + '\'' + ", registdate=" + registdate + ", nodes=" + nodes + '}';
    }
}
