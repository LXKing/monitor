package mmp.gps.domain.permission;

public class Permission {
    private String permissionid;

    private String name;

    private String pid;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCOMPANYID() {
        return COMPANYID;
    }
    public void setCOMPANYID(String COMPANYID) {
        this.COMPANYID = COMPANYID;
    }
    private String id;


    private String COMPANYID;

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid == null ? null : permissionid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}
