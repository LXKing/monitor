package mmp.gps.monitor.service;

import mmp.gps.common.util.Errors;
import mmp.gps.common.util.KeyValue;
import mmp.gps.common.util.ObjectId;
import mmp.gps.common.util.Page;
import mmp.gps.domain.locate.LatestDto;
import mmp.gps.domain.vehicle.Vehicle;
import mmp.gps.domain.vehicle.VehicleInfo;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.IVehicleDao;
import mmp.gps.monitor.godp.IGodpDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户服务类
 */
@Service
public class VehicleService {
    @Autowired
    private IVehicleDao vehicleDao;
    @Autowired
    private IGodpDao godpDao;


    public Page<VehicleInfo> query(String companyId, String filter, int pageIndex, int pageSize) throws Exception {
        int total = vehicleDao.queryPageCount(companyId, filter);
        Page<VehicleInfo> page = new Page<VehicleInfo>();
        page.total = total;

        if (total > 0) {
            List<VehicleInfo> rows = vehicleDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            List<String> list = new ArrayList<>();
            rows.forEach(vehicleInfo -> {
                list.add(vehicleInfo.getDeviceNumber());

            });
            Map<String, LatestDto> map = godpDao.loadLatest(list);

            rows.forEach(vehicleInfo -> {
//                System.out.println(map);
//                System.out.println(vehicleInfo.getDeviceNumber());
//                System.out.println(map.get(vehicleInfo.getDeviceNumber()));
                LatestDto dto = map.get(vehicleInfo.getDeviceNumber());
                System.out.println(dto);

                if (dto != null) {
                    System.out.println(dto.gt);
                    vehicleInfo.setGpstime(dto.gt);
                }


//

            });


            page.rows.addAll(rows);
            // for (VehicleInfoDto dto : rows) {
            //     VehicleInfo info = new VehicleInfo();
            //     info.setId(dto.id);
            //     info.setDeviceNumber(dto.deviceNumber);
            //     info.setPhoneNumber(dto.phoneNumber);
            //     info.setPlateNumber(dto.plateNumber);
            //     info.setPlateColor(dto.plateColor);
            //     info.setInstallDate(dto.installDate);
            //     info.setAnnualSurveyDate(dto.annualSurveyDate);
            //     info.setMotorcade(dto.motorcade);
            //     info.setMarker(dto.marker);
            //     info.setRemark(dto.remark);
            //     page.rows.add(info);
            // }
        }

        return page;
    }


    @RequiresPermissions("baseinfo.vehicle.create")
    @Log(name = "创建新的车辆")
    @Transactional
    public void create(Vehicle vehicle) {
        vehicle.setId(ObjectId.next());
        // VehicleDto dto = new VehicleDto();
        // dto.id = vehicle.getId();
        // dto.companyId = vehicle.getCompanyId();
        // dto.motorcadeId = vehicle.getMotorcadeId();
        // dto.deviceId = vehicle.getDeviceId();
        // dto.deviceNumber = vehicle.getDeviceNumber();
        // dto.plateNumber = vehicle.getPlateNumber();
        // dto.plateColor = vehicle.getPlateColor();
        // dto.vehicleColor = vehicle.getVehicleColor();
        // dto.vehicleType = vehicle.getVehicleType();
        // dto.vehicleVoltage = vehicle.getVehicleVoltage();
        // dto.carryType = vehicle.getCarryType();
        // dto.initialMileage = vehicle.getInitialMileage();
        // dto.oilWear = vehicle.getOilWear();
        // dto.usefullife = vehicle.getusefulLife();
        // dto.installDate = vehicle.getInstallDate();
        // dto.annualSurveyDate = vehicle.getAnnualSurveyDate();
        // dto.adminArea = vehicle.getAdminArea();
        // dto.marker = vehicle.getMarker();
        // dto.rotate = vehicle.isRotate();
        // dto.remark = vehicle.getRemark();

        vehicleDao.create(vehicle);
    }


