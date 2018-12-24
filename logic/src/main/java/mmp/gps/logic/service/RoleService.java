package mmp.gps.logic.service;

import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.permission.PermissionDto;
import mmp.gps.domain.role.*;
import mmp.gps.logic.cache.Authorizes;
import mmp.gps.logic.dao.IPermissionDao;
import mmp.gps.logic.dao.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionDao permissionDao;


    @Transactional
    public void create(CreateRoleRequest request) {
        if (this.roleDao.exsits(request.getName())) {
            throw new RuntimeException("此角色已存在！");
        } else {
            RoleDto dto = new RoleDto();
            dto.id = ObjectId.next();
            dto.name = request.getName();
            dto.remark = request.getRemark();
            this.roleDao.create(dto);
        }
    }

    @Transactional
    public void update(EditRoleRequest request) {
        String adminRoleId = this.permissionDao.getAdminRoleId();
        if (adminRoleId.equals(request.getId())) {
            throw new RuntimeException("拒绝对管理员角色的操作");
        } else if (this.roleDao.exsitsOutId(request.getName(), request.getId())) {
            throw new RuntimeException("此角色已存在！");
        } else {
            RoleDto old = this.roleDao.fetch(request.getId());
            RoleDto dto = new RoleDto();
            dto.id = request.getId();
            dto.name = request.getName();
            dto.remark = request.getRemark();
            dto.editTime = request.getEditTime();
            int rows = this.roleDao.update(dto);
            if (rows != 1) {
                throw new RuntimeException("数据已被其它用户修改！");
            } else {
                if (!old.name.equals(dto.name)) {
                    Authorizes.updateRole(old.name, dto.name);
                }

            }
        }
    }

    public FetchRoleResponse fetch(String id) {
        RoleDto dto = this.roleDao.fetch(id);
        FetchRoleResponse response = new FetchRoleResponse();
        response.setId(dto.id);
        response.setName(dto.name);
        response.setRemark(dto.remark);
        response.setEditTime(dto.editTime);
        return response;
    }

    @Transactional
    public void delete(String id) {
        RoleDto dto = this.roleDao.fetch(id);
        String adminRoleId = this.permissionDao.getAdminRoleId();
        if (adminRoleId.equals(id)) {
            throw new RuntimeException("拒绝对管理员角色的操作");
        } else {
            this.roleDao.deleteAuthorize(id);
            this.roleDao.delete(id);
            Authorizes.removeRole(dto.name);
        }
    }

    public ListRoleResponse list(boolean includeAdmin) {
        ListRoleResponse response = new ListRoleResponse();
        List rows = this.roleDao.list();
        response.setTotal(rows.size());
        String adminRoleId = this.permissionDao.getAdminRoleId();
        ArrayList roles = new ArrayList();
        Iterator var6 = rows.iterator();

        while (var6.hasNext()) {
            RoleDto dto = (RoleDto) var6.next();
            if (includeAdmin || !dto.id.equals(adminRoleId)) {
                RoleInfo info = new RoleInfo();
                info.setId(dto.id);
                info.setName(dto.name);
                info.setRemark(dto.remark);
                roles.add(info);
            }
        }

        response.setRoles(roles);
        return response;
    }

    @Transactional
    public void authorize(String roleId, String permissionId, boolean allow) {
        RoleDto role = this.roleDao.fetch(roleId);
        String adminRoleId = this.permissionDao.getAdminRoleId();
        if (adminRoleId.equals(roleId)) {
            throw new RuntimeException("拒绝对管理员角色的操作");
        } else {
            PermissionDto permission = this.permissionDao.fetch(permissionId);
            Authorizes.authorize(permission.name, role.name, allow);
            if (!allow) {
                this.roleDao.removePermission(roleId, permissionId);
            } else {
                this.roleDao.assignPermission(roleId, permissionId);
            }

        }
    }

    public boolean exists(String key, String id, boolean checkId) {
        return checkId ? this.roleDao.exsitsOutId(key, id) : this.roleDao.exsits(key);
    }

    public RolePermissionsResponse getPermissions(String roleId) {
        RolePermissionsResponse response = new RolePermissionsResponse();
        List permissions = this.roleDao.getPermissions(roleId);
        response.setPermissions(permissions);
        response.setRoleId(roleId);
        return response;
    }
}
