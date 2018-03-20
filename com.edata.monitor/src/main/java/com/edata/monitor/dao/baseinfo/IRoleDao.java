package com.edata.monitor.dao.baseinfo;

import java.util.List;

import com.edata.monitor.util.KeyValue;

/**
 * 角色数据访问接口
 * 
 * @author yangzs
 *
 */
public interface IRoleDao {
	/**
	 * 获取企业角色列表
	 * 
	 * @param companyId
	 *            企业id
	 */
	List<RoleDto> list(String companyId);

	/**
	 * 创建新的角色
	 */
	void create(RoleDto dto);

	/**
	 * 读取角色
	 */
	RoleDto fetch(String id);

	/**
	 * 更新角色
	 */
	int update(RoleDto dto);

	/**
	 * 删除角色
	 */
	void deleteRole(String id);

	void deleteRoleAuthorize(String id);

	/**
	 * 是否已存在角色
	 */
	boolean exist(String name, String companyId);

	/**
	 * 是否已存在角色
	 */
	boolean existOutId(String name, String companyId, String id);

	/**
	 * 获取角色所拥有的权限
	 */
	List<String> authorizes(String roleId);

	/**
	 * 角色授权
	 */
	void authorize(List<KeyValue> rows);

	/**
	 * 清理企业所有授权中，企业已解除的授权项，防止与企业授权不一致
	 */
	void clreanupAuthorize(String companyId);

}
