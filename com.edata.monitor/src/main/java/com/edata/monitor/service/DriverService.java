package com.edata.monitor.service;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.driver.Driver;
import com.edata.monitor.dao.baseinfo.driver.DriverInfo;
import com.edata.monitor.dao.baseinfo.driver.IDriverDao;
import com.edata.monitor.dao.baseinfo.vehicle.VehicleInfo;
import com.edata.monitor.dao.baseinfo.vehicle.VehicleInfoDto;
import com.edata.monitor.util.KeyValue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * sim卡服务类
 *
 * @author yangzs
 */
@Service
public class DriverService {
    @Autowired
    private IDriverDao driverDao;

    public Page<DriverInfo> query(String companyId, String filter, int pageIndex, int pageSize) throws Exception {
        Page<DriverInfo> query = new Page<DriverInfo>();
        int total = driverDao.queryPageCount(companyId, filter);
        query.total = total;
        if (total > 0) {
            List<DriverInfo> rows = driverDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            query.rows.addAll(rows);

        }
        return query;
    }

    @ServiceMethod(id = "baseinfo.driver.create", pid = "baseinfo.driver", name = "创建新的驾驶员")
    @Transactional
    public void create(Driver driver) throws Exception {
        driver.setId(ObjectId.next());


        driverDao.create(driver);
    }

    public Driver fetch(String id) throws Exception {
        Driver driver = driverDao.fetch(id);

        return driver;
    }

    @ServiceMethod(id = "baseinfo.driver.update", pid = "baseinfo.driver", name = "修改驾驶员")
    @Transactional
    public void update(Driver driver) throws Exception {


        int rows = driverDao.update(driver);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    @ServiceMethod(id = "baseinfo.driver.delete", pid = "baseinfo.driver", name = "删除驾驶员")
    @Transactional
    public void delete(String id) throws Exception {
        driverDao.delete(id);
        driverDao.deleteVehicleInDriver(id);
    }

    public List<VehicleInfo> assignedVehicles(String driverId) throws Exception {
        List<VehicleInfoDto> vehicles = driverDao.assignedVehicles(driverId);

        List<VehicleInfo> list = new ArrayList<VehicleInfo>();
        for (VehicleInfoDto dto : vehicles) {
            VehicleInfo info = new VehicleInfo();

            BeanUtils.copyProperties(dto, info);
            // info.setId(dto.id);
            // info.setDeviceNumber(dto.deviceNumber);
            // info.setPhoneNumber(dto.phoneNumber);
            // info.setPlateNumber(dto.plateNumber);
            // info.setPlateColor(dto.plateColor);
            // info.setInstallDate(dto.installDate);
            // info.setAnnualSurveyDate(dto.annualSurveyDate);
            // info.setMotorcade(dto.motorcade);
            // info.setRemark(dto.remark);
            list.add(info);
        }

        return list;
    }

    @ServiceMethod(id = "baseinfo.driver.addVehicles", pid = "baseinfo.driver", name = "驾驶员绑定车辆")
    @Transactional
    public void addVehicles(String driverId, List<String> vehicles) throws Exception {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (String id : vehicles) {
            KeyValue row = new KeyValue();
            row.setKey(driverId);
            row.setValue(id);

            rows.add(row);
        }
        driverDao.addVehicles(rows);
    }

    @ServiceMethod(id = "baseinfo.driver.removeVehicle", pid = "baseinfo.driver", name = "驾驶员解除车辆")
    @Transactional
    public void removeVehicle(String driverId, String vehicleId) throws Exception {
        driverDao.removeVehicle(driverId, vehicleId);
    }
}
