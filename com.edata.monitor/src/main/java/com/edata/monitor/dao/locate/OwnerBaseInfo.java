package com.edata.monitor.dao.locate;

/**
 * 车主基本资料类
 *
 * @author yangzs
 */
public class OwnerBaseInfo {

    private String ownerName;
    private String phone;
    private String idType;
    private String idNumber;
    private String email;
    private String remark;

    @Override
    public String toString() {
        return "OwnerBaseInfo{" + "ownerName='" + ownerName + '\'' + ", phone='" + phone + '\'' + ", idType='" +
                idType + '\'' + ", idNumber='" + idNumber + '\'' + ", email='" + email + '\'' + ", remark='" + remark
                + '\'' + '}';
    }

    /**
     * 获取姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
     * 获取证件编号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置证件编号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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
