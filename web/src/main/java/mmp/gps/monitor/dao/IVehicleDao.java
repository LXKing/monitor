package mmp.gps.monitor.dao;


import mmp.gps.common.util.KeyValue;
import mmp.gps.domain.driver.DriverInfo;
import mmp.gps.domain.vehicle.Vehicle;
import mmp.gps.domain.vehicle.VehicleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆数据访问接口
 */

@Repository
public interface IVehicleDao {


    int transfer(String vehicleId, String companyId, String motorcadeId);


    /**
     * 查询车辆总行数
     */
    int queryPageCount(String companyId, String filter);

    /**
     * 查询车辆页内容
     */
    List<VehicleInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);


    List<DriverInfo> groupShit(String num);

    /**
     * 创建车辆
     */
    void create(Vehicle dto);

    /**
     * 修改车辆
     */
    int update(Vehicle dto);

    /**
     * 删除车辆与车主
     */
    void deleteVehicleInOwner(String vehicleId);

    /**
     * 删除车辆
     */
    void deleteVehicleInDriver(String vehicleId);

    /**
     * 删除车辆
     */
    void delete(String id);

    /**
     * 读取车辆
     */
    Vehicle fetch(String id);

    /**
     * 是否已存在车辆
     */
    boolean exist(String plateNumber);

    /**
     * 是否已存在车辆
     */
    boolean existOutId(String plateNumber, String id);

    /**
     * 搜索车辆总行数
     */
    int searchPageCount(String companyId, String plateNumber);

    /**
     * 搜索车辆页内容
     */
    List<VehicleInfo> searchPageDetail(String companyId, String plateNumber, int pageIndex, int pageSize);


    /**
     * 添加车辆与车主关系
     */
    void addOwners(List<KeyValue> owners);

    /**
     * 删除车辆与车主关系
     */
    void removeOwner(String vehicleId, String ownerId);

    /**
     * 获取车辆与驾驶员关系
     */
    List<DriverInfo> assignedDrivers(String vehicleId);

    /**
     * 添加车辆与驾驶员关系
     */
    void addDrivers(List<KeyValue> drivers);

    /**
     * 删除车辆与驾驶员关系
     */
    void removeDriver(String vehicleId, String driverId);

    /**
     * 重置车辆图标
     */
    void resetMarker(String oldName, String newName);

    String getByD(String dn);

    Vehicle getWhat(String dn);


    Vehicle getSS(String dn);


    int addGC(String MOTORCADEID, String ID);
}
