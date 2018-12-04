package mmp.gps.monitor.service;

import mmp.gps.common.util.Errors;
import mmp.gps.common.util.KeyValue;
import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.role.Role;
import mmp.gps.domain.role.RoleInfo;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.configuration.AppConfig;
import mmp.gps.monitor.dao.IRoleDao;
import mmp.gps.monitor.service.shiro.ShiroRealm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色服务类
 */
@Service
public class RoleService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private ShiroRealm shiroRealm;

    /**
     * 读取企业所有角色
     *
     * @param companyId 企业唯一编号
     */
    public List<RoleInfo> list(String companyId) throws Exception {
        List<Role> list = roleDao.list(companyId);
        List<RoleInfo> roles = new ArrayList<RoleInfo>(list.size());

        list.forEach(role -> {
            RoleInfo info = new RoleInfo();
            BeanUtils.copyProperties(role, info);
            roles.add(info);
        });
        // for (RoleDto dto : list) {
        //     RoleInfo info = new RoleInfo();
        //     info.setId(dto.id);
        //     info.setName(dto.name);
        //     info.setRemark(dto.remark);
        //
        //     roles.add(info);
        // }
        return roles;
    }

    /**
     * 创建新的角色
     */
    @RequiresPermissions("baseinfo.role.create")
    @Log(name = "创建新的角色")
    @Transactional
    public void create(Role role) throws Exception {
        role.setId(ObjectId.next());
        // RoleDto dto = new RoleDto();
        // dto.id = role.getId();
        // dto.companyId = role.getCompanyId();
        // dto.name = role.getName();
        // dto.remark = role.getRemark();

        roleDao.create(role);
    }

    /**
     * 读取角色资料
     *
     * @param id 角色唯一编号
     */
    public Role fetch(String id) throws Exception {
        Role role = roleDao.fetch(id);
        // Role role = new Role();
        // role.setId(dto.id);
        // role.setCompanyId(dto.companyId);
        // role.setName(dto.name);
        // role.setRemark(dto.remark);
        // role.setEditTime(dto.editTime);

        return role;
    }

    /**
     * 更新角色资料
     */
    @RequiresPermissions("baseinfo.role.update")
    @Log(name = "修改角色资料")
    @Transactional
    public void update(Role role) throws Exception {
        // RoleDto dto = new RoleDto();
        // dto.id = role.getId();
        // dto.companyId = role.getCompanyId();
        // dto.name = role.getName();
        // dto.remark = role.getRemark();
        // dto.editTime = role.getEditTime();

        int rows = roleDao.update(role);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    /**
     * 删除角色
     *
     * @param id 角色唯一编号
     */
    @RequiresPermissions("baseinfo.role.delete")
    @Log(name = "删除角色资料")
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
     * @param roleId 角色唯一编号
     * @param list   授权列表
     */
    @RequiresPermissions("baseinfo.role.authorize")
    @Log(name = "角色授权")
    @Transactional
    public void authorize(String companyId, String roleId, List<String> list) throws Exception {
        boolean cleanup = !appConfig.getTopCompanyId().equals(companyId);
        // 删除该角色所有授权项
        roleDao.deleteRoleAuthorize(roleId);
        // 保存新的授权项
        List<KeyValue> rows = new ArrayList<>();

        if (list != null) {
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
        }

        // 清理企业所有授权中，企业已解除的授权项，防止与企业授权不一致
        if (cleanup)
            roleDao.clreanupAuthorize(companyId);
        shiroRealm.clearCached();
        // AuthorizeCache.reload();
    }

    /**
     * 是否已存在企业角色
     *
     * @param name      角色名称
     * @param companyId 企业唯一编号
     */
    public boolean exist(String name, String companyId) throws Exception {
        return roleDao.exist(name, companyId);
    }

    /**
     * 是否已存在企业角色
     *
     * @param name      角色名称
     * @param companyId 企业唯一编号
     * @param id        角色唯一编号
     */
    public boolean exist(String name, String companyId, String id) throws Exception {
        return roleDao.existOutId(name, companyId, id);
    }
}