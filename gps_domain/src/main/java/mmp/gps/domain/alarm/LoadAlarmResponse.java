package mmp.gps.domain.alarm;

import java.util.ArrayList;
import java.util.List;

public class LoadAlarmResponse {
    private int total;
    private List<Alarm> alarms = new ArrayList<Alarm>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
}
