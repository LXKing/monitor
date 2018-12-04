package mmp.gps.monitor.dao;

import mmp.gps.common.util.KeyValue;
import mmp.gps.domain.driver.Driver;
import mmp.gps.domain.driver.DriverInfo;
import mmp.gps.domain.vehicle.VehicleInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 驾驶员数据访问接口
 */

@Repository
public interface IDriverDao {
    /**
     * 查询驾驶员行数
     */
    int queryPageCount(String companyId, String filter);

    /**
     * 查询驾驶员内容
     */
    List<DriverInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    /**
     * 创建驾驶员
     */
    void create(Driver dto);

    /**
     * 修改驾驶员
     */
    int update(Driver dto);

    /**
     * 删除驾驶员
     */
    void delete(String id);

    /**
     * 删除驾驶员与车辆关联
     */
    void deleteVehicleInDriver(String driverId);

    /**
     * 读取驾驶员
     */
    Driver fetch(String id);

    /**
     * 获取驾驶员与车辆关系
     */
    List<VehicleInfoDto> assignedVehicles(String driverId);

    /**
     * 添加驾驶员与车辆关系
     */
    int addVehicles(List<KeyValue> vehicles);

    /**
     * 删除驾驶员与车辆关系
     */
    void removeVehicle(String driverId, String vehicleId);
}
