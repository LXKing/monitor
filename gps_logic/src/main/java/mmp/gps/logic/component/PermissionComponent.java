package mmp.gps.logic.component;

import mmp.gps.domain.exist.ExistRequest;
import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.permission.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class PermissionComponent {

    @Autowired
    private PermissionService permissionService;


    public PermissionComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "permission.create",
            description = "创建新的权限信息"
    )
    public void create(CreatePermissionRequest request) throws Exception {
        this.permissionService.create(request);
    }

    @ServiceMethod(
            value = "permission.fetch",
            description = "读取权限信息"
    )
    public FetchPermissionResponse fetch(FetchPermissionRequest request) throws Exception {
        return this.permissionService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "permission.delete",
            description = "删除权限信息"
    )
    public void delete(DeletePermissionRequest request) throws Exception {
        this.permissionService.delete(request.getId());
    }

    @ServiceMethod(
            value = "permission.list",
            description = "查询权限信息"
    )
    public ListPermissionResponse list() throws Exception {
        return this.permissionService.list();
    }

    @ServiceMethod(
            value = "permission.exists",
            allowAnoumous = true,
            description = "检查是否已存在权限信息"
    )
    public ExistResponse exists(ExistRequest request) throws Exception {
        ExistResponse response = new ExistResponse();
        response.setExistent(this.permissionService.exists(request.getKey(), request.getId(), request.isCheckId()));
        return response;
    }

    @ServiceMethod(
            value = "permission.collect",
            allowAnoumous = true,
            description = "汇集权限信息"
    )
    public CollectPermissionResponse collect() {
        return this.permissionService.collect();
    }
}
