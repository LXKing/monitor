package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.godp.domain.role.RoleInfo;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.IUserDao;
import com.edata.monitor.dao.baseinfo.MonitorInfoDto;
import com.edata.monitor.dao.baseinfo.RoleDto;
import com.edata.monitor.dao.baseinfo.UserDto;
import com.edata.monitor.dao.baseinfo.UserInfoDto;
import com.edata.monitor.domain.baseinfo.MonitorInfo;
import com.edata.monitor.domain.baseinfo.User;
import com.edata.monitor.domain.baseinfo.UserInfo;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.kind.UserKinds;
import com.edata.monitor.util.kind.UserOptions;

/**
 * 用户服务类
 * 
 * @author 生
 *
 */
@Service
public class UserService {
	@Autowired
	private IUserDao userDao;

	public Page<UserInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		Page<UserInfo> page = new Page<UserInfo>();
		int total = userDao.queryPageCount(companyId, filter);
		if (total > 0) {
			page.total = total;
			List<UserInfoDto> users = userDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (UserInfoDto dto : users) {
				UserInfo info = new UserInfo();
				info.setServiceStartDate(dto.serviceStartDate);
				info.setServiceEndDate(dto.serviceEndDate);
				info.setEnable(dto.enable);
				info.setId(dto.id);
				info.setName(dto.name);
				info.setPhone(dto.phone);
				info.setRemark(dto.remark);
				info.setCreateTime(dto.createTime);
				page.rows.add(info);
			}
		}

