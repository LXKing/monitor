package com.edata.monitor.model.baseinfo;


public class AdminMenu {

    private String id;
    private String pid;
    private String text;
    private String url;
    private boolean leaf;

    @Override
    public String toString() {
        return "AdminMenu{" + "id='" + id + '\'' + ", pid='" + pid + '\'' + ", text='" + text + '\'' + ", url='" +
                url + '\'' + ", leaf=" + leaf + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
