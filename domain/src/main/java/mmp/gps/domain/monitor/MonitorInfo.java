package mmp.gps.domain.monitor;

public class MonitorInfo {

    private String id;
    private String name;
    private String type;
    private String remark;

    @Override
    public String toString() {
        return "MonitorInfo{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", type='" + type + '\'' + ", " + "remark='" + remark + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
