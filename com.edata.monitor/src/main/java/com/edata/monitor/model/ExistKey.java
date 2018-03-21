package com.edata.monitor.model;

public class ExistKey {

    private String key;
    private String id;
    private boolean checkId;

    @Override
    public String toString() {
        return "ExistKey{" + "key='" + key + '\'' + ", id='" + id + '\'' + ", checkId=" + checkId + '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheckId() {
        return checkId;
    }

    public void setCheckId(boolean checkId) {
        this.checkId = checkId;
    }

}
