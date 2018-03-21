package com.edata.monitor.dao.baseinfo.polygonArea;

import com.edata.common.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩形区域信息
 *
 * @author yangzs
 */
public class PolygonAreaInfo {
    private long id;
    private String name;
    private boolean deviceCatch;
    private String remark;
    private List<LatLng> points = new ArrayList<LatLng>();

    @Override
    public String toString() {
        return "PolygonAreaInfo{" + "id=" + id + ", name='" + name + '\'' + ", deviceCatch=" + deviceCatch + ", " +
                "remark='" + remark + '\'' + ", points=" + points + '}';
    }

    /**
     * 获取记录唯一编号
     */
    public long getId() {
        return id;
    }

    /**
     * 设置记录唯一编号
     */
    public void setId(long id) {
        this.id = id;
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
     * 获取路径点
     */
    public List<LatLng> getPoints() {
        return points;
    }

    /**
     * 设置路径点
     */
    public void setPoints(List<LatLng> points) {
        this.points = points;
    }
}
