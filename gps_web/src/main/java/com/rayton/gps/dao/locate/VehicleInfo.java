package com.rayton.gps.dao.locate;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆资料类
 *
 * @author yangzs
 */
public class VehicleInfo {

    private String id;
    private VehicleBaseInfo baseInfo;
    private List<OwnerBaseInfo> owners = new ArrayList<OwnerBaseInfo>();
    private List<DriverBaseInfo> drivers = new ArrayList<DriverBaseInfo>();

    @Override
    public String toString() {
        return "VehicleInfo{" + "id='" + id + '\'' + ", baseInfo=" + baseInfo + ", owners=" + owners + ", drivers=" +
                drivers + '}';
    }

    /**
     * 获取id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取车辆基本信息
     */
    public VehicleBaseInfo getBaseInfo() {
        return baseInfo;
    }

    /**
     * 设置车辆基本信息
     */
    public void setBaseInfo(VehicleBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    /**
     * 获取车主资料
     */
    public List<OwnerBaseInfo> getOwners() {
        return owners;
    }

    /**
     * 设置车主资料
     */
    public void setOwners(List<OwnerBaseInfo> owners) {
        this.owners = owners;
    }

    /**
     * 获取驾驶员资料
     */
    public List<DriverBaseInfo> getDrivers() {
        return drivers;
    }

    /**
     * 设置驾驶员资料
     */
    public void setDrivers(List<DriverBaseInfo> drivers) {
        this.drivers = drivers;
    }

}
