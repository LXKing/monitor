package com.rayton.gps.dao.security;


import com.rayton.gps.dao.permission.PermissionDto;

import java.util.List;

/**
 * 安全管理接口
 *
 * @author 生
 */
public interface ISecurityDao {
    /**
     * 用户登录
     *
     * @param account  帐号
     * @param password 密码
     * @return 用户身份信息
     */
    IdentityInfoDto login(String account, String password);

    /**
     * 获取用户角色
     */
    List<String> getRoleInUser(String id);

    /**
     * 获取权限列表
     */
    List<PermissionDto> loadPermissions();

    /**
     * 获取权限所授权的角色列表
     */
    List<String> loadRoleInPermission(String permission);

    /**
     * 读取个人信息
     */
    MyinfoDto getMyInfo(String id);

    /**
     * 保存个人信息
     */
    int saveMyInfo(MyinfoDto dto);

    /**
     * 保存新密码
     */
    void saveMyKey(String id, String newKey);
}
