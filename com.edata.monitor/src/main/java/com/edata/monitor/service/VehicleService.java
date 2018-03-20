package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.DriverInfoDto;
import com.edata.monitor.dao.baseinfo.IVehicleDao;
import com.edata.monitor.dao.baseinfo.OwnerInfoDto;
import com.edata.monitor.dao.baseinfo.VehicleDto;
import com.edata.monitor.dao.baseinfo.VehicleInfoDto;
import com.edata.monitor.domain.baseinfo.DriverInfo;
import com.edata.monitor.domain.baseinfo.OwnerInfo;
import com.edata.monitor.domain.baseinfo.Vehicle;
import com.edata.monitor.domain.baseinfo.VehicleInfo;
import com.edata.monitor.util.KeyValue;

/**
 * 用户服务类
 * 
 * @author 生
 *
 */
@Service
public class VehicleService {
	@Autowired
	private IVehicleDao vehicleDao;

	public Page<VehicleInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = vehicleDao.queryPageCount(companyId, filter);
		Page<VehicleInfo> page = new Page<VehicleInfo>();
		page.total = total;

		if (total > 0) {
			List<VehicleInfoDto> rows = vehicleDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);

			for (VehicleInfoDto dto : rows) {
				VehicleInfo info = new VehicleInfo();
				info.setId(dto.id);
				info.setDeviceNumber(dto.deviceNumber);
				info.setPhoneNumber(dto.phoneNumber);
				info.setPlateNumber(dto.plateNumber);
				info.setPlateColor(dto.plateColor);
				info.setInstallDate(dto.installDate);
				info.setAnnualSurveyDate(dto.annualSurveyDate);
				info.setMotorcade(dto.motorcade);
				info.setMarker(dto.marker);
				info.setRemark(dto.remark);
				page.rows.add(info);
			}
		}

		return page;
	}

	@ServiceMethod(id = "baseinfo.vehicle.create", pid = "baseinfo.vehicle", name = "创建新的车辆")
	@Transactional
	public void create(Vehicle vehicle) {
		vehicle.setId(ObjectId.next());
		VehicleDto dto = new VehicleDto();
		dto.id = vehicle.getId();
		dto.companyId = vehicle.getCompanyId();
		dto.motorcadeId = vehicle.getMotorcadeId();
		dto.deviceId = vehicle.getDeviceId();
		dto.deviceNumber = vehicle.getDeviceNumber();
		dto.plateNumber = vehicle.getPlateNumber();
		dto.plateColor = vehicle.getPlateColor();
		dto.vehicleColor = vehicle.getVehicleColor();
		dto.vehicleType = vehicle.getVehicleType();
		dto.vehicleVoltage = vehicle.getVehicleVoltage();
		dto.carryType = vehicle.getCarryType();
		dto.initialMileage = vehicle.getInitialMileage();
		dto.oilWear = vehicle.getOilWear();
		dto.usefullife = vehicle.getUsefullLife();
		dto.installDate = vehicle.getInstallDate();
		dto.annualSurveyDate = vehicle.getAnnualSurveyDate();
		dto.adminArea = vehicle.getAdminArea();
		dto.marker = vehicle.getMarker();
		dto.rotate = vehicle.isRotate();
		dto.remark = vehicle.getRemark();

		vehicleDao.create(dto);
	}

	@ServiceMethod(id = "baseinfo.vehicle.update", pid = "baseinfo.vehicle", name = "修改车辆资料")
	@Transactional
	public void update(Vehicle vehicle) {
		VehicleDto dto = new VehicleDto();
		dto.id = vehicle.getId();
		dto.companyId = vehicle.getCompanyId();
		dto.motorcadeId = vehicle.getMotorcadeId();
		dto.deviceId = vehicle.getDeviceId();
		dto.deviceNumber = vehicle.getDeviceNumber();
		dto.plateNumber = vehicle.getPlateNumber();
		dto.plateColor = vehicle.getPlateColor();
		dto.vehicleColor = vehicle.getVehicleColor();
		dto.vehicleType = vehicle.getVehicleType();
		dto.vehicleVoltage = vehicle.getVehicleVoltage();
		dto.carryType = vehicle.getCarryType();
		dto.initialMileage = vehicle.getInitialMileage();
		dto.oilWear = vehicle.getOilWear();
		dto.usefullife = vehicle.getUsefullLife();
		dto.installDate = vehicle.getInstallDate();
		dto.annualSurveyDate = vehicle.getAnnualSurveyDate();
		dto.adminArea = vehicle.getAdminArea();
		dto.marker = vehicle.getMarker();
		dto.rotate = vehicle.isRotate();
		dto.remark = vehicle.getRemark();
		dto.editTime = vehicle.getEditTime();

		int rows = vehicleDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	@ServiceMethod(id = "baseinfo.vehicle.delete", pid = "baseinfo.vehicle", name = "删除车辆资料")
	@Transactional
	public void delete(String id) {
		vehicleDao.deleteVehicleInOwner(id);
		vehicleDao.deleteVehicleInDriver(id);
		vehicleDao.delete(id);
	}

	public Vehicle fetch(String id) {
		VehicleDto dto = vehicleDao.fetch(id);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(dto.id);
		vehicle.setCompanyId(dto.companyId);
		vehicle.setMotorcadeId(dto.motorcadeId);
		vehicle.setMotorcade(dto.motorcade);
		vehicle.setDeviceId(dto.deviceId);
		vehicle.setDeviceNumber(dto.deviceNumber);
		vehicle.setPlateNumber(dto.plateNumber);
		vehicle.setPlateColor(dto.plateColor);
		vehicle.setVehicleColor(dto.vehicleColor);
		vehicle.setVehicleType(dto.vehicleType);
		vehicle.setVehicleVoltage(dto.vehicleVoltage);
		vehicle.setCarryType(dto.carryType);
		vehicle.setInitialMileage(dto.initialMileage);
		vehicle.setOilWear(dto.oilWear);
		vehicle.setUsefullLife(dto.usefullife);
		vehicle.setInstallDate(dto.installDate);
		vehicle.setAnnualSurveyDate(dto.annualSurveyDate);
		vehicle.setAdminArea(dto.adminArea);
		vehicle.setMarker(dto.marker);
		vehicle.setRotate(dto.rotate);
		vehicle.setRemark(dto.remark);
		vehicle.setEditTime(dto.editTime);

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
			List<VehicleInfoDto> rows = vehicleDao.searchPageDetail(companyId, plateNumber, (pageIndex - 1) * pageSize, pageSize);
			for (VehicleInfoDto dto : rows) {
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
				page.rows.add(info);
			}
		}

		return page;
	}

	public List<OwnerInfo> assignedOwners(String vehicleId) {
		List<OwnerInfoDto> list = vehicleDao.assignedOwners(vehicleId);
		List<OwnerInfo> owners = new ArrayList<OwnerInfo>();
		for (OwnerInfoDto dto : list) {
			OwnerInfo info = new OwnerInfo();
			info.setId(dto.id);
			info.setOwnerName(dto.ownerName);
			info.setPhone(dto.phone);
			info.setIdType(dto.idType);
			info.setIdNumber(dto.idNumber);
			info.setEnabled(dto.enable);
			info.setServiceEndDate(dto.serviceEndDate);
			info.setServiceStartDate(dto.serviceStartDate);
			info.setCreateTime(dto.createTime);
			info.setRemark(dto.remark);

			owners.add(info);
		}

		return owners;
	}

	@ServiceMethod(id = "baseinfo.vehicle.addOwners", pid = "baseinfo.vehicle", name = "车辆绑定车主")
	@Transactional
	public void addOwners(String vehicleId, List<String> owners) {
		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (String id : owners) {
			KeyValue row = new KeyValue();
			row.setKey(vehicleId);
			row.setValue(id);

			rows.add(row);
		}
		vehicleDao.addOwners(rows);
	}

	@ServiceMethod(id = "baseinfo.vehicle.removeOwner", pid = "baseinfo.vehicle", name = "车辆解除车主")
	@Transactional
	public void removeOwner(String vehicleId, String ownerId) {
		vehicleDao.removeOwner(vehicleId, ownerId);
	}

	public List<DriverInfo> assignedDrivers(String vehicleId) {
		List<DriverInfoDto> list = vehicleDao.assignedDrivers(vehicleId);
		List<DriverInfo> drivers = new ArrayList<DriverInfo>();
		for (DriverInfoDto dto : list) {
			DriverInfo info = new DriverInfo();
			info.setId(dto.id);
			info.setName(dto.name);
			info.setSex(dto.sex);
			info.setPhone(dto.phone);
			info.setDrivingLicenseNumber(dto.drivingLicenseNumber);
			info.setRemark(dto.remark);

			drivers.add(info);
		}
		return drivers;
	}

	@ServiceMethod(id = "baseinfo.vehicle.addDrivers", pid = "baseinfo.vehicle", name = "车辆绑定驾驶员")
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

	@ServiceMethod(id = "baseinfo.vehicle.removeDriver", pid = "baseinfo.vehicle", name = "车辆解除驾驶员")
	@Transactional
	public void removeDriver(String vehicleId, String driverId) {
		vehicleDao.removeDriver(vehicleId, driverId);
	}

	@Transactional
	public void resetMarker(String oldName, String newName) {
		vehicleDao.resetMarker(oldName, newName);
	}
}
