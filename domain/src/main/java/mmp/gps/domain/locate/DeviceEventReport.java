package mmp.gps.domain.locate;

import java.util.Date;

public class DeviceEventReport {
    private String id;
    private String number;
    private String plateNumber;
    private Date time;
    private int eventId;
    private String content;

    @Override
    public String toString() {
        return "DeviceEventReport{" + "id='" + id + '\'' + ", number='" + number + '\'' + ", plateNumber='" + plateNumber + '\'' + ", time=" + time + ", eventId=" + eventId + ", content='" + content + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
