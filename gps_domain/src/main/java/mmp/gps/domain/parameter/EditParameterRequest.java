package mmp.gps.domain.parameter;

import java.util.Date;

public class EditParameterRequest extends CreateParameterRequest {
    private String id;
    private Date editTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
