package com.rayton.gps.dao.locate;

/**
 * 组别车辆
 *
 * @author yangzs
 */
public class GroupVehicle extends Latest {

    private String id;
    private String pid;
    private int type;
    private String icon;
    private String marker;
    private int rotate;

    private String iconOpen;
    private String iconClose;

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    private String inum;
    private String driname;

    private String phone;

    private String sim;

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInum() {
        return inum;
    }

    public void setInum(String inum) {
        this.inum = inum;
    }

    public String getDriname() {
        return driname;
    }

    public void setDriname(String driname) {
        this.driname = driname;
    }

    @Override
    public String toString() {
        return "GroupVehicle{" + "id='" + id + '\'' + ", pid='" + pid + '\'' + ", type=" + type + ", icon='" + icon + '\'' + ", marker='" + marker + '\'' + ", rotate=" + rotate + '}';
    }

    /**
     * 获取组别或车辆id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置组别或车辆id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取组别或车辆的父id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置组别或车辆的父id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取类型:0车辆，1车队,2企业
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型:0车辆，1车队,2企业
     */
    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}
