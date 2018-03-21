package com.edata.monitor.dao.baseinfo.staff;

import java.util.Date;

public class Staff {
    private Long id;

    private String staffname;

    private String department;

    private Date hiredate;

    private String residentaddress;

    private String bussinessagent;

    private String phone;

    private String position;

    private String idnum;

    private String nowaddress;

    private String companyid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname == null ? null : staffname.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getResidentaddress() {
        return residentaddress;
    }

    public void setResidentaddress(String residentaddress) {
        this.residentaddress = residentaddress == null ? null : residentaddress.trim();
    }

    public String getBussinessagent() {
        return bussinessagent;
    }

    public void setBussinessagent(String bussinessagent) {
        this.bussinessagent = bussinessagent == null ? null : bussinessagent.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum == null ? null : idnum.trim();
    }

    public String getNowaddress() {
        return nowaddress;
    }

    public void setNowaddress(String nowaddress) {
        this.nowaddress = nowaddress == null ? null : nowaddress.trim();
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }
}