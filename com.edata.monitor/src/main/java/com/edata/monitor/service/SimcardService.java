package com.edata.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.ISimcardDao;
import com.edata.monitor.dao.baseinfo.SimcardDto;
import com.edata.monitor.dao.baseinfo.SimcardSearchInfoDto;
import com.edata.monitor.domain.baseinfo.Simcard;
import com.edata.monitor.domain.baseinfo.SimcardInfo;
import com.edata.monitor.domain.baseinfo.SimcardSearchInfo;

/**
 * sim卡服务类
 * 
 * @author yangzs
 *
 */
@Service
public class SimcardService {
	@Autowired
	private ISimcardDao simcardDao;

	public Page<SimcardInfo> query(String companyId, String filter, int pageIndex, int pageSize) throws Exception {
		int total = simcardDao.queryPageCount(companyId, filter);
		Page<SimcardInfo> query = new Page<SimcardInfo>();
		query.total = total;
		if (total > 0) {
			List<SimcardDto> rows = simcardDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (SimcardDto dto : rows) {
				SimcardInfo info = new SimcardInfo();
				info.setCarrierOperator(dto.carrierOperator);
				info.setCreateTime(dto.createTime);
				info.setExpireDate(dto.expireDate);
				info.setId(dto.id);
				info.setOpenSMS(dto.openSMS);
				info.setPrepayment(dto.prepayment);
				info.setPurchaseDate(dto.purchaseDate);
				info.setPhoneNumber(dto.phoneNumber);
				info.setSimcardNumber(dto.simcardNumber);
				info.setSpeechType(dto.speechType);
				info.setRemark(dto.remark);

				query.rows.add(info);
			}
		}
		return query;
	}

	@ServiceMethod(id = "baseinfo.simcard.create", pid = "baseinfo.simcard", name = "创建新的sim卡")
	@Transactional
	public void create(Simcard simcard) throws Exception {
		simcard.setId(ObjectId.next());

		SimcardDto dto = new SimcardDto();
		dto.carrierOperator = simcard.getCarrierOperator();
		dto.companyId = simcard.getCompanyId();
		dto.expireDate = simcard.getExpireDate();
		dto.flowset = simcard.getFlowset();
		dto.id = simcard.getId();
		dto.openDate = simcard.getOpenDate();
		dto.openSMS = simcard.isOpenSMS();
		dto.prepayment = simcard.getPrepayment();
		dto.purchaseDate = simcard.getPurchaseDate();
		dto.remark = simcard.getRemark();
		dto.phoneNumber = simcard.getPhoneNumber();
		dto.simcardNumber = simcard.getSimcardNumber();
		dto.speechType = simcard.getSpeechType();

		simcardDao.create(dto);

	}

	public Simcard fetch(String id) throws Exception {
		SimcardDto dto = simcardDao.fetch(id);
		Simcard simcard = new Simcard();
		simcard.setCarrierOperator(dto.carrierOperator);
		simcard.setCompanyId(dto.companyId);
		simcard.setEditTime(dto.editTime);
		simcard.setExpireDate(dto.expireDate);
		simcard.setFlowset(dto.flowset);
		simcard.setId(dto.id);
		simcard.setOpenDate(dto.openDate);
		simcard.setOpenSMS(dto.openSMS);
		simcard.setPrepayment(dto.prepayment);
		simcard.setPurchaseDate(dto.purchaseDate);
		simcard.setRemark(dto.remark);
		simcard.setPhoneNumber(dto.phoneNumber);
		simcard.setSimcardNumber(dto.simcardNumber);
		simcard.setSpeechType(dto.speechType);

		return simcard;
	}

	@ServiceMethod(id = "baseinfo.simcard.update", pid = "baseinfo.simcard", name = "修改sim卡")
	@Transactional
	public void update(Simcard simcard) throws Exception {
		SimcardDto dto = new SimcardDto();
		dto.carrierOperator = simcard.getCarrierOperator();
		dto.companyId = simcard.getCompanyId();
		dto.expireDate = simcard.getExpireDate();
		dto.flowset = simcard.getFlowset();
		dto.id = simcard.getId();
		dto.openDate = simcard.getOpenDate();
		dto.openSMS = simcard.isOpenSMS();
		dto.prepayment = simcard.getPrepayment();
		dto.purchaseDate = simcard.getPurchaseDate();
		dto.remark = simcard.getRemark();
		dto.phoneNumber = simcard.getPhoneNumber();
		dto.simcardNumber = simcard.getSimcardNumber();
		dto.speechType = simcard.getSpeechType();
		dto.editTime = simcard.getEditTime();

		int rows = simcardDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	@ServiceMethod(id = "baseinfo.simcard.delete", pid = "baseinfo.simcard", name = "删除sim卡")
	@Transactional
	public void delete(String id) throws Exception {
		String deviceNumber = simcardDao.assignedDevice(id);
		if (deviceNumber != null && !deviceNumber.isEmpty())
			throw new RuntimeException("此卡已绑定终端：" + deviceNumber + ",不能被删除！");
		simcardDao.delete(id);
	}

	public boolean exist(String phoneNumber) throws Exception {
		return simcardDao.exist(phoneNumber);
	}

	public boolean exist(String phoneNumber, String id) throws Exception {
		return simcardDao.existOutId(phoneNumber, id);
	}

	public Page<SimcardSearchInfo> search(String companyId, String filter, int pageIndex, int pageSize) throws Exception {
		int total = simcardDao.searchPageCount(companyId, filter);
		Page<SimcardSearchInfo> page = new Page<SimcardSearchInfo>();
		page.total = total;
		if (total > 0) {
			List<SimcardSearchInfoDto> rows = simcardDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (SimcardSearchInfoDto dto : rows) {
				SimcardSearchInfo info = new SimcardSearchInfo();
				info.setCarrierOperator(dto.carrierOperator);
				info.setId(dto.id);
				info.setOpenSMS(dto.openSMS);
				info.setPhoneNumber(dto.phoneNumber);
				info.setSpeechType(dto.speechType);
				info.setRemark(dto.remark);
				page.rows.add(info);
			}
		}
		return page;
	}

	public Page<SimcardSearchInfo> free(String companyId, String phoneNumber, int pageIndex, int pageSize) throws Exception {
		int total = simcardDao.freePageCount(companyId, phoneNumber);
		Page<SimcardSearchInfo> page = new Page<SimcardSearchInfo>();
		page.total = total;
		if (total > 0) {
			List<SimcardSearchInfoDto> rows = simcardDao.freePageDetail(companyId, phoneNumber, (pageIndex - 1) * pageSize, pageSize);
			for (SimcardSearchInfoDto dto : rows) {
				SimcardSearchInfo info = new SimcardSearchInfo();
				info.setCarrierOperator(dto.carrierOperator);
				info.setId(dto.id);
				info.setOpenSMS(dto.openSMS);
				info.setPhoneNumber(dto.phoneNumber);
				info.setSpeechType(dto.speechType);
				info.setRemark(dto.remark);
				page.rows.add(info);
			}
		}
		return page;
	}

}
