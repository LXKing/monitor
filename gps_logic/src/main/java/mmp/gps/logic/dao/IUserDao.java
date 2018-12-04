package mmp.gps.logic.dao;

import mmp.gps.domain.user.UserDto;
import mmp.gps.domain.user.UserPushUrlDto;

import java.util.List;

/**
 * 用户信息数据访问接口
 */
public interface IUserDao {
    /**
     * 创建新用户信息
     *
     * @
     */
    void create(UserDto dto);

    /**
     * 更新用户信息
     */
    int update(UserDto dto);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     */
    UserDto fetch(String id);

    /**
     * 删除用户与设备关系
     */
    void deleteDeviceInUser(String userId);

    /**
     * 删除用户与角色关系
     */
    void deleteRoleInUser(String userId);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    void delete(String id);

    /**
     * 是否已存在用户
     *
     * @param account 帐号
     */
    boolean exist(String account);

    /**
     * 是否已存在用户
     *
     * @param account 帐号
     * @param id      用户id
     */
    boolean existOutId(String account, String id);

    /**
     * 查询用户页总行数
     */
    int queryPageCount(String nameFilter);

    /**
     * 查询用户页内容
     *
     * @param nameFilter 名称过滤器
     * @param pageIndex  页序号
     * @param pageSize   页大小
     */
    List<UserDto> queryPageDetail(String nameFilter, int pageIndex, int pageSize);

    /**
     * 分配角色
     */
    void assignRole(String userId, String roleId);

    /**
     * 解除角色
     */
    void removeRole(String userId, String roleId);

    /**
     * 读取用户角色列表
     */
    List<String> getRoles(String userId);

    /**
     * 获取数据推送Url列表，Key:用户id,Value:url
     */
    List<UserPushUrlDto> getPushUrls();
}
