package com.edata.monitor.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.IMaintainDao;
import com.edata.monitor.dao.baseinfo.MaintainDto;
import com.edata.monitor.dao.baseinfo.MaintainInfoDto;
import com.edata.monitor.domain.baseinfo.Maintain;
import com.edata.monitor.domain.baseinfo.MaintainInfo;

@Service
public class MaintainService {
	@Autowired
	private IMaintainDao maintainDao;

	public Page<MaintainInfo> query(String companyId, String plateNumber, Date from, Date to, int pageIndex, int pageSize) {
		int total = maintainDao.queryPageCount(companyId, plateNumber, from, to);

		Page<MaintainInfo> query = new Page<MaintainInfo>();
		query.total = total;
		if (total > 0) {
			List<MaintainInfoDto> rows = maintainDao.queryPageDetail(companyId, plateNumber, from, to, (pageIndex - 1) * pageSize, pageSize);
			for (MaintainInfoDto dto : rows) {
				MaintainInfo info = new MaintainInfo();
				info.setContent(dto.content);
				info.setDoneDate(dto.doneDate);
				info.setFee(dto.fee);
				info.setId(dto.id);
				info.setMileage(dto.mileage);
				info.setNextDate(dto.nextDate);
				info.setNextMileage(dto.nextMileage);
				info.setPlateNumber(dto.plateNumber);
				info.setUserName(dto.userName);

				query.rows.add(info);
			}
		}
		return query;
	}

	@ServiceMethod(id = "baseinfo.maintain.create", pid = "baseinfo.maintain", name = "创建新的车辆保养记录")
	@Transactional
	public void Create(Maintain maintain) {
		maintain.setId(ObjectId.next());
		MaintainDto dto = new MaintainDto();
		dto.id = maintain.getId();
		dto.agent = maintain.getAgent();
		dto.companyId = maintain.getCompanyId();
		dto.content = maintain.getContent();
		dto.doneDate = maintain.getDoneDate();
		dto.fee = maintain.getFee();
		dto.garage = maintain.getGarage();
		dto.mileage = maintain.getMileage();
		dto.nextDate = maintain.getNextDate();
		dto.nextMileage = maintain.getNextMileage();
		dto.type = maintain.getType();
		dto.userId = maintain.getUserId();
		dto.userName = maintain.getUserName();
		dto.vehicleId = maintain.getVehicleId();

		maintainDao.create(dto);
		maintainDao.updateNextMaintainDate(dto.vehicleId, dto.nextDate);
	}

	public Maintain fetch(String id) {
		MaintainDto dto = maintainDao.fetch(id);
		Maintain maintain = new Maintain();
		maintain.setId(dto.id);
		maintain.setAgent(dto.agent);
		maintain.setCompanyId(dto.companyId);
		maintain.setContent(dto.content);
		maintain.setDoneDate(dto.doneDate);
		maintain.setEditTime(dto.editTime);
		maintain.setFee(dto.fee);
		maintain.setGarage(dto.garage);
		maintain.setMileage(dto.mileage);
		maintain.setNextDate(dto.nextDate);
		maintain.setNextMileage(dto.nextMileage);
		maintain.setType(dto.type);
		maintain.setUserId(dto.userId);
		maintain.setUserName(dto.userName);
		maintain.setVehicleId(dto.vehicleId);
		maintain.setPlateNumber(dto.plateNumber);
		return maintain;
	}

	@ServiceMethod(id = "baseinfo.maintain.update", pid = "baseinfo.maintain", name = "修改车辆保养记录")
	@Transactional
	public void update(Maintain maintain) {
		MaintainDto dto = new MaintainDto();
		dto.id = maintain.getId();
		dto.agent = maintain.getAgent();
		dto.companyId = maintain.getCompanyId();
		dto.content = maintain.getContent();
		dto.doneDate = maintain.getDoneDate();
		dto.fee = maintain.getFee();
		dto.garage = maintain.getGarage();
		dto.mileage = maintain.getMileage();
		dto.nextDate = maintain.getNextDate();
		dto.nextMileage = maintain.getNextMileage();
		dto.type = maintain.getType();
		dto.userId = maintain.getUserId();
		dto.userName = maintain.getUserName();
		dto.vehicleId = maintain.getVehicleId();
		dto.editTime = maintain.getEditTime();

		int rows = maintainDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);

		maintainDao.updateNextMaintainDate(dto.vehicleId, dto.nextDate);
	}

	@ServiceMethod(id = "baseinfo.maintain.delete", pid = "baseinfo.maintain", name = "删除车辆保养记录")
	@Transactional
	public void delete(String id) {
		maintainDao.delete(id);
	}

}
