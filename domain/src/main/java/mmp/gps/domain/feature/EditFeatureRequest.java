package mmp.gps.domain.feature;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class EditFeatureRequest extends CreateFeatureRequest {
    @NotEmpty
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
