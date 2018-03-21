package com.edata.monitor.dao.baseinfo.sim;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * sim卡类
 *
 * @author yangzs
 */
public class Simcard {

    /**
     * 创建时间
     */
    public Date createTime;
    private String id;
    private String companyId;
    private String phoneNumber;
    private String simcardNumber;
    private String speechType;
    private boolean openSMS;
    private Date openDate;
    private Date purchaseDate;
    private String carrierOperator;
    private double prepayment;
    private Date expireDate;
    private String flowset;
    private String remark;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "Simcard{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", phoneNumber='" + phoneNumber
                + '\'' + ", simcardNumber='" + simcardNumber + '\'' + ", speechType='" + speechType + '\'' + ", " +
                "openSMS=" + openSMS + ", openDate=" + openDate + ", purchaseDate=" + purchaseDate + ", " +
                "carrierOperator='" + carrierOperator + '\'' + ", prepayment=" + prepayment + ", expireDate=" +
                expireDate + ", flowset='" + flowset + '\'' + ", remark='" + remark + '\'' + ", editTime=" + editTime
                + ", createTime=" + createTime + '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取唯一编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编号
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
     * 获取电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取sim卡号
     */
    public String getSimcardNumber() {
        return simcardNumber;
    }

    /**
     * 设置sim卡号
     */
    public void setSimcardNumber(String simcardNumber) {
        this.simcardNumber = simcardNumber;
    }

    /**
     * 获取语音类型
     */
    public String getSpeechType() {
        return speechType;
    }

    /**
     * 设置语音类型
     */
    public void setSpeechType(String speechType) {
        this.speechType = speechType;
    }

    /**
     * 获取是否开通短信
     */
    public boolean isOpenSMS() {
        return openSMS;
    }

    /**
     * 设置是否开通短信
     */
    public void setOpenSMS(boolean openSMS) {
        this.openSMS = openSMS;
    }

    /**
     * 获取开通日期
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * 设置开通日期
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 获取购买日期
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 设置购买日期
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * 获取运营商
     */
    public String getCarrierOperator() {
        return carrierOperator;
    }

    /**
     * 设置运营商
     */
    public void setCarrierOperator(String carrierOperator) {
        this.carrierOperator = carrierOperator;
    }

    /**
     * 获取预交费
     */
    public double getPrepayment() {
        return prepayment;
    }

    /**
     * 设置预交费
     */
    public void setPrepayment(double prepayment) {
        this.prepayment = prepayment;
    }

    /**
     * 获取到期日期
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置到期日期
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取流量套餐
     */
    public String getFlowset() {
        return flowset;
    }

    /**
     * 设置流量套餐
     */
    public void setFlowset(String flowset) {
        this.flowset = flowset;
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
