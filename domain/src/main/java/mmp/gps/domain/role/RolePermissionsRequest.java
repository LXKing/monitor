package mmp.gps.domain.role;

import org.hibernate.validator.constraints.NotEmpty;

public class RolePermissionsRequest {
    @NotEmpty
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
