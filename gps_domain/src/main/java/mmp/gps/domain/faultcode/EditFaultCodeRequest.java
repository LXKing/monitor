package mmp.gps.domain.faultcode;

import java.sql.Timestamp;

public class EditFaultCodeRequest extends CreateFaultCodeRequest {
    private String id;
    private Timestamp editTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

}
