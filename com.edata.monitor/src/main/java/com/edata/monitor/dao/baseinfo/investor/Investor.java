package com.edata.monitor.dao.baseinfo.investor;

import java.util.Date;

public class Investor {
    private Integer id;

    private String investor;

    private String investorname;

    private String email;

    private String fax;

    private String address;

    private String phone;

    private String zipcode;

    private Date registrationdate;

    private String bussinessagent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor == null ? null : investor.trim();
    }

    public String getInvestorname() {
        return investorname;
    }

    public void setInvestorname(String investorname) {
        this.investorname = investorname == null ? null : investorname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getBussinessagent() {
        return bussinessagent;
    }

    public void setBussinessagent(String bussinessagent) {
        this.bussinessagent = bussinessagent == null ? null : bussinessagent.trim();
    }
}