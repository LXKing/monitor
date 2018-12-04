package mmp.gps.domain.parameter;

import org.hibernate.validator.constraints.NotEmpty;

public class MoveParameterRequest {
    @NotEmpty
    private String id;
    private String pid;

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

}
