package com.edata.godp.dao.permission;

import java.util.List;

public interface IPermissionDao {
    /**
     * 查找系统管理员的记录号
     */
    String getAdminRoleId();

    /**
     * 系统管理员授权
     */
    void adminRoleAuthorize(String roleId, String permId);

    void create(PermissionDto dto);

    PermissionDto fetch(String id);

    void deleteAuthorize(String permId);

    void delete(String id);

    List<PermissionDto> list();

    boolean exsits(String name);

    boolean exsitsOutId(String name, String id);

}
