package mmp.gps.logic.component;

import mmp.gps.domain.exist.ExistRequest;
import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.role.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class RoleComponent {

    @Autowired
    private RoleService roleService;


    public RoleComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "role.create",
            description = "创建新的角色信息"
    )
    public void create(CreateRoleRequest request) throws Exception {
        this.roleService.create(request);
    }

    @ServiceMethod(
            value = "role.update",
            description = "更新角色信息"
    )
    public void update(EditRoleRequest request) throws Exception {
        this.roleService.update(request);
    }

    @ServiceMethod(
            value = "role.fetch",
            description = "读取角色信息"
    )
    public FetchRoleResponse fetch(FetchRoleRequest request) throws Exception {
        return this.roleService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "role.delete",
            description = "删除角色信息"
    )
    public boolean delete(DeleteRoleRequest request) throws Exception {
        this.roleService.delete(request.getId());
        return true;
    }

    @ServiceMethod(
            value = "role.list",
            description = "查询角色信息"
    )
    public ListRoleResponse list(ListRoleRequest request) throws Exception {
        return this.roleService.list(request.isIncludeAdmin());
    }

    @ServiceMethod(
            value = "role.authorize",
            description = "角色授权"
    )
    public boolean authorize(AuthorizeRequest request) throws Exception {
        this.roleService.authorize(request.getRoleId(), request.getPermissionId(), request.isAllow());
        return true;
    }

    @ServiceMethod(
            value = "role.exists",
            allowAnoumous = true,
            description = "检查是否已存在角色信息"
    )
    public ExistResponse exists(ExistRequest request) throws Exception {
        ExistResponse response = new ExistResponse();
        response.setExistent(this.roleService.exists(request.getKey(), request.getId(), request.isCheckId()));
        return response;
    }

    @ServiceMethod(
            value = "role.permissions",
            description = "读取角色已授权限"
    )
    public RolePermissionsResponse getPermissions(RolePermissionsRequest request) throws Exception {
        return this.roleService.getPermissions(request.getRoleId());
    }
}
