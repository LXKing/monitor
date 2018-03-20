package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.baseinfo.IMotorcadeDao;
import com.edata.monitor.dao.baseinfo.MotorcadeDto;
import com.edata.monitor.domain.baseinfo.Motorcade;
import com.edata.monitor.domain.baseinfo.MotorcadeInfo;

/**
 * 车队服务类
 * 
 * @author yangzs
 *
 */
@Service
public class MotorcadeService {
	@Autowired
	private IMotorcadeDao motorcadeDao;

	/**
	 * 读取企业所有车队
	 * 
	 * @param companyId
	 *            企业唯一编号
	 */
	public List<MotorcadeInfo> list(String companyId)  {
		List<MotorcadeDto> list = motorcadeDao.list(companyId);
		List<MotorcadeInfo> motorcades = new ArrayList<MotorcadeInfo>(list.size());
		for (MotorcadeDto dto : list) {
			MotorcadeInfo info = new MotorcadeInfo();
			info.setId(dto.id);
			info.setType(dto.type);
			info.setName(dto.name);
			info.setParentVisible(dto.parentVisible);
			info.setRemark(dto.remark);

			motorcades.add(info);
		}
		return motorcades;
	}

	/**
	 * 创建新的车队
	 */
	@ServiceMethod(id = "baseinfo.motorcade.create", pid = "baseinfo.motorcade", name = "创建新的车队")
	@Transactional
	public void create(Motorcade motorcade)  {
		motorcade.setId(ObjectId.next());
		MotorcadeDto dto = new MotorcadeDto();
		dto.id = motorcade.getId();
		dto.companyId = motorcade.getCompanyId();
		dto.type = motorcade.getType();
		dto.name = motorcade.getName();
		dto.linkMan = motorcade.getLinkMan();
		dto.phone = motorcade.getPhone();
		dto.parentVisible = motorcade.isParentVisible();
		dto.remark = motorcade.getRemark();

		motorcadeDao.create(dto);
	}

	/**
	 * 读取车队资料
	 * 
	 * @param id
	 *            车队唯一编号
	 */
	public Motorcade fetch(String id)  {
		MotorcadeDto dto = motorcadeDao.fetch(id);
		Motorcade motorcade = new Motorcade();
		motorcade.setId(dto.id);
		motorcade.setCompanyId(dto.companyId);
		motorcade.setType(dto.type);
		motorcade.setName(dto.name);
		motorcade.setLinkMan(dto.linkMan);
		motorcade.setPhone(dto.phone);
		motorcade.setParentVisible(dto.parentVisible);
		motorcade.setRemark(dto.remark);
		motorcade.setEditTime(dto.editTime);

		return motorcade;
	}

	/**
	 * 更新车队资料
	 */
	@ServiceMethod(id = "baseinfo.motorcade.update", pid = "baseinfo.motorcade", name = "更新车队资料")
	@Transactional
	public void update(Motorcade motorcade)  {
		MotorcadeDto dto = new MotorcadeDto();
		dto.id = motorcade.getId();
		dto.companyId = motorcade.getCompanyId();
		dto.type = motorcade.getType();
		dto.name = motorcade.getName();
		dto.linkMan = motorcade.getLinkMan();
		dto.phone = motorcade.getPhone();
		dto.parentVisible = motorcade.isParentVisible();
		dto.remark = motorcade.getRemark();
		dto.editTime = motorcade.getEditTime();

		int rows = motorcadeDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	/**
	 * 删除车队
	 * 
	 * @param id
	 *            车队唯一编号
	 */
	@ServiceMethod(id = "baseinfo.motorcade.delete", pid = "baseinfo.motorcade", name = "删除车队资料")
	@Transactional
	public void delete(String id)  {
		if (motorcadeDao.hasVehicle(id))
			throw new RuntimeException("该车队已存放车辆资料，不能被删除！");
		motorcadeDao.delete(id);
	}

	/**
	 * 是否已存在企业车队
	 * 
	 * @param name
	 *            车队名称
	 * @param companyId
	 *            企业唯一编号
	 */
	public boolean exist(String name, String companyId)  {
		return motorcadeDao.exist(name, companyId);
	}

	/**
	 * 是否已存在企业车队
	 * 
	 * @param name
	 *            车队名称
	 * @param companyId
	 *            企业唯一编号
	 * @param id
	 *            车队唯一编号
	 */
	public boolean exist(String name, String companyId, String id)  {
		return motorcadeDao.existOutId(name, companyId, id);
	}
}
