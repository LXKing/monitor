package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AuthorizeCache;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.CompanyDto;
import com.edata.monitor.dao.baseinfo.CompanyInfoDto;
import com.edata.monitor.dao.baseinfo.ICompanyDao;
import com.edata.monitor.dao.baseinfo.IUserDao;
import com.edata.monitor.dao.baseinfo.RoleDto;
import com.edata.monitor.dao.baseinfo.UserDto;
import com.edata.monitor.domain.baseinfo.Company;
import com.edata.monitor.domain.baseinfo.CompanyInfo;
import com.edata.monitor.domain.baseinfo.User;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.kind.UserKinds;

/**
 * 公司服务类
 * 
 * @author yangzs
 *
 */
@Service
public class CompanyService {
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IUserDao userDao;

	public Page<CompanyInfo> query(String pid, String filter, int pageIndex, int pageSize) throws Exception {
		int total = companyDao.queryPageCount(pid, filter);
		List<CompanyInfoDto> rows = companyDao.queryPageDetail(pid, filter, (pageIndex - 1) * pageSize, pageSize);
		Page<CompanyInfo> query = new Page<CompanyInfo>();
		query.total = total;
		for (CompanyInfoDto dto : rows) {
			CompanyInfo info = new CompanyInfo();
			info.setId(dto.id);
			info.setFullName(dto.fullName);
			info.setShortName(dto.shortName);
			info.setOfficeAddress(dto.officeAddress);
			info.setEnabled(dto.enable);
			info.setServiceEndDate(dto.serviceEndDate);
			info.setServiceStartDate(dto.serviceStartDate);
			info.setCreateTime(dto.createTime);
			info.setOndutyPhone(dto.ondutyPhone);
			info.setRemark(dto.remark);

			query.rows.add(info);
		}

		return query;
	}

	@ServiceMethod(id = "baseinfo.company.create", pid = "baseinfo.company", name = "创建企业资料")
	@Transactional
	public void create(Company company, User user) throws Exception {
		String id = ObjectId.next();
		company.setId(id);
		user.setId(id);

		CompanyDto c = new CompanyDto();
		c.id = company.getId();
		c.fullName = company.getFullName();
		c.officeAddress = company.getOfficeAddress();
		c.ondutyPhone = company.getOndutyPhone();
		c.organCode = company.getOrganCode();
		c.corporation = company.getCorporation();
		c.registAddress = company.getRegistAddress();
		c.registDate = company.getRegistDate();
		c.shortName = company.getShortName();
		c.parentVisible = company.isParentVisible();

		UserDto u = new UserDto();
		u.id = user.getId();
		u.pid = user.getPid();
		u.companyId = u.id;
		u.kind = UserKinds.CompanyAdmin.getIndex();
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

		companyDao.create(c);
		userDao.create(u);

		RoleDto role = new RoleDto();
		role.id = ObjectId.next();
		role.companyId = company.getId();
		role.name = "企业管理员";
		role.remark = "";

		companyDao.createAdminRole(role);
		companyDao.assignAdminRole(u.id, role.id);
	}

	public Tuple<Company, User> fetch(String id) throws Exception {
		CompanyDto c = companyDao.fetch(id);
		Company company = new Company();
		company.setId(c.id);
		company.setFullName(c.fullName);
		company.setShortName(c.shortName);
		company.setOrganCode(c.organCode);
		company.setRegistDate(c.registDate);
		company.setCorporation(c.corporation);
		company.setOndutyPhone(c.ondutyPhone);
		company.setOfficeAddress(c.officeAddress);
		company.setRegistAddress(c.registAddress);
		company.setParentVisible(c.parentVisible);
		company.setEditTime(c.editTime);

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

		return Tuple.of(company, user);

	}

	@ServiceMethod(id = "baseinfo.company.update", pid = "baseinfo.company", name = "修改企业资料")
	@Transactional
	public void update(Company company, User user) throws Exception {
		CompanyDto c = new CompanyDto();
		c.id = company.getId();
		c.fullName = company.getFullName();
		c.officeAddress = company.getOfficeAddress();
		c.ondutyPhone = company.getOndutyPhone();
		c.organCode = company.getOrganCode();
		c.corporation = company.getCorporation();
		c.registAddress = company.getRegistAddress();
		c.registDate = company.getRegistDate();
		c.shortName = company.getShortName();
		c.parentVisible = company.isParentVisible();
		c.editTime = company.getEditTime();

		UserDto u = new UserDto();
		u.id = user.getId();
		u.pid = user.getPid();
		u.companyId = u.id;
		u.kind = UserKinds.CompanyAdmin.getIndex();
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
		u.editTime = user.getEditTime();

		int rows = companyDao.update(c);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
		rows = userDao.update(u);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	@ServiceMethod(id = "baseinfo.company.delete", pid = "baseinfo.company", name = "删除企业资料")
	@Transactional
	public void delete(String id) throws Exception {
		companyDao.deleteCircle(id);
		companyDao.deleteDevice(id);
		companyDao.deleteDriver(id);
		companyDao.deleteLog(id);
		companyDao.deleteMaintain(id);
		companyDao.deleteMaplayer(id);
		companyDao.deleteMotorcade(id);
		companyDao.deleteOwner(id);
		companyDao.deletePoi(id);
		companyDao.deletePolygon(id);
		companyDao.deleteRectangle(id);
		companyDao.deleteRole(id);
		companyDao.deleteRoleinuser(id);
		companyDao.deleteRoleauthorize(id);
		companyDao.deleteRoute(id);
		companyDao.deleteSimcard(id);
		companyDao.deleteVehicle(id);
		companyDao.deleteCompany(id);
		companyDao.deleteCompanyauthorize(id);
		companyDao.deleteUser(id);
	}

	public List<String> authorizes(String companyId) throws Exception {
		return companyDao.authorizes(companyId);
	}

	@ServiceMethod(id = "baseinfo.company.authorize", pid = "baseinfo.company", name = "企业授权")
	@Transactional
	public void authorize(String companyId, List<String> list) throws Exception {
		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (String id : list) {
			KeyValue row = new KeyValue();
			row.setKey(companyId);
			row.setValue(id);

			rows.add(row);
		}
		companyDao.deleteCompanyauthorize(companyId);
		companyDao.deleteRoleauthorize(companyId);
		companyDao.deleteAdminAuthorize(companyId);
		companyDao.assignCompanyAuthorize(rows);
		companyDao.assingAdminAuthorize(rows);
		companyDao.cleanRoleauthorize(companyId);
		AuthorizeCache.reload();
	}
}
