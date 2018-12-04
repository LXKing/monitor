package mmp.gps.domain.area;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 圆形区域类
 */
public class CircleArea {

    private long id;
    private String companyId;
    @NotEmpty
    private String name;
    private boolean deviceCatch = true;
    private int flag;
    private double lat;
    private double lng;
    private int radius;
    private int maxSpeed;
    private short overspeedSeconds;
    private Date startTime;
    private Date endTime;
    private String remark;
    private Timestamp editTime;


    @Override
    public String toString() {
        return "CircleArea{" + "id=" + id + ", companyId='" + companyId + '\'' + ", name='" + name + '\'' + ", " + "deviceCatch=" + deviceCatch + ", flag=" + flag + ", lat=" + lat + ", lng=" + lng + ", radius=" + radius + ", maxSpeed=" + maxSpeed + ", overspeedSeconds=" + overspeedSeconds + ", startTime=" + startTime + ", endTime=" + endTime + ", remark='" + remark + '\'' + ", editTime=" + editTime + '}';
    }

    /**
     * 获取唯一编号
     */
    public long getId() {
        return id;
    }

    /**
     * 设置唯一编号
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取所属公司id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置所属公司id
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取区域名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区域名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取终端计算否
     */
    public boolean isDeviceCatch() {
        return deviceCatch;
    }

    /**
     * 设置终端计算否
     */
    public void setDeviceCatch(boolean deviceCatch) {
        this.deviceCatch = deviceCatch;
    }

    /**
     * 获取区域属性
     */
    public int getFlag() {
        return flag;
    }

    /**
     * 设置区域属性
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * 获取中心点纬度
     */
    public double getLat() {
        return lat;
    }

    /**
     * 设置中心点纬度
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * 获取中心点经度
     */
    public double getLng() {
        return lng;
    }

    /**
     * 设置中心点经度
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * 获取半径
     */
    public int getRadius() {
        return radius;
    }

    /**
     * 设置半径
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * 获取最高速度
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 设置最高速度
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 获取超速持续时间
     */
    public short getOverspeedSeconds() {
        return overspeedSeconds;
    }

    /**
     * 设置超速持续时间
     */
    public void setOverspeedSeconds(short overspeedSeconds) {
        this.overspeedSeconds = overspeedSeconds;
    }

    /**
     * 获取开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    /**
     * 获取时间戳
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 设置时间戳
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }


}
