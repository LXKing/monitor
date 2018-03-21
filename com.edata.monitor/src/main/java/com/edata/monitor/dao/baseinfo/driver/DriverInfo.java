package com.edata.monitor.dao.baseinfo.driver;

/**
 * 驾驶员信息
 *
 * @author yangzs
 */
public class DriverInfo {

    private String id;
    private String name;
    private String sex;
    private String phone;
    private String drivingLicenseNumber;
    private String remark;

    @Override
    public String toString() {
        return "DriverInfo{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", phone='" +
                phone + '\'' + ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' + ", remark='" + remark +
                '\'' + '}';
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
}
