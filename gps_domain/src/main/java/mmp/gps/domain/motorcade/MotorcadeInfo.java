package mmp.gps.domain.motorcade;

/**
 * 车队信息类
 */
public class MotorcadeInfo {

    private String id;
    private String type;
    private String name;
    private boolean parentVisible;
    private String remark;

    private String PID;

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    @Override
    public String toString() {
        return "MotorcadeInfo{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", name='" + name + '\'' + ", " + "parentVisible=" + parentVisible + ", remark='" + remark + '\'' + '}';
    }

    /**
     * 获取记录ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取车队类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置车队类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级可见否
     */
    public boolean isParentVisible() {
        return parentVisible;
    }

    /**
     * 设置上级可见否
     */
    public void setParentVisible(boolean parentVisible) {
        this.parentVisible = parentVisible;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
