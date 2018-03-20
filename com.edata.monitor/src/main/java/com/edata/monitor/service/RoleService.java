package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AuthorizeCache;
import com.edata.monitor.dao.baseinfo.IRoleDao;
import com.edata.monitor.dao.baseinfo.RoleDto;
import com.edata.monitor.domain.AppConfig;
import com.edata.monitor.domain.baseinfo.Role;
import com.edata.monitor.domain.baseinfo.RoleInfo;
import com.edata.monitor.util.KeyValue;

/**
 * 角色服务类
 * 
 * @author yangzs
 *
 */
@Service
public class RoleService {
	@Autowired
	private AppConfig appConfig;
	@Autowired
	private IRoleDao roleDao;

	/**
	 * 读取企业所有角色
	 * 
	 * @param companyId
	 *            企业唯一编号
	 */
	public List<RoleInfo> list(String companyId) throws Exception {
		List<RoleDto> list = roleDao.list(companyId);
		List<RoleInfo> roles = new ArrayList<RoleInfo>(list.size());
		for (RoleDto dto : list) {
			RoleInfo info = new RoleInfo();
			info.setId(dto.id);
			info.setName(dto.name);
			info.setRemark(dto.remark);

			roles.add(info);
		}
		return roles;
	}

	/**
	 * 创建新的角色
	 */
	@ServiceMethod(id = "baseinfo.role.create", pid = "baseinfo.role", name = "创建新的角色")
	@Transactional
	public void create(Role role) throws Exception {
		role.setId(ObjectId.next());
		RoleDto dto = new RoleDto();
		dto.id = role.getId();
		dto.companyId = role.getCompanyId();
		dto.name = role.getName();
		dto.remark = role.getRemark();

		roleDao.create(dto);
	}

	/**
	 * 读取角色资料
	 * 
	 * @param id
	 *            角色唯一编号
	 */
	public Role fetch(String id) throws Exception {
		RoleDto dto = roleDao.fetch(id);
		Role role = new Role();
		role.setId(dto.id);
		role.setCompanyId(dto.companyId);
		role.setName(dto.name);
		role.setRemark(dto.remark);
		role.setEditTime(dto.editTime);

		return role;
	}

	/**
	 * 更新角色资料
	 */
	@ServiceMethod(id = "baseinfo.role.update", pid = "baseinfo.role", name = "修改角色资料")
	@Transactional
	public void update(Role role) throws Exception {
		RoleDto dto = new RoleDto();
		dto.id = role.getId();
		dto.companyId = role.getCompanyId();
		dto.name = role.getName();
		dto.remark = role.getRemark();
		dto.editTime = role.getEditTime();

		int rows = roleDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 *            角色唯一编号
	 */
	@ServiceMethod(id = "baseinfo.role.delete", pid = "baseinfo.role", name = "删除角色资料")
	@Transactional
	public void delete(String id) throws Exception {
		roleDao.deleteRole(id);
		roleDao.deleteRoleAuthorize(id);
	}

	/**
	 * 获取角色已授权项
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<String> authorizes(String id) throws Exception {
		return roleDao.authorizes(id);
	}

	/**
	 * 角色授权
	 * 
	 * @param roleId
	 *            角色唯一编号
	 * @param list
	 *            授权列表
	 */
	@ServiceMethod(id = "baseinfo.role.authorize", pid = "baseinfo.role", name = "角色授权")
	@Transactional
	public void authorize(String companyId, String roleId, List<String> list) throws Exception {
		boolean cleanup = !appConfig.getTopCompanyId().equals(companyId);
		// 删除该角色所有授权项
		roleDao.deleteRoleAuthorize(roleId);
		// 保存新的授权项
		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (String id : list) {
			KeyValue row = new KeyValue();
			row.setKey(companyId);
			KeyValue sub = new KeyValue();
			sub.setKey(roleId);
			sub.setValue(id);
			
			row.setValue(sub);
			rows.add(row);
		}
		roleDao.authorize(rows);
		// 清理企业所有授权中，企业已解除的授权项，防止与企业授权不一致
		if (cleanup)
			roleDao.clreanupAuthorize(companyId);
		AuthorizeCache.reload();
	}

	/**
	 * 是否已存在企业角色
	 * 
	 * @param name
	 *            角色名称
	 * @param companyId
	 *            企业唯一编号
	 */
	public boolean exist(String name, String companyId) throws Exception {
		return roleDao.exist(name, companyId);
	}

	/**
	 * 是否已存在企业角色
	 * 
	 * @param name
	 *            角色名称
	 * @param companyId
	 *            企业唯一编号
	 * @param id
	 *            角色唯一编号
	 */
	public boolean exist(String name, String companyId, String id) throws Exception {
		return roleDao.existOutId(name, companyId, id);
	}
}
