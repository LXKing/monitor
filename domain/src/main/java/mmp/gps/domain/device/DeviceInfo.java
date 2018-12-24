package mmp.gps.domain.device;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;

public class DeviceInfo {


    private java.util.Date gpstime;


    private byte online;


    private String motorcade;

    public byte getOnline() {
        return online;
    }

    public String getMotorcade() {
        return motorcade;
    }

    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }


    public void setOnline(byte online) {
        this.online = online;
    }

    public java.util.Date getGpstime() {
        return gpstime;
    }

    public void setGpstime(java.util.Date gpstime) {
        this.gpstime = gpstime;
    }

    private String id;
    private String deviceNumber;
    private String phoneNumber;
    private String factoryNumber;
    private String model;
    private boolean enable;
    @JSONField(format = "yyyy-MM-dd")
    private Date serviceStartDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date serviceEndDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date warranty;
    private String remark;
    private String protocolName;
    private String Protocol;
    //
    private String SN;
    @JSONField(format = "yyyy-MM-dd")
    private Date INSTOCKDATE;
    @JSONField(format = "yyyy-MM-dd")
    private Date OUTSTOCKDATE;
    @JSONField(format = "yyyy-MM-dd")
    private Date ACTIVATIONDATE;
    private String IMEI;
    private String LIFECYCLE;
    @JSONField(format = "yyyy-MM-dd")
    private Date LIFEEXPIRATIONDATE;
    private String repairman;//维修员

    private String EDITUSER;

    @JSONField(format = "yyyy-MM-dd")
    private Date maintenanceTime;//维修时间

    private boolean terminalServiceState;//终端服务状态

    private String causeofFailure;//故障原因

    private String remarks;//备注

    private String vid;


    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }
    //

   /* @Override
    public String toString() {
        return "DeviceInfo{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", factoryNumber='" + factoryNumber + '\'' + ", model='" + model + '\'' + ", " + "enable=" + enable + ", serviceStartDate=" + serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", warranty=" + warranty + ", remark='" + remark + '\'' + ", SN='" + SN + '\'' + ", INSTOCKDATE=" + INSTOCKDATE + ", OUTSTOCKDATE=" + OUTSTOCKDATE + ", ACTIVATIONDATE=" + ACTIVATIONDATE + ", IMEI='" + IMEI + '\'' + ", LIFECYCLE='" + LIFECYCLE + '\'' + ", LIFEEXPIRATIONDATE=" + LIFEEXPIRATIONDATE + '}';
    }*/

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "id='" + id + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", factoryNumber='" + factoryNumber + '\'' +
                ", model='" + model + '\'' +
                ", enable=" + enable +
                ", serviceStartDate=" + serviceStartDate +
                ", serviceEndDate=" + serviceEndDate +
                ", warranty=" + warranty +
                ", remark='" + remark + '\'' +
                ", protocolName='" + protocolName + '\'' +
                ", Protocol='" + Protocol + '\'' +
                ", SN='" + SN + '\'' +
                ", INSTOCKDATE=" + INSTOCKDATE +
                ", OUTSTOCKDATE=" + OUTSTOCKDATE +
                ", ACTIVATIONDATE=" + ACTIVATIONDATE +
                ", IMEI='" + IMEI + '\'' +
                ", LIFECYCLE='" + LIFECYCLE + '\'' +
                ", LIFEEXPIRATIONDATE=" + LIFEEXPIRATIONDATE +
                ", repairman='" + repairman + '\'' +
                ", EDITUSER='" + EDITUSER + '\'' +
                ", maintenanceTime=" + maintenanceTime +
                ", terminalServiceState=" + terminalServiceState +
                ", causeofFailure='" + causeofFailure + '\'' +
                ", remarks='" + remarks + '\'' +
                ", vid='" + vid + '\'' +
                '}';
    }

    public String getEDITUSER() {
        return EDITUSER;
    }

    public void setEDITUSER(String EDITUSER) {
        this.EDITUSER = EDITUSER;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public Date getINSTOCKDATE() {
        return INSTOCKDATE;
    }

    public void setINSTOCKDATE(Date INSTOCKDATE) {
        this.INSTOCKDATE = INSTOCKDATE;
    }

    public Date getOUTSTOCKDATE() {
        return OUTSTOCKDATE;
    }

    public void setOUTSTOCKDATE(Date OUTSTOCKDATE) {
        this.OUTSTOCKDATE = OUTSTOCKDATE;
    }

    public Date getACTIVATIONDATE() {
        return ACTIVATIONDATE;
    }

    public void setACTIVATIONDATE(Date ACTIVATIONDATE) {
        this.ACTIVATIONDATE = ACTIVATIONDATE;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getLIFECYCLE() {
        return LIFECYCLE;
    }

    public void setLIFECYCLE(String LIFECYCLE) {
        this.LIFECYCLE = LIFECYCLE;
    }

    public Date getLIFEEXPIRATIONDATE() {
        return LIFEEXPIRATIONDATE;
    }

    public void setLIFEEXPIRATIONDATE(Date LIFEEXPIRATIONDATE) {
        this.LIFEEXPIRATIONDATE = LIFEEXPIRATIONDATE;
    }

    /**
     * 获取ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取设备号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设备设备号
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * 获取SIM号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设备SIM号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取出厂号
     */
    public String getFactoryNumber() {
        return factoryNumber;
    }

    /**
     * 设置出厂号
     */
    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    /**
     * 获取型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置型号
     */
    public void setModel(String model) {
        this.model = model;
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
     * 获取保修期
     */
    public Date getWarranty() {
        return warranty;
    }

    /**
     * 设置保修期
     */
    public void setWarranty(Date warranty) {
        this.warranty = warranty;
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

    public String getRepairman() {
        return repairman;
    }

    public void setRepairman(String repairman) {
        this.repairman = repairman;
    }

    public Date getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(Date maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public boolean isTerminalServiceState() {
        return terminalServiceState;
    }

    public void setTerminalServiceState(boolean terminalServiceState) {
        this.terminalServiceState = terminalServiceState;
    }

    public String getCauseofFailure() {
        return causeofFailure;
    }

    public void setCauseofFailure(String causeofFailure) {
        this.causeofFailure = causeofFailure;
    }
}
