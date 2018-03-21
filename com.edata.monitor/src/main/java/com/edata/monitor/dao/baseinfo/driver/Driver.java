package com.edata.monitor.dao.baseinfo.driver;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 驾驶员类
 *
 * @author yangzs
 */
public class Driver {

    private String id;
    private String companyId;
    @NotBlank
    private String name;
    private String sex;
    private String phone;
    private String idType;
    private String idNumber;
    @NotBlank
    private String drivingLicenseNumber;
    private Date drivingLicenseExpiryDate;
    private String remark;
    private Timestamp editTime;



    // FFFFFFUUUUUUCCCCKKKKKKK
    private Date REGISTRATIONDATE;
    private String PERMITCODE;
    private String EMERGENCYCONTACTA;
    private String EMERGENCYCONTACTB;

    //

    public Date getREGISTRATIONDATE() {
        return REGISTRATIONDATE;
    }

    public void setREGISTRATIONDATE(Date REGISTRATIONDATE) {
        this.REGISTRATIONDATE = REGISTRATIONDATE;
    }

    public String getPERMITCODE() {
        return PERMITCODE;
    }

    public void setPERMITCODE(String PERMITCODE) {
        this.PERMITCODE = PERMITCODE;
    }

    public String getEMERGENCYCONTACTA() {
        return EMERGENCYCONTACTA;
    }

    public void setEMERGENCYCONTACTA(String EMERGENCYCONTACTA) {
        this.EMERGENCYCONTACTA = EMERGENCYCONTACTA;
    }

    public String getEMERGENCYCONTACTB() {
        return EMERGENCYCONTACTB;
    }

    public void setEMERGENCYCONTACTB(String EMERGENCYCONTACTB) {
        this.EMERGENCYCONTACTB = EMERGENCYCONTACTB;
    }

    @Override
    public String toString() {
        return "Driver{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", name='" + name + '\'' + ", " +
                "sex='" + sex + '\'' + ", phone='" + phone + '\'' + ", idType='" + idType + '\'' + ", idNumber='" +
                idNumber + '\'' + ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' + ", " +
                "drivingLicenseExpiryDate=" + drivingLicenseExpiryDate + ", remark='" + remark + '\'' + ", editTime="
                + editTime + ", REGISTRATIONDATE=" + REGISTRATIONDATE + ", PERMITCODE='" + PERMITCODE + '\'' + ", " +
                "EMERGENCYCONTACTA='" + EMERGENCYCONTACTA + '\'' + ", EMERGENCYCONTACTB='" + EMERGENCYCONTACTB + '\'' + '}';
    }

    /**
     * 获取记录唯一编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录唯一编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取公司唯一编号
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司唯一编号
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     */
    public void setSex(String sex) {
        this.sex = sex;
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
     * 获取证件类型
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 设置证件类型
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * 获取证件号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置证件号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 获取驾驶证号
     */
    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    /**
     * 设置驾驶证号
     */
    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    /**
     * 获取驾驶证有效日期
     */
    public Date getDrivingLicenseExpiryDate() {
        return drivingLicenseExpiryDate;
    }

    /**
     * 设置驾驶证有效日期
     */
    public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) {
        this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
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
