package mmp.gps.domain.log;

import java.util.Date;

public class ParameterLog {

    private String number;
    private Date time;
    private String event;

    @Override
    public String toString() {
        return "ParameterLog{" + "number='" + number + '\'' + ", time=" + time + ", event='" + event + '\'' + '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
