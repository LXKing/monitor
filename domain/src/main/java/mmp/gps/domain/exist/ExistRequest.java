package mmp.gps.domain.exist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExistRequest {
    @NotNull
    @Size(min = 1, max = 50)
    private String key;
    private String id;
    private boolean checkId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheckId() {
        return checkId;
    }

    public void setCheckId(boolean checkId) {
        this.checkId = checkId;
    }
}
