package com.rayton.gps.dao.baseinfo;

import java.util.Date;

public class MarkerFileInfo {

    private String name;
    private String img;
    private Date time;

    @Override
    public String toString() {
        return "MarkerFileInfo{" + "name='" + name + '\'' + ", img='" + img + '\'' + ", time=" + time + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
