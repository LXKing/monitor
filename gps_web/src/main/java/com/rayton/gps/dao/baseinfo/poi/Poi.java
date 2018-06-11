package com.rayton.gps.dao.baseinfo.poi;

import java.sql.Timestamp;

/**
 * 兴趣点类
 *
 * @author yangzs
 */
public class Poi {

    private long id;
    private String companyId;
    private String type;
    private String name;
    private double lat;
    private double lng;
    private String remark;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "Poi{" + "id=" + id + ", companyId='" + companyId + '\'' + ", type='" + type + '\'' + ", name='" + name + '\'' + ", lat=" + lat + ", lng=" + lng + ", remark='" + remark + '\'' + ", editTime=" + editTime + '}';
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
     * 获取公司唯一编号
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司唯一编号
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类别
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
     * 获取纬度
     */
    public double getLat() {
        return lat;
    }

    /**
     * 设置纬度
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * 获取经度
     */
    public double getLng() {
        return lng;
    }

    /**
     * 设置经度
     */
    public void setLng(double lng) {
        this.lng = lng;
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
