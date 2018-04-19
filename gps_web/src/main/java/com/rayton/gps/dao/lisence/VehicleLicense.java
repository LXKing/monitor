package com.rayton.gps.dao.lisence;

public class VehicleLicense implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String owner;//所有人
    private String overallSize;//外廓尺寸
    private String tractionMass;//准牵引总质量
    private String address;//住址
    private String companyId;//公司id
    private String approvedPassengersCapacity;//核定载人数
    private String inspectionRecord;//检验记录
    private String vehicleType;//车辆类型
    private java.util.Date registerDate;//注册日期
    private String remark;//备注
    private String approvedLoad;//核定载质量
    private String engineNo;//发动机号码
    private java.util.Date issueDate;//发证日期
    private String plateNo;//车牌号
    private String documentNo;//档案编号
    private String useCharacter;//使用性质
    private String model;//品牌型号
    private String vin;//车辆识别代号
    private String curbWeight;//整备质量
    private String totalMass;//总质量

    public VehicleLicense() {
        super();
    }

    public VehicleLicense(Integer id, String owner, String overallSize, String tractionMass, String address, String
            companyId, String approvedPassengersCapacity, String inspectionRecord, String vehicleType, java.util.Date
            registerDate, String remark, String approvedLoad, String engineNo, java.util.Date issueDate, String
            plateNo, String documentNo, String useCharacter, String model, String vin, String curbWeight, String
            totalMass) {
        super();
        this.id = id;
        this.owner = owner;
        this.overallSize = overallSize;
        this.tractionMass = tractionMass;
        this.address = address;
        this.companyId = companyId;
        this.approvedPassengersCapacity = approvedPassengersCapacity;
        this.inspectionRecord = inspectionRecord;
        this.vehicleType = vehicleType;
        this.registerDate = registerDate;
        this.remark = remark;
        this.approvedLoad = approvedLoad;
        this.engineNo = engineNo;
        this.issueDate = issueDate;
        this.plateNo = plateNo;
        this.documentNo = documentNo;
        this.useCharacter = useCharacter;
        this.model = model;
        this.vin = vin;
        this.curbWeight = curbWeight;
        this.totalMass = totalMass;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOverallSize() {
        return this.overallSize;
    }

    public void setOverallSize(String overallSize) {
        this.overallSize = overallSize;
    }

    public String getTractionMass() {
        return this.tractionMass;
    }

    public void setTractionMass(String tractionMass) {
        this.tractionMass = tractionMass;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getApprovedPassengersCapacity() {
        return this.approvedPassengersCapacity;
    }

    public void setApprovedPassengersCapacity(String approvedPassengersCapacity) {
        this.approvedPassengersCapacity = approvedPassengersCapacity;
    }

    public String getInspectionRecord() {
        return this.inspectionRecord;
    }

    public void setInspectionRecord(String inspectionRecord) {
        this.inspectionRecord = inspectionRecord;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public java.util.Date getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(java.util.Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprovedLoad() {
        return this.approvedLoad;
    }

    public void setApprovedLoad(String approvedLoad) {
        this.approvedLoad = approvedLoad;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public java.util.Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(java.util.Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getPlateNo() {
        return this.plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getUseCharacter() {
        return this.useCharacter;
    }

    public void setUseCharacter(String useCharacter) {
        this.useCharacter = useCharacter;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return this.vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCurbWeight() {
        return this.curbWeight;
    }

    public void setCurbWeight(String curbWeight) {
        this.curbWeight = curbWeight;
    }

    public String getTotalMass() {
        return this.totalMass;
    }

    public void setTotalMass(String totalMass) {
        this.totalMass = totalMass;
    }

}
