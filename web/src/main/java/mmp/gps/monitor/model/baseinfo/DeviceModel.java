package mmp.gps.monitor.model.baseinfo;

import mmp.gps.domain.device.Device;
import mmp.gps.domain.user.User;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;

public class DeviceModel {

    private String id;
    private String companyId;
    @NotEmpty
    private String deviceNumber;
    private String simcardId;
    private String phoneNumber;
    private String model;
    private String factoryName;
    private String factoryNumber;
    private byte cameras;
    private boolean hasMicrophone;
    private boolean hasNavigation;
    private String sensors;
    private Date warranty;
    private Date purchaseDate;
    private Date installDate;
    private Timestamp deviceEditTime;
    private String pid;
    private int kind;
    // @NotBlank
    private String account;
    // @NotBlank
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

    private String SN;
    private Date INSTOCKDATE;
    private Date OUTSTOCKDATE;
    private Date ACTIVATIONDATE;
    private String IMEI;
    private String LIFECYCLE;
    private Date LIFEEXPIRATIONDATE;
    private String protocolName;
    private String protocol;
    private String REPAIR;
    private String EDITUSER;

    private String repairman;//维修员
    private Date maintenanceTime;//维修时间

    private boolean terminalServiceState;//终端服务状态

    private String causeofFailure;//故障原因

    private String remarks;//备注

    private String vid;
    private String motorcadeId;//车队

    public String getREPAIR() {
        return REPAIR;
    }

    public void setREPAIR(String REPAIR) {
        this.REPAIR = REPAIR;
    }

    public String getEDITUSER() {
        return EDITUSER;
    }

    public void setEDITUSER(String EDITUSER) {
        this.EDITUSER = EDITUSER;
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

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

  /*  @Override
    public String toString() {
        return "DeviceModel{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", simcardId='" + simcardId + '\'' + ", phoneNumber='" + phoneNumber + '\'' + "," + " model='" + model + '\'' + ", factoryName='" + factoryName + '\'' + ", factoryNumber='" + factoryNumber + '\'' + ", cameras=" + cameras + ", hasMicrophone=" + hasMicrophone + ", " + "hasNavigation=" + hasNavigation + ", sensors='" + sensors + '\'' + ", warranty=" + warranty + ", " + "purchaseDate=" + purchaseDate + ", installDate=" + installDate + ", deviceEditTime=" + deviceEditTime + ", pid='" + pid + '\'' + ", enums=" + kind + ", account='" + account + '\'' + ", " + "name='" + name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", contact='" + contact + '\'' + ", " + "enable=" + enable + ", serviceStartDate=" + serviceStartDate + ", " + "serviceEndDate=" + serviceEndDate + ", createTime=" + createTime + ", remark='" + remark + '\'' + "," + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + " userEditTime=" + userEditTime + '}';
    }*/

    @Override
    public String toString() {
        return "DeviceModel{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", simcardId='" + simcardId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", model='" + model + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", factoryNumber='" + factoryNumber + '\'' +
                ", cameras=" + cameras +
                ", hasMicrophone=" + hasMicrophone +
                ", hasNavigation=" + hasNavigation +
                ", sensors='" + sensors + '\'' +
                ", warranty=" + warranty +
                ", purchaseDate=" + purchaseDate +
                ", installDate=" + installDate +
                ", deviceEditTime=" + deviceEditTime +
                ", pid='" + pid + '\'' +
                ", kind=" + kind +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", contact='" + contact + '\'' +
                ", enable=" + enable +
                ", serviceStartDate=" + serviceStartDate +
                ", serviceEndDate=" + serviceEndDate +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", userEditTime=" + userEditTime +
                ", SN='" + SN + '\'' +
                ", INSTOCKDATE=" + INSTOCKDATE +
                ", OUTSTOCKDATE=" + OUTSTOCKDATE +
                ", ACTIVATIONDATE=" + ACTIVATIONDATE +
                ", IMEI='" + IMEI + '\'' +
                ", LIFECYCLE='" + LIFECYCLE + '\'' +
                ", LIFEEXPIRATIONDATE=" + LIFEEXPIRATIONDATE +
                ", protocolName='" + protocolName + '\'' +
                ", protocol='" + protocol + '\'' +
                ", REPAIR='" + REPAIR + '\'' +
                ", EDITUSER='" + EDITUSER + '\'' +
                ", vid='" + vid + '\'' +
                '}';
    }

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

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    /**
     * 获取设备号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设置设备号
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * 获取sim卡号
     */
    public String getSimcardId() {
        return simcardId;
    }

    /**
     * 设置sim卡号
     */
    public void setSimcardId(String simcardId) {
        this.simcardId = simcardId;
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
     * 获取厂家
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 设置厂家
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
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
     * 获取摄像头路数
     */
    public byte getCameras() {
        return cameras;
    }

    /**
     * 设置摄像头路数
     */
    public void setCameras(byte cameras) {
        this.cameras = cameras;
    }

    /**
     * 获取有无麦克风
     */
    public boolean isHasMicrophone() {
        return hasMicrophone;
    }

    /**
     * 设置有无麦克风
     */
    public void setHasMicrophone(boolean hasMicrophone) {
        this.hasMicrophone = hasMicrophone;
    }

    /**
     * 获取有无导航屏
     */
    public boolean isHasNavigation() {
        return hasNavigation;
    }

    /**
     * 设置有无导航屏
     */
    public void setHasNavigation(boolean hasNavigation) {
        this.hasNavigation = hasNavigation;
    }

    /**
     * 获取传感器列表
     */
    public String getSensors() {
        return sensors;
    }

    /**
     * 设置传感器列表
     */
    public void setSensors(String sensors) {
        this.sensors = sensors;
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
     * 获取安装日期
     */
    public Date getInstallDate() {
        return installDate;
    }

    /**
     * 设置安装日期
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Timestamp getDeviceEditTime() {
        return deviceEditTime;
    }

    public void setDeviceEditTime(Timestamp deviceEditTime) {
        this.deviceEditTime = deviceEditTime;
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

    public Timestamp getUserEditTime() {
        return userEditTime;
    }

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

    public void fill(Device device) {
        id = device.getId();

        deviceNumber = device.getDeviceNumber();

        protocol = device.getProtocol();


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

    public Device getDevice() {
        Device device = new Device();

        device.setId(id);

        device.setDeviceNumber(deviceNumber);

        device.setProtocol(protocol);

        return device;
    }
}
