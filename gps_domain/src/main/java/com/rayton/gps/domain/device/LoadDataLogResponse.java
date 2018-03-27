package com.rayton.gps.domain.device;

import java.util.ArrayList;
import java.util.List;

public class LoadDataLogResponse {
    private int total;
    private List<DataLog> logs = new ArrayList<DataLog>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataLog> getLogs() {
        return logs;
    }

    public void setLogs(List<DataLog> logs) {
        this.logs = logs;
    }
}
