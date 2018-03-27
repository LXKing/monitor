package com.rayton.gps.dao.baseinfo.driver;

import java.sql.Date;
import java.sql.Timestamp;

public class DriverDto {
    public String id;
    public String companyId;
    public String name;
    public String sex;
    public String phone;
    public String idType;
    public String idNumber;
    public String drivingLicenseNumber;
    public Date drivingLicenseExpiryDate;
    public String remark;
    public Timestamp editTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Date getDrivingLicenseExpiryDate() {
        return drivingLicenseExpiryDate;
    }

    public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) {
        this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "DriverDto{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", name='" + name + '\'' + "," +
                "" + "" + "" + " sex='" + sex + '\'' + ", phone='" + phone + '\'' + ", idType='" + idType + '\'' + "," +
                " " + "idNumber='" + idNumber + '\'' + ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' + ", " +
                "" + "drivingLicenseExpiryDate=" + drivingLicenseExpiryDate + ", remark='" + remark + '\'' + ", " +
                "editTime=" + editTime + '}';
    }
}
