package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.DriverDto;
import com.edata.monitor.dao.baseinfo.DriverInfoDto;
import com.edata.monitor.dao.baseinfo.IDriverDao;
import com.edata.monitor.dao.baseinfo.VehicleInfoDto;
import com.edata.monitor.domain.baseinfo.Driver;
import com.edata.monitor.domain.baseinfo.DriverInfo;
import com.edata.monitor.domain.baseinfo.VehicleInfo;
import com.edata.monitor.util.KeyValue;

/**
 * sim卡服务类
 * 
 * @author yangzs
 *
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
			List<DriverInfoDto> rows = driverDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);

			for (DriverInfoDto dto : rows) {
				DriverInfo info = new DriverInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setSex(dto.sex);
				info.setPhone(dto.phone);
				info.setDrivingLicenseNumber(dto.drivingLicenseNumber);
				info.setRemark(dto.remark);

				query.rows.add(info);
			}
		}
		return query;
	}

	@ServiceMethod(id = "baseinfo.driver.create", pid = "baseinfo.driver", name = "创建新的驾驶员")
	@Transactional
	public void create(Driver driver) throws Exception {
		driver.setId(ObjectId.next());

		DriverDto dto = new DriverDto();
		dto.id = driver.getId();
		dto.companyId = driver.getCompanyId();
		dto.name = driver.getName();
		dto.sex = driver.getSex();
		dto.phone = driver.getPhone();
		dto.idType = driver.getIdType();
		dto.idNumber = driver.getIdNumber();
		dto.drivingLicenseNumber = driver.getDrivingLicenseNumber();
		dto.drivingLicenseExpiryDate = driver.getDrivingLicenseExpiryDate();
		dto.remark = driver.getRemark();

		driverDao.create(dto);
	}

	public Driver fetch(String id) throws Exception {
		DriverDto dto = driverDao.fetch(id);
		Driver driver = new Driver();
		driver.setId(dto.id);
		driver.setCompanyId(dto.companyId);
		driver.setName(dto.name);
		driver.setSex(dto.sex);
		driver.setPhone(dto.phone);
		driver.setIdType(dto.idType);
		driver.setIdNumber(dto.idNumber);
		driver.setDrivingLicenseNumber(dto.drivingLicenseNumber);
		driver.setDrivingLicenseExpiryDate(dto.drivingLicenseExpiryDate);
		driver.setRemark(dto.remark);
		driver.setEditTime(dto.editTime);

		return driver;
	}

	@ServiceMethod(id = "baseinfo.driver.update", pid = "baseinfo.driver", name = "修改驾驶员")
	@Transactional
	public void update(Driver driver) throws Exception {
		DriverDto dto = new DriverDto();
		dto.id = driver.getId();
		dto.companyId = driver.getCompanyId();
		dto.name = driver.getName();
		dto.sex = driver.getSex();
		dto.phone = driver.getPhone();
		dto.idType = driver.getIdType();
		dto.idNumber = driver.getIdNumber();
		dto.drivingLicenseNumber = driver.getDrivingLicenseNumber();
		dto.drivingLicenseExpiryDate = driver.getDrivingLicenseExpiryDate();
		dto.remark = driver.getRemark();
		dto.editTime = driver.getEditTime();

		int rows = driverDao.update(dto);
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
			info.setId(dto.id);
			info.setDeviceNumber(dto.deviceNumber);
			info.setPhoneNumber(dto.phoneNumber);
			info.setPlateNumber(dto.plateNumber);
			info.setPlateColor(dto.plateColor);
			info.setInstallDate(dto.installDate);
			info.setAnnualSurveyDate(dto.annualSurveyDate);
			info.setMotorcade(dto.motorcade);
			info.setRemark(dto.remark);
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
