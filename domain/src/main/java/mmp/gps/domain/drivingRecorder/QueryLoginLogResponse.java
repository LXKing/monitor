package mmp.gps.domain.drivingRecorder;

import java.util.ArrayList;
import java.util.List;

public class QueryLoginLogResponse {
    private int total;
    private List<LoginLogoutLog> rows = new ArrayList<LoginLogoutLog>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LoginLogoutLog> getRows() {
        return rows;
    }

    public void setRows(List<LoginLogoutLog> rows) {
        this.rows = rows;
    }
}
