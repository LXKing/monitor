package com.edata.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.DeviceDto;
import com.edata.monitor.dao.baseinfo.DeviceInfoDto;
import com.edata.monitor.dao.baseinfo.DeviceSearchInfoDto;
import com.edata.monitor.dao.baseinfo.IDeviceDao;
import com.edata.monitor.dao.baseinfo.IUserDao;
import com.edata.monitor.dao.baseinfo.UserDto;
import com.edata.monitor.domain.baseinfo.Device;
import com.edata.monitor.domain.baseinfo.DeviceInfo;
import com.edata.monitor.domain.baseinfo.DeviceSearchInfo;
import com.edata.monitor.domain.baseinfo.User;
import com.edata.monitor.godp.IGodpDao;
import com.edata.monitor.util.kind.UserKinds;

/**
 * 设备服务类
 * 
 * @author 生
 *
 */
@Service
public class DeviceService {
	@Autowired
	private IDeviceDao deviceDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IGodpDao godpDao;

	public Page<DeviceInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = deviceDao.queryPageCount(companyId, filter);
		Page<DeviceInfo> query = new Page<DeviceInfo>();
		query.total = total;
		if (total > 0) {
			List<DeviceInfoDto> rows = deviceDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (DeviceInfoDto dto : rows) {
				DeviceInfo info = new DeviceInfo();
				info.setServiceStartDate(dto.serviceStartDate);
				info.setServiceEndDate(dto.serviceEndDate);
				info.setEnable(dto.enable);
				info.setId(dto.id);
				info.setDeviceNumber(dto.deviceNumber);
				info.setPhoneNumber(dto.phoneNumber);
				info.setRemark(dto.remark);
				info.setWarranty(dto.warranty);
				info.setFactoryNumber(dto.factoryNumber);
				info.setModel(dto.model);
				query.rows.add(info);
			}
		}
		return query;
	}

	@ServiceMethod(id = "baseinfo.device.create", pid = "baseinfo.device", name = "创建新的终端资料")
	@Transactional
	public void create(Device device, User user) {
		String id = ObjectId.next();
		device.setId(id);
		user.setId(id);

		DeviceDto d = new DeviceDto();
		d.id = device.getId();
		d.companyId = device.getCompanyId();
		d.deviceNumber = device.getDeviceNumber();
		d.simcardId = device.getSimcardId();
		d.phoneNumber = device.getPhoneNumber();
		d.model = device.getModel();
		d.factoryName = device.getFactoryName();
		d.factoryNumber = device.getFactoryNumber();
		d.cameras = device.getCameras();
		d.hasMicrophone = device.isHasMicrophone();
		d.hasNavigation = device.isHasNavigation();
		d.sensors = device.getSensors();
		d.warranty = device.getWarranty();
		d.purchaseDate = device.getPurchaseDate();
		d.installDate = device.getInstallDate();

		UserDto u = new UserDto();
		u.id = user.getId();
		u.pid = user.getPid();
		u.companyId = user.getCompanyId();
		u.kind = UserKinds.Device.getIndex();
		u.account = user.getAccount();
		u.name = user.getName();
		u.contact = user.getContact();
		u.createTime = user.getCreateTime();
		u.email = user.getEmail();
		u.enable = user.isEnable();
		u.password = "888888";
		u.phone = user.getPhone();
		u.remark = user.getRemark();
		u.serviceEndDate = user.getServiceEndDate();
		u.serviceStartDate = user.getServiceStartDate();

		deviceDao.create(d);
		userDao.create(u);

		godpDao.syncDeviceInUser(d.deviceNumber, true);
	}

	@ServiceMethod(id = "baseinfo.device.update", pid = "baseinfo.device", name = "修改终端资料")
	@Transactional
	public void update(Device device, User user) {
		String oldNumber = deviceDao.getDeviceNumber(device.getId());

		DeviceDto d = new DeviceDto();
		d.id = device.getId();
		d.companyId = device.getCompanyId();
		d.deviceNumber = device.getDeviceNumber();
		d.simcardId = device.getSimcardId();
		d.phoneNumber = device.getPhoneNumber();
		d.model = device.getModel();
		d.factoryName = device.getFactoryName();
		d.factoryNumber = device.getFactoryNumber();
		d.cameras = device.getCameras();
		d.hasMicrophone = device.isHasMicrophone();
		d.hasNavigation = device.isHasNavigation();
		d.sensors = device.getSensors();
		d.warranty = device.getWarranty();
		d.purchaseDate = device.getPurchaseDate();
		d.installDate = device.getInstallDate();
		d.editTime=device.getEditTime();

		UserDto u = new UserDto();
		u.id = user.getId();
		u.pid = user.getPid();
		u.companyId = user.getCompanyId();
		u.kind = UserKinds.Device.getIndex();
		u.account = user.getAccount();
		u.name = user.getName();
		u.contact = user.getContact();
		u.createTime = user.getCreateTime();
		u.email = user.getEmail();
		u.enable = user.isEnable();
		u.phone = user.getPhone();
		u.remark = user.getRemark();
		u.serviceEndDate = user.getServiceEndDate();
		u.serviceStartDate = user.getServiceStartDate();
		u.editTime=user.getEditTime();

		int rows = deviceDao.update(d);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
		rows = userDao.update(u);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);

		if (!oldNumber.equals(d.deviceNumber)) {
			godpDao.syncDeviceInUser(oldNumber, false);
			godpDao.syncDeviceInUser(d.deviceNumber, true);
		}
	}

	public Tuple<Device, User> fetch(String id) {
		DeviceDto d = deviceDao.fetch(id);
		Device device = new Device();
		device.setId(d.id);
		device.setCompanyId(d.companyId);
		device.setDeviceNumber(d.deviceNumber);
		device.setSimcardId(d.simcardId);
		device.setPhoneNumber(d.phoneNumber);
		device.setModel(d.model);
		device.setFactoryName(d.factoryName);
		device.setFactoryNumber(d.factoryNumber);
		device.setCameras(d.cameras);
		device.setHasMicrophone(d.hasMicrophone);
		device.setHasNavigation(d.hasNavigation);
		device.setSensors(d.sensors);
		device.setWarranty(d.warranty);
		device.setPurchaseDate(d.purchaseDate);
		device.setInstallDate(d.installDate);
		device.setEditTime(d.editTime);

		UserDto u = userDao.fetch(id);
		User user = new User();
		user.setId(u.id);
		user.setPid(u.pid);
		user.setCompanyId(u.companyId);
		user.setKind(u.kind);
		user.setAccount(u.account);
		user.setName(u.name);
		user.setEmail(u.email);
		user.setPhone(u.phone);
		user.setContact(u.contact);
		user.setCreateTime(u.createTime);
		user.setEnable(u.enable);
		user.setEditTime(u.editTime);
		user.setServiceStartDate(u.serviceStartDate);
		user.setServiceEndDate(u.serviceEndDate);
		user.setRemark(u.remark);

		return Tuple.of(device, user);
	}

	@ServiceMethod(id = "baseinfo.device.delete", pid = "baseinfo.device", name = "删除终端资料")
	@Transactional
	public void delete(String id) {
		String plateNumber = deviceDao.assignedVehicle(id);
		if (plateNumber != null && !plateNumber.isEmpty())
			throw new RuntimeException("此终端已绑定车辆：" + plateNumber + ",不能被删除！");
		String deviceNumber = deviceDao.getDeviceNumber(id);
		deviceDao.delete(id);
		userDao.deleteUser(id);

		if (deviceNumber != null)
			godpDao.syncDeviceInUser(deviceNumber, false);
	}

	public boolean exist(String deviceNumber) {
		return deviceDao.exist(deviceNumber);
	}

	public boolean exist(String number, String id) {
		return deviceDao.existOutId(number, id);
	}

	public Page<DeviceSearchInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = deviceDao.searchPageCount(companyId, filter);
		Page<DeviceSearchInfo> page = new Page<DeviceSearchInfo>();
		page.total = total;
		if (total > 0) {
			List<DeviceSearchInfoDto> rows = deviceDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (DeviceSearchInfoDto dto : rows) {
				DeviceSearchInfo info = new DeviceSearchInfo();
				info.setId(dto.id);
				info.setDeviceNumber(dto.deviceNumber);
				info.setPhoneNumber(dto.phoneNumber);
				info.setFactoryName(dto.factoryName);
				info.setModel(dto.model);
				info.setFactoryNumber(dto.factoryNumber);
				page.rows.add(info);
			}
		}
		return page;
	}

	public Object free(String companyId, String deviceNumber, int pageIndex, int pageSize) {
		int total = deviceDao.freePageCount(companyId, deviceNumber);
		Page<DeviceSearchInfo> page = new Page<DeviceSearchInfo>();
		page.total = total;
		if (total > 0) {
			List<DeviceSearchInfoDto> rows = deviceDao.freePageDetail(companyId, deviceNumber, (pageIndex - 1) * pageSize, pageSize);
			for (DeviceSearchInfoDto dto : rows) {
				DeviceSearchInfo info = new DeviceSearchInfo();
				info.setId(dto.id);
				info.setDeviceNumber(dto.deviceNumber);
				info.setPhoneNumber(dto.phoneNumber);
				info.setFactoryName(dto.factoryName);
				info.setModel(dto.model);
				info.setFactoryNumber(dto.factoryNumber);
				page.rows.add(info);
			}
		}
		return page;
	}
}
