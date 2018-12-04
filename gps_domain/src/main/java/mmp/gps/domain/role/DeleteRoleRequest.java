package mmp.gps.domain.role;

import org.hibernate.validator.constraints.NotEmpty;

public class DeleteRoleRequest {
    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
