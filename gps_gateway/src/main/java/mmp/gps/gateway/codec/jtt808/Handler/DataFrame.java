package mmp.gps.gateway.codec.jtt808.Handler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.jboss.netty.util.internal.ConcurrentHashMap;

class DataFrame {

    private String number;
    private int command;
    private int serialNumber;
    private int page;
    private int pages;
    private byte[] top;
    private Map after = new ConcurrentHashMap();
    private Date lastUploadTime;
    private ScheduledFuture future;


    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCommand() {
        return this.command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public byte[] getTop() {
        return this.top;
    }

    public void setTop(byte[] top) {
        this.top = top;
    }

    public Map getAfter() {
        return this.after;
    }

    public void setAfter(Map after) {
        this.after = after;
    }

    public Date getLastUploadTime() {
        return this.lastUploadTime;
    }

    public void setLastUploadTime(Date lastUploadTime) {
        this.lastUploadTime = lastUploadTime;
    }

    public boolean isCompleted() {
        return this.top != null && this.after.size() == this.pages - 1;
    }

    public ScheduledFuture getFuture() {
        return this.future;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
    }
}
