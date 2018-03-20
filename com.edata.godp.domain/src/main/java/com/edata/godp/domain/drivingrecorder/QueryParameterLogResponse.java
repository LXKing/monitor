package com.edata.godp.domain.drivingrecorder;

import java.util.ArrayList;
import java.util.List;

public class QueryParameterLogResponse {
    private int total;
    private List<ParameterChangeLog> rows = new ArrayList<ParameterChangeLog>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ParameterChangeLog> getRows() {
        return rows;
    }

    public void setRows(List<ParameterChangeLog> rows) {
        this.rows = rows;
    }
}
