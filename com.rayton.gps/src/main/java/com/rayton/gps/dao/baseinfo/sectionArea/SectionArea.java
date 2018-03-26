package com.rayton.gps.dao.baseinfo.sectionArea;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 路段类
 *
 * @author yangzs
 */
public class SectionArea {

    private long id;
    private String companyId;
    private String name;
    private short width;
    private short flag;
    private int maxSeconds;
    private int minSeconds;
    private int maxSpeed;
    private short overspeedSeconds;
    private String remark;
    private Timestamp editTime;
    private List<SectionPoint> points = new ArrayList<SectionPoint>();

    @Override
    public String toString() {
        return "SectionArea{" + "id=" + id + ", companyId='" + companyId + '\'' + ", name='" + name + '\'' + ", " +
                "width=" + width + ", flag=" + flag + ", maxSeconds=" + maxSeconds + ", minSeconds=" + minSeconds +
                "," + " maxSpeed=" + maxSpeed + ", overspeedSeconds=" + overspeedSeconds + ", remark='" + remark +
                '\'' + "," + " editTime=" + editTime + ", points=" + points + '}';
    }

    /**
     * 获取路段id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置路段id
     */
    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取路段宽度
     */
    public short getWidth() {
        return width;
    }

    /**
     * 设置路段宽度
     */
    public void setWidth(short width) {
        this.width = width;
    }

    /**
     * 获取路段属性
     */
    public short getFlag() {
        return flag;
    }

    /**
     * 设置路段属性
     */
    public void setFlag(short flag) {
        this.flag = flag;
    }

    /**
     * 获取路段行驶过长阈值
     */
    public int getMaxSeconds() {
        return maxSeconds;
    }

    /**
     * 设置路段行驶过长阈值
     */
    public void setMaxSeconds(int maxSeconds) {
        this.maxSeconds = maxSeconds;
    }

    /**
     * 获取路段行驶不足阈值
     */
    public int getMinSeconds() {
        return minSeconds;
    }

    /**
     * 设置路段行驶不足阈值
     */
    public void setMinSeconds(int minSeconds) {
        this.minSeconds = minSeconds;
    }

    /**
     * 获取路段最高速度
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 设置路段最高速度
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 获取路段超速持续时间
     */
    public short getOverspeedSeconds() {
        return overspeedSeconds;
    }

    /**
     * 设置路段超速持续时间
     */
    public void setOverspeedSeconds(short overspeedSeconds) {
        this.overspeedSeconds = overspeedSeconds;
    }

    public String getRemark() {
        return remark;
    }

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

    /**
     * 获取路径点
     */
    public List<SectionPoint> getPoints() {
        return points;
    }

    /**
     * 设置路径点
     */
    public void setPoints(List<SectionPoint> points) {
        this.points = points;
    }
}
