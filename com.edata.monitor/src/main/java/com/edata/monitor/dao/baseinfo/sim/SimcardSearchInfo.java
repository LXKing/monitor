package com.edata.monitor.dao.baseinfo.sim;

public class SimcardSearchInfo {

    private String id;
    private String phoneNumber;
    private String speechType;
    private boolean openSMS;
    private String carrierOperator;
    private String remark;

    @Override
    public String toString() {
        return "SimcardSearchInfo{" + "id='" + id + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", speechType='"
                + speechType + '\'' + ", openSMS=" + openSMS + ", carrierOperator='" + carrierOperator + '\'' + ", "
                + "remark='" + remark + '\'' + '}';
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
