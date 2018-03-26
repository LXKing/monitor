package com.rayton.gps.dao.devicedata.log;

import java.util.Date;
import java.util.List;

public class SpeedStatusLog {

    private Date startTime;
    private Date endTime;
    private String state;
    private List<Short> content;

    @Override
    public String toString() {
        return "SpeedStatusLog{" + "startTime=" + startTime + ", endTime=" + endTime + ", state='" + state + '\'' +
                "," + " content=" + content + '}';
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Short> getContent() {
        return content;
    }

    public void setContent(List<Short> content) {
        this.content = content;
    }
}
