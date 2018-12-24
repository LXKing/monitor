package mmp.gps.domain.statistics;

import java.util.Date;

public class AreaIoCount {

    private String motorcadeId;
    private String motorcade;
    private int in;
    private int out;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "AreaIoCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", in=" + in + ", out=" + out + ", start=" + start + ", end=" + end + '}';
    }

    public String getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    public String getMotorcade() {
        return motorcade;
    }

    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
