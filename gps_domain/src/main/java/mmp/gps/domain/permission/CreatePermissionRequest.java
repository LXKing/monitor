package mmp.gps.domain.permission;

import javax.validation.constraints.Size;

public class CreatePermissionRequest {
    @Size(min = 1, max = 50)
    private String name;
    @Size(min = 1, max = 100)
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
