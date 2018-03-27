package com.rayton.gps.dao.cache.association;

public class MonitorTargetDto {
    /**
     * 车辆、车队、企业的唯一编号
     */
    public String id;
    /**
     * 车辆、车队、企业的父级唯一编号
     */
    public String pid;
    /**
     * 类型:0车辆，1车队,2企业
     */
    public int type;
    /**
     * 名称，车队名、车牌号、企业名
     */
    public String name;
    /**
     * 设备号
     */
    public String deviceNumber;
    /**
     * 车辆图标
     */
    public String marker;
    /**
     * 是否旋转车辆图标
     */
    public int rotate;
}
