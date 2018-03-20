package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AssociationCache;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.common.DataLogDto;
import com.edata.monitor.dao.common.DeviceStatusDto;
import com.edata.monitor.domain.cache.UserMonitorTarget;
import com.edata.monitor.domain.common.DataLog;
import com.edata.monitor.domain.common.DeviceStatus;
import com.edata.monitor.godp.IGodpDao;

@Service
public class CommonService {
	@Autowired
	private IGodpDao godpDao;
	
	public DeviceStatus getDeviceStatus(String number) throws Exception {
		DeviceStatusDto dto = godpDao.getDeviceStatus(number);
		DeviceStatus status = new DeviceStatus();
		status.setCurVer(dto.curVer);
		status.setDebugging(dto.debugging);
		status.setMatching(dto.matching);
		status.setMatchResult(dto.matchResult);
		status.setMatchTime(dto.matchTime);
		status.setNumber(dto.number);
		status.setPreVer(dto.preVer);
		status.setRepairing(dto.repairing);
		status.setSleeping(dto.sleeping);
		status.setUpgradeEnd(dto.upgradeEnd);
		status.setUpgradeStart(dto.upgradeStart);
		status.setUpgrading(dto.upgrading);

		return status;

	}

	public Object loadDataLog(String number, Date start, Date end, int pageIndex, int pageSize) throws Exception {
		Page<DataLog> page = new Page<DataLog>();
		page.rows = new ArrayList<DataLog>();
		if (number == null || number.length() <= 0)
			return page;

		Page<DataLogDto> logs = godpDao.loadDataLog(number, start, end, pageIndex, pageSize);
		if (logs.rows == null || logs.rows.size() <= 0)
			return page;

		for (DataLogDto dto : logs.rows) {
			DataLog log = new DataLog();
			log.setTime(dto.time);
			log.setRaw(dto.raw);

			page.rows.add(log);
		}

		page.total = logs.total;
		return page;
	}

	@ServiceMethod(id = "center.locate.datalog", pid = "center.locate", name = "数据调试")
	public Object setDataLog(String number, boolean enable) throws Exception {
		return godpDao.setDataLog(number, enable);
	}

	public Object getDeviceVehicle(String userId, String number) {
		UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
		if (target == null)
			return null;

		return target.getDevices().get(number);
	}
}
