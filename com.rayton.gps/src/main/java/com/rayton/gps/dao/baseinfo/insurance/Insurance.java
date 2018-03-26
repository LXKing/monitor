package com.rayton.gps.dao.baseinfo.insurance;

import java.util.Date;

public class Insurance {
    private String insurancecompany;

    private String policyno;

    private Date policyexpirenotifydate;

    private String insurancecompanycontact;

    private String premium;

    private String policypic;

    private String insurancecompanytel;

    private String periodvalidity;

    public String getInsurancecompany() {
        return insurancecompany;
    }

    public void setInsurancecompany(String insurancecompany) {
        this.insurancecompany = insurancecompany == null ? null : insurancecompany.trim();
    }

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno == null ? null : policyno.trim();
    }

    public Date getPolicyexpirenotifydate() {
        return policyexpirenotifydate;
    }

    public void setPolicyexpirenotifydate(Date policyexpirenotifydate) {
        this.policyexpirenotifydate = policyexpirenotifydate;
    }

    public String getInsurancecompanycontact() {
        return insurancecompanycontact;
    }

    public void setInsurancecompanycontact(String insurancecompanycontact) {
        this.insurancecompanycontact = insurancecompanycontact == null ? null : insurancecompanycontact.trim();
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium == null ? null : premium.trim();
    }

    public String getPolicypic() {
        return policypic;
    }

    public void setPolicypic(String policypic) {
        this.policypic = policypic == null ? null : policypic.trim();
    }

    public String getInsurancecompanytel() {
        return insurancecompanytel;
    }

    public void setInsurancecompanytel(String insurancecompanytel) {
        this.insurancecompanytel = insurancecompanytel == null ? null : insurancecompanytel.trim();
    }

    public String getPeriodvalidity() {
        return periodvalidity;
    }

    public void setPeriodvalidity(String periodvalidity) {
        this.periodvalidity = periodvalidity == null ? null : periodvalidity.trim();
    }
}