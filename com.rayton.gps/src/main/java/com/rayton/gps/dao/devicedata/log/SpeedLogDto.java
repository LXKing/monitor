package com.rayton.gps.dao.devicedata.log;

import java.util.Arrays;
import java.util.Date;

public class SpeedLogDto {
    public Date time;
    public byte[] content;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SpeedLogDto{" + "time=" + time + ", content=" + Arrays.toString(content) + '}';
    }
}
