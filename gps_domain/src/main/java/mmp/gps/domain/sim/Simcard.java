package mmp.gps.domain.sim;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * sim卡类
 */
public class Simcard {

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    public Date createTime;
    /**
     * sim卡id
     */
    private String id;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * sim卡号
     */
    private String simcardNumber;
    /**
     * 语音类型
     */
    private String speechType;
    /**
     * 是否开通短信
     */
    private boolean openSMS;
    /**
     * 开通日期(SIM卡激活时间)
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date openDate;
    /**
     * 购买日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date purchaseDate;
    /**
     * 运营商
     */
    private String carrierOperator;
    /**
     * 预交费
     */
    private double prepayment;
    /**
     * 到期日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date expireDate;
    /**
     * 流量套餐
     */
    private String flowset;
    /**
     * 备注
     */
    private String remark;
    private Timestamp editTime;

    /**
     * SIM卡开户名
     */
    private String accountName;
    /**
     * SIM卡月租
     */
    private String monthlyrent;
    /**
     * SIM卡入库时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date instockDate;
    /**
     * SIM卡出库时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date outstockDate;
    /**
     * SIM卡费到期前提示时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date expirenotifyDate;
    // FUCK ME
    /**
     * ICCID
     */
    private String iccid;
    /**
     * MISDN
     */
    private String misdn;

    /**
     * 终端id绑定时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date equtpmentbindDate;
    /**
     * sim服务状态
     */
    private boolean simState;

    private String equid;

    @Override
    public String toString() {
        return "Simcard{" +
                "createTime=" + createTime +
                ", id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", simcardNumber='" + simcardNumber + '\'' +
                ", speechType='" + speechType + '\'' +
                ", openSMS=" + openSMS +
                ", openDate=" + openDate +
                ", purchaseDate=" + purchaseDate +
                ", carrierOperator='" + carrierOperator + '\'' +
                ", prepayment=" + prepayment +
                ", expireDate=" + expireDate +
                ", flowset='" + flowset + '\'' +
                ", remark='" + remark + '\'' +
                ", editTime=" + editTime +
                ", accountName='" + accountName + '\'' +
                ", monthlyrent='" + monthlyrent + '\'' +
                ", instockDate=" + instockDate +
                ", outstockDate=" + outstockDate +
                ", expirenotifyDate=" + expirenotifyDate +
                ", iccid='" + iccid + '\'' +
                ", misdn='" + misdn + '\'' +
                ", equtpmentbindDate=" + equtpmentbindDate +
                ", simState=" + simState +
                '}';
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMonthlyrent() {
        return monthlyrent;
    }

    public void setMonthlyrent(String monthlyrent) {
        this.monthlyrent = monthlyrent;
    }

    public Date getInstockDate() {
        return instockDate;
    }

    public void setInstockDate(Date instockDate) {
        this.instockDate = instockDate;
    }

    public Date getOutstockDate() {
        return outstockDate;
    }

    public void setOutstockDate(Date outstockDate) {
        this.outstockDate = outstockDate;
    }

    public Date getExpirenotifyDate() {
        return expirenotifyDate;
    }

    public void setExpirenotifyDate(Date expirenotifyDate) {
        this.expirenotifyDate = expirenotifyDate;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getMisdn() {
        return misdn;
    }

    public void setMisdn(String misdn) {
        this.misdn = misdn;
    }

    public Date getEqutpmentbindDate() {
        return equtpmentbindDate;
    }

    public void setEqutpmentbindDate(Date equtpmentbindDate) {
        this.equtpmentbindDate = equtpmentbindDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isSimState() {
        return simState;
    }

    public void setSimState(boolean simState) {
        this.simState = simState;
    }

    public String getEquid() {
        return equid;
    }

    public void setEquid(String equid) {
        this.equid = equid;
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
