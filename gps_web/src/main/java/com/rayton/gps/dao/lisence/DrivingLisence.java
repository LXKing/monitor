package com.rayton.gps.dao.lisence;

public class DrivingLisence implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String address;//地址
    private String birthdate;//生日
    private String companyId;
    private String nationality;//国籍
    private String validPeriod;//有效期限
    private String sex;//性别
    private String docNo;//档案编号
    private String record;//记录
    private String name;//司机名
    private String carClass;//准驾车型
    private String dateOfFirstIssue;//初次领证日期

    public DrivingLisence() {
        super();
    }

    public DrivingLisence(String id, String address, String birthdate, String companyId, String nationality, String
            validPeriod, String sex, String docNo, String record, String name, String carClass, String
            dateOfFirstIssue) {
        super();
        this.id = id;
        this.address = address;
        this.birthdate = birthdate;
        this.companyId = companyId;
        this.nationality = nationality;
        this.validPeriod = validPeriod;
        this.sex = sex;
        this.docNo = docNo;
        this.record = record;
        this.name = name;
        this.carClass = carClass;
        this.dateOfFirstIssue = dateOfFirstIssue;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getValidPeriod() {
        return this.validPeriod;
    }

    public void setValidPeriod(String validPeriod) {
        this.validPeriod = validPeriod;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDocNo() {
        return this.docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getRecord() {
        return this.record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarClass() {
        return this.carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getDateOfFirstIssue() {
        return this.dateOfFirstIssue;
    }

    public void setDateOfFirstIssue(String dateOfFirstIssue) {
        this.dateOfFirstIssue = dateOfFirstIssue;
    }

    @Override
    public String toString() {
        return "DrivingLisence{" + "id='" + id + '\'' + ", address='" + address + '\'' + ", birthdate='" + birthdate
                + '\'' + ", companyId='" + companyId + '\'' + ", nationality='" + nationality + '\'' + ", " +
                "validPeriod='" + validPeriod + '\'' + ", sex='" + sex + '\'' + ", docNo='" + docNo + '\'' + ", " +
                "record='" + record + '\'' + ", name='" + name + '\'' + ", carClass='" + carClass + '\'' + ", " +
                "dateOfFirstIssue='" + dateOfFirstIssue + '\'' + '}';
    }
}