    @RequiresPermissions("baseinfo.vehicle.update")
    @Log(name = "修改车辆资料")
    @Transactional
    public void update(Vehicle vehicle) {
        // VehicleDto dto = new VehicleDto();
        // dto.id = vehicle.getId();
        // dto.companyId = vehicle.getCompanyId();
        // dto.motorcadeId = vehicle.getMotorcadeId();
        // dto.deviceId = vehicle.getDeviceId();
        // dto.deviceNumber = vehicle.getDeviceNumber();
        // dto.plateNumber = vehicle.getPlateNumber();
        // dto.plateColor = vehicle.getPlateColor();
        // dto.vehicleColor = vehicle.getVehicleColor();
        // dto.vehicleType = vehicle.getVehicleType();
        // dto.vehicleVoltage = vehicle.getVehicleVoltage();
        // dto.carryType = vehicle.getCarryType();
        // dto.initialMileage = vehicle.getInitialMileage();
        // dto.oilWear = vehicle.getOilWear();
        // dto.usefullife = vehicle.getusefulLife();
        // dto.installDate = vehicle.getInstallDate();
        // dto.annualSurveyDate = vehicle.getAnnualSurveyDate();
        // dto.adminArea = vehicle.getAdminArea();
        // dto.marker = vehicle.getMarker();
        // dto.rotate = vehicle.isRotate();
        // dto.remark = vehicle.getRemark();
        // dto.editTime = vehicle.getEditTime();
        int rows = 0;
        try {
            rows = vehicleDao.update(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }


    @RequiresPermissions("baseinfo.vehicle.delete")
    @Log(name = "删除车辆资料")
    @Transactional
    public void delete(String id) {
        vehicleDao.deleteVehicleInOwner(id);
        vehicleDao.deleteVehicleInDriver(id);
        vehicleDao.delete(id);
    }

    public Vehicle fetch(String id) {
        Vehicle vehicle = vehicleDao.fetch(id);
        // Vehicle vehicle = new Vehicle();
        // vehicle.setId(dto.id);
        // vehicle.setCompanyId(dto.companyId);
        // vehicle.setMotorcadeId(dto.motorcadeId);
        // vehicle.setMotorcade(dto.motorcade);
        // vehicle.setDeviceId(dto.deviceId);
        // vehicle.setDeviceNumber(dto.deviceNumber);
        // vehicle.setPlateNumber(dto.plateNumber);
        // vehicle.setPlateColor(dto.plateColor);
        // vehicle.setVehicleColor(dto.vehicleColor);
        // vehicle.setVehicleType(dto.vehicleType);
        // vehicle.setVehicleVoltage(dto.vehicleVoltage);
        // vehicle.setCarryType(dto.carryType);
        // vehicle.setInitialMileage(dto.initialMileage);
        // vehicle.setOilWear(dto.oilWear);
        // vehicle.setusefulLife(dto.usefullife);
        // vehicle.setInstallDate(dto.installDate);
        // vehicle.setAnnualSurveyDate(dto.annualSurveyDate);
        // vehicle.setAdminArea(dto.adminArea);
        // vehicle.setMarker(dto.marker);
        // vehicle.setRotate(dto.rotate);
        // vehicle.setRemark(dto.remark);
        // vehicle.setEditTime(dto.editTime);

        return vehicle;
    }

    public boolean exist(String plateNumber) {
        return vehicleDao.exist(plateNumber);
    }

    public boolean exist(String plateNumber, String id) {
        return vehicleDao.existOutId(plateNumber, id);
    }

    public Page<VehicleInfo> search(String companyId, String plateNumber, int pageIndex, int pageSize) {
        int total = vehicleDao.searchPageCount(companyId, plateNumber);
        Page<VehicleInfo> page = new Page<VehicleInfo>();
        page.total = total;
        if (total > 0) {
            List<VehicleInfo> rows = vehicleDao.searchPageDetail(companyId, plateNumber, (pageIndex - 1) * pageSize, pageSize);
            page.rows.addAll(rows);
            // for (VehicleInfoDto dto : rows) {
            //     VehicleInfo info = new VehicleInfo();
            //     info.setId(dto.id);
            //     info.setDeviceNumber(dto.deviceNumber);
            //     info.setPhoneNumber(dto.phoneNumber);
            //     info.setPlateNumber(dto.plateNumber);
            //     info.setPlateColor(dto.plateColor);
            //     info.setInstallDate(dto.installDate);
            //     info.setAnnualSurveyDate(dto.annualSurveyDate);
            //     info.setMotorcade(dto.motorcade);
            //     info.setRemark(dto.remark);
            //     page.rows.add(info);
            // }
        }

        return page;
    }


    @RequiresPermissions("baseinfo.vehicle.addDrivers")
    @Log(name = "车辆绑定驾驶员")
    @Transactional
    public void addDrivers(String vehicleId, List<String> drivers) {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (String id : drivers) {
            KeyValue row = new KeyValue();
            row.setKey(vehicleId);
            row.setValue(id);

            rows.add(row);
        }
        vehicleDao.addDrivers(rows);
    }

    @RequiresPermissions("baseinfo.vehicle.removeDriver")
    @Log(name = "车辆解除驾驶员")
    @Transactional
    public void removeDriver(String vehicleId, String driverId) {
        vehicleDao.removeDriver(vehicleId, driverId);
    }

    @Transactional
    public void resetMarker(String oldName, String newName) {
        vehicleDao.resetMarker(oldName, newName);
    }
}
