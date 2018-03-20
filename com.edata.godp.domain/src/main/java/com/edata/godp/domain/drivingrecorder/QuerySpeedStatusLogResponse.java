package com.edata.godp.domain.drivingrecorder;

import java.util.ArrayList;
import java.util.List;

public class QuerySpeedStatusLogResponse {
    private int total;
    private List<SpeedStatusLog> rows = new ArrayList<SpeedStatusLog>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SpeedStatusLog> getRows() {
        return rows;
    }

    public void setRows(List<SpeedStatusLog> rows) {
        this.rows = rows;
    }
}
