package mmp.gps.domain.firmware;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class EditFirmwareRequest extends CreateFirmwareRequest {
    @Min(1)
    private String id;
    @NotNull
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
