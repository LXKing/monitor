package mmp.gps.domain.monitor;

public class MonitorTarget {

    private String vid;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    private String id;
    private String pid;
    private int type;
    private String name;
    private String deviceNumber;
    private String marker;
    private int rotate;

    @Override
    public String toString() {
        return "MonitorTarget{" + "id='" + id + '\'' + ", pid='" + pid + '\'' + ", type=" + type + ", name='" + name + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", marker='" + marker + '\'' + ", rotate=" + rotate + '}';
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

    /**
     * 获取名称，车队名、车牌号、企业名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称，车队名、车牌号、企业名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取设备号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设置设备号
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
