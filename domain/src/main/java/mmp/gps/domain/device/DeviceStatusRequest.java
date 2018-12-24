package mmp.gps.domain.device;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeviceStatusRequest {
    @NotNull
    @Size(min = 5, max = 20)
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
