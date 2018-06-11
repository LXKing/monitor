package com.rayton.gps.dao.security;

public class Permission {

    private String id;
    private String pid;
    private String name;

    private String COMPANYID;


    public String getCOMPANYID() {
        return COMPANYID;
    }

    public void setCOMPANYID(String COMPANYID) {
        this.COMPANYID = COMPANYID;
    }

    @Override
    public String toString() {
        return "Permission{" + "id='" + id + '\'' + ", pid='" + pid + '\'' + ", name='" + name + '\'' + ", " + "COMPANYID='" + COMPANYID + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
