package com.edata.godp.dao.role;

import java.util.List;

/**
 * 角色数据访问接口
 *
 * @author 生
 */
public interface IRoleDao {
    /**
     * 创建角色
     */
    void create(RoleDto dto);

    /**
     * 更新角色
     */
    int update(RoleDto dto);

    /**
     * 读取角色
     */
    RoleDto fetch(String id);

    /**
     * 删除角色所有已授予权限
     */
    void deleteAuthorize(String roleId);

    /**
     * 删除角色
     */
    void delete(String id);

    /**
     * 读取角色列表
     */
    List<RoleDto> list();

    /**
     * 是否存在角色
     */
    boolean exsits(String name);

    /**
     * 是否存在角色
     */
    boolean exsitsOutId(String name, String id);

    /**
     * 分配权限
     */
    void assignPermission(String roleId, String permissionId);

    /**
     * 解除权限
     */
    void removePermission(String roleId, String permissionId);

    /**
     * 读取角色权限列表
     */
    List<String> getPermissions(String roleId);

}
