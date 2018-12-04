package mmp.gps.domain.vehicle;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 车辆信息类
 */
public class VehicleInfo {
    private String deviceId;


    private String vehicleColor;
    private String vehicleType;


    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    private String VEHICLESTATE;

    public String getVEHICLESTATE() {
        return VEHICLESTATE;
    }

    private String CT;
    private String SECONDNUMBER;

    public String getSECONDNUMBER() {
        return SECONDNUMBER;
    }

    public void setSECONDNUMBER(String SECONDNUMBER) {
        this.SECONDNUMBER = SECONDNUMBER;
    }

    public String getCT() {
        return CT;
    }

    public void setCT(String CT) {
        this.CT = CT;
    }

    public void setVEHICLESTATE(String VEHICLESTATE) {
        this.VEHICLESTATE = VEHICLESTATE;
    }

    private Date gpstime;

    public Date getGpstime() {
        return gpstime;
    }

    public void setGpstime(Date gpstime) {
        this.gpstime = gpstime;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private String CONTACTNUMBER;
    private String CONTACTS;

    public String getCONTACTNUMBER() {
        return CONTACTNUMBER;
    }

    public void setCONTACTNUMBER(String CONTACTNUMBER) {
        this.CONTACTNUMBER = CONTACTNUMBER;
    }

    public String getCONTACTS() {
        return CONTACTS;
    }

    public void setCONTACTS(String CONTACTS) {
        this.CONTACTS = CONTACTS;
    }

    private String id;
    private String deviceNumber;
    private String phoneNumber;
    private String plateNumber;
    private String plateColor;
    @JSONField(format = "yyyy-MM-dd")
    private Date installDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date annualSurveyDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date serviceStartDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date serviceEndDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date nextMaintainDate;


    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private String motorcade;
    private String marker;
    private String remark;


    // 别打我
    private String FACTORYPLATEMODE;
    private String AXLENUM;
    private String MANUFACTURER;
    private String TRAILERNUM;
    private String VIN;
    private String BUYMONEY;
    private String SERVICEPROVIDER;
    private String LICENSEPLATESELFNUM;
    private String ENGINENUM;
    private String CARRIAGELONG;
    @JSONField(format = "yyyy-MM-dd")
    private Date BUYDATE;
    private String PROVIDERCONTACT;
    private String DANGERPLATENUM;
    private String VEHICULARWEIGHT;
    private String INVOICEAMOUNT;
    private String PROVIDERTEL;
    @JSONField(format = "yyyy-MM-dd")
    private Date REGISTRATIONDATE;
    private String BUYTAX;
    private String LICENSEEXAMINATIONPERIOD;
    private String VEHICLEPIC;
    private String VINPIC;
    private String FUELTYPE;

    public String getFUELTYPE() {
        return FUELTYPE;
    }

    public void setFUELTYPE(String FUELTYPE) {
        this.FUELTYPE = FUELTYPE;
    }
    //


    @Override
    public String toString() {
        return "VehicleInfo{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", plateNumber='" + plateNumber + '\'' + ", plateColor='" + plateColor + '\'' + ", installDate=" + installDate + ", annualSurveyDate=" + annualSurveyDate + ", serviceStartDate=" + serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", nextMaintainDate=" + nextMaintainDate + ", motorcade='" + motorcade + '\'' + ", marker='" + marker + '\'' + ", remark='" + remark + '\'' + "," + "" + "" + "" + "" + "" + "" + "" + "" + "" + " FACTORYPLATEMODE='" + FACTORYPLATEMODE + '\'' + ", " + "AXLENUM='" + AXLENUM + '\'' + ", " + "" + "MANUFACTURER='" + MANUFACTURER + '\'' + ", TRAILERNUM='" + TRAILERNUM + '\'' + "," + " " + "VIN='" + VIN + '\'' + ", " + "BUYMONEY='" + BUYMONEY + '\'' + ", " + "SERVICEPROVIDER='" + SERVICEPROVIDER + '\'' + ", " + "" + "LICENSEPLATESELFNUM='" + LICENSEPLATESELFNUM + '\'' + ", " + "ENGINENUM='" + ENGINENUM + '\'' + ", " + "CARRIAGELONG='" + CARRIAGELONG + '\'' + ", BUYDATE=" + BUYDATE + ", PROVIDERCONTACT='" + PROVIDERCONTACT + '\'' + ", " + "DANGERPLATENUM='" + DANGERPLATENUM + '\'' + ", VEHICULARWEIGHT='" + VEHICULARWEIGHT + '\'' + ", " + "INVOICEAMOUNT='" + INVOICEAMOUNT + '\'' + ", PROVIDERTEL='" + PROVIDERTEL + '\'' + ", " + "REGISTRATIONDATE=" + REGISTRATIONDATE + ", BUYTAX='" + BUYTAX + '\'' + ", " + "LICENSEEXAMINATIONPERIOD='" + LICENSEEXAMINATIONPERIOD + '\'' + ", " + "VEHICLEPIC='" + VEHICLEPIC + '\'' + ", VINPIC='" + VINPIC + '\'' + '}';
    }

    public String getFACTORYPLATEMODE() {
        return FACTORYPLATEMODE;
    }

    public void setFACTORYPLATEMODE(String FACTORYPLATEMODE) {
        this.FACTORYPLATEMODE = FACTORYPLATEMODE;
    }

    public String getAXLENUM() {
        return AXLENUM;
    }

    public void setAXLENUM(String AXLENUM) {
        this.AXLENUM = AXLENUM;
    }

    public String getMANUFACTURER() {
        return MANUFACTURER;
    }

    public void setMANUFACTURER(String MANUFACTURER) {
        this.MANUFACTURER = MANUFACTURER;
    }

    public String getTRAILERNUM() {
        return TRAILERNUM;
    }

    public void setTRAILERNUM(String TRAILERNUM) {
        this.TRAILERNUM = TRAILERNUM;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getBUYMONEY() {
        return BUYMONEY;
    }

    public void setBUYMONEY(String BUYMONEY) {
        this.BUYMONEY = BUYMONEY;
    }

    public String getSERVICEPROVIDER() {
        return SERVICEPROVIDER;
    }

    public void setSERVICEPROVIDER(String SERVICEPROVIDER) {
        this.SERVICEPROVIDER = SERVICEPROVIDER;
    }

    public String getLICENSEPLATESELFNUM() {
        return LICENSEPLATESELFNUM;
    }

    public void setLICENSEPLATESELFNUM(String LICENSEPLATESELFNUM) {
        this.LICENSEPLATESELFNUM = LICENSEPLATESELFNUM;
    }

    public String getENGINENUM() {
        return ENGINENUM;
    }

    public void setENGINENUM(String ENGINENUM) {
        this.ENGINENUM = ENGINENUM;
    }

    public String getCARRIAGELONG() {
        return CARRIAGELONG;
    }

    public void setCARRIAGELONG(String CARRIAGELONG) {
        this.CARRIAGELONG = CARRIAGELONG;
    }

    public Date getBUYDATE() {
        return BUYDATE;
    }

    public void setBUYDATE(Date BUYDATE) {
        this.BUYDATE = BUYDATE;
    }

    public String getPROVIDERCONTACT() {
        return PROVIDERCONTACT;
    }

    public void setPROVIDERCONTACT(String PROVIDERCONTACT) {
        this.PROVIDERCONTACT = PROVIDERCONTACT;
    }

    public String getDANGERPLATENUM() {
        return DANGERPLATENUM;
    }

    public void setDANGERPLATENUM(String DANGERPLATENUM) {
        this.DANGERPLATENUM = DANGERPLATENUM;
    }

    public String getVEHICULARWEIGHT() {
        return VEHICULARWEIGHT;
    }

    public void setVEHICULARWEIGHT(String VEHICULARWEIGHT) {
        this.VEHICULARWEIGHT = VEHICULARWEIGHT;
    }

    public String getINVOICEAMOUNT() {
        return INVOICEAMOUNT;
    }

    public void setINVOICEAMOUNT(String INVOICEAMOUNT) {
        this.INVOICEAMOUNT = INVOICEAMOUNT;
    }

    public String getPROVIDERTEL() {
        return PROVIDERTEL;
    }

    public void setPROVIDERTEL(String PROVIDERTEL) {
        this.PROVIDERTEL = PROVIDERTEL;
    }

    public Date getREGISTRATIONDATE() {
        return REGISTRATIONDATE;
    }

    public void setREGISTRATIONDATE(Date REGISTRATIONDATE) {
        this.REGISTRATIONDATE = REGISTRATIONDATE;
    }

    public String getBUYTAX() {
        return BUYTAX;
    }

    public void setBUYTAX(String BUYTAX) {
        this.BUYTAX = BUYTAX;
    }

    public String getLICENSEEXAMINATIONPERIOD() {
        return LICENSEEXAMINATIONPERIOD;
    }

    public void setLICENSEEXAMINATIONPERIOD(String LICENSEEXAMINATIONPERIOD) {
        this.LICENSEEXAMINATIONPERIOD = LICENSEEXAMINATIONPERIOD;
    }

    public String getVEHICLEPIC() {
        return VEHICLEPIC;
    }

    public void setVEHICLEPIC(String VEHICLEPIC) {
        this.VEHICLEPIC = VEHICLEPIC;
    }

    public String getVINPIC() {
        return VINPIC;
    }

    public void setVINPIC(String VINPIC) {
        this.VINPIC = VINPIC;
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
     * 获取车牌号码
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * 设置车牌号码
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * 获取车牌颜色
     */
    public String getPlateColor() {
        return plateColor;
    }

    /**
     * 设置车牌颜色
     */
    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
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

    /**
     * 获取年检日期
     */
    public Date getAnnualSurveyDate() {
        return annualSurveyDate;
    }

    /**
     * 设置年检日期
     */
    public void setAnnualSurveyDate(Date annualSurveyDate) {
        this.annualSurveyDate = annualSurveyDate;
    }

    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public Date getNextMaintainDate() {
        return nextMaintainDate;
    }

    public void setNextMaintainDate(Date nextMaintainDate) {
        this.nextMaintainDate = nextMaintainDate;
    }

    /**
     * 获取车队
     */
    public String getMotorcade() {
        return motorcade;
    }

    /**
     * 设置车队
     */
    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    /**
     * 获取车辆图标
     */
    public String getMarker() {
        return marker;
    }

    /**
     * 设置车辆图标
     */
    public void setMarker(String marker) {
        this.marker = marker;
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
