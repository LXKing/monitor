package com.rayton.gps.dao.lisence;

import com.alibaba.fastjson.annotation.JSONField;

public class DrivingLisence implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String address;//地址

    @JSONField(format = "yyyy-MM-dd")
    private java.util.Date birthdate;//生日
    private String companyId;
    private String nationality;//国籍
    @JSONField(format = "yyyy-MM-dd")
    private java.util.Date validPeriod;//有效期限
    private String sex;//性别
    private String docNo;//档案编号
    private String record;//记录
    private String name;//司机名
    private String carClass;//准驾车型
    @JSONField(format = "yyyy-MM-dd")
    private java.util.Date dateOfFirstIssue;//初次领证日期

    public DrivingLisence() {
        super();
    }

    public DrivingLisence(Integer id, String address, java.util.Date birthdate, String companyId, String nationality, java.util.Date validPeriod, String sex, String docNo, String record, String name, String carClass, java.util.Date dateOfFirstIssue) {
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.util.Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(java.util.Date birthdate) {
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

    public java.util.Date getValidPeriod() {
        return this.validPeriod;
    }

    public void setValidPeriod(java.util.Date validPeriod) {
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

    public java.util.Date getDateOfFirstIssue() {
        return this.dateOfFirstIssue;
    }

    public void setDateOfFirstIssue(java.util.Date dateOfFirstIssue) {
        this.dateOfFirstIssue = dateOfFirstIssue;
    }

}
