package mmp.gps.domain.parameter;

import org.hibernate.validator.constraints.NotEmpty;

public class FetchParameterRequest {
    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
