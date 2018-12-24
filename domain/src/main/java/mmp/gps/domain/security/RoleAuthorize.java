package mmp.gps.domain.security;

public class RoleAuthorize {

    private String roleId;
    private String permissionId;

    @Override
    public String toString() {
        return "RoleAuthorize{" + "roleId='" + roleId + '\'' + ", permissionId='" + permissionId + '\'' + '}';
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}