		return page;
	}

	@ServiceMethod(id = "baseinfo.user.create", pid = "baseinfo.user", name = "创建新的用户")
	@Transactional
	public void create(User user) {
		user.setId(ObjectId.next());
		user.setKind(UserKinds.CompanyUser.getIndex());

		UserDto dto = new UserDto();
		dto.id = user.getId();
		dto.kind = user.getKind();
		dto.companyId = user.getCompanyId();
		dto.account = user.getAccount();
		dto.password = "888888";
		dto.contact = user.getContact();
		dto.email = user.getEmail();
		dto.enable = user.isEnable();
		dto.name = user.getName();
		dto.phone = user.getPhone();
		dto.pid = user.getPid();
		dto.remark = user.getRemark();
		dto.serviceEndDate = user.getServiceEndDate();
		dto.serviceStartDate = user.getServiceStartDate();

		userDao.create(dto);
	}

	@ServiceMethod(id = "baseinfo.user.update", pid = "baseinfo.user", name = "修改用户资料")
	@Transactional
	public void update(User user) {
		UserDto dto = new UserDto();
		dto.id = user.getId();
		dto.kind = user.getKind();
		dto.companyId = user.getCompanyId();
		dto.account = user.getAccount();
		dto.contact = user.getContact();
		dto.email = user.getEmail();
		dto.enable = user.isEnable();
		dto.name = user.getName();
		dto.phone = user.getPhone();
		dto.pid = user.getPid();
		dto.remark = user.getRemark();
		dto.serviceEndDate = user.getServiceEndDate();
		dto.serviceStartDate = user.getServiceStartDate();
		dto.editTime = user.getEditTime();

		int rows = userDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	@ServiceMethod(id = "baseinfo.user.delete", pid = "baseinfo.user", name = "删除用户资料")
	@Transactional
	public void delete(String id) {
		userDao.deleteUser(id);
		userDao.deleteInfo(id);
		userDao.deleteMaplayer(id);
		userDao.deleteTextmessage(id);
	}

	public User fetch(String id) {
		UserDto dto = userDao.fetch(id);
		User user = new User();
		user.setKind(dto.kind);
		user.setAccount(dto.account);
		user.setContact(dto.contact);
		user.setCreateTime(dto.createTime);
		user.setEditTime(dto.editTime);
		user.setEmail(dto.email);
		user.setEnable(dto.enable);
		user.setId(dto.id);
		user.setName(dto.name);
		user.setPhone(dto.phone);
		user.setCompanyId(dto.companyId);
		user.setRemark(dto.remark);
		user.setServiceEndDate(dto.serviceEndDate);
		user.setServiceStartDate(dto.serviceStartDate);

		return user;
	}

	public boolean exist(String account) {
		return userDao.exist(account);
	}

	public boolean exist(String account, String id) {
		return userDao.existOutId(account, id);
	}

	public Page<MonitorInfo> getTargets(String companyId, String filter, int pageIndex, int pageSize) {
		int total = userDao.targetsPageCount(companyId, filter);
		List<MonitorInfoDto> rows = userDao.targetsPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
		Page<MonitorInfo> monitors = new Page<MonitorInfo>();
		monitors.total = total;
		for (MonitorInfoDto dto : rows) {
			MonitorInfo info = new MonitorInfo();
			info.setId(dto.id);
			info.setName(dto.name);
			info.setType(dto.type);
			info.setRemark(dto.remark);

			monitors.rows.add(info);
		}

		return monitors;
	}

	public List<MonitorInfo> assignedMonitors(String userId, String companyId) {
		List<MonitorInfoDto> list = userDao.assignedMonitors(userId, companyId);
		List<MonitorInfo> monitors = new ArrayList<MonitorInfo>();
		for (MonitorInfoDto dto : list) {
			MonitorInfo info = new MonitorInfo();
			info.setId(dto.id);
			info.setName(dto.name);
			info.setType(dto.type);
			info.setRemark(dto.remark);

			monitors.add(info);
		}

		return monitors;
	}

	@ServiceMethod(id = "baseinfo.user.addMonitors", pid = "baseinfo.user", name = "用户分配监控对象")
	@Transactional
	public void addMonitors(String userId, List<String> monitors) {
		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (String id : monitors) {
			KeyValue row = new KeyValue();
			row.setKey(userId);
			row.setValue(id);

			rows.add(row);
		}
		userDao.addMonitors(rows);
	}

	@ServiceMethod(id = "baseinfo.user.removeMonitor", pid = "baseinfo.user", name = "用户解除监控对象")
	@Transactional
	public void removeMonitor(String userId, String targetId) {
		userDao.removeMonitor(userId, targetId);
	}

	public List<RoleInfo> assignedRoles(String userId) {
		List<RoleDto> list = userDao.assignedRoles(userId);
		List<RoleInfo> monitors = new ArrayList<RoleInfo>();
		for (RoleDto dto : list) {
			RoleInfo info = new RoleInfo();
			info.setId(dto.id);
			info.setName(dto.name);
			info.setRemark(dto.remark);

			monitors.add(info);
		}

		return monitors;
	}

	@ServiceMethod(id = "baseinfo.user.addRoles", pid = "baseinfo.user", name = "用户绑定角色")
	@Transactional
	public void addRoles(String userId, List<String> roles) {
		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (String id : roles) {
			KeyValue row = new KeyValue();
			row.setKey(userId);
			row.setValue(id);

			rows.add(row);
		}
		userDao.addRoles(rows);
	}

	@ServiceMethod(id = "baseinfo.user.removeRole", pid = "baseinfo.user", name = "用户解除角色")
	@Transactional
	public void removeRole(String userId, String roleId) {
		userDao.removeRole(userId, roleId);
	}

	@SuppressWarnings("unchecked")
	public Map<Object, Object> getUserOptions(String userId, UserOptions type) {
		Map<Object, Object> values = new Hashtable<Object, Object>();
		String json = userDao.getUserOptions(userId, type.getIndex());
		values = JsonMapper.toObject(json, values.getClass());
		return values;
	}

	public void saveUserOptions(String userId, UserOptions type, Object values) {
		String json = JsonMapper.toJson(values);
		userDao.saveUserOptions(userId, type.getIndex(), json);
	}
}
