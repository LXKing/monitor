package mmp.gps.domain.drivingRecorder;

import org.hibernate.validator.constraints.NotBlank;

public class QueryDrivingRecorderInfoRequest {
    @NotBlank
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
