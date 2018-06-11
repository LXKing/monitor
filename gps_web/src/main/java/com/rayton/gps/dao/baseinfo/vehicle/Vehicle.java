package com.rayton.gps.dao.baseinfo.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 车辆类
 *
 * @author yangzs
 */
public class Vehicle {
    private String id;
    private String companyId;
    private String motorcadeId;
    @NotEmpty
    private String motorcade;
    private String deviceNumber;
    private String deviceId;
    @NotEmpty
    private String plateNumber;
    private String plateColor;
    private String vehicleColor;
    private String vehicleType;
    private String vehicleVoltage;
    private String carryType;
    private int initialMileage;
    private double oilWear;
    private int usefulLife;

    @JSONField(format = "yyyy-MM-dd")
    private Date installDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date annualSurveyDate;
    private String adminArea;
    private String marker;
    private boolean rotate;
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;


    /**
     * 设置是否旋转图标
     */
    private String remark;
    private Timestamp editTime;

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
        return "Vehicle{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", " + "deviceId='" + deviceId + '\'' + ", plateNumber='" + plateNumber + '\'' + ", plateColor='" + plateColor + '\'' + ", vehicleColor='" + vehicleColor + '\'' + ", vehicleType='" + vehicleType + '\'' + ", vehicleVoltage='" + vehicleVoltage + '\'' + ", carryType='" + carryType + '\'' + ", " + "initialMileage=" + initialMileage + ", oilWear=" + oilWear + ", usefulLife=" + usefulLife + ", " + "installDate=" + installDate + ", annualSurveyDate=" + annualSurveyDate + ", adminArea='" + adminArea + '\'' + ", marker='" + marker + '\'' + ", rotate=" + rotate + ", createTime=" + createTime + ", " + "remark='" + remark + '\'' + ", editTime=" + editTime + ", FACTORYPLATEMODE='" + FACTORYPLATEMODE + '\'' + ", AXLENUM='" + AXLENUM + '\'' + ", MANUFACTURER='" + MANUFACTURER + '\'' + ", TRAILERNUM='" + TRAILERNUM + '\'' + ", VIN='" + VIN + '\'' + ", BUYMONEY='" + BUYMONEY + '\'' + ", SERVICEPROVIDER='" + SERVICEPROVIDER + '\'' + ", LICENSEPLATESELFNUM='" + LICENSEPLATESELFNUM + '\'' + ", ENGINENUM='" + ENGINENUM + '\'' + ", CARRIAGELONG='" + CARRIAGELONG + '\'' + ", BUYDATE=" + BUYDATE + ", " + "PROVIDERCONTACT='" + PROVIDERCONTACT + '\'' + ", DANGERPLATENUM='" + DANGERPLATENUM + '\'' + ", " + "VEHICULARWEIGHT='" + VEHICULARWEIGHT + '\'' + ", INVOICEAMOUNT='" + INVOICEAMOUNT + '\'' + ", " + "PROVIDERTEL='" + PROVIDERTEL + '\'' + ", REGISTRATIONDATE=" + REGISTRATIONDATE + ", BUYTAX='" + BUYTAX + '\'' + ", LICENSEEXAMINATIONPERIOD='" + LICENSEEXAMINATIONPERIOD + '\'' + ", VEHICLEPIC='" + VEHICLEPIC + '\'' + ", VINPIC='" + VINPIC + '\'' + '}';
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
     * 获取所属公司唯一编号
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置所属公司唯一编号
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取所属车队唯一编号
     */
    public String getMotorcadeId() {
        return motorcadeId;
    }

    /**
     * 设置所属车队唯一编号
     */
    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    /**
     * 获取所属车队
     */
    public String getMotorcade() {
        return motorcade;
    }

    /**
     * 设置所属车队
     */
    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
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
     * 获取设备唯一编号
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备唯一编号
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
     * 获取车辆颜色
     */
    public String getVehicleColor() {
        return vehicleColor;
    }

    /**
     * 设置车辆颜色
     */
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    /**
     * 获取车辆类型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 设置车辆类型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 获取车辆电压
     */
    public String getVehicleVoltage() {
        return vehicleVoltage;
    }

    /**
     * 设置车辆电压
     */
    public void setVehicleVoltage(String vehicleVoltage) {
        this.vehicleVoltage = vehicleVoltage;
    }

    /**
     * 获取载运类型
     */
    public String getCarryType() {
        return carryType;
    }

    /**
     * 设置载运类型
     */
    public void setCarryType(String carryType) {
        this.carryType = carryType;
    }

    /**
     * 获取初始里程
     */
    public int getInitialMileage() {
        return initialMileage;
    }

    /**
     * 设置初始里程
     */
    public void setInitialMileage(int initialMileage) {
        this.initialMileage = initialMileage;
    }

    /**
     * 获取百公里油耗
     */
    public double getOilWear() {
        return oilWear;
    }

    /**
     * 设置百公里油耗
     */
    public void setOilWear(double oilWear) {
        this.oilWear = oilWear;
    }

    /**
     * 获取使用年限
     */
    public int getusefulLife() {
        return usefulLife;
    }

    /**
     * 设置使用年限
     */
    public void setusefulLife(int usefulLife) {
        this.usefulLife = usefulLife;
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

    /**
     * 获取所属行政区域
     */
    public String getAdminArea() {
        return adminArea;
    }

    /**
     * 设置所属行政区域
     */
    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
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
     * 获取是否旋转图标
     */
    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
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
