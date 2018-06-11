package com.rayton.gps.dao.baseinfo.sectionArea;

/**
 * 路段点类
 *
 * @author yangzs
 */
public class SectionPoint {


    private long id;
    private Long sectionId;
    private double lat;
    private double lng;
    private int index;

    @Override
    public String toString() {
        return "SectionPoint{" + "id=" + id + ", sectionId=" + sectionId + ", lat=" + lat + ", lng=" + lng + ", " + "index=" + index + '}';
    }

    /**
     * 获取拐点id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置拐点id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取路段id
     */
    public Long getSectionId() {
        return sectionId;
    }

    /**
     * 设置路段id
     */
    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * 获取拐点纬度
     */
    public double getLat() {
        return lat;
    }

    /**
     * 设置拐点纬度
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * 获取拐点经度
     */
    public double getLng() {
        return lng;
    }

    /**
     * 设置拐点经度
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
