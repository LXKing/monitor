package com.edata.godp.domain.drivingrecorder;

import java.util.ArrayList;
import java.util.List;

public class QuerySpeedLogResponse {
    private int total;
    private List<SpeedLog> rows = new ArrayList<SpeedLog>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SpeedLog> getRows() {
        return rows;
    }

    public void setRows(List<SpeedLog> rows) {
        this.rows = rows;
    }
}
